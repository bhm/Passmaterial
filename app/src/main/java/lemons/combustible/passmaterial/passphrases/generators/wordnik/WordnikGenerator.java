package lemons.combustible.passmaterial.passphrases.generators.wordnik;

import com.bustiblelemons.logging.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import lemons.combustible.passmaterial.passphrases.PassPhrase;
import lemons.combustible.passmaterial.passphrases.PassPhraseConfig;
import lemons.combustible.passmaterial.passphrases.PassPhraseGenerator;
import lemons.combustible.passmaterial.passphrases.PassphraseBundle;
import lemons.combustible.passmaterial.passphrases.Word;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hiv on 01.04.15.
 */
public class WordnikGenerator implements PassPhraseGenerator {

    private static final Logger log = new Logger(WordnikGenerator.class);
    private Subscription mSub;

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
    public void generateBundleAsync(Observer<? super PassPhrase> observer, PassPhraseConfig config) {
        mSub = Observable.create(getPassPhraseFromWordnik(config))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public Subscription getDefinition(Observer<? super Word> observer, Word phrase) {
        return Observable.create(getDefinitionAsyn(phrase))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private Observable.OnSubscribe<Word> getDefinitionAsyn(final Word word) {
        return new Observable.OnSubscribe<Word>() {
            @Override
            public void call(Subscriber<? super Word> subscriber) {
                if (word != null) {
                    getWordDefinition(word);
                    subscriber.onNext(word);
                }
            }
        };
    }

    private Func1<? super PassPhrase, PassPhrase> getDefinitions() {
        return new Func1<PassPhrase, PassPhrase>() {
            @Override
            public PassPhrase call(PassPhrase passPhrase) {
                if (passPhrase != null && passPhrase.getWords() != null) {
                    for (Word word : passPhrase.getWords()) {
                        getWordDefinition(word);
                    }
                }
                return passPhrase;
            }
        };
    }

    private void getWordDefinition(Word word) {
        WordnikDefinitionQuery query = new WordnikDefinitionQuery(word);
        try {
            List<WordnikDefnition> wordnikWords =
                    query.getObjectCollection(WordnikDefnition.class);
            if (wordnikWords != null && wordnikWords.size() > 0) {
                WordnikDefnition wordnikDefnition = wordnikWords.get(0);
                word.setWordDefinition(wordnikDefnition.getWordDefinition());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unsubscribe() {
        if (mSub != null) {
            mSub.unsubscribe();
        }
    }

    public Observable.OnSubscribe<PassPhrase> getPassPhraseFromWordnik(final PassPhraseConfig config) {
        return new Observable.OnSubscribe<PassPhrase>() {
            @Override
            public void call(Subscriber<? super PassPhrase> subscriber) {
                try {
                    WordnikRandomWordsQuery q = new WordnikRandomWordsQuery();
                    q.withLimit(config.getMaxWordCount());
                    List<WordnikWord> object = q.getObjectCollection(WordnikWord.class);
                    PassPhrase phrase = new PassPhrase(config);
                    phrase.addWords(object);
                    subscriber.onNext(phrase);
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        };
    }


    private static class LazyHolder {
        public static final PassPhraseGenerator INSTANCE = new WordnikGenerator();
    }
}
