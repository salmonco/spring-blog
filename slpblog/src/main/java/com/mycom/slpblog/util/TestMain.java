package com.mycom.slpblog.util;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		aa();
		ab();
		ac();
	}
	
	public static void aa() {
		int inta = 1;
		int intb = 5;
		int intc = inta/intb;
		System.out.println("inta/intb="+inta/intb);
		System.out.println("inta/intb="+Math.ceil(inta/intb));
		System.out.println("(int)inta/intb="+(int)Math.ceil(inta/intb));
	}
	
	public static void ab() {
		double inta = 1;
		double intb = 5;
//		int intc = inta/intb;
		System.out.println("inta/intb="+inta/intb);
		System.out.println("inta/intb="+Math.ceil(inta/intb));
		System.out.println("(int)inta/intb="+(int)Math.ceil(inta/intb));
	}
	
	public static void ac() {
		int inta = 1;
		int intb = 5;
		int intc = inta/intb;
		System.out.println("inta/intb="+inta/intb);
		System.out.println("inta/intb="+Math.ceil(inta/intb));
		System.out.println("(int)inta/intb="+(int)Math.ceil((double)inta/intb));
	}

}
