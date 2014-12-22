package com.e6893.model;

/**
 * Represents ECLS-K data stored for a student
 * 
 * @author Jairo Pava
 *
 */
public class Student {
	/*
	 * ECLS-K variables follow below. See ECLS-K code book for meaning of these variables
	 */
	
	private String childid;
	private Float c1r4rtsc;
	private Float c2r4rtsc;
	private Float c3r4rtsc;
	private Float c4r4rtsc;
	private Float c5r4rtsc;
	private Float c6r4rtsc;
	private Float c7r4rtsc;
	private Float c1r4mtsc;
	private Float c2r4mtsc;
	private Float c3r4mtsc;
	private Float c4r4mtsc;
	private Float c5r4mtsc;
	private Float c6r4mtsc;
	private Float c7r4mtsc;
	private Float c5r2stsc;
	private Float c6r2stsc;
	private Float c7r2stsc;
	private String p1chlboo;
	private String p2librar;
	private String p3sthlib;
	private String p4clbcrd;
	private String p5rlbcrd;
	private String p6chlboo;
	private String a2gotoli;
	private String a2borrow;
	private String a4gotoli;
	private String a4kborro;
	private String a5gotoli;
	private String a5borrow;
	private String g6gotoli;
	private String g6borrow;
	private String k2q1c_a;
	private String k2q1c_c;
	private String k2q1c_f;
	private String k4q1a_c;
	private String k4q2a_c;
	private String k4q2d_c;
	public String getChildid() {
		return childid;
	}
	public void setChildid(String childid) {
		this.childid = childid;
	}
	public Float getC1r4rtsc() {
		return c1r4rtsc;
	}
	public void setC1r4rtsc(Float c1r4rtsc) {
		this.c1r4rtsc = c1r4rtsc;
	}
	public Float getC2r4rtsc() {
		return c2r4rtsc;
	}
	public void setC2r4rtsc(Float c2r4rtsc) {
		this.c2r4rtsc = c2r4rtsc;
	}
	public Float getC3r4rtsc() {
		return c3r4rtsc;
	}
	public void setC3r4rtsc(Float c3r4rtsc) {
		this.c3r4rtsc = c3r4rtsc;
	}
	public Float getC4r4rtsc() {
		return c4r4rtsc;
	}
	public void setC4r4rtsc(Float c4r4rtsc) {
		this.c4r4rtsc = c4r4rtsc;
	}
	public Float getC5r4rtsc() {
		return c5r4rtsc;
	}
	public void setC5r4rtsc(Float c5r4rtsc) {
		this.c5r4rtsc = c5r4rtsc;
	}
	public Float getC6r4rtsc() {
		return c6r4rtsc;
	}
	public void setC6r4rtsc(Float c6r4rtsc) {
		this.c6r4rtsc = c6r4rtsc;
	}
	public Float getC7r4rtsc() {
		return c7r4rtsc;
	}
	public void setC7r4rtsc(Float c7r4rtsc) {
		this.c7r4rtsc = c7r4rtsc;
	}
	public Float getC1r4mtsc() {
		return c1r4mtsc;
	}
	public void setC1r4mtsc(Float c1r4mtsc) {
		this.c1r4mtsc = c1r4mtsc;
	}
	public Float getC2r4mtsc() {
		return c2r4mtsc;
	}
	public void setC2r4mtsc(Float c2r4mtsc) {
		this.c2r4mtsc = c2r4mtsc;
	}
	public Float getC3r4mtsc() {
		return c3r4mtsc;
	}
	public void setC3r4mtsc(Float c3r4mtsc) {
		this.c3r4mtsc = c3r4mtsc;
	}
	public Float getC4r4mtsc() {
		return c4r4mtsc;
	}
	public void setC4r4mtsc(Float c4r4mtsc) {
		this.c4r4mtsc = c4r4mtsc;
	}
	public Float getC5r4mtsc() {
		return c5r4mtsc;
	}
	public void setC5r4mtsc(Float c5r4mtsc) {
		this.c5r4mtsc = c5r4mtsc;
	}
	public Float getC6r4mtsc() {
		return c6r4mtsc;
	}
	public void setC6r4mtsc(Float c6r4mtsc) {
		this.c6r4mtsc = c6r4mtsc;
	}
	public Float getC7r4mtsc() {
		return c7r4mtsc;
	}
	public void setC7r4mtsc(Float c7r4mtsc) {
		this.c7r4mtsc = c7r4mtsc;
	}
	public Float getC5r2stsc() {
		return c5r2stsc;
	}
	public void setC5r2stsc(Float c5r2stsc) {
		this.c5r2stsc = c5r2stsc;
	}
	public Float getC6r2stsc() {
		return c6r2stsc;
	}
	public void setC6r2stsc(Float c6r2stsc) {
		this.c6r2stsc = c6r2stsc;
	}
	public Float getC7r2stsc() {
		return c7r2stsc;
	}
	public void setC7r2stsc(Float c7r2stsc) {
		this.c7r2stsc = c7r2stsc;
	}
	public String getP1chlboo() {
		return p1chlboo;
	}
	public void setP1chlboo(Float p1chlboo) {
		if(p1chlboo >= 0 && p1chlboo <= 200) {
			this.p1chlboo = p1chlboo + "";
		}
		else if(p1chlboo == -7) {
			this.p1chlboo = "Refused";
		}
		else if(p1chlboo == -8) {
			this.p1chlboo = "Don't Know";		
		}
		else if(p1chlboo == -9) {
			this.p1chlboo = "Not Ascertained";
		}
		else {
			this.p1chlboo = "N/A";
		}
	}
	public String getP2librar() {
		return p2librar;
	}
	public void setP2librar(Float p2librar) {		
		if(p2librar == 1){
			this.p2librar = "Yes";
		}
		else if(p2librar == 2) {
			this.p2librar = "No";
		}
		else if(p2librar == -1) {
			this.p2librar = "Not applicable";
		}
		else if(p2librar == -7) {
			this.p2librar = "Refused";
		}
		else if(p2librar == -8) {
			this.p2librar = "Don't know";
		}
		else if(p2librar == -9) {
			this.p2librar = "Not ascertained";
		}
		else {
			this.p2librar = "N/A";
		}
	}
	public String getP3sthlib() {
		return p3sthlib;
	}
	public void setP3sthlib(Float p3sthlib) {
		if(p3sthlib == 1){
			this.p3sthlib = "Yes";
		}
		else if(p3sthlib == 2) {
			this.p3sthlib = "No";
		}
		else if(p3sthlib == -1) {
			this.p3sthlib = "Not applicable";
		}
		else if(p3sthlib == -7) {
			this.p3sthlib = "Refused";
		}
		else if(p3sthlib == -8) {
			this.p3sthlib = "Don't know";
		}
		else if(p3sthlib == -9) {
			this.p3sthlib = "Not ascertained";
		}
		else {
			this.p3sthlib = "N/A";
		}
	}
	public String getP4clbcrd() {
		return p4clbcrd;
	}
	public void setP4clbcrd(Float p4clbcrd) {
		if(p4clbcrd == 1){
			this.p4clbcrd = "Yes";
		}
		else if(p4clbcrd == 2) {
			this.p4clbcrd = "No";
		}
		else if(p4clbcrd == -7) {
			this.p4clbcrd = "Refused";
		}
		else if(p4clbcrd == -8) {
			this.p4clbcrd = "Don't know";
		}
		else if(p4clbcrd == -9) {
			this.p4clbcrd = "Not ascertained";
		}
		else {
			this.p4clbcrd = "N/A";
		}
	}
	public String getP5rlbcrd() {
		return p5rlbcrd;
	}
	public void setP5rlbcrd(Float p5rlbcrd) {
		if(p5rlbcrd == 1){
			this.p5rlbcrd = "Yes";
		}
		else if(p5rlbcrd == 2) {
			this.p5rlbcrd = "No";
		}
		else if(p5rlbcrd == -7) {
			this.p5rlbcrd = "Refused";
		}
		else if(p5rlbcrd == -8) {
			this.p5rlbcrd = "Don't know";
		}
		else if(p5rlbcrd == -9) {
			this.p5rlbcrd = "Not ascertained";
		}
		else {
			this.p5rlbcrd = "N/A";
		}
	}
	public String getP6chlboo() {
		return p6chlboo;
	}
	public void setP6chlboo(Float p6chlboo) {
		if(p6chlboo >= 0 && p6chlboo <= 5000) {
			this.p6chlboo = p6chlboo + "";
		}
		else if(p6chlboo == -7) {
			this.p6chlboo = "Refused";
		}
		else if(p6chlboo == -8) {
			this.p6chlboo = "Don't Know";		
		}
		else if(p6chlboo == -9) {
			this.p6chlboo = "Not Ascertained";
		}
		else {
			this.p6chlboo = "N/A";
		}
	}
	public String getA2gotoli() {
		return a2gotoli;
	}
	public void setA2gotoli(Float a2gotoli) {
		if(a2gotoli == 0) {
			this.a2gotoli = "No library or media center in this school";
		}
		else if(a2gotoli == 1) {
			this.a2gotoli = "Once a month or less";
		}
		else if(a2gotoli == 2) {
			this.a2gotoli = "Two or three times a month";		
		}
		else if(a2gotoli == 3) {
			this.a2gotoli = "Once or twice a week";
		}
		else if(a2gotoli == 4) {
			this.a2gotoli = "Three or four times a week";
		}
		else if(a2gotoli == 5) {
			this.a2gotoli = "Daily";
		}
		else if(a2gotoli == -7) {
			this.a2gotoli = "Refused";
		}
		else if(a2gotoli == -8) {
			this.a2gotoli = "Don't know";
		}
		else if(a2gotoli == -9) {
			this.a2gotoli = "Not ascertained";
		}
		else {
			this.a2gotoli = "N/A";
		}
	}
	public String getA2borrow() {
		return a2borrow;
	}
	public void setA2borrow(Float a2borrow) {
		if(a2borrow == 0) {
			this.a2borrow = "No library or media center in this school";
		}
		else if(a2borrow == 1) {
			this.a2borrow = "Once a month or less";
		}
		else if(a2borrow == 2) {
			this.a2borrow = "Two or three times a month";		
		}
		else if(a2borrow == 3) {
			this.a2borrow = "Once or twice a week";
		}
		else if(a2borrow == 4) {
			this.a2borrow = "Three or four times a week";
		}
		else if(a2borrow == 5) {
			this.a2borrow = "Daily";
		}
		else if(a2borrow == -7) {
			this.a2borrow = "Refused";
		}
		else if(a2borrow == -8) {
			this.a2borrow = "Don't know";
		}
		else if(a2borrow == -9) {
			this.a2borrow = "Not ascertained";
		}
		else {
			this.a2gotoli = "N/A";
		}
	}
	public String getA4gotoli() {
		return a4gotoli;
	}
	public void setA4gotoli(Float a4gotoli) {
		if(a4gotoli == 0) {
			this.a4gotoli = "No library or media center in this school";
		}
		else if(a4gotoli == 1) {
			this.a4gotoli = "Once a month or less";
		}
		else if(a4gotoli == 2) {
			this.a4gotoli = "Two or three times a month";		
		}
		else if(a4gotoli == 3) {
			this.a4gotoli = "Once or twice a week";
		}
		else if(a4gotoli == 4) {
			this.a4gotoli = "Three or four times a week";
		}
		else if(a4gotoli == 5) {
			this.a4gotoli = "Daily";
		}
		else if(a4gotoli == -7) {
			this.a4gotoli = "Refused";
		}
		else if(a4gotoli == -8) {
			this.a4gotoli = "Don't know";
		}
		else if(a4gotoli == -9) {
			this.a4gotoli = "Not ascertained";
		}
		else {
			this.a4gotoli = "N/A";
		}
	}
	public String getA4kborro() {
		return a4kborro;
	}
	public void setA4kborro(Float a4kborro) {
		if(a4kborro == 0) {
			this.a4kborro = "No library or media center in this school";
		}
		else if(a4kborro == 1) {
			this.a4kborro = "Once a month or less";
		}
		else if(a4kborro == 2) {
			this.a4kborro = "Two or three times a month";		
		}
		else if(a4kborro == 3) {
			this.a4kborro = "Once or twice a week";
		}
		else if(a4kborro == 4) {
			this.a4kborro = "Three or four times a week";
		}
		else if(a4kborro == 5) {
			this.a4kborro = "Daily";
		}
		else if(a4kborro == -7) {
			this.a4kborro = "Refused";
		}
		else if(a4kborro == -8) {
			this.a4kborro = "Don't know";
		}
		else if(a4kborro == -9) {
			this.a4kborro = "Not ascertained";
		}
		else {
			this.a4kborro = "N/A";
		}
	}
	public String getA5gotoli() {
		return a5gotoli;
	}
	public void setA5gotoli(Float a5gotoli) {
		if(a5gotoli == 0) {
			this.a5gotoli = "No library or media center in this school";
		}
		else if(a5gotoli == 1) {
			this.a5gotoli = "Once a month or less";
		}
		else if(a5gotoli == 2) {
			this.a5gotoli = "Two or three times a month";		
		}
		else if(a5gotoli == 3) {
			this.a5gotoli = "Once or twice a week";
		}
		else if(a5gotoli == 4) {
			this.a5gotoli = "Three or four times a week";
		}
		else if(a5gotoli == 5) {
			this.a5gotoli = "Daily";
		}
		else if(a5gotoli == -7) {
			this.a5gotoli = "Refused";
		}
		else if(a5gotoli == -8) {
			this.a5gotoli = "Don't know";
		}
		else if(a5gotoli == -9) {
			this.a5gotoli = "Not ascertained";
		}
		else {
			this.a5gotoli = "N/A";
		}
	}
	public String getA5borrow() {
		return a5borrow;
	}
	public void setA5borrow(Float a5borrow) {
		if(a5borrow == 0) {
			this.a5borrow = "No library or media center in this school";
		}
		else if(a5borrow == 1) {
			this.a5borrow = "Once a month or less";
		}
		else if(a5borrow == 2) {
			this.a5borrow = "Two or three times a month";		
		}
		else if(a5borrow == 3) {
			this.a5borrow = "Once or twice a week";
		}
		else if(a5borrow == 4) {
			this.a5borrow = "Three or four times a week";
		}
		else if(a5borrow == 5) {
			this.a5borrow = "Daily";
		}
		else if(a5borrow == -7) {
			this.a5borrow = "Refused";
		}
		else if(a5borrow == -8) {
			this.a5borrow = "Don't know";
		}
		else if(a5borrow == -9) {
			this.a5borrow = "Not ascertained";
		}
		else {
			this.a5borrow = "N/A";
		}
	}
	public String getG6gotoli() {
		return g6gotoli;
	}
	public void setG6gotoli(Float g6gotoli) {
		if(g6gotoli == 0) {
			this.g6gotoli = "No library or media center in this school";
		}
		else if(g6gotoli == 1) {
			this.g6gotoli = "Once a month or less";
		}
		else if(g6gotoli == 2) {
			this.g6gotoli = "Two or three times a month";		
		}
		else if(g6gotoli == 3) {
			this.g6gotoli = "Once or twice a week";
		}
		else if(g6gotoli == 4) {
			this.g6gotoli = "Three or four times a week";
		}
		else if(g6gotoli == 5) {
			this.g6gotoli = "Daily";
		}
		else if(g6gotoli == -7) {
			this.g6gotoli = "Refused";
		}
		else if(g6gotoli == -8) {
			this.g6gotoli = "Don't know";
		}
		else if(g6gotoli == -9) {
			this.g6gotoli = "Not ascertained";
		}
		else {
			this.g6gotoli = "N/A";
		}
	}
	public String getG6borrow() {
		return g6borrow;
	}
	public void setG6borrow(Float g6borrow) {
		if(g6borrow == 0) {
			this.g6borrow = "No library or media center in this school";
		}
		else if(g6borrow == 1) {
			this.g6borrow = "Once a month or less";
		}
		else if(g6borrow == 2) {
			this.g6borrow = "Two or three times a month";		
		}
		else if(g6borrow == 3) {
			this.g6borrow = "Once or twice a week";
		}
		else if(g6borrow == 4) {
			this.g6borrow = "Three or four times a week";
		}
		else if(g6borrow == 5) {
			this.g6borrow = "Daily";
		}
		else if(g6borrow == -7) {
			this.g6borrow = "Refused";
		}
		else if(g6borrow == -8) {
			this.g6borrow = "Don't know";
		}
		else if(g6borrow == -9) {
			this.g6borrow = "Not ascertained";
		}
		else {
			this.g6borrow = "N/A";
		}
	}
	public String getK2q1c_a() {
		return k2q1c_a;
	}
	public void setK2q1c_a(Float k2q1c_a) {
		if(k2q1c_a == 1){
			this.k2q1c_a = "Yes";
		}
		else if(k2q1c_a == 2) {
			this.k2q1c_a = "No";
		}
		else if(k2q1c_a == -7) {
			this.k2q1c_a = "Refused";
		}
		else if(k2q1c_a == -8) {
			this.k2q1c_a = "Don't know";
		}
		else if(k2q1c_a == -9) {
			this.k2q1c_a = "Not ascertained";
		}
		else {
			this.k2q1c_a = "N/A";
		}
	}
	public String getK2q1c_c() {
		return k2q1c_c;
	}
	public void setK2q1c_c(Float k2q1c_c) {
		if(k2q1c_c == 1) {
			this.k2q1c_c = "Satisfactory";
		}
		else if(k2q1c_c == 2) {
			this.k2q1c_c = "Unsatisfactory";
		}
		else if(k2q1c_c == -1) {
			this.k2q1c_c = "Not applicable";
		}
		else if(k2q1c_c == -7) {
			this.k2q1c_c = "Refused";
		}
		else if(k2q1c_c == -8) {
			this.k2q1c_c = "Don't know";
		}
		else if(k2q1c_c == -9) {
			this.k2q1c_c = "Not ascertained";
		}
		else {
			this.k2q1c_c = "N/A";
		}
	}
	public String getK2q1c_f() {
		return k2q1c_f;
	}
	public void setK2q1c_f(Float k2q1c_f) {
		if(k2q1c_f == 1) {
			this.k2q1c_f = "Satisfactory";
		}
		else if(k2q1c_f == 2) {
			this.k2q1c_f = "Unsatisfactory";
		}
		else if(k2q1c_f == -1) {
			this.k2q1c_f = "Not applicable";
		}
		else if(k2q1c_f == -7) {
			this.k2q1c_f = "Refused";
		}
		else if(k2q1c_f == -8) {
			this.k2q1c_f = "Don't know";
		}
		else if(k2q1c_f == -9) {
			this.k2q1c_f = "Not ascertained";
		}
		else {
			this.k2q1c_f = "N/A";
		}
	}
	public String getK4q1a_c() {
		return k4q1a_c;
	}
	public void setK4q1a_c(Float k4q1a_c) {
		if(k4q1a_c == 1){
			this.k4q1a_c = "Yes";
		}
		else if(k4q1a_c == 2) {
			this.k4q1a_c = "No";
		}
		else if(k4q1a_c == -7) {
			this.k4q1a_c = "Refused";
		}
		else if(k4q1a_c == -8) {
			this.k4q1a_c = "Don't know";
		}
		else if(k4q1a_c == -9) {
			this.k4q1a_c = "Not ascertained";
		}
		else {
			this.k4q1a_c = "N/A";
		}
	}
	public String getK4q2a_c() {
		return k4q2a_c;
	}
	public void setK4q2a_c(Float k4q2a_c) {
		if(k4q2a_c == 1) {
			this.k4q2a_c = "Satisfactory";
		}
		else if(k4q2a_c == 2) {
			this.k4q2a_c = "Unsatisfactory";
		}
		else if(k4q2a_c == -1) {
			this.k4q2a_c = "Not applicable";
		}
		else if(k4q2a_c == -7) {
			this.k4q2a_c = "Refused";
		}
		else if(k4q2a_c == -8) {
			this.k4q2a_c = "Don't know";
		}
		else if(k4q2a_c == -9) {
			this.k4q2a_c = "Not ascertained";
		}
		else {
			this.k4q2a_c = "N/A";
		}
	}
	public String getK4q2d_c() {
		return k4q2d_c;
	}
	public void setK4q2d_c(Float k4q2d_c) {
		if(k4q2d_c == 1) {
			this.k4q2d_c = "Satisfactory";
		}
		else if(k4q2d_c == 2) {
			this.k4q2d_c = "Unsatisfactory";
		}
		else if(k4q2d_c == -1) {
			this.k4q2d_c = "Not applicable";
		}
		else if(k4q2d_c == -7) {
			this.k4q2d_c = "Refused";
		}
		else if(k4q2d_c == -8) {
			this.k4q2d_c = "Don't know";
		}
		else if(k4q2d_c == -9) {
			this.k4q2d_c = "Not ascertained";
		}
		else {
			this.k4q2d_c = "N/A";
		}
	}
}