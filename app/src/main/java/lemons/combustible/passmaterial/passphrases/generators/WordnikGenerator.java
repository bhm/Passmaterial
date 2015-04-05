package lemons.combustible.passmaterial.passphrases.generators;

import com.bustiblelemons.logging.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import lemons.combustible.passmaterial.passphrases.OnPassPhraseGenerated;
import lemons.combustible.passmaterial.passphrases.PassPhrase;
import lemons.combustible.passmaterial.passphrases.PassPhraseGenerator;
import lemons.combustible.passmaterial.passphrases.PassphraseBundle;
import lemons.combustible.passmaterial.passphrases.generators.wordnik.WordnikRandomWordsQuery;
import lemons.combustible.passmaterial.passphrases.generators.wordnik.WordnikWord;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hiv on 01.04.15.
 */
public class WordnikGenerator implements PassPhraseGenerator, Observable.OnSubscribe<PassPhrase> {

    private static final Logger log = new Logger(WordnikGenerator.class);
    private Subscription          mSub;
    private OnPassPhraseGenerated mCallback;
    private Observer<? super PassPhrase> observer = new Observer<PassPhrase>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(PassPhrase bundle) {
            if (mCallback != null) {
                mCallback.onPassPhraseGenerated(bundle);
            }

        }
    };

    private WordnikGenerator() {
    }

    public static PassPhraseGenerator getGenerator() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public PassphraseBundle generatePassphraseBundle(int seed) {
        return null;
    }

    @Override
    public void generateBundleAsync(OnPassPhraseGenerated callback) {
        mCallback = callback;
        mSub = Observable.create(this)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void unsubscribe() {
        if (mSub != null) {
            mSub.unsubscribe();
        }
        mCallback = null;
    }

    @Override
    public void call(Subscriber<? super PassPhrase> subscriber) {
        try {
            WordnikRandomWordsQuery q = new WordnikRandomWordsQuery();
            q.withLimit(10);
            List<WordnikWord> object = q.getObjectCollection(WordnikWord.class);
            PassPhrase phrase = new PassPhrase();
            phrase.addWords(object);
            subscriber.onNext(phrase);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder {
        public static final PassPhraseGenerator INSTANCE = new WordnikGenerator();
    }
}
