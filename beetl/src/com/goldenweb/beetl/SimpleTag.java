package com.goldenweb.beetl;

import java.io.IOException;
import java.util.Map;

import org.beetl.core.Tag;

public class SimpleTag extends Tag {

  public void render() {
    String tagName = (String) this.args[0];
    Map attrs = (Map)this.args[1];
    String value = (String) attrs.get("attr");
    try {
      this.ctx.byteWriter.writeString(value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
