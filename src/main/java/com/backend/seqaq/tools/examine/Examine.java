package com.backend.seqaq.tools.examine;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.json.JSONObject;

public class Examine {
    String APP_ID = "23116938";
    String API_KEY = "sKk3lVBq3t08U8fiBf2k1vZD";
    String SECRET_KEY = "0c9hhsFTs1Ph8vHAMTwGcbgiijqyNFNa";
    private AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
    public JSONObject forText(String text) {
        JSONObject response = client.textCensorUserDefined(text);
        return response;
    }
}
