package com.bit.campfire.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.bit.campfire.db.RecordManager;
import com.bit.campfire.vo.DataVo;

@Repository
public class TempDao {

	public int insertTempVisit() {
		
		return RecordManager.insertTempVisit();
	}
	
	public int getTodayVisit() {
		
		return RecordManager.getTodayVisit();
	}
	
	public int insertTempPlay() {
		
		return RecordManager.insertTempPlay();
	}
	
	public int getTodayPlay() {
		
		return RecordManager.getTodayPlay();
	}
	
	public int deleteTemp() {
		
		return RecordManager.deleteTemp();
	}
	

	public int insertData(HashMap map) {
		
		return RecordManager.insertData(map);
	}
	
}
