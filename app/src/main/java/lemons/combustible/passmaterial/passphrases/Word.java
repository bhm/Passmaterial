package lemons.combustible.passmaterial.passphrases;

/**
 * Created by hiv on 05.04.15.
 */
public interface Word {
    CharSequence getWordText();

    CharSequence getWordDefinition();

    void setWordDefinition(CharSequence sequence);

    boolean hasDefinition();
}
