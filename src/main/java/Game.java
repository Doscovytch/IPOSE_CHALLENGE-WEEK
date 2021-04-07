import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends GameApplication {

    private Player player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(800);
        settings.setTitle("its a Fucking Game");
        settings.setVersion("0.69");
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.D, () -> player.dx += player.speed);
        FXGL.onKey(KeyCode.A, () -> player.dx -= player.speed);
        FXGL.onKey(KeyCode.W, () -> player.dy -= player.speed);
        FXGL.onKey(KeyCode.S, () -> player.dy += player.speed);
    }

    @Override
    protected void initGame() {
        player = new Player();
    }

    @Override
    public void onUpdate(double tpf) {
        player.update();
    }

    @Override
    protected void initPhysics() {

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

    public static void main(String [] args) {
        launch(args);
    }
}
