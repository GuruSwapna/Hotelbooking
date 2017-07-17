package com.example.enchanterswapna.hotelbooking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by enchanterswapna on 7/7/17.
 */

public class Tab1Details extends android.support.v4.app.Fragment {
    TextView tname,taddress,tdesc;
    ImageView hotelimg;
    Context context;
    Button bookroom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1details, container, false);

        bookroom=(Button)rootView.findViewById(R.id.btnroom);
        tname=(TextView)rootView.findViewById(R.id.txname);
        taddress=(TextView)rootView.findViewById(R.id.txadd);

        tdesc=(TextView)rootView.findViewById(R.id.txdesc);

        hotelimg=(ImageView)rootView.findViewById(R.id.htimg);

        Intent intent = getActivity().getIntent();
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
                Intent intn1=new Intent(getActivity(),Bookroom.class);
                intn1.putExtra("name",name);
                intn1.putExtra("address",place);
                startActivity(intn1);
            }
        });




        return rootView;
    }
}
