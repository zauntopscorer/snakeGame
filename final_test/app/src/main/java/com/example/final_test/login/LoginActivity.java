package com.example.final_test.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

import com.example.final_test.CommonData;
import com.example.final_test.R;
import com.example.final_test.dataBaseVisit.UserInfoVisit;
import com.example.final_test.entity.UserInfo;
import com.example.final_test.game.GameActivity;

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
    private Button            backToLo          = null;
    private ListView          listView          = null;
    private RelativeLayout    login             = null;
    private RelativeLayout    register          = null;
    private RelativeLayout    rankInterface     = null;
    private ArrayList<String> listViewDataList  = new ArrayList<String>();
    private MyListViewAdapter myListViewAdapter = null;

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
        CommonData.loginActivity = this;
        listView         = findViewById(R.id.listView);
        myListViewAdapter= new MyListViewAdapter(this);
        listView.setAdapter(myListViewAdapter);
        //login界面控件
        loginUsername    = findViewById(R.id.loginUsername);
        loginPassword    = findViewById(R.id.loginPassword);
        loginBtn         = findViewById(R.id.loginBtn);
        turnToRe         = findViewById(R.id.turnToRegister);
        rankBtn          = findViewById(R.id.rankList);
        login            = findViewById(R.id.login_interface);
        rankInterface    = findViewById(R.id.rankInterface);
        backToLo         = findViewById(R.id.backToLo);
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
                CommonData.presentUsername = userName;
                CommonData.presentPassword = password;
                if (function.isMatchPassword(userName,password)){
                    Toast.makeText(LoginActivity.this,"password:"+function.isMatchPassword(userName,password),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, GameActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else Toast.makeText(LoginActivity.this,"password-error",Toast.LENGTH_SHORT).show();
            }
        });
        turnToRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.GONE);
                rankInterface.setVisibility(View.GONE);
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
//                    ArrayList<UserInfo> users = UserInfoVisit.queryAllUsers();
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
                rankInterface.setVisibility(View.GONE);
            }
        });

        //打开排行榜界面
        rankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.GONE);
                register.setVisibility(View.GONE);
                rankInterface.setVisibility(View.VISIBLE);
                //向listView中添加数据
                ArrayList<UserInfo> users = UserInfoVisit.queryAllUsersOrderByScore();
                    for (int i = 0; i < users.size(); i++) {
                        String info = "";
                        info = "用户名："+users.get(i).getUserName()+" "+"得分："+users.get(i).getScorer();
                        listViewDataList.add(info);
                    }
//                UserInfoVisit.modifyUserInfo(CommonData.presentUsername,CommonData.presentPassword,CommonData.scorer+"");
            }
        });

        //返回登录界面
        backToLo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.VISIBLE);
                register.setVisibility(View.GONE);
                rankInterface.setVisibility(View.GONE);
                listViewDataList.clear();
            }
        });
    }

    //为listView创建适配器
    class MyListViewAdapter extends BaseAdapter{
        private LayoutInflater inflater = null;
        public MyListViewAdapter (Context context){
            inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return listViewDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflater.inflate(R.layout.listview_item,null);
                TextView usernameText = convertView.findViewById(R.id.listView_username);
                usernameText.setText(listViewDataList.get(position));
            }else {
                TextView usernameText = convertView.findViewById(R.id.listView_username);
                usernameText.setText(listViewDataList.get(position));
            }
            return convertView;
        }
    }
}
