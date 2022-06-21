package com.mycom.slpblog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycom.slpblog.service.MainService;
import com.mycom.slpblog.util.FileUtilities;
import com.mycom.slpblog.vo.BoardVO;
import com.mycom.slpblog.vo.MemberVO;
import com.mycom.slpblog.vo.PageVO;
import com.mycom.slpblog.vo.ReplyVO;

@Controller
public class MainController
{

  @Inject
  private MainService mainService;

  @Resource
  private String fileUploadPath;
  private int r = 0;

  /**
   * 알럿창
   * @param notice
   * @param response
   * @throws IOException
   */
  public void alert(String notice, HttpServletResponse response) throws IOException {
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script type='text/javascript'>");
    out.println("alert('" + notice + "');");
    out.println("</script>");
    out.flush();
  }

  /**
   * 로그인 확인
   * @param session
   * @return
   */
  public boolean loginCheck(HttpSession session) {
    if (session.getAttribute("userId") == null) {
      return false;
    }
    return true;
  }

  /**
   * 홈 페이지
   * @return
   */
  @RequestMapping(value = {"/", "home.do"})
  public String home() {
    return "home";
  }

  /**
   * 로그인 페이지
   * @return
   */
  @RequestMapping(value = "login.do")
  public String login() {
    return "login";
  }

  /**
   * 로그인 처리
   * @param req
   * @param response
   * @param session
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "loginProcess.do", method = RequestMethod.POST)
  public String loginProcess(HttpServletRequest req, HttpServletResponse response, 
		  HttpSession session) throws IOException {
    response.setCharacterEncoding("UTF-8");

    String userId = req.getParameter("userId");
    String password = req.getParameter("password");

    int result = mainService.loginProcess(userId, password);

    if (result == 1) {
      session.setAttribute("userId", userId);
      return "home";
    }
    alert("아이디 또는 비밀번호가 올바르지 않습니다.", response);
    return "login";
  }

  /**
   * 로그아웃 처리
   * @param req
   * @return
   */
  @RequestMapping(value = "logout.do")
  public String logout(HttpServletRequest req) {
    HttpSession session = req.getSession();
    session.invalidate();
    return "home";
  }

  /**
   * 회원가입 페이지
   * @return
   */
  @RequestMapping(value = "join.do")
  public String join() {
    return "join";
  }

