package com.example.final_test.login;

import android.widget.Toast;

import com.example.final_test.dataBaseVisit.UserInfoVisit;
import com.example.final_test.entity.UserInfo;

class function {
    //判断用户名是否合法，用户名长度为11
//    static boolean isLegalUsername(long username){
//        return username.length() == 11;
//    }

    //判断密码是否合法.密码要求：1.长度为6-12位2.包含字母和数字
    static boolean isLegalPassword(String password){
        int count = 0;//用于计算是否全部为数字
        if (password.length()>=6 || password.length()<=12){
            for (int i = 0; i < password.length(); i++) {
                //将string类型转换位char
                StringBuffer stringBuffer = new StringBuffer(password);
                stringBuffer.toString();
                char c = stringBuffer.charAt(i);//参数表示从stringBuffer下标从0开始第i个字符
                if (c>='0' && c<='9' || c>='a' && c<='z' || c>='A' && c<='Z'){
                    if (c >= '0' && c <= '9'){
                        count++;
                    }else count--;
                }
            }
            return count < password.length();
        }else return false;
    }

    //创建判断当前输入的密码是否与数据库中的密码一致
    static boolean isMatchPassword(String userName,String password){
        //1.按id查询
        UserInfo users = UserInfoVisit.queryUserByID(userName);
        String info = "";
        if (isExistUser(userName)){//判断所查询的id值是否存在
        //若存在则获取password，并与输入的password比较
            info = "" + users.getPassword();
            return info.equals(password);
        }else return false;
    }

    //向数据库中查找用户名是否存在
    static boolean isExistUser(String userName){
        UserInfo users = UserInfoVisit.queryUserByID(userName);
        String info = "";
        info = info + users;
        return !info.equals("null");
    }
}
