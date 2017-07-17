package com.example.enchanterswapna.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bookroom extends AppCompatActivity {

    TextView tvname,tvadd,tvroom;
    EditText edps;
    String st1;
    double presult,pers;
    Button bbook;
    double result,n1=4.000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookroom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bbook=(Button)findViewById(R.id.bookr);
        edps=(EditText)findViewById(R.id.edit1);
        tvname=(TextView)findViewById(R.id.honame);
        tvadd=(TextView)findViewById(R.id.hoadd);
        tvroom=(TextView)findViewById(R.id.newsdisp);

        //pers=Double.parseDouble(st1);

           // pers = Double.valueOf(st1);

        bbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st1=edps.getText().toString();
                try {

                    pers = new Double(edps.getText().toString());

                }catch (NumberFormatException n)
                {
                    n.printStackTrace();
                    tvroom.setText("");
                }

                presult=pers/n1;
                result= Math.ceil(presult);
                int d1= (int) Math.abs(result);

//                SpannableString str = new SpannableString("Highlighted. Not highlighted.");
//                str.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, 11, 0);
//                tvroom.setText(str);
                tvroom.setText(d1+"\t"+"ROOMS ARE BOOKED \n\n CAN PAY AT HOTEL");
            }
        });

        Intent in1=getIntent();
        String name=in1.getStringExtra("name");
        String add=in1.getStringExtra("address");
        tvname.setText(name);
        tvadd.setText(add);
    }

}
