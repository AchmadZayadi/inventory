package com.example.aloel.susu_panas.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aloel on 6/2/2017.
 */

public class Model_Barang_Masuk implements Parcelable
{

    public String nama_msk;
    public String brg_msk;
    public int tgl_msk;

    public Model_Barang_Masuk()
    {


    }

    public Model_Barang_Masuk(Parcel in)
    {
        nama_msk = in.readString();
        brg_msk = in.readString();
        tgl_msk = in.readInt();
    }


    public static final Parcelable.Creator<Model_Barang_Masuk> CREATOR = new Parcelable.Creator<Model_Barang_Masuk>()
    {

        @Override
        public Model_Barang_Masuk createFromParcel(Parcel in)
        {
            // TODO Auto-generated method stub
            return new Model_Barang_Masuk(in);
        }

        @Override
        public Model_Barang_Masuk[] newArray(int size)
        {
            // TODO Auto-generated method stub
            return new Model_Barang_Masuk[size];
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
        out.writeString(nama_msk);
        out.writeString(brg_msk);
        out.writeInt(tgl_msk);
    }
}
