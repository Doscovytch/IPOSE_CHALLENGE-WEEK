import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class CamTargetComponent extends Component {

    private Entity player;



    @Override
    public void onAdded() {

    }

    @Override
    public void onUpdate(double tpf) {
        if (player == null) {
            player = entity.getWorld().getSingletonOptional(EntityTypes.PLAYER).orElse(null);
        }

        Point2D tarPos = player.getCenter();
        Point2D pos = entity.getPosition();

        double lspeed = 5000/pos.distance(tarPos);
        
        double newX = pos.getX();
        double newY = pos.getY();

        if (pos.getX() != tarPos.getX()) {
            newX = pos.getX() + (tarPos.getX() - pos.getX()) / lspeed;
        }
        if (pos.getY() != tarPos.getY()) {
            newY = pos.getY()+(tarPos.getY() - pos.getY())/lspeed;
        }

        entity.setX(newX);
        entity.setY(newY);
    }
}
