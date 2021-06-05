package com.obj;

public class TestComparableObject implements Comparable{
	String name;
	double price ;
	
	public TestComparableObject() {
		super();

	}
 	public TestComparableObject(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	//指明对象比较的方式
	@Override
	public int compareTo(Object o) {
		if(o instanceof TestComparableObject) {
			TestComparableObject testObj = (TestComparableObject) o;
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


