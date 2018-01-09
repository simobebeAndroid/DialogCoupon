package com.example.tanapatphongubon.couponslide.dialogvp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanapatphongubon.couponslide.R;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class CouponViewpagerDialogFragment extends DialogFragment {

    private static final String KEY_START_POSITION = "key_start_position";

    private ViewPager vpCoupon;
    private TextView tvNumberOfCoupon;
    private ImageView ivPreviousCoupon;
    private ImageView ivNextCoupon;
    private List<Fragment> fragmentList;
    private CouponPagerAdapter couponPagerAdapter;


    private int startPosition;

    public static CouponViewpagerDialogFragment newInstance(int startPosition) {
        CouponViewpagerDialogFragment fragment = new CouponViewpagerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_START_POSITION, startPosition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            restoreArguments(getArguments());
        } else {
            restoreInstanceState(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_coupon_viewpager_fragment, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimationScale;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupView();
        setupItemFragment(9);
        setupListener();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_START_POSITION, startPosition);
//        outState.putInt(KEY_POSITIVE, positive);
//        outState.putInt(KEY_NEGATIVE, negative);
    }

    private void restoreInstanceState(Bundle bundle) {
        startPosition = bundle.getInt(KEY_START_POSITION);
//        positive = bundle.getInt(KEY_POSITIVE);
//        negative = bundle.getInt(KEY_NEGATIVE);
    }

    private void restoreArguments(Bundle bundle) {
        startPosition = bundle.getInt(KEY_START_POSITION);
//        positive = bundle.getInt(KEY_POSITIVE);
//        negative = bundle.getInt(KEY_NEGATIVE);
    }

    private void bindView(View view) {
        vpCoupon = view.findViewById(R.id.vp_coupon);
        tvNumberOfCoupon = view.findViewById(R.id.tv_number_of_coupon);
        ivPreviousCoupon = view.findViewById(R.id.iv_previous);
        ivNextCoupon = view.findViewById(R.id.iv_next);
//        tvMessage = (TextView) view.findViewById(R.id.tv_message);
//        btnPositive = (Button) view.findViewById(R.id.btn_positive);
//        btnNegative = (Button) view.findViewById(R.id.btn_negative);
    }

    private void setupView() {
        fragmentList = new ArrayList<>();
        couponPagerAdapter = new CouponPagerAdapter(getChildFragmentManager(), fragmentList);
        vpCoupon.setAdapter(couponPagerAdapter);
//        tvMessage.setText(message);
//        btnPositive.setText(positive);
//        btnNegative.setText(negative);
//
//        btnPositive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OnDialogListener listener = getOnDialogListener();
//                if (listener != null) {
//                    listener.onPositiveButtonClick();
//                }
//                dismiss();
//            }
//        });
//
//        btnNegative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OnDialogListener listener = getOnDialogListener();
//                if (listener != null) {
//                    listener.onNegativeButtonClick();
//                }
//                dismiss();
//            }
//        });
    }

    public void setupItemFragment(int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            fragmentList.add(CouponFragment.newInstance(String.valueOf(i), String.valueOf(itemCount)));
        }
        couponPagerAdapter.notifyDataSetChanged();
        if (couponPagerAdapter.getCount() > startPosition) vpCoupon.setCurrentItem(startPosition);
        tvNumberOfCoupon.setText(couponPagerAdapter.getNumberOfCoupon(vpCoupon.getCurrentItem()));
    }

    private void setupListener() {
        ivPreviousCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousCoupon();
            }


        });

        ivNextCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCoupon();
            }
        });
        vpCoupon.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Timber.i("position : %s , positionOffset : %s , positionOffsetPixels : %s", position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                //      Timber.i("position : %s", position);
                tvNumberOfCoupon.setText(couponPagerAdapter.getNumberOfCoupon(vpCoupon.getCurrentItem()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        vpCoupon.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                int pageWidth = vpCoupon.getMeasuredWidth() - vpCoupon.getPaddingLeft() - vpCoupon.getPaddingRight();
//                float transformPos = (float)(page.getLeft() - (vpCoupon.getScrollX() + vpCoupon.getPaddingLeft())) / pageWidth;
//                float normalizedPosition = Math.abs(Math.abs(transformPos) - 1);
//                float alpha = normalizedPosition + 0.5f;
//                page.setAlpha(alpha);
//                //setAnimationAlpha(alpha);
//                float scaleFactorT = Math.max(0.2f, 1 - Math.abs(transformPos));
//
//            //    Timber.i("OnTransformPage position : %s , transformPos : %s , scaleFactorT : %s , normalizedPosition : %s", position, transformPos, scaleFactorT,normalizedPosition);
//                if (transformPos == 0f){
//                    // Normal
//                    page.setScaleX(1f);
//                    page.setScaleY(1f);
//              //      setAnimationScaleFactor(1f);
//                }else{
//                    // Swipe
//                    page.setScaleX(scaleFactorT);
//                    page.setScaleY(scaleFactorT);
//             //       setAnimationScaleFactor(scaleFactorT);
//                }
//            }
//        });

    }

    private void setAnimationScaleFactor(float scaleFactor) {
        tvNumberOfCoupon.setScaleX(scaleFactor);
        tvNumberOfCoupon.setScaleY(scaleFactor);

        ivNextCoupon.setScaleX(scaleFactor);
        ivNextCoupon.setScaleY(scaleFactor);

        ivPreviousCoupon.setScaleX(scaleFactor);
        ivPreviousCoupon.setScaleY(scaleFactor);
    }

    private void setAnimationAlpha(float alpha) {
        tvNumberOfCoupon.setAlpha(alpha);

        ivNextCoupon.setAlpha(alpha);

        ivPreviousCoupon.setAlpha(alpha);
    }

    private void previousCoupon() {
        if (couponPagerAdapter.canPrevious(vpCoupon.getCurrentItem())) {
            vpCoupon.setCurrentItem(vpCoupon.getCurrentItem() - 1, true);
        }

    }

    private void nextCoupon() {
        if (couponPagerAdapter.canNext(vpCoupon.getCurrentItem())) {
            vpCoupon.setCurrentItem(vpCoupon.getCurrentItem() + 1, true);
        }
    }


    public static class Builder {

        private int startPosition;

        public Builder() {
        }

        public Builder setStartPosotion(@StringRes int startPosition) {
            this.startPosition = startPosition;
            return this;
        }
//
//        public Builder setPosition(@StringRes int positive) {
//            this.positive = positive;
//            return this;
//        }
//
//        public Builder setNegative(@StringRes int negative) {
//            this.negative = negative;
//            return this;
//        }

        public CouponViewpagerDialogFragment build() {
            return CouponViewpagerDialogFragment.newInstance(startPosition);
        }
    }
}
