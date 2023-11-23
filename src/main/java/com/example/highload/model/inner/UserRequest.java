package com.example.highload.model.inner;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "user_rq", schema = "public")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "login", nullable = false, unique = true)
    String login;

    @NotBlank
    @Column(name = "hash_password", nullable = false)
    String hashPassword;

}