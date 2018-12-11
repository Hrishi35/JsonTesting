package com.example.owner.jsontesting;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class FetchActivity extends AppCompatActivity {
        TextView name1,email1;
    //RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
            name1=(TextView)findViewById(R.id.name);
           email1=(TextView)findViewById(R.id.email);

        //recyclerView=(RecyclerView)findViewById(R.id.rcy);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sendData();

    }
    public void sendData(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please Wait ...");
        dialog.show();
        RequestQueue queue =Volley.newRequestQueue(this);
        String url="https://api.androidhive.info/contacts/";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                JSONObject object=null;
                try {
                    object=new JSONObject(response);
                    JSONArray contacts = object.getJSONArray("contacts");
                    for (int i=0;i<1;i++ ) {
                        JSONObject ob = contacts.getJSONObject(i);
                        String name = ob.getString("name");
                        Log.d("TAG", "Name= " + name);
                        String email = ob.getString("email");
                        Log.d("TAG", "Email= " + email);

                          name1.setText(name);
                         email1.setText(email);


                      //  recyclerView.setVisibility(View.VISIBLE);


                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
            }
        });
        queue.add(request);

    }
}