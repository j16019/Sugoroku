package com.sugoroku.sugoroku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.*;

import java.util.Random;

/*j16019*/

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions markerOptions = new MarkerOptions();
    private Marker[] marker = new Marker[4];//プレイヤーのマーカーの情報を保存
    private int num = 0;//ターン

    private double zure = 0.001;//プレイヤーのコマ同士の間隔

    //マスの座標
    private double[][] i_k = {
            {26.2125,127.68111}	//那覇		i_k[0][0],i[0][1]
            ,{28.380746,129.496922}	//奄美大島	i_k[1][0],i[1][1]
            ,{31.56028,130.55806}	//鹿児島	i_k[2][0],i[2][1]
            ,{31.91111,131.42389}	//宮城		i_k[3][0],i[3][1]
            ,{32.78972,130.74167}	//熊本		i_k[4][0],i[4][1]
            ,{32.74472,129.87361}	//長崎		i_k[5][0],i[5][1]
            ,{33.24944,130.29889}	//佐賀		i_k[6][0],i[6][1]
            ,{33.60639,130.41806}	//福岡		i_k[7][0],i[7][1]
            ,{33.23806,131.6125}	//大分		i_k[8][0],i[8][1]
            ,{33.55972,133.53111}	//高知		i_k[9][0],i[9][1]
            ,{33.84167,132.76611}	//愛媛		i_k[10][0],i[10][1]
            ,{34.18583,131.47139}	//山口		i_k[11][0],i[11][1]
            ,{34.39639,132.45944}	//広島		i_k[12][0],i[12][1]
            ,{34.66167,133.935}	//岡山		i_k[13][0],i[13][1]
            ,{34.34028,134.04333}	//香川		i_k[14][0],i[14][1]
            ,{34.06583,134.55944}	//徳島		i_k[15][0],i[15][1]
            ,{34.69139,135.18306}	//兵庫		i_k[16][0],i[16][1]
            ,{35.02139,135.75556}	//京都		i_k[17][0],i[17][1]
            ,{34.68639,135.52}	//大阪		i_k[18][0],i[18][1]
            ,{34.68528,135.83278}	//奈良		i_k[19][0],i[19][1]
            ,{34.22611,135.1675}	//和歌山	i_k[20][0],i[20][1]
            ,{34.73028,136.50861}	//三重		i_k[21][0],i[21][1]
            ,{35.00444,135.86833}	//滋賀		i_k[22][0],i[22][1]
            ,{36.06528,136.22194}	//福井		i_k[23][0],i[23][1]
            ,{36.59444,136.62556}	//石川		i_k[24][0],i[24][1]
            ,{36.69528,137.21139}	//富山		i_k[25][0],i[25][1]
            ,{35.39111,136.72222}	//岐阜		i_k[26][0],i[26][1]
            ,{35.18028,136.90667}	//愛知		i_k[27][0],i[27][1]
            ,{34.97694,138.38306}	//静岡		i_k[28][0],i[28][1]
            ,{35.66389,138.56833}	//山梨		i_k[29][0],i[29][1]
            ,{36.65139,138.18111}	//長野		i_k[30][0],i[30][1]
            ,{37.90222,139.02361}	//新潟		i_k[31][0],i[31][1]
            ,{36.39111,139.06083}	//群馬		i_k[32][0],i[32][1]
            ,{35.85694,139.64889}	//埼玉		i_k[33][0],i[33][1]
            ,{35.44778,139.6425}	//神奈川	i_k[34][0],i[34][1]
            ,{35.68944,139.69167}	//東京		i_k[35][0],i[35][1]
            ,{35.60472,140.12333}	//千葉		i_k[36][0],i[36][1]
            ,{36.34139,140.44667}	//茨城		i_k[37][0],i[37][1]
            ,{36.56583,139.88361}	//栃木		i_k[38][0],i[38][1]
            ,{37.75,140.46778}	//福島		i_k[39][0],i[39][1]
            ,{38.24056,140.36333}	//山形		i_k[40][0],i[40][1]
            ,{38.26889,140.87194}	//宮城		i_k[41][0],i[41][1]
            ,{39.70361,141.1525}	//岩手		i_k[42][0],i[42][1]
            ,{39.71861,140.1025}	//秋田		i_k[43][0],i[43][1]
            ,{40.82444,140.74}	//青森		i_k[44][0],i[44][1]
            ,{41.774400,140.725971}	//函館（北海道）	i_k[45][0],i[45][1]
            ,{42.255920,140.265028}	//八雲町（北海道）	i_k[46][0],i[46][1]
            ,{42.513721,140.380685}	//長万部町（北海道）	i_k[47][0],i[47][1]
            ,{42.804660,140.687510}	//ニセコ町（北海道）	i_k[48][0],i[48][1]
            ,{43.062542,141.353584}	//札幌（北海道）	i_k[49][0],i[49][1]
            ,{43.557693,141.910482}	//滝川市（北海道）	i_k[50][0],i[50][1]
            ,{43.770858,142.366015}	//旭川市（北海道）	i_k[51][0],i[51][1]
            ,{44.725727,142.262515}	//音威子府村（北海道）	i_k[52][0],i[52][1]
            ,{45.102961,141.776806}	//豊富町（北海道）	i_k[53][0],i[53][1]
            ,{45.449592,141.645252}	//稚内市稚内灯台（北海道）i_k[54][0],i[54][1]
    };

    //マスの名前
    private String[] markerM_T = {
            "那覇"//
            ,"奄美大島"//i_k[1][0],i[1][1]
            ,"鹿児島"//i_k[2][0],i[2][1]
            ,"宮城"	//i_k[3][0],i[3][1]
            ,"熊本"	//i_k[4][0],i[4][1]
            ,"長崎"	//i_k[5][0],i[5][1]
            ,"佐賀"	//i_k[6][0],i[6][1]
            ,"福岡"	//i_k[7][0],i[7][1]
            ,"大分"	//i_k[8][0],i[8][1]
            ,"高知"	//i_k[9][0],i[9][1]
            ,"愛媛"	//i_k[10][0],i[10][1]
            ,"山口"	//i_k[11][0],i[11][1]
            ,"広島"	//i_k[12][0],i[12][1]
            ,"岡山"	//i_k[13][0],i[13][1]
            ,"香川"	//i_k[14][0],i[14][1]
            ,"徳島"	//i_k[15][0],i[15][1]
            ,"兵庫"	//i_k[16][0],i[16][1]
            ,"京都"	//i_k[17][0],i[17][1]
            ,"大阪"	//i_k[18][0],i[18][1]
            ,"奈良"	//i_k[19][0],i[19][1]
            ,"和歌山"//i_k[20][0],i[20][1]
            ,"三重"//i_k[21][0],i[21][1]
            ,"滋賀"//i_k[22][0],i[22][1]
            ,"福井"//i_k[23][0],i[23][1]
            ,"石川"//i_k[24][0],i[24][1]
            ,"富山"//i_k[25][0],i[25][1]
            ,"岐阜"//i_k[26][0],i[26][1]
            ,"愛知"//i_k[27][0],i[27][1]
            ,"静岡"//i_k[28][0],i[28][1]
            ,"山梨"//i_k[29][0],i[29][1]
            ,"長野"//i_k[30][0],i[30][1]
            ,"新潟"//i_k[31][0],i[31][1]
            ,"群馬"//i_k[32][0],i[32][1]
            ,"埼玉"//i_k[33][0],i[33][1]
            ,"神奈川"//i_k[34][0],i[34][1]
            ,"東京"//i_k[35][0],i[35][1]
            ,"千葉"//i_k[36][0],i[36][1]
            ,"茨城"//i_k[37][0],i[37][1]
            ,"栃木"//i_k[38][0],i[38][1]
            ,"福島"//i_k[39][0],i[39][1]
            ,"山形"//i_k[40][0],i[40][1]
            ,"宮城"//i_k[41][0],i[41][1]
            ,"岩手"//i_k[42][0],i[42][1]
            ,"秋田"//i_k[43][0],i[43][1]
            ,"青森"//i_k[44][0],i[44][1]
            ,"函館（北海道）"//i_k[45][0],i[45][1]
            ,"八雲町（北海道）"//i_k[46][0],i[46][1]
            ,"長万部町（北海道）"//i_k[47][0],i[47][1]
            ,"ニセコ町（北海道）"//i_k[48][0],i[48][1]
            ,"札幌（北海道）"//i_k[49][0],i[49][1]
            ,"滝川市（北海道）"//i_k[50][0],i[50][1]
            ,"旭川市（北海道）"//i_k[51][0],i[51][1]
            ,"音威子府村（北海道）"//i_k[52][0],i[52][1]
            ,"豊富町（北海道）"//i_k[53][0],i[53][1]
            ,"稚内市稚内灯台（北海道）"//i_k[54][0],i[54][1]
    };

    //サイコロの画像のID
    private int[] saikoroI = {
            R.drawable.saikoro_1
            , R.drawable.saikoro_2
            , R.drawable.saikoro_3
            , R.drawable.saikoro_4
            , R.drawable.saikoro_5
            , R.drawable.saikoro_6
    };

    //プレイヤーのマーカーの画像のID
    private BitmapDescriptor[] markerI_P = new BitmapDescriptor[4];

    //マスのマーカーの色
    private BitmapDescriptor[] markerI_C = new BitmapDescriptor[6];

    private final int[] kugiri = {1, 9, 16, 29, 36, 45, 54};//色の変更点
    private final int kugiriN = 6; //kugiriの配列数-1

    private final int goal = 54; //i_kの配列の数 - 1

    private LatLng[] komaP = new LatLng[4];
    private int[] koma = {0, 0, 0, 0};
    private int komaN;

    private Button saikoroButton;

    private int saikoroM;

    private boolean[] goalCheck = {false, false, false, false};

    private String titleT;
    private double i_k_0;
    private double i_k_1;

    private TextView[] textView = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //縦画面固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView[0] = (TextView)findViewById(R.id.textView);
        textView[1] = (TextView)findViewById(R.id.textView2);
        textView[2] = (TextView)findViewById(R.id.textView3);
        textView[3] = (TextView)findViewById(R.id.textView4);

        textView[0].setTextColor(Color.BLUE);
        textView[1].setTextColor(Color.RED);
        textView[2].setTextColor(Color.rgb(255,200,0));
        textView[3].setTextColor(Color.rgb(0,200,0));

        saikoroButton = (Button) findViewById(R.id.button2);

        saikoroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (goalCheck[num]) {
                    saikoroButton.setEnabled(false);

                    keycheck = false;//バックキーをoff

                    saikoroShake();//サイコロを振る
                }
            }
        });


    }
    private UiSettings uiSettings;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        uiSettings = mMap.getUiSettings();

        uiSettings.setMapToolbarEnabled(false);//GoogleMapの使わないボタンを消去
        //uiSettings.setScrollGesturesEnabled(false);

        mMap.setMinZoomPreference(6.5f);//縮小の最大を設定

        markerI_P[0] = BitmapDescriptorFactory.fromResource(R.drawable.p1);
        markerI_P[1] = BitmapDescriptorFactory.fromResource(R.drawable.p2);
        markerI_P[2] = BitmapDescriptorFactory.fromResource(R.drawable.p3);
        markerI_P[3] = BitmapDescriptorFactory.fromResource(R.drawable.p4);

        markerI_C[0] = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);//1~8
        markerI_C[1] = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);//9~15
        markerI_C[2] = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);//16~28
        markerI_C[3] = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);//29~35
        markerI_C[4] = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA);//36~44
        markerI_C[5] = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);//45~53

        iniM();

        iniP();
    }

    //マスのマーカーの初期化
    public void iniM() {
        PolylineOptions polylineOptions = new PolylineOptions().geodesic(true);
        LatLng latLng;

        latLng = new LatLng(i_k[0][0], i_k[0][1]);

        mMap.addMarker(markerOptions
                .position(latLng)
                .title(markerM_T[0])
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_yellow_s))
        );

        polylineOptions.add(latLng);

        for(int j = 0; j < kugiriN; j++){
            for(int i = kugiri[j]; i < kugiri[j+1]; i++){
                latLng = new LatLng(i_k[i][0], i_k[i][1]);

                mMap.addMarker(markerOptions
                        .position(latLng)
                        .title(markerM_T[i])
                        .icon(markerI_C[j])
                );

                polylineOptions.add(latLng);
            }
        }

        latLng = new LatLng(i_k[goal][0], i_k[goal][1]);

        mMap.addMarker(markerOptions
                .position(latLng)
                .title(markerM_T[goal])
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_yellow_g))
        );

        polylineOptions.add(latLng);

        mMap.addPolyline(polylineOptions);
    }

    //プレイヤーのマーカーの初期化
    public void iniP() {
        for (int i = 3; i >= 0; i--) {
            i_k_0 = i_k[0][0];
            i_k_1 = i_k[0][1] + ((double)(i+1) * zure);
            komaP[i] = new LatLng(i_k_0, i_k_1);
            titleT = (i + 1) + "P";
            marker[i] = mMap.addMarker(markerOptions
                    .position(komaP[i])
                    .title(titleT)
                    .icon(markerI_P[i])
            );

            textView[i].setText(titleT + "のターン\n残り" + (goal - koma[i]) + "マス\n現在地：" + markerM_T[koma[i]]);

            goalCheck[i] = true;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(komaP[0], 7.5F));

        new AlertDialog.Builder(this)
                .setTitle("確認ダイアログ")
                .setMessage(titleT + "のターンです")
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .show();
    }

    //サイコロを振る
    public void saikoroShake(){

        handler.post(sr);

    }

    final Handler handler = new Handler();

    final Runnable sr = new Runnable() {
        int speed = 100;
        int count = 0;

        @Override
        public void run(){
            count++;

            saikoroM = randomM();

            saikoroButton.setBackgroundResource(saikoroI[saikoroM - 1]);

            if(count == 17){
                speed = 600;
            }

            if(count == 20){
                count = 0;
                speed = 100;

                moveD();

                return;
            }

            handler.postDelayed(this, speed);
        }
    };
    //handler.post(sr);

    //サイコロの結果を表示
    public void moveD(){
        new AlertDialog.Builder(this)
                .setTitle("確認ダイアログ")
                .setMessage(saikoroM + "マス進む")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveM();
                    }
                })
                .setCancelable(false)
                .show();
    }

    //プレイヤーのマーカーの移動
    public void moveM() {
        titleT = (num + 1) + "P";

        komaN = koma[num];//前回の位置を保存

        koma[num] += saikoroM;//randomの結果を代入

        if (koma[num] >= goal) {
            koma[num] = goal;
            goalCheck[num] = false;
        }

        handler.post(mr);
    }

    final Runnable mr = new Runnable() {
        int count = 0;

        @Override
        public void run() {
            if(komaN == koma[num]){
                count++;
            }

            if(komaN != koma[num]) {
                marker[num].remove();

                komaN++;

                i_k_0 = i_k[komaN][0];
                i_k_1 = i_k[komaN][1] + ((double) (num + 1) * zure);

                komaP[num] = new LatLng(i_k_0, i_k_1);

                marker[num] = mMap.addMarker(markerOptions
                        .position(komaP[num])
                        .title(titleT)
                        .icon(markerI_P[num])
                );

                mMap.moveCamera(CameraUpdateFactory.newLatLng(komaP[num]));

                textView[num].setText(titleT + "のターン\n残り" + (goal - komaN) + "マス\n現在地：" + markerM_T[komaN]);
            }

            if(count == 1) {
                count = 0;

                nextD();

                return;
            }

            handler.postDelayed(this, 1000);
        }
    };
    //handler.post(mr);

    //ゴール確認
    public void nextD(){
        if (!goalCheck[num]) {

            new AlertDialog.Builder(this)
                    .setTitle("確認ダイアログ")
                    .setMessage(titleT + "がゴールしました。")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            num = (num + 1) % 4;
                            titleT = (num + 1) + "P";

                            nextT();
                        }
                    })
                    .setCancelable(false)
                    .show();
        } else {
            num = (num + 1) % 4;
            titleT = (num + 1) + "P";

            nextT();
        }

        saikoroButton.setEnabled(true);
    }

    //移動した結果を出力
    public void nextT(){
        mMap.moveCamera(CameraUpdateFactory.newLatLng(komaP[num]));

        new AlertDialog.Builder(this)
                .setTitle("確認ダイアログ")
                .setMessage("次は" + titleT + "のターンです")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView[num].setText(titleT + "のターン\n残り" + (goal - koma[num]) + "マス\n現在地：" + markerM_T[koma[num]]);

                        keycheck = true;
                    }
                })
                .setCancelable(false)
                .show();
    }

    //サイコロの目
    public int randomM() {
        int i;

        Random random = new Random();
        i = random.nextInt(6) + 1; //0～5に+1して、1～6まででランダム

        return i;
    }


    private boolean keycheck = true;

    //画面を閉じる処理
    /*j16004*/
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:

                if(keycheck) {
                    new AlertDialog.Builder(this)
                            .setTitle("タイトルに戻る")
                            .setMessage("タイトルに戻ります。進捗は保存されません。よろしいですか？")
                            .setNegativeButton("NO", null)
                            .setPositiveButton("YES",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
                                        }
                                    }
                            )
                            .show();
                }
        }
        return false;
    }
}