package com.kitri.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.ZipcodeDto;
import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;

public class MemberDaoImpl implements MemberDao {

	private static MemberDao memberDao;
	
	static {
		memberDao = new MemberDaoImpl();
	}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}


	private MemberDaoImpl() {
		
	}

	
	@Override
	public int idcheck(String id) {
		int cnt = 1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select count(id) ");
			sql.append("from member ");
			sql.append("where id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			cnt = 1;
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<ZipcodeDto> zipSearch(String doro) {
		List<ZipcodeDto> list = new ArrayList<ZipcodeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select 	case \n");
			sql.append("			when length(new_post_code) = 4 then '0'||new_post_code ");
			sql.append("			else new_post_code ");
			sql.append("		end zipcode, ");
			sql.append("		sido_kor sido, gugun_kor gugun, ");
			sql.append("		nvl(upmyon_kor, ' ') upmyon, doro_kor doro, ");
			sql.append("		case when building_refer_number != '0' ");
			sql.append("			then building_origin_number||'-'||building_refer_number ");
			sql.append("			else trim(to_char(building_origin_number, '99999')) ");
			sql.append("		end building_number, sigugun_building_name ");
			sql.append("from 	postcode ");
			sql.append("where 	doro_kor like '%'||?||'%' ");
			sql.append("or sigugun_building_name like '%'||?||'%'");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, doro);
			pstmt.setString(2, doro);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeDto zipDto = new ZipcodeDto();
				zipDto.setZipcode(rs.getString("zipcode"));
				zipDto.setSido(rs.getString("sido"));
				zipDto.setGugun(rs.getString("gugun"));
				zipDto.setUpmyon(rs.getString("upmyon"));
				zipDto.setDoro(rs.getString("doro"));
				zipDto.setBuildingNumber(rs.getString("building_number"));
				zipDto.setSigugunBuildingName(rs.getString("sigugun_building_name"));
				
				list.add(zipDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetialDto) {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.makeConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("insert all ");
			sql.append("into member (id, name, pass, emailid, emaildomain, joindate) ");
			sql.append("values (?, ?, ?, ?, ?, sysdate) ");
			sql.append("into member_detail (id, zipcode, address, address_detail, tel1, tel2, tel3) ");
			sql.append("values (?, ?, ?, ?, ?, ?, ?) ");
			sql.append("select * ");
			sql.append("from dual");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 1;
			pstmt.setString(idx++, memberDetialDto.getId());
			pstmt.setString(idx++, memberDetialDto.getName());
			pstmt.setString(idx++, memberDetialDto.getPass());
			pstmt.setString(idx++, memberDetialDto.getEmailid());
			pstmt.setString(idx++, memberDetialDto.getEmaildomain());
			
			pstmt.setString(idx++, memberDetialDto.getId());
			pstmt.setString(idx++, memberDetialDto.getZipcode());
			pstmt.setString(idx++, memberDetialDto.getAddress());
			pstmt.setString(idx++, memberDetialDto.getAddressDetail());
			pstmt.setString(idx++, memberDetialDto.getTel1());
			pstmt.setString(idx++, memberDetialDto.getTel2());
			pstmt.setString(idx++, memberDetialDto.getTel3());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
		MemberDto memberDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select name, id, emailid, emaildomain, joindate ");
			sql.append("from member ");
			sql.append("where id = (?) and pass = (?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 1;
			pstmt.setString(idx++, map.get("userid"));
			pstmt.setString(idx++, map.get("userpwd"));
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberDto = new MemberDto();
				
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				memberDto.setEmailid(rs.getString("emailid"));
				memberDto.setEmaildomain(rs.getString("emaildomain"));
				memberDto.setJoindate(rs.getString("joindate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return memberDto;
	}

	@Override
	public MemberDetailDto getMember(String id) {
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto memberDetialDto) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("delete from member_detail ");
			sql.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			cnt += pstmt.executeUpdate();
			pstmt.close();
			
			sql.delete(0, sql.length());
			
			sql.append("delete from member ");
			sql.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			cnt += pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}
}
