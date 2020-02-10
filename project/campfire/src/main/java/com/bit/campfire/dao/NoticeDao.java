package com.bit.campfire.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bit.campfire.db.NoticeManager;
import com.bit.campfire.vo.NoticeVo;

@Repository
public class NoticeDao {

	public int updateNotice(NoticeVo vo) {
		return NoticeManager.updateNotice(vo);
	}
	
	public NoticeVo getBoard(HashMap map) {
		return NoticeManager.getNotice(map);
	}
	
	public int insertNotice(NoticeVo vo) {
		return NoticeManager.insertNotice(vo);
	}
	public int deleteNotice(HashMap map) {
		return NoticeManager.deleteNotice(map);
	}
	
	public NoticeVo getNotice(HashMap map) {

		return NoticeManager.getNotice(map);
	}
	public List<NoticeVo> listNotice(HashMap map){
		return NoticeManager.listNotice(map);
	}
	
	public int getNoticeCnt() {
		return NoticeManager.getNoticeCnt();
	}
	
}
