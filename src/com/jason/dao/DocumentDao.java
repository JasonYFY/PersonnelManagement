package com.jason.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.jason.domain.Document;


public class DocumentDao extends BaseDao{
	
	public int insertDocument(Document doc) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into document_inf(TITLE,filename,REMARK,CREATE_DATE,file_data,USER_ID) values(?,?,?,?,?,?)");

			pstmt.setString(1, doc.getTitle());
			pstmt.setString(2, doc.getFileName());
			pstmt.setString(3, doc.getRemark());
			pstmt.setTimestamp(4, new Timestamp(doc.getCreateDate().getTime()));
			pstmt.setBlob(5, doc.getFileData());
			pstmt.setInt(6, doc.getUserId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(null,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	public int deleteDocument(Document doc) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from document_inf where ID=?");
			
			pstmt.setInt(1, doc.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(null,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public ArrayList<Document> queryDocument(Document doc){
		ArrayList<Document> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_inf where title like ? ");

			pstmt.setString(1, "%"+doc.getTitle()+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Document nowDoc = new Document();
				nowDoc.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
				nowDoc.setFileData(rs.getBlob("file_data").getBinaryStream());
				nowDoc.setFileName(rs.getString("filename"));
				nowDoc.setId(rs.getInt("ID"));
				nowDoc.setRemark(rs.getString("remark"));
				nowDoc.setTitle(rs.getString("TITLE"));
				nowDoc.setUserId(rs.getInt("USER_ID"));
				list.add(nowDoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<Document> queryAllDocument(){
		ArrayList<Document> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from document_inf ");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Document nowDoc = new Document();
				nowDoc.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
				nowDoc.setFileData(rs.getBlob("file_data").getBinaryStream());
				nowDoc.setFileName(rs.getString("filename"));
				nowDoc.setId(rs.getInt("ID"));
				nowDoc.setRemark(rs.getString("remark"));
				nowDoc.setTitle(rs.getString("TITLE"));
				nowDoc.setUserId(rs.getInt("USER_ID"));
				list.add(nowDoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
}
