package lemons.combustible.passmaterial.passphrases;

import android.view.View;
import android.widget.TextView;

import com.bustiblelemons.recycler.AbsRecyclerHolder;
import com.rey.material.widget.Switch;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import lemons.combustible.passmaterial.R;
import lemons.combustible.passmaterial.passphrases.settings.OnOpenSettings;

/**
 * Created by hiv on 01.04.15.
 */
public class PassPhraseCell extends AbsRecyclerHolder<PassPhrase> implements
                                                                  View.OnClickListener {

    @Optional
    @InjectView(android.R.id.title)
    TextView mTitleView;

    //    @Optional
//    @InjectView(R.id.use_padding)
    Switch mUsePaddingCheckbox;

    //    @Optional
//    @InjectView(R.id.use_delimiters)
    Switch mUseDelimitersCheckbox;

    private OnCopyToClipBoard mOnCopyToClipBoard;
    private PassPhrase     mItem;
    private OnOpenSettings mOpenSettingsCallback;

    public PassPhraseCell(View view, OnCopyToClipBoard onCopyToClipBoard) {
        super(view);
        mUsePaddingCheckbox = (Switch) view.findViewById(R.id.use_padding);
        mUseDelimitersCheckbox = (Switch) view.findViewById(R.id.use_delimiters);
        View openSettings = view.findViewById(R.id.quick_settings);
        if (openSettings != null) {
            openSettings.setOnClickListener(this);
        }
        mOnCopyToClipBoard = onCopyToClipBoard;
        if (mUsePaddingCheckbox != null) {
            mUsePaddingCheckbox.setOnClickListener(this);
        }
        if (mUseDelimitersCheckbox != null) {
            mUseDelimitersCheckbox.setOnClickListener(this);
        }
    }

    public OnOpenSettings getOpenSettingsCallback() {
        return mOpenSettingsCallback;
    }

    public PassPhraseCell withOpenSettingsCallback(OnOpenSettings arg) {
        mOpenSettingsCallback = arg;
        return this;
    }

    //    @Optional
//    @OnClick(R.id.action_settings)
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
            mItem = item;
            setText(item);
            boolean usePadding = item.isUsePadding();
            boolean useDelimiters = item.isUseDelimiters();
            setUsePadding(usePadding);
            setUseDelimiters(useDelimiters);
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

    private void setText(PassPhrase item) {
        if (mTitleView != null) {
            mTitleView.setText(item.getText());
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Switch) {
            Switch aSwitch = (Switch) v;
            boolean isChecked = aSwitch.isChecked();
            int id = v.getId();
            if (id == R.id.use_padding) {
                mItem.setUsePadding(isChecked);
            } else if (id == R.id.use_delimiters) {
                mItem.setUseDelimiters(isChecked);
            }
            setText(mItem);
        } else if (R.id.quick_settings == v.getId()) {
//            onOpenSettings();
        }
    }
}
