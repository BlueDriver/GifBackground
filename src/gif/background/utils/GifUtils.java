package gif.background.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ui.UIUtil;
import gif.background.bo.FrameInfo;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * backgroundGif
 * bg.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/6 16:33 星期五
 */
public class GifUtils {
    private static final Logger log = Logger.getInstance(GifUtils.class);

    private static final String DEFAULT_TARGET_FOLDER = "D:\\.GifBackground\\";

    @Nullable
    public static List<FrameInfo> getFrameList(String gifPath) {
        if (StringUtils.isEmpty(gifPath)) {
            gifPath = PropertiesUtils.getValue(PropertiesUtils.GIF_PATH);
        }
        if (StringUtils.isEmpty(gifPath)) {
            return null;
        }
        return convertGif(gifPath, 1F);
    }

    /**
     * convert gif image into png images
     */
    @Nullable
    public static List<FrameInfo> convertGif(@NotNull String gifPath, float opacity) {
        //region check if gif file exists
        FileInputStream inputStream;
        String targetFolder;
        try {
            File gifFile = new File(gifPath);
            if (!gifFile.exists()) {
                throw new Exception("Gif File Not Found: " + gifPath);
            }
            if (gifFile.isDirectory()) {
                DialogUtils.showError(gifPath + "\nThis is a directory path!");
                return null;
            }
            targetFolder = gifFile.getParentFile().getAbsolutePath();
            if (StringUtils.isEmpty(targetFolder)) {
                targetFolder = DEFAULT_TARGET_FOLDER;
            } else {
                targetFolder += "\\.GifBackground\\";
            }
            inputStream = new FileInputStream(gifPath);
        } catch (Exception e) {
            log.warn(e);
            DialogUtils.showError(e.getMessage());
            return null;
        }
        //endregion

        //region make dir for target folder
        File folder = new File(targetFolder);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
        folder.mkdirs();
        //endregion

        List<FrameInfo> list = new ArrayList<>();

        final GifDecoder.GifImage gif;
        try {
            gif = GifDecoder.read(inputStream);
            final int frameCount = gif.getFrameCount();
            String targetFilePath;
            FrameInfo info;
            BufferedImage img;
            int delay;
            for (int i = 0; i < frameCount; i++) {
                img = gif.getFrame(i);
                delay = gif.getDelay(i);
                img = setOpacity(img, opacity);
                targetFilePath = targetFolder + System.currentTimeMillis() + "-" + i;
                ImageIO.write(img, "png", new File(targetFilePath));

                info = new FrameInfo();
                info.setDelay(delay);
                info.setPath(targetFilePath);

                list.add(info);
            }
        } catch (IOException e) {
            DialogUtils.showError(e.getMessage());
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e1) {
                log.warn(e1);
            }
        }

        return list;
    }

    public static BufferedImage setOpacity(BufferedImage image, float opacity) {
        BufferedImage img = UIUtil.createImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        g.dispose();
        return img;
    }

    private GifUtils() {
    }
}
