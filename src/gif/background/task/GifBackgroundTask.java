package gif.background.task;

import com.intellij.openapi.application.ApplicationManager;
import gif.background.bo.FrameInfo;
import gif.background.utils.BackgroundUtils;

import java.util.List;

/**
 * backgroundGif
 * bg.task
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/6 17:03 星期五
 */
public class GifBackgroundTask implements Runnable {
    private int index;
    private List<FrameInfo> frameInfoList;

    public GifBackgroundTask(List<FrameInfo> frameInfoList) {
        this.index = 0;
        this.frameInfoList = frameInfoList;
    }

    @Override
    public void run() {
        index++;
        if (index >= frameInfoList.size()) {
            index = 0;
        }
        ApplicationManager.getApplication().invokeLater(() -> {
            BackgroundUtils.setBackground(frameInfoList.get(index).getPath());
        });
    }
}
