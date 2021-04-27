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
		//���ô��ڴ�С
		this.setSize(400,300);
		//���ô�������
		this.setTitle("������");
		getToolkit();
		//���ó�ʼλ��
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width-this.getWidth())/2;
		int y = (screen.height-this.getHeight())/2-32;
		this.setLocation(x, y);
		//���ñ���
        lblTitle.setFont(new java.awt.Font("�����п�", Font.BOLD, 33));
        lblTitle.setForeground(Color.green);
        lblTitle.setText("��ƱԤ��ϵͳ");
        lblTitle.setBounds(new Rectangle(92, 54, 220, 87));
		//���ð�ť
        btnBookingTicket.setBounds(new Rectangle(40, 177, 98, 30));
        btnBookingTicket.setFont(new java.awt.Font("�����п�", Font.BOLD, 15));
        btnBookingTicket.setForeground(Color.red);
        btnBookingTicket.setToolTipText("");
        btnBookingTicket.setText("��ƱԤ��");
        btnBookingTicket.addActionListener(new MainFrame_btnBookingTicket_actionAdapter(this));
        btnOrder.setBounds(new Rectangle(155, 177, 98, 30));
        btnOrder.setFont(new java.awt.Font("�����п�", Font.BOLD, 15));
        btnOrder.setForeground(Color.magenta);
        btnOrder.setText("��������");
        btnOrder.addActionListener(new MainFrame_btnOrder_actionAdapter(this));
        btnexit.setBounds(new Rectangle(270, 177, 98, 30));
        btnexit.setFont(new java.awt.Font("�����п�", Font.BOLD, 15));
        btnexit.setForeground(Color.blue);
        btnexit.setToolTipText("");
        btnexit.setText("�˳�");
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
	//����¼�����
    //����Ԥ����Ʊ��ť�ļ�����
    //����Ԥ����Ʊ��ť�ļ�����
    public void btnOrder_actionPerformed(ActionEvent e) {
    	new OrderFrame();
    }
    
    //��������ȡƱ��ť�ļ�����
    public void btnexit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    //�����˳���ť�ļ�����
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
