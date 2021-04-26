package com.ghw.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ghw.dao.OrderDAO;
import com.ghw.dao.TicketDAO;
import com.ghw.model.Order;
import com.ghw.model.Ticket;

public class OrderFrame extends JFrame{
    JLabel lblTitle = new JLabel();
    JLabel lblSearchID = new JLabel();
    JTextField txtSearchID = new JTextField();
    JButton btnSearch = new JButton();
    JButton btnGetTicket = new JButton();
    JButton btnExit = new JButton();
    JLabel lblName = new JLabel();
    JTextField txtPriceNo = new JTextField();
    JLabel lblPriceNo = new JLabel();
    JTextField txtName = new JTextField();
    JLabel lblIDCard = new JLabel();
    JTextField txtIDCard = new JTextField();
    JLabel lblPrice = new JLabel();
    JTextField txtPrice = new JTextField();
    JLabel lblFlightNo = new JLabel();
    JTextField txtFlightNo = new JTextField();
    JLabel lblOverTime = new JLabel();
    JLabel lblDentist = new JLabel();
    JLabel lblStar = new JLabel();
    JTextField txtDentist = new JTextField();
    JTextField txtOverTime = new JTextField();

    public OrderFrame() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(null);
        this.setSize(new Dimension(400, 580));
        this.setLocation(400, 100);
        lblTitle.setFont(new java.awt.Font("华文行楷", Font.BOLD, 30));
        lblTitle.setForeground(Color.cyan);
        lblTitle.setText("订单管理");
        lblTitle.setBounds(new Rectangle(144, 14, 132, 40));
        this.setResizable(false);
        this.setVisible(true);
        lblSearchID.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblSearchID.setText("请输入旅客取票的账单号：");
        lblSearchID.setBounds(new Rectangle(15, 68, 182, 24));
        txtSearchID.setBounds(new Rectangle(203, 68, 162, 20));
        btnSearch.setBounds(new Rectangle(163, 118, 82, 23));
        btnSearch.setText("查询");
        btnSearch.addActionListener(new OrderFrame_btnSearch_actionAdapter(this));
        btnGetTicket.setBounds(new Rectangle(36, 494, 100, 23));
        btnGetTicket.setText("付费取票");
        btnGetTicket.addActionListener(new OrderFrame_btnGetTicket_actionAdapter(this));
        btnExit.setBounds(new Rectangle(280, 494, 83, 23));
        btnExit.setText("退出");
        btnExit.addActionListener(new OrderFrame_btnExit_actionAdapter(this));
        lblName.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblName.setText("姓  名：");
        lblName.setBounds(new Rectangle(51, 228, 62, 23));
        txtPriceNo.setBounds(new Rectangle(180, 184, 147, 20));
        lblPriceNo.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblPriceNo.setText("账单号：");
        lblPriceNo.setBounds(new Rectangle(51, 184, 62, 25));
        txtName.setBounds(new Rectangle(180, 231, 147, 20));
        lblIDCard.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblIDCard.setText("身份证号：");
        lblIDCard.setBounds(new Rectangle(36, 274, 84, 21));
        txtIDCard.setBounds(new Rectangle(180, 273, 147, 20));
        lblPrice.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblPrice.setText("付款金额：");
        lblPrice.setBounds(new Rectangle(36, 312, 79, 24));
        txtPrice.setBounds(new Rectangle(180, 312, 147, 20));
        lblFlightNo.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblFlightNo.setText("航班号：");
        lblFlightNo.setBounds(new Rectangle(51, 353, 62, 27));
        txtFlightNo.setBounds(new Rectangle(180, 353, 147, 20));
        lblOverTime.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblOverTime.setText("取票截止日期：");
        lblOverTime.setBounds(new Rectangle(28, 398, 111, 26));
        lblDentist.setFont(new java.awt.Font("华文行楷", Font.PLAIN, 15));
        lblDentist.setText("目的地：");
        lblDentist.setBounds(new Rectangle(51, 442, 62, 22));
        lblStar.setText(
                "******************************************************************************");
        lblStar.setBounds(new Rectangle(2, 151, 399, 15));
        txtDentist.setBounds(new Rectangle(180, 442, 147, 20));
        txtOverTime.setBounds(new Rectangle(180, 398, 147, 20));
        this.getContentPane().add(lblTitle, null);
        this.getContentPane().add(lblSearchID);
        this.getContentPane().add(txtSearchID);
        this.getContentPane().add(btnSearch);
        this.getContentPane().add(txtPriceNo);
        this.getContentPane().add(txtIDCard);
        this.getContentPane().add(txtName);
        this.getContentPane().add(txtPrice);
        this.getContentPane().add(lblPriceNo);
        this.getContentPane().add(lblName);
        this.getContentPane().add(lblIDCard);
        this.getContentPane().add(lblPrice);
        this.getContentPane().add(lblFlightNo);
        this.getContentPane().add(txtFlightNo);
        this.getContentPane().add(lblDentist);
        this.getContentPane().add(btnGetTicket);
        this.getContentPane().add(btnExit);
        this.getContentPane().add(lblStar);
        this.getContentPane().add(txtDentist);
        this.getContentPane().add(lblOverTime);
        this.getContentPane().add(txtOverTime);
        this.txtDentist.setEnabled(false);
        this.txtFlightNo.setEnabled(false);
        this.txtIDCard.setEnabled(false);
        this.txtName.setEnabled(false);
        this.txtOverTime.setEnabled(false);
        this.txtPrice.setEnabled(false);
        this.txtPriceNo.setEnabled(false);
    }

	public void btnSearch_actionPeerformed(ActionEvent e) {
    	String strId = txtSearchID.getText();
    	int id = 0;
    	boolean bb = false;
    	if(strId.equals("")) {
    		JOptionPane.showMessageDialog(null, "账单号不能为空");
    	}
    	else {
    		try {
    			id = Integer.parseInt(strId);
    		} catch (Exception ee) {
    			id = 0;
    			bb = true;
    			JOptionPane.showMessageDialog(null, "账单号必须为数字");
    		}
    	}
    	if(!bb) {
    		OrderDAO dao = new OrderDAO();
    		Order oo = dao.selectByGuestId(id);
    		if(oo == null) {
    			JOptionPane.showMessageDialog(null, "账单号不存在");
    			return;
    		}
    		Ticket t = new TicketDAO().selectByOrderId(oo.getId());
    		txtPriceNo.setText("" + id);
    		txtName.setText(oo.getGuest().getName());
    		txtIDCard.setText(oo.getGuest().getIDCard());
    		txtPrice.setText("" +oo.getTotalPays());
    		txtFlightNo.setText("" + t.getFlight().getId());
    		txtOverTime.setText("" + t.getFlight().getStartTime());
    		txtDentist.setText(t.getFlight().getEnd().getName());
    	}
    	if(id == 0)
    		JOptionPane.showMessageDialog(null, "账单号不存在");
    }
    
    public void btnGetTicket_actionPeerformed(ActionEvent e) {
    	String strId = txtSearchID.getText();
    	int id = Integer.parseInt(strId);
    	Order oo = new OrderDAO().selectByGuestId(id);
    	Ticket t = new TicketDAO().selectByOrderId(oo.getId());
    	String message;
    	if(txtPriceNo.equals("")) {
    		JOptionPane.showMessageDialog(null, "该旅客还没有预订机票,\n不能付费取票");
			return;
    	}
    	message = "             飞机票\n   " + t.getFlight().getStart().getName() + "--------" + t.getFlight().getEnd().getName()
    			+ "\n航班号：" + t.getFlight().getId() + "\n座位类型：";
    	if(oo.getSeattype() == 0)message += "头等舱";
    	if(oo.getSeattype() == 1)message += "普通舱";
    	if(oo.getSeattype() == 2)message += "经济舱";
    	message += "\n票价：" + oo.getTotalPays();
    	JOptionPane.showMessageDialog(null, message);
    }
    
}

class OrderFrame_btnSearch_actionAdapter implements ActionListener {
    private OrderFrame adaptee;
    OrderFrame_btnSearch_actionAdapter(OrderFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnSearch_actionPeerformed(e);
    }
}

class OrderFrame_btnGetTicket_actionAdapter implements ActionListener {
    private OrderFrame adaptee;
    OrderFrame_btnGetTicket_actionAdapter(OrderFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnGetTicket_actionPeerformed(e);
    }
}

class OrderFrame_btnExit_actionAdapter implements ActionListener {
    private OrderFrame adaptee;
    OrderFrame_btnExit_actionAdapter(OrderFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
    	adaptee.dispose();
    }
}
