package com.example.aloel.susu_panas.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aloel on 5/16/2017.
 */

public class Model_Barang implements Parcelable {


    public String idTransaksi;
    public String nama;
    public String createdAt;
    public String jmlMasuk;


    public Model_Barang() {
    }

    public Model_Barang(Parcel in) {
        idTransaksi = in.readString();
        nama = in.readString();
        createdAt = in.readString();
        jmlMasuk = in.readString();

    }

    public static final Parcelable.Creator<Model_Barang> CREATOR = new Parcelable.Creator<Model_Barang>()
    {

        @Override
        public Model_Barang createFromParcel(Parcel in)
        {
            // TODO Auto-generated method stub
            return new Model_Barang(in);
        }

        @Override
        public Model_Barang[] newArray(int size)
        {
            // TODO Auto-generated method stub
            return new Model_Barang[size];
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
        out.writeString(idTransaksi);
        out.writeString(nama);
        out.writeString(createdAt);
        out.writeString(jmlMasuk);
    }
}
