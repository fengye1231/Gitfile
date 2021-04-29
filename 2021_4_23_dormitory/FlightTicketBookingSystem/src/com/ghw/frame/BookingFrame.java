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
	//标题
    JLabel lblTitle = new JLabel();
    //旅客信息
    //姓名
    JLabel lblName = new JLabel();
    JTextField txtName = new JTextField();
    //电话
    JLabel lblTelephone = new JLabel();
    JTextField txtTelephone = new JTextField();
    //地址
    JLabel lbladress = new JLabel();
    JTextField txtadress = new JTextField();
    //身份证号
    JLabel lblIDCard = new JLabel();
    JTextField txtIDCard = new JTextField();
    
    //订单信息：
    
    //出发时间
    JLabel lblstartTime = new JLabel();
    JComboBox<Integer> cmbSYear = new JComboBox<Integer>();
    JLabel lblSYear = new JLabel();
    JComboBox<Integer> cmbSMonth = new JComboBox<Integer>();
    JLabel lblSMonth = new JLabel();
    JLabel lblSDay = new JLabel();
    JComboBox<Integer> cmbSDay = new JComboBox<Integer>();
    int Sdays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    //返回时间
    JLabel lblreturnTime = new JLabel();
    JComboBox<Integer> cmbRYear = new JComboBox<Integer>();
    JLabel lblRYear = new JLabel();
    JComboBox<Integer> cmbRMonth = new JComboBox<Integer>();
    JLabel lblRMonth = new JLabel();
    JLabel lblRDay = new JLabel();
    JComboBox<Integer> cmbRDay = new JComboBox<Integer>();
    int Rdays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    //起始地
    JLabel lblstartPlace = new JLabel();
    JTextField txtstartPlace = new JTextField();
    
    //目的地
    JLabel lblendPlace = new JLabel();
    JTextField txtendPlace = new JTextField();
    //座位类型
    JLabel lblseatClass = new JLabel();
    JComboBox<String> cmbseatClass = new JComboBox<String>();
    String[] seat = {"普通舱","经济舱","头等舱"};
    
    
    //订票数量
    JLabel lblticketNum = new JLabel();
    JTextField txtticketNum = new JTextField();
    
   
    
    
    //写**********
    //通过航班查询得到：
    //***********
    JLabel lblNote = new JLabel();
    JLabel lblStart = new JLabel();
    JLabel lblStart2 = new JLabel();
   
    //航班号
    JLabel lblFlightNo = new JLabel();
    JTextField txtFlightNo = new JTextField();
   
    //价格
    JLabel lblPrice = new JLabel();
    JTextField txtPrice = new JTextField();
   
    //航空公司
    JLabel lblplaneCompany = new JLabel();
    JTextField txtplaneCompany = new JTextField();
  
    //剩余座位
    JLabel lblsSeat = new JLabel();
    JTextField txtsSeat = new JTextField();
  
    //按钮定义
    //航班查询
    JButton btnSearchFlights = new JButton();
   
    //订票
    JButton btnOrderTicket = new JButton();
   
    //重置
    JButton btnReset = new JButton();
   
    //绘制身份证号查询
    JLabel lblSearchID = new JLabel();
    JLabel lblTravelerInformation = new JLabel();
    JLabel lblOrderTicketSearch = new JLabel();
    JTextField txtIDSearch = new JTextField();
   
    //身份证查询
    JButton btnIDSearch = new JButton();
  
    //查询结果
    JLabel lblSearchResult = new JLabel();
    JTextArea areaResults = new JTextArea();
    
    //保存按钮
    JButton btnsave = new JButton();
    //打印账单
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
        //设置大小
