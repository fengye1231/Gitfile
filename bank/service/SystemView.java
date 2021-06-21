package bank.service;

import java.util.ArrayList;
import java.util.List;

/*
 * 模拟银行的ATM界面
 * 实现功能的选择
 * 使用了ArrayList来实现菜单功能
 * 
 */

public class SystemView {
	public void SystemView() {
		System.out.println("欢迎使用ATM系统 ");
		System.out.println("****************************");
		List<String> li = new ArrayList<String>();
		li.add("开户");
		li.add("查询");
		li.add("存款");
		li.add("取款");
		li.add("退出");
		//循环List li 输出菜单列表
		for (int i = 0; i < 5; i++) {
			System.out.println(i + "-----" + li.get(i));
		}
		System.out.println("****************************");
		System.out.println("请选择你要执行的功能： ");
	}
}
