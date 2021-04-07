import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;


import static com.almasb.fxgl.dsl.FXGL.image;
import static com.almasb.fxgl.dsl.FXGL.spawnFadeIn;
import static utils.Util.*;

public class PlayerComponent extends Component {

    private PhysicsComponent physics;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;

    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    public boolean down = false;

    public double speed = 200;

    public PlayerComponent() {

//        Image image = image("dude.png");
//
//        animIdle = new AnimationChannel(image, 1, 64, 64, Duration.seconds(1), 1, 1);
//        animWalk = new AnimationChannel(image, 1, 64, 64, Duration.seconds(1), 1, 1);
//
//        texture = new AnimatedTexture(animIdle);
//        texture.loop();
    }

    @Override
    public void onAdded() {
//        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
//        entity.getViewComponent().addChild(texture);

//        physics.onGroundProperty().addListener((obs, old, isOnGround) -> {
//            if (isOnGround) {
//                //play("land.wav");
//                jumps = 2;
//            }
//        });
    }

    @Override
    public void onUpdate(double tpf) {
//        if (physics.isMovingX()) {
//            if (texture.getAnimationChannel() != animWalk) {
//                texture.loopAnimationChannel(animWalk);
//            }
//        } else {
//            if (texture.getAnimationChannel() != animIdle) {
//                texture.loopAnimationChannel(animIdle);
//            }
//        }

        int dxv = boolToInt(right) - boolToInt(left);
        int dyv = boolToInt(down) - boolToInt(up);
        physics.setLinearVelocity(new Point2D(dxv*speed, dyv*speed));

        System.out.println(" x:" + entity.getX() + " y:" + entity.getY());
    }
}
