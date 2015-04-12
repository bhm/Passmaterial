package lemons.combustible.passmaterial.passphrases;

import lemons.combustible.passmaterial.passphrases.model.PassPhrase;
import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;
import rx.Observer;
import rx.Subscription;

/**
 * Created by hiv on 01.04.15.
 */
public interface PassPhraseGenerator {
    PassphraseBundle generatePassphraseBundle(int seed);

    void generateBundleAsync(Observer<? super PassPhrase> observer, PassPhraseConfig config);

    Subscription getDefinition(Observer<? super Word> observer, Word phrase);

    void unsubscribe();


}
