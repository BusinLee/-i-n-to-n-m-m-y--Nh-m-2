package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {

    Button btnLoginGoogle, btnLoginFacebook, btnLogin;
    TextView tvRegister, tvForgotPassword;
    EditText edEmailLogin, edPasswordLogin;
    GoogleApiClient apiClient;
    CallbackManager mCallabackFacebook;
    public static int REQUEST_CODE_LOGIN_GOOGLE = 3;
    public static int CHECK_AUTH_PROVIDER_LOGIN = 0;
    FirebaseAuth firebaseAuth;
    LoginManager loginManager;
    List<String> permissionFacebook = Arrays.asList("email", "public_profile");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layout_login);

        mCallabackFacebook = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLoginGoogle = (Button) findViewById(R.id.btnLoginGoogle);
        btnLoginFacebook = (Button) findViewById(R.id.btnLoginFacebook);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgotPassword = (TextView) findViewById(R.id.tvFogetPass);
        edEmailLogin = (EditText) findViewById(R.id.edEmail);
        edPasswordLogin = (EditText) findViewById(R.id.edPassword);

        btnLogin.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);
        btnLoginFacebook.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        CreateClientLoginGoogle();
    }

    private void CreateClientLoginGoogle(){
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    private void LoginFacebook(){
        loginManager.logInWithReadPermissions(this, permissionFacebook);
        loginManager.registerCallback(mCallabackFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                CHECK_AUTH_PROVIDER_LOGIN = 2;
                String tokenID = loginResult.getAccessToken().getToken();
                AuthLoginFirebase(tokenID);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    private void LoginGoogle(GoogleApiClient apiClient){
        CHECK_AUTH_PROVIDER_LOGIN = 1;
        Intent iLoginGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(iLoginGoogle, REQUEST_CODE_LOGIN_GOOGLE);
    }

    private void AuthLoginFirebase(String tolenID){
        if (CHECK_AUTH_PROVIDER_LOGIN == 1){
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tolenID, null);
            firebaseAuth.signInWithCredential(authCredential);
        }else if (CHECK_AUTH_PROVIDER_LOGIN == 2){
            AuthCredential authCredential = FacebookAuthProvider.getCredential(tolenID);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LOGIN_GOOGLE){
            if (resultCode == RESULT_OK){
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();
                String tokenID = account.getIdToken();
                AuthLoginFirebase(tokenID);
            }
        }else {
            mCallabackFacebook.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnLoginGoogle:
                LoginGoogle(apiClient);
                break;
            case R.id.btnLoginFacebook:
                LoginFacebook();
                break;
            case R.id.tvRegister:
                Intent iRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(iRegister);
                break;
            case R.id.btnLogin:
                Login();
                break;
            case R.id.tvFogetPass:
                Intent iRecoverPass = new Intent(LoginActivity.this, RecoveryPasswordActivity.class);
                startActivity(iRecoverPass);
                break;
        }
    }

    private void Login(){
        String email = edEmailLogin.getText().toString();
        String password=edPasswordLogin.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,getString(R.string.taikhoankhongtontai), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent iHome = new Intent(this, HomeActivity.class);
            startActivity(iHome);
        }else {
            //Toast.makeText(this,"Login Fail!", Toast.LENGTH_SHORT).show();
        }
    }
}
