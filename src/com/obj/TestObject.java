package com.obj;

public class TestObject implements Comparable{
	String name;
	double price ;
	
	public TestObject() {
		super();

	}
 	public TestObject(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	//指明对象比较的方式
	@Override
	public int compareTo(Object o) {
		if(o instanceof TestObject) {
			TestObject testObj = (TestObject) o;
			if(this.price > testObj.price) {
				return 1;
			}else if(this.price < testObj.price) {
				return -1;
			}else {
				//相等
				return 0;
			}
		}
		return 0;
	}
	@Override
	public String toString() {
		
		return "TestObject [name=" + name + ", price=" + price + "]";
	}
	
	
}
