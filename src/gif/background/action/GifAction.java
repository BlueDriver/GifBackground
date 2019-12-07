package gif.background.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import gif.background.task.GifBackgroundService;
import gif.background.utils.BackgroundUtils;
import gif.background.utils.DialogUtils;
import gif.background.utils.PropertiesUtils;
import org.apache.commons.lang.StringUtils;

/**
 * backgroundGif
 * bg.action
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/6 15:28 星期五
 */
public class GifAction extends AnAction {
    private static final String SET_GIF = "Set GIF";
    private static final String CLEAR_GIF = "Clear GIF";

    @Override
    public void actionPerformed(AnActionEvent e) {
        final String menuText = e.getPresentation().getText();

        if (SET_GIF.equals(menuText)) {

            String gifPath = PropertiesUtils.getValue(PropertiesUtils.GIF_PATH);

            gifPath = Messages.showInputDialog("Input GIF File Path", DialogUtils.DEFAULT_TITLE,
                    Messages.getInformationIcon(), gifPath, new InputValidator() {
                        @Override
                        public boolean checkInput(String s) {
                            if (s.trim().length() <= 0) {
                                return false;
                            }
                            return s.endsWith(".gif") || s.endsWith(".GIF");
                        }

                        @Override
                        public boolean canClose(String s) {
                            return true;
                        }
                    });
            if (StringUtils.isEmpty(gifPath)) {
                return;
            }
            GifBackgroundService.restart(gifPath.trim());

            PropertiesUtils.saveValue(PropertiesUtils.GIF_ENABLE, "TRUE");

        } else if (CLEAR_GIF.equals(menuText)) {
            GifBackgroundService.stop();
            BackgroundUtils.clearBackground();
            PropertiesUtils.removeKey(PropertiesUtils.GIF_ENABLE);
        }
    }
}
