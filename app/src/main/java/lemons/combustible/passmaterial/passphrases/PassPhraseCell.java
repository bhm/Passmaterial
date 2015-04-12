package lemons.combustible.passmaterial.passphrases;

import android.view.View;
import android.widget.TextView;

import com.bustiblelemons.recycler.AbsRecyclerHolder;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import lemons.combustible.passmaterial.OnPassphraseConfigChanged;
import lemons.combustible.passmaterial.R;
import lemons.combustible.passmaterial.passphrases.model.PassPhrase;
import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;
import lemons.combustible.passmaterial.settings.OnOpenSettings;
import lemons.combustible.passmaterial.settings.SettingsPresenter;

/**
 * Created by hiv on 01.04.15.
 */
public class PassPhraseCell extends AbsRecyclerHolder<PassPhrase> implements
                                                                  View.OnClickListener {

    private final SettingsPresenter mSettingsPresenter;
    @Optional
    @InjectView(android.R.id.title)
    TextView mTitleView;

    private OnCopyToClipBoard mOnCopyToClipBoard;
    private OnOpenSettings mOpenSettingsCallback;

    public PassPhraseCell(View view) {
        super(view);
        mSettingsPresenter = new SettingsPresenter(view)
                .withShowAdditionalSettings(false);
        View openSettings = view.findViewById(R.id.quick_settings);
        if (openSettings != null) {
            openSettings.setOnClickListener(this);
        }
    }

    public PassPhraseCell withOnCopyToClipboard(OnCopyToClipBoard arg) {
        mOnCopyToClipBoard = arg;
        return this;
    }

    public PassPhraseCell withOnPassphraseConfigChanged(OnPassphraseConfigChanged arg) {
        if (mSettingsPresenter != null) {
            mSettingsPresenter.withOnPassphraseChangedCallback(arg);
        }
        return this;
    }

    public PassPhraseCell withOpenSettingsCallback(OnOpenSettings arg) {
        mOpenSettingsCallback = arg;
        return this;
    }

    void onOpenSettings() {
        if (mOpenSettingsCallback != null) {
            mOpenSettingsCallback.onOpenSettings();
        }
    }

    @Optional
    @OnClick(android.R.id.copy)
    void onCopyToClipBoard() {
        if (mOnCopyToClipBoard != null && mTitleView != null) {
            CharSequence phrase = mTitleView.getText();
            mOnCopyToClipBoard.onCopyToClipboard(phrase);
        }
    }

    @Override
    public void onBindData(PassPhrase item, int position) {
        if (item != null) {
            if (mSettingsPresenter != null) {
                mSettingsPresenter.updateSettings();
            }
            item.useConfig(PassPhraseConfig.getPassPhraseConfig());
            setText(item);
        }
    }


    private void setText(PassPhrase item) {
        if (mTitleView != null) {
            mTitleView.setText(item.getText());
        }
    }

    @Override
    public void onClick(View v) {
        if (R.id.quick_settings == v.getId()) {
            onOpenSettings();
        }
    }
}
