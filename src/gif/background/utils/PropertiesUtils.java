package gif.background.utils;

import com.intellij.ide.util.PropertiesComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * gifBackground
 * gif.background.utils
 *
 * @author BlueDriver
 * @email cpwu@foxmail.com
 * @date 2019/12/7 14:11 星期六
 */
public class PropertiesUtils {
    public static final String GIF_PATH = "GIF_PATH";
    public static final String GIF_ENABLE = "TRUE";


    public static void removeKey(@NotNull String key) {
        saveValue(key, null);
    }

    public static void saveValue(@NotNull String key, String value) {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        prop.setValue(key, value);
    }

    @Nullable
    public static String getValue(@NotNull String key) {
        return getValue(key, null);
    }


    public static String getValue(@NotNull String key, String defaultValue) {
        PropertiesComponent prop = PropertiesComponent.getInstance();
        String value = prop.getValue(key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    private PropertiesUtils() {
    }
}
