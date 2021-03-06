package com.bustiblelemons.utils;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by bhm on 17.09.14.
 */
public class ResourceHelper {

    public static final  String UNDERSCORE_SPLITER = "_";
    private static final String STATUS_BAR_HEIGHT  = "status_bar_height";
    private static final String DIMEN              = "dimen";
    private static final String STRING             = "string";
    private static final String ANDROID_PACKAGE    = "android";
    private final Context mContext;
    private String mSpliter = UNDERSCORE_SPLITER;
    private String mPackageName;
    private String[] mParts = new String[0];
    private boolean  mThrow = false;

    public ResourceHelper(Context context) {
        mContext = context;
        mPackageName = mContext.getPackageName();
    }

    public static ResourceHelper from(Context context) {
        return new ResourceHelper(context);
    }

    public ResourceHelper withSpliter(String spliter) {
        if (spliter != null) {
            mSpliter = spliter;
        }
        return this;
    }

    public ResourceHelper withNameParts(String... parts) {
        if (parts != null) {
            mParts = parts;
        }
        return this;
    }

    public ResourceHelper throwException(boolean throwException) {
        mThrow = throwException;
        return this;
    }

    public String getString() {
        int id = getIdentifierForStringByNamePartsWithSpliter(mSpliter, mParts);
        if (mThrow) {
            return mContext.getString(id);
        } else {
            try {
                return mContext.getString(id);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public int getIdentifierForStringByNameParts(String... parts) {
        return getIdentifierForStringByNamePartsWithSpliter(UNDERSCORE_SPLITER, parts);
    }

    public int getIdentifierForStringByNamePartsWithSpliter(String spliter, String... parts) {
        StringBuilder b = new StringBuilder();
        String fix = "";
        for (String part : parts) {
            if (part != null) {
                b.append(fix);
                b.append(part.toLowerCase(Locale.ENGLISH));
                fix = spliter;
            }
        }
        return getIdentifierForString(b.toString());
    }

    public int getIdentifierForString(String resName) {
        int resId = 0;
        try {
            return mContext.getResources().getIdentifier(resName, STRING, mPackageName);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return resId;
    }

    public int getStatusBarHeight() {
        int result = 0;
        if (mContext != null) {
            Resources resources = mContext.getResources();
            int resourceId = resources.getIdentifier(STATUS_BAR_HEIGHT, DIMEN, ANDROID_PACKAGE);
            if (resourceId > 0) {
                result = resources.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }
}
