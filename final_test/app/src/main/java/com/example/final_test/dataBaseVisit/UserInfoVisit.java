package com.example.final_test.dataBaseVisit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.final_test.entity.UserInfo;

import java.util.ArrayList;

public class UserInfoVisit extends BaseVisit {
    //1.将该数据所有字段、类型定义位成员变量
    public static final String tableName = "userInfo";
    public static final String ID        = "userName";
    public static final String PASSWORD  = "userPassword";
    public static final String SCORER    = "userScorer";
    public static SQLiteDatabase db      = null;
    public static Context context        = null;

    //2.定义创建表的成员方法
    public static void initDBTable(){
        if(db == null){
            db = context.openOrCreateDatabase(DBName,Context.MODE_PRIVATE,null);
            String sql = "create table if not exists "+tableName+" ("+ID+" text primary key "+
                    ","+PASSWORD+" text not null"+", "+SCORER+" text not null);";
            db.execSQL(sql);
            Toast.makeText(context,DBName+"创建成功",Toast.LENGTH_SHORT).show();
        }
    }

    //3.数据表相关操作的成员方法
    //插入用户信息
    public static long insertUserInfo(String userName,String password,String scorer){
        ContentValues cv = new ContentValues();
        cv.put(ID,userName);
        cv.put(PASSWORD,password);
        cv.put(SCORER,scorer);
        //第一个参数是表名，第二个参数是当某一列没给数据时的默认数据
        //返回值表示rawID，不是主键，一般为插入过的总记录条数值
        long l = db.insert(tableName,"#NOVALUE",cv);
        return l;
    }

    //修改用户信息
    public static int modifyUserInfo(String userName,String password,String scorer) {
        if(db != null){
            ContentValues cv = new ContentValues();
            cv.put(ID,userName);
            cv.put(PASSWORD,password);
            cv.put(SCORER,scorer);
            //第一个参数是表名，第二个参数是当ContentValues中某一列没给数据时的默认数据
            //返回值表示rawID，不是主键，一般为插入过的总记录条数值（包括已删除的）
            int l = db.update(tableName,cv,ID+"=?",new String[]{userName});
            return l;
        }else{
            return -1;
        }
    }

    //查询用户信息
    public static UserInfo queryUserByID(String userName){
        ArrayList<UserInfo> users = new ArrayList<UserInfo>();
        Cursor cursor = db.rawQuery("select * from "+tableName+" where "+ID+" = ?",new String[]{userName+""});
        cursor.moveToFirst();
        UserInfo userInfo = null;
        while (!cursor.isAfterLast()){//查询指针移动到最后一行
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            String scorer = cursor.getString(cursor.getColumnIndex(SCORER));
            userInfo = new UserInfo();
            userInfo.setUserName(userName);
            userInfo.setPassword(password);
            userInfo.setScorer(scorer);
            users.add(userInfo);
            cursor.moveToNext();//需要将查询指针下移一行
        }
        return userInfo;
    }

    //查询所有用户
    public static ArrayList<UserInfo> queryAllUsers(){
        ArrayList<UserInfo> users = new ArrayList<UserInfo>();
        if (db != null){
            Cursor cursor = db.rawQuery("select * from "+tableName,new String[]{});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){//查询指针移动到最后一行
                String userName = cursor.getString(cursor.getColumnIndex(ID));//参数表示列号
                String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
                String scorer = cursor.getString(cursor.getColumnIndex(SCORER));
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(userName);
                userInfo.setPassword(password);
                userInfo.setScorer(scorer);
                users.add(userInfo);
                cursor.moveToNext();//需要将查询指针下移一行
            }
        }
        return users;
    }

    //查询所有用户，按分数从高到低排序
    public static ArrayList<UserInfo> queryAllUsersOrderByScore(){
        ArrayList<UserInfo> users = new ArrayList<UserInfo>();
        if (db != null){
            Cursor cursor = db.rawQuery("select * from "+tableName+" ORDER BY "+SCORER+" DESC",new String[]{});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){//查询指针移动到最后一行
                String userName = cursor.getString(cursor.getColumnIndex(ID));//参数表示列号
                String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
                String scorer = cursor.getString(cursor.getColumnIndex(SCORER));
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(userName);
                userInfo.setPassword(password);
                userInfo.setScorer(scorer);
                users.add(userInfo);
                cursor.moveToNext();//需要将查询指针下移一行
            }
        }
        return users;
    }
}
