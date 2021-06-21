package bank.service;

import bank.Bank;

/*
 * ATM操作类，对存款进行操作
 * 
 */

public abstract class ATM {
	private Bank bank; // 银行属性
	private double yue; // 卡上余额

	public double getYue() {
		return yue;
	}

	public void setYue(double yue) {
		this.yue = yue;
	}

	// 查询方法
	public void yue() {
		System.out.println("卡上余额为：" + this.yue);
	}

	// 存款方法
	public void cun(double cun) {
		if (cun < 0) {
			System.out.println("输入有误");
		} else {
			this.yue += cun;
			System.out.println("卡上余额为：" + this.yue);
		}
	}

	// 取款方法
	public void qu(double qu) {
		if (qu > this.yue) {
			System.out.println("余额不足");
		} else {
			this.yue -= qu;
			System.out.println("卡上余额为：" + this.yue);
		}
	}

}
