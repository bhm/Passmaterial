package com.bustiblelemons.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by bhm on 25.07.14.
 */
public abstract class AbsJacksonQuery<T> extends AbsOnlineQuery {

    public T getObject(Class<T> clss) throws IOException, URISyntaxException {
        return getObject(clss, null);
    }

    public T getObject(Class<T> clss, T def) throws IOException, URISyntaxException {
        HttpResponse response = query();
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                InputStream in = response.getEntity().getContent();
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(in, clss);
            }
        }
        return def;
    }

    public List<T> getObjectCollection(Class<T> clss)
            throws IOException, URISyntaxException {
        HttpResponse response = query();
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                InputStream in = response.getEntity().getContent();
                ObjectMapper mapper = new ObjectMapper();
                CollectionType type = mapper.getTypeFactory()
                        .constructCollectionType(List.class, clss);
                return mapper.readValue(in, type);
            }
        }
        return null;
    }
}
