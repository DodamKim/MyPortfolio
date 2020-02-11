package com.bit.campfire.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bit.campfire.db.LoginManager;
import com.bit.campfire.vo.MemberVo;

@Repository
public class LoginDao {

	public MemberVo getMember(HashMap map) {
		return LoginManager.getMember(map);
	}

	public int insertMember(HashMap map) {
		return LoginManager.insertMember(map);
	}

	public List<MemberVo> listMember(HashMap map) {

		return LoginManager.listMember(map);
	}

	public int stopMember(HashMap map) {

		return LoginManager.stopMember(map);
	}

	public int resetMember(HashMap map) {

		return LoginManager.resetMember(map);
	}

	public int deleteMember(HashMap map) {

		return LoginManager.deleteMember(map);
	}

	public int getTotal() {

		return LoginManager.getTotal();
	}


	public int getCountType(String type) {
		return LoginManager.getCountType(type);
	}

}
