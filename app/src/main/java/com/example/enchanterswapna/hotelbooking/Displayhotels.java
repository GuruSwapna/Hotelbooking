package com.example.enchanterswapna.hotelbooking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Displayhotels extends AppCompatActivity {

    TextView tname,taddress,tdesc;
    ImageView hotelimg;
    Context context;
    Button bookroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayhotels);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bookroom=(Button)findViewById(R.id.btnroom);
        tname=(TextView)findViewById(R.id.txname);
        taddress=(TextView)findViewById(R.id.txadd);

        tdesc=(TextView)findViewById(R.id.txdesc);

        hotelimg=(ImageView)findViewById(R.id.htimg);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String place = intent.getStringExtra("Address");
        final String desc=intent.getStringExtra("desc");
        String imagesn =intent.getStringExtra("image");

        final String ratingpb=intent.getStringExtra("ratingsr");

        tname.setText(name);
        taddress.setText(place);

        tdesc.setText(desc);
        Picasso.with(context).load(imagesn).fit().into(hotelimg);
//        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("Bitmap");
//        hotelimg.setImageBitmap(bitmap);
        //Bitmap bmp = (Bitmap) intent1.getParcelableExtra("bmp_Image");

        bookroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intn1=new Intent(Displayhotels.this,Bookroom.class);
                intn1.putExtra("name",name);
                intn1.putExtra("address",place);
                startActivity(intn1);
            }
        });

    }

}
