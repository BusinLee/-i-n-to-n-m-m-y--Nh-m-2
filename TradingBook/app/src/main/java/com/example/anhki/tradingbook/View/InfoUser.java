package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Adapter.ViewPagerAdapterInfoUser;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.Fragment.User.Account;

public class InfoUser extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtName, txtAddress;
    ImageButton btnBack;
    Button btnReport;
    RelativeLayout relativeLayout;
    ImageView imgUser;
    Bitmap bitmap = null;
    String path;
    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_user);

        tabLayout = (TabLayout) findViewById(R.id.tablayoutuser);
        viewPager = (ViewPager) findViewById(R.id.viewpageruser);
        txtName = (TextView) findViewById(R.id.txtUserName);
        txtAddress = (TextView) findViewById(R.id.txtUserAddress);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnReport = (Button) findViewById(R.id.btnReport);
        relativeLayout = (RelativeLayout) findViewById((R.id.relativelayout));
        imgUser = (ImageView) findViewById(R.id.imgUser);

        ViewPagerAdapterInfoUser adapter = new ViewPagerAdapterInfoUser(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent= getIntent();
        txtName.setText(intent.getStringExtra("name"));
        txtAddress.setText(intent.getStringExtra("address"));
        path = intent.getStringExtra("image");
        bitmap = BitmapFactory.decodeFile(path);
        imgUser.setImageBitmap(bitmap);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View customView = inflater.inflate(R.layout.popup_report,null);
                mPopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                ImageButton btnClose = (ImageButton) customView.findViewById(R.id.btnClose);
                EditText txtReason = (EditText) customView.findViewById(R.id.txtReason);
                mPopupWindow.setFocusable(true);
                mPopupWindow.update();
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(relativeLayout, Gravity.CENTER,0,0);
            }
        });

    }
}
