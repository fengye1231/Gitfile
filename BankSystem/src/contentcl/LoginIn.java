package contentcl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class LoginIn extends JFrame{

	JTextField f1;
    JTextField f2;
    JButton b1;
    JButton b2;
    String power;//表示权限
    String imgePath = "225091.jpg";
    JPanel p5;
    
    
    
    

    
    
    LoginIn(){
    	Container cp=getContentPane();
    	Label l1=new Label("管理员账号：");
    	l1.setFont(new  java.awt.Font("华文琥珀", Font.PLAIN  + Font.BOLD , 35));
    	l1.setForeground(Color.black);
    	
    	
    	Label l2=new Label("密码：");
    	l2.setFont(new  java.awt.Font("华文琥珀", Font.PLAIN  + Font.BOLD , 35));
    	l2.setForeground(Color.black);
    	
    	
    	ImageIcon img=new ImageIcon("系统背景.jpg");
    	JLabel lb1=new JLabel(img);
    	lb1.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
    	this.getLayeredPane().add(lb1,new Integer(Integer.MIN_VALUE));
    	JPanel imagePanel=(JPanel)this.getContentPane();
    	imagePanel.setOpaque(false);
  
    	JPanel p1=new JPanel();
    	JPanel p2=new JPanel();
    	JPanel p3=new JPanel();
    	JPanel p4=new JPanel();
    	p5=new JPanel(){
          
        };
 

        f1=new JTextField(15);
        f2=new JPasswordField(15); 
        b1=new JButton("登录");
        b1.setBorderPainted(false);
        b1.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 30));
        b1.setForeground(Color.red);
        
        
		
        b2=new JButton("重置");
        b2.setBorderPainted(false);
        b2.setFont(new  java.awt.Font("华文楷体", Font.PLAIN  + Font.BOLD , 30));
        b2.setForeground(Color.red);
		
        p1.setBackground(Color.black);
        p2.add(l1);
        p2.add(f1);
        
        p2.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
        p2.setBackground(Color.pink);
        p3.add(l2);
        p3.add(f2);
        p3.setBackground(Color.pink);
        p3.setBorder(new MatteBorder(0,0,0,0,Color.CYAN));
        p4.add(b1);
        p4.add(b2);
        p4.setBorder(new MatteBorder(-3,-3,-3,-3,Color.CYAN));
        p4.setBackground(Color.ORANGE);
        p5.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        p5.add(p2);
        p5.add(p3);
        p5.add(p4);
        cp.add(p5,BorderLayout.CENTER);       
        b1.addActionListener(new Enter());
        b2.addActionListener(new ReWrite());
        addWindowListener(new winClose());
 }

    

    
    public static void main(String[] args) {
  LoginIn log=new LoginIn();
  log.setTitle("银行储蓄管理系统登录界面");
  log.setSize(1400, 900); 		//设置窗体大小
  log.setLocation(250,50);		//设置位置
  log.setVisible(true);
  

 }

 class Enter implements ActionListener{
  public void actionPerformed(ActionEvent e)
  {  
              if((f1.getText()).equals("admin")&&(f2.getText()).equals("123"))
              {
            	  JOptionPane.showMessageDialog(null, "登录成功！权限为管理员");
            	  dispose();
            	  power="adminstrator";
            	  new Menu();
            	  

              }
              else if((f1.getText()).equals("snake")&&(f2.getText()).equals("123456"))
              {
              JOptionPane.showMessageDialog(null, "登录成功!登录成功！用户权限是user");
              power="adminstrator";
              }
        else JOptionPane.showMessageDialog(null, "登录失败，请重新登录！");
  }
 }
 	class ReWrite implements ActionListener{
 		public void actionPerformed(ActionEvent e)
 		{
 			f1.setText("");
 			f2.setText("");
 			f1.requestFocus();
  }
 }
 
 
 class winClose extends WindowAdapter
 {
 public void windowClosing(WindowEvent e)
 {
    (e.getWindow()).dispose();
  System.exit(0);
 }
 }
} 
