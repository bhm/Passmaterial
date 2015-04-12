package lemons.combustible.passmaterial.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bustiblelemons.fragments.dialog.AbsArgDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lemons.combustible.passmaterial.OnPassphraseConfigChanged;
import lemons.combustible.passmaterial.R;
import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;

/**
 * Created by hiv on 11.04.15.
 */
public class SettingsDialog extends AbsArgDialogFragment<PassPhraseConfig> {

    public static final String TAG = SettingsDialog.class.getSimpleName();
    private SettingsPresenter         mSettingsPresenter;
    private OnPassphraseConfigChanged mOnPassphraseConfigChanged;


    public static SettingsDialog newInstance(PassPhraseConfig config) {
        SettingsDialog dialog = new SettingsDialog();
        dialog.setNewInstanceArgument(config);
        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnPassphraseConfigChanged) {
            mOnPassphraseConfigChanged = (OnPassphraseConfigChanged) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_dialog, container, false);
        ButterKnife.inject(this, view);
        mSettingsPresenter = new SettingsPresenter(view)
                .withShowAdditionalSettings(true)
                .withOnPassphraseChangedCallback(mOnPassphraseConfigChanged);
        return view;
    }

    @OnClick(android.R.id.closeButton)
    public void onClose() {
        dismiss();
    }

    @Override
    protected void onInstanceArgumentRead(PassPhraseConfig instanceArgument) {
        if (instanceArgument != null) {
            mSettingsPresenter.updateSettings();
        }
    }
}
