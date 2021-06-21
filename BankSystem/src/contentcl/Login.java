package contentcl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{

	JButton jb1, jb2, jb3;  //按钮
	JPanel jp1,jp2,jp3, jp4;		//面板
	JTextField jtf;   //文本框
	JLabel jlb1, jlb2, jlb3; //标签
	JPasswordField jpf; //密码框
	
	String name = "123";   //账号密码
	String pwd = "123"; 
	String order;
	
	public Login(String order) {
		this.order = order;
		// TODO Auto-generated constructor stub
		jb1 = new JButton("登录");
		jb1.setContentAreaFilled(false);
		jb1.setBorderPainted(false);
		jb1.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 30));
		jb1.setForeground(Color.BLACK);
		
		
		jb2 = new JButton("重置");
		jb2.setContentAreaFilled(false);
		jb2.setBorderPainted(false);
		jb2.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 30));
		jb2.setForeground(Color.BLACK);
		
		//设置按钮监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		jp1 = new JPanel();  //创建面板
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jlb1 = new JLabel("用户账号");  //添加标签
		jlb2 = new JLabel("  密  码");
		jlb1.setFont(new   java.awt.Font("华文新魏",   1,   38)); //设置字体类型， 是否加粗，字号
		jlb2.setFont(new   java.awt.Font("华文新魏",   1,   38)); //设置字体类型， 是否加粗，字号
		
		jtf = new JTextField(10);	//创建文本框和密码框
		jpf = new JPasswordField(10);
		
		//加入面板中
		jp1.add(jlb1);//用户账号，文本框
		jp1.add(jtf);
		jp1.setOpaque(false);
		
		
		jp2.add(jlb2);//密码  密码框
		jp2.add(jpf);
		jp1.setOpaque(false);
		
		jp3.add(jb1);// 登录  重置
		jp3.add(jb2);
		jp1.setOpaque(false);
		
		
		ImageIcon im=new ImageIcon("登录.jpg");
		JLabel lb11=new JLabel(im);
		lb11.setBounds(0,0,im.getIconWidth(),im.getIconHeight());
		this.getLayeredPane().add(lb11,new Integer(Integer.MIN_VALUE));
		JPanel imagePanel=(JPanel)this.getContentPane();
		imagePanel.setOpaque(false);
		
		
		
		
		//将JPane加入JFrame中  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
        
        
       //设置布局
        this.setTitle("用户登录");
        this.setLayout(new GridLayout(3,1));  //设置布局
        this.setSize(500, 300);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)  
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        
        this.setVisible(true);  //设置可见
        this.setResizable(false);	//设置不可拉伸大小
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="登录")
		{
			try {
				login();
			} catch (HeadlessException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand()=="重置")  
        {  
             clear();  
        }
	}
	//清空账号和密码框
	private void clear() {
		// TODO Auto-generated method stub
		jtf.setText("");    //设置为空
        jpf.setText("");  
		
	}

	//验证登录信息，并做处理
	public void login() throws HeadlessException, IOException
	{
		//判断账户名和密码是否包含中文
		if (new Check().checkcountname(jtf.getText())||new Check().checkcountname(jpf.getText()))
		{
			JOptionPane.showMessageDialog(null, "用户名或密码存在中文，不合法!","消息提示",JOptionPane.WARNING_MESSAGE);
		}
		else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "账号密码为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
		}
		else if (jtf.getText().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "账号为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
		}
		else if (jpf.getText().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "密码为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
			
		}
		else if (new Check().check1(jtf.getText(),jpf.getText()))
		{
			JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);
			//dispose();  //使文原窗体消失
			if (order.equals("账户查询"))
			{
				new Inquiry(jtf.getText()); //跳转到登录个人信息选项界面，传入为用户名
				dispose();
			}
			if (order.equals("存款业务"))
			{
				new SaveMoney(jtf.getText());
				dispose();
			}
			if (order.equals("取款业务"))
			{
				new DrawMoney(jtf.getText());
				dispose();
			}
			if (order.equals("更改密码"))
			{
				new Modify(jtf.getText());
				dispose();
			}
			if (order.equals("转账办理"))
			{
				new Transfer(jtf.getText());
				dispose();
			}
			if (order.equals("利息计算"))
			{
				new ratecalculate(jtf.getText());
				dispose();
				
			}
			if (order.equals("账户注销"))
			{
				JOptionPane.showMessageDialog(null, "账户已注销","消息提示",JOptionPane.WARNING_MESSAGE);
				new delete(jtf.getText());
				dispose();
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "账户不存在或密码错误，请重新输入！","消息提示",JOptionPane.ERROR_MESSAGE);
			clear();  //调用清除函数
		}
	}
	

	
}
