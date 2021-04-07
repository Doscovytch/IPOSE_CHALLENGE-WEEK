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

    public boolean dodge = false;
    public boolean isDodge = false;
    public Point2D dodgeVec;
    public double dodgeStartTime;
    public double dodgeDuration;
    public double dodgeSpeed = 300;




    public PlayerComponent() {

//        Image image = image("wawamn.png");
//
//        animIdle = new AnimationChannel(image, 4, 32, 42, Duration.seconds(1), 1, 1);
//        animWalk = new AnimationChannel(image, 4, 32, 42, Duration.seconds(0.66), 0, 3);
//
//        texture = new AnimatedTexture(animIdle);
//        texture.loop();
    }

    @Override
    public void onAdded() {
//        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
//        entity.getViewComponent().addChild(texture);
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
        Point2D vector = new Point2D(dxv, dyv).normalize();

        if (dodge) {
            if (isDodge) {
                Dodge();
            } else {
                Dodge(System.currentTimeMillis(), 500, vector);
            }
        } else {
            vector = vector.multiply(speed);
            physics.setLinearVelocity(vector);
        }

    }

    private void Dodge(double startTime, double duration, Point2D vec) {
        dodgeStartTime = startTime;
        dodgeDuration = duration;

        isDodge = true;
        dodgeVec = vec;
        physics.setLinearVelocity(dodgeVec.multiply(dodgeSpeed));
    }

    private void Dodge() {
        if(System.currentTimeMillis()-dodgeStartTime < dodgeDuration) {
            physics.setLinearVelocity(dodgeVec.multiply(dodgeSpeed));
        } else {
            dodge = false;
            isDodge = false;
        }
    }
}
