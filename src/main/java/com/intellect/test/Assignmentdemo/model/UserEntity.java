package com.intellect.test.Assignmentdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USERINFO")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="pincode", nullable=false)
    private int pincode;

    @Column(name="birthdate", nullable=false)
    private String birthdate;

}
