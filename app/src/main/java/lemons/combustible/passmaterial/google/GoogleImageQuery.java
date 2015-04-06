package lemons.combustible.passmaterial.google;

import com.bustiblelemons.google.apis.model.GoogleImageResponse;
import com.bustiblelemons.google.apis.search.params.GImageSearch;
import com.bustiblelemons.network.AbsJacksonQuery;

/**
 * Created by hiv on 06.04.15.
 */
public class GoogleImageQuery extends AbsJacksonQuery<GoogleImageResponse> {
    GImageSearch mGImageSearch;

    public GoogleImageQuery(GImageSearch GImageSearch) {
        mGImageSearch = GImageSearch;
    }

    @Override
    protected boolean usesSSL() {
        return false;
    }

    @Override
    public String getHost() {
        return mGImageSearch.getHost();
    }

    @Override
    public String getMethod() {
        return mGImageSearch.getMethod();
    }
}
