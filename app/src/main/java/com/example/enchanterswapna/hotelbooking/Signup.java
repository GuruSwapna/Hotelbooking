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

import static android.widget.Toast.makeText;

public class Signup extends AppCompatActivity {

    EditText edname,edpass,edmail;
    Button go;
    TextView tw1;
    String sname,spass,smail,sval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sval="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        //sval="^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
        edname=(EditText)findViewById(R.id.input_name);
        edmail=(EditText)findViewById(R.id.input_email);
        edpass=(EditText)findViewById(R.id.input_password);
        go=(Button) findViewById(R.id.btn_signup);
        tw1=(TextView)findViewById(R.id.link_login);
        tw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Signup.this,Login.class);
                startActivity(in);
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname = edname.getText().toString();
                smail = edmail.getText().toString();
                spass = edpass.getText().toString();

                if (sname.isEmpty() || spass.isEmpty() || smail.isEmpty()) {
                    makeText(getApplicationContext(), "No field should be empty", Toast.LENGTH_SHORT).show();
                } else if (spass.length() < 6 || spass.length() > 8) {
                    //makeText(getApplicationContext(), "password must between 6 to 8", Toast.LENGTH_SHORT).show();
                    edpass.setError("password must between 6-8");
                } else if (!smail.matches(sval)) {
                    edmail.setError("Invalid Emailid");
                } else {
                    insert_service(sname, smail, spass);
                }

            }
        });

    }

    private void insert_service(final String ssname,final String ssmail,final String sspass) {

        StringRequest stringreqs = new StringRequest(Request.Method.POST, Global_Url.SIGN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean abc = jObj.getBoolean("exits");
                    if (abc)
                    {
                        JSONObject users = jObj.getJSONObject("user_det");
                        String named = users.getString("cname");
                        String maild = users.getString("cmail");
                        String passd = users.getString("cpass");

                        //Toast.makeText(getApplicationContext(), "User Exists already with  mobile number!", Toast.LENGTH_SHORT).show();
                        //String maill = users.getString("password");
                        // String stname = users.getString("studentname");

                        Intent intent1=new Intent(Signup.this,Login.class);
                        intent1.putExtra("stname", named);
                        startActivity(intent1);
                    }
                    else
                    {
                        //String msg=jObj.getString("messeade");
                        Toast.makeText(getApplicationContext(), "not valid", Toast.LENGTH_SHORT).show();
                    }
                   /*Intent intent = new Intent(SignUp_Screen.this, Home_Screen.class);
                   intent.putExtra("name", name);
                   intent.putExtra("mail", mail);
                   intent.putExtra("uuidq", uuidq);
                   startActivity(intent);
                   Toast.makeText(getApplicationContext(), "Welcome" + name, Toast.LENGTH_SHORT).show();
*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "INTERNET CONNECTION NOT AVAILABLE", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> uandme = new HashMap<String, String>();
                uandme.put("usernum", ssname);
                uandme.put("usermail", ssmail);
                uandme.put("userpass", sspass);
                //uandme.put("password2", password1);
                return uandme;
            }
        };
        AppController.getInstance().addToRequestQueue(stringreqs);

    }
}



