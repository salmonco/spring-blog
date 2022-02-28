package com.mycom.slpblog.vo;

import java.util.Date;

public class ReplyVO
{
  private int boardNo;
  private int replyNo;
  private String replyWriter;
  private String replyContent;
  private Date replyDate;
  private int replyCount;

  public ReplyVO()
  {
  }

  public ReplyVO(int boardNo, int replyNo, String replyWriter, String replyContent, Date replyDate, int replyCount)
  {
    this.boardNo = boardNo;
    this.replyNo = replyNo;
    this.replyWriter = replyWriter;
    this.replyContent = replyContent;
    this.replyDate = replyDate;
    this.replyCount = replyCount;
  }

  public int getBoardNo()
  {
    return this.boardNo;
  }

  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }

  public int getReplyNo() {
    return this.replyNo;
  }

  public void setReplyNo(int replyNo) {
    this.replyNo = replyNo;
  }

  public String getReplyWriter() {
    return this.replyWriter;
  }

  public void setReplyWriter(String replyWriter) {
    this.replyWriter = replyWriter;
  }

  public String getReplyContent() {
    return this.replyContent;
  }

  public void setReplyContent(String replyContent) {
    this.replyContent = replyContent;
  }

  public Date getReplyDate() {
    return this.replyDate;
  }

  public void setReplyDate(Date replyDate) {
    this.replyDate = replyDate;
  }

  public int getReplyCount() {
    return this.replyCount;
  }

  public void setReplyCount(int replyCount) {
    this.replyCount = replyCount;
  }
}