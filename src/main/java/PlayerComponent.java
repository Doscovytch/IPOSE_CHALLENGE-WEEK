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
    private AnimationChannel animIdle, animWalk, animRoll;

    public double scale = 2;

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

        Image image1 = image("player/Pidle.png");
        Image image2 = image("player/Pwalking.png");
        Image image3 = image("player/Prole.png");

        animIdle = new AnimationChannel(image1, 1, 25, 25, Duration.seconds(1), 0, 0);
        animWalk = new AnimationChannel(image2, 2, 25, 25, Duration.seconds(0.33), 0, 1);
        animRoll = new AnimationChannel(image3, 7, 25, 25, Duration.seconds(1), 0, 6);

        texture = new AnimatedTexture(animIdle);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(12, 12));
        entity.getViewComponent().addChild(texture);
        entity.setScaleUniform(scale);
    }

    @Override
    public void onUpdate(double tpf) {
        if (!dodge) {
            if (physics.isMoving()) {
                if (texture.getAnimationChannel() != animWalk) {
                    texture.loopAnimationChannel(animWalk);
                }
            } else {
                if (texture.getAnimationChannel() != animIdle) {
                    texture.loopAnimationChannel(animIdle);
                }
            }
        }

        int dxv = boolToInt(right) - boolToInt(left);
        int dyv = boolToInt(down) - boolToInt(up);
        Point2D vector = new Point2D(dxv, dyv).normalize();

//        if (dxv != 0) {
//            getEntity().setScaleX(dxv*scale);
//        }

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

        texture.loopAnimationChannel(animRoll);

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
