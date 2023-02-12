package org.example;

import org.example.students_information.Student_super;
import org.example.students_information.student.Student_Dao;
import org.example.students_information.student.Student_Dao_impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        Student_Dao student_Dao = new Student_Dao_impl();

//       Student_super student_super =new Student_super(0,"ahmed","ali",new Date(),true,"9348243","0982334545","teklimakan@gmail.com","Istanbol");

        //student_Dao.seve(student_super);           // this code use to Insert or Update

        //student_Dao.findAll().forEach(System.out::println);       //  this code is to Selection to all column

       // Student_super student = student_Dao.findById(1);           //   this code is to find column by id


        // System.out.println(student);

        student_Dao.deleteById(1);
        System.out.println("yes");

    }


}