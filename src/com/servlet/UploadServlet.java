package com.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jspsmart.upload.*;
import com.model.Member;
import com.model.Upload;
import com.service.MemberService;
import com.service.UploadService;
import com.util.PageUtil;
@WebServlet("/Upload/*")
@MultipartConfig
public class UploadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
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
        }
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
    	doPost(request,response);
    }
	private void queryByKeyWords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadName = request.getParameter("uploadName");
        UploadService service = new UploadService();
        List<Upload> uList = service.queryByKeyWords(uploadName);
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
        List<Upload> uploadList = service.queryAll(pageUtil);
        request.setAttribute("mList",mList);
        request.setAttribute("uploadList",uploadList);
        request.setAttribute("uList",uList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/upload/UploadList.jsp");
        dis.forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uploadID = Integer.parseInt(request.getParameter("uploadID"));
        UploadService service = new UploadService();
        Upload upload = service.edit(uploadID);
        MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();      
        request.setAttribute("mList",mList);
        request.setAttribute("upload",upload);
        RequestDispatcher dis = request.getRequestDispatcher("/upload/UploadEdit.jsp");
        dis.forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uploadID = Integer.parseInt(request.getParameter("uploadID"));
        String uploadName = request.getParameter("uploadName");
        int umId = Integer.parseInt(request.getParameter("umId"));
        Upload upload = new Upload(uploadID,uploadName,umId);
        UploadService service = new UploadService();
        boolean bln = service.update(upload);
        response.sendRedirect("/CEPACMS/upload/UploadList.jsp?msg="+bln);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uploadID = Integer.parseInt(request.getParameter("uploadID"));
        UploadService service = new UploadService();
        boolean bln = service.delete(uploadID);
        response.sendRedirect("/CEPACMS/upload/UploadList.jsp?msg="+bln);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("执行上传");   	
		// 判断请求是否为multipart请求
		if( !ServletFileUpload.isMultipartContent(request) ) {
			throw new RuntimeException("当前请求不支持文件上传");			
		}
    	SmartUpload smartUpload = new SmartUpload();
    	
		smartUpload.initialize(this.getServletConfig(), request, response);//初始化上传操作

		try {			
			smartUpload.upload();//上传准备
			String uploadName = smartUpload.getRequest().getParameter("uploadName");		
	        int umId = Integer.parseInt(smartUpload.getRequest().getParameter("umId"));
            System.out.println(uploadName);
            System.out.println(umId);
            Upload upload = new Upload(0, uploadName, umId);
            UploadService service = new UploadService();
            Boolean bln = service.add(upload);    		
            //上传到uploadFile文件夹中
            smartUpload.save("/WEB-INF/upload");
            response.sendRedirect("/CEPACMS/upload/UploadList.jsp?msg="+bln);
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //分页查询所有
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageUtil pageUtil = new PageUtil();
        UploadService service = new UploadService();
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
        List<Upload> uList = service.queryAll(pageUtil);
        request.setAttribute("uList",uList);
        MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();        
        request.setAttribute("mList",mList);        
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/upload/UploadList.jsp");
        dis.forward(request,response);
    }

}