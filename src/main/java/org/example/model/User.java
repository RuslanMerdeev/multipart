package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="login", unique = true, nullable = false)
    private String login;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="account", unique = true, nullable = false)
    private String account;

    @Column(name="name", nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="timestamptz", nullable = false)
    private Date timestamp = new Date();
}
