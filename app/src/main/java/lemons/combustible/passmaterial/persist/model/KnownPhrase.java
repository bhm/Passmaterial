package lemons.combustible.passmaterial.persist.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hiv on 11.04.15.
 */
public class KnownPhrase extends RealmObject {
    private String                mPhrase;
    private int                   id;
    private RealmList<KnownImage> mKnownImages;

    public String getPhrase() {
        return mPhrase;
    }

    public void setPhrase(String phrase) {
        mPhrase = phrase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<KnownImage> getKnownImages() {
        return mKnownImages;
    }

    public void setKnownImages(RealmList<KnownImage> knownImages) {
        mKnownImages = knownImages;
    }
}
