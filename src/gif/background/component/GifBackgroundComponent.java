package gif.background.component;

import com.intellij.openapi.components.ProjectComponent;
import gif.background.task.GifBackgroundService;
import gif.background.utils.GifUtils;
import org.apache.commons.lang.StringUtils;

/**
 * gifBackground
 * gif.background.component
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/6 22:15 星期五
 */
public class GifBackgroundComponent implements ProjectComponent {

    @Override
    public void projectOpened() {
        String gifPath = GifUtils.getGifPath();
        if (StringUtils.isEmpty(gifPath)) {
            return;
        }
        GifBackgroundService.restart(gifPath.trim());
    }

    @Override
    public void projectClosed() {

    }
}
