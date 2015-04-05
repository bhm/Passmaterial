package lemons.combustible.passmaterial.passphrases.generators.wordnik;

import com.bustiblelemons.network.AbsJacksonQuery;

import lemons.combustible.passmaterial.passphrases.generators.Wordnik_Api;

/**
 * Created by hiv on 05.04.15.
 */
public class WordnikRandomWordsQuery extends AbsJacksonQuery<WordnikWord> {
    private static final String sHost   = "api.wordnik.com";
    private static final String sApiKey = Wordnik_Api.___Key;
    private static final String sMethod = "/v4/words.json/randomWords";
    private static final int    sPort   = 80;
    private static final String LIMIT   = "limit";
    private static final String API_KEY = "api_key";
    private int mLimit;

    public WordnikRandomWordsQuery() {
        addParam(API_KEY, sApiKey);
    }

    public int getLimit() {
        return mLimit;
    }

    public WordnikRandomWordsQuery withLimit(int arg) {
        mLimit = arg;
        addParam(LIMIT, mLimit + "");
        return this;
    }

    @Override
    public Type getType() {
        return Type.GET;
    }

    @Override
    public int getPort() {
        return sPort;
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
    public String getMethod() {
        return sMethod;
    }
}
