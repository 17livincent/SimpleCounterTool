package com.example.simplecountertool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AdMob initialize
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        // Create PitchCounter class
        PitchCounter();
    }

    public void incrementCount(View view) {
        // call the PitchCounter.add() function
        add();
        TextView count = findViewById(R.id.textView2);
        count.setText(getCount());
    }

    public void undoCount(View view) {
        // call the PitchCounter.undo() function
        subtract();
        TextView count = findViewById(R.id.textView2);
        count.setText(getCount());
    }

    public void clearCount(View view) {
        // call the PitchCounter.clear() function
        clear();
        TextView count = findViewById(R.id.textView2);
        count.setText(getCount());
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void PitchCounter();
    public native void add();
    public native void subtract();
    public native void clear();
    public native String getCount();
}
