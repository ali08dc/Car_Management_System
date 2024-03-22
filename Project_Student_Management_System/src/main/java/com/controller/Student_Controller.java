package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Student;

public class Student_Controller {

	public int saveStudent(Student student, Connection con) {

		int n = 0;

		try {
			PreparedStatement psmt = con.prepareStatement("insert into student values(?,?,?,?,?,?,?)");

			psmt.setInt(1, student.getId());
			psmt.setString(2, student.getName());
			psmt.setString(3, student.getEmail());
			psmt.setString(4, student.getPassword());
			psmt.setString(5, student.getGender());
			psmt.setLong(6, student.getMob());
			psmt.setString(7, student.getDob());

			n = psmt.executeUpdate();
			

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return n;
	}

	public ResultSet findStudentById(int id, Connection con) {

		ResultSet rs = null;

		try {
			PreparedStatement psmt = con.prepareStatement("Select * from student where id = ?");

			psmt.setInt(1, id);

			rs = psmt.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;
	}
	
	public int updateStudentByID(int id, String email, Connection con) {
		int n = 0;
		
		try {
			PreparedStatement psmt = con.prepareStatement("Update student set email = ? where id = ?");
			
			psmt.setString(1,email);
			psmt.setInt(2, id);
			
			n = psmt.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return n;
		
	}
	
	public int deleteStudentByID(int id, Connection con) {
		
		int b = 0;
		
		try {
			PreparedStatement psmt = con.prepareStatement("delete from student where id = ?");
			
			psmt.setInt(1, id);
			
			b = psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return b;
	}

	public ResultSet findAll(Connection con) {
		ResultSet rs = null;
		
		try {
			PreparedStatement psmt = con.prepareStatement("select * from student");
			
			rs = psmt.executeQuery();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rs;
	}
}
