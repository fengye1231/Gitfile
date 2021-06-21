package dazuoye;

/**
 * 功能：学生成绩管理系统
 * 步骤1、登录界面的静态实现
 * author：ywq
 */
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public  class Login extends JFrame implements  ActionListener{
	//定义组件
	JFrame frame=new JFrame();
	JPanel jp1,jp2,jp3;//面板
	JLabel jlb1,jlb2;//标签
	JButton jb1,jb2;//按钮
	
	//构造函数
	public Login(){
		//创建面板
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		//创建标签
		jlb1=new JLabel("请按照你的身份选择登陆");
		
		//创建按钮
		jb1=new JButton("信息管理人员");
		jb2=new JButton("学生");
				
		//设置布局管理
		frame.setLayout(new GridLayout(3, 1));//网格式布局
		
		//加入各个组件
		jp1.add(jlb1);
			
		jp3.add(jb1);
		jp3.add(jb2);
		
		//加入到JFrame
		frame.add(jp2);
		frame.add(jp1);
		//this.add(jp2);
		frame.add(jp3);
		
		//设置窗体
		frame.setTitle("用户登录界面");//窗体标签
		frame.setSize(400, 300);//窗体大小
		frame.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame
		frame.setVisible(true);//显示窗体
		
		//锁定窗体
		//this.setResizable(false);
				
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   frame.setVisible(false);
				new ManagerLogin();
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   frame.setVisible(false);
				new StudentLogin();
			}
		});
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
