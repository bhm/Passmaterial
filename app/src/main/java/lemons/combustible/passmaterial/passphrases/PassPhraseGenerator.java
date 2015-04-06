package lemons.combustible.passmaterial.passphrases;

import rx.Observer;
import rx.Subscription;

/**
 * Created by hiv on 01.04.15.
 */
public interface PassPhraseGenerator {
    PassphraseBundle generatePassphraseBundle(int seed);

    void generateBundleAsync(Observer<? super PassPhrase> observer);

    Subscription getDefinition(Observer<? super Word> observer, Word phrase);

    void unsubscribe();


}
