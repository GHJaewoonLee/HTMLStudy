package com.kitri.member.model.dao;

import java.util.List;
import java.util.Map;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.ZipcodeDto;

public interface MemberDao {

	int idcheck(String id); 								// 0이면 사용가능, 1이면 불가능
	List<ZipcodeDto> zipSearch(String doro);
	int registerMember(MemberDetailDto memberDetialDto);	// 0이면 실패, 1이면 성공
	MemberDto loginMember(Map<String, String> map);			// 0이면 실패, 1이면 성공
	
	MemberDetailDto getMember(String id);
	int modifyMember(MemberDetailDto memberDetialDto);		// 0이면 실패, 1이면 성공
	int deleteMember(String id);							// 0이면 실패, 1이면 성공
}
