package lemons.combustible.passmaterial.passphrases.generators;

import lemons.combustible.passmaterial.passphrases.PassPhraseGenerator;
import lemons.combustible.passmaterial.passphrases.generators.wordnik.WordnikGenerator;

/**
 * Created by hiv on 01.04.15.
 */
public class PassphraseGenerators {
    public static PassPhraseGenerator getGenerator(PreferredGenerator preferredGenerator) {
        return WordnikGenerator.getGenerator();
    }
}
