package contentcl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * 
 * @author contentcl
 * Menu是一个 菜单类，也是最为底层的一个类
 * 提供各个功能的按钮
 * 
 * 此类未使用布局， 所以使用坐标固定了各个标签和按钮的位置
 *
 */

public class Menu extends JFrame implements ActionListener{
	
	JButton jb1, jb2, jb3,jb4,jb5,jb6,jb7, jb8,jb9,jb10;  //创建按钮
	JLabel jlb1, jlb2, jlb3;   //标签
	public Menu() 
	{	//将图片加入到标签中，并设置标签的大小正好为窗口大小
		ImageIcon img=new ImageIcon("系统背景.jpg");
		JLabel lb1=new JLabel(img);
		lb1.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
		this.getLayeredPane().add(lb1,new Integer(Integer.MIN_VALUE));
		JPanel imagePanel=(JPanel)this.getContentPane();
		imagePanel.setOpaque(false);
		
		jb1 = new JButton("账户查询");
		jb2 = new JButton("存款业务");
		jb3 = new JButton("取款业务");
		jb4 = new JButton("转账办理");
		jb5 = new JButton("更改密码");
		jb6 = new JButton("新增账户");
		jb7 = new JButton("利息计算");
		jb8 = new JButton("挂失办理");
		jb9 = new JButton("退出系统");
		jb10= new JButton("账户注销");
		jb1.setContentAreaFilled(false);
		jb1.setBorderPainted(false);
		jb1.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb1.setForeground(Color.BLACK);
		
		jb2.setContentAreaFilled(false);
		jb2.setBorderPainted(false);
		jb2.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb2.setForeground(Color.BLACK);
		
		jb3.setContentAreaFilled(false);
		jb3.setBorderPainted(false);
		jb3.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb3.setForeground(Color.BLACK);
		
		jb4.setContentAreaFilled(false);
		jb4.setBorderPainted(false);
		jb4.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb4.setForeground(Color.BLACK);
		
		jb5.setContentAreaFilled(false);
		jb5.setBorderPainted(false);
		jb5.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb5.setForeground(Color.BLACK);
		
		jb6.setContentAreaFilled(false);
		jb6.setBorderPainted(false);
		jb6.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb6.setForeground(Color.BLACK);
		
		jb7.setContentAreaFilled(false);
		jb7.setBorderPainted(false);
		jb7.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb7.setForeground(Color.BLACK);
		
		jb8.setContentAreaFilled(false);
		jb8.setBorderPainted(false);
		jb8.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb8.setForeground(Color.red);
		
		jb9.setContentAreaFilled(false);
		jb9.setBorderPainted(false);
		jb9.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb9.setForeground(Color.red);
		
		jb10.setContentAreaFilled(false);
		jb10.setBorderPainted(false);
		jb10.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 40));
		jb10.setForeground(Color.red);
		
		
		jlb1 = new JLabel("银行储蓄管理系统");
		jlb1.setFont(new   java.awt.Font("华文新魏",   1,   38)); //设置字体类型， 是否加粗，字号
		jlb2 = new JLabel("欢迎您");
		jlb2.setFont(new   java.awt.Font("华文新魏",   1,   38));
		jlb3 = new JLabel("请选择服务");
		jlb3.setFont(new   java.awt.Font("华文新魏",   1,   38));
		
		
		jb1.addActionListener(this);   //事件监听
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		jb7.addActionListener(this);
		jb8.addActionListener(this);
		jb9.addActionListener(this);
		jb10.addActionListener(this);
		
		this.setTitle("银行账户管理系统");  //设置窗体标题
		this.setSize(1400, 900); 		//设置窗体大小
		this.setLocation(250,50);		//设置位置
		this.setLayout(null);			//设置布局，不采用布局
		
		//设置按钮的位置和大小
		jb1.setBounds( 100, 80,240,100);   
		jb2.setBounds( 100,180,240,100);
		jb3.setBounds( 100,280,240,100);
		jb4.setBounds( 100,380,240,100);
		jb5.setBounds( 100,480,240,100);
		jb6.setBounds( 380,80,240,100);    
		jb7.setBounds( 380,180,240,100);
		jb8.setBounds( 380,280,240,100);
		jb10.setBounds( 380,380,240,100);
		jb9.setBounds( 1150,780,240,100);
		
		//设置标签的位置和大小
		jlb1.setBounds(1000,320,500,50);
		jlb2.setBounds(1100,360,150,50);
		jlb3.setBounds(1060,400,300,50);
		
		this.add(jb1);   //加入窗体
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		this.add(jb6);
		this.add(jb7);
		this.add(jb8);
		this.add(jb9);
		this.add(jb10);
		
		this.add(jlb1);
		this.add(jlb2);
		this.add(jlb3);
		
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);  //设置可关闭
	     
	    this.setVisible(true);  //设置可见
	    this.setResizable(false);	//设置不可拉伸大小
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="账户查询")
		{
			//String order = e.getActionCommand();
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="存款业务")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="取款业务")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="转账办理")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="更改密码")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="新增账户")
		{
			new Register();  //跳转开户界面
		}
		else if (e.getActionCommand()=="挂失办理")
		{
			new ReportLose();  
		}
		else if (e.getActionCommand()=="退出系统")
		{
			System.exit(0);
		}
		else if (e.getActionCommand()=="利息计算")
		{
			new Login(e.getActionCommand());//先登录 登陆成功后  弹出窗口后输入年月点击确定后弹出利息信息并且将利息更新到余额中
		}
		else if (e.getActionCommand()=="账户注销")
		{
			new Login(e.getActionCommand());//先登录 登录成功后点击确定键显示账户已经注销
		}
		
	}

}
