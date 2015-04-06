package lemons.combustible.passmaterial.google;

import android.view.View;

import com.bustiblelemons.model.OnlinePhotoUrl;
import com.bustiblelemons.recycler.AbsRecyclerHolder;
import com.bustiblelemons.views.loadingimage.RemoteImage;

import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by hiv on 06.04.15.
 */
public class RemoteImageHolder extends AbsRecyclerHolder<OnlinePhotoUrl> {

    @Optional
    @InjectView(android.R.id.icon)
    RemoteImage mRemoteImage;

    public RemoteImageHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(OnlinePhotoUrl item, int position) {
        if (mRemoteImage != null && item != null) {
            mRemoteImage.loadFrom(item.getUrl());
        }

    }
}
