package com.dao.impl;

import com.dao.ICommunityDAO;
import com.model.Community;
import com.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommunityDAOImpl implements ICommunityDAO {

    private DBUtils db = new DBUtils();

  //分页
    public List<Community> queryAll(int startIndex, int pageSize) {
        List<Community> list = new ArrayList<Community>();
        Connection conn = db.openConnection();
        String sql = "select * from community limit ?,?";
        ResultSet set = db.query(conn, sql, startIndex, pageSize);
        try {
            while (set.next()){
                int cId = set.getInt("cId");
                String cName = set.getString("cName");
                String cProvince = set.getString("cProvince");
                String cCity = set.getString("cCity");
                String cStreet = set.getString("cStreet");
                String cTel = set.getString("cTel");
                Community community = new Community(cId,cName,cProvince,cCity,cStreet,cTel);
                list.add(community);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection(conn);
        }
        return list;
    }

  //查询总笔数

    public int getCount() {
        int count = 0;
        Connection conn = db.openConnection();
        String sql ="select count(*) from community";
        ResultSet set = db.query(conn, sql);
        try {
            if (set.next()) {
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

  
    public List<Community> queryAllC() {
        List<Community> list = new ArrayList<Community>();
        Connection conn = db.openConnection();
        String sql = "select * from community ";
        ResultSet set = db.query(conn, sql);
        try {
            while (set.next()){
                int cId = set.getInt("cId");
                String cName = set.getString("cName");
                String cProvince = set.getString("cProvince");
                String cCity = set.getString("cCity");
                String cStreet = set.getString("cStreet");
                String cTel = set.getString("cTel");
                Community community = new Community(cId,cName,cProvince,cCity,cStreet,cTel);
                list.add(community);
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
        return list;
    }

  //添加
  
    public int add(Community community) {
        Connection conn = db.openConnection();
        String sql = "insert into community(cId,cName,cProvince,cCity,cStreet,cTel) values(NULL,?,?,?,?,?)";
        int count = db.update(conn,sql,community.getcName(),community.getcProvince(),community.getcCity(),
        community.getcStreet(),community.getcTel());
        db.closeConnection(conn);
        return count;
    }

   
    public int delete(int cId) {
        Connection conn = db.openConnection();
        String sql ="delete from community where cId = ?";
        int count = db.update(conn,sql,cId);
        db.closeConnection(conn);
        return count;
    }

 
    public Community queryByKey(int cId) {
        Connection conn = db.openConnection();
        String sql = "select * from community where cId = ?";
        ResultSet set = db.query(conn,sql,cId);
        Community community = null;
        try {
            while (set.next()){
                int cID = set.getInt("cId");
                String cName = set.getString("cName");
                String cProvince = set.getString("cProvince");
                String cCity = set.getString("cCity");
                String cStreet = set.getString("cStreet");
                String cTel = set.getString("cTel");
                community = new Community(cID,cName,cProvince,cCity,cStreet,cTel);
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
        return community;
    }

   
    public int update(Community community) {
        Connection conn = db.openConnection();
        String sql = "update community set cName = ? , cProvince = ? , cCity = ? ," +
                "cStreet = ? , cTel = ? where cId = ?";
        int count = db.update(conn,sql,community.getcName(),community.getcProvince(),community.getcCity(),
                community.getcStreet(),community.getcTel(),community.getcId());
        db.closeConnection(conn);
        return count;
    }



    
    public List<Community> queryByKeyWords(String cName) {
        List<Community> cLists = new ArrayList<Community>();
        Connection conn = db.openConnection();
        String sql = "select * from community where cName like ?";
        ResultSet set = db.query(conn,sql,"%"+cName+"%");
        try {
            while (set.next()){
                int cId = set.getInt("cId");
                String cname = set.getString("cName");
                String cProvince = set.getString("cProvince");
                String cCity = set.getString("cCity");
                String cStreet = set.getString("cStreet");
                String cTel = set.getString("cTel");
                Community community = new Community(cId,cname,cProvince,cCity,cStreet,cTel);
                cLists.add(community);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection(conn);
        }
        return cLists;
    }
}
