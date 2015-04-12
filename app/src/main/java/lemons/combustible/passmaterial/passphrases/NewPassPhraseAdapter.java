package lemons.combustible.passmaterial.passphrases;

import android.view.View;

import com.bustiblelemons.recycler.AbsRecyclerAdapter;
import com.bustiblelemons.recycler.AbsRecyclerHolder;

import java.util.List;

import lemons.combustible.passmaterial.OnPassphraseConfigChanged;
import lemons.combustible.passmaterial.R;
import lemons.combustible.passmaterial.passphrases.model.PassPhrase;
import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;
import lemons.combustible.passmaterial.settings.OnOpenSettings;

/**
 * Created by hiv on 31.03.15.
 */
public class NewPassPhraseAdapter
        extends AbsRecyclerAdapter<PassPhrase, AbsRecyclerHolder<PassPhrase>> implements
                                                                              OnPassphraseConfigChanged {


    private OnCopyToClipBoard mOnCopyToClipBoard;
    private OnOpenSettings mOnOpenSettings;


    public NewPassPhraseAdapter withOnOpenSettings(OnOpenSettings arg) {
        mOnOpenSettings = arg;
        return this;
    }

    @Override
    public int getItemCount() {
        if (getData().get(0) != null) {
            List<Word> words = getData().get(0).getWordsToUse();
            return words.size() + 2;
        }
        return 0;
    }

    @Override
    public PassPhrase getItem(int i) {
        return super.getItem(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.id.peek_through;
        } else if (position == 1) {
            return R.id.pass_phrase;
        }
        return R.id.pass_phrase_definition;
    }

    @Override
    public int getLayoutId(int viewType) {
        if (viewType == R.id.peek_through) {
            return R.layout.new_pharse_peek_through;
        } else if (viewType == R.id.pass_phrase) {
            return R.layout.single_passphrase;
        }
        return R.layout.single_passphrase_definition;
    }

    @Override
    public AbsRecyclerHolder<PassPhrase> getViewHolder(View view, int viewType) {
        if (viewType == R.id.peek_through) {
            return new PeekThroughHolder(view);
        } else if (viewType == R.id.pass_phrase) {

            return new PassPhraseCell(view)
                    .withOpenSettingsCallback(mOnOpenSettings)
                    .withOnCopyToClipboard(mOnCopyToClipBoard)
                    .withOnPassphraseConfigChanged(this);
        }
        return new PassPhraseDefinition(view);
    }

    public NewPassPhraseAdapter withOnCopyToClipboard(OnCopyToClipBoard callback) {
        mOnCopyToClipBoard = callback;
        return this;
    }

    @Override
    public void onPassphraseConfigChanged(PassPhraseConfig config) {
        notifyDataSetChanged();
    }
}
