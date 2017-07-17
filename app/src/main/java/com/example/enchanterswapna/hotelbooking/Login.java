package com.example.enchanterswapna.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText etmail,etpass;
    String stmail,stpass;
    Button btlogin;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        t1=(TextView)findViewById(R.id.link_signup);
        etmail=(EditText)findViewById(R.id.input_email);
        etpass=(EditText)findViewById(R.id.input_password);
        btlogin=(Button)findViewById(R.id.btn_login);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stmail=etmail.getText().toString();
                stpass=etpass.getText().toString();
                logininto(stmail,stpass);
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int12=new Intent(Login.this,Signup.class);
                startActivity(int12);
            }
        });
    }

    public void logininto(final String sstmail, final String sstpass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Global_Url.LOGINPG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean abc = jObj.getBoolean("exits");
                    if (abc)
                    {
                        JSONObject users = jObj.getJSONObject("user_det");
                        String uname1 = users.getString("pmail");
                        String upass1 = users.getString("ppass");
                        Intent intent2=new Intent(Login.this,Home_screen.class);
                        intent2.putExtra("ghtw",uname1);
                        intent2.putExtra("sssw",upass1);
                        startActivity(intent2);
                        //   Toast.makeText(getApplicationContext(),mobile_number,Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> insert = new HashMap<String, String>();
                insert.put("pmail", sstmail);
                insert.put("ppass", sstpass);

                return insert;

            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}
