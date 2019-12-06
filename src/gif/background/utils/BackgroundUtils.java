package gif.background.utils;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;

/**
 * backgroundGif
 * bg.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/6 15:59 星期五
 */
public class BackgroundUtils {

    public static void clearBackground() {
        setBackground(null);
    }

    public static void setBackground(String path) {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        prop.setValue(IdeBackgroundUtil.FRAME_PROP, path);
        prop.setValue(IdeBackgroundUtil.EDITOR_PROP, path);
        IdeBackgroundUtil.repaintAllWindows();
    }

    private BackgroundUtils() {
    }
}
