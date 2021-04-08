import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Game extends GameApplication {

    private Entity player;
    private Entity weapon;
    private Entity camTarget;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(800);
        settings.setTitle("its a Fucking Game");
        settings.setVersion("0.69");
        settings.setTicksPerSecond(60);
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

        getInput().addAction(new UserAction("Dodge") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).dodge = true;
            }
        }, KeyCode.SPACE, VirtualButton.B);
    }

    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundRepeat("background/void.png");
        getGameWorld().addEntityFactory(new GameFactory());

        player = null;
        weapon = null;
        camTarget = null;
//        nextLevel();



        player = spawn("player", 500, 500);
        set("player", player);

        weapon = spawn("sword", 400, 400);
        camTarget = spawn("camTarget", 500, 500);

        Viewport viewport = getGameScene().getViewport();
        viewport.bindToEntity(camTarget, getAppWidth() / 2, getAppHeight() / 2);
        viewport.setZoom(2);
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
