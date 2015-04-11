package lemons.combustible.passmaterial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bustiblelemons.fragments.dialog.AbsArgDialogFragment;

import lemons.combustible.passmaterial.passphrases.PassPhraseConfig;

/**
 * Created by hiv on 11.04.15.
 */
public class SettingsDialog extends AbsArgDialogFragment<PassPhraseConfig> {

    public static final String TAG = SettingsDialog.class.getSimpleName();

    public static SettingsDialog newInstance(PassPhraseConfig config) {
        SettingsDialog dialog = new SettingsDialog();
        dialog.setNewInstanceArgument(config);
        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_dialog, container, false);
        return view;
    }

    @Override
    protected void onInstanceArgumentRead(PassPhraseConfig instanceArgument) {

    }
}
