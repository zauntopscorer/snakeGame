package com.example.final_test.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.final_test.R;
import com.example.final_test.dataBaseVisit.UserInfoVisit;
import com.example.final_test.entity.UserInfo;
import com.example.final_test.game.MainActivity;

import java.util.ArrayList;

public class LoginActivity extends Activity {
    private EditText          loginUsername     = null;
    private EditText          loginPassword     = null;
    private EditText          registerUsername  = null;
    private EditText          registerPassword  = null;
    private EditText          confirmPassword   = null;
    private Button            loginBtn          = null;
    private Button            turnToRe          = null;
    private Button            registerBtn       = null;
    private Button            turnToLo          = null;
    private Button            rankBtn           = null;
    private RelativeLayout    login             = null;
    private RelativeLayout    register          = null;

//    private EditText loginShowInfoEdit    = null;
//    private EditText registerShowInfoEdit = null;
//    private Button   loginQueryBtn        = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (UserInfoVisit.db != null){
            UserInfoVisit.db.close();
        }
    }

    //    @Override
//    protected void onRestart() {
//        super.onRestart();
//
//    }

    private void init(){
        //login界面控件
        loginUsername    = findViewById(R.id.loginUsername);
        loginPassword    = findViewById(R.id.loginPassword);
        loginBtn         = findViewById(R.id.loginBtn);
        turnToRe         = findViewById(R.id.turnToRegister);
        rankBtn          = findViewById(R.id.rankList);
        login            = findViewById(R.id.login_interface);
//        loginQueryBtn    = findViewById(R.id.queryButton);
        //register界面控件
        registerUsername = findViewById(R.id.registerUsername);
        registerPassword = findViewById(R.id.registerPassword);
        confirmPassword  = findViewById(R.id.confirmPassword);
        registerBtn      = findViewById(R.id.registerBtn);
        turnToLo         = findViewById(R.id.turnToLogin);
        register         = findViewById(R.id.register_interface);

        UserInfoVisit.context = this;//第一次调用StudentInfoVisit类时，静态代码块就会执行
        UserInfoVisit.initDBTable();//初始化数据库，主键为userName

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = loginPassword.getText().toString();
                String userName = loginUsername.getText().toString();
                if (function.isMatchPassword(userName,password)){
                    Toast.makeText(LoginActivity.this,"password:"+function.isMatchPassword(userName,password),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else Toast.makeText(LoginActivity.this,"password-error",Toast.LENGTH_SHORT).show();
            }
        });
        turnToRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.GONE);
                register.setVisibility(View.VISIBLE);
            }
        });

//        loginQueryBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int userName_length   = loginUsername.length();
//                String username       = loginUsername.getText().toString();
//                String info = "";
//                if (userName_length>0){
//                    info = "";
//                    UserInfo users = UserInfoVisit.queryUserByID(username);
//                    info = info+users;
////                    loginShowInfoEdit.setText(info);
//                    Toast.makeText(LoginActivity.this,info,Toast.LENGTH_SHORT).show();
//                }else {
//                    ArrayList<UserInfo> users = UserInfoVisit.queryAllStudents();
//                    info = "";
//                    for (int i = 0; i < users.size(); i++) {
//                        info = info+users.get(i)+"\n";
//                    }
//                }
//                Toast.makeText(LoginActivity.this,info,Toast.LENGTH_SHORT).show();
////                loginShowInfoEdit.setText(info);
//            }
//        });



        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName        = registerUsername.getText().toString();
                String password        = registerPassword.getText().toString();
                String confirmPassword = registerPassword.getText().toString();
                String scorer          = "0";
                if (function.isLegalPassword(password)){
                    if (!function.isExistUser(userName)){
                        if (confirmPassword.equals(password)){
                            long l = UserInfoVisit.insertUserInfo(userName,password,scorer);
                            if(l < 0)Toast.makeText(LoginActivity.this,userName+"-"+password+"-"+scorer+":insert failed",Toast.LENGTH_SHORT).show();
                            else Toast.makeText(LoginActivity.this,"insert success",Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(LoginActivity.this,"confirmPassword:"+confirmPassword.equals(password),Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(LoginActivity.this,"PasswordNotExist",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(LoginActivity.this,"password:"+function.isLegalPassword(password),Toast.LENGTH_SHORT).show();
            }
        });

        turnToLo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.VISIBLE);
                register.setVisibility(View.GONE);
            }
        });
    }
}
