package com.zjm.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Context myContext;
    private EditText edUserName;
    private EditText edPassWord;
    private CheckBox cbSavePassWord;
    private Button btnLogin;

    private String userName;
    private String passWord;
    private boolean isrember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContext = this;

        //初始化控件
        initview();
        //为控件设置监听器
        setClick();
        //回显用户信息
        setUser();
    }

    /**
     * 回显用户信息
     */
    private void setUser() {
        HashMap<String ,String> map = UserInfoUtil.getUserInfo(myContext);
        if (map!=null){
            edUserName.setText(map.get("username"));
            edPassWord.setText(map.get("password"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在登录界面退出之前保存用户名和密码
        UserInfoUtil.saveUserInfo(userName, passWord,myContext);
    }

    /**
     * 为控件设置监听方法
     */
    private void setClick() {
        btnLogin.setOnClickListener(this);

    }

    /**
     * 初始化控件
     */
    private void initview() {
        edUserName = (EditText) findViewById(R.id.ed_username);
        edPassWord = (EditText) findViewById(R.id.ed_password);
        cbSavePassWord = (CheckBox) findViewById(R.id.cb_save_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();

        }
    }

    /**
     * 登录
     */
    private void login() {
        //获取输入的用户名和密码
        userName = edUserName.getText().toString().trim();
        passWord = edPassWord.getText().toString();
        //判断是否需要记住密码
        isrember = cbSavePassWord.isChecked();
        //判断输入的密码和用户名是否为空，如果不为空执行登录业务
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord)){
            Toast.makeText(myContext,"用户名和密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        //判断是否需要记住密码，如果需要就将用户名和密码保存到本地
        if(isrember) {
           boolean resout = UserInfoUtil.saveUserInfo(userName, passWord,myContext);
            if (resout){
                Toast.makeText(myContext,"用户名和密码保存成功",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
