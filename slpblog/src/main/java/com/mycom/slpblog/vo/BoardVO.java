package com.mycom.slpblog.vo;

import java.util.Date;

public class BoardVO
{
  private int boardNo;
  private String boardTitle;
  private String boardContent;
  private String userId;
  private int boardCount;
  private Date boardDate;
  private String boardFile1;
  private String boardFile2;
  private int replyCount;
  private int boardHit;

  public BoardVO()
  {
  }

  public BoardVO(int boardNo, String boardTitle, String boardContent, String userId, int boardCount, Date boardDate, int replyCount)
  {
    this.boardNo = boardNo;
    this.boardTitle = boardTitle;
    this.boardContent = boardContent;
    this.userId = userId;
    this.boardCount = boardCount;
    this.boardDate = boardDate;
    this.replyCount = replyCount;
  }

  public int getBoardNo() {
    return this.boardNo;
  }

  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }

  public String getBoardTitle() {
    return this.boardTitle;
  }

  public void setBoardTitle(String boardTitle) {
    this.boardTitle = boardTitle;
  }

  public String getBoardContent() {
    return this.boardContent;
  }

  public void setBoardContent(String boardContent) {
    this.boardContent = boardContent;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getBoardCount() {
    return this.boardCount;
  }

  public void setBoardCount(int boardCount) {
    this.boardCount = boardCount;
  }

  public Date getBoardDate() {
    return this.boardDate;
  }

  public void setBoardDate(Date boardDate) {
    this.boardDate = boardDate;
  }

  public String toString()
  {
    return "BoardVO [boardNo=" + this.boardNo + ", boardTitle=" + this.boardTitle + ", boardContent=" + this.boardContent + 
      ", userId=" + this.userId + ", boardCount=" + this.boardCount + ", boardDate=" + this.boardDate + "]";
  }

  public String getBoardFile1() {
    return this.boardFile1;
  }

  public void setBoardFile1(String boardFile1) {
    this.boardFile1 = boardFile1;
  }

  public String getBoardFile2() {
    return this.boardFile2;
  }

  public void setBoardFile2(String boardFile2) {
    this.boardFile2 = boardFile2;
  }

  public int getReplyCount()
  {
    return this.replyCount;
  }

  public void setReplyCount(int replyCount) {
    this.replyCount = replyCount;
  }

  public int getBoardHit() {
    return this.boardHit;
  }

  public void setBoardHit(int boardHit) {
    this.boardHit = boardHit;
  }
}