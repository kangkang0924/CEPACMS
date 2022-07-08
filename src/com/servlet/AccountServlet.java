package com.servlet;

import com.model.Account;
import com.service.AccountService;
import com.util.PageUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/Account/*")
public class AccountServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String opt = request.getAttribute("opt")==null ?
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
        }else if (opt.equalsIgnoreCase("queryKeyWords.do")){
            queryKeyWords(request,response);
        
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }


    private void queryKeyWords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("accountName");
        AccountService service = new AccountService();
        List<Account> aList = service.queryKeyWords(accountName);
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
        request.setAttribute("aList",aList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/account/AccountList.jsp");
        dis.forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        String accountName = request.getParameter("accountName");
        String accountPassword = request.getParameter("accountPassword");
        String accountStatus = request.getParameter("accountStatus");
        String accountRole = request.getParameter("accountRole");
        AccountService service = new AccountService();
        Account account = new Account(accountID,accountName,accountPassword,accountStatus,accountRole);
        Boolean bln = service.update(account);
        response.sendRedirect("/CEPACMS/account/AccountList.jsp?msg="+bln);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        AccountService service = new AccountService();
        Account account = service.edit(accountID);
        request.setAttribute("account",account);
        RequestDispatcher dis = request.getRequestDispatcher("/account/AccountEdit.jsp");
        dis.forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        AccountService service = new AccountService();
        boolean bln = service.delete(accountID);
        response.sendRedirect("/CEPACMS/account/AccountList.jsp?msg="+bln);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountName = request.getParameter("accountName");
        String accountPassword = request.getParameter("accountPassword");
        String accountStatus = request.getParameter("accountStatus");
        String accountRole = request.getParameter("accountRole");
        AccountService service = new AccountService();
        Account account = new Account(0,accountName,accountPassword,accountStatus,accountRole);
        boolean bln = service.add(account);
        response.sendRedirect("/CEPACMS/account/AccountList.jsp?msg="+bln);
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageUtil pageUtil = new PageUtil();
        AccountService service = new AccountService();
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
        List<Account> aList = service.queryAll(pageUtil);
        request.setAttribute("aList",aList);
        request.setAttribute("pageUtil",pageUtil);
        RequestDispatcher dis = request.getRequestDispatcher("/account/AccountList.jsp");
        dis.forward(request,response);
    }
}
