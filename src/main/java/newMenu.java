import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.beans.binding.StringBinding;

public class newMenu extends FXGLMenu{

    public newMenu() {
        super(MenuType.MAIN_MENU);
    }

    protected Texture createActionButton(StringBinding stringBinding, Runnable runnable){
        return FXGL.texture("background/void.png");
    }
}
