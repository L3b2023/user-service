package com.br.userservice.controllers;

import java.sql.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@RestController
public class DeleteUser {

    @Value("${spring.datasource.url}")
    private String url_db;

    @Value("${spring.datasource.user}")
    private String user_db;

    @Value("${spring.datasource.password}")
    private String password_db;

    @DeleteMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam int id){

        try {
            Connection conn = DriverManager.getConnection(url_db, user_db, password_db);
            String queryDelete = "DELETE FROM userdata WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(queryDelete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return "user " + id + " deleted successfully";
        } catch (SQLException e){
            e.printStackTrace();
            return "failed to delete user with id:" + id;
        }
    }
}