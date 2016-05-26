package com.goldenweb.beetl.text;

import java.io.Serializable;

public class Commodity implements Serializable {
  
  private String name;
  
  private String path;
  
  public Commodity(String name, String path) {
    this.name = name;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
