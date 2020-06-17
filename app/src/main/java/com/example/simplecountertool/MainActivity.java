package com.example.simplecountertool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupAds();

        // set up cancel alert
        Button clearButton = findViewById(R.id.button3);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                builder.setTitle(R.string.clear)
                        .setMessage(R.string.cancel_prompt)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // affirm
                                clearCount(view);
                                //finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Create PitchCounter class
        PitchCounter();

    }

    public void setupAds() {
        // use rdp for ccpa
        Bundle networkExtrasBundle = new Bundle();
        networkExtrasBundle.putInt("rdp", 1);
        AdRequest request1 = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, networkExtrasBundle)
                .build();
        // AdMob initialize
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        List<String> testDeviceIds = Arrays.asList("9E86A33DEE58FABB4244129F816B9102");
        RequestConfiguration config = new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        // define banner
        AdView bannerAd = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAd.loadAd(adRequest);
    }

    public void incrementCount(View view) {
        // call the PitchCounter.add() function
        add();
        TextView tv = findViewById(R.id.textView);
        tv.setText(getCount());
    }

    public void undoCount(View view) {
        // call the PitchCounter.undo() function
        subtract();
        TextView count = findViewById(R.id.textView);
        count.setText(getCount());
    }

    public void clearCount(View view) {
        // call the PitchCounter.clear() function
        clear();
        TextView count = findViewById(R.id.textView);
        count.setText(getCount());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    public native void PitchCounter();
    public native void add();
    public native void subtract();
    public native void clear();
    public native String getCount();

}
