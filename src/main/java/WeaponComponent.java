import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class WeaponComponent extends Component {
    public Entity target;
    public double scale = 1;

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(4, 21));
        entity.getTransformComponent().setRotationOrigin(new Point2D(4, 21));
        entity.setScaleUniform(scale);
    }

    @Override
    public void onUpdate(double tpf) {
        if (target == null) {
            target = entity.getWorld().getSingletonOptional(EntityTypes.PLAYER).orElse(null);
        }

//        target.getComponent()
        Point2D tarPos = target.getPosition().add((target.getWidth()-10), 0);
        entity.setPosition(tarPos);

        entity.rotateBy(1);

        System.out.println(entity.getTransformComponent().getRotationOrigin());
    }
}
