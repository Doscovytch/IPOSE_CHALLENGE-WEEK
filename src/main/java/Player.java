import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;


final public class Player {
    Entity player;
    double speed = 5;
    protected transient double dx = 0;
    protected transient double dy = 0;

    public Player(String sprite_path) {
        player = init(sprite_path, true);
    }

    public Player(String sprite_path, boolean collisions) {
        player = init(sprite_path, collisions);
    }

    private Entity init(String sprite_path, boolean collisions)
    {
        return FXGL.entityBuilder()
                .at(700, 700)
                .viewWithBBox(sprite_path)
                .with(new CollidableComponent(collisions))
                .scale(5, 5)
                .type(EntityTypes.PLAYER)
                .buildAndAttach();
    }


    protected void update() {
        byte xsign = 1, ysign = 1;
        if (dx != 0 && dy != 0) {
            if (dx < 0)
            {
                xsign = -1;
            }
            if(dy < 0)
            {
                ysign = -1;
            }


            dx = dy = Math.pow(speed*speed / 2, 0.5);

        }

        player.translate(dx * xsign, dy * ysign);
        dx = 0;
        dy = 0;
    }

    public double getX() {
        return player.getX();
    }

    public double getY() {
        return player.getY();
    }
}
