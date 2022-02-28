package com.mycom.slpblog.mapper;

import com.mycom.slpblog.vo.BoardVO;
import com.mycom.slpblog.vo.MemberVO;
import com.mycom.slpblog.vo.PageVO;
import com.mycom.slpblog.vo.ReplyVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper
{
  public int loginProcess(String paramString1, String paramString2);

  public int idCheck(MemberVO paramMemberVO);

  public void joinProcess(MemberVO paramMemberVO);

  public int cntContent(String paramString);

  public List<BoardVO> viewBoard(PageVO paramPageVO);

  public void writeProcess(BoardVO paramBoardVO);

  public BoardVO viewContent(int paramInt);

  public void updateProcess(BoardVO paramBoardVO);

  public void deleteProcess(int paramInt);

  public void writeReply(ReplyVO paramReplyVO);

  public List<ReplyVO> viewReply(int paramInt);

  public void updateReply(ReplyVO paramReplyVO);

  public void deleteReply(int paramInt);

  public void updateHit(int paramInt);

  public void deleteAllReplies(int paramInt);

  public void updateProfile(String paramString1, String paramString2);

  public String selectProfile(String paramString);

  public int checkLike(HashMap<String, Object> paramHashMap);

  public void upLike(int paramInt);

  public void insertLikeInfo(HashMap<String, Object> paramHashMap);

  public void downLike(int paramInt);

  public void deleteLikeInfo(HashMap<String, Object> paramHashMap);

  public int likeCnt(int paramInt);

  public List<Map<String, Object>> likeRank();
}