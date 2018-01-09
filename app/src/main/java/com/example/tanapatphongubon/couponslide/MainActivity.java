package com.example.tanapatphongubon.couponslide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.tanapatphongubon.couponslide.dialoggrid.CouponGridDialogFragment;
import com.example.tanapatphongubon.couponslide.dialogvp.CouponViewpagerDialogFragment;
import com.example.tanapatphongubon.couponslide.model.CouponModel;

public class MainActivity extends AppCompatActivity implements CouponGridDialogFragment.OnCouponGridDialogListener {
    private static String TAG_DIALOG_VP = "dialog_vp";
    private static String TAG_DIALOG_GRID = "dialog_grid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton btnShowVp = findViewById(R.id.btn_show_vp_dialog);
        btnShowVp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShowDialogViewPagerButtonClick(0);
            }
        });

        AppCompatButton btnShowGrid = findViewById(R.id.btn_show_grid_dialog);
        btnShowGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShowDialogGridButtonClick();
            }
        });
    }

    private void onShowDialogViewPagerButtonClick(int startPosition) {
        CouponViewpagerDialogFragment fragment = new CouponViewpagerDialogFragment.Builder()
                .setStartPosotion(startPosition)
                .build();
        fragment.show(getSupportFragmentManager(), TAG_DIALOG_VP);
    }

    private void onShowDialogGridButtonClick() {
        CouponGridDialogFragment fragment = new CouponGridDialogFragment.Builder()
                .build();
        fragment.show(getSupportFragmentManager(), TAG_DIALOG_GRID);
    }
    // Grid Dialog
    @Override
    public void onCouponItemClick(CouponModel couponModel,int position) {
        onShowDialogViewPagerButtonClick(position);
    }
}
