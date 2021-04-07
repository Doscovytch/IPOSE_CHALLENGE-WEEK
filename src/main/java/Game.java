import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.input.KeyCode;


public class Game extends GameApplication {

    private Player player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(800);
        settings.setTitle("its a Fucking Game");
        settings.setVersion("0.69");
        settings.setTicksPerSecond(60);
        player.player.getPosition().
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
        player = new Player("dude.png");
    }

    @Override
    public void onUpdate(double tpf) {
        player.update();
    }




    public static void main(String [] args) {
        launch(args);
    }
}
