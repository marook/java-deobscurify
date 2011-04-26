package my.test;

import java.util.Date;

public class ClassWithMethods {
	
	PackageVisibleClass packageVisible(){
		String s = "hello world";
	}

	private void privateVisible(int i){
		
	}
	
	protected void protectedVisible(Date d){
		
	}
	
	public void publicVisible(){
		
	}
	
	
}