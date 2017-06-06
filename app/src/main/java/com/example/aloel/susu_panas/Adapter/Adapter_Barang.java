package com.example.aloel.susu_panas.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aloel.susu_panas.Model.Model_Barang;
import com.example.aloel.susu_panas.R;
import java.util.ArrayList;

public class Adapter_Barang extends RecyclerView.Adapter<Adapter_Barang.ItemViewHolderCategoryList>
{
    private Context mContext;
    private ArrayList<Model_Barang> mDaerahList;
    View view;
    private OnItemClickListener mListener;
    private MoreCerita mMoreCerita;
    public Adapter_Barang(ArrayList<Model_Barang> mDaerahListt, Context context)
    {
        mDaerahList = mDaerahListt;
        mContext = context;
    }
    public void moreCerita(MoreCerita morecerita)
    {
        mMoreCerita = morecerita;
    }
    @Override
    public ItemViewHolderCategoryList onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.tabel_barang_isi, parent, false);
        return new ItemViewHolderCategoryList(itemView);
    }
    public void setData(ArrayList<Model_Barang> data)
    {
        this.mDaerahList = data;
    }
    @Override
    public int getItemCount()
    {
        return (mDaerahList.size());
    }
    @Override
    public void onBindViewHolder(ItemViewHolderCategoryList holder, final int position)
    {
        Model_Barang barang = mDaerahList.get(position);
        holder.ID.setText(barang.idTransaksi);
        holder.NAMA.setText(barang.nama);
        holder.TANGGAL.setText(barang.createdAt);
        holder.JUMLAH_MASUK.setText(barang.jmlMasuk);
    }
    class ItemViewHolderCategoryList extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView ID;
        TextView NAMA;
        TextView TANGGAL;
        TextView JUMLAH_MASUK;
        public ItemViewHolderCategoryList(View itemView)
        {
            super(itemView);
            ID = (TextView) itemView.findViewById(R.id.id_stock_View);
            NAMA = (TextView) itemView.findViewById(R.id.nama_stock_view);
            TANGGAL = (TextView) itemView.findViewById(R.id.stock_tanggal_view);
            JUMLAH_MASUK = (TextView) itemView.findViewById(R.id.stock_stock_view);
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

    public void setClickListener(OnItemClickListener clickListener)
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
