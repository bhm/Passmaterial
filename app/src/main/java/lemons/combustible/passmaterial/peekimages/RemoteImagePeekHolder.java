package lemons.combustible.passmaterial.peekimages;

import android.view.View;

import com.bustiblelemons.model.OnlinePhotoUrl;
import com.bustiblelemons.recycler.AbsRecyclerHolder;
import com.bustiblelemons.views.loadingimage.RemoteImage;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by hiv on 31.03.15.
 */
public class RemoteImagePeekHolder extends AbsRecyclerHolder<OnlinePhotoUrl> {

    @InjectView(android.R.id.icon)
    RemoteImage mRemoteImage;

    public RemoteImagePeekHolder(View view, int viewType) {
        super(view);
        ButterKnife.inject(this, view);
    }

    @Override
    public void onBindData(OnlinePhotoUrl item) {
        if (mRemoteImage != null && item != null) {
            mRemoteImage.loadFrom(item.getUrl());
        }
    }
}
