package com.bit.campfire.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bit.campfire.db.BoardManager;
import com.bit.campfire.vo.BoardVo;
import com.bit.campfire.vo.CommentsVo;

@Repository
public class BoardDao {

	public List<BoardVo> listBoard(HashMap map){
		
		return BoardManager.listBoard(map);
	}

	public BoardVo getBoard(HashMap map) {

		return BoardManager.getBoard(map);
	}

	public int getBoardCnt() {

		return BoardManager.getBoardCnt();
	}

	public int insertBoard(BoardVo vo) {

		return BoardManager.insertBoard(vo);
	}

	public int updateBoard(BoardVo vo) {

		return BoardManager.updateBoard(vo);
	}

	public int deleteBoard(HashMap map) {

		return BoardManager.deleteBoard(map);
	}

	public int getCno() {

		return BoardManager.getCno();
	}

	public CommentsVo getComment(HashMap map) {

		return BoardManager.getComment(map);
	}

	public void updateStep(HashMap map) {

		BoardManager.updateStep(map);
	}

	public int insertCommets(CommentsVo vo) {
		
		return BoardManager.insertCommets(vo);
	}

	public List<CommentsVo> listComments(HashMap map) {

		return BoardManager.listComments(map);
	}

	public int getCommentNum(HashMap map) {
		
		return BoardManager.getCommentNum(map);
	}

	public int updateRC(HashMap map) {
		
		return BoardManager.updateRC(map);
	}
	
	public int updateDC(HashMap map) {
		
		return BoardManager.updateDC(map);
	}
	
	public int updateRP(HashMap map) {
		
		return BoardManager.updateRP(map);
	}

	public List<BoardVo> listAdminBoard() {
		
		return BoardManager.listAdminBoard();
	}

	public int resetReport(HashMap map) {
		
		return BoardManager.resetReport(map);
	}
	
}
