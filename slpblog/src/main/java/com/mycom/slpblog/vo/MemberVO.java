package com.mycom.slpblog.vo;

public class MemberVO
{
  private String name;
  private String userId;
  private String password;

  public MemberVO()
  {
  }

  public MemberVO(String name, String userId, String password)
  {
    this.name = name;
    this.userId = userId;
    this.password = password;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}