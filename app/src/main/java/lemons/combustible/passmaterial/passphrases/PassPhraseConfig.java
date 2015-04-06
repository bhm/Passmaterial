package lemons.combustible.passmaterial.passphrases;

import android.content.Context;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiv on 06.04.15.
 */
public class PassPhraseConfig implements Serializable {
    public static final int DEFAULT_MAX_WORD_COUNT = 10;
    public static final int DEFAULT_WORD_COUNT     = 4;
    @JsonProperty("use_padding")
    private boolean    mUsePadding;
    @JsonProperty("use_delimiters")
    private boolean    mUseDelimiters;
    @JsonProperty("words")
    private List<Word> mWords;
    @JsonProperty("delimiters")
    private Delimiters mDelimiters;
    @JsonProperty("word_count")
    private int        mWordCount;
    @JsonProperty("max_word_count")
    private int        mMaxWordCount;
    @JsonProperty("padding_length")
    private int        mPaddingLength;

    public PassPhraseConfig() {
        mWordCount = DEFAULT_WORD_COUNT;
        mMaxWordCount = DEFAULT_MAX_WORD_COUNT;
        mUseDelimiters = true;
        mUsePadding = true;
        mWords = new ArrayList<Word>(0);
        mDelimiters = Delimiters.DEFAULT;
    }

    public static File getConfigFile(Context context) {
        String filename = PassPhraseConfig.class.getSimpleName().concat(".json");
        File rootDir = context.getCacheDir();
        return new File(rootDir, filename);
    }

    @JsonProperty("max_word_count")
    public int getMaxWordCount() {
        return mMaxWordCount;
    }

    @JsonProperty("max_word_count")
    public void setMaxWordCount(int maxWordCount) {
        mMaxWordCount = maxWordCount;
    }

    @JsonProperty("max_word_count")
    public PassPhraseConfig withMaxWordCount(int arg) {
        mMaxWordCount = arg;
        return this;
    }

    @JsonProperty("word_count")
    public int getWordCount() {
        return mWordCount;
    }

    @JsonProperty("word_count")
    public void setWordCount(int wordCount) {
        mWordCount = wordCount;
    }

    @JsonIgnore
    public PassPhraseConfig withWordCount(int arg) {
        mWordCount = arg;
        return this;
    }

    @JsonProperty("padding_length")
    public int getPaddingLength() {
        return mPaddingLength;
    }

    @JsonProperty("padding_length")
    public void setPaddingLength(int paddingLength) {
        mPaddingLength = paddingLength;
    }

    @JsonIgnore
    public PassPhraseConfig withPaddingLenghth(int arg) {
        mPaddingLength = arg;
        return this;
    }

    @JsonProperty("use_padding")
    public boolean getUsePadding() {
        return mUsePadding;
    }

    @JsonProperty("use_padding")
    public void setUsePadding(boolean usePadding) {
        mUsePadding = usePadding;
    }

    @JsonIgnore
    public PassPhraseConfig withUsePadding(boolean arg) {
        mUsePadding = arg;
        return this;
    }

    @JsonProperty("use_delimiters")
    public boolean getUseDelimiters() {
        return mUseDelimiters;
    }

    @JsonProperty("use_delimiters")
    public void setUseDelimiters(boolean useDelimiters) {
        mUseDelimiters = useDelimiters;
    }

    @JsonIgnore
    public PassPhraseConfig withUseDelimiters(boolean arg) {
        mUseDelimiters = arg;
        return this;
    }

    @JsonProperty("words")
    public List<Word> getWords() {
        return mWords;
    }

    @JsonProperty("words")
    public void setWords(List<Word> words) {
        mWords = words;
    }

    @JsonIgnore
    public PassPhraseConfig withWords(List<Word> col) {
        mWords = col;
        return this;
    }

    @JsonProperty("delimiters")
    public Delimiters getDelimiters() {
        return mDelimiters;
    }

    @JsonProperty("delimiters")
    public void setDelimiters(Delimiters delimiters) {
        mDelimiters = delimiters;
    }

    @JsonIgnore
    public PassPhraseConfig withDelimiters(Delimiters arg) {
        mDelimiters = arg;
        return this;
    }
}
