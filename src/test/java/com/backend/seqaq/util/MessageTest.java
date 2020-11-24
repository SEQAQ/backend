package com.backend.seqaq.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageTest {
  @Test
  void customizeAll() {
    Message<Message<String>> msg = new Message<>();
    msg.setCode(7);
    msg.setMessage("You shall not pass!");
    Message<String> nestedMsg = new Message<>("Frodo");
    msg.setData(nestedMsg);
    assertNotNull(msg);
    assertNotNull(msg.getData());
    assertEquals(msg.getData().getData(), "Frodo");
  }

  @Test
  void codeTest() {
    Message<Object> msg = new Message<>();
    msg.setCode(2333);
    assertEquals(2333, msg.getCode());
  }

  @Test
  void messageTest() {
    String rick = "wubba lubba dub dub";
    Message<Object> msg = new Message<>(5, rick);
    assertEquals(rick, msg.getMessage());
    assertEquals(5, msg.getCode());
  }

  @Test
  void dataTest() {
    Message<String> msg = new Message<>(5800, "X", "___, YES!");
    assertEquals(msg.getData(), "___, YES!");
    String xia = "二号去听经，晚上住旅店。三号去餐厅，然后看电影。";
    Message<String> xiaMsg = new Message<>(xia);
    assertEquals(xia, xiaMsg.getData());
  }
}
