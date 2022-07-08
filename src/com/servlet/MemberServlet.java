package com.servlet;

import com.service.CommunityService;
import com.service.MemberService;
import com.model.Community;
import com.model.Member;
import com.util.PageUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/Member/*")
public class MemberServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String opt = request.getAttribute("opt")==null ? "" : request.getAttribute("opt").toString();
         if (opt.equalsIgnoreCase("queryAll.do")){
             queryAll(request,response);
         }else if(opt.equalsIgnoreCase("queryAllM.do")){
             queryAllM(request,response);
         }else if (opt.equalsIgnoreCase("add.do")){
             add(request,response);
         }else if(opt.equalsIgnoreCase("initAdd.do")){
             CommunityService communityService = new CommunityService();
             List<Community> cList = communityService.queryAllC();
             request.setAttribute("cList",cList);
             request.getRequestDispatcher("/member/MemberAdd.jsp").forward(request,response);
         }else if (opt.equalsIgnoreCase("delete.do")){
             delete(request,response);
         }else if (opt.equalsIgnoreCase("update.do")){
             update(request,response);
         }else if (opt.equalsIgnoreCase("edit.do")){
             edit(request,response);
         }else if (opt.equalsIgnoreCase("queryByKeyWords.do")){
             queryByKeyWords(request,response);
         }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }

    private void queryByKeyWords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mName = request.getParameter("mName");
        MemberService service = new MemberService();
        List<Member> mList = service.queryByKeyWords(mName);
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
        request.setAttribute("mList",mList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/member/MemberList.jsp");
        dis.forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mId = Integer.parseInt(request.getParameter("mId"));
        MemberService service = new MemberService();
        Member member = service.edit(mId);
        CommunityService communityService = new CommunityService();
        List<Community> cList = communityService.queryAllC();
        request.setAttribute("cList",cList);
        request.setAttribute("member",member);
        RequestDispatcher dis = request.getRequestDispatcher("/member/MemberEdit.jsp");
        dis.forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mId = Integer.parseInt(request.getParameter("mId"));
        String mName = request.getParameter("mName");
        String mVaccine = request.getParameter("mVaccine");
        String mTel = request.getParameter("mTel");
        String mSex = request.getParameter("mSex");
        int mAge = Integer.parseInt(request.getParameter("mAge")) ;
        String mHouseNum = request.getParameter("mHouseNum");
        String mWorkUnit = request.getParameter("mWorkUnit");
        String mCarNum = request.getParameter("mCarNum");
        String mIsHousehold = request.getParameter("mIsHousehold");
        int mcId = Integer.parseInt(request.getParameter("mcId"));
        Member member = new Member(mId,mName,mVaccine,mTel,mSex,mAge,mHouseNum,mWorkUnit,mCarNum,mIsHousehold,mcId);
        MemberService service = new MemberService();
        boolean bln = service.update(member);
        response.sendRedirect("/CEPACMS/member/MemberList.jsp?msg="+bln);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mId = Integer.parseInt(request.getParameter("mId"));
        MemberService service = new MemberService();
        boolean bln = service.delete(mId);
        response.sendRedirect("/CEPACMS/member/MemberList.jsp?msg="+bln);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mName = request.getParameter("mName");
        String mVaccine = request.getParameter("mVaccine");
        String mTel = request.getParameter("mTel");
        String mSex = request.getParameter("mSex");
        int mAge = Integer.parseInt(request.getParameter("mAge")) ;
        String mHouseNum = request.getParameter("mHouseNum");
        String mWorkUnit = request.getParameter("mWorkUnit");
        String mCarNum = request.getParameter("mCarNum");
        String mIsHousehold = request.getParameter("mIsHousehold");
        int mcId = Integer.parseInt(request.getParameter("mcId"));
        MemberService service = new MemberService();
        Member member = new Member(0,mName,mVaccine,mTel,mSex,mAge,mHouseNum,mWorkUnit,mCarNum,mIsHousehold,mcId);
        Boolean bln = service.add(member);
        response.sendRedirect("/CEPACMS/member/MemberList.jsp?msg="+bln);
    }

    //查询所有
    private void queryAllM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberService service = new MemberService();
        List<Member> mList = service.queryAllM();
        CommunityService communityService = new CommunityService();
        List<Community> cList = communityService.queryAllC();
        request.setAttribute("cList",cList);
        request.setAttribute("mList",mList);
        RequestDispatcher dis = request.getRequestDispatcher("/member/MemberList.jsp");
        dis.forward(request,response);
    }

    //分页查询所有
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageUtil pageUtil = new PageUtil();
        MemberService service = new MemberService();
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
        CommunityService communityService = new CommunityService();
        List<Community> cList = communityService.queryAllC();
        request.setAttribute("cList",cList);
        List<Member> mList = service.queryAll(pageUtil);
        request.setAttribute("mList",mList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/member/MemberList.jsp");
        dis.forward(request,response);
    }
}
