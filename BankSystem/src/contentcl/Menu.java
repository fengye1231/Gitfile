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
 * Menu��һ�� �˵��࣬Ҳ����Ϊ�ײ��һ����
 * �ṩ�������ܵİ�ť
 * 
 * ����δʹ�ò��֣� ����ʹ������̶��˸�����ǩ�Ͱ�ť��λ��
 *
 */

public class Menu extends JFrame implements ActionListener{
	
	JButton jb1, jb2, jb3,jb4,jb5,jb6,jb7, jb8,jb9,jb10;  //������ť
	JLabel jlb1, jlb2, jlb3;   //��ǩ
	public Menu() 
	{	//��ͼƬ���뵽��ǩ�У������ñ�ǩ�Ĵ�С����Ϊ���ڴ�С
		ImageIcon img=new ImageIcon("ϵͳ����.jpg");
		JLabel lb1=new JLabel(img);
		lb1.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
		this.getLayeredPane().add(lb1,new Integer(Integer.MIN_VALUE));
		JPanel imagePanel=(JPanel)this.getContentPane();
		imagePanel.setOpaque(false);
		
		jb1 = new JButton("�˻���ѯ");
		jb2 = new JButton("���ҵ��");
		jb3 = new JButton("ȡ��ҵ��");
		jb4 = new JButton("ת�˰���");
		jb5 = new JButton("��������");
		jb6 = new JButton("�����˻�");
		jb7 = new JButton("��Ϣ����");
		jb8 = new JButton("��ʧ����");
		jb9 = new JButton("�˳�ϵͳ");
		jb10= new JButton("�˻�ע��");
		jb1.setContentAreaFilled(false);
		jb1.setBorderPainted(false);
		jb1.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb1.setForeground(Color.BLACK);
		
		jb2.setContentAreaFilled(false);
		jb2.setBorderPainted(false);
		jb2.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb2.setForeground(Color.BLACK);
		
		jb3.setContentAreaFilled(false);
		jb3.setBorderPainted(false);
		jb3.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb3.setForeground(Color.BLACK);
		
		jb4.setContentAreaFilled(false);
		jb4.setBorderPainted(false);
		jb4.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb4.setForeground(Color.BLACK);
		
		jb5.setContentAreaFilled(false);
		jb5.setBorderPainted(false);
		jb5.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb5.setForeground(Color.BLACK);
		
		jb6.setContentAreaFilled(false);
		jb6.setBorderPainted(false);
		jb6.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb6.setForeground(Color.BLACK);
		
		jb7.setContentAreaFilled(false);
		jb7.setBorderPainted(false);
		jb7.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb7.setForeground(Color.BLACK);
		
		jb8.setContentAreaFilled(false);
		jb8.setBorderPainted(false);
		jb8.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb8.setForeground(Color.red);
		
		jb9.setContentAreaFilled(false);
		jb9.setBorderPainted(false);
		jb9.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb9.setForeground(Color.red);
		
		jb10.setContentAreaFilled(false);
		jb10.setBorderPainted(false);
		jb10.setFont(new  java.awt.Font("���Ŀ���", Font.PLAIN  + Font.BOLD , 40));
		jb10.setForeground(Color.red);
		
		
		jlb1 = new JLabel("���д������ϵͳ");
		jlb1.setFont(new   java.awt.Font("������κ",   1,   38)); //�����������ͣ� �Ƿ�Ӵ֣��ֺ�
		jlb2 = new JLabel("��ӭ��");
		jlb2.setFont(new   java.awt.Font("������κ",   1,   38));
		jlb3 = new JLabel("��ѡ�����");
		jlb3.setFont(new   java.awt.Font("������κ",   1,   38));
		
		
		jb1.addActionListener(this);   //�¼�����
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		jb7.addActionListener(this);
		jb8.addActionListener(this);
		jb9.addActionListener(this);
		jb10.addActionListener(this);
		
		this.setTitle("�����˻�����ϵͳ");  //���ô������
		this.setSize(1400, 900); 		//���ô����С
		this.setLocation(250,50);		//����λ��
		this.setLayout(null);			//���ò��֣������ò���
		
		//���ð�ť��λ�úʹ�С
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
		
		//���ñ�ǩ��λ�úʹ�С
		jlb1.setBounds(1000,320,500,50);
		jlb2.setBounds(1100,360,150,50);
		jlb3.setBounds(1060,400,300,50);
		
		this.add(jb1);   //���봰��
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
		
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);  //���ÿɹر�
	     
	    this.setVisible(true);  //���ÿɼ�
	    this.setResizable(false);	//���ò��������С
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="�˻���ѯ")
		{
			//String order = e.getActionCommand();
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="���ҵ��")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="ȡ��ҵ��")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="ת�˰���")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="��������")
		{
			new Login(e.getActionCommand());
		}
		else if (e.getActionCommand()=="�����˻�")
		{
			new Register();  //��ת��������
		}
		else if (e.getActionCommand()=="��ʧ����")
		{
			new ReportLose();  
		}
		else if (e.getActionCommand()=="�˳�ϵͳ")
		{
			System.exit(0);
		}
		else if (e.getActionCommand()=="��Ϣ����")
		{
			new Login(e.getActionCommand());//�ȵ�¼ ��½�ɹ���  �������ں��������µ��ȷ���󵯳���Ϣ��Ϣ���ҽ���Ϣ���µ������
		}
		else if (e.getActionCommand()=="�˻�ע��")
		{
			new Login(e.getActionCommand());//�ȵ�¼ ��¼�ɹ�����ȷ������ʾ�˻��Ѿ�ע��
		}
		
	}

}
