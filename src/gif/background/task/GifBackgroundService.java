package gif.background.task;

import com.intellij.openapi.diagnostic.Logger;
import gif.background.bo.FrameInfo;
import gif.background.utils.BackgroundUtils;
import gif.background.utils.DialogUtils;
import gif.background.utils.GifUtils;
import gif.background.utils.PropertiesUtils;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * backgroundGif
 * bg.task
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/6 17:18 星期五
 */
public class GifBackgroundService {
    private static final Logger log = Logger.getInstance(GifBackgroundService.class);

    private static ScheduledExecutorService executorService = null;

    public static void start(String gifPath) {
        List<FrameInfo> list = GifUtils.getFrameList(gifPath);
        if (list == null || list.isEmpty()) {
            return;
        }
        if (executorService != null) {
            stop();
        }

        GifBackgroundTask task = new GifBackgroundTask(list);

        executorService = Executors.newSingleThreadScheduledExecutor();
        try {
            BackgroundUtils.clearBackground();
            int delay = list.get(0).getDelay();
            log.warn("default delay: " + delay);
            //higher delay higher performance
            if (delay <= 100) {
                delay = 100;
            }
            executorService.scheduleAtFixedRate(task, delay, delay, TimeUnit.MILLISECONDS);

            PropertiesUtils.saveValue(PropertiesUtils.GIF_PATH, gifPath);
        } catch (Exception e) {
            log.warn(e);
            stop();
            DialogUtils.showError(e.getMessage());
        }
    }


    public static void restart(String gifPath) {
        stop();
        start(gifPath);
    }

    public static void stop() {
        if (executorService != null && !executorService.isTerminated()) {
            executorService.shutdownNow();
        }
        executorService = null;
    }
}
