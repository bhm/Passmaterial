package lemons.combustible.passmaterial.passphrases;

import java.util.List;

import lemons.combustible.passmaterial.passphrases.model.PassPhrase;

/**
 * Created by hiv on 31.03.15.
 */
public class PassphraseBundle {
    private List<PassPhrase> mPassPhraseList;

    public List<PassPhrase> getPassPhraseList() {
        return mPassPhraseList;
    }

    public void setPassPhraseList(List<PassPhrase> passPhraseList) {
        mPassPhraseList = passPhraseList;
    }
}
