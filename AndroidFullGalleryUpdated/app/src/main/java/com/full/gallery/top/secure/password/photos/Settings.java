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

    private LinearLayout donateBtn,suportBtn,rateBtn;
    private static final String TAG =
            "com.full.gallery.top.secure.password.photos";
    private BillingClient billingClient;
    private ProductDetails productDetails;
    private Purchase purchase;
    private SkuDetails skuDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        donateBtn = (LinearLayout) findViewById(R.id.donate);
        suportBtn = (LinearLayout) findViewById(R.id.support);
        rateBtn = (LinearLayout) findViewById(R.id.rate);

        suportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PopupManager().onAppStarted(
                        Settings.this,
                        BuildConfig.APPLICATION_ID,
                        true,
                        false,
                        true,
                        0,
                        (action) -> {
                            DonationManager.INSTANCE.purchaseFromSettings(Settings.this,
                                    () -> {
                                        return null;
                                    },
                                    () -> {
                                        new PopupManager().showDonationSuccess(Settings.this);
                                        return null;
                                    }
                            );
                            return null;
                        }
                );
            }
        });

        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PopupManager().onAppStarted(
                        Settings.this,
                        BuildConfig.APPLICATION_ID,
                        true,
                        true,
                        false,
                        0,
                        (action) -> {

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