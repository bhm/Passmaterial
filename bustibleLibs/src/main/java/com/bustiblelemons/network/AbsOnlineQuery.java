package com.bustiblelemons.network;

import com.bustiblelemons.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bhm on 25.07.14.
 *
 * @version 1.0.1
 */
public abstract class AbsOnlineQuery implements OnlineQuery {

    private static final Logger log = new Logger(AbsOnlineQuery.class);
    private List<NameValuePair> mParams;
    private String sContentEncoding = "Accept-Encoding";
    private HttpClient client;

    protected List<NameValuePair> getNameValuePairs() {
        if (mParams == null) {
            mParams = new ArrayList<NameValuePair>();
        }
        return mParams;
    }

    protected abstract boolean usesSSL();

    @Override
    public String getScheme() {
        return usesSSL() ? "https" : "http";
    }

    @Override
    public int getPort() {
        return -1;
    }

    protected URI getUri() throws URISyntaxException {
        return getUri(Collections.<NameValuePair>emptyList());
    }

    protected URI getUri(List<NameValuePair> valuePairs) throws URISyntaxException {
        String query = URLEncodedUtils.format(valuePairs, "utf-8");
        return URIUtils.createURI(getScheme(), getHost(), getPort(), getMethod(), query,
                getFragment());
    }

    @Override
    public String getFragment() {
        return null;
    }

    public String getEncoding() {
        return "UTF-8";
    }

    @Override
    public HttpResponse query() throws IOException, URISyntaxException {
        List<NameValuePair> valuePairs = getNameValuePairs();
        if (Type.POST.equals(getType())) {
            return queryForPost(valuePairs);
        } else {
            URI uri = getUri(valuePairs);
            log.d("Uri %s", uri.toString());
            HttpRequestBase base = getHttpEntity();
            base.setURI(uri);
            base.setHeader(sContentEncoding, getEncoding());
            HttpClient client = getClient();
            return client.execute(base);
        }
    }

    private HttpResponse queryForPost(List<NameValuePair> valuePairs) throws URISyntaxException, IOException {
        HttpPost post = new HttpPost();
        post.setURI(getUri());
        HttpEntity entity = new UrlEncodedFormEntity(valuePairs, getEncoding());
        post.setEntity(entity);
        HttpClient client = getClient();
        return client.execute(post);
    }

    protected HttpRequestBase getHttpEntity() {
        return getType().getHttpEntity();
    }

    public Type getType() {
        return Type.POST;
    }

    public HttpClient getClient() {
        if (client == null) {
            HttpParams params = new BasicHttpParams();
            params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, HTTP.UTF_8);
            params.setParameter(CoreProtocolPNames.USER_AGENT, "Apache-HttpClient/Android");
            params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, SERVER_DELAY);
            params.setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false);
            client = new DefaultHttpClient(params);
        }
        return client;
    }

    /**
     * @param param
     * @param value
     * @return
     * @since 1.0.1
     */
    public NameValuePair addParam(String param, String value) {
        NameValuePair v = new BasicNameValuePair(param, value);
        getNameValuePairs().add(v);
        return v;
    }

    public enum Type {
        GET {
            @Override
            public HttpRequestBase getHttpEntity() {
                return new HttpGet();
            }
        }, PUT {
            @Override
            public HttpRequestBase getHttpEntity() {
                return new HttpPut();
            }
        }, DELETE {
            @Override
            public HttpRequestBase getHttpEntity() {
                return new HttpDelete();
            }
        }, POST {
            @Override
            public HttpRequestBase getHttpEntity() {
                return new HttpPost();
            }
        };

        public abstract HttpRequestBase getHttpEntity();
    }
}
