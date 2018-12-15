package com.wcc.dao.impl;

import com.wcc.dao.StudentDao;
import com.wcc.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    public boolean insert(Connection connection, Student student) throws SQLException {
        boolean status = false;
//        String sql = "INSERT INTO STUDENT(id,`name`,gender,age) VALUES(?,?,?,?)";
        String sql = "INSERT INTO STUDENT(id, `name`, gender, age) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getId());
        statement.setString(2, student.getName());
        statement.setString(3, student.getGender());
        statement.setInt(4, student.getAge());
        status = statement.execute();
        return status;
    }

    public boolean update(Connection connection, Student student) throws SQLException {
        String sql = "UPDATE STUDENT SET gender = ?, age = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getGender());
        statement.setInt(2, student.getAge());
        statement.setString(3, student.getId());
        return statement.execute();
    }

    public boolean delete(Connection connection, String id) throws SQLException {
        String sql = "DELETE FROM STUDENT WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        return statement.execute();
    }

    public Student select(Connection connection, String id) throws SQLException {
        String sql = "SELECT id,name,gender,age FROM STUDENT WHERE id = ? LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();//！！！！一定要将结果集的指针移动到第一行

        Student student = new Student();
        student.setId(resultSet.getString(1));
        student.setName(resultSet.getString(2));
        student.setGender(resultSet.getString(3));
        student.setAge(resultSet.getInt(4));

        return student;
    }

    @Override
    public List<Student> selectAll(Connection connection) throws SQLException {
        String sql = "SELECT id,name,gender,age FROM STUDENT";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String gender = resultSet.getString(3);
            int age = resultSet.getInt(4);

            Student student = new Student(id, name, age, gender);
            students.add(student);
        }
        return students;
    }
}
