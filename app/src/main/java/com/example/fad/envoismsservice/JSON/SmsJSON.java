package com.example.fad.envoismsservice.JSON;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fad.envoismsservice.Entities.Sms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;


/**
 * Created by Crepin Hugues FADJO (f@dcrepin) on 23/08/2016.
 */
public class SmsJSON extends  JSONDAO<Sms>{
    public SmsJSON(Context context) {
        super(context);
    }



    private JSONObject prepareObj(Sms sms) {
        JSONObject data = new JSONObject();
        try {

            data.put("user_id", sms.getUser_id());
            data.put("content", sms.getContent());
            data.put("receiver", sms.getReceiver());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }







    @Override
    public Object parse(JSONObject jsonObject) {
        Sms sms=new Sms();
        sms.setId(jsonObject.optInt("id"));
        sms.setContent(jsonObject.optString("content"));
        sms.setReceiver(jsonObject.optString("receiver"));
        sms.setUser_id(jsonObject.optInt("user_id"));
        return sms;
    }

    @Override
    public ArrayList<Sms> parseMany(JSONArray json) {
        ArrayList<Sms> smsList = new ArrayList<Sms>();
        Sms sms;
        int len = json.length();
        for (int i = 0; i < len; i++) {
            if ((sms =( Sms) parse(json.optJSONObject(i))) != null) smsList.add(sms);
        }
        // Log.e("disc",discussionList.get(discussionList.size()>0 ? 0 : 0).getName());
        return smsList ;
    }

    @Override
    public void create(Sms obj, DAOResponseListener<Sms> responseListener) {

    }

    @Override
    public void read(long objectId, DAOResponseListener<Sms> responseListener) {

    }

    @Override
    public void readMany(Map<String, String> params, final DAOResponseListener<Sms> responseListener) {


       String url = "http://conceptic.net/getsms.php";
        Log.e("mon url", url);

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                JSONArray array = new JSONArray();
                try {
//                    array = jsonObject.getJSONArray("discussions");
                    array = jsonObject.getJSONArray("sms");
                    Log.d("Liste des sms : ", jsonObject.toString());
                } catch (JSONException e) {
                    Log.e("Convert json to array", e.getMessage());
                }

                ArrayList<Sms> data = parseMany(array);
                if (data == null) {
                    responseListener.onFail(0, "Null survenu dans la récupération des données");
                } else {
                    responseListener.onSuccess(data);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                responseListener.onFail(0, "Erreur survenu dans la récupération des données");
                Log.e("Erreur: :", volleyError.toString());
            }
        });
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(objectRequest);



    }

    @Override
    public void update(Sms obj, DAOResponseListener<Sms> responseListener) {

    }

    @Override
    public void delete(long objectId, DAOResponseListener<Sms> responseListener) {

    }
}
