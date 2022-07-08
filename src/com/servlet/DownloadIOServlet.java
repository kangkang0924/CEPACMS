package com.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadIO/*")
public class DownloadIOServlet  extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);				
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String opt = request.getAttribute("opt") == null ?
                "" : request.getAttribute("opt").toString();
        if (opt.equalsIgnoreCase("service.do")){
        	service(request,response);
        }
      
    }
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		//转码链接的中文参数
  		String filename =new String( request.getParameter("filename").getBytes("ISO-8859-1"),"utf-8");
  		
  		String filePath = request.getServletContext().getRealPath("/")+"WEB-INF/download/"+filename;
  		System.out.println(filePath);
  		File file = new File(filePath);
  		
  		//逆编码,传送文件名
  	    response.setHeader("Content-Disposition", "attachment;filename="+
  				 new String(filename.getBytes("utf-8"),"ISO-8859-1"));
  		
  	    response.setHeader("Content-Length", ""+file.length());
  		response.setContentType("application/octet-stream");
  		InputStream inStream  = new FileInputStream(file);
  		//写出数据
  		write(inStream,response.getOutputStream());
  		return ;
		
	}
	private static void write(InputStream inStream,OutputStream outStream){
	
	BufferedInputStream in = new BufferedInputStream(inStream);
	BufferedOutputStream out = new BufferedOutputStream(outStream);
	int len =0;
	byte[] b =new byte[1024];
	try {
		while((len=in.read(b))>=0){
			out.write(b, 0, len);
			out.flush();
		}
	} catch (IOException e) {
		return ;
	}
}
	
}
