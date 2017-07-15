package com.zjm.login;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * Created by zjm on 2017/7/15.
 */

class UserInfoUtil {
    /**
     * 将用户名和密码保存到本地
     * @param userName ：用户名
     * @param passWord ：密码
     * @param myContext
     * @return
     */

    public static boolean saveUserInfo(String userName, String passWord, Context myContext) {

        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            String inputtext = userName+"##"+passWord;
            out = myContext.openFileOutput("data",Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputtext);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if (writer!=null){
                try {
                    writer.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 回显用户名和密码
     * @return
     * @param myContext
     */

    public static HashMap<String,String> getUserInfo(Context myContext) {
        FileInputStream in = null;
        BufferedReader reader = null;
        HashMap<String,String> hashMap = null;
        try {
            in = myContext.openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String readerLine = reader.readLine();
            String[] split = readerLine.split("##");
            hashMap = new HashMap<>();
            hashMap.put("username",split[0]);
            hashMap.put("password",split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return hashMap;


    }
}
