package com.example.hp.voicetext;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG  = "yin_voiceText";

    private TextToSpeech  tts = null ;
    private Button bt_textVoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initTTS();
        initView();

    }

    private void initTTS(){
        //实例化自带语音对象
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == tts.SUCCESS){
                    //Locale loc1 = new Locale("us");

                    tts.setPitch(1.0f);//控制音调
                    tts.setSpeechRate(1.0f);//控制语速

                    //判断是否支出以下两种语言
                    int result1 = tts.setLanguage(Locale.US);
                    int result2 = tts.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);
                    Log.i(TAG, "onInit: 支持US：" +a+"\n"+"支持zh-CN："+ b);


                }else {
                    Log.i(TAG, "onInit: 数据丢失或不支持！");
                }


            }
        });
    }
    private void initView(){
        bt_textVoice = (Button) findViewById(R.id.bt_main_textVoice);

        bt_textVoice.setOnClickListener(this);
    }


    private void startAuto(String data){
        tts.speak(data,TextToSpeech.QUEUE_FLUSH,null,"yin_text");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_main_textVoice:
                startAuto("hello world i like you");
                break;
        }
    }

    protected  void onStop() {
        super.onStop();
        tts.stop();//不管是否在朗读，tts都会被打断
        tts.shutdown();//关闭释放资源
    }
}
