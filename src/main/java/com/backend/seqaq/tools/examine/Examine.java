package com.backend.seqaq.tools.examine;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.json.JSONObject;

import java.util.Base64;

public class Examine {
  String APP_ID = "23116938";
  String API_KEY = "sKk3lVBq3t08U8fiBf2k1vZD";
  String SECRET_KEY = "0c9hhsFTs1Ph8vHAMTwGcbgiijqyNFNa";
  private AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

  public JSONObject forText(String text) {
    JSONObject response = client.textCensorUserDefined(text);
    return response;
  }

  public JSONObject forImage(String image) {
    String head = "data:image/";
    boolean flag = image.startsWith(head);
    if (flag == true) {
      image = image.substring(head.length());
      while (!image.startsWith("base64,")) {
        image = image.substring(1);
      }
      image = image.substring(7);
    }
    Base64.Decoder decoder = Base64.getDecoder();
    byte[] bytes = decoder.decode(image);
    for (int i = 0; i < bytes.length; ++i) {
      if (bytes[i] < 0) { // 调整异常数据
        bytes[i] += 256;
      }
    }
    JSONObject response = client.imageCensorUserDefined(bytes, null);
    return response;
  }
}
