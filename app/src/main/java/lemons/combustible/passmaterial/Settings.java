package lemons.combustible.passmaterial;

import android.content.Context;

import com.bustiblelemons.settings.BaseSettings;


/**
 * Created by hiv on 06.04.15.
 */
public class Settings extends BaseSettings {

    public static final int DEFAULT_WORD_COUNT = 4;

    public static int getWordCount(Context context) {
        return getWordCount(context, DEFAULT_WORD_COUNT);

    }

    public static int getWordCount(Context context, int wordCount) {
        return getInt(context, Keys.WordCount, wordCount);
    }

    private static final class Keys {
        public static String WordCount = "word_count";
    }
}
