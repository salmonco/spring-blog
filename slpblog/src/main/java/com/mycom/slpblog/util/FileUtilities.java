package com.mycom.slpblog.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class FileUtilities
{
  public static String uploadFile(MultipartFile multipartFile, String uploadPath)
    throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException
  {
    verifyUploadPath(uploadPath);

    String origFilename = multipartFile.getOriginalFilename();
    if ((origFilename == null) || ("".equals(origFilename))) return null;

    String filename = getUuidFileName(origFilename);
    byte[] bytes = multipartFile.getBytes();

    String filePath = uploadPath + filename;
    System.out.println("filePath=" + filePath);
    OutputStream outStr = new FileOutputStream(new File(filePath));

    outStr.write(bytes);
    outStr.close();

    return filename;
  }

  private static void verifyUploadPath(String path) {
    if (!new File(path).exists())
      try {
        new File(path).mkdir();
      } catch (Exception e) {
        e.getStackTrace();
      }
  }

  public static File getDownloadFile(String uploadPath, String fileName)
  {
    return new File(uploadPath + fileName);
  }

  public static String getUuidFileName(String filename)
  {
    UUID uuid = UUID.randomUUID();
    StringBuilder sb = new StringBuilder();
    sb.append(FilenameUtils.getBaseName(filename))
      .append("_").append(uuid).append(".").append(FilenameUtils.getExtension(filename));

    return sb.toString();
  }

  public static MediaType getMediaType(String filename)
  {
    String contentType = FilenameUtils.getExtension(filename);
    MediaType mediaType = null;

    if (contentType.equals("png"))
      mediaType = MediaType.IMAGE_PNG;
    else if ((contentType.equals("jpeg")) || (contentType.equals("jpg")))
      mediaType = MediaType.IMAGE_JPEG;
    else if (contentType.equals("gif")) {
      mediaType = MediaType.IMAGE_GIF;
    }

    return mediaType;
  }
}