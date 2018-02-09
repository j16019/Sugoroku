package com.sugoroku.sugoroku;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Random;

/*j16004*/

public class TitleActivity extends AppCompatActivity {

    Button startButton;
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        //縦画面固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_title);
        
        //GIFの再生
        ImageView imageView = (ImageView) findViewById(R.id.gifView1);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.saikoro).into(target);

        startButton = (Button) findViewById(R.id.startButton);



        // ゲーム画面へ移動
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                //インターネットに接続されているかの判定
                if (networkInfo == null){
                    new AlertDialog.Builder(TitleActivity.this)
                            .setTitle("ネットワーク")
                            .setMessage("インターネットに接続されていません。インターネットに接続してください。")
                            .setPositiveButton("OK",null)
                            .setNeutralButton("Wifi設定をする",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Wifi設定画面を開く
                                            Intent intent = new Intent();
                                            intent.setAction(Settings.ACTION_WIFI_SETTINGS);
                                            if (intent.resolveActivity(getPackageManager()) != null) {
                                                startActivity(intent);
                                                overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
                                            }
                                        }
                                    }
                            )
                            .show();

                }else{
                    setTitle("");
                    num=0;
                    Intent intent = new Intent(getApplication(), MapsActivity.class);
                    startActivity(intent);

                    overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
                }
            }
        });

        //サイコロのGIFをタップした時の処理
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int n = r.nextInt(6) + 1;
                
                //ランダムに生成されたサイコロの目をダイアログで表示
                if (n == 1) {
                    num+=n;
                    ImageView i = new ImageView(TitleActivity.this);
                    i.setImageResource(R.drawable.saikoro_1);    //画像を指定
                    new AlertDialog.Builder(TitleActivity.this)
                            .setTitle("1")
                            .setView(i)
                            //.setMessage(s)
                            .setPositiveButton("Close", null)
                            .show();

                } else if (n == 2) {
                    num+=n;
                    ImageView i = new ImageView(TitleActivity.this);
                    i.setImageResource(R.drawable.saikoro_2);    //画像を指定
                    new AlertDialog.Builder(TitleActivity.this)
                            .setTitle("2")
                            .setView(i)
                            //.setMessage(s)
                            .setPositiveButton("Close", null)
                            .show();
                } else if (n == 3) {
                    num+=n;
                    ImageView i = new ImageView(TitleActivity.this);
                    i.setImageResource(R.drawable.saikoro_3);    //画像を指定
                    new AlertDialog.Builder(TitleActivity.this)
                            .setTitle("3")
                            .setView(i)
                            //.setMessage(s)
                            .setPositiveButton("Close", null)
                            .show();
                } else if (n == 4) {
                    num+=n;
                    ImageView i = new ImageView(TitleActivity.this);
                    i.setImageResource(R.drawable.saikoro_4);    //画像を指定
                    new AlertDialog.Builder(TitleActivity.this)
                            .setTitle("4")
                            .setView(i)
                            //.setMessage(s)
                            .setPositiveButton("Close", null)
                            .show();
                } else if (n == 5) {
                    num+=n;
                    ImageView i = new ImageView(TitleActivity.this);
                    i.setImageResource(R.drawable.saikoro_5);    //画像を指定
                    new AlertDialog.Builder(TitleActivity.this)
                            .setTitle("5")
                            .setView(i)
                            //.setMessage(s)
                            .setPositiveButton("Close", null)
                            .show();
                } else if (n == 6) {
                    num+=n;
                    ImageView i = new ImageView(TitleActivity.this);
                    i.setImageResource(R.drawable.saikoro_6);    //画像を指定
                    new AlertDialog.Builder(TitleActivity.this)
                            .setTitle("6")
                            .setView(i)
                            //.setMessage(s)
                            .setPositiveButton("Close", null)
                            .show();
                }
            }
        });
    }

}