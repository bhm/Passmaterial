package lemons.combustible.passmaterial.passphrases;

import java.io.Serializable;

/**
 * Created by hiv on 05.04.15.
 */
public interface Word extends Serializable {
    CharSequence getWordText();

    CharSequence getWordDefinition();

    void setWordDefinition(CharSequence sequence);

    boolean hasDefinition();
}
