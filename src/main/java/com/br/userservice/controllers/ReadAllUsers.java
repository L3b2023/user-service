package com.br.userservice.controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadAllUsers {

    @Value("${spring.datasource.url}")
    private String url_db;

    @Value("${spring.datasource.user}")
    private String user_db;

    @Value("${spring.datasource.password}")
    private String password_db;

    @GetMapping("/readAllUsers")
    @ResponseBody
    public List<String> read(){

        List<String> results = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url_db, user_db, password_db);
            String queryReadAll = "SELECT * FROM userdata;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryReadAll);

            while (rs.next()) {
                String rowDataId = rs.getString("id");
                String rowDataUsername = rs.getString("username");
                String rowDataEmail = rs.getString("email");
                String rowData = "id: " + rowDataId + ", username: " + rowDataUsername + ", email: " + rowDataEmail;
                results.add(rowData);
            }

            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return results;
    }
}