package com.example.Movie.Data;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Volleysingleton {
    private static Volleysingleton mInstance;
    private RequestQueue mRequestQueue;

    private Volleysingleton(Context context){
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized Volleysingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new Volleysingleton(context);
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
