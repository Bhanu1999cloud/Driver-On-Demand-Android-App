package com.example.driverondemandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class Welcome extends AppCompatActivity {
TextToSpeech text_to_speech;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        speak("Namaste Driver On Demand App mai apka swaqaat hai");
    }
        public void speak(final String text){ // make text 'final'

            // ... do not declare tts here

            text_to_speech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS){
                        int result = text_to_speech.setLanguage(Locale.ENGLISH);
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                            Log.e("TTS", "Language not supported");
                        } else {

                            text_to_speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } else {
                        Log.e("TTS", "Failed");
                    }
                }
            });


      Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this, Login.class);
                startActivity(intent);
                Welcome.this.finish();
            }
        }, 4000);


    }
    @Override
    protected void onDestroy() {

        if(text_to_speech!=null)
        {
            text_to_speech.stop();
            text_to_speech.shutdown();
        }
        super.onDestroy();
    }
}
