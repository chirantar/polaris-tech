package com.example.polaris.models;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="user_name")
    private String userName;
    private String password;
    private String name;
}
