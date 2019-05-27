package com.orange.shop;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ShopLocator s = new ShopLocator();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("please enter a latitude: ");
		double lat = scanner.nextDouble();
		
		System.out.println("please enter a longitude: ");
		double lan = scanner.nextDouble();
		System.out.println("Available mobile device models 1.sunusng  2.ipom  3.weiwei");
		
		System.out.println("please enter a mobile name: ");
		String mobile = scanner.next();
		//scanner.close();
		
		String nearest=s.findOrangeShopWithMobileAvailable(lat, lan, mobile.toLowerCase().toString());
		System.out.println(nearest);
		
		//test here for the in program testing by uncommenting the below lines.....
		//double lat1=49.1667  ;
		//double long1=-1.2833;
		//nameMobile="sunusng";
		//String nearest=s.findOrangeShopWithMobileAvailable(lat, lan, mobile);
		//System.out.println(nearest);
		
	}

}
