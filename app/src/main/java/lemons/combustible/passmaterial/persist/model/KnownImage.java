package lemons.combustible.passmaterial.persist.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hiv on 11.04.15.
 */
public class KnownImage extends RealmObject {
    private RealmList<KnownPhrase> mKnownPhrases;
    private int                    mId;
    private String                 mUrl;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public RealmList<KnownPhrase> getKnownPhrases() {
        return mKnownPhrases;
    }

    public void setKnownPhrases(RealmList<KnownPhrase> knownPhrases) {
        mKnownPhrases = knownPhrases;
    }
}
