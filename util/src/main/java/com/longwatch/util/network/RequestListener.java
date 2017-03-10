package com.longwatch.util.network;

import com.longwatch.util.jsondata.BaseJsonData;

import java.util.HashMap;

public interface RequestListener<T extends BaseJsonData> {

    void onRequestSucc(Object obj);

    void onRequestFail(RequestError<T> error);

    Class<T> getClassForJsonData();

    String getUrl();

    HashMap<String, String> getParams();

    Object getTag();
}