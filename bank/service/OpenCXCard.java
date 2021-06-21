package bank.service;

import java.util.Random;
import java.util.Scanner;
import bank.Bank;
import bank.card.CXCard;

/*
 * 储蓄卡开户类
 * 
 */

public class OpenCXCard {
	//实例化
	private Bank bank = Bank.getBank();

	public void kaihu() {
		// 开户方法
		// 输入姓名
		CXCard cxk = new CXCard();
		try {
			Scanner sc1 = new Scanner(System.in);
			System.out.println("请输入你的姓名：");
			String st = sc1.nextLine();
			cxk.setName(st);
			// 输入身份证
			System.out.println("请输入你的身份证号：");
			for (int ig = 0; ig >= 0; ig++) {
				Scanner sc2 = new Scanner(System.in);
				String str = sc2.nextLine();
				String st1 = "\\d{15}|\\d{17}[\\dxX]";
				if (str.matches(st1)) {
					cxk.setShenfen(str);
					break;
				} else {
					System.out.println("输入的身份证号不是18位身份号");
				}
			}
			// 设置账户密码
			System.out.println("请输入你的密码：");
			for (int ig = 0; ig >= 0; ig++) {
				Scanner sc3 = new Scanner(System.in);
				String pass = sc3.nextLine();
				String mm = "\\d{6}";
				if (pass.matches(mm)) {
					cxk.setPassword(pass);
					break;
				} else {
					System.out.println("请输入正确的6位数密码");
				}
			}
			// 生成6位数储蓄卡号
			Random a = new Random();
			int[] ch = new int[6];
			StringBuilder str = new StringBuilder();
			String s;
			for (int i = 0; i < 6; i++) {
				ch[i] = a.nextInt(9);
			}
			for (Integer c : ch) {
				str.append(c);
			}
			s = str.toString();
			cxk.setCard(s);
			File file=new File("users.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw=new FileWriter(file,true);

			String all="姓名："+st+"	身份证号："+str1+"	密码："+pass+"	账号："+s+"\r\n";
			fw.write(all);
			fw.close();
			// 将生成的数据导入银行卡list列表中
			Bank.getBank().list.add(cxk.getCard());
			// 将生成的数据导入银行卡Map列表中
			Bank.getBank().map.put(cxk.getCard(), cxk);
			System.out.println("开户成功，您的新卡号为：" + cxk.getCard());
		} catch (Exception e) {
			System.out.println("输入有误");
		}
	}
}