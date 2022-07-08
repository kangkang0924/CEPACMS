package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.IUploadDAO;
import com.model.Upload;
import com.util.DBUtils;

public class UploadDAOImpl implements IUploadDAO {
	
	private DBUtils db = new DBUtils();
	
	// 分页
	@Override
	public List<Upload> queryAll(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<Upload> uList = new ArrayList<Upload>();
		Connection conn = db.openConnection();
		String sql = "SELECT upload.*,member.mName FROM upload,member "
				+ "WHERE member.mId = upload.umId LIMIT ?,?";
		ResultSet set = db.query(conn, sql, startIndex, pageSize);
		try {
			while (set.next()) {
				int uploadID = set.getInt("uploadID");
				String uploadName = set.getString("uploadName");
				int umId = set.getInt("umId");
				String mName = set.getString("mName");
				Upload upload = new Upload(uploadID, uploadName, umId, mName);
				uList.add(upload);
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
		return uList;
	}
	
	// 计算总数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		Connection conn = db.openConnection();
		String sql = "select count(*) from upload";
		ResultSet set = db.query(conn, sql);
		try {
			if (set.next()) {
				count = set.getInt(1);
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
		return count;
	}

	@Override
	public int add(Upload upload) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "insert into upload(uploadID,uploadName,umId) values(NULL,?,?)";
		int count = db.update(conn, sql, upload.getUploadName(), upload.getUmId());
		db.closeConnection(conn);
		return count;
	}

	@Override
	public int delete(int uploadID) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "delete from upload where uploadID = ?";
		int count = db.update(conn, sql, uploadID);
		db.closeConnection(conn);
		return count;
	}

	@Override
	public int update(Upload upload) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "update upload set uploadName = ? , umId = ? where uploadID = ?";
		int count = db.update(conn, sql, upload.getUploadName(), upload.getUmId(),upload.getUploadID());
		db.closeConnection(conn);
		return count;
	}

	@Override
	public Upload queryByKey(int uploadID) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "select upload.*,member.mName from upload,member where member.mId = upload.umId and uploadID = ?";
		ResultSet set = db.query(conn, sql, uploadID);
		Upload upload = null;
		try {
			while (set.next()) {
				int uploadId = set.getInt("uploadID");
				String uploadName = set.getString("uploadName");
				int umId = set.getInt("umId");
				String mName = set.getString("mName");
				upload = new Upload(uploadId, uploadName, umId, mName);
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
		return upload;
	}

	@Override
	public List<Upload> queryByWords(String mName) {
		// TODO Auto-generated method stub
		List<Upload> uList = new ArrayList<Upload>();
		Connection conn = db.openConnection();
		String sql = "SELECT upload.*,member.mName FROM upload,member WHERE member.mId = upload.umId and member.mName like ?";
		ResultSet set = db.query(conn, sql, "%" + mName + "%");
		try {
			while (set.next()) {
				int uploadID = set.getInt("uploadID");
				String uploadName = set.getString("uploadName");
				int umId = set.getInt("umId");
				String mname = set.getString("mName");
				Upload upload = new Upload(uploadID, uploadName, umId, mname);
				uList.add(upload);
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
		return uList;
	}
	public static void main(String[] args) {
        IUploadDAO dao = new UploadDAOImpl();
        List<Upload> list = dao.queryAll(0, 5);
		for (Upload upload : list) {
			System.out.println(upload.toString());
		}
    }

}
