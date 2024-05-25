package com.br.userservice.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UpdateUser {

    @Value("${spring.datasource.url}")
    private String url_db;

    @Value("${spring.datasource.user}")
    private String user_db;

    @Value("${spring.datasource.password}")
    private String password_db;

    @PutMapping("/updateUserData")
    @ResponseBody
    public String updateUser(@RequestParam int id, @RequestParam String username, @RequestParam String email) {

        try {
            Connection conn = DriverManager.getConnection(url_db, user_db, password_db);
            String queryUpdateUser = "UPDATE userdata SET username = ?, email = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(queryUpdateUser);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                return "User updated successfully";
            } else {
                return "Failed to update user";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}