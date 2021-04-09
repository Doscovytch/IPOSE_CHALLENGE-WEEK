import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Game extends GameApplication {

    private Entity player;




    @Override
    protected void initSettings(GameSettings settings) {
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory(){
//            @Override
            public FXGLMenu newMenu(){
                return new newMenu();
            }
        });
//        settings.setWidth(800);
//        settings.setHeight(800);
//        settings.setTitle("its a Fucking Game");
//        settings.setVersion("0.69");
//        settings.setTicksPerSecond(60);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left = true;
            }
            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).left = false;
            }
        }, KeyCode.A, VirtualButton.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right = true;
            }
            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).right = false;
            }
        }, KeyCode.D, VirtualButton.RIGHT);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).up = true;
            }
            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).up = false;
            }
        }, KeyCode.W, VirtualButton.UP);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).down = true;
            }
            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).down = false;
            }
        }, KeyCode.S, VirtualButton.DOWN);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameFactory());

        player = null;
//        nextLevel();

        // player must be spawned after call to nextLevel, otherwise player gets removed
        // before the update tick _actually_ adds the player to game world
        player = spawn("player", 500, 500);
        set("player", player);

        spawn("background");

        Viewport viewport = getGameScene().getViewport();
//        viewport.setBounds(-1500, -1500, 1500, 1500);
        viewport.bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
        viewport.setZoom(2);
        viewport.setLazy(true);
    }

    @Override
    public void onUpdate(double tpf) {
        System.out.print("\rFPS: " + 1/tpf);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
    }

    @Override
    protected void initUI() {
        Label myText = new Label("Hello There");
        myText.setStyle("-fx-text-fill: white");
        myText.setTranslateX(50);
        myText.setTranslateY(50);
        myText.textProperty().bind(FXGL.getWorldProperties().intProperty("kills").asString());

        FXGL.getGameScene().setBackgroundColor(Color.BLACK);
        FXGL.getGameScene().addUINode(myText);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("kills", 0);
    }

    public static void main(String [] args) {
        launch(args);
    }
}
