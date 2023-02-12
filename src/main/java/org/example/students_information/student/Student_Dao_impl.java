package org.example.students_information.student;

import org.example.JDBCConnector;
import org.example.students_information.Student_super;
import org.example.students_information.student.util_date.Utils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Student_Dao_impl implements Student_Dao {

    @Override
    public List<Student_super> findAll() throws SQLException {
        Connection connection =JDBCConnector.getConnection();
        if(connection==null){ }

        List<Student_super> student_supers = new LinkedList<>();

             String query = "SELECT * FROM student_information ;";
             try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                 ResultSet resultSet =preparedStatement.executeQuery(query);
                 while (resultSet.next()){
                     Student_super student_super = new Student_super(resultSet.getInt("id"),resultSet.getString("first_name"
                     ), resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getBoolean("gender"), resultSet.getString("TC_address"), resultSet.getString("phone_number"), resultSet.getString("email_address"), resultSet.getString("home_address"));
                    student_supers.add(student_super);
                 }

             }catch (SQLException e){
                 e.printStackTrace();
             }

            return student_supers;

    }

    @Override
    public Student_super findById(int id) throws SQLException {
        Connection connection =JDBCConnector.getConnection();
        if(connection == null){
            return null;
        }else{
            String query = "SELECT * FROM student_information WHERE id=?;";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    Student_super student_super = new Student_super(resultSet.getInt("id"),resultSet.getString("first_name"
                    ), resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getBoolean("gender"), resultSet.getString("TC_address"), resultSet.getString("phone_number"
                    ), resultSet.getString("email_address"), resultSet.getString("home_address"));
                return student_super;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void seve(Student_super student_super) throws SQLException {
        Connection connection = JDBCConnector.getConnection();
        if (connection == null) {
            return;
        }
        if (student_super.getId() > 0) {  //Update
            String query = "UPDATE student_information SET first_name = ?,last_name=?,birth_date=?,gender=?,TC_address=?,phone_number=?,email_address=?,home_address=? WHERE id=?; ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, student_super.getFirst_name());
                preparedStatement.setString(2, student_super.getLast_name());
                preparedStatement.setDate(3, Utils.getSqlDate((Date) student_super.getBirth_date()));
                preparedStatement.setBoolean(4, student_super.isGender());
                preparedStatement.setString(5, student_super.getTC_address());
                preparedStatement.setString(6, student_super.getPhone_number());
                preparedStatement.setString(7, student_super.getEmail_address());
                preparedStatement.setString(8, student_super.getPhone_number());
                preparedStatement.setInt(9, student_super.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        } else {       // Insert into
            String query = "INSERT INTO student_information (first_name,last_name,birth_date,gender,TC_address,phone_number,email_address,home_address) VALUES (?,?,?,?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, student_super.getFirst_name());
                preparedStatement.setString(2, student_super.getLast_name());
                preparedStatement.setDate(3, Utils.getSqlDate(student_super.getBirth_date()));
                preparedStatement.setBoolean(4, student_super.isGender());
                preparedStatement.setString(5, student_super.getTC_address());
                preparedStatement.setString(6, student_super.getPhone_number());
                preparedStatement.setString(7, student_super.getEmail_address());
                preparedStatement.setString(8, student_super.getPhone_number());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        Connection connection =JDBCConnector.getConnection();
        if (connection==null){
        return;
        }else {
            String query ="DELETE FROM student_information WHERE id=?; ";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}