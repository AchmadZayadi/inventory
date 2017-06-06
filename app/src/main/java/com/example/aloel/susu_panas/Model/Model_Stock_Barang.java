package com.example.aloel.susu_panas.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aloel on 5/22/2017.
 */

public class Model_Stock_Barang implements Parcelable {

    public String stock;
    public String nama;

    public Model_Stock_Barang()
    {


    }


    public Model_Stock_Barang(Parcel in)
    {
        stock = in.readString();
        nama = in.readString();
    }


    public static final Parcelable.Creator<Model_Stock_Barang> CREATOR = new Parcelable.Creator<Model_Stock_Barang>() {

        @Override
        public Model_Stock_Barang createFromParcel(Parcel in)
        {
                // TODO Auto-generated method stub
                return new Model_Stock_Barang(in);
        }

        @Override
        public Model_Stock_Barang[] newArray(int size)
        {
            // TODO Auto-generated method stub
            return new Model_Stock_Barang[size];
        }
    };

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        // TODO Auto-generated method stub
        out.writeString(stock);
        out.writeString(nama);

    }
    }