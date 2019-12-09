package com.dongxiaoxing.redis.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dongxiaoxing.redis.entity.User;
import com.dongxiaoxing.redis.utils.RandomUtil;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class RedisTest {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	
	//4.使用JDK系列化方式保存5万个user随机对象到Redis，并计算耗时
	@Test
	public void jdkTest() {
		//保存前时间
		long time1 = System.currentTimeMillis();
		//选择redis类型为string
		ValueOperations ops_jdk = redisTemplate.opsForValue();
		//循环生成50000个user对象
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			
			//编号
			user.setId(i);
			//姓名
			user.setName(RandomUtil.getChineseName());
			//性别
			user.setSex(RandomUtil.getSex());
			//手机号
			user.setPhone(RandomUtil.getPhone());
			//邮箱
			user.setEmail(RandomUtil.getEmail());
			//生日
			Date birthday = RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00");
			user.setBirthday(birthday);
			
			//System.out.println(user);
			ops_jdk.set(i+"", user);
			
		}
		
		//保存后时间
		long time2 = System.currentTimeMillis();
		long time = time2 - time1;
		System.out.println("序列化方式:jdk, 所耗时间:"+time+", 保存数量:50000");
	}
	
	
	//5.使用JSON系列化方式保存5万个user随机对象到Redis，并计算耗时
	@Test
	public void jsonTest() {
		//保存前时间
		long time1 = System.currentTimeMillis();
		//选择redis类型为string
		ValueOperations ops_jdk = redisTemplate.opsForValue();
		//循环生成50000个user对象
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			
			//编号
			user.setId(i);
			//姓名
			user.setName(RandomUtil.getChineseName());
			//性别
			user.setSex(RandomUtil.getSex());
			//手机号
			user.setPhone(RandomUtil.getPhone());
			//邮箱
			user.setEmail(RandomUtil.getEmail());
			//生日
			Date birthday = RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00");
			user.setBirthday(birthday);
			
			//System.out.println(user);
			ops_jdk.set(i+"", user);
			
		}
		
		//保存后时间
		long time2 = System.currentTimeMillis();
		long time = time2 - time1;
		System.out.println("序列化方式:json, 所耗时间:"+time+", 保存数量:50000");
	}
	
	
	//6.使用Redis的Hash类型保存5万个user随机对象到Redis，并计算耗时
	@Test
	public void hashTest() {
		//保存前时间
		long time1 = System.currentTimeMillis();
		//选择redis类型为hash
		HashOperations ops_hash = redisTemplate.opsForHash();
		//循环生成50000个user对象
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			
			//编号
			user.setId(i);
			//姓名
			user.setName(RandomUtil.getChineseName());
			//性别
			user.setSex(RandomUtil.getSex());
			//手机号
			user.setPhone(RandomUtil.getPhone());
			//邮箱
			user.setEmail(RandomUtil.getEmail());
			//生日
			Date birthday = RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00");
			user.setBirthday(birthday);
			
			//System.out.println(user);
			ops_hash.put("user"+i, i+"", user.toString());
			
		}
		//保存后时间
		long time2 = System.currentTimeMillis();
		long time = time2 - time1;
		System.out.println("序列化方式:hash, 所耗时间:"+time+", 保存数量:50000");
	}

}
