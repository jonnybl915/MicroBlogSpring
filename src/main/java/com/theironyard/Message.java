package com.theironyard;

import javax.persistence.*;

/**
 * Created by jonathandavidblack on 6/20/16.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String text;

    public Message(String text) {
        this.text = text;
    }
    public Message() {
    }

    public Message(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message(Integer id) {
        this.id = id;
    }
}
