package bank.test;

import java.util.Scanner;

import bank.card.CXCard;
import bank.service.OpenCXCard;
import bank.service.SystemView;

/*
 * 测试方法类
 * 此方法用于测试银行程序
 * 
 */

public class Test {
	public static void main(String[] args) {
		// 实例化储蓄卡对象
		CXCard cxk = new CXCard();
		// 实例化主界面
		SystemView zjm = new SystemView();
		try {
			// 创建死循环，保证程序功能完成后可返回主界面
			for (int i = 0; i >= 0; i++) {
				// 构建ATM菜单界面
				zjm.SystemView();
				Scanner sc = new Scanner(System.in);
				int in = sc.nextInt();
				// 判断输入数字是否符合界面要求
				if (in == 0 || in == 1 || in == 2 || in == 3 || in == 4) {
					// 开储蓄卡账户功能
					if (in == 0) {
						// 实例化开储蓄卡账户的类
						OpenCXCard atm = new OpenCXCard();
						System.out.println("****************************");
						System.out.println("你选择的为开储蓄卡账户功能");
						// 调用开户方法
						atm.kaihu();
						continue;
					}
					// 查询功能
					if (in == 1) {
						System.out.println("****************************");
						System.out.println("你选择的为查询功能");
						// 调用查询方法
						cxk.chaxun();
						continue;
					}
					// 存款功能
					if (in == 2) {
						System.out.println("****************************");
						System.out.println("你选择的为存款功能");
						//调用存款方法
						cxk.cun();
						continue;
					}
					// 取款功能
					if (in == 3) {
						System.out.println("****************************");
						System.out.println("你选择的为取款功能");
						//调用取款方法
						cxk.qu();
						continue;
					}
					// 退出功能
					if (in == 4) {
						System.out.println("谢谢使用！");
						break;
					}
				}
				// 不符合菜单内容打印
				else {
					System.out.println("你选择的菜单不存在！");
				}
			}
			// 异常抛出打印
		} catch (Exception e) {
			System.out.println("输入有误");
		}
	}
}