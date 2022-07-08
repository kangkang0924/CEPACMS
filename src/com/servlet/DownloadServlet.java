package com.servlet;


import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Download;
import com.model.Member;
import com.service.DownloadService;
import com.service.MemberService;

import com.util.PageUtil;
@WebServlet("/Download/*")
public class DownloadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);				
			}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String opt = request.getAttribute("opt") == null ?
                "" : request.getAttribute("opt").toString();
        if (opt.equalsIgnoreCase("queryAll.do")){
            queryAll(request,response);
        }else if (opt.equalsIgnoreCase("add.do")){
            add(request,response);
        }else if (opt.equalsIgnoreCase("delete.do")){
            delete(request,response);
        }else if (opt.equalsIgnoreCase("edit.do")){
            edit(request,response);
        }else if (opt.equalsIgnoreCase("update.do")){
            update(request,response);
        
        }else if (opt.equalsIgnoreCase("queryByKeyWords.do")){
            queryByKeyWords(request,response);
        }else if (opt.equalsIgnoreCase("service.do")){
        	service(request,response);
        }
      
    }

	private void queryByKeyWords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String downloadName = request.getParameter("downloadName");
        DownloadService service = new DownloadService();
        List<Download> dList = service.queryByKeyWords(downloadName);
        PageUtil pageUtil = new PageUtil();
        int totalCount = service.getCount();
        pageUtil.setTotalCount(totalCount);
        pageUtil.setPageSize(5);
        String str_currentPageIndex = request.getParameter("currentPageIndex");
        if (str_currentPageIndex == null || "".equals(str_currentPageIndex)) {
            pageUtil.setCurrentPageIndex(0);
        }else {
            int currentPageIndex = Integer.parseInt(str_currentPageIndex);
            pageUtil.setCurrentPageIndex(currentPageIndex);
        }
        System.out.println(pageUtil.toString());
        MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();
        List<Download> downloadList = service.queryAll(pageUtil);
        request.setAttribute("mList",mList);
        request.setAttribute("downloadList",downloadList);
        request.setAttribute("dList",dList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/download/DownloadList.jsp");
        dis.forward(request,response);
    }
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int downloadID = Integer.parseInt(request.getParameter("downloadID"));
        DownloadService service = new DownloadService();
        Download download = service.edit(downloadID);
        MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();      
        request.setAttribute("mList",mList);
        request.setAttribute("download",download);
        RequestDispatcher dis = request.getRequestDispatcher("/download/DownloadEdit.jsp");
        dis.forward(request,response);
    }
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int downloadID = Integer.parseInt(request.getParameter("downloadID"));
        String downloadName = request.getParameter("downloadName");
        int dmId = Integer.parseInt(request.getParameter("dmId"));
        Download download = new Download(downloadID,downloadName,dmId);
        DownloadService service = new DownloadService();
        boolean bln = service.update(download);
        response.sendRedirect("/CEPACMS/download/DownloadList.jsp?msg="+bln);
    }
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int downloadID = Integer.parseInt(request.getParameter("downloadID"));
        DownloadService service = new DownloadService();
        boolean bln = service.delete(downloadID);
        response.sendRedirect("/CEPACMS/download/DownloadList.jsp?msg="+bln);
    }
	
private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        /*UploadService service = new UploadService();
        
    	System.out.println("执行上传");    	
		// 判断请求是否为multipart请求
		if( !ServletFileUpload.isMultipartContent(request) ) {
			throw new RuntimeException("当前请求不支持文件上传");			
		}

    	SmartUpload smartUpload = new SmartUpload();
    	
		smartUpload.initialize(this.getServletConfig(), request, response);

		try {			
			smartUpload.upload();
			String uploadName = smartUpload.getRequest().getParameter("uploadName");		
	        int umId = Integer.parseInt(smartUpload.getRequest().getParameter("umId"));
            System.out.println(uploadName);
            System.out.println(umId);
            Upload upload = new Upload(0, uploadName, umId);
            Boolean bln = service.add(upload);
    		
            //上传到uploadFile文件夹中
            smartUpload.save("/WEB-INF/upload");
            response.sendRedirect("/CEPACMS/upload/UploadList.jsp");
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	
	    String downloadName = request.getParameter("downloadName");
	    int dmId = Integer.parseInt(request.getParameter("dmId"));
	    DownloadService service = new DownloadService();
	    Download download = new Download(0,downloadName,dmId);
	    boolean bln = service.add(download);
	    response.sendRedirect("/CEPACMS/download/DownloadList.jsp?msg="+bln);
	    
	  /*//转码链接的中文参数
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
	  		return ;*/
    }
	//分页查询所有
	private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageUtil pageUtil = new PageUtil();
	    DownloadService service = new DownloadService();
	    int totalCount = service.getCount();
	    pageUtil.setTotalCount(totalCount);
	    pageUtil.setPageSize(5);
	    String str_currentPageIndex = request.getParameter("currentPageIndex");
	    if (str_currentPageIndex == null || "".equals(str_currentPageIndex)) {
	        pageUtil.setCurrentPageIndex(0);
	    }else {
	        int currentPageIndex = Integer.parseInt(str_currentPageIndex);
	        pageUtil.setCurrentPageIndex(currentPageIndex);
	    }
	    System.out.println(pageUtil.toString());
	    MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();
	    List<Download> dList = service.queryAll(pageUtil);
	    request.setAttribute("mList",mList);
	    request.setAttribute("dList",dList);
	    request.setAttribute("pageUtil",pageUtil);
	    RequestDispatcher dis = request.getRequestDispatcher("/download/DownloadList.jsp");
	    dis.forward(request,response);
	}


}

















