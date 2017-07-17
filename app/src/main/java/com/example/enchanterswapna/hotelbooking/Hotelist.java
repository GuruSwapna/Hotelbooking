package com.example.enchanterswapna.hotelbooking;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Hotelist extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = (ListView) findViewById(R.id.list1);
        new kilomilo().execute(Global_Url.LIST_URL);
    }

    public class MovieAdap extends ArrayAdapter {
        private List<hlist> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;

        MovieAdap(Context context, int resource, List<hlist> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context = context;
            this.resource = resource;
            inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View converti, ViewGroup parent) {
            final ViewHolder holder;
            if (converti == null) {
                converti = inflater.inflate(resource, null);
                holder = new ViewHolder();
                holder.imageview1 = (ImageView) converti.findViewById(R.id.img2);
                holder.shopaddres1 = (TextView) converti.findViewById(R.id.address26);
                holder.text_name1 = (TextView) converti.findViewById(R.id.name12);
                holder.price = (TextView) converti.findViewById(R.id.address27);
                holder.rating=(TextView)converti.findViewById(R.id.rat);
//                holder.button2=(Button) converti.findViewById(R.id.but2);
                converti.setTag(holder);
            } else {
                holder = (ViewHolder) converti.getTag();
            }
            hlist ccitac = movieModelList.get(position);
            Picasso.with(context).load(ccitac.getHtimage()).error(R.drawable.ic_menu_share).fit().into(holder.imageview1);
            holder.text_name1.setText(ccitac.getHtname());
            holder.shopaddres1.setText(ccitac.getHtaddress());
            holder.price.setText(ccitac.getHtprice());
            holder.rating.setText(ccitac.getHtrating());


//            holder.button2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(),"This salon offers in home service",Toast.LENGTH_SHORT).show();
//                }
//            });
            return converti;
        }

        class ViewHolder {
     public TextView text_name1, shopaddres1,price,rating;
       public ImageView imageview1,star1,star2,star3,star4;

            //Button button29, button2;
        }
    }

    public class kilomilo extends AsyncTask<String, String, List<hlist>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<hlist> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("result");
                List<hlist> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    hlist catego = gson.fromJson(finalObject.toString(), hlist.class);
                    milokilo.add(catego);
                }
                return milokilo;
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<hlist> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()!=0) {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.hotelslist, movieMode);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        hlist item = movieMode.get(position);
                        ViewGroup vb=(ViewGroup) view;
                        //Intent intent = new Intent(Friendslist.this,NextActivity.class);
//                        TextView t1=(TextView)vb.findViewById(R.id.name12);
//                        TextView t2=(TextView)vb.findViewById(R.id.address26);
//                        TextView t3=(TextView)vb.findViewById(R.id.address27);
//                        TextView t4=(TextView)vb.findViewById(R.id.rat);
//                        ImageView im1=(ImageView)vb.findViewById(R.id.img2);
//                        String amm=t1.getText().toString();
//                        String ann=t2.getText().toString();
//                        String amn=t3.getText().toString();
//                        String s3=t4.getText().toString();
//                        int imn1=Integer.parseInt(s3);
//                        ImageView imgs1=(ImageView)findViewById(R.id.imageViewst1);
//                        ImageView imgs2=(ImageView)findViewById(R.id.imageViewst2);
//                        ImageView imgs3=(ImageView)findViewById(R.id.imageViewst3);
//                        ImageView imgs4=(ImageView)findViewById(R.id.imageViewst4);
//                        ImageView imgs5=(ImageView)findViewById(R.id.imageViewst5);
//                        if(imn1==1){
//                            imgs1.setVisibility(view.VISIBLE);
//                        }
//                        else if(imn1==2){
//                            imgs1.setVisibility(view.VISIBLE);
//                            imgs2.setVisibility(view.VISIBLE);
//                        }else if (imn1==3){
//                            imgs1.setVisibility(view.VISIBLE);
//                            imgs2.setVisibility(view.VISIBLE);
//                            imgs3.setVisibility(view.VISIBLE);
//                        }else if(imn1==4){
//                            imgs1.setVisibility(view.VISIBLE);
//                            imgs2.setVisibility(view.VISIBLE);
//                            imgs3.setVisibility(view.VISIBLE);
//                            imgs4.setVisibility(view.VISIBLE);
//                        }else if (imn1==5){
//                            imgs1.setVisibility(view.VISIBLE);
//                            imgs2.setVisibility(view.VISIBLE);
//                            imgs3.setVisibility(view.VISIBLE);
//                            imgs4.setVisibility(view.VISIBLE);
//                            imgs5.setVisibility(view.VISIBLE);
//                        }

                        Intent i1 = new Intent(Hotelist.this, Holldetails.class);

                        i1.putExtra("name",item.getHtname());
                        i1.putExtra("Address",item.getHtaddress());
                        i1.putExtra("price",item.getHtprice());
                        i1.putExtra("image",item.getHtimage());
                        i1.putExtra("desc",item.getHtdescrip());


//                        im1.setDrawingCacheEnabled(true);
//                        Bitmap b=im1.getDrawingCache();
//
//
//                        i.putExtra("Bitmap", b);
//                        Picasso.with(context).load(ccitac.getHtimage()).error(R.drawable.ic_menu_share).fit().into(holder.imageview1);

//                        Picasso.with(context).load(ccitac.getShopimage()).error(R.drawable.brush).fit().into(holder.imageview);
                        startActivity(i1);

                    }
                });
//                List_Comi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                    @Override
//                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                        Toast.makeText(cardview_services.this, "Check all", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> arg0) {
//
//                    }
//                });
            } else {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

}





