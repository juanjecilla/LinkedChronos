package com.m0n0l0c0.LinkedChronos.ui;

import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.m0n0l0c0.LinkedChronos.R;
import com.m0n0l0c0.LinkedChronos.base.BaseActivity;
import com.m0n0l0c0.LinkedChronos.interfaces.CheckEmpty;
import com.m0n0l0c0.LinkedChronos.model.Chrono;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements CheckEmpty {

    private ArrayList<Chrono> data;
    private TextView empty;
    private ChronoAdapter chronoAdapter;
    private int actualChrono = 0;
    private long actualChronoTimeLeft = 0;

    private RecyclerView recyclerView;

    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new ArrayList<>();
        chronoAdapter = new ChronoAdapter(data, this);

        empty = (TextView)findViewById(R.id.empty_view);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(chronoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);

    }

    private void createNewChrono(long timeInMilis){

        CountDownTimer chrono = new CountDownTimer(timeInMilis, 1000) {
            @Override
            public void onTick(long l) {

                Log.d("CHRONO", "Chrono " + actualChrono + "----- Time left = " + actualChronoTimeLeft);
                actualChronoTimeLeft = l;
            }

            @Override
            public void onFinish() {

                Log.d("CHRONO", "Chrono " + actualChrono + " Finished!");
                Toast.makeText(getApplicationContext(), "Chrono number " + actualChrono +  " finished!", Toast.LENGTH_SHORT).show();

                actualChrono++;
                actualChronoTimeLeft = 0;

                if (actualChrono < data.size()){
                    data.get(actualChrono).getCountDownTimer().start();
                } else {
                    actualChrono = 0;
                    data.get(0).getCountDownTimer().start();
                }

                ringtone.play();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ringtone.stop();

                    }
                }, 2000);

            }

        };

        String name = getResources().getString(R.string.chrono) + " " + (data.size()+1);

        Chrono newChrono = new Chrono(timeInMilis, name, chrono);

        chronoAdapter.insertData(data.size(), newChrono);
        checkEmptyView();

    }

    private void startChronoChain(){

        if (data.size()!=0){
            data.get(0).getCountDownTimer().start();
        } else {
            Toast.makeText(this, R.string.no_chronos, Toast.LENGTH_LONG).show();
        }

    }

    private void resetChronoChain(){

        if (data.size() != 0){
            data.get(actualChrono).getCountDownTimer().cancel();
            Toast.makeText(this, R.string.chain_stopped, Toast.LENGTH_LONG).show();
        }
    }

    public void createNewChrono(View view) {
        getChronoTime();
    }

    public void startChronoChain(View view) {
        startChronoChain();
    }

    private void getChronoTime(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        editText.setHint(R.string.time_in_seconds);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        alertDialog.setTitle(R.string.new_chrono)
                .setCancelable(true)
                .setTitle(R.string.new_chrono)
                .setView(editText)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        long time = Integer.parseInt(editText.getText().toString());
                        if (time > 359999000){
                            Toast.makeText(getApplicationContext(), R.string.number_too_high, Toast.LENGTH_LONG).show();
                        }
                        createNewChrono(time*1000);
                    }
                });

        alertDialog.show();
    }

    public void resetChronoChain(View view) {
        resetChronoChain();
    }

    @Override
    public void checkEmptyView() {
        if (data.size() == 0){
            recyclerView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
    }
}
