package lemons.combustible.passmaterial.passphrases.generators.wordnik;

import com.bustiblelemons.network.AbsJacksonQuery;

import lemons.combustible.passmaterial.passphrases.generators.Wordnik_Api;

/**
 * Created by hiv on 06.04.15.
 */
public abstract class AbsWordnikQuery<T> extends AbsJacksonQuery<T> {

    private static final String sHost = "api.wordnik.com";

    private static final int    sPort   = 80;
    private static final String sApiKey = Wordnik_Api.___Key;
    private static final String API_KEY = "api_key";
    private static final String LIMIT   = "limit";
    private int mLimit;

    public AbsWordnikQuery() {
        addParam(API_KEY, sApiKey);
    }

    @Override
    protected boolean usesSSL() {
        return false;
    }

    @Override
    public String getHost() {
        return sHost;
    }

    @Override
    public Type getType() {
        return Type.GET;
    }

    @Override
    public int getPort() {
        return sPort;
    }

    public int getLimit() {
        return mLimit;
    }

    public AbsJacksonQuery withLimit(int arg) {
        mLimit = arg;
        addParam(LIMIT, mLimit + "");
        return this;
    }
}
