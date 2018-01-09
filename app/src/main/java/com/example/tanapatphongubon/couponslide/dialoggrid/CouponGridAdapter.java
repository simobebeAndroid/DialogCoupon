package com.example.tanapatphongubon.couponslide.dialoggrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tanapatphongubon.couponslide.R;
import com.example.tanapatphongubon.couponslide.model.CouponModel;

import java.util.List;


public class CouponGridAdapter extends RecyclerView.Adapter<CouponGridAdapter.CouponViewHolder> {


    private Context mContext;
    private List<CouponModel> couponList;
    private CouponGridAdapterListener mListener;


    public CouponGridAdapter(Context mContext, List<CouponModel> couponList) {
        this.mContext = mContext;
        this.couponList = couponList;
    }

    public void setmListener(CouponGridAdapterListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public CouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_coupon, parent, false);

        return new CouponViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CouponViewHolder holder, final int position) {
        final CouponModel couponModel = couponList.get(position);
        Glide.with(mContext)
                .load(couponModel.getCouponImage())
                .into(holder.ivCoupon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) mListener.onCouponClickListener(couponModel,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }


    public interface CouponGridAdapterListener {
        public void onCouponClickListener(CouponModel coupon , int position);
    }


    public class CouponViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCoupon;

        public CouponViewHolder(View view) {
            super(view);
            ivCoupon = view.findViewById(R.id.iv_coupon);
        }
    }
}
