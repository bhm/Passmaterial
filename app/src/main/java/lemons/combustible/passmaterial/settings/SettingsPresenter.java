package lemons.combustible.passmaterial.settings;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.rey.material.widget.Slider;
import com.rey.material.widget.Switch;

import lemons.combustible.passmaterial.OnPassphraseConfigChanged;
import lemons.combustible.passmaterial.R;
import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;

/**
 * Created by hiv on 12.04.15.
 */
public class SettingsPresenter implements View.OnClickListener {

    private final Switch mUseLettersSwitch;
    private final Switch mUseNumbersSwitch;
    private final View   mAdditionalSettings;

    private final Switch mUsePaddingCheckbox;

    private final Switch mUseDelimitersCheckbox;
    private final Slider mWordCountSlider;

    private boolean                   mShowAdditionalSettings;
    private OnPassphraseConfigChanged mOnPassphraseChangedCallback;
    private TextView                  mMaxWordsTitle;

    public SettingsPresenter(View view) {
        mAdditionalSettings = view.findViewById(R.id.padding_additional_settings);
        mUsePaddingCheckbox = (Switch) view.findViewById(R.id.use_padding);
        mUseDelimitersCheckbox = (Switch) view.findViewById(R.id.use_delimiters);
        mUseLettersSwitch = (Switch) view.findViewById(R.id.padding_use_letters);
        mUseNumbersSwitch = (Switch) view.findViewById(R.id.padding_use_numbers);
        setOnClicks(mUseNumbersSwitch,
                mUseLettersSwitch,
                mUseDelimitersCheckbox,
                mUsePaddingCheckbox);
        mWordCountSlider = (Slider) view.findViewById(R.id.max_words);
        if (mWordCountSlider != null) {
            mWordCountSlider.setOnClickListener(this);
        }
        mMaxWordsTitle = (TextView) view.findViewById(R.id.max_words_title);
    }

    private void setOnClicks(View... views) {
        if (views != null) {
            for (View v : views) {
                if (v != null) {
                    v.setOnClickListener(this);
                }
            }
        }
    }


    public void updateSettings() {
        if (mShowAdditionalSettings && mAdditionalSettings != null) {
            mAdditionalSettings.setVisibility(View.VISIBLE);
        }
        setUsePadding(PassPhraseConfig.getPassPhraseConfig().getUsePadding());
        setUseDelimiters(PassPhraseConfig.getPassPhraseConfig().getUseDelimiters());
        setUseLetters(PassPhraseConfig.getPassPhraseConfig().isUseLettersInPadding());
        setUseNumbers(PassPhraseConfig.getPassPhraseConfig().isUseNumbersInPadding());
        setMaxWords(PassPhraseConfig.getPassPhraseConfig().getWordCount());
        setMaxWordsSlider(PassPhraseConfig.getPassPhraseConfig().getWordCount());
    }

    private void setMaxWords(int wordCount) {
        if (mMaxWordsTitle != null) {
            Resources resources = mMaxWordsTitle.getResources();
            String formated = resources.getString(R.string.max_words_format, wordCount);
            mMaxWordsTitle.setText(formated);
        }
    }

    private void setMaxWordsSlider(int wordCount) {
        if (mWordCountSlider != null) {
            mWordCountSlider.setPosition(wordCount, true);
        }
    }

    private void setUseNumbers(boolean useNumbersInPadding) {
        if (mUseNumbersSwitch != null) {
            mUseNumbersSwitch.setChecked(useNumbersInPadding);
        }
    }

    private void setUseLetters(boolean useLettersInPadding) {
        if (mUseLettersSwitch != null) {
            mUseLettersSwitch.setChecked(useLettersInPadding);
        }
    }


    private void setUsePadding(boolean usePadding) {
        if (mUsePaddingCheckbox != null) {
            mUsePaddingCheckbox.setChecked(usePadding);
        }
    }

    private void setUseDelimiters(boolean useDelimiters) {
        if (mUseDelimitersCheckbox != null) {
            mUseDelimitersCheckbox.setChecked(useDelimiters);
        }
    }

    public boolean getShowAdditionalSettings() {
        return mShowAdditionalSettings;
    }

    public SettingsPresenter withShowAdditionalSettings(boolean show) {
        mShowAdditionalSettings = show;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Switch) {
            Switch aSwitch = (Switch) v;
            boolean isChecked = aSwitch.isChecked();
            int id = v.getId();
            if (id == R.id.use_padding) {
                PassPhraseConfig.getPassPhraseConfig().setUsePadding(isChecked);
            } else if (id == R.id.use_delimiters) {
                PassPhraseConfig.getPassPhraseConfig().setUseDelimiters(isChecked);
            } else if (id == R.id.padding_use_letters) {
                PassPhraseConfig.getPassPhraseConfig().setUseLettersInPadding(isChecked);
            } else if (id == R.id.padding_use_numbers) {
                PassPhraseConfig.getPassPhraseConfig().setUseNumbersInPadding(isChecked);
            }
        } else {
            if (v instanceof Slider) {
                Slider slider = (Slider) v;
                if (R.id.max_words == v.getId()) {
                    int value = slider.getValue();
                    PassPhraseConfig.getPassPhraseConfig().setWordCount(value);
                    int cacheAdjust = (int) Math.sqrt(value);
                    PassPhraseConfig.getPassPhraseConfig().setMaxWordCount(value + cacheAdjust);
                    setMaxWords(value);
                }
            }
        }
        if (mOnPassphraseChangedCallback != null) {
            mOnPassphraseChangedCallback.onPassphraseConfigChanged(PassPhraseConfig.getPassPhraseConfig());
        }
    }

    public OnPassphraseConfigChanged getOnPassphraseChangedCallback() {
        return mOnPassphraseChangedCallback;
    }

    public SettingsPresenter withOnPassphraseChangedCallback(OnPassphraseConfigChanged arg) {
        mOnPassphraseChangedCallback = arg;
        return this;
    }

}
