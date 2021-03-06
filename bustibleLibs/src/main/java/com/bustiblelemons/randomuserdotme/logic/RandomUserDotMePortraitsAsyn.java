package com.bustiblelemons.randomuserdotme.logic;

import android.content.Context;
import android.util.DisplayMetrics;

import com.bustiblelemons.async.AbsSimpleAsync;
import com.bustiblelemons.logging.Logger;
import com.bustiblelemons.model.OnlinePhotoUrl;
import com.bustiblelemons.model.OnlinePhotoUrlImpl;
import com.bustiblelemons.randomuserdotme.model.Picture;
import com.bustiblelemons.randomuserdotme.model.RandomUserDotMe;
import com.bustiblelemons.randomuserdotme.model.Results;
import com.bustiblelemons.randomuserdotme.model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bhm on 24.09.14.
 */
public class RandomUserDotMePortraitsAsyn extends
                                          AbsSimpleAsync<RandomUserMEQuery, List<OnlinePhotoUrl>> {
    private final int                    mDensity;
    private       OnRandomUsersRetreived listener;
    private Logger log = new Logger(RandomUserDotMeAsyn.class);

    public RandomUserDotMePortraitsAsyn(Context context, OnRandomUsersRetreived listener) {
        this.listener = listener;
        mDensity = context.getResources().getDisplayMetrics().densityDpi;
    }

    @Override
    protected List<OnlinePhotoUrl> call(RandomUserMEQuery... queries) throws Exception {
        List<OnlinePhotoUrl> r = Collections.emptyList();
        if (queries != null) {
            for (RandomUserMEQuery query : queries) {
                List<OnlinePhotoUrl> photos = getRandomUserPictures(query);
                publishProgress(query, photos);
            }
        }
        return r;
    }


    private List<OnlinePhotoUrl> getRandomUserPictures(RandomUserMEQuery rudmQuery) {
        List<OnlinePhotoUrl> r = new ArrayList<OnlinePhotoUrl>();
        if (rudmQuery != null) {
            RandomUserDotMe randomUser = null;
            try {
                randomUser = rudmQuery.getObject(RandomUserDotMe.class);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            log.d("random user %s", randomUser);
            for (Results result : randomUser.getResults()) {
                User u = result.getUser();
                if (u != null && u.getPicture() != null) {
                    String properUrl = getDensityAdjustedUrl(u.getPicture());
                    OnlinePhotoUrl o = new OnlinePhotoUrlImpl(properUrl);
                    r.add(o);
                }
            }
        }
        return r;
    }

    private String getDensityAdjustedUrl(Picture pic) {
        switch (mDensity) {
        case DisplayMetrics.DENSITY_MEDIUM:
        case DisplayMetrics.DENSITY_LOW:
            return pic.getMedium();
        default:
            return pic.getLarge();
        }
    }

    @Override
    protected void onProgressUpdate(RandomUserMEQuery param, List<OnlinePhotoUrl> result) {
        if (listener != null) {
            listener.onRandomUsersPortraits(param, result);
        }
    }


    @Override
    protected boolean onException(Exception e) {
        return false;
    }

    @Override
    protected boolean onSuccess(List<OnlinePhotoUrl> result) {
        return false;
    }

}
