package com.kitri.member.model.service;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;

public interface MemberService {

	String idcheck(String id); 								// 0이면 사용가능, 1이면 불가능
	String zipSearch(String doro);
	int registerMember(MemberDetailDto memberDetialDto);	// 0이면 실패, 1이면 성공
	MemberDto loginMember(String id, String pass);			// 0이면 실패, 1이면 성공
	
	MemberDetailDto getMember(String id);
	int modifyMember(MemberDetailDto memberDetialDto);		// 0이면 실패, 1이면 성공
	int deleteMember(String id);							// 0이면 실패, 1이면 성공
}
