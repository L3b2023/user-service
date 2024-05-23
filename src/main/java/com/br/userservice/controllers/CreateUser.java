package com.br.userservice.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {

    @Value("${spring.datasource.url}")
    private String url_db;

    @Value("${spring.datasource.user}")
    private String user_db;

    @Value("${spring.datasource.password}")
    private String password_db;

    @PostMapping("/createUser")
    @ResponseBody
    public String createUser(@RequestParam String username, @RequestParam String email){

        try {
            Connection conn = DriverManager.getConnection(url_db, user_db, password_db);
            String queryCreate = "INSERT INTO userdata (username, email) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(queryCreate, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }

            conn.close();
            return "user created successfully with id: " + userId;
        } catch (SQLException e){
            e.printStackTrace();
            return "failed to create user";
        }
    }
}