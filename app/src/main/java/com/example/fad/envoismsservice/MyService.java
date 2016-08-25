package com.example.fad.envoismsservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.fad.envoismsservice.Entities.Sms;
import com.example.fad.envoismsservice.JSON.DAOResponseListener;
import com.example.fad.envoismsservice.JSON.SmsJSON;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Crepin Hugues FADJO (f@dcrepin) on 23/08/2016.
 */
public class MyService extends Service {

    private Timer timer ;
    String num="";
    String msg="";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        timer=new Timer();
        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
               new SmsJSON(MyService.this).readMany(null, new DAOResponseListener<Sms>() {
                    @Override
                    public void onSuccess(ArrayList<Sms> data) {
                        for (Sms sms:data
                             ) {
                          msg=sms.getContent();
                            num=sms.getReceiver();
                            Log.e("msg",msg);
                            Log.e("num",num);

                        }
                    }

                    @Override
                    public void onFail(int errorcode, String errormessage) {

                    }
                });
                // Executer de votre tâche
               if(msg!=null && num!=null){

                    sendSms(num,msg);
                }



            }
        }, 0, 60000); //3600000


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }

    public void sendSms(String numero,String message){
        SmsManager.getDefault().sendTextMessage(numero, null, message, null, null);

    }
}
