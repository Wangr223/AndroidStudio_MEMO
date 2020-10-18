package com.example.mynote;

import android.content.Context;
import android.content.Intent;
import android.security.identity.CipherSuiteNotSupportedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TickerAdapter extends RecyclerView.Adapter<TickerAdapter.ViewHolder>{
    private List<Ticker> aTickerList;
    private Context aContext;

    @NonNull
    @Override
    public TickerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note,parent,false);
        final ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TickerAdapter.ViewHolder holder, int position) {
        Ticker tker = aTickerList.get(position);
        holder.contentTXT.setText(tker.getContent());
        holder.timeTXT.setText(tker.getTime());
    }

    @Override
    public int getItemCount() {
        return aTickerList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public static OnItemClickListener mOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        View WholeView;
        TextView timeTXT;
        TextView contentTXT;
        Button checkBTN;

        public ViewHolder(View v){
            super(v);
            WholeView = v;
            timeTXT = v.findViewById(R.id.note_time);
            contentTXT = v.findViewById(R.id.note_content);
            checkBTN = v.findViewById(R.id.note_delete);
            checkBTN.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public TickerAdapter(List<Ticker> tl, Context context){
        aTickerList = tl;
        aContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }


}
