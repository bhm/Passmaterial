package lemons.combustible.passmaterial.passphrases.generators.wordnik;

import android.net.Uri;

import java.util.Locale;

import lemons.combustible.passmaterial.passphrases.Word;

/**
 * Created by hiv on 06.04.15.
 */
public class WordnikDefinitionQuery extends AbsWordnikQuery<WordnikDefnition> {
    private static final String sMethodPattern = "v4/word.json/%s/definitions";
    private Word mWord;

    public WordnikDefinitionQuery(Word word) {
        mWord = word;
        withLimit(1);
    }

    @Override
    public String getMethod() {
        if (mWord != null && mWord.getWordText() != null) {
            return String.format(Locale.ENGLISH, sMethodPattern, Uri.encode(mWord.getWordText().toString()));
        }
        return null;
    }
}
