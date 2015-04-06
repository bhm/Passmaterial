package lemons.combustible.passmaterial.google;

import android.view.View;

import com.bustiblelemons.model.OnlinePhotoUrl;
import com.bustiblelemons.recycler.AbsRecyclerAdapter;
import com.bustiblelemons.recycler.AbsRecyclerHolder;

import lemons.combustible.passmaterial.R;

/**
 * Created by hiv on 06.04.15.
 */
public class ImagesAdapter extends
                           AbsRecyclerAdapter<OnlinePhotoUrl, AbsRecyclerHolder<OnlinePhotoUrl>> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.single_online_image;
    }

    @Override
    public AbsRecyclerHolder<OnlinePhotoUrl> getViewHolder(View view, int viewType) {
        return new RemoteImageHolder(view);
    }
}
