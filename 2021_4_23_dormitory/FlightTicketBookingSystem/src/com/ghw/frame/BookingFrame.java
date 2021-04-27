package com.ghw.frame;

import java.awt.*;
import java.awt.event.*;
//import java.sql.Date;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import com.ghw.dao.FlightDAO;
import com.ghw.dao.GuestDAO;
import com.ghw.dao.OrderDAO;
import com.ghw.dao.TicketDAO;
import com.ghw.model.*;
public class BookingFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����
    JLabel lblTitle = new JLabel();
    //�ÿ���Ϣ
    //����
    JLabel lblName = new JLabel();
    JTextField txtName = new JTextField();
    //�绰
    JLabel lblTelephone = new JLabel();
    JTextField txtTelephone = new JTextField();
    //��ַ
    JLabel lbladress = new JLabel();
    JTextField txtadress = new JTextField();
    //���֤��
    JLabel lblIDCard = new JLabel();
    JTextField txtIDCard = new JTextField();
    
    //������Ϣ��
    
    //����ʱ��
    JLabel lblstartTime = new JLabel();
    JComboBox<Integer> cmbSYear = new JComboBox<Integer>();
    JLabel lblSYear = new JLabel();
    JComboBox<Integer> cmbSMonth = new JComboBox<Integer>();
    JLabel lblSMonth = new JLabel();
    JLabel lblSDay = new JLabel();
    JComboBox<Integer> cmbSDay = new JComboBox<Integer>();
    int Sdays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    //����ʱ��
    JLabel lblreturnTime = new JLabel();
    JComboBox<Integer> cmbRYear = new JComboBox<Integer>();
    JLabel lblRYear = new JLabel();
    JComboBox<Integer> cmbRMonth = new JComboBox<Integer>();
    JLabel lblRMonth = new JLabel();
    JLabel lblRDay = new JLabel();
    JComboBox<Integer> cmbRDay = new JComboBox<Integer>();
    int Rdays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    //��ʼ��
    JLabel lblstartPlace = new JLabel();
    JTextField txtstartPlace = new JTextField();
    
    //Ŀ�ĵ�
    JLabel lblendPlace = new JLabel();
    JTextField txtendPlace = new JTextField();
    //��λ����
    JLabel lblseatClass = new JLabel();
    JComboBox<String> cmbseatClass = new JComboBox<String>();
    String[] seat = {"��ͨ��","���ò�","ͷ�Ȳ�"};
    //д**********
    //ͨ�������ѯ�õ���
    //***********
    JLabel lblNote = new JLabel();
    JLabel lblStart = new JLabel();
    JLabel lblStart2 = new JLabel();
   
    //�����
    JLabel lblFlightNo = new JLabel();
    JTextField txtFlightNo = new JTextField();
   
    //�۸�
    JLabel lblPrice = new JLabel();
    JTextField txtPrice = new JTextField();
   
    //���չ�˾
    JLabel lblplaneCompany = new JLabel();
    JTextField txtplaneCompany = new JTextField();
  
    //ʣ����λ
    JLabel lblsSeat = new JLabel();
    JTextField txtsSeat = new JTextField();
  
    //��ť����
    //�����ѯ
    JButton btnSearchFlights = new JButton();
   
    //��Ʊ
    JButton btnOrderTicket = new JButton();
   
    //����
    JButton btnReset = new JButton();
   
    //�������֤�Ų�ѯ
    JLabel lblSearchID = new JLabel();
    JLabel lblTravelerInformation = new JLabel();
    JLabel lblOrderTicketSearch = new JLabel();
    JTextField txtIDSearch = new JTextField();
   
    //���֤��ѯ
    JButton btnIDSearch = new JButton();
  
    //��ѯ���
    JLabel lblSearchResult = new JLabel();
    JTextArea areaResults = new JTextArea();
    
    //���水ť
    JButton btnsave = new JButton();
    //��ӡ�˵�
    JButton btnBill = new JButton();
	private Guest guest;
	
	JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "txt&java", "txt", "java");
    //JButton btnExit = new JButton();
    public BookingFrame() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
	private void jbInit() {
		// TODO Auto-generated method stub
        getContentPane().setLayout(null);
        //���ô�С
        this.setSize(564, 580);
        //����λ�þ���
        Toolkit toolkit = getToolkit().getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width-this.getWidth())/2;
        int y = (screen.height-this.getHeight())/2-32;
        this.setLocation(x, y);
        //���ƴ���
        //��������
        lblTitle.setFont(new java.awt.Font("�����п�", Font.BOLD, 25));
        lblTitle.setForeground(Color.red);
        lblTitle.setText("��ƱԤ��");
        lblTitle.setBounds(new Rectangle(197, 0, 107, 44));
        //�û���Ϣ
        lblName.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblName.setText("��   ����");
        lblName.setBounds(new Rectangle(16, 75, 75, 20));
        txtName.setBounds(new Rectangle(110, 75, 170, 20));
        //Tel
        lblTelephone.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblTelephone.setText("�绰���룺");
        lblTelephone.setBounds(new Rectangle(10, 100, 75, 20));
        txtTelephone.setBounds(new Rectangle(110, 100, 170, 20));
        //סַ
        lbladress.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lbladress.setToolTipText("");
        lbladress.setText("������λ��");
        lbladress.setBounds(new Rectangle(10, 125, 75, 21));
        txtadress.setBounds(new Rectangle(110, 125, 170, 20));
        //���֤��
        lblIDCard.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblIDCard.setText("���֤�ţ�");
        lblIDCard.setBounds(new Rectangle(10, 150, 75, 22));
        txtIDCard.setBounds(new Rectangle(110, 150, 170, 20));
        //����ʱ��
        lblstartTime.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblstartTime.setText("����ʱ�䣺");
        lblstartTime.setBounds(new Rectangle(10, 175, 75, 20));
        cmbSYear.setSelectedItem(null);
        cmbSYear.setBounds(new Rectangle(109, 175, 53, 20));
        for(int i=2013;i<=2014;i++)
            cmbSYear.addItem(i);
        lblSYear.setText("��");
        lblSYear.setBounds(new Rectangle(163, 175, 14, 15));
        for(int i=1;i<=12;i++)
            cmbSMonth.addItem(i);
        cmbSMonth.setBounds(new Rectangle(177, 175, 39, 20));
        lblSMonth.setText("��");
        lblSMonth.setBounds(new Rectangle(218, 175, 14, 15));
        cmbSDay.setBounds(new Rectangle(232, 175, 39, 20));
        for(int i=1;i<=31;i++)
            cmbSDay.addItem(i);
        lblSDay.setText("��");
        lblSDay.setBounds(new Rectangle(273, 175, 13, 15));
        //����ʱ��
        lblreturnTime.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblreturnTime.setText("����ʱ�䣺");
        lblreturnTime.setBounds(new Rectangle(10,200, 75, 20));
        cmbRYear.setSelectedItem(null);
        cmbRYear.setBounds(new Rectangle(109, 200, 53, 20));
        for(int i=2013;i<=2014;i++)
            cmbRYear.addItem(i);
        lblRYear.setText("��");
        lblRYear.setBounds(new Rectangle(163, 200, 14, 15));
        for(int i=1;i<=12;i++)
            cmbRMonth.addItem(i);
        cmbRMonth.setBounds(new Rectangle(177, 200, 39, 20));
        lblRMonth.setText("��");
        lblRMonth.setBounds(new Rectangle(218, 200, 14, 15));
        cmbRDay.setBounds(new Rectangle(232, 200, 39, 20));
        for(int i=1;i<=31;i++)
            cmbRDay.addItem(i);
        lblRDay.setText("��");
        lblRDay.setBounds(new Rectangle(273, 200, 13, 15));
        //��ʼ��
        lblstartPlace.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblstartPlace.setText("��ʼ�أ�");
        lblstartPlace.setBounds(new Rectangle(23, 225, 65, 22));
        txtstartPlace.setBounds(new Rectangle(110, 225, 170, 20));
        //Ŀ�ĵ�
        lblendPlace.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblendPlace.setText("Ŀ�ĵأ�");
        lblendPlace.setBounds(new Rectangle(23, 250, 65, 22));
        txtendPlace.setBounds(new Rectangle(110, 250, 170, 20));
        //��λ����
        lblseatClass.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblseatClass.setText("��λѡ��");
        lblseatClass.setBounds(new Rectangle(10, 275, 75, 20));
        cmbseatClass.setSelectedItem(null);
        cmbseatClass.setBounds(new Rectangle(109, 275, 70, 20));
        for(int i=0;i<=2;i++)
            cmbseatClass.addItem(seat[i]);
        //�������ķָ���
        lblNote.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblNote.setText("����������ͨ�������ѯ�ĵõ������ݣ�");
        lblNote.setBounds(new Rectangle(10, 317, 270, 18));
        lblStart.setText("*****************************************************");
        lblStart.setBounds(new Rectangle(10, 295, 270, 15));
        lblStart2.setText("*****************************************************");
        lblStart2.setBounds(new Rectangle(10, 342, 270, 15));
        //�����
        lblFlightNo.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblFlightNo.setText("����ţ�");
        lblFlightNo.setBounds(new Rectangle(23, 367, 65, 20));
        txtFlightNo.setBounds(new Rectangle(110, 367, 170, 20));
        //�۸�
        lblPrice.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblPrice.setText("Ʊ  �ۣ�");
        lblPrice.setBounds(new Rectangle(23, 392, 65, 20));
        txtPrice.setBounds(new Rectangle(110,392, 170, 20));
        //ʣ����λ
        lblsSeat.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblsSeat.setText("ʣ����λ��");
        lblsSeat.setBounds(new Rectangle(23, 417, 75, 20));
        txtsSeat.setBounds(new Rectangle(110, 417, 170, 20));
        //���չ�˾
        lblplaneCompany.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblplaneCompany.setText("���չ�˾��");
        lblplaneCompany.setBounds(new Rectangle(23, 442, 75, 20));
        txtplaneCompany.setBounds(new Rectangle(110, 442, 170, 20));
        //��ť
        btnSearchFlights.setBounds(new Rectangle(13, 503, 100, 23));
        btnSearchFlights.setText("�����ѯ");
        btnSearchFlights.addActionListener(new BookingFrame_btnSearchFlights_actionAdapter(this));
        btnOrderTicket.setBounds(new Rectangle(133, 503, 83, 23));
        btnOrderTicket.setText("��Ʊ");
        btnOrderTicket.addActionListener(new BookingFrame_btnOrderTicket_actionAdapter(this));
        btnReset.setBounds(new Rectangle(232, 503, 83, 23));
        btnReset.setText("����");
        btnReset.addActionListener(new BookingFrame_btnReset_actionAdapter(this));
        lblSearchID.setFont(new java.awt.Font("�����п�", Font.PLAIN, 15));
        lblSearchID.setText("�������ÿ͵����֤�ţ�");
        lblSearchID.setBounds(new Rectangle(316, 75, 190, 20));
        lblTravelerInformation.setFont(new java.awt.Font("�����п�", Font.BOLD, 17));
        lblTravelerInformation.setText("�ÿ���Ϣ��");
        lblTravelerInformation.setBounds(new Rectangle(4, 38, 94, 23));
        lblOrderTicketSearch.setFont(new java.awt.Font("�����п�", Font.BOLD, 17));
        lblOrderTicketSearch.setText("��Ʊ��ѯ��");
        lblOrderTicketSearch.setBounds(new Rectangle(316, 39, 102, 23));
        txtIDSearch.setBounds(new Rectangle(348, 106, 156, 20));
        btnIDSearch.setBounds(new Rectangle(368, 149, 83, 23));
        btnIDSearch.setText("��ѯ");
        btnIDSearch.addActionListener(new BookingFrame_btnIDSearch_actionAdapter(this));
        lblSearchResult.setFont(new java.awt.Font("�����п�", Font.PLAIN, 17));
        lblSearchResult.setForeground(Color.green);
        lblSearchResult.setText("��ѯ���");
        lblSearchResult.setBounds(new Rectangle(376, 205, 83, 26));
        areaResults.setBounds(new Rectangle(319, 254, 204, 179));
        btnBill.setBounds(new Rectangle(334, 503, 100, 23));
        btnBill.setText("�鿴����");
        btnBill.addActionListener(new BookingFrame_btnBill_actionAdapter(this));
        btnsave.setBounds(new Rectangle (446,503, 100, 23));
       // btnsave.setText("����");
        //btnsave.addActionListener(new BookingFrame_btnsave_actionAdapter(this));
        this.getContentPane().add(areaResults);
        this.getContentPane().add(btnBill);
        this.getContentPane().add(btnIDSearch);
        this.getContentPane().add(btnOrderTicket);
        this.getContentPane().add(btnReset);
        this.getContentPane().add(btnSearchFlights);
        this.getContentPane().add(cmbRDay);
        this.getContentPane().add(cmbRMonth);
        this.getContentPane().add(cmbRYear);
        this.getContentPane().add(cmbseatClass);
        this.getContentPane().add(cmbSMonth);
        this.getContentPane().add(cmbSYear);
        this.getContentPane().add(lbladress);
        this.getContentPane().add(lblendPlace);
        this.getContentPane().add(lblFlightNo);
        this.getContentPane().add(lblIDCard);
        this.getContentPane().add(lblName);
        this.getContentPane().add(lblNote);
        this.getContentPane().add(lblOrderTicketSearch);
        this.getContentPane().add(lblplaneCompany);
        this.getContentPane().add(lblPrice);
        this.getContentPane().add(lblRDay);
        this.getContentPane().add(lblreturnTime);
        this.getContentPane().add(lblRMonth);
        this.getContentPane().add(lblRYear);
        this.getContentPane().add(lblSDay);
        this.getContentPane().add(lblSearchID);
        this.getContentPane().add(lblSearchResult);
        this.getContentPane().add(lblseatClass);
        this.getContentPane().add(lblSMonth);
        this.getContentPane().add(lblSYear);
        this.getContentPane().add(lblsSeat);
        this.getContentPane().add(lblStart);
        this.getContentPane().add(lblStart2);
        this.getContentPane().add(lblstartPlace);
        this.getContentPane().add(lblstartTime);
        this.getContentPane().add(lblTelephone);
        this.getContentPane().add(lblTitle);
        this.getContentPane().add(lblTravelerInformation);
        this.getContentPane().add(txtadress);
        this.getContentPane().add(txtendPlace);
        this.getContentPane().add(txtFlightNo);
        this.getContentPane().add(txtIDCard);
        this.getContentPane().add(txtIDSearch);
        this.getContentPane().add(txtName);
        this.getContentPane().add(txtplaneCompany);
        this.getContentPane().add(txtPrice);
        this.getContentPane().add(txtsSeat);
        this.getContentPane().add(txtstartPlace);
        this.getContentPane().add(txtTelephone);
        this.getContentPane().add(cmbSDay);
       // this.getContentPane().add(btnsave);
        this.txtFlightNo.setEnabled(false);
        this.txtPrice.setEnabled(false);
        this.txtsSeat.setEnabled(false);
        this.txtplaneCompany.setEnabled(false);
        this.setResizable(false);
        this.setVisible(true);
	}
    //�������ð�ť�ļ�����
    public void btnReset_actionPerformed(ActionEvent e) {
    	this.txtName.setText("");
    	this.txtTelephone.setText("");
    	this.txtadress.setText("");
    	this.txtIDCard.setText("");
    	this.cmbSYear.setSelectedIndex(0);
    	this.cmbSMonth.setSelectedIndex(0);
    	this.cmbSDay.setSelectedIndex(0);
    	this.cmbRYear.setSelectedIndex(0);
    	this.cmbRMonth.setSelectedIndex(0);
    	this.cmbRDay.setSelectedIndex(0);
    	this.txtstartPlace.setText("");
    	this.txtendPlace.setText("");
    	this.txtFlightNo.setText("");
    	this.txtPrice.setText("");
    	this.txtIDSearch.setText("");
    	this.areaResults.setText("");
    	this.txtsSeat.setText("");
    	this.txtplaneCompany.setText("");
    	this.cmbseatClass.setSelectedIndex(0);
    }
    //������Ʊ��ť�ļ�����
	public void btnOrderTicket_actionPerformed(ActionEvent e) {
    	String name = txtName.getText();
    	String telephone = txtTelephone.getText();
    	String adress = txtadress.getText();
    	String IdCard = txtIDCard.getText();
    	int Syear = (Integer)cmbSYear.getSelectedItem();
    	int Smonth = (Integer)cmbSMonth.getSelectedItem();
    	int Sday = (Integer)cmbSDay.getSelectedItem();
        LocalDateTime date = LocalDateTime.of(Syear, Smonth, Sday,0,0,0);
    	int Ryear = (Integer)cmbRYear.getSelectedItem();
    	int Rmonth = (Integer)cmbRMonth.getSelectedItem();
    	int Rday = (Integer)cmbRDay.getSelectedItem();
        LocalDateTime Rdate = LocalDateTime.of(Ryear, Rmonth, Rday,0,0,0);
    	String startPlace = txtstartPlace.getText();
    	String endPlace = txtendPlace.getText();
    	int SeatClass = cmbseatClass.getSelectedIndex();
    	int flightNo;
    	try {
    		flightNo = Integer.parseInt(txtFlightNo.getText());
    	} catch (Exception ee) {
    		flightNo = 0;
    	}
    	float money;
    	try {
    		 money = Float.parseFloat(txtPrice.getText());
    	} catch (Exception ee) {
    		money = 0;
    	}
    	
    	if(name.equals("") || telephone.equals("") || adress.equals("") || IdCard.equals("")||startPlace.equals("")||endPlace.equals("")) {
    		JOptionPane.showMessageDialog(null, "�ÿ���Ϣû��������");
    	}
    	
    	else if(flightNo == 0) {
    		JOptionPane.showMessageDialog(null, "û������Ҫ�ĺ���");
    	}
    	
    	else {
    		Guest g = new Guest();
    		g.setName(name);
    		g.setIDCard(IdCard);
    		g.setStartDate(date);
    		g.setTelephone(telephone);
    		g.setAdress(adress);
    		GuestDAO gDao = new GuestDAO();
    		gDao.add(g);
    		g = gDao.selectByIdCard(IdCard);
    		System.out.println(g.getId());
    		
    		Order oo = new Order();
    		oo.setNumber(1);
    		oo.setTotalPays(money);
    		oo.setGuest(guest);
    		PlaneCompany planecompany = new PlaneCompany();
    		planecompany.setId(1);
    		planecompany.setName("��������");
    		planecompany.setAdress("����");
    		oo.setPlanecompany(planecompany);
    		int a = 0;
    		if(money == 1000) a = 1;
    		if(money == 800) a = 2;
    		if(money == 500) a = 3;
    		oo.setSeattype(a);
    		OrderDAO oDao = new OrderDAO();
    		oDao.add(oo);
    		oo = oDao.selectByGuestId(g.getId());
    		
    		
    		Ticket t = new Ticket();
    		Flight f = new FlightDAO().selectById(flightNo);
    		t.setFlight(f);
    		t.setOrder(oo);
    		t.setCount(1);
    		boolean bb = new TicketDAO().add(t);
    		if(bb) 
    			JOptionPane.showMessageDialog(null, "��ƱԤ���ɹ�");
    		else 
    			JOptionPane.showMessageDialog(null, "��ƱԤ��ʧ��,�����ԣ�");
    	}
		
    	String temp="";
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt&java", "txt");
	     chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
          FileWriter file;
		  BufferedWriter buf;
		 try {
				
			//temp=jta.getText();
				JOptionPane.showMessageDialog(null,"����ѡȡ���ļ�����:"+temp);
		
				file = new FileWriter(chooser.getSelectedFile());					
				buf = new BufferedWriter(file);
			     buf.write(toString());
			     buf.flush();

			} catch (FileNotFoundException e1) {
					e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		 }
    }
	public String toString(){
		return getName();
		
	}
    //�������֤��ѯ��ť�ļ�����
    public void btnIDSearch_actionPerformed(ActionEvent e) {
    	String idCard = this.txtIDSearch.getText();
    	if(idCard.equals("")) {
    		JOptionPane.showMessageDialog(null, "���֤�Ų���Ϊ��");
    		return;
    	}
    	String str = "";
    	Guest guest = new GuestDAO().selectByIdCard(idCard);
    	if(guest != null) {
    		str = "������" + guest.getName() + "\n���֤Ϊ��" + guest.getIDCard();
    		Order order = new OrderDAO().selectByGuestId(guest.getId());
        	Ticket ticket = new TicketDAO().selectByOrderId(order.getId());
        	Flight flight = new FlightDAO().selectById(ticket.getFlight().getId());
        	str += "\nĿ�ĵأ�" + flight.getEnd().getName() + "\n����ţ�" + flight.getId() 
        		+ "\n���ʱ�䣺" + flight.getStartTime() + "\n����ʱ�䣺" + flight.getEndTime()
        		+ "\n��λ���ͣ�";
        	if(order.getSeattype() == 0)str += "ͷ�Ȳ�";
        	if(order.getSeattype() == 1)str += "��ͨ��";
        	if(order.getSeattype() == 2)str += "���ò�";
        	str += "\n��λ��" + order.getTotalPays() + "\n�ۿۣ�" + (1 - ticket.getCount()) * 100 + "%";
    	}	
    	else 
        	str = "���ÿͻ�û��Ԥ����Ʊ";
    	areaResults.setText(str);
    }
    //���������ѯ��ť�ļ�����
    public void btnSearchFlights_actionPerformed(ActionEvent e) {
    	new FlightSearchFrame(this);
    }
    //������ӡ�˵���ť�ļ�����
    public void btnBill_actionPerformed(ActionEvent e) {
    	if(areaResults.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "�����Ƚ������֤�ŵĲ���,���ڸ��ÿ͵���Ϣ,���ܴ�ӡ�˵�,�����ܴ�ӡ�˵�");
    		return;
    	}
    	String idCard = this.txtIDSearch.getText();
    	Guest guest = new GuestDAO().selectByIdCard(idCard);
    	Order order = new OrderDAO().selectByGuestId(guest.getId());
    	Ticket ticket = new TicketDAO().selectByOrderId(order.getId());
    	String message;
    	message = "         �𾴵�" + guest.getName() + "�ÿ�\n����Ҫ��������" + ticket.getFlight().getId() + "����\nΪ";
    	if(order.getSeattype() == 0)message += "ͷ�Ȳ�";
    	if(order.getSeattype() == 1)message += "��ͨ��";
    	if(order.getSeattype() == 2)message += "���ò�";
    	message += "1��\n�ܼ�ǮΪ��" + order.getTotalPays();
    	JOptionPane.showMessageDialog(null, message);
    }
}

