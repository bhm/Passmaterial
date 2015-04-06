package lemons.combustible.passmaterial.passphrases;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bustiblelemons.recycler.AbsRecyclerHolder;
import com.rey.material.widget.CheckBox;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import lemons.combustible.passmaterial.R;
import lemons.combustible.passmaterial.passphrases.settings.OnOpenSettings;

/**
 * Created by hiv on 01.04.15.
 */
public class PassPhraseCell extends AbsRecyclerHolder<PassPhrase> implements
                                                                  CompoundButton.OnCheckedChangeListener {

    @Optional
    @InjectView(android.R.id.title)
    TextView mTitleView;

    @Optional
    @InjectView(R.id.use_padding)
    CheckBox mUsePaddingCheckbox;

    @Optional
    @InjectView(R.id.use_delimiters)
    CheckBox mUseDelimitersCheckbox;

    private OnCopyToClipBoard mOnCopyToClipBoard;
    private PassPhrase     mItem;
    private OnOpenSettings mOnOpenSettings;

    public PassPhraseCell(View view, OnCopyToClipBoard onCopyToClipBoard) {
        super(view);
        mOnCopyToClipBoard = onCopyToClipBoard;
        if (mUsePaddingCheckbox != null) {
            mUsePaddingCheckbox.setOnCheckedChangeListener(this);
        }
        if (mUseDelimitersCheckbox != null) {
            mUseDelimitersCheckbox.setOnCheckedChangeListener(this);
        }
    }

    @Optional
    @OnClick(R.id.action_settings)
    void onOpenSettings() {
        if (mOnOpenSettings != null) {
            mOnOpenSettings.onOpenSettings();
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
            mItem = item;
            setText(item);
        }
    }

    private void setText(PassPhrase item) {
        if (mTitleView != null) {
            mTitleView.setText(item.getText());
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView != null && mItem != null) {
            int id = buttonView.getId();
            if (id == R.id.use_padding) {
                mItem.setUsePadding(isChecked);
            } else if (id == R.id.use_delimiters) {
                mItem.setUseDelimiters(isChecked);
            }
        }
        setText(mItem);
    }
}
