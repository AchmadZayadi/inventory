package com.example.aloel.susu_panas.Adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aloel.susu_panas.Model.Model_Barang_Masuk;
import com.example.aloel.susu_panas.R;
import com.example.aloel.susu_panas.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZAYADI on 6/2/2017.
 */

public class Adapter_Barang_Masuk extends RecyclerView.Adapter<Adapter_Barang_Masuk.ItemViewHolderCategoryList>
{
    private Context mContext;
    private ArrayList<Model_Barang_Masuk> mDaerahList;
    private Adapter_Barang_Masuk.OnItemClickListener mListener;
    private Adapter_Barang_Masuk.MoreCerita mMoreCerita;
    private List<String> items ;

    public Adapter_Barang_Masuk(ArrayList<Model_Barang_Masuk> mDaerahListt, Context context)
    {

        mDaerahList = mDaerahListt;
        mContext = context;

    }
    public void moreCerita(Adapter_Barang_Masuk.MoreCerita morecerita)
    {
        mMoreCerita = morecerita;
    }

    @Override
    public Adapter_Barang_Masuk.ItemViewHolderCategoryList onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_view_barang_masuk, parent, false);

        return new Adapter_Barang_Masuk.ItemViewHolderCategoryList(itemView);
    }

    public void setData(ArrayList<Model_Barang_Masuk> data)

    {
        this.mDaerahList = data;
    }

    @Override
    public int getItemCount()

    {
        return (mDaerahList.size());
    }

    @Override
    public void onBindViewHolder(Adapter_Barang_Masuk.ItemViewHolderCategoryList holder, final int position)
    {
        Model_Barang_Masuk barang = mDaerahList.get(position);
        holder.Barang_masuk.setText(barang.brg_msk);
        holder.Nama.setText(barang.nama_msk);

        Log.e("tgl", String.valueOf(barang.tgl_msk));

        holder.Tgl_Masuk.setText(DateUtil.timeMilisToString24(barang.tgl_msk * 1000L));


    }

    class ItemViewHolderCategoryList extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView Barang_masuk;
        TextView Nama;
        TextView Tgl_Masuk;
        public ItemViewHolderCategoryList(View itemView)
        {
            super(itemView);
            Nama = (TextView) itemView.findViewById(R.id.nama_stock_view);
            Barang_masuk = (TextView) itemView.findViewById(R.id.stock_stock_view);
            Tgl_Masuk = (TextView) itemView.findViewById(R.id.stock_tanggal_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if (mListener != null)
            {
                mListener.onItemClick(v, getPosition());
            }
        }
    }

    public void setClickListener(Adapter_Barang_Masuk.OnItemClickListener clickListener)
    {
        this.mListener = clickListener;
    }

    public interface OnItemClickListener
    {
        public abstract void onItemClick(View view, int position);
    }

    public interface MoreCerita
    {
        public abstract void OnMoreClickCerita(View view, int position);
    }

}
