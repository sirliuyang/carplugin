package test;

public class TestRegex {
	public static void main(String[] args){
		String str1 = "排名";
		String str2 = "12";
		
		System.out.println(str2.matches("^-?\\d+$"));
	}
}
