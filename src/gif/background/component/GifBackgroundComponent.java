package gif.background.component;

import com.intellij.openapi.components.ProjectComponent;
import gif.background.task.GifBackgroundService;
import gif.background.utils.PropertiesUtils;
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
        String value = PropertiesUtils.getValue(PropertiesUtils.GIF_ENABLE);
        if (StringUtils.isEmpty(value)) {
            return;
        }

        String gifPath = PropertiesUtils.getValue(PropertiesUtils.GIF_PATH);
        if (StringUtils.isEmpty(gifPath)) {
            return;
        }
        GifBackgroundService.restart(gifPath.trim());
    }

    @Override
    public void projectClosed() {

    }
}
