package test;

import java.sql.Date;

import util.DateTransfer;

public class TestDate {
	
	
	
	public static void main(String[] args){
		String datestr = "2015年9月";
		
		Date date = DateTransfer.parserToDate(datestr);
		
		System.out.println(date);
	}
}
