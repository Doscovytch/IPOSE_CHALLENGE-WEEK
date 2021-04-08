import com.almasb.fxgl.dsl.components.LiftComponent;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.ui.FontType;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameFactory implements EntityFactory {


//    @Spawns("exitTrigger")
//    public Entity newExitTrigger(SpawnData data) {
//        return entityBuilder(data)
//                .type(EXIT_TRIGGER)
//                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
//                .with(new CollidableComponent(true))
//                .build();
//    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
//        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));

        // this avoids player sticking to walls
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
            .type(EntityTypes.PLAYER)
                .bbox(new HitBox(new Point2D(5,5), BoundingShape.circle(12)))
                .bbox(new HitBox(new Point2D(10,25), BoundingShape.box(10, 17)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("sword")
    public Entity newSword(SpawnData data) {

        return entityBuilder(data)
                .type(EntityTypes.WEAPON)
                .viewWithBBox("weapons/Psword.png")
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new WeaponComponent())
                .build();
    }

    @Spawns("camTarget")
    public Entity newCamTarget(SpawnData data) {
        System.out.println(data);
        return entityBuilder(data)
                .type(EntityTypes.CAMTAR)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new CamTargetComponent())
                .build();
    }
}
