package com.bs;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.sys.entity.Dept;
import com.bs.sys.service.IDeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTests {

	@Autowired
	private IDeptService deptService;
	@Test
	void contextLoads() {
		Dept dept=deptService.getDeptByName("一一班");
		System.out.println(dept.getId());
	}

}
