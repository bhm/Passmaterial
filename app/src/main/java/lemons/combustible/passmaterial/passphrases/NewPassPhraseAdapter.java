package lemons.combustible.passmaterial.passphrases;

import android.view.View;

import com.bustiblelemons.logging.Logger;
import com.bustiblelemons.recycler.AbsRecyclerAdapter;
import com.bustiblelemons.recycler.AbsRecyclerHolder;

import lemons.combustible.passmaterial.R;

/**
 * Created by hiv on 31.03.15.
 */
public class NewPassPhraseAdapter
        extends AbsRecyclerAdapter<PassPhrase, AbsRecyclerHolder<PassPhrase>> {


    private static final Logger log = new Logger(NewPassPhraseAdapter.class);
    private OnCopyToClipBoard mOnCopyToClipBoard;

    @Override
    public int getItemCount() {
        return getData().get(0).getWords().size() + 2;
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

            return new PassPhraseCell(view, mOnCopyToClipBoard);
        }
        return new PassPhraseDefinition(view);
    }

    public NewPassPhraseAdapter withOnCopyToClipboard(OnCopyToClipBoard callback) {
        mOnCopyToClipBoard = callback;
        return this;
    }
}
