package com.full.gallery.top.secure.password.photos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.billingclient.BuildConfig;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.SkuDetails;
import com.fulldive.startapppopups.PopupManager;
import com.fulldive.startapppopups.donation.DonationAction;
import com.fulldive.startapppopups.donation.DonationManager;
import com.fulldive.startapppopups.donation.DonationSnackbar;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

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