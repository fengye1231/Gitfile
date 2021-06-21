package bank.card;

import java.util.Scanner;

import bank.Bank;

/*
 * 储蓄卡实体类
 * 
 */

public class CXCard implements Card {
	// 储蓄卡属性
	private Bank bank;
	private String name;
	private String shenfen;
	private String card;
	private String password;
	private double balance;

	// 生成toString ();
	@Override
	public String toString() {
		return "姓名=" + name + "\t\n" + "身份证=" + shenfen + "\t\n" + "卡号=" + card + "\t\n" + " 账户金额=" + balance;
	}

	// 对储蓄卡的属性进行get set设置
	public Bank getbank() {
		return bank = Bank.getBank();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShenfen() {
		return shenfen;
	}

	public void setShenfen(String shenfen) {
		this.shenfen = shenfen;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// 查询储蓄卡方法，此方法作用是获得要操作的储蓄卡在Bank
	// Map中的key，即银行卡号，进而可通过Bank.getBank().map.get(s)，获取到这个储蓄卡对象
	public void chaxun() {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("请输入你要查询的卡号：");
				String s = sc.nextLine();
				// 遍历Bank 的Map集合，比对银行卡号
				for (String temp : Bank.getBank().map.keySet()) {
					if (s.equals(temp)) {
						this.card = s;
					}
				}
				if (s.equals(card)) // 验证卡号是否存在
				{
					int i = 0;
					System.out.println("请输入你的密码：");
					while (true) {
						Scanner sc1 = new Scanner(System.in);
						String st1 = sc1.nextLine();
						if (st1.equals(Bank.getBank().map.get(s).getPassword())) {
							System.out.println("你查询的账号信息为：");
							System.out.println("******************************");
							System.out.println(Bank.getBank().map.get(s));
							break;
						} else {
							i++;
							if (i == 3) {
								System.out.println("密码输入错误超过3次");
								break;
							} else {
								System.out.println("密码输入有误");
							}
						}
					}
				} else {
					System.out.println("卡号输入有误请确认");
				}
			} catch (Exception e) {
				System.out.println("输入有误");
			}
			break;
		}
	}

	// 存款方法
	@Override
	public void cun() {
		// 实例化储蓄卡对象
		CXCard cxcard = new CXCard();
		// 存款前先获得储蓄卡对象
		cxcard.chaxun();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要存入的金额：");
		double cun = sc.nextDouble();
		// 实现存款方法
		if (cun < 0) {
			System.out.println("你输入的数据无效，系统将自动退出！谢谢使用");
		} else {
			this.balance += cun;
			Bank.getBank().map.get(cxcard.card).setBalance(balance);
			System.out.println("存款成功，剩余金额" + Bank.getBank().map.get(cxcard.card).getBalance());
		}
	}

	// 取款方法运算
	@Override
	public void qu() {
		// 实例化储蓄卡对象
		CXCard cxcard = new CXCard();
		// 取款前先获得储蓄卡对象
		cxcard.chaxun();
		Scanner sc1 = new Scanner(System.in);
		System.out.println("请输入你要取款的金额：");
		double qu = sc1.nextDouble();
		// 实现取款方法
		if (qu < balance) {
			this.balance -= qu;
			Bank.getBank().map.get(cxcard.card).setBalance(balance);
			System.out.println("取款成功，剩余金额：" + Bank.getBank().map.get(cxcard.card).getBalance());
		} else if (qu < 0) {
			System.out.println("你输入的数据无效，系统将自动退出！谢谢使用");
			;
		} else {
			System.out.println("余额不足");
		}
	}

	@Override
	public void getyu() {
		// 实例化储蓄卡对象
		CXCard cxcard = new CXCard();
		System.out.println("剩余金额" + cxcard.getBalance());
	}
}