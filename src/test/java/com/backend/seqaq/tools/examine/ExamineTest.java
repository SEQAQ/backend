package com.backend.seqaq.tools.examine;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamineTest {
  private Examine examine = new Examine();

  @Test
  void forText() {
    System.out.println(
        examine
            .forText("有没有免费翻墙的软件啊,球球")
            .getJSONArray("data")
            .getJSONObject(0)
            .getJSONArray("hits")
            .getJSONObject(0)
            .getJSONArray("words")
            .toString());
  }

  @Test
  void forimage()
  {
  }
}
