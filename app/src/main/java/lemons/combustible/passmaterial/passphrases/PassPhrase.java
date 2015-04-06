package lemons.combustible.passmaterial.passphrases;

import org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hiv on 31.03.15.
 */
public class PassPhrase implements IPassPhrase {
    private final Delimiters       mDelimiters;
    private       List<Word>       mWords;
    private       boolean          mUsePadding;
    private       boolean          mUseDelimiters;
    private       int              mPaddingLength;
    private       PassPhraseConfig mConfig;

    public PassPhrase(PassPhraseConfig config) {
        mUsePadding = config.getUsePadding();
        mPaddingLength = config.getPaddingLength();
        mUseDelimiters = config.getUseDelimiters();
        mDelimiters = config.getDelimiters();
    }

    public int getPaddingLength() {
        return mPaddingLength;
    }

    public PassPhrase withPaddingLenghth(int arg) {
        mPaddingLength = arg;
        return this;
    }

    public List<Word> getWords() {
        if (mWords == null) {
            mWords = new ArrayList<Word>();
        }
        return mWords;
    }

    public void setWords(List<Word> words) {
        mWords = words;
    }

    public boolean isUsePadding() {
        return mUsePadding;
    }

    public void setUsePadding(boolean usePadding) {
        mUsePadding = usePadding;
    }

    public boolean isUseDelimiters() {
        return mUseDelimiters;
    }

    public void setUseDelimiters(boolean useDelimiters) {
        mUseDelimiters = useDelimiters;
    }

    @Override
    public CharSequence getText() {
        StringBuilder b = new StringBuilder();
        String padding = RandomStringUtils.random(mPaddingLength, true, true);
        if (mUsePadding) {
            b.append(padding);
        }
        String delimiter = "";
        for (Word w : getWords()) {
            if (w != null) {
                if (mUseDelimiters) {
                    b.append(delimiter);
                    delimiter = mDelimiters.getRandomDelimiter();
                }
                b.append(w.getWordText());
            }
        }
        if (mUsePadding) {
            b.append(padding);
        }
        return b.toString();
    }

    public <W extends Word> void addWords(Collection<W> words) {
        if (mWords == null) {
            mWords = new ArrayList<Word>();
            mWords.addAll(words);
        }
    }

    public <W extends Word> void addWord(W word) {
        if (mWords == null) {
            mWords = new ArrayList<Word>();
            mWords.add(word);
        }
    }

    @Override
    public String toString() {
        return "PassPhrase{" +
                "mWords=" + mWords +
                ", mUsePadding=" + mUsePadding +
                ", mUseDelimiters=" + mUseDelimiters +
                '}';
    }
}
