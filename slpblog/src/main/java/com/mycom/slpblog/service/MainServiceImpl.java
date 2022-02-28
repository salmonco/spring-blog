package com.mycom.slpblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycom.slpblog.mapper.MainMapper;
import com.mycom.slpblog.vo.BoardVO;
import com.mycom.slpblog.vo.MemberVO;
import com.mycom.slpblog.vo.PageVO;
import com.mycom.slpblog.vo.ReplyVO;

@Service
public class MainServiceImpl implements MainService {
	
	@Resource
	private MainMapper mainMapper;

	@Override
	public int loginProcess(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return mainMapper.loginProcess(paramString1, paramString2);
	}

	@Override
	public int idCheck(MemberVO paramMemberVO) {
		// TODO Auto-generated method stub
		return mainMapper.idCheck(paramMemberVO);
	}

	@Override
	public void joinProcess(MemberVO paramMemberVO) {
		// TODO Auto-generated method stub
		mainMapper.joinProcess(paramMemberVO);
	}

	@Override
	public int cntContent(String paramString) {
		// TODO Auto-generated method stub
		return mainMapper.cntContent(paramString);
	}

	@Override
	public List<BoardVO> viewBoard(PageVO paramPageVO) {
		// TODO Auto-generated method stub
		return mainMapper.viewBoard(paramPageVO);
	}

	@Override
	public void writeProcess(BoardVO paramBoardVO) {
		// TODO Auto-generated method stub
		mainMapper.writeProcess(paramBoardVO);
	}

	@Override
	public BoardVO viewContent(int paramInt) {
		// TODO Auto-generated method stub
		return mainMapper.viewContent(paramInt);
	}

	@Override
	public void updateProcess(BoardVO paramBoardVO) {
		// TODO Auto-generated method stub
		mainMapper.updateProcess(paramBoardVO);
	}

	@Override
	public void deleteProcess(int paramInt) {
		// TODO Auto-generated method stub
		mainMapper.deleteProcess(paramInt);
	}

	@Override
	public void writeReply(ReplyVO paramReplyVO) {
		// TODO Auto-generated method stub
		mainMapper.writeReply(paramReplyVO);
	}

	@Override
	public List<ReplyVO> viewReply(int paramInt) {
		// TODO Auto-generated method stub
		return mainMapper.viewReply(paramInt);
	}

	@Override
	public void updateReply(ReplyVO paramReplyVO) {
		// TODO Auto-generated method stub
		mainMapper.updateReply(paramReplyVO);
	}

	@Override
	public void deleteReply(int paramInt) {
		// TODO Auto-generated method stub
		mainMapper.deleteReply(paramInt);
	}

	@Override
	public void updateHit(int paramInt) {
		// TODO Auto-generated method stub
		mainMapper.updateHit(paramInt);
	}

	@Override
	public void deleteAllReplies(int paramInt) {
		// TODO Auto-generated method stub
		mainMapper.deleteAllReplies(paramInt);
	}

	@Override
	public void updateProfile(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		mainMapper.updateProfile(paramString1, paramString2);
	}

	@Override
	public String selectProfile(String paramString) {
		// TODO Auto-generated method stub
		return mainMapper.selectProfile(paramString);
	}

	@Override
	public int checkLike(HashMap<String, Object> paramHashMap) {
		// TODO Auto-generated method stub
		return mainMapper.checkLike(paramHashMap);
	}

	@Override
	public void upLike(int paramInt) {
		// TODO Auto-generated method stub
		mainMapper.upLike(paramInt);
	}

	@Override
	public void insertLikeInfo(HashMap<String, Object> paramHashMap) {
		// TODO Auto-generated method stub
		mainMapper.insertLikeInfo(paramHashMap);
	}

	@Override
	public void downLike(int paramInt) {
		// TODO Auto-generated method stub
		mainMapper.downLike(paramInt);
	}

	@Override
	public void deleteLikeInfo(HashMap<String, Object> paramHashMap) {
		// TODO Auto-generated method stub
		mainMapper.deleteLikeInfo(paramHashMap);
	}

	@Override
	public int likeCnt(int paramInt) {
		// TODO Auto-generated method stub
		return mainMapper.likeCnt(paramInt);
	}

	@Override
	public List<Map<String, Object>> likeRank() {
		// TODO Auto-generated method stub
		return mainMapper.likeRank();
	}
	
}