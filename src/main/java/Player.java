import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;

public class Player extends Component {
    boolean left, right, up, down;
    double dx = 0;
    double dy = 0;
    double speed = 5;
    Entity player;

    private PhysicsComponent physics;

//    public Player() {
//        player = FXGL.entityBuilder()
//                .at(700, 700)
//                .viewWithBBox("dude.png")
//                .with(new CollidableComponent(true)
//                .with(physics)
//                .scale(5, 5)
//                .type(EntityTypes.PLAYER)
//                .buildAndAttach();
//    }

    @Override
    public void onUpdate(double tpf) {
//        double factor;
//        if (dx != 0 || dy != 0) {
//            factor = Math.pow((speed*speed) / ((dx*dx) + (dy*dy)), 0.5);
//        } else {
//            factor = 1;
//        }
//
//        player.translateX(dx*factor);
//        player.translateY(dy*factor);
//
//        dx = 0;
//        dy = 0;
    }

    public void left() {
//        getEntity().setScaleX(-1);
        physics.setVelocityX(-170);
    }

    public void right() {
//        getEntity().setScaleX(1);
        physics.setVelocityX(170);
    }

    public void stop() {
        physics.setVelocityX(0);
    }

    public double getX() {
        return player.getX();
    }

    public double getY() {
        return player.getY();
    }
}
