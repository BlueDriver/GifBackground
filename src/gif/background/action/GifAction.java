package gif.background.action;

import gif.background.task.GifBackgroundService;
import gif.background.utils.BackgroundUtils;
import gif.background.utils.DialogUtils;
import gif.background.utils.GifUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
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

            String gifPath = GifUtils.getGifPath();

            gifPath = Messages.showInputDialog("Input GIF file path", DialogUtils.DEFAULT_TITLE,
                    Messages.getInformationIcon(), gifPath, new InputValidator() {
                        @Override
                        public boolean checkInput(String s) {
                            return s.trim().length() > 0;
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
        } else if (CLEAR_GIF.equals(menuText)) {
            GifBackgroundService.stop();
            BackgroundUtils.clearBackground();
        }
    }
}
