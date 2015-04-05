package lemons.combustible.passmaterial.passphrases;

import android.view.View;
import android.widget.TextView;

import com.bustiblelemons.recycler.AbsRecyclerHolder;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by hiv on 01.04.15.
 */
public class PassPhraseCell extends AbsRecyclerHolder<PassPhrase> {

    @Optional
    @InjectView(android.R.id.title)
    TextView mTitleView;
    private OnCopyToClipBoard mOnCopyToClipBoard;

    public PassPhraseCell(View view, OnCopyToClipBoard onCopyToClipBoard) {
        super(view);
        mOnCopyToClipBoard = onCopyToClipBoard;
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
    public void onBindData(PassPhrase item) {
        if (item != null && mTitleView != null) {
            mTitleView.setText(item.getText());
        }
    }
}
