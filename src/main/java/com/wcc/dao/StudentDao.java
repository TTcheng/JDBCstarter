package com.wcc.dao;

import com.wcc.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    public boolean insert(Connection connection, Student student) throws SQLException;
    public boolean update(Connection connection, Student student) throws SQLException;
    public boolean delete(Connection connection, String id) throws SQLException;
    public Student select(Connection connection, String id) throws SQLException;
    public List<Student> selectAll(Connection connection) throws SQLException;
}
