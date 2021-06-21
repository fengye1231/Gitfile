package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bank.card.CXCard;

/*
 * 银行类
 * 
 */

public class Bank {
	// 银行类属性
	private String name = "建设银行";
	// Bank 的List集合，用来存储银行卡号
	public List<String> list = new ArrayList<>();
	// Bank 的Map集合，用来存储 <银行卡号，储蓄卡对象>键值对
	public Map<String, CXCard> map = new HashMap<>();

	// 私有化构造方法
	private Bank() {
		super();
	}

	// 私有bank对象
	private static Bank bank = new Bank();

	// 私有静态方法，供其他类调用时实例化Bank对象
	public static Bank getBank() {
		return bank;
	}
}