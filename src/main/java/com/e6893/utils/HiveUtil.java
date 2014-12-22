package com.e6893.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.e6893.model.Student;

/**
 * Class used to interact with Hive Server
 * 
 * @author Jairo Pava
 *
 */
public class HiveUtil {
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	/**
	 * Get student given student Id
	 * @param studentId
	 * @return
	 */
	public static Student getStudent(String studentId) {
		Student student = new Student();

		try {
			Class.forName(driverName);

			Connection con = DriverManager.getConnection(
					"jdbc:hive://localhost:10000/default", "", "");
			Statement stmt = con.createStatement();
			String tableName = "eclsk";

			// select * query
			String sql = "select * from " + tableName
					+ " where childid = '" + studentId + "'";
			
			Logger.log("Running: " + sql);
			
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				populateRecord(student, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return student;
	}

	/**
	 * Populate Student object given HiveQL results
	 * 
	 * @param student
	 * @param res
	 * @throws SQLException
	 */
	private static void populateRecord(Student student, ResultSet res)
			throws SQLException {
		student.setChildid(res.getString(1));
		student.setC1r4rtsc(res.getFloat(2));
		student.setC2r4rtsc(res.getFloat(3));
		student.setC3r4rtsc(res.getFloat(4));
		student.setC4r4rtsc(res.getFloat(5));
		student.setC5r4rtsc(res.getFloat(6));
		student.setC6r4rtsc(res.getFloat(7));
		student.setC7r4rtsc(res.getFloat(8));
		student.setC1r4mtsc(res.getFloat(9));
		student.setC2r4mtsc(res.getFloat(10));
		student.setC3r4mtsc(res.getFloat(11));
		student.setC4r4mtsc(res.getFloat(12));
		student.setC5r4mtsc(res.getFloat(13));
		student.setC6r4mtsc(res.getFloat(14));
		student.setC7r4mtsc(res.getFloat(15));
		student.setC5r2stsc(res.getFloat(16));
		student.setC6r2stsc(res.getFloat(17));
		student.setC7r2stsc(res.getFloat(18));
		student.setP1chlboo(res.getFloat(19));
		student.setP2librar(res.getFloat(20));
		student.setP3sthlib(res.getFloat(21));
		student.setP4clbcrd(res.getFloat(22));
		student.setP5rlbcrd(res.getFloat(23));
		student.setP6chlboo(res.getFloat(24));
		student.setA2gotoli(res.getFloat(25));
		student.setA2borrow(res.getFloat(26));
		student.setA4gotoli(res.getFloat(27));
		student.setA4kborro(res.getFloat(28));
		student.setA5gotoli(res.getFloat(29));
		student.setA5borrow(res.getFloat(30));
		student.setG6gotoli(res.getFloat(31));
		student.setG6borrow(res.getFloat(32));
		student.setK2q1c_a(res.getFloat(33));
		student.setK2q1c_c(res.getFloat(34));
		student.setK2q1c_f(res.getFloat(35));
		student.setK4q1a_c(res.getFloat(36));
		student.setK4q2a_c(res.getFloat(37));
		student.setK4q2d_c(res.getFloat(38));
	}
}
