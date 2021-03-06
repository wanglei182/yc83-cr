package com.yc.library.dao;

import java.math.BigDecimal;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

import com.yc.library.bean.Book;

/**
 * 	单元测试类 ,用于测试  BookDao
 * @author 廖彦
 *
 */
public class BookDaoTest {
	
	/**
	 * 	单元测试方法, 要求不能带任何参数
	 * 
	 * 	在该方法上必须加入  Test 注解, 给程序看的 注释
	 */
	@Test
	public void testInsert() {
		// 创建dao对象
		BookDao bDao = new BookDao();
		Book book = new Book();
		// 如何多次测试 insert 方法不报错
		/**
		 * 	方法一: 每次测试时,删除该(  id = 1 )记录
		 * 	方法二: 生成不同的id值
		 * 		1.  使用时间戳    System.currentTimeMillis()  long  单线程不重复
		 * 			问题, 值太大
		 * 		2.	使用 oracle 数据库   序列   可以在多线程状态运行时不重复,  必须改sql
		 * 		3.  使用 UUID 类生成  java 随机生成不重复序列的类,  生成的是 String, 
		 * 				理论上来说几千年才会重复一次
		 */
		// book.setId(System.currentTimeMillis());
		book.setName("C++编程思想");
		int cnt = bDao.insert(book);
		// 断言类
		Assert.assertEquals(1, cnt);
	}
	
	@Test
	public void testSelectAll() {
		// 创建dao对象
		BookDao bDao = new BookDao();
		List<Map<String,Object>> list = bDao.selectAll();
		// 断言类
		Assert.assertTrue(list.size()>0);
		// List<Book> list =   bDao.selectAll();
		// 1, JDBC 的数据类型与实体类的数据类型不一致
		// 2, DBHelper 返回List<Map> 不方便操作
		Map<String,Object> book = list.get(0);
		Assert.assertEquals(BigDecimal.valueOf(1), book.get("ID"));
		System.out.println(book);
		
		System.out.println("===============================");
		
		List<Book> list1 = bDao.selectAll1();
		Assert.assertTrue(list1.size()>0);
		Book book1 = list1.get(0);
		Assert.assertEquals(Long.valueOf(1), book1.getId());
		System.out.println(book1);
	}

}