class BookingFrame_btnSearchFlights_actionAdapter implements ActionListener {
private BookingFrame adaptee;
BookingFrame_btnSearchFlights_actionAdapter(BookingFrame adaptee) {
    this.adaptee = adaptee;
}

public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchFlights_actionPerformed(e);
	}
}

class BookingFrame_btnReset_actionAdapter implements ActionListener {
    private BookingFrame adaptee;
    BookingFrame_btnReset_actionAdapter(BookingFrame adaptee) {
        this.adaptee = adaptee;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        adaptee.btnReset_actionPerformed(e);
	}

}
class BookingFrame_btnOrderTicket_actionAdapter implements ActionListener {
    private BookingFrame adaptee;
    BookingFrame_btnOrderTicket_actionAdapter(BookingFrame adaptee) {
        this.adaptee = adaptee;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        adaptee.btnOrderTicket_actionPerformed(e);
	}

}

class BookingFrame_btnIDSearch_actionAdapter implements ActionListener {
    private BookingFrame adaptee;
    BookingFrame_btnIDSearch_actionAdapter(BookingFrame adaptee) {
        this.adaptee = adaptee;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        adaptee.btnIDSearch_actionPerformed(e);
	}
}
class BookingFrame_btnBill_actionAdapter implements ActionListener {
    private BookingFrame adaptee;
    BookingFrame_btnBill_actionAdapter(BookingFrame adaptee) {
        this.adaptee = adaptee;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        adaptee.btnBill_actionPerformed(e);
	}

}
