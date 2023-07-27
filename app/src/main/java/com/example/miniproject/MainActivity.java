package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyStore;
import java.util.Locale;

public class    MainActivity extends AppCompatActivity {
    static TextToSpeech mTTS;
    public static int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    mTTS.setLanguage(Locale.UK);
                }
            }
        });


        EditText editText,editText2;
        Button button = (Button) findViewById(R.id.button);
        editText=(EditText) findViewById(R.id.editText);
        editText2=(EditText) findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=editText.getText().toString();
                String sms=editText2.getText().toString();

                try {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,sms,null,null);
                    Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String number = editText.getText().toString();
                String sms = editText2.getText().toString();

                // Validate phone number
                if (!isValidPhoneNumber(number)) {
                    Toast.makeText(MainActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add text limit (e.g., 100 characters)
                int maxCharacters = 20;
                if (sms.length() > maxCharacters) {
                    Toast.makeText(MainActivity.this, "Message exceeds the character limit", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, sms, null, null);
                    Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                    speak("Message sent");
                    // Display the number of characters in the message
                    int characterCount = sms.length();
                    Toast.makeText(MainActivity.this, "Message sent! Character count: " + characterCount, Toast.LENGTH_SHORT).show();



                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    speak("Failed");

                }
            }
        });


    }
    private boolean isValidPhoneNumber(String number) {
        // Validate phone number format
        String regex = "\\d{10}"; // 10 digits only
        return number.matches(regex);

    }
public static void speak( String text){

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH,null);
}

    }



