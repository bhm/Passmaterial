package lemons.combustible.passmaterial;

import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;

public interface OnPassphraseConfigChanged {
    void onPassphraseConfigChanged(PassPhraseConfig config);
}