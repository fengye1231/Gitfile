package com.ghw.frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class MainFrame extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
    JLabel lblTitle = new JLabel();
    JButton btnBookingTicket = new JButton();
    JButton btnOrder = new JButton();
    JButton btnexit = new JButton();
    JPanel jPanel1 = new JPanel();
	public MainFrame(){
	      try {
	            setDefaultCloseOperation(EXIT_ON_CLOSE);
	            jbInit();
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	}

	private void jbInit() throws Exception{
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
		// TODO Auto-generated method stub
		//设置窗口大小
		this.setSize(400,300);
		//设置窗口名称
		this.setTitle("主界面");
		getToolkit();
		//设置初始位置
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width-this.getWidth())/2;
		int y = (screen.height-this.getHeight())/2-32;
		this.setLocation(x, y);
		//设置标题
        lblTitle.setFont(new java.awt.Font("华文行楷", Font.BOLD, 33));
        lblTitle.setForeground(Color.green);
        lblTitle.setText("机票预订系统");
        lblTitle.setBounds(new Rectangle(92, 54, 220, 87));
		//设置按钮
        btnBookingTicket.setBounds(new Rectangle(40, 177, 98, 30));
        btnBookingTicket.setFont(new java.awt.Font("华文行楷", Font.BOLD, 15));
        btnBookingTicket.setForeground(Color.red);
        btnBookingTicket.setToolTipText("");
        btnBookingTicket.setText("机票预订");
        btnBookingTicket.addActionListener(new MainFrame_btnBookingTicket_actionAdapter(this));
        btnOrder.setBounds(new Rectangle(155, 177, 98, 30));
        btnOrder.setFont(new java.awt.Font("华文行楷", Font.BOLD, 15));
        btnOrder.setForeground(Color.magenta);
        btnOrder.setText("订单管理");
        btnOrder.addActionListener(new MainFrame_btnOrder_actionAdapter(this));
        btnexit.setBounds(new Rectangle(270, 177, 98, 30));
        btnexit.setFont(new java.awt.Font("华文行楷", Font.BOLD, 15));
        btnexit.setForeground(Color.blue);
        btnexit.setToolTipText("");
        btnexit.setText("退出");
        btnexit.addActionListener(new MainFrame_btnexit_actionAdapter(this));
        contentPane.setToolTipText("");
        jPanel1.setBounds(new Rectangle(335, 234, 10, 10));
        contentPane.add(btnBookingTicket);
        contentPane.add(btnOrder);
        contentPane.add(btnexit);
        contentPane.add(jPanel1);
        contentPane.add(lblTitle);
        this.setVisible(true);
        this.setResizable(false);
        
	}
	//添加事件监听
    //监听预订机票按钮的监听器
    //监听预订机票按钮的监听器
    public void btnOrder_actionPerformed(ActionEvent e) {
    	new OrderFrame();
    }
    
    //监听付费取票按钮的监听器
    public void btnexit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    //监听退出按钮的监听器
    public void btnBooking_actionPerformed(ActionEvent e) {
    	new BookingFrame();
    }
    

class MainFrame_btnBookingTicket_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_btnBookingTicket_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnBooking_actionPerformed(e);
    }
}


class MainFrame_btnexit_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_btnexit_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnexit_actionPerformed(e);
    }

}
class MainFrame_btnOrder_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_btnOrder_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnOrder_actionPerformed(e);
    }
}
}
