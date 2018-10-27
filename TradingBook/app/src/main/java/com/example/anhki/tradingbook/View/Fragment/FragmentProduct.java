package com.example.anhki.tradingbook.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.R;

public class FragmentProduct extends Fragment {

    public static FragmentProduct newInstance() {
        return new FragmentProduct();
    }
    ProductModel productModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_product, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        productModel = new ProductModel();
        productModel.getDanhSachSanPham();
    }
}