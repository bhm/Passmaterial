package com.bustiblelemons.logging;

import android.util.Log;

import java.util.Locale;

/**
 * Created by bhm on 18.07.14.
 */
public class Logger {
    private String  mTag;
    private boolean mForceLog;

    public Logger(Class<?> cls) {
        this(cls, false);
    }

    public Logger(Class<?> cls, boolean forceLog) {
        mForceLog = forceLog;
        this.mTag = cls.getSimpleName();
    }

    public int d(Throwable throwable) {
        return Log.d(mTag, throwable != null ? throwable.getLocalizedMessage() : "", throwable);
    }

    public int e(Throwable throwable) {
        return Log.d(mTag, throwable != null ? throwable.getLocalizedMessage() : "", throwable);
    }

    public int i(Throwable throwable) {
        return Log.d(mTag, throwable != null ? throwable.getLocalizedMessage() : "", throwable);
    }

    public int v(Throwable throwable) {
        return Log.d(mTag, throwable != null ? throwable.getLocalizedMessage() : "", throwable);
    }

    public int w(Throwable throwable) {
        return Log.d(mTag, throwable != null ? throwable.getLocalizedMessage() : "", throwable);
    }

    public int wtf(Throwable throwable) {
        return Log.d(mTag, throwable != null ? throwable.getLocalizedMessage() : "", throwable);
    }

    public int d(Throwable throwable, String format, Object... args) {
        return Log.d(mTag, String.format(Locale.ENGLISH, format, args), throwable);
    }

    public int e(Throwable throwable, String format, Object... args) {
        return Log.e(mTag, String.format(Locale.ENGLISH, format, args), throwable);
    }

    public int i(Throwable throwable, String format, Object... args) {
        return Log.i(mTag, String.format(Locale.ENGLISH, format, args), throwable);
    }

    public int v(Throwable throwable, String format, Object... args) {
        return Log.v(mTag, String.format(Locale.ENGLISH, format, args), throwable);
    }

    public int wtf(Throwable throwable, String format, Object... args) {
        return Log.wtf(mTag, String.format(Locale.ENGLISH, format, args), throwable);
    }

    public int w(Throwable throwable, String format, Object... args) {
        return Log.w(mTag, String.format(Locale.ENGLISH, format, args), throwable);
    }


    public int d(String format, Object... args) {
        return Log.d(mTag, String.format(Locale.ENGLISH, format, args));
    }

    public int e(String format, Object... args) {
        return Log.e(mTag, String.format(Locale.ENGLISH, format, args));
    }

    public int i(String format, Object... args) {
        return Log.i(mTag, String.format(Locale.ENGLISH, format, args));
    }

    public int v(String format, Object... args) {
        return Log.v(mTag, String.format(Locale.ENGLISH, format, args));
    }

    public int wtf(String format, Object... args) {
        return Log.wtf(mTag, String.format(Locale.ENGLISH, format, args));
    }

    public int w(String format, Object... args) {
        return Log.w(mTag, String.format(Locale.ENGLISH, format, args));
    }
}