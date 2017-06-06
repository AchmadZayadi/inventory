package com.example.aloel.susu_panas.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.aloel.susu_panas.Model.Model_Stock_Barang;
import com.example.aloel.susu_panas.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.R.id.text;

/**
 * Created by Aloel on 5/22/2017.
 */

public class Adapter_Stock_barang extends RecyclerView.Adapter<Adapter_Stock_barang.ItemViewHolderCategoryList>
{


    private Context mContext;
    String text23;
    private ArrayList<Model_Stock_Barang> mDaerahList;
    View view;
    private OnItemClickListener mListener;
    private MoreCerita mMoreCerita;
    private List<String> items ;

    public Adapter_Stock_barang(ArrayList<Model_Stock_Barang> mDaerahListt, Context context)
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
                inflate(R.layout.list_view_stock, parent, false);

        return new ItemViewHolderCategoryList(itemView);
    }

    public void setData(ArrayList<Model_Stock_Barang> data)
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
        Model_Stock_Barang barang = mDaerahList.get(position);
        holder.NAMA.setText(barang.nama);
        holder.STOCK.setText(barang.stock);
    }

    class ItemViewHolderCategoryList extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView NAMA;
        TextView STOCK;
        public ItemViewHolderCategoryList(View itemView)
        {
            super(itemView);
            NAMA = (TextView) itemView.findViewById(R.id.nama_stock_view);
            STOCK = (TextView) itemView.findViewById(R.id.stock_stock_view);
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
