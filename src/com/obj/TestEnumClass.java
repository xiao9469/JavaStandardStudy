package com.obj;

import java.util.ArrayList;

import org.junit.Test;

import com.testInterface.TestAnnotation;
import com.testInterface.TestInterface;

public class TestEnumClass {
	public static void main(String[] args) {
		/*
		//toString方法
		Season2 season2 = Season2.SUMMER;
		System.out.println(season2.getClass().getSuperclass());
		//values()方法
		Season2 [] season3 = Season2.values();
		for (Season2 s : season3) {
			System.out.println(s);
		}
		*/
		
		TestEnum te = TestEnum.valueOf("ONE");
		System.out.println(te);
		
		
		
	}
	@Test
	public void testValueOfMethod() {
		//valueOf 方法：返回枚举类中对象名是objName的对象
		Season2 s4 = Season2.valueOf("WINTER");
		System.out.println(s4);
	}
}


enum TestEnum{
	ONE,TWO,THREE;
	String name;
	int ID;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
//enum声明枚举类
enum Season2 implements TestInterface{
	SPRING("Spring","2"){

		@Override
		public void testImplementsMethod() {
			// TODO Auto-generated method stub
			
		}
		
	},
	SUMMER("Spring","2"){

		@Override
		public void testImplementsMethod() {
			// TODO Auto-generated method stub
			
		}
		
	},
	AUTUMN("Spring","2"){

		@Override
		public void testImplementsMethod() {
			// TODO Auto-generated method stub
			
		}
		
	},
	WINTER("Spring","2"){

		@Override
		public void testImplementsMethod() {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	//声明Season对象的属性  private final修饰  通过构造器进行赋值
		private final String seasonName;
		private final String seasonMonth;
		
	//私有化构造器  不让外部创建对象
	private Season2(String seasonName,String seasonMonth) {
		this.seasonName = seasonName;
		this.seasonMonth = seasonMonth;
		
	}
	//其他方法
	public String getSeasonName() {
		return seasonName;
	}
	public String getSeasonMonth() {
		return seasonMonth;
	}
	
	
}

//自定义枚举类
class Season{
	//声明Season对象的属性  private final修饰  通过构造器进行赋值
	private final String seasonName;
	private final String seasonMonth;
	//私有化构造器  不让外部创建对象
	private Season(String seasonName,String seasonMonth) {
		this.seasonName = seasonName;
		this.seasonMonth = seasonMonth;
		
	}
	//提供枚举类对象
	private static final Season SPRING = new Season("Spring", "3"); 
	private static final Season SUMMER = new Season("Summer", "6"); 
	private static final Season AUTUMN = new Season("Autumn", "9"); 
	private static final Season WINTER = new Season("Winter", "12"); 
	
	//其他方法
	public String getSeasonName() {
		return seasonName;
	}
	public String getSeasonMonth() {
		return seasonMonth;
	}
	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonMonth=" + seasonMonth + "]";
	}
	
}

class Generic<@TestAnnotation T>{
	ArrayList<@TestAnnotation T> list = new ArrayList<>();
}