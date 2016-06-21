package com.theironyard;

import javax.persistence.*;

/**
 * Created by jonathandavidblack on 6/20/16.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {

    }
}

