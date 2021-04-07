import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;

public class Player extends Entity {
    boolean left, right, up, down;
    double dx = 0;
    double dy = 0;
    double speed = 5;
    Entity player;

    public Player() {
        player = FXGL.entityBuilder()
                .at(700, 700)
                .viewWithBBox("dude.png")
                .with(new CollidableComponent(true))
                .scale(5, 5)
                .type(EntityTypes.PLAYER)
                .buildAndAttach();
    }


    protected void update() {
        double factor;
        if (dx != 0 || dy != 0) {
            factor = Math.pow((speed*speed) / ((dx*dx) + (dy*dy)), 0.5);
        } else {
            factor = 1;
        }

        player.translateX(dx*factor);
        player.translateY(dy*factor);

        dx = 0;
        dy = 0;
    }
}
