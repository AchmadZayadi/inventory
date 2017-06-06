package com.example.aloel.susu_panas.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aloel.susu_panas.Model.Model_Barang_Keluar;
import com.example.aloel.susu_panas.R;
import com.example.aloel.susu_panas.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZAYADI on 6/2/2017.
 */

public class Adapter_Barang_Keluar extends RecyclerView.Adapter<Adapter_Barang_Keluar.ItemViewHolderCategoryList>
{
    private Context mContext;
    private ArrayList<Model_Barang_Keluar> mDaerahList;
    private Adapter_Barang_Keluar.OnItemClickListener mListener;
    private Adapter_Barang_Keluar.MoreCerita mMoreCerita;
    private List<String> items ;
    public Adapter_Barang_Keluar(ArrayList<Model_Barang_Keluar> mDaerahListt, Context context)
    {
        mDaerahList = mDaerahListt;
        mContext = context;
    }
    public void moreCerita(Adapter_Barang_Keluar.MoreCerita morecerita)
    {
        mMoreCerita = morecerita;
    }

    @Override
    public Adapter_Barang_Keluar.ItemViewHolderCategoryList onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_view_send, parent, false);

        return new Adapter_Barang_Keluar.ItemViewHolderCategoryList(itemView);
    }

    public void setData(ArrayList<Model_Barang_Keluar> data)
    {
        this.mDaerahList = data;
    }

    @Override
    public int getItemCount()
    {
        return (mDaerahList.size());
    }

    @Override
    public void onBindViewHolder(Adapter_Barang_Keluar.ItemViewHolderCategoryList holder, final int position)
    {
        Model_Barang_Keluar barang = mDaerahList.get(position);
        holder.nama_klr.setText(barang.nama_klr);
        holder.brg_klr.setText(barang.brg_klr);
        holder.tgl_klr.setText(DateUtil.timeMilisToString24(barang.tgl_klr * 1000L));
    }

    class ItemViewHolderCategoryList extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView nama_klr;
        TextView brg_klr;
        TextView tgl_klr;

        public ItemViewHolderCategoryList(View itemView)
        {
            super(itemView);
            nama_klr = (TextView) itemView.findViewById(R.id.product);
            brg_klr = (TextView) itemView.findViewById(R.id.category);
            tgl_klr = (TextView) itemView.findViewById(R.id.price);
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

    public void setClickListener(Adapter_Barang_Keluar.OnItemClickListener clickListener)
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
