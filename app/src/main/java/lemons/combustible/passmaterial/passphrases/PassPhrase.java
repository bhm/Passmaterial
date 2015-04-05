package lemons.combustible.passmaterial.passphrases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hiv on 31.03.15.
 */
public class PassPhrase implements IPassPhrase {
    private List<Word> mWords;
    private boolean    mUsePadding;
    private boolean    mUseDelimiters;

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
        if (mUsePadding) {
            b.append("---");
        }
        String delimiter = "";
        for (Word w : getWords()) {
            if (w != null) {
                if (mUseDelimiters) {
                    b.append(delimiter);
                    delimiter = ",";
                }
                b.append(w.getWordText());
            }
        }
        if (mUsePadding) {
            b.append("---");
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
