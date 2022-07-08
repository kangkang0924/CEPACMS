package com.dao.impl;

import com.dao.IRecordDAO;
import com.model.Record;
import com.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDAOImpl implements IRecordDAO {

	private DBUtils db = new DBUtils();

	// 分页
	public List<Record> queryAll(int startIndex, int pageSize) {
		List<Record> rList = new ArrayList<Record>();
		Connection conn = db.openConnection();
		String sql = "SELECT record.*,community.cName,member.mName FROM record,community,member "
				+ "WHERE community.cId = record.rcId and member.mId = record.rmId LIMIT ?,?";
		ResultSet set = db.query(conn, sql, startIndex, pageSize);
		try {
			while (set.next()) {
				int rId = set.getInt("rId");
				int rmId = set.getInt("rmId");
				int rcId = set.getInt("rcId");
				String rIsNucleicAcidTest = set.getString("rIsNucleicAcidTest");
				String rIsOutCity = set.getString("rIsOutCity");
				String rIsFromHB = set.getString("rIsFromHB");
				String rIsHousehold = set.getString("rIsHousehold");
				String rNowTime = set.getString("rNowTime");
				String cName = set.getString("cName");
				String mName = set.getString("mName");
				Record record = new Record(rId, rmId, rcId,rIsNucleicAcidTest,rIsOutCity,
						rIsFromHB, rIsHousehold, rNowTime, cName, mName);
				rList.add(record);
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
		return rList;
	}

	// 计算总数
	public int getCount() {
		int count = 0;
		Connection conn = db.openConnection();
		String sql = "select count(*) from record";
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

	// 添加
	public int add(Record record) {
		Connection conn = db.openConnection();
		String sql = "insert into record(rId,rmId,rcId,rIsNucleicAcidTest,rIsOutCity,rIsFromHB,rIsHousehold,rNowTime) values(NULL,?,?,?,?,?,?,?)";
		int count = db.update(conn, sql, record.getRmId(), record.getRcId(),record.getrIsNucleicAcidTest(),
				record.getrIsOutCity(), record.getrIsFromHB(),
				record.getrIsHousehold(), record.getrNowTime());
		db.closeConnection(conn);
		return count;
	}

	public int delete(int rId) {
		Connection conn = db.openConnection();
		String sql = "delete from record where rId = ?";
		int count = db.update(conn, sql, rId);
		db.closeConnection(conn);
		return count;
	}

	public int update(Record record) {
		Connection conn = db.openConnection();
		String sql = "update record set rmId = ? , rcId = ? , rIsNucleicAcidTest = ? , rIsOutCity = ? , rIsFromHB = ? , rIsHousehold = ? , rNowTime = ? where rId = ?";
		int count = db.update(conn, sql, record.getRmId(), record.getRcId(),record.getrIsNucleicAcidTest(),
						record.getrIsOutCity(), record.getrIsFromHB(),
						record.getrIsHousehold(), record.getrNowTime(), record.getrId());
		db.closeConnection(conn);
		return count;
	}

	public Record queryByKey(int rId) {
		Connection conn = db.openConnection();
		String sql = "select record.*,community.cName,member.mName from record,community,member where community.cId = record.rcId and member.mId = record.rmId and rId = ?";
		ResultSet set = db.query(conn, sql, rId);
		Record record = null;
		try {
			while (set.next()) {
				int rID = set.getInt("rId");
				int rmId = set.getInt("rmId");
				int rcId = set.getInt("rcId");
				String rIsNucleicAcidTest = set.getString("rIsNucleicAcidTest");
				String rIsOutCity = set.getString("rIsOutCity");
				String rIsFromHB = set.getString("rIsFromHB");
				String rIsHousehold = set.getString("rIsHousehold");
				String rNowTime = set.getString("rNowTime");
				String cName = set.getString("cName");
				String mName = set.getString("mName");
				record = new Record(rID, rmId, rcId,rIsNucleicAcidTest, rIsOutCity, rIsFromHB,
						rIsHousehold, rNowTime, cName, mName);
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
		return record;
	}

	public List<Record> queryByWords(String mName) {
		List<Record> rList = new ArrayList<Record>();
		Connection conn = db.openConnection();
		String sql = "SELECT record.*,community.cName,member.mName FROM record,community,member WHERE community.cId = record.rcId and member.mId = record.rmId and member.mName like ?";
		ResultSet set = db.query(conn, sql, "%" + mName + "%");
		try {
			while (set.next()) {
				int rId = set.getInt("rId");
				int rmId = set.getInt("rmId");
				int rcId = set.getInt("rcId");
				String rIsNucleicAcidTest = set.getString("rIsNucleicAcidTest");
				String rIsOutCity = set.getString("rIsOutCity");
				String rIsFromHB = set.getString("rIsFromHB");
				String rIsHousehold = set.getString("rIsHousehold");
				String rNowTime = set.getString("rNowTime");
				String cName = set.getString("cName");
				String mname = set.getString("mName");
				Record record = new Record(rId, rmId, rcId,rIsNucleicAcidTest, rIsOutCity,
						rIsFromHB, rIsHousehold, rNowTime, cName, mname);
				rList.add(record);
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
		return rList;
	}

	public static void main(String[] args) {
		IRecordDAO dao = new RecordDAOImpl();
		List<Record> list = dao.queryAll(0, 5);
		for (Record record : list) {
			System.out.println(record.toString());
		}
	}
}
