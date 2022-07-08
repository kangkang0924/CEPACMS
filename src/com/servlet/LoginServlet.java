package com.servlet;

import com.service.AccountService;
import com.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {

	AccountService service = new AccountService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 //从request中获取accountName的值，赋给accountName
    	String accountName = request.getParameter("accountName");
    	//从request中获取accountPassword的值，赋值给accountPassword
        String accountPassword = request.getParameter("accountPassword");
        
        
        Account account = service.login(accountName,accountPassword);
        if (account==null){
            response.sendRedirect("/CEPACMS/login.jsp?msg=no");
        }else {
            if (account.getAccountStatus().equals("1")){
                response.sendRedirect("/CEPACMS/login.jsp?msg=stop");
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("role",account.getAccountRole());
                session.setAttribute("username",account.getAccountName());
                response.sendRedirect("/CEPACMS/index.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);

    }
}
