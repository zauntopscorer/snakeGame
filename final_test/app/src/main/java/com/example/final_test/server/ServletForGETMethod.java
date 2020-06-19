package com.example.final_test.server;


//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class ServletForGETMethod
// */
//@WebServlet("/ServletForGETMethod")
//public class ServletForGETMethod extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ServletForGETMethod() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//    /**
//     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//     */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        //获取请求的参数（使用utf-8进行解码，然后用进行ISO8859-1编码）
////		String name=new String(request.getParameter("name").getBytes("ISO8859-1"),"utf-8");
//        String name = request.getParameter("name");
//        String pwd = request.getParameter("pwd");
//        System.out.println("name:" + name + "   pwd:" + pwd);
//
//    }
//
//    /**
//     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//    }
//}