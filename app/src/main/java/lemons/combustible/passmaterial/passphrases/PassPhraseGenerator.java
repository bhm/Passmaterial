package lemons.combustible.passmaterial.passphrases;

/**
 * Created by hiv on 01.04.15.
 */
public interface PassPhraseGenerator {
    PassphraseBundle generatePassphraseBundle(int seed);

    void generateBundleAsync(OnPassPhraseGenerated callback);

    void unsubscribe();
}
