package com.backend.seqaq.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Document(collection = "answers")
public class AnswerDetail {
    private ObjectId _id;
    private Long aid;
    // Markdown source code of the answer
    private String mdText;

    public AnswerDetail() {
    }

    public AnswerDetail(Long aid, String mdText) {
        this.aid = aid;
        this.mdText = mdText;
    }

    @Id
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @Column(name = "aid")
    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    @Column(name = "md")
    public String getMdText() {
        return mdText;
    }

    public void setMdText(String mdText) {
        this.mdText = mdText;
    }
}
