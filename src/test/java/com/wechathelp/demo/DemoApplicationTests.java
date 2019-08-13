package com.wechathelp.demo;

import com.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	DataSource dataSource;
	@Autowired
	UserMapper userMapper;

	static ReentrantLock mainlock = new ReentrantLock();

	ThreadPoolExecutor threadPoolExecutor;



	@Test
	public void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println("########################################");
		System.out.println(connection);
		connection.close();
	}

	@Test
	public void getSQL() {
		System.out.println(userMapper.getUserById(1));
		System.out.println(userMapper.getTotalNum());
	}



	public static void main(String[] args){
		Thread t1 = new Thread(()-> {

			for(int i = 0;;i++){
				System.out.print(i+" ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(()->{

			for(int i = 100;;i++){
				System.out.print(i+" ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		t1.start();
		t2.start();
	}

}
