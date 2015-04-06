package lemons.combustible.passmaterial.google;

import com.bustiblelemons.google.apis.model.GoogleImageObject;
import com.bustiblelemons.google.apis.model.GoogleImageResponse;
import com.bustiblelemons.google.apis.search.params.GImageSearch;
import com.bustiblelemons.google.apis.search.params.GoogleImageSearch;
import com.bustiblelemons.model.OnlinePhotoUrl;
import com.bustiblelemons.model.OnlinePhotoUrlImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lemons.combustible.passmaterial.passphrases.Word;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hiv on 06.04.15.
 */
public class ImagesRetriever {

    public static final ImagesRetriever getImagesRetriever() {
        return LazyImagesRetrieverHolder.INSTANCE;
    }

    public void getImagesFor(Observer<Collection<OnlinePhotoUrl>> observer, Collection<Word> words) {
        Observable.create(getImages(words))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private Observable.OnSubscribe<Collection<OnlinePhotoUrl>> getImages(final Collection<Word> words) {
        return new Observable.OnSubscribe<Collection<OnlinePhotoUrl>>() {
            @Override
            public void call(Subscriber<? super Collection<OnlinePhotoUrl>> subscriber) {
                Collection<OnlinePhotoUrl> images = new ArrayList<OnlinePhotoUrl>();
                if (words != null) {
                    for (Word word : words) {
                        if (word != null && word.getWordText() != null) {
                            List<OnlinePhotoUrl> imagesFor = getImagesFor(word);
                            if (imagesFor != null) {
                                images.addAll(imagesFor);
                            }
                        }
                    }
                }
                subscriber.onNext(images);
            }
        };
    }

    private List<OnlinePhotoUrl> getImagesFor(Word word) {
        CharSequence wordText = word.getWordText();
        GoogleImageSearch.Options opts = new GoogleImageSearch.Options();
        opts.setQuery(wordText.toString());
        opts.setResultsPerPage(1);
        GImageSearch search = opts.build();
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpResponse httpResponse = search.query();
            InputStream in = httpResponse.getEntity().getContent();
            GoogleImageResponse response = mapper.readValue(in, GoogleImageResponse.class);
            List<OnlinePhotoUrl> photoUrls = new ArrayList<OnlinePhotoUrl>();
            for (GoogleImageObject o : response.getResults()) {
                OnlinePhotoUrl opu = new OnlinePhotoUrlImpl(o.getUrl());
                photoUrls.add(opu);
            }
            return photoUrls;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final class LazyImagesRetrieverHolder {
        private static final ImagesRetriever INSTANCE = new ImagesRetriever();
    }
}
