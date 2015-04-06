package lemons.combustible.passmaterial.passphrases.generators.wordnik;

/**
 * Created by hiv on 05.04.15.
 */
public class WordnikRandomWordsQuery extends AbsWordnikQuery<WordnikWord> {
    private static final String sMethod = "/v4/words.json/randomWords";

    @Override
    public String getMethod() {
        return sMethod;
    }
}
