package com.full.gallery.top.secure.password.photos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.fulldive.startapppopups.donation.DonationManager;
import com.fulldive.startapppopups.PopupManager;

public class Settings extends AppCompatActivity {

    private LinearLayout supportBtn;
    private static final String TAG =
            "com.full.gallery.top.secure.password.photos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        supportBtn = (LinearLayout) findViewById(R.id.support);

        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DonationManager.INSTANCE.purchaseFromSettings(Settings.this,
                        () -> {
                            return null;
                        },
                        () -> {
                            new PopupManager().showDonationSuccess(Settings.this);
                            return null;
                        }
                );
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DonationManager.INSTANCE.destroy();
    }
}
