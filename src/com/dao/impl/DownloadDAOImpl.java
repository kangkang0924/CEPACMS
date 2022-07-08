package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.IDownloadDAO;
import com.model.Download;
import com.util.DBUtils;

public class DownloadDAOImpl implements IDownloadDAO {
	private DBUtils db = new DBUtils();
	
	// 分页
	@Override
	public List<Download> queryAll(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<Download> dList = new ArrayList<Download>();
		Connection conn = db.openConnection();
		String sql = "SELECT download.*,member.mName FROM download,member "
				+ "WHERE member.mId = download.dmId LIMIT ?,?";
		ResultSet set = db.query(conn, sql, startIndex, pageSize);
		try {
			while (set.next()) {
				int downloadID = set.getInt("downloadID");
				String downloadName = set.getString("downloadName");
				int dmId = set.getInt("dmId");
				String mName = set.getString("mName");
				Download download = new Download(downloadID, downloadName, dmId, mName);
				dList.add(download);
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
		return dList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		Connection conn = db.openConnection();
		String sql = "select count(*) from download";
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
	public int add(Download download) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "insert into download(downloadID,downloadName,dmId) values(NULL,?,?)";
		int count = db.update(conn, sql, download.getDownloadName(), download.getDmId());
		db.closeConnection(conn);
		return count;
	}

	@Override
	public int delete(int downloadID) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "delete from download where downloadID = ?";
		int count = db.update(conn, sql, downloadID);
		db.closeConnection(conn);
		return count;
	}

	@Override
	public int update(Download download) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "update download set downloadName = ? , dmId = ? where downloadID = ?";
		int count = db.update(conn, sql, download.getDownloadName(), download.getDmId(),download.getDownloadID());
		db.closeConnection(conn);
		return count;
	}

	@Override
	public Download queryByKey(int downloadID) {
		// TODO Auto-generated method stub
		Connection conn = db.openConnection();
		String sql = "select download.*,member.mName from download,member where member.mId = download.dmId and downloadID = ?";
		ResultSet set = db.query(conn, sql, downloadID);
		Download download = null;
		try {
			while (set.next()) {
				int downloadId = set.getInt("downloadID");
				String downloadName = set.getString("downloadName");
				int dmId = set.getInt("dmId");
				String mName = set.getString("mName");
				download = new Download(downloadId, downloadName, dmId, mName);
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
		return download;
	}

	@Override
	public List<Download> queryByWords(String mName) {
		// TODO Auto-generated method stub
		List<Download> dList = new ArrayList<Download>();
		Connection conn = db.openConnection();
		String sql = "SELECT download.*,member.mName FROM download,member WHERE member.mId = download.dmId and member.mName like ?";
		ResultSet set = db.query(conn, sql, "%" + mName + "%");
		try {
			while (set.next()) {
				int downloadID = set.getInt("downloadID");
				String downloadName = set.getString("downloadName");
				int dmId = set.getInt("dmId");
				String mname = set.getString("mName");
				Download download = new Download(downloadID, downloadName, dmId, mname);
				dList.add(download);
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
		return dList;
	}
	public static void main(String[] args) {
        IDownloadDAO dao = new DownloadDAOImpl();
        List<Download> list = dao.queryAll(0, 5);
		for (Download download : list) {
			System.out.println(download.toString());
		}
    }

}
