import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;

public class Player {
    Entity player;
    double speed = 5;
    transient double dx = 0;
    transient double dy = 0;

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
        if (dx != 0 && dy != 0) {
            dx = dy = Math.pow((speed*speed) / 2, 0.5);
        }

        player.translate(dx, dy);
        dx = dy = 0;
    }

    public double getX() {
        return player.getX();
    }

    public double getY() {
        return player.getY();
    }
}
