package lemons.combustible.passmaterial.passphrases.generators.wordnik;

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
        return String.format(Locale.ENGLISH, sMethodPattern, mWord.getWordText());
    }
}
