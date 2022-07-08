package com.dao.impl;

import com.dao.IAccountDAO;
import com.model.Account;
import com.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements IAccountDAO {

    private DBUtils db = new DBUtils();
  //登录
    public Account queryByKeyWords(String username, String password) {
    	//打开连接
        Connection conn = db.openConnection();
        //查询数据库
        String sql = "select * from account where accountName = ? and accountPassword = ?";
        Account account = null;
        //调用查询方法，返回结果集
        ResultSet set = db.query(conn, sql, username, password);
        //循环取值
        try {
            if(set.next()){
            	//获取	
                int accountID = set.getInt("accountID");
                String accountName = set.getString("accountName");
                String accountPassword = set.getString("accountPassword");
                String accountStatus = set.getString("accountStatus");
                String accountRole = set.getString("accountRole");
                
                account = new Account(accountID,accountName,
                        accountPassword,accountStatus,accountRole);
            }
            set.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.closeConnection(conn);
        }

        return account;
    }

  //分页
    public List<Account> queryAll(int startIndex, int pageSize) {
    	//声明一个List集合实体对象
        List<Account> aList = new ArrayList<Account>();
        //打开连接
        Connection conn = db.openConnection();
        //编写SQL
        String sql = "SELECT * FROM account LIMIT ?,?";
      //调用查询方法，返回结果集
        ResultSet set = db.query(conn, sql,startIndex,pageSize);
        //循环取值
        try {
            while (set.next()) {
                int accountID = set.getInt("accountID");
                String accountName = set.getString("accountName");
                String accountPassword = set.getString("accountPassword");
                String accountStatus = set.getString("accountStatus");
                String accountRole = set.getString("accountRole");
              //声明是一个实体
                Account account = new Account(accountID, accountName, accountPassword,
                		accountStatus, accountRole);
              //添加到集合中
                aList.add(account);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection(conn);
        }
        return aList;
    }

    //查询总笔数
    public int getCount() {
        int count = 0;
        Connection conn = db.openConnection();
        String sql = "select count(*) from account";
        ResultSet set = db.query(conn,sql);
        try {
            if (set.next()){
                count = set.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection(conn);
        }
        return count;
    }

    //添加
    public int add(Account account) {
        Connection conn = db.openConnection();
        String sql = "insert into account(accountID,accountName,accountPassword,accountStatus,accountRole)" +
                " values(NULL,?,?,?,?)";
        int count = db.update(conn,sql,account.getAccountName(),account.getAccountPassword(),
                account.getAccountStatus(),account.getAccountRole());
        db.closeConnection(conn);
        return count;
    }

    public int delete(int accountID) {
        Connection conn = db.openConnection();
        String sql = "delete from account where accountID = ?";
        int count = db.update(conn,sql,accountID);
        db.closeConnection(conn);
        return count;
    }

    public int update(Account account) {
        Connection conn = db.openConnection();
        String sql = "update account set accountName = ? , accountPassword = ? , accountStatus = ? , accountRole = ? where accountID = ?";
        int count = db.update(conn,sql,account.getAccountName(),account.getAccountPassword(),
                account.getAccountStatus(),account.getAccountRole(),account.getAccountID());
        db.closeConnection(conn);
        return count;
    }

    public Account queryByKey(int accountID) {
        Connection conn = db.openConnection();
        String sql = "select * from account where accountID = ?";
        ResultSet set = db.query(conn,sql,accountID);
        Account account = null;
        try {
            while (set.next()){
                int accountId = set.getInt("accountID");
                String accountName = set.getString("accountName");
                String accountPassword = set.getString("accountPassword");
                String accountStatus = set.getString("accountStatus");
                String accountRole = set.getString("accountRole");
                account = new Account(accountId,accountName,accountPassword,accountStatus,accountRole);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection(conn);
        }
        return account;
    }



    public List<Account> queryKeyWords(String accountName) {
        List<Account> aList = new ArrayList<Account>();
        Connection conn = db.openConnection();
        String sql = "select * from account where accountName like ?";
        ResultSet set = db.query(conn,sql,"%"+accountName+"%");
        try {
            while (set.next()){
                int accountID = set.getInt("accountID");
                String accountname = set.getString("accountName");
                String accountPassword = set.getString("accountPassword");
                String accountStatus = set.getString("accountStatus");
                String accountRole = set.getString("accountRole");
                Account account = new Account(accountID,accountname,accountPassword,accountStatus,accountRole);
                aList.add(account);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection(conn);
        }
        return aList;
    }

    public static void main(String[] args) {
        IAccountDAO dao = new AccountDAOImpl();
        List<Account> account = dao.queryAll(0,4);
        for (Account account1 : account) {
            System.out.println(account1);
        }
    }
}
