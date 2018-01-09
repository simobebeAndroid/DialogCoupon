package com.example.tanapatphongubon.couponslide.dialoggrid;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tanapatphongubon.couponslide.R;
import com.example.tanapatphongubon.couponslide.model.CouponModel;

import java.util.ArrayList;
import java.util.List;



public class CouponGridDialogFragment extends DialogFragment {

    private RecyclerView rvCoupon;
    private List<CouponModel> couponList;
    private CouponGridAdapter mCouponAdapter;


    public static CouponGridDialogFragment newInstance() {
        return new CouponGridDialogFragment();
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
        return inflater.inflate(R.layout.dialog_coupon_grid_fragment, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // the content

        final ConstraintLayout root = new ConstraintLayout(getActivity());
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupView();
        prepareCoupon(9);
        setupListener();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimationScale;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt(KEY_MESSAGE, message);
//        outState.putInt(KEY_POSITIVE, positive);
//        outState.putInt(KEY_NEGATIVE, negative);
    }

    private OnCouponGridDialogListener getOnCouponGridDialogListener() {
        Fragment fragment = getParentFragment();
        try {
            if (fragment != null) {
                return (OnCouponGridDialogListener) fragment;
            } else {
                return (OnCouponGridDialogListener) getActivity();
            }
        } catch (ClassCastException ignored) {
        }
        return null;
    }

    private void bindView(View view) {
        rvCoupon = view.findViewById(R.id.rv_coupon);
//        tvMessage = (TextView) view.findViewById(R.id.tv_message);
//        btnPositive = (Button) view.findViewById(R.id.btn_positive);
//        btnNegative = (Button) view.findViewById(R.id.btn_negative);
    }

    private void setupView() {
        couponList = new ArrayList<>();
         RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
      //  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvCoupon.setLayoutManager(mLayoutManager);
        mCouponAdapter = new CouponGridAdapter(getContext(), couponList);

        rvCoupon.setAdapter(mCouponAdapter);

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

    private void setupListener() {
        mCouponAdapter.setmListener(new CouponGridAdapter.CouponGridAdapterListener() {
            @Override
            public void onCouponClickListener(CouponModel coupon, int position) {
                Toast.makeText(getContext(),""+coupon.getCouponCode(),Toast.LENGTH_SHORT).show();
                OnCouponGridDialogListener listener = getOnCouponGridDialogListener();
                if (listener != null && coupon != null) {
                    listener.onCouponItemClick(coupon,position);
                }
            }
        });

    }

    private void prepareCoupon(int count) {
        for (int i = 0 ; i < count ; i++){
            couponList.add(new CouponModel(i,"http://www.projamm.com/images/ckfinder/userfiles/files/711-2.jpg",String.valueOf(i)));
        }
        mCouponAdapter.notifyDataSetChanged();
    }

    private void restoreInstanceState(Bundle bundle) {
//        message = bundle.getInt(KEY_MESSAGE);
//        positive = bundle.getInt(KEY_POSITIVE);
//        negative = bundle.getInt(KEY_NEGATIVE);
    }

    private void restoreArguments(Bundle bundle) {
//        message = bundle.getInt(KEY_MESSAGE);
//        positive = bundle.getInt(KEY_POSITIVE);
//        negative = bundle.getInt(KEY_NEGATIVE);
    }

    public interface OnCouponGridDialogListener {
        void onCouponItemClick(CouponModel couponModel, int position);
    }


    public static class Builder {
        private int message;
        private int positive;
        private int negative;

        public Builder() {
        }

//        public Builder setMessage(@StringRes int message) {
//            this.message = message;
//            return this;
//        }
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

        public CouponGridDialogFragment build() {
            return CouponGridDialogFragment.newInstance();
        }
    }
}
