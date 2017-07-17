package com.example.enchanterswapna.hotelbooking;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanterswapna on 6/7/17.
 */

public class hlist implements Parcelable{
    String htname;
    String htprice;
    String  htimage;
    String htaddress;
    String htrating;

    public String getHtrating() {
        return htrating;
    }

    public void setHtrating(String htrating) {
        this.htrating = htrating;
    }

    public String getHtdescrip() {
        return htdescrip;
    }

    public void setHtdescrip(String htdescrip) {
        this.htdescrip = htdescrip;
    }

    String htdescrip;


    protected hlist(Parcel in) {
        htname = in.readString();
        htprice = in.readString();
        htimage = in.readString();
        htaddress = in.readString();
    }

    public static final Creator<hlist> CREATOR = new Creator<hlist>() {
        @Override
        public hlist createFromParcel(Parcel in) {
            return new hlist(in);
        }

        @Override
        public hlist[] newArray(int size) {
            return new hlist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(htname);
        parcel.writeString(htprice);
        parcel.writeString(htimage);
        parcel.writeString(htaddress);
    }

    public String getHtname() {
        return htname;
    }

    public void setHtname(String htname) {
        this.htname = htname;
    }

    public String getHtprice() {
        return htprice;
    }

    public void setHtprice(String htprice) {
        this.htprice = htprice;
    }

    public String getHtimage() {
        return htimage;
    }

    public void setHtimage(String htimage) {
        this.htimage = htimage;
    }

    public String getHtaddress() {
        return htaddress;
    }

    public void setHtaddress(String htaddress) {
        this.htaddress = htaddress;
    }

    public static Creator<hlist> getCREATOR() {
        return CREATOR;
    }
}
