package com.bit.campfire.data;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bit.campfire.dao.TempDao;
import com.bit.campfire.vo.DataVo;

@Component
public class DataComponent {
	
	@Autowired
	TempDao dao;

	
	@Scheduled(cron="59 59 23 * * *")
	public void updateData() {

		int visit = dao.getTodayVisit();
		int play = dao.getTodayPlay();
		System.out.println("오늘의 총 방문자 수");
		
		HashMap map = new HashMap();
		map.put("visit", visit);
		map.put("play", play);
		
		int r = dao.insertData(map);
		
		if( r == 1 ) {
			dao.deleteTemp();
			System.out.println("오늘의 데이터, 기록 후  삭제");
		}
		
	}
	
//	@Scheduled(cron="30 47 * * * *")
//	public void test() {
//		
//		System.out.println("콜콜");
//		
//	}
	
}
