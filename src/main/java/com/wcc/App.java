package com.wcc;

import com.wcc.dao.StudentDao;
import com.wcc.dao.impl.StudentDaoImpl;
import com.wcc.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getInstance().makeConnection();
        try {
            StudentDao studentDao = new StudentDaoImpl();
            //多条查询
            List<Student> students = studentDao.selectAll(connection);
            for (Student student : students) {
                System.out.println("student = " + student);
            }
            //单条查询
            Student student = studentDao.select(connection,"93671b8a001c11e9b28f5254002d9a46");
            System.out.println("student = " + student);
            //插入
            String uuid = String.valueOf(UUID.randomUUID()).replace("-", "");
            studentDao.insert(connection, new Student(uuid, "Jack", 22, "M"));
            //更新
            studentDao.update(connection, new Student("93671b8a001c11e9b28f5254002d9a46", null, 22, "M"));
            //删除
            /*studentDao.delete(connection,"a8cd5a52002911e9b28f5254002d9a46");
            connection.commit();*/
            System.out.println("操作成功");
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("已经回退");
            } catch (SQLException e1) {
                System.out.println("回退失败");
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
