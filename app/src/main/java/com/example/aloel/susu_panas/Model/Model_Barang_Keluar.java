package com.example.aloel.susu_panas.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aloel on 6/2/2017.
 */

public class Model_Barang_Keluar implements Parcelable
{

    public String nama_klr;
    public String brg_klr;
    public int tgl_klr;

    public Model_Barang_Keluar()
    {


    }

    public Model_Barang_Keluar(Parcel in)
    {
        nama_klr = in.readString();
        brg_klr = in.readString();
        tgl_klr = in.readInt();
    }


    public static final Parcelable.Creator<Model_Barang_Keluar> CREATOR = new Parcelable.Creator<Model_Barang_Keluar>() {

        @Override
        public Model_Barang_Keluar createFromParcel(Parcel in)
        {
            // TODO Auto-generated method stub
            return new Model_Barang_Keluar(in);
        }

        @Override
        public Model_Barang_Keluar[] newArray(int size)
        {
            // TODO Auto-generated method stub
            return new Model_Barang_Keluar[size];
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
        out.writeString(nama_klr);
        out.writeString(brg_klr);
        out.writeInt(tgl_klr);
    }

}
