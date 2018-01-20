package com.sugoroku.sugoroku;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.*;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions markerOptions = new MarkerOptions();
    private Marker[] marker = new Marker[4];
    private int Num = 0;

    double[][] i_k = {
            {-35,150} //i_k[0][0],i_k[0][1]
            ,{-30,145} //i_k[1][0],i_k[1][1]
            ,{-25,140} //i_k[2][0],i_k[2][1]
            ,{-20,135} //i_k[3][0],i_k[3][1]
            ,{-15,130} //i_k[4][0],i_k[4][1]
            ,{-10,125} //i_k[5][0],i_k[5][1]
            ,{-5,120} //i_k[6][0],i_k[6][1]
            ,{0,115} //i_k[7][0],i_k[7][1]
            ,{5,110} //i_k[8][0],i_k[8][1]
    };

    private final int goal = 8; //i_kの配列の数 - 1

    //LatLng[] koma = new LatLng[4];
    private LatLng komaP;
    private int koma[] = {0,0,0,0};
    private int komaN;

    private boolean[] goalCheck = {false,false,false,false};

    private String titleT;
    private double i_k_0;
    private double i_k_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_k[0][0] += 5;
                i_k[0][1] += 5;

                if(i_k[0][0] > 90){
                    i_k[0][0] -= 180;
                }
                if(i_k[0][1] > 360){
                    i_k[0][1] -= 360;
                }

                if(goalCheck[Num] == true) {
                    moveM();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ini();
    }

    public void ini(){
        String titleT = "";
        double i_k_0;
        double i_k_1;

        for(int i=3; i > 0; i--) {
            i_k_0 = i_k[0][0] + (double)i;
            i_k_1 = i_k[0][1] + (double)i;
            komaP = new LatLng(i_k_0, i_k_1);
            titleT = (i+1) + "P";
            marker[i] = mMap.addMarker(markerOptions.position(komaP).title(titleT));
            goalCheck[i] = true;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(komaP));
        dialogR(titleT);
    }

    public void moveM(){
        marker[Num].remove();

        koma[Num] += randomM();

        if(koma[Num] >= goal) {
            koma[Num] = goal;
            goalCheck[Num] = false;
        }

        komaN = koma[Num];

        i_k_0 = i_k[komaN][0] + (double)Num;
        i_k_1 = i_k[komaN][1] + (double)Num;

        //komaP = new LatLng(i_k[koma[Num]][0], i_k[koma[Num]][1]);
        //komaP = new LatLng(i_k[0][0], i_k[0][1]);
        komaP = new LatLng(i_k_0, i_k_1);

        String titleT = (Num+1) + "P";

        marker[Num] = mMap.addMarker(markerOptions
                .position(komaP)
                .title(titleT));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(komaP));

        dialogR(titleT);

        Num = (Num+1)%4;
    }

    public int randomM(){
        int i;
        /*
        Random random = new Random();
        i = random.nextInt(5) + 1;
        */

        i = 1;

        //dialogM();

        return i;
    }

    /*
    public void dialogM(){
        new AlertDialog.Builder(this)
                .setTitle("ああああ")
                .setMessage("いいいいいいいいいいい")
                .setPositiveButton("YES",null)
                .setNegativeButton("NO",null)
                .show();
    }
    */

    public void dialogR(String titleT){
        if(komaN == goal){
            new AlertDialog.Builder(this)
                    .setTitle("ゴール確認")
                    .setMessage(titleT)
                    .setPositiveButton("YES",null)
                    .setNegativeButton("NO",null)
                    .show();

            handler.post(r);
        }

        String titleT_T = titleT + "のターン";

        new AlertDialog.Builder(this)
                .setTitle("ターン確認")
                .setMessage(titleT_T)
                .setPositiveButton("YES",null)
                .setNegativeButton("NO",null)
                .show();
    }

    final Handler handler = new Handler();
    final Runnable r = new Runnable() {
        int count = 0;
        @Override
        public void run() {
            // UIスレッド
            count++;
            if (count > 5) { // 5回実行したら終了
                count = 0;
                return;
            }
            doSomething(); // 何かやる
            handler.postDelayed(this, 1000);
        }
    };
    //handler.post(r);

    public void doSomething(){

    }
}
