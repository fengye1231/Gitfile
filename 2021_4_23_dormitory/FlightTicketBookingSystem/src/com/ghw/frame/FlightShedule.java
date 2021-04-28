package com.ghw.frame;

import com.ghw.dao.FlightDAO;
import com.ghw.model.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;



public class FlightShedule extends JFrame{
	/**
	 * 
	 */


	JLabel lblTitle = new JLabel();
	JComboBox<String> cmbFlightResults = new JComboBox<String>();
	JComboBox<String> cmbSeatTypes = new JComboBox<String>();
	JButton btnSelectFlight = new JButton();
	private FlightDAO dao = new FlightDAO();
	String[] SeatTypes = { "ͷ�Ȳ�", "��ͨ��", "���ò�" };
	private ArrayList<Flight> flights = null;
	private BookingFrame bookingFrame;
	//private Date date ;

	public FlightShedule(BookingFrame bookingFrame) {
		try {
			this.bookingFrame = bookingFrame;
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		//int year = (Integer) orderFrame.cmbYear.getSelectedItem();
		//int month = (Integer) orderFrame.cmbMonth.getSelectedItem();
		///int day = (Integer) orderFrame.cmbDay.getSelectedItem();
		//System.out.println(orderFrame.cmbDay.getSelectedItem().toString());
		getContentPane().setLayout(null);
		this.setLocation(400, 200);
		this.setSize(new Dimension(400, 350));
		lblTitle.setFont(new Font("�ź�����", Font.BOLD, 20));
		lblTitle.setForeground(Color.pink);
		lblTitle.setText("�����ѯ�����ʾ");
		lblTitle.setBounds(new Rectangle(111, 10, 178, 34));
		this.setResizable(false);
		this.setVisible(true);
		cmbFlightResults.setBounds(new Rectangle(20, 54, 280, 30));



		//date = new Date(year-1900,month-1,day);

		LocalDateTime date = LocalDateTime.of(2018, 12, 30, 20, 20, 20);
		System.out.println(date);
		flights = dao.selectDate(date);
		System.out.println(flights.size());
		//flights = dao.selectAll();
		if (flights.size() == 0)
			JOptionPane.showConfirmDialog(null, "û�����������ĺ���");
		else {
			for (int i = 0; i < flights.size(); i++) {
				Flight f = flights.get(i);
				//System.out.println(f.getId());
				//System.out.println(f.getStart().getName());
				String str = "" + f.getId() + "����," + f.getStart().getName()
						+ "����" + f.getEnd().getName() + ",����ʱ��:"
						+ f.getStartTime();
				cmbFlightResults.addItem(str);
			}
		}
		cmbSeatTypes.setBounds(310, 54, 70, 30);
		for (int i = 0; i < 3; i++) {
			cmbSeatTypes.addItem(SeatTypes[i]);
		}
		btnSelectFlight.setBounds(new Rectangle(155, 251, 100, 23));
		btnSelectFlight.setText("ѡ�񺽰�");
		btnSelectFlight
				.addActionListener(new FlightSheduleFrame_btnSelectFlight_actionAdapter(
						this));
		this.getContentPane().add(lblTitle, null);
		this.getContentPane().add(cmbFlightResults);
		this.getContentPane().add(cmbSeatTypes);
		this.getContentPane().add(btnSelectFlight);
	}

	public void btnSelectFlight_actionPerformed(ActionEvent e) {
		int i = cmbFlightResults.getSelectedIndex();
		int j = cmbSeatTypes.getSelectedIndex();
		Flight f = flights.get(i);
		bookingFrame.txtendPlace.setText(f.getEnd().getName());
		bookingFrame.txtFlightNo.setText("" + f.getId());
		if (j == 0)
			bookingFrame.txtPrice.setText("" + f.getSuperPrice());
		if (j == 1)
			bookingFrame.txtPrice.setText("" + f.getGeneralPrice());
		if (j == 2)
			bookingFrame.txtPrice.setText("" + f.getEconomicPrice());

	}
}

class FlightSheduleFrame_btnSelectFlight_actionAdapter implements ActionListener {
	private FlightShedule adaptee;

	FlightSheduleFrame_btnSelectFlight_actionAdapter(FlightShedule adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnSelectFlight_actionPerformed(e);
		adaptee.dispose();
	}
}