//        this.setSize(564, 580);
        
        
        this.setSize(594, 610);
        
        //设置位置居中
        Toolkit toolkit = getToolkit().getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width-this.getWidth())/2;
        int y = (screen.height-this.getHeight())/2-32;
        this.setLocation(x, y);
        //绘制窗口
        //窗口名称
        lblTitle.setFont(new java.awt.Font("华文行楷", Font.BOLD, 25));
        lblTitle.setForeground(Color.red);
        lblTitle.setText("机票预订");
        lblTitle.setBounds(new Rectangle(197, 0, 107, 44));
        //用户信息       
        lblName.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblName.setText("姓   名：");
        lblName.setBounds(new Rectangle(16, 75, 75, 20));
        txtName.setBounds(new Rectangle(110, 75, 170, 20));
        //Tel
        lblTelephone.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblTelephone.setText("电话号码：");
        lblTelephone.setBounds(new Rectangle(10, 100, 75, 20));
        txtTelephone.setBounds(new Rectangle(110, 100, 170, 20));
        //住址
        lbladress.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lbladress.setToolTipText("");
        lbladress.setText("工作单位：");
        lbladress.setBounds(new Rectangle(10, 125, 75, 21));
        txtadress.setBounds(new Rectangle(110, 125, 170, 20));
        //身份证号
        lblIDCard.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblIDCard.setText("身份证号：");
        lblIDCard.setBounds(new Rectangle(10, 150, 75, 22));
        txtIDCard.setBounds(new Rectangle(110, 150, 170, 20));
        //出发时间
        lblstartTime.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblstartTime.setText("出发时间：");
        lblstartTime.setBounds(new Rectangle(10, 175, 75, 20));
        cmbSYear.setSelectedItem(null);
        cmbSYear.setBounds(new Rectangle(109, 175, 53, 20));
        for(int i=2013;i<=2014;i++)
            cmbSYear.addItem(i);
        lblSYear.setText("年");
        lblSYear.setBounds(new Rectangle(163, 175, 14, 15));
        for(int i=1;i<=12;i++)
            cmbSMonth.addItem(i);
        cmbSMonth.setBounds(new Rectangle(177, 175, 39, 20));
        lblSMonth.setText("月");
        lblSMonth.setBounds(new Rectangle(218, 175, 14, 15));
        cmbSDay.setBounds(new Rectangle(232, 175, 39, 20));
        for(int i=1;i<=31;i++)
            cmbSDay.addItem(i);
        lblSDay.setText("日");
        lblSDay.setBounds(new Rectangle(273, 175, 13, 15));
        //返回时间
        lblreturnTime.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblreturnTime.setText("返回时间：");
        lblreturnTime.setBounds(new Rectangle(10,200, 75, 20));
        cmbRYear.setSelectedItem(null);
        cmbRYear.setBounds(new Rectangle(109, 200, 53, 20));
        for(int i=2013;i<=2014;i++)
            cmbRYear.addItem(i);
        lblRYear.setText("年");
        lblRYear.setBounds(new Rectangle(163, 200, 14, 15));
        for(int i=1;i<=12;i++)
            cmbRMonth.addItem(i);
        cmbRMonth.setBounds(new Rectangle(177, 200, 39, 20));
        lblRMonth.setText("月");
        lblRMonth.setBounds(new Rectangle(218, 200, 14, 15));
        cmbRDay.setBounds(new Rectangle(232, 200, 39, 20));
        for(int i=1;i<=31;i++)
            cmbRDay.addItem(i);
        lblRDay.setText("日");
        lblRDay.setBounds(new Rectangle(273, 200, 13, 15));
        //起始地
        lblstartPlace.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblstartPlace.setText("起始地：");
        lblstartPlace.setBounds(new Rectangle(23, 225, 65, 22));
        txtstartPlace.setBounds(new Rectangle(110, 225, 170, 20));
        //目的地
        lblendPlace.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblendPlace.setText("目的地：");
        lblendPlace.setBounds(new Rectangle(23, 250, 65, 22));
        txtendPlace.setBounds(new Rectangle(110, 250, 170, 20));
        //座位类型
        lblseatClass.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblseatClass.setText("座位选择：");
        lblseatClass.setBounds(new Rectangle(10, 275, 75, 20));
        cmbseatClass.setSelectedItem(null);
        cmbseatClass.setBounds(new Rectangle(109, 275, 70, 20));
        for(int i=0;i<=2;i++)
            cmbseatClass.addItem(seat[i]);
        
        
        
        //订票数量
        
        lblticketNum.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblticketNum.setText("订票数量：");
        lblticketNum.setBounds(new Rectangle(10, 300, 85, 20));     
        txtticketNum.setBounds(new Rectangle(109, 300, 85, 20));
        
        
        
        //华丽丽的分割线
        lblNote.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblNote.setText("以下内容是通过航班查询的得到的内容：");
        lblNote.setBounds(new Rectangle(10, 362, 270, 18));
        lblStart.setText("*****************************************************");
        lblStart.setBounds(new Rectangle(10, 355, 270, 15));
        lblStart2.setText("*****************************************************");
        lblStart2.setBounds(new Rectangle(10, 377, 270, 15));
        //航班号
        lblFlightNo.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblFlightNo.setText("航班号：");
        lblFlightNo.setBounds(new Rectangle(23, 397, 65, 20));
        txtFlightNo.setBounds(new Rectangle(110, 397, 170, 20));
        //价格
        lblPrice.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblPrice.setText("票  价：");
        lblPrice.setBounds(new Rectangle(23, 412, 65, 20));
        txtPrice.setBounds(new Rectangle(110,412, 170, 20));
        //剩余座位
        lblsSeat.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblsSeat.setText("剩余座位：");
        lblsSeat.setBounds(new Rectangle(23, 447, 75, 20));
        txtsSeat.setBounds(new Rectangle(110, 447, 170, 20));
        //航空公司
        lblplaneCompany.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblplaneCompany.setText("航空公司：");
        lblplaneCompany.setBounds(new Rectangle(23, 472, 75, 20));
        txtplaneCompany.setBounds(new Rectangle(110, 472, 170, 20));
        //按钮
        btnSearchFlights.setBounds(new Rectangle(13, 533, 100, 23));
        btnSearchFlights.setText("航班查询");
        btnSearchFlights.addActionListener(new BookingFrame_btnSearchFlights_actionAdapter(this));
        btnOrderTicket.setBounds(new Rectangle(133, 533, 83, 23));
        btnOrderTicket.setText("订票");
        btnOrderTicket.addActionListener(new BookingFrame_btnOrderTicket_actionAdapter(this));
        btnReset.setBounds(new Rectangle(232, 533, 83, 23));
        btnReset.setText("重置");
        btnReset.addActionListener(new BookingFrame_btnReset_actionAdapter(this));
        lblSearchID.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblSearchID.setText("请输入旅客的身份证号：");
        lblSearchID.setBounds(new Rectangle(316, 105, 190, 20));
        lblTravelerInformation.setFont(new java.awt.Font("华文行楷", Font.BOLD, 17));
        lblTravelerInformation.setText("旅客信息：");
        lblTravelerInformation.setBounds(new Rectangle(4, 338, 94, 23));
        lblOrderTicketSearch.setFont(new java.awt.Font("华文行楷", Font.BOLD, 17));
        lblOrderTicketSearch.setText("订票查询：");
        lblOrderTicketSearch.setBounds(new Rectangle(316, 69, 102, 23));
        txtIDSearch.setBounds(new Rectangle(348, 136, 156, 20));
        btnIDSearch.setBounds(new Rectangle(368, 179, 83, 23));
        btnIDSearch.setText("查询");
        btnIDSearch.addActionListener(new BookingFrame_btnIDSearch_actionAdapter(this));
        lblSearchResult.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 17));
        lblSearchResult.setForeground(Color.green);
        lblSearchResult.setText("查询结果");
        lblSearchResult.setBounds(new Rectangle(376, 235, 83, 26));
        areaResults.setBounds(new Rectangle(319, 284, 204, 179));
        btnBill.setBounds(new Rectangle(334, 533, 100, 23));
        btnBill.setText("查看订单");
        btnBill.addActionListener(new BookingFrame_btnBill_actionAdapter(this));
        btnsave.setBounds(new Rectangle (446,533, 100, 23));
       // btnsave.setText("保存");
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
        
        this.getContentPane().add(lblticketNum);
        this.getContentPane().add(txtticketNum);
        
       // this.getContentPane().add(btnsave);
        this.txtFlightNo.setEnabled(false);
        this.txtPrice.setEnabled(false);
        this.txtsSeat.setEnabled(false);
        this.txtplaneCompany.setEnabled(false);
        this.setResizable(false);
        this.setVisible(true);
	}
    //监听重置按钮的监听器
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
    //监听订票按钮的监听器
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
//    		JOptionPane.showMessageDialog(null, "旅客信息没有填完整");
    		System.out.println("旅客信息没有填完整");
    		
    	}
    	
    	else if(flightNo == 0) {
    		JOptionPane.showMessageDialog(null, "没有您需要的航班");
    		
    		
    	}
    	
    	else {
//    		Guest g = new Guest();
//    		g.setName(name);
//    		g.setIDCard(IdCard);
//    		g.setStartDate(date);
//    		g.setTelephone(telephone);
//    		g.setAdress(adress);
//    		GuestDAO gDao = new GuestDAO();
//    		gDao.add(g);
//    		g = gDao.selectByIdCard(IdCard);
//    		System.out.println(g.getId());
//    		
//    		Order oo = new Order();
//    		oo.setNumber(1);
//    		oo.setTotalPays(money);
//    		oo.setGuest(guest);
//    		PlaneCompany planecompany = new PlaneCompany();
//    		planecompany.setId(1);
//    		planecompany.setName("北京机场");
//    		planecompany.setAdress("北京");
//    		oo.setPlanecompany(planecompany);
//    		int a = 0;
//    		if(money == 1000) a = 1;
//    		if(money == 800) a = 2;
//    		if(money == 500) a = 3;
//    		oo.setSeattype(a);
//    		OrderDAO oDao = new OrderDAO();
//    		oDao.add(oo);
//    		oo = oDao.selectByGuestId(g.getId());
    		
    		
    		Ticket t = new Ticket();
    		Flight f = new FlightDAO().selectById(flightNo);
    		t.setFlight(f);
//    		t.setOrder(oo);
    		
    		
    		t.setCount(1);
    		
    		
    		boolean bb = new TicketDAO().add(t);
    		if(bb) 
    			JOptionPane.showMessageDialog(null, "机票预订成功");
    		else 
    			JOptionPane.showMessageDialog(null, "机票预订失败,请重试！");
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
				JOptionPane.showMessageDialog(null,"你所选取的文件内容:"+temp);
		
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
    //监听身份证查询按钮的监听器
    public void btnIDSearch_actionPerformed(ActionEvent e) {
    	String idCard = this.txtIDSearch.getText();
    	if(idCard.equals("")) {
    		JOptionPane.showMessageDialog(null, "身份证号不能为空");
    		return;
    	}
    	String str = "";
    	Guest guest = new GuestDAO().selectByIdCard(idCard);
    	if(guest != null) {
    		str = "姓名：" + guest.getName() + "\n身份证为：" + guest.getIDCard();
    		Order order = new OrderDAO().selectByGuestId(guest.getId());
        	Ticket ticket = new TicketDAO().selectByOrderId(order.getId());
        	Flight flight = new FlightDAO().selectById(ticket.getFlight().getId());
        	str += "\n目的地：" + flight.getEnd().getName() + "\n航班号：" + flight.getId() 
        		+ "\n起飞时间：" + flight.getStartTime() + "\n到达时间：" + flight.getEndTime()
        		+ "\n座位类型：";
        	if(order.getSeattype() == 0)str += "头等舱";
        	if(order.getSeattype() == 1)str += "普通舱";
        	if(order.getSeattype() == 2)str += "经济舱";
        	str += "\n价位：" + order.getTotalPays() + "\n折扣：" + (1 - ticket.getCount()) * 100 + "%";
    	}	
    	else 
        	str = "该旅客还没有预订机票";
    	areaResults.setText(str);
    }
    //监听航班查询按钮的监听器
    public void btnSearchFlights_actionPerformed(ActionEvent e) {
    	new FlightSearchFrame(this);
    }
    //监听打印账单按钮的监听器
    public void btnBill_actionPerformed(ActionEvent e) {
    	if(areaResults.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "必须先进行身份证号的查找,存在该旅客的信息,才能打印账单,否则不能打印账单");
    		return;
    	}
    	String idCard = this.txtIDSearch.getText();
    	Guest guest = new GuestDAO().selectByIdCard(idCard);
    	Order order = new OrderDAO().selectByGuestId(guest.getId());
    	Ticket ticket = new TicketDAO().selectByOrderId(order.getId());
    	String message;
    	message = "         尊敬的" + guest.getName() + "旅客\n您将要乘坐的是" + ticket.getFlight().getId() + "航班\n为";
    	if(order.getSeattype() == 0)message += "头等舱";
    	if(order.getSeattype() == 1)message += "普通舱";
    	if(order.getSeattype() == 2)message += "经济舱";
    	message += "1张\n总价钱为：" + order.getTotalPays();
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