  /**
   * 회원가입 처리
   * @param req
   * @param response
   * @return
   */
  @RequestMapping(value = "joinProcess.do", method = RequestMethod.POST)
  public String joinProcess(HttpServletRequest req, HttpServletResponse response) {
    try {
      response.setCharacterEncoding("UTF-8");
      MemberVO vo = new MemberVO();

      vo.setName(req.getParameter("name"));
      vo.setUserId(req.getParameter("userId"));
      vo.setPassword(req.getParameter("password"));

      mainService.joinProcess(vo);
      alert("회원가입이 완료되었습니다.", response);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return "login";
  }

  /**
   * 아이디 중복 확인
   * @param vo
   * @return
   */
  @ResponseBody
  @RequestMapping(value= "idCheck.do", method = RequestMethod.POST)
  public int idCheck(MemberVO vo) {
    int result = mainService.idCheck(vo);
    return result;
  }

  /**
   * 게시글 목록 페이지
   * @param pagevo
   * @param model
   * @param nowPage
   * @param cntPerPage
   * @param req
   * @return
   * @throws Exception
   */
  @RequestMapping(value = {"index.do", "search.do"})
  public String board(PageVO pagevo, Model model, String nowPage, String cntPerPage, 
		  HttpServletRequest req) throws Exception {
    String skey = req.getParameter("skey");
    if (skey != null) skey = "%" + skey + "%";

    int total = mainService.cntContent(skey);

    if ((nowPage == null) && (cntPerPage == null)) {
      nowPage = "1";
      cntPerPage = "10";
    } else if (nowPage == null) {
      nowPage = "1";
    } else if (cntPerPage == null) {
      cntPerPage = "10";
    }

    pagevo = new PageVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

    pagevo.setSkey(skey);

    model.addAttribute("paging", pagevo);

    @SuppressWarnings("unchecked")
	List<Map<String, Object>> list = (List<Map<String, Object>>) (List<?>) mainService.viewBoard(pagevo);
    model.addAttribute("list", list);

    return "index";
  }

  /**
   * 글 작성 페이지
   * @param response
   * @param session
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "write.do")
  public String writePage(HttpServletResponse response, HttpSession session) 
		  throws IOException {
    response.setCharacterEncoding("UTF-8");
    if (!loginCheck(session)) {
      alert("로그인이 필요합니다.", response);
      return "login";
    }

    return "write";
  }

  /**
   * 글 작성 처리
   * @param req
   * @param session
   * @param response
   * @param multipartRequest
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "writeProcess.do", method = RequestMethod.POST)
  public String writeProcess(HttpServletRequest req, HttpSession session, HttpServletResponse response, 
		  MultipartHttpServletRequest multipartRequest) throws Exception {
    response.setCharacterEncoding("UTF-8");

    if (Boolean.parseBoolean(req.getParameter("editorStatus"))) {
      alert("등록에 실패하였습니다.\n최대 500 Byte까지 입력가능합니다.", response);
      return "home";
    }

    BoardVO vo = new BoardVO();
    vo.setBoardTitle(req.getParameter("boardTitle"));
    vo.setBoardContent(req.getParameter("boardContent"));
    vo.setUserId((String)session.getAttribute("userId"));

    MultipartFile file1 = multipartRequest.getFile("file1");
    MultipartFile file2 = multipartRequest.getFile("file2");

    if (!file1.isEmpty()) {
      String savedFileName1 = FileUtilities.uploadFile(file1, fileUploadPath);
      vo.setBoardFile1(savedFileName1);
    }

    if (!file2.isEmpty()) {
      String savedFileName2 = FileUtilities.uploadFile(file2, fileUploadPath);
      vo.setBoardFile2(savedFileName2);
    }

    mainService.writeProcess(vo);

    return "redirect:/index.do";
  }

  /**
   * 글 내용 보기 페이지
   * @param model
   * @param boardNo
   * @param session
   * @param response
   * @param req
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "viewContent.do", method = RequestMethod.GET)
  public String viewContent(Model model, int boardNo, HttpSession session, 
		  HttpServletResponse response, HttpServletRequest req) throws IOException {
    response.setCharacterEncoding("UTF-8");
    if (!loginCheck(session)) {
      alert("로그인이 필요합니다.", response);
      return "login";
    }

    Cookie[] cookies = req.getCookies();
    Cookie viewCookie = null;

    if ((cookies != null) && (cookies.length > 0)) {
      for (Cookie cookie : cookies)
      {
        if (cookie.getName().equals("cookie" + boardNo))
        {
          viewCookie = cookie;
        }
      }
    }

    if (viewCookie == null)
    {
      Cookie newCookie = new Cookie("cookie" + boardNo, "|" + boardNo + "|");
      response.addCookie(newCookie);
      mainService.updateHit(boardNo);
    }

    model.addAttribute("content", mainService.viewContent(boardNo));

    @SuppressWarnings("unchecked")
	List<Map<String, Object>> list = (List<Map<String, Object>>) (List<?>) mainService.viewReply(boardNo);
    model.addAttribute("reply", list);

    String userId = (String)session.getAttribute("userId");
    HashMap<String, Object> hashmap = new HashMap<String, Object>();
    hashmap.put("boardNo", boardNo);
    hashmap.put("userId", userId);

    int like = mainService.checkLike(hashmap);
    int likeCnt = mainService.likeCnt(boardNo);

    model.addAttribute("like", Integer.valueOf(like));
    model.addAttribute("likeCnt", Integer.valueOf(likeCnt));

    return "viewContent";
  }

  /**
   * 글 수정 페이지
   * @param model
   * @param boardNo
   * @return
   */
  @RequestMapping(value = "updateContent.do", method = RequestMethod.GET)
  public String updateContent(Model model, int boardNo) {
    model.addAttribute("content", mainService.viewContent(boardNo));
    return "update";
  }

  @RequestMapping(value = "updateProcess.do", method = RequestMethod.POST)
  public String updateProcess(HttpServletRequest req, HttpSession session, HttpServletResponse response, 
		  MultipartHttpServletRequest multipartRequest) throws Exception {
    response.setCharacterEncoding("UTF-8");

    if (Boolean.parseBoolean(req.getParameter("editorStatus"))) {
      alert("등록에 실패하였습니다.\n최대 500 Byte까지 입력가능합니다.", response);
      return "home";
    }

    BoardVO vo = new BoardVO();
    vo.setBoardTitle(req.getParameter("boardTitle"));
    vo.setBoardContent(req.getParameter("boardContent"));
    int boardNo = Integer.parseInt(req.getParameter("boardNo"));
    vo.setBoardNo(boardNo);

    MultipartFile file1 = multipartRequest.getFile("file1");
    MultipartFile file2 = multipartRequest.getFile("file2");

    if (!file1.isEmpty()) {
      String savedFileName1 = FileUtilities.uploadFile(file1, fileUploadPath);
      vo.setBoardFile1(savedFileName1);
    }

    if (!file2.isEmpty()) {
      String savedFileName2 = FileUtilities.uploadFile(file2, fileUploadPath);
      vo.setBoardFile2(savedFileName2);
    }

    mainService.updateProcess(vo);

    return "redirect:/index.do";
  }

  /**
   * 글 삭제 처리
   * @param boardNo
   * @param session
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "deleteProcess.do", method = RequestMethod.GET)
  public String deleteProcess(int boardNo, HttpSession session) throws IOException {
    mainService.deleteProcess(boardNo);
    
    String userId = (String)session.getAttribute("userId");
    HashMap<String, Object> hashmap = new HashMap<String, Object>();
    hashmap.put("boardNo", boardNo);
    hashmap.put("userId", userId);

    mainService.deleteLikeInfo(hashmap);
    
    return "redirect:/index.do";
  }

  /**
   * 게시글 좋아요 처리
   * @param boardNo
   * @param userId
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "boardLike.do", method = RequestMethod.POST)
  public HashMap<String, Object> boardLike(int boardNo, String userId) {
    HashMap<String, Object> hashmap = new HashMap<String, Object>();
    hashmap.put("boardNo", boardNo);
    hashmap.put("userId", userId);

    int result = mainService.checkLike(hashmap);

    if (result == 1) {
      mainService.downLike(boardNo);
      mainService.deleteLikeInfo(hashmap);
      result = 0;
    } else {
      mainService.upLike(boardNo);
      mainService.insertLikeInfo(hashmap);
      result = 1;
    }
    int likeCnt = mainService.likeCnt(boardNo);

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("likeCnt", likeCnt);
    map.put("result", result);

    return map;
  }

  /**
   * 첨부파일 업로드 처리 
   * @param req
   * @param response
   * @param multipartRequest
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "fileUpload.do", method = RequestMethod.POST)
  @ResponseBody
  public String fileUploadFromCKEditor(HttpServletRequest req, HttpServletResponse response, 
		  MultipartHttpServletRequest multipartRequest) throws Exception {
    PrintWriter printWriter = null;

    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    try
    {
      String uploadPath = req.getSession().getServletContext().getRealPath("/") + "/resources/ckeditor/images/";
      String fileName = FileUtilities.uploadFile(multipartRequest.getFile("upload"), uploadPath);

      String fileUrl = req.getContextPath() + "/resources/ckeditor/images/" + fileName;

      String callback = req.getParameter("CKEditorFuncNum");
      System.out.println("callback=" + callback);
      printWriter = response.getWriter();

      if (!callback.equals("1")) {
        printWriter.println("<script>alert('이미지 업로드에 실패했습니다.');</script>");
      }
      else {
        printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl + "','이미지가 업로드되었습니다.')" + "</script>");
      }

      printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
      printWriter.flush();
    }
    catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (printWriter != null) {
        printWriter.close();
      }
    }

    return null;
  }

  /**
   * 첨부파일 다운로드 처리
   * @param fileName
   * @param request
   * @param response
   */
  @RequestMapping(value = "fileDownload.do")
  public void ckSubmit(@RequestParam("fileName") String fileName, HttpServletRequest request, 
		  HttpServletResponse response) {
    File file = FileUtilities.getDownloadFile(fileUploadPath, fileName);
    try {
      byte[] data = FileUtils.readFileToByteArray(file);

      response.setContentLength(data.length);
      response.setHeader("Content-Transfer-Encoding", "binary");
      response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");

      response.getOutputStream().write(data);
      response.getOutputStream().flush();
      response.getOutputStream().close();
    }
    catch (IOException e) {
      throw new RuntimeException("파일 다운로드에 실패하였습니다.");
    } catch (Exception e) {
      throw new RuntimeException("시스템에 문제가 발생하였습니다.");
    }
  }

  /**
   * 댓글 작성 페이지
   * @param redirectAttributes
   * @param req
   * @param session
   * @param response
   * @param boardNo
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "writeReply.do", method = RequestMethod.POST)
  public String writeReply(RedirectAttributes redirectAttributes, HttpServletRequest req, HttpSession session, 
		  HttpServletResponse response, int boardNo) throws Exception {
    response.setCharacterEncoding("UTF-8");
    if (!loginCheck(session)) {
      alert("로그인이 필요합니다.", response);
      return "login";
    }

    ReplyVO vo = new ReplyVO();
    vo.setBoardNo(boardNo);
    vo.setReplyWriter((String)session.getAttribute("userId"));
    vo.setReplyContent(req.getParameter("replyContent"));

    mainService.writeReply(vo);
    redirectAttributes.addAttribute("boardNo", Integer.valueOf(boardNo));

    return "redirect:/viewContent.do";
  }

  /**
   * 댓글 수정 페이지
   * @param model
   * @param session
   * @param req
   * @param response
   * @param boardNo
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "updateReply.do", method = RequestMethod.GET)
  public String updateReply(Model model, HttpSession session, HttpServletRequest req, 
		  HttpServletResponse response, int boardNo) throws IOException {
    response.setCharacterEncoding("UTF-8");
    if (!loginCheck(session)) {
      alert("로그인이 필요합니다.", response);
      return "login";
    }

    int replyNo = Integer.parseInt(req.getParameter("replyNo"));

    model.addAttribute("content", mainService.viewContent(boardNo));
    model.addAttribute("reply", mainService.viewReply(boardNo));
    model.addAttribute("update", Integer.valueOf(replyNo));

    String userId = (String)session.getAttribute("userId");
    HashMap<String, Object> hashmap = new HashMap<String, Object>();
    hashmap.put("boardNo", boardNo);
    hashmap.put("userId", userId);

    int like = mainService.checkLike(hashmap);
    int likeCnt = mainService.likeCnt(boardNo);

    model.addAttribute("like", like);
    model.addAttribute("likeCnt", likeCnt);

    return "updateReply";
  }

  /**
   * 댓글 수정 처리
   * @param redirectAttributes
   * @param req
   * @param response
   * @param locale
   * @param model
   * @param replyNo
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "updateReplyPro.do", method = RequestMethod.POST)
  public String updateReplyPro(RedirectAttributes redirectAttributes, HttpServletRequest req, 
		  HttpServletResponse response, Locale locale, Model model, int replyNo) throws IOException {
    ReplyVO vo = new ReplyVO();
    vo.setReplyNo(Integer.parseInt(req.getParameter("replyNo")));
    vo.setReplyContent(req.getParameter("updateReply"));
    int boardNo = Integer.parseInt(req.getParameter("boardNo"));

    mainService.updateReply(vo);
    redirectAttributes.addAttribute("boardNo", Integer.valueOf(boardNo));

    return "redirect:/viewContent.do";
  }

  /**
   * 댓글 삭제 처리
   * @param session
   * @param redirectAttributes
   * @param response
   * @param req
   * @param model
   * @param boardNo
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "deleteReply.do", method = RequestMethod.GET)
  public String deleteReply(HttpSession session, RedirectAttributes redirectAttributes, 
		  HttpServletResponse response, HttpServletRequest req, Model model, int boardNo) throws IOException {
    response.setCharacterEncoding("UTF-8");
    if (!loginCheck(session)) {
      alert("로그인이 필요합니다.", response);
      return "login";
    }

    redirectAttributes.addAttribute("boardNo", Integer.valueOf(boardNo));

    int replyNo = Integer.parseInt(req.getParameter("replyNo"));

    mainService.deleteReply(replyNo);
    return "redirect:/viewContent.do";
  }

  /**
   * 유저 프로필 보기 페이지
   * @param userId
   * @param response
   * @param session
   * @param model
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "profile.do")
  public String profile(@RequestParam("userId") String userId, HttpServletResponse response, 
		  HttpSession session, Model model) throws IOException {
    response.setCharacterEncoding("UTF-8");
    if (!loginCheck(session)) {
      alert("로그인이 필요합니다.", response);
      return "login";
    }

    String profileName = mainService.selectProfile(userId);

    model.addAttribute("profileName", profileName);
    model.addAttribute("userId", userId);

    return "profile";
  }

  /**
   * 유저 프로필 사진 업로드 처리
   * @param req
   * @param redirectAttributes
   * @param multipartRequest
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "profileUpload.do", method = RequestMethod.POST)
  public String profileUpload(HttpServletRequest req, RedirectAttributes redirectAttributes, 
		  MultipartHttpServletRequest multipartRequest) throws Exception {
    String userId = req.getParameter("userId");
    try
    {
      String uploadPath = req.getSession().getServletContext().getRealPath("/") + "resources\\img\\";

      String profileName = FileUtilities.uploadFile(multipartRequest.getFile("profile"), uploadPath);

      mainService.updateProfile(userId, profileName);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    redirectAttributes.addAttribute("userId", userId);

    return "redirect:/profile.do";
  }

  /**
   * 유저 프로필 사진 보기 처리
   * @param userId
   * @param model
   * @return
   */
  @RequestMapping(value = "getProfileName.do", method = RequestMethod.POST)
  public String getProfileName(String userId, Model model) {
    String profileName = mainService.selectProfile(userId);

    model.addAttribute("profileName", profileName);

    return "common/viewProfile";
  }

  /**
   * 채팅 페이지
   * @param session
   * @param response
   * @param model
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "chat.do")
  public String chat(HttpSession session, HttpServletResponse response, Model model) 
		  throws IOException {
    response.setCharacterEncoding("UTF-8");
    if (!loginCheck(session)) {
      alert("로그인이 필요합니다.", response);
      return "login";
    }

    model.addAttribute("userId", (String)session.getAttribute("userId"));

    return "chat";
  }

  /**
   * 신고 처리
   * @param reportDate
   * @param reportTarget
   * @param reportTitle
   * @param reportContent
   * @param req
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "report.do", method = RequestMethod.POST)
  public String report(String reportDate, String reportTarget, String reportTitle, 
		  String reportContent, HttpServletRequest req) throws IOException {
    String path = "D:\\jisu\\report.xlsx";
    FileInputStream fis = new FileInputStream(path);

    XSSFWorkbook workbook = new XSSFWorkbook(fis);

    String sheetName = "report";

    XSSFSheet sheet = workbook.getSheet(sheetName);

    XSSFRow row = sheet.createRow(r);
    XSSFCell cell0 = row.createCell(0);
    XSSFCell cell1 = row.createCell(1);
    XSSFCell cell2 = row.createCell(2);
    XSSFCell cell3 = row.createCell(3);
    cell0.setCellValue(reportDate);
    cell1.setCellValue(reportTarget);
    cell2.setCellValue(reportTitle);
    cell3.setCellValue(reportContent);
    try
    {
      FileOutputStream fos = new FileOutputStream(path);
      workbook.write(fos);
      fos.close();
      r += 1;
      System.out.println("r=" + r);
    }
    catch (IOException e) {
      e.printStackTrace();
      System.out.println("엑셀파일 쓰기 실패");
    }

    String referer = req.getHeader("Referer");
    return "redirect:" + referer;
  }

  /**
   * 좋아요 수에 따른 게시글 랭킹
   * @param model
   * @return
   */
  @RequestMapping(value = "rank.do")
  public String rank(Model model) {
    List<Map<String, Object>> likeRank = (List<Map<String, Object>>) mainService.likeRank();
    model.addAttribute("likeRank", likeRank);

    return "rank";
  }
}