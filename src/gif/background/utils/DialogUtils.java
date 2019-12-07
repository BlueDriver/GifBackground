package gif.background.utils;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * backgroundGif
 * bg.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/6 16:41 星期五
 */
public class DialogUtils {
    public static final String DEFAULT_TITLE = "Gif Background";

    public static void showInfo(@NotNull String message) {
        ApplicationManager.getApplication().invokeLater(() -> Messages.showInfoMessage(message, DEFAULT_TITLE));
    }

    public static void showError(@NotNull String message) {
        ApplicationManager.getApplication().invokeLater(() -> Messages.showErrorDialog(message, DEFAULT_TITLE));
    }

    private DialogUtils() {
    }
}
