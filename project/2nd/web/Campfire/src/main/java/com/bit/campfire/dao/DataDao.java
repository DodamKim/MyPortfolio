package com.bit.campfire.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bit.campfire.db.DataManager;
import com.bit.campfire.vo.DataVo;

@Repository
public class DataDao {

	public int getTotalPlay() {
		return DataManager.getTotalPlay();
	}

	public int getTotalVisit() {
		return DataManager.getTotalVisit();
	}

	public List<DataVo> dataList(HashMap map) {
		// TODO Auto-generated method stub
		return DataManager.dataList(map);
	}
}
