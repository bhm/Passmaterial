package com.bustiblelemons.network;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by bhm on 25.07.14.
 */
public interface OnlineQuery {
    int SERVER_DELAY = 15000;

    String getScheme();

    String getHost();

    int getPort();

    String getMethod();

    String getFragment();

    HttpResponse query() throws IOException, URISyntaxException;
}
