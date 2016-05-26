package com.goldenweb.beetl;

import java.io.File;
import java.io.IOException;

import sun.misc.IOUtils;


public class FileUtils {
  
  public static boolean createFile(File file) throws IOException {
    if (!file.exists()) {
      makeDir(file.getParentFile());
    }else{
      file.getAbsoluteFile().delete();
    }
    return file.createNewFile();
  }

  public static void makeDir(File dir) {
    if (!dir.getParentFile().exists()) {
      makeDir(dir.getParentFile());
    }
    dir.mkdir();
  }
}
