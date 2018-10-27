package com.example.anhki.tradingbook.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductModel {
    String IdProduct, NameProduct, State, DetailPic, GeneralPic, Decription;
    long price;

    DatabaseReference dataProduct;

    public ProductModel(){
        Log.d("theFirst",  "Successful");
        dataProduct = FirebaseDatabase.getInstance().getReference().child("products");
        Log.d("theSecond",  "Successful");
    }

    public String getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(String idProduct) {
        IdProduct = idProduct;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getDetailPic() {
        return DetailPic;
    }

    public void setDetailPic(String detailPic) {
        DetailPic = detailPic;
    }

    public String getGeneralPic() {
        return GeneralPic;
    }

    public void setGeneralPic(String generalPic) {
        GeneralPic = generalPic;
    }

    public String getDecription() {
        return Decription;
    }

    public void setDecription(String decription) {
        Decription = decription;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void getDanhSachSanPham(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("kiemtra", dataSnapshot + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dataProduct.addListenerForSingleValueEvent(valueEventListener);
    }
}
