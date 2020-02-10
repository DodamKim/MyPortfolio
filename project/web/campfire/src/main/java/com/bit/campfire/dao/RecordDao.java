package com.bit.campfire.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bit.campfire.db.RecordManager;
import com.bit.campfire.vo.RecordVo;

@Repository
public class RecordDao {

	public List<RecordVo> listRecord() {

		return RecordManager.listRecord();
	}

	public List<RecordVo> todayRecord() {

		return RecordManager.todayRecord();
	}

}
