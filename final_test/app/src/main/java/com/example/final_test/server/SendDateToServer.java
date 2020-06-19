package com.example.final_test.server;

//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//import android.os.Handler;
//
///**
// * 通过GET方式向服务器发送数据
// * @author jph
// * Date:2014.09.27
// */
//public class SendDateToServer {
//    private static String url="http://47.98.252.242:8080/ServerForGETMethod/ServletForGETMethod";
//    public static final int SEND_SUCCESS=0x123;
//    public static final int SEND_FAIL=0x124;
//    private Handler handler;
//    public SendDateToServer(Handler handler) {
//        // TODO Auto-generated constructor stub
//        this.handler=handler;
//    }
//    /**
//     * 通过Get方式向服务器发送数据
//     * @param name 用户名
//     * @param pwd  密码
//     */
//    public void SendDataToServer(String name,String pwd) {
//        // TODO Auto-generated method stub
//        final Map<String, String>map=new HashMap<String, String>();
//        map.put("name", name);
//        map.put("pwd", pwd);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                try {
//                    if (sendGetRequest(map,url,"utf-8")) {
//                        handler.sendEmptyMessage(SEND_SUCCESS);//通知主线程数据发送成功
//                    }else {
//                        //将数据发送给服务器失败
//                    }
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//    /**
//     * 发送GET请求
//     * @param map 请求参数
//     * @param url 请求路径
//     * @return
//     * @throws Exception
//     */
//    private  boolean sendGetRequest(Map<String, String> param, String url,String encoding) throws Exception {
//        // TODO Auto-generated method stub
//        //http://localhost:8080/ServerForGETMethod/ServletForGETMethod?name=aa&pwd=124
//        StringBuffer sb=new StringBuffer(url);
//        if (!url.equals("")&!param.isEmpty()) {
//            sb.append("?");
//            for (Map.Entry<String, String>entry:param.entrySet()) {
//                sb.append(entry.getKey()+"=");
//                sb.append(URLEncoder.encode(entry.getValue(), encoding));
//                sb.append("&");
//            }
//            sb.deleteCharAt(sb.length()-1);//删除字符串最后 一个字符“&”
//        }
//        HttpURLConnection conn=(HttpURLConnection) new URL(sb.toString()).openConnection();
//        conn.setConnectTimeout(5000);
//        conn.setRequestMethod("GET");//设置请求方式为GET
//        if (conn.getResponseCode()==200) {
//            return true;
//        }
//        return false;
//    }
//}

