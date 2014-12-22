package com.e6893.model;

/**
 * Represents information entered by the user about the student in the web application
 * 
 * @author Jairo Pava
 *
 */
public class Feature {
	
	/**
	 * The student's name
	 */
	private String name;
	
	/**
	 * Kindergarten reading score
	 */
	private double kReading;
	
	/**
	 * Kindergarten math score
	 */
	private double kMath;
	
	/**
	 * First grade reading score
	 */
	private double fReading;
	
	/**
	 * First grade math score
	 */
	private double fMath;
	
	/**
	 * Third grade reading score
	 */
	private double tReading;
	
	/**
	 * Third grade math score
	 */
	private double tMath;
	
	/**
	 * Third grade science score
	 */
	private double tScience;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getkReading() {
		return kReading;
	}

	public void setkReading(double kReading) {
		this.kReading = kReading;
	}

	public double getkMath() {
		return kMath;
	}

	public void setkMath(double kMath) {
		this.kMath = kMath;
	}

	public double getfReading() {
		return fReading;
	}

	public void setfReading(double fReading) {
		this.fReading = fReading;
	}

	public double getfMath() {
		return fMath;
	}

	public void setfMath(double fMath) {
		this.fMath = fMath;
	}

	public double gettReading() {
		return tReading;
	}

	public void settReading(double tReading) {
		this.tReading = tReading;
	}

	public double gettMath() {
		return tMath;
	}

	public void settMath(double tMath) {
		this.tMath = tMath;
	}

	public double gettScience() {
		return tScience;
	}

	public void settScience(double tScience) {
		this.tScience = tScience;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Student Name: " + this.getName() + "\n");
		sb.append("Kindergarten Reading: " + this.getkReading() + "\n");
		sb.append("Kindergarten Math: " + this.getkMath() + "\n");
		sb.append("First Grade Reading: " + this.getfReading() + "\n");
		sb.append("First Grade Math: " + this.getfMath() + "\n");
		sb.append("Third Grade Reading: " + this.gettReading() + "\n");
		sb.append("Third Grade Math: " + this.gettMath() + "\n");
		sb.append("Third Grade Science: " + this.gettScience() + "\n");
		
		return sb.toString();
	}
}
