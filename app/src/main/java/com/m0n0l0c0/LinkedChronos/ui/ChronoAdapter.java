package com.m0n0l0c0.LinkedChronos.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m0n0l0c0.LinkedChronos.R;
import com.m0n0l0c0.LinkedChronos.interfaces.CheckEmpty;
import com.m0n0l0c0.LinkedChronos.model.Chrono;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by juanje on 22/05/16.
 */
public class ChronoAdapter extends RecyclerView.Adapter<ChronoAdapter.ChronoViewHolder> {

    private ArrayList<Chrono> data;
    private Context context;
    private static final String FORMAT = "%02d:%02d:%02d";

    public static class ChronoViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView time;

        public ChronoViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.chrono_name);
            time = (TextView)itemView.findViewById(R.id.chrono_time);

        }

        public void bindChrono(Chrono chrono){
            name.setText(chrono.getName());

            long milis = chrono.getMiliseconds();

            time.setText(String.format(FORMAT, TimeUnit.MILLISECONDS.toHours(milis),
                    TimeUnit.MILLISECONDS.toMinutes(milis) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(milis)),
                    TimeUnit.MILLISECONDS.toSeconds(milis) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(milis))));
        }
    }

    public ChronoAdapter(ArrayList<Chrono> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ChronoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        ImageView delete = (ImageView) itemView.findViewById(R.id.delete_chrono);
        final int position = i;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(position);
                ((CheckEmpty)context).checkEmptyView();
            }
        });

        ChronoViewHolder chronoViewHolder = new ChronoViewHolder(itemView);

        return chronoViewHolder;
    }

    @Override
    public void onBindViewHolder(ChronoViewHolder chronoViewHolder, int i) {
        Chrono item = data.get(i);
        chronoViewHolder.bindChrono(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void insertData(int pos, Chrono chrono){
        data.add(pos, chrono);
        notifyItemInserted(pos);
    }

    public void deleteData(int pos){
        data.remove(pos);
        notifyItemRemoved(pos);
    }

}
