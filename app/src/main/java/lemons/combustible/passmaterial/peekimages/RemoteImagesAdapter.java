package lemons.combustible.passmaterial.peekimages;

import android.view.View;

import com.bustiblelemons.model.OnlinePhotoUrl;
import com.bustiblelemons.recycler.AbsRecyclerAdapter;
import com.bustiblelemons.recycler.AbsRecyclerHolder;

import lemons.combustible.passmaterial.R;

/**
 * Created by hiv on 31.03.15.
 */
public class RemoteImagesAdapter
        extends AbsRecyclerAdapter<OnlinePhotoUrl, AbsRecyclerHolder<OnlinePhotoUrl>> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.single_peek_image;
    }

    @Override
    public AbsRecyclerHolder<OnlinePhotoUrl> getViewHolder(View view, int viewType) {
        return new RemoteImagePeekHolder(view, viewType);
    }
}
