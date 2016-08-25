package com.example.fad.envoismsservice.JSON;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by arcadius on 9/8/2015.
 */
public abstract class JSONDAO<T> {
    public static final String PARAM_POST_TYPE="post_type";
    public static final String PARAM_POST_ID="post_id";
    public static final String PARAM_POST_META="post_meta";
    public static final String PARAM_AUTHOR_META="author_meta";
    public static final String URL_SERVER_BASE="https://beautyafrik.com/wc-api/v2";

    public static final HashMap<String,String> authParams=new HashMap<String,String>();
    public Context context;

    public JSONDAO(Context context) {
        this.context = context;
    }

    public abstract Object parse(JSONObject jsonObject);
    public abstract ArrayList<T> parseMany(JSONArray json);

    public abstract void create(T obj, DAOResponseListener<T> responseListener);
    public abstract void read(long objectId, DAOResponseListener<T> responseListener);
    public abstract void readMany(Map<String, String> params, final DAOResponseListener<T> responseListener);
    public abstract void update(T obj, DAOResponseListener<T> responseListener);
    public abstract void delete(long objectId, DAOResponseListener<T> responseListener);
}
