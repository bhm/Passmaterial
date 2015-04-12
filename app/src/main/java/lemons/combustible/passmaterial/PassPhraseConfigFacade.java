package lemons.combustible.passmaterial;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hiv on 06.04.15.
 */
public class PassPhraseConfigFacade {
    public static Subscription getConfig(Observer<PassPhraseConfig> observer, File file) {
        return Observable.create(getConfig(file))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    public static Subscription writeConfig(Observer<PassPhraseConfig> observer,
                                           PassPhraseConfig config,
                                           File file) {
        return Observable.create(writeConfig(config, file))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    private static Observable.OnSubscribe<PassPhraseConfig> writeConfig(final PassPhraseConfig config,
                                                                        final File file) {
        return new Observable.OnSubscribe<PassPhraseConfig>() {
            @Override
            public void call(Subscriber<? super PassPhraseConfig> subscriber) {
                if (file != null) {
                    writeDefault(new ObjectMapper(), config, file);
                    subscriber.onNext(config);
                }
            }
        };
    }

    private static Observable.OnSubscribe<PassPhraseConfig> getConfig(final File file) {
        return new Observable.OnSubscribe<PassPhraseConfig>() {
            @Override
            public void call(Subscriber<? super PassPhraseConfig> subscriber) {
                if (file != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    PassPhraseConfig config = new PassPhraseConfig();
                    if (!file.exists() || !(file.length() > 0)) {
                        writeDefault(mapper, config, file);
                    }
                    try {
                        config = mapper.readValue(file, PassPhraseConfig.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(config);
                    subscriber.onCompleted();
                }
            }
        };
    }

    private static void writeDefault(ObjectMapper mapper, PassPhraseConfig config, File file) {
        try {
            mapper.writeValue(file, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
