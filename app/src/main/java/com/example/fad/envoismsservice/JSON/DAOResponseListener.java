package com.example.fad.envoismsservice.JSON;

import java.util.ArrayList;

public interface DAOResponseListener<T> {
    void onSuccess(ArrayList<T> data);

    void onFail(int errorcode, String errormessage);
}
