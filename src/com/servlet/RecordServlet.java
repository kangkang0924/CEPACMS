package com.servlet;

import com.service.CommunityService;
import com.service.MemberService;
import com.service.RecordService;
import com.model.Community;
import com.model.Member;
import com.model.Record;
import com.util.PageUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/Record/*")
public class RecordServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String opt = request.getAttribute("opt") == null ?
                "" : request.getAttribute("opt").toString();
        if (opt.equalsIgnoreCase("queryAll.do")){
            queryAll(request,response);
        }else if (opt.equalsIgnoreCase("initAdd.do")){
            CommunityService communityService = new CommunityService();
            List<Community> cList = communityService.queryAllC();
            MemberService memberService = new MemberService();
            List<Member> mList = memberService.queryAllM();
            request.setAttribute("cList",cList);
            request.setAttribute("mList",mList);
            request.getRequestDispatcher("/record/RecordAdd.jsp").forward(request,response);
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
        String mName = request.getParameter("mName");
        RecordService service = new RecordService();
        List<Record> rList = service.queryByKeyWords(mName);
        PageUtil pageUtil = new PageUtil();
        int totalCount = service.getCount();
        pageUtil.setTotalCount(totalCount);
        pageUtil.setPageSize(5);
        String str_currentPageIndex = request.getParameter("currentPageIndex");
        if (str_currentPageIndex == null || "".equals(str_currentPageIndex))  {
            pageUtil.setCurrentPageIndex(0);
        }else {
            int currentPageIndex = Integer.parseInt(str_currentPageIndex);
            pageUtil.setCurrentPageIndex(currentPageIndex);
        }
        System.out.println(pageUtil.toString());
        CommunityService communityService = new CommunityService();
        List<Community> cList = communityService.queryAllC();
        MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();
        List<Record> recordList = service.queryAll(pageUtil);
        request.setAttribute("cList",cList);
        request.setAttribute("mList",mList);
        request.setAttribute("recordList",recordList);
        request.setAttribute("rList",rList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/record/RecordList.jsp");
        dis.forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rId = Integer.parseInt(request.getParameter("rId"));
        int rmId = Integer.parseInt(request.getParameter("rmId"));
        int rcId = Integer.parseInt(request.getParameter("rcId"));
        String rIsNucleicAcidTest = request.getParameter("rIsNucleicAcidTest");
        String rIsOutCity = request.getParameter("rIsOutCity");
        String rIsFromHB = request.getParameter("rIsFromHB");
        String rIsHousehold = request.getParameter("rIsHousehold");
        String rNowTime = request.getParameter("rNowTime");
        RecordService service = new RecordService();
        Record record = new Record(rId,rmId,rcId,rIsNucleicAcidTest,rIsOutCity,rIsFromHB,rIsHousehold,rNowTime);
        boolean bln = service.update(record);
        response.sendRedirect("/CEPACMS/record/RecordList.jsp?msg="+bln);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rId = Integer.parseInt(request.getParameter("rId"));
        RecordService service = new RecordService();
        Record record = service.edit(rId);
        CommunityService communityService = new CommunityService();
        List<Community> cList = communityService.queryAllC();
        MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();
        request.setAttribute("mList",mList);
        request.setAttribute("cList",cList);
        request.setAttribute("record",record);
        RequestDispatcher dis = request.getRequestDispatcher("/record/RecordEdit.jsp");
        dis.forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rId = Integer.parseInt(request.getParameter("rId"));
        RecordService service = new RecordService();
        boolean bln = service.delete(rId);
        response.sendRedirect("/CEPACMS/record/RecordList.jsp?msg="+bln);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rmId = Integer.parseInt(request.getParameter("rmId"));
        int rcId = Integer.parseInt(request.getParameter("rcId"));
        String rIsNucleicAcidTest = request.getParameter("rIsNucleicAcidTest");
        String rIsOutCity = request.getParameter("rIsOutCity");
        String rIsFromHB = request.getParameter("rIsFromHB");
        String rIsHousehold = request.getParameter("rIsHousehold");
        String rNowTime = request.getParameter("rNowTime");
        RecordService service = new RecordService();
        Record record = new Record(0,rmId,rcId,rIsNucleicAcidTest,rIsOutCity,rIsFromHB,rIsHousehold,rNowTime);
        boolean bln = service.add(record);
        response.sendRedirect("/CEPACMS/record/RecordList.jsp?msg="+bln);

    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageUtil pageUtil = new PageUtil();
        RecordService service = new RecordService();
        int totalCount = service.getCount();
        pageUtil.setTotalCount(totalCount);
        pageUtil.setPageSize(5);
        String str_currentPageIndex = request.getParameter("currentPageIndex");
        if (str_currentPageIndex == null || "".equals(str_currentPageIndex))  {
            pageUtil.setCurrentPageIndex(0);
        }else {
            int currentPageIndex = Integer.parseInt(str_currentPageIndex);
            pageUtil.setCurrentPageIndex(currentPageIndex);
        }
        System.out.println(pageUtil.toString());
        CommunityService communityService = new CommunityService();
        List<Community> cList = communityService.queryAllC();
        MemberService memberService = new MemberService();
        List<Member> mList = memberService.queryAllM();
        List<Record> rList = service.queryAll(pageUtil);
        request.setAttribute("cList",cList);
        request.setAttribute("mList",mList);
        request.setAttribute("rList",rList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/record/RecordList.jsp");
        dis.forward(request,response);
    }
}
