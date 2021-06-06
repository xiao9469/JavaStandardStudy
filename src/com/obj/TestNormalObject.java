package com.obj;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import java.io.Serializable;
import java.util.Comparator;

public class TestNormalObject implements Comparable , Serializable {
	String name ;
	int ID ;

	@Override
	public String toString() {
		return "TestNormalObject{" +
				"name='" + name + '\'' +
				", ID=" + ID +
				'}';
	}

	public static final long  serialVersionUID = -6632586151270989650L;
	public TestNormalObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestNormalObject(String name, int iD) {
		super();
		this.name = name;
		ID = iD;
	}

	public int getID() {
		return ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestNormalObject other = (TestNormalObject) obj;
		if (ID != other.ID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	//按从小到大排序
	@Override
	public int compareTo(Object o) {
		if (o instanceof  TestNormalObject){
			TestNormalObject testNormalObject = (TestNormalObject) o;
			int comparaToResult_1 =  this.name.compareTo(testNormalObject.name);
			if(comparaToResult_1 != 0){
				return comparaToResult_1;
			}else {
				return Integer.compare(this.ID, testNormalObject.ID);
			}
		}else {
			throw new RuntimeException("输入类型不匹配");
		}
	}
}
