package com.kitri.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.ZipcodeDto;
import com.kitri.member.model.dao.MemberDaoImpl;


// using singleton pattern

// 1. create private constructor method
// 2. create static global variable
// 3. create static method (initiate)
// 4. create getter method

public class MemberServiceImpl implements MemberService {

	private static MemberService memberService;
	
	static {
		memberService = new MemberServiceImpl();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}

	
	private MemberServiceImpl() {
		
	}
	

	// text, XML, JSON
	@Override
	public String idcheck(String id) {
		int cnt = MemberDaoImpl.getMemberDao().idcheck(id);
		String result = "";
		result += "<idcount>\n";
		result += "	<cnt>" + cnt + "</cnt>\n";
		result += "</idcount>";
		
		return result;
	}

	@Override
	public String zipSearch(String doro) {
		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDao().zipSearch(doro);
		
		result += "<ziplist>\n";
		for (ZipcodeDto zipDto : list) {
			result += "	<zip>\n";
			result += "		<zipcode>" + zipDto.getZipcode() + "</zipcode>\n";
			result += "		<address><![CDATA[" + zipDto.getSido() + " " + zipDto.getGugun() + " " + zipDto.getUpmyon() + " " + zipDto.getDoro() + " " + zipDto.getBuildingNumber() + " " + zipDto.getSigugunBuildingName() + "]]></address>\n";
			result += "	</zip>\n";
		}
		result += "</ziplist>";
		
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetialDto) {
		return MemberDaoImpl.getMemberDao().registerMember(memberDetialDto);
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("userpwd", pass);
		
		return MemberDaoImpl.getMemberDao().loginMember(map);
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
		return 0;
	}
}
