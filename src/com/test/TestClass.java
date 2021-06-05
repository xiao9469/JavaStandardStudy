package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.obj.TestComparableObject;
import com.obj.TestNormalObject;
import com.testInterface.TestAnnotation;
import com.testInterface.TestAnnotation2;

public class TestClass {
	
	
	@Test
	//数组转集合
	public void testArrayToCollectionMethod() {
		String [] strArr = new String[] {"1","2","3","4"};
		List list = Arrays.asList(strArr);
		System.out.println(list);
		
		Integer [] intArr = new Integer[] {123,456};
		List<Integer> list2 = Arrays.asList(intArr);
		System.out.println(list2);
		
	}
	@Test
	//集合转数组
	public void testCollectionToArrayMethod() {
		Collection collection = Arrays.asList(123,"qwe");
		Object [] objArr = collection.toArray();
		System.out.println(objArr);
	}
	@Test
	public void testCollectionMethod() {
		
		TestNormalObject testNormalObject = new TestNormalObject("bbb", 345);
		
		Collection collection = new ArrayList();
		Collection collection2 = Arrays.asList(123,"qwe");
				
		collection.add(123);
		collection.add("qwe");
		collection.add(new String("aaa"));
		collection.add(testNormalObject);
		collection.add(new TestNormalObject("bbb", 345));
		
		collection.retainAll(collection2);
		
		System.out.println(collection.contains(123));
		System.out.println(collection.contains("qwe"));
		System.out.println(collection.contains(new String("aaa")));
		System.out.println(collection.contains(testNormalObject)); //地址一样
		System.out.println(collection.contains(new TestNormalObject("bbb", 345)));
		
		System.out.println(collection.containsAll(collection2));
		
	}
	
	
	public void testMethod() {
        
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	//comparator接口实现测试  从大到小排列
	public void testComparetorMethod() {
		TestComparableObject []  testObjs = new TestComparableObject[3];
		testObjs[0] = new TestComparableObject("A", 13.4);
		testObjs[1] = new TestComparableObject("B", 99.3);
		testObjs[2] = new TestComparableObject("c", 0.99);
		
		Arrays.sort(testObjs,new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
					if(o1 instanceof TestComparableObject && o2 instanceof TestComparableObject) {
						TestComparableObject obj1 = (TestComparableObject) o1;
						TestComparableObject obj2 = (TestComparableObject) o2;
						return -obj1.compareTo(obj2);
					}
				throw new RuntimeException("类型不一致");
			}
			
		});
		System.out.println(Arrays.toString(testObjs));
	}
	@Test
	//comparable接口实现测试
	public void testSortObjectMethod() {
		TestComparableObject []  testObjs = new TestComparableObject[3];
		testObjs[0] = new TestComparableObject("A", 13.4);
		testObjs[1] = new TestComparableObject("B", 99.3);
		testObjs[2] = new TestComparableObject("c", 0.99);
		
		Arrays.sort(testObjs);
		System.out.println(Arrays.toString(testObjs));
	}
	
	@Test
	//异常抛出测试
	public void testThrowRuntimeException() {
		throw new RuntimeException("测试异常抛出");
	}
	
	@Test 
	//DateTimeFromatter实例化
	public void testDateTimeFormatterClass() {
		
		//方式一
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime localDateTime =LocalDateTime.now();
		String time = formatter.format(localDateTime);
		System.out.println(time);//2021-05-30T11:01:56.0404963
		//逆向：字符串到日期
		TemporalAccessor templateTime = formatter.parse(time);
		System.out.println(templateTime);
		
		System.out.println("-------------------------------------------------------");
		
		//方式二【重点:自定义格式化】
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		String time2 = dateTimeFormatter.format(localDateTime);
		System.out.println(time2);
		//逆向 解析
		TemporalAccessor parse = dateTimeFormatter.parse(time2);
		System.out.println(parse);
	}
	@Test 
	//从毫秒数到instant实例
	public void testInstantOfEpochMilliMethod() {
		
		long milli = 1622342686646L;
		Instant instant = Instant.ofEpochMilli(milli);
		System.out.println(instant);
		
	}
	@Test 
	//获取1970年1月1日0时0分0秒(UTC开始的毫秒数)
	public void testInstantToEpochMilliMethod() {
		//获取实例
		Instant instant = Instant.now();
		//设置时区
		OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
		long milli = instant.toEpochMilli();
		System.out.println(milli);
		
	}
	@Test 
	//instant实例
	public void testInstantClass() {
		//获取实例
		Instant instant = Instant.now();
		//设置时区
		OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
		System.out.println(offsetDateTime);
		
	}
	@Test 
	//minus方法 减少天数
	public void testDateTimeMinusMethod() {
		LocalDate localDate = LocalDate.now();
		LocalDate newLocalDate = localDate.minusDays(10);
		//
		System.out.println(localDate);
		System.out.println(newLocalDate);
		
	}
	@Test 
	//plus方法 追加天数
	public void testDateTimePlusMethod() {
		LocalDate localDate = LocalDate.now();
		LocalDate newLocalDate = localDate.plusDays(5);
		//
		System.out.println(localDate);
		System.out.println(newLocalDate);
		
	}
	
	@Test 
	//with方法 改成今年的第一百天
	public void testDateTimeWithMethod() {
		LocalDate localDate = LocalDate.now();
		LocalDate newLocalDate = localDate.withDayOfYear(100);
		//
		System.out.println(localDate);
		System.out.println(newLocalDate);
		
	}
	
	@Test 
	//get方法 
	public void testDateTimeGetMethod() {
		LocalDateTime localDateTime = LocalDateTime.of(2021,05,10,11,10,59);
		
		System.out.println(localDateTime.getDayOfYear());
		System.out.println(localDateTime.getDayOfMonth());
		System.out.println(localDateTime.getMonth());
		System.out.println(localDateTime.getMinute());
		System.out.println(localDateTime.getMonthValue());
	}
	@Test 
	//指定年月日时分秒 
	public void testDateTimeOfMethod() {
		LocalDateTime localDateTime = LocalDateTime.of(2021,05,10,11,10,59);
		System.out.println(localDateTime);
		
		System.out.println(localDateTime.getDayOfYear());
		System.out.println(localDateTime.getDayOfMonth());
		System.out.println(localDateTime.getMonth());
		System.out.println(localDateTime.getMinute());
		System.out.println(localDateTime.getMonthValue());
	}
	
	@Test 
	//jdk8新增的时间api测试
	public void testDateTimeNowMethod() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		System.out.println(localDate);
		System.out.println(localTime);
		System.out.println(localDateTime);
		
		
	}
	
	@Test 
	//时间格式化测试
	public void testSimpleDateFormat() throws ParseException {
		//指定格式
		String format = "yyyy-MM-dd HH:mm:ss";
		//实例化simpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String time = "2022-10-29 09:12:10";
		Date date = sdf.parse(time);
		
		System.out.println(date);
		
	}
	
	
}
