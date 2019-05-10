package com.kitri.admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;


public class AdminDaoImpl implements AdminDao {

	private static AdminDao adminDao; 
	
	static {
		adminDao = new AdminDaoImpl();
	}
	
	private AdminDaoImpl() {
		
	}
	
	public static AdminDao getAdminDao() {
		return adminDao;
	}
	
	
	@Override
	public List<MemberDetailDto> getMemberList(Map<String, String> map) {
		List<MemberDetailDto> list = new ArrayList<MemberDetailDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select m.name, m.id, m.emailid, m.emaildomain, m.joindate, ");
			sql.append("d.tel1, d.tel2, d.tel3, d.zipcode, d.address, d.address_detail ");
			sql.append("from member m, member_detail d ");
			sql.append("where m.id = d.id ");
			
			String key = map.get("key");
			String word = map.get("word");
			
			if ((word != null) && (!word.isEmpty())) {
				if ("id".equals(key)) {
					sql.append("and m.id = ? ");
				} else {
					sql.append("and d." + key + " like '%'||?||'%' ");
				}
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 1;
			if ((word != null) && (!word.isEmpty())) {
				pstmt.setString(idx++, word);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberDetailDto memberDetailDto = new MemberDetailDto();
				
				memberDetailDto.setName(rs.getString("name"));
				memberDetailDto.setId(rs.getString("id"));
				memberDetailDto.setEmailid(rs.getString("emailid"));
				memberDetailDto.setEmaildomain(rs.getString("emaildomain"));
				memberDetailDto.setJoindate(rs.getString("joindate"));
				memberDetailDto.setTel1(rs.getString("tel1"));
				memberDetailDto.setTel2(rs.getString("tel2"));
				memberDetailDto.setTel3(rs.getString("tel3"));
				memberDetailDto.setZipcode(rs.getString("zipcode"));
				memberDetailDto.setAddress(rs.getString("address"));
				memberDetailDto.setAddressDetail(rs.getString("address_detail"));
				
				list.add(memberDetailDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
}
