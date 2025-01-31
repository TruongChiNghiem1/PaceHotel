package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.BillDetail_DAO;
import dao.Bill_DAO;
import dao.Booking_DAO;
import dao.Employee_DAO;
import dao.Guest_DAO;
import dao.Room_DAO;
import entity.BillDetail;
import entity.BillEntity;
import entity.Booking;
import entity.Guest;

public class frmInforBooking extends JFrame implements ActionListener 
{
	private JLabel pName, pGender, pCard, pPhone, pNumOfAdults, pNumOfChildren, pTimeOfArrival, pTimeOfDeparture, pNote,
			pTotal, pRoomType, pNumOfBed, pPrice, pSurcharge, pDiscount, pFirstHour, pNextHour, pOvernight, pTitle,
			pRoomID, pStarPoints, pDOB, pEmail, pTimeOfBooking, pAddress;
	private JTextField inputName, inputCard, inputPhone, inputNumOfAdults, inputNumOfChildren,
			inputTimeOfArrival, inputTimeOfDeinputarture, inputTotal, inputRoomType, inputNumOfBed, inputPrice,
			inputSurcharge, inputDiscount, inputFirstHour, inputNextHour, inputOvernight, inputStarPoints, inputDOB,
			inputEmail, inputTimeOfBooking, inputAddress;
	private JButton btnCheckIn, btnCheckOut, btnAddService, btnFind, btnOrder, btnReady;
	private JPanel sectionBottom, sectionCenter, sectionBorder;
	private JTextArea aNote;
	private Room_DAO roomList;
	private Guest_DAO guestList;
	private Booking_DAO bookingList;
	private Bill_DAO billList;
	private BillDetail_DAO billDetailList;
	private Guest guest;
	private int iRoom;
	private String idRoom;
	private JComboBox<String> cbGender;
	private JTextField txtTenNV;
	private String maNhanVien;
	private Employee_DAO employee_dao;

	public frmInforBooking(String id, String maNhanVien) 
	{
		employee_dao = new Employee_DAO();
		this.maNhanVien = maNhanVien;
		setTitle("Infor booking");
		setSize(1200, 700);
		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		sectionBorder = new JPanel();
		sectionBorder.setLayout(new BorderLayout());
		add(sectionBorder);

		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());

		JPanel pnC1 = new JPanel();
		pnC1.setLayout(null);

		roomList = new Room_DAO();
		guestList = new Guest_DAO();
		bookingList = new Booking_DAO();
		billList = new Bill_DAO();
		billDetailList = new BillDetail_DAO();
		idRoom = id;

		btnAddService = new JButton("Add service");
//		btnCancel = new JButton("Cancel");
		btnCheckIn = new JButton("Check in");
		btnCheckOut = new JButton("Check out");
		btnFind = new JButton("Find");
		btnOrder = new JButton("Order");
		btnReady = new JButton("Ready");

		pTitle = new JLabel("Guest information");
		pCard = new JLabel("Indentity Card:");
		pName = new JLabel("Fullname:");
		pGender = new JLabel("Gender:");
		pStarPoints = new JLabel("Star Points:");
		pDOB = new JLabel("DOB:");
		pPhone = new JLabel("Phone Number:");
		pEmail = new JLabel("Email:");
		pTimeOfBooking = new JLabel("Time of booking:");
		pAddress = new JLabel("Address:");
		pNumOfAdults = new JLabel("Number of adults:");
		pNumOfChildren = new JLabel("Number of Children:");
		pTimeOfArrival = new JLabel("Time of arrival:");
		pTimeOfDeparture = new JLabel("Time of departure:");
		pNote = new JLabel("Note:");
		pTotal = new JLabel("Total:");
		pRoomType = new JLabel("Room type:");
		pNumOfBed = new JLabel("Number of beds:");
		pPrice = new JLabel("Price:");
		pSurcharge = new JLabel("Surcharge:");
		pDiscount = new JLabel("Discount:");
		pFirstHour = new JLabel("First hour fee:");
		pNextHour = new JLabel("Next hour fee:");
		pOvernight = new JLabel("Overnight fee");
		pRoomID = new JLabel("ROOM " + id);

		inputCard = new JTextField();
		inputName = new JTextField();
		
		
		inputStarPoints = new JTextField();
		inputDOB = new JTextField();
		inputPhone = new JTextField();
		inputEmail = new JTextField();
		inputTimeOfBooking = new JTextField();
		inputAddress = new JTextField();
		inputNumOfAdults = new JTextField();
		inputNumOfChildren = new JTextField();
		inputTimeOfArrival = new JTextField();
		inputTimeOfDeinputarture = new JTextField();
		aNote = new JTextArea();
		inputTotal = new JTextField();

		iRoom = roomList.findRoom(id);

		double priceFirst = Double.parseDouble(roomList.getAllInfoRoom().get(iRoom)[4].toString());
		double priceNext = Double.parseDouble(roomList.getAllInfoRoom().get(iRoom)[5].toString());
		double priceOvernight = Double.parseDouble(roomList.getAllInfoRoom().get(iRoom)[6].toString());
        Locale localeVN = new Locale("vi", "VN");
        
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(localeVN);
        String inputFirstHourFormat = vnFormat.format(priceFirst);
        
        String inputNextHourFormat = vnFormat.format(priceNext);
        
        String inputOvernightHourFormat = vnFormat.format(priceOvernight);
		
		inputRoomType = new JTextField(roomList.getAllRoom().get(iRoom).getRoomType());
		inputNumOfBed = new JTextField(roomList.getAllInfoRoom().get(iRoom)[3].toString());
		inputPrice = new JTextField();
		inputSurcharge = new JTextField(20);
		inputDiscount = new JTextField(roomList.getAllInfoRoom().get(iRoom)[8].toString());
		inputFirstHour = new JTextField(inputFirstHourFormat);
		inputNextHour = new JTextField(inputNextHourFormat);
		inputOvernight = new JTextField(inputOvernightHourFormat);
	
		pnC1.add(pTitle);
		pnC1.add(pCard);
		pnC1.add(inputCard);
		pnC1.add(btnFind);
		pnC1.add(pName);
		pnC1.add(inputName);
		pnC1.add(pGender);
		pnC1.add(cbGender =  new JComboBox<String>());
		cbGender.addItem("Male");
		cbGender.addItem("Female");
		pnC1.add(pStarPoints);
		pnC1.add(inputStarPoints);
		pnC1.add(pDOB);
		pnC1.add(inputDOB);
		pnC1.add(pPhone);
		pnC1.add(inputPhone);
		pnC1.add(pEmail);
		pnC1.add(inputEmail);
		pnC1.add(pTimeOfBooking);
		pnC1.add(inputTimeOfBooking);
		pnC1.add(pAddress);
		pnC1.add(inputAddress);
		pnC1.add(pNumOfAdults);
		pnC1.add(inputNumOfAdults);
		pnC1.add(pNumOfChildren);
		pnC1.add(inputNumOfChildren);
		pnC1.add(pTimeOfArrival);
		pnC1.add(inputTimeOfArrival);
		pnC1.add(pTimeOfDeparture);
		pnC1.add(inputTimeOfDeinputarture);
		pnC1.add(pNote);
		pnC1.add(aNote);

		pTitle.setBounds(250, 30, 500, 30);
		pTitle.setFont(new Font("Arial", Font.BOLD, 30));

		pCard.setBounds(160, 100, 100, 20);
		inputCard.setBounds(pCard.getX() + 100, 100, 200, 20);
		btnFind.setBounds(inputCard.getX() + 220, 100, 60, 20);
		btnFind.setBackground(Color.blue);
		btnFind.setForeground(Color.white);

		pName.setBounds(50, pCard.getY() + 60, 60, 20);
		inputName.setBounds(pName.getX() + 110, pCard.getY() + 60, 180, 20);
		pGender.setBounds(inputName.getX() + 190, pCard.getY() + 60, 80, 20);
		cbGender.setBounds(pGender.getX() + 120, pCard.getY() + 60, 180, 20);
		pStarPoints.setBounds(cbGender.getX() + 190, pCard.getY() + 60, 100, 20);
		inputStarPoints.setBounds(pStarPoints.getX() + 80, pCard.getY() + 60, 50, 20);

		pDOB.setBounds(50, pName.getY() + 40, 60, 20);
		inputDOB.setBounds(pName.getX() + 110, pName.getY() + 40, 180, 20);
		pPhone.setBounds(inputDOB.getX() + 190, pName.getY() + 40, 100, 20);
		inputPhone.setBounds(pPhone.getX() + 120, pName.getY() + 40, 180, 20);

		pEmail.setBounds(50, pDOB.getY() + 40, 60, 20);
		inputEmail.setBounds(pEmail.getX() + 110, pDOB.getY() + 40, 180, 20);
		pTimeOfBooking.setBounds(inputEmail.getX() + 190, pDOB.getY() + 40, 100, 20);
		inputTimeOfBooking.setBounds(pTimeOfBooking.getX() + 120, pDOB.getY() + 40, 180, 20);

		pAddress.setBounds(50, pEmail.getY() + 40, 60, 20);
		inputAddress.setBounds(pAddress.getX() + 110, pEmail.getY() + 40, 490, 20);

		pNumOfAdults.setBounds(50, pAddress.getY() + 40, 100, 20);
		inputNumOfAdults.setBounds(pNumOfAdults.getX() + 110, pAddress.getY() + 40, 180, 20);
		pNumOfChildren.setBounds(inputNumOfAdults.getX() + 190, pAddress.getY() + 40, 120, 20);
		inputNumOfChildren.setBounds(pNumOfChildren.getX() + 120, pAddress.getY() + 40, 180, 20);

		pTimeOfArrival.setBounds(50, pNumOfAdults.getY() + 40, 100, 20);
		inputTimeOfArrival.setBounds(pTimeOfArrival.getX() + 110, pNumOfAdults.getY() + 40, 180, 20);
		pTimeOfDeparture.setBounds(inputTimeOfArrival.getX() + 190, pNumOfAdults.getY() + 40, 120, 20);
		inputTimeOfDeinputarture.setBounds(pTimeOfDeparture.getX() + 120, pNumOfAdults.getY() + 40, 180, 20);

		pNote.setBounds(50, pTimeOfArrival.getY() + 40, 100, 20);
		aNote.setBounds(pNote.getX() + 110, pTimeOfArrival.getY() + 40, 490, 20);

		JPanel pnC2 = new JPanel();
		pnC2.setLayout(new BoxLayout(pnC2, BoxLayout.Y_AXIS));

//		pnC2.add(pRoomID);
//		pnC2.add(pRoomType);
//		pnC2.add(inputRoomType);
//		pnC2.add(pNumOfBed);
//		pnC2.add(inputNumOfBed);
//		pnC2.add(pPrice);
//		pnC2.add(inputPrice);
//		pnC2.add(pSurcharge);
//		pnC2.add(inputSurcharge);
//		pnC2.add(pDiscount);
//		pnC2.add(inputDiscount);
//		pnC2.add(pFirstHour);
//		pnC2.add(inputFirstHour);
//		pnC2.add(pNextHour);
//		pnC2.add(inputNextHour);
//		pnC2.add(pOvernight);
//		pnC2.add(inputOvernight);

		Box b = Box.createVerticalBox();// dọc
		Box b1 = Box.createHorizontalBox();// ngang
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		Box b10 = Box.createHorizontalBox();
		b.add(Box.createVerticalStrut(30));
		b1.add(Box.createHorizontalStrut(60));
		b.add(b1);
		b.add(Box.createVerticalStrut(30));
		b2.add(Box.createHorizontalStrut(40));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b3.add(Box.createHorizontalStrut(40));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b4.add(Box.createHorizontalStrut(40));
		b.add(b4);
		b.add(Box.createVerticalStrut(10));
		b5.add(Box.createHorizontalStrut(40));
		b.add(b5);
		b.add(Box.createVerticalStrut(10));
		b6.add(Box.createHorizontalStrut(40));
		b.add(b6);
		b.add(Box.createVerticalStrut(10));
		b7.add(Box.createHorizontalStrut(40));
		b.add(b7);
		b.add(Box.createVerticalStrut(10));
		b8.add(Box.createHorizontalStrut(40));
		b.add(b8);
		b.add(Box.createVerticalStrut(10));
		b9.add(Box.createHorizontalStrut(40));
		b.add(b9);
		b.add(Box.createVerticalStrut(30));
		b10.add(Box.createHorizontalStrut(60));
		b.add(b10);
		b.add(Box.createVerticalStrut(180));

		b1.add(pRoomID);
		pRoomID.setFont(new Font("Arial", Font.BOLD, 30));

		b2.add(pRoomType);
		b2.add(inputRoomType);
		pRoomType.setPreferredSize(pNumOfBed.getPreferredSize());

		b3.add(pNumOfBed);
		b3.add(inputNumOfBed);
		pNumOfBed.setPreferredSize(pNumOfBed.getPreferredSize());


		b5.add(pSurcharge);
		b5.add(inputSurcharge);
		pSurcharge.setPreferredSize(pNumOfBed.getPreferredSize());

		b6.add(pDiscount);
		b6.add(inputDiscount);
		pDiscount.setPreferredSize(pNumOfBed.getPreferredSize());

		b7.add(pFirstHour);
		b7.add(inputFirstHour);
		pFirstHour.setPreferredSize(pNumOfBed.getPreferredSize());

		b8.add(pNextHour);
		b8.add(inputNextHour);
		pNextHour.setPreferredSize(pNumOfBed.getPreferredSize());

		b9.add(pOvernight);
		b9.add(inputOvernight);
		pOvernight.setPreferredSize(pNumOfBed.getPreferredSize());

//		b10.add(btnAddService);

		pnC2.add(b);

		pnC2.setBackground(new Color(166, 180, 184));

		pnC2.setBorder(new EmptyBorder(0, 0, 0, 50));

		pnCenter.add(pnC1, BorderLayout.CENTER);
		pnCenter.add(pnC2, BorderLayout.EAST);

		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BorderLayout());
		pnSouth.setBackground(new Color(227, 219, 176));

		JPanel pnSouthR = new JPanel();
		pnSouthR.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		pnSouthR.setBackground(new Color(227, 219, 176));

		JPanel pnSouthL = new JPanel();
		pnSouthL.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		pnSouthL.setBackground(new Color(227, 219, 176));

		JPanel pnSouthC = new JPanel();
		pnSouthC.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		pnSouthC.setBackground(new Color(227, 219, 176));

		pnSouthL.add(pTotal);
		pnSouthL.add(inputTotal = new JTextField(10));
		pnSouthL.add(txtTenNV = new JTextField(9));
		txtTenNV.setText(employee_dao.layNhanVienTheoMa(maNhanVien).getFullName());
		txtTenNV.setEditable(false);

		if(roomList.findStatus(id)) {
			pnSouthC.add(btnReady);			
		}

		if(roomList.findStatusOccupied(id)) {
			pnSouthR.add(btnCheckOut = new JButton("Check Out"));	
		}
		
		if(roomList.findStatusOrdered(id)) {
			pnSouthC.add(btnCheckIn = new JButton("Check In"));
		}
		
		if(roomList.findStatusReady(id)) {
			pnSouthC.add(btnOrder);
			pnSouthC.add(btnCheckIn = new JButton("Check In"));
		}
		
		
		
			

		pnSouth.add(pnSouthL, BorderLayout.WEST);
		pnSouth.add(pnSouthC, BorderLayout.CENTER);
		pnSouth.add(pnSouthR, BorderLayout.EAST);

		sectionBorder.add(pnCenter, BorderLayout.CENTER);
		sectionBorder.add(pnSouth, BorderLayout.SOUTH);

//		btnCancel.setFocusable(false);
		btnCheckIn.setFocusable(false);
		btnCheckOut.setFocusable(false);

//		btnCancel.setBackground(new Color(126,126,126));
//		btnCancel.setForeground(Color.white);
		btnReady.setBackground(new Color(0, 0, 0));
		btnReady.setForeground(Color.white);
		btnOrder.setBackground(new Color(0, 0, 0));
		btnOrder.setForeground(Color.white);
		btnCheckIn.setBackground(new Color(0, 0, 0));
		btnCheckIn.setForeground(Color.white);
		btnCheckOut.setBackground(new Color(197, 4, 4));
		btnCheckOut.setForeground(Color.white);
		btnAddService.setBackground(new Color(255, 165, 0));
		btnAddService.setForeground(Color.white);
		
		inputDiscount.setEditable(false);
		inputTimeOfDeinputarture.setEditable(false);
		inputFirstHour.setEditable(false);
		inputNextHour.setEditable(false);
		inputOvernight.setEditable(false);
		inputTimeOfDeinputarture.setEditable(false);
		inputTimeOfBooking.setEditable(false);
		inputTimeOfArrival.setEditable(false);
		inputTotal.setEditable(false);
		inputRoomType.setEditable(false);
		inputNumOfBed.setEditable(false);
		
		btnCheckIn.addActionListener(this);
		btnCheckOut.addActionListener(this);
		btnAddService.addActionListener(this);
		btnFind.addActionListener(this);
		btnOrder.addActionListener(this);
		btnReady.addActionListener(this);

		renderData();
	}

	public void renderData() {
		if (bookingList.findRoomBooking(idRoom) != -1) {
			int idBookingGet = bookingList.findRoomBooking(idRoom);
			Object[] bookingItem = bookingList.getOneBooking(idBookingGet);

			guest = guestList.getOneGuest((String) bookingItem[5]);

			inputName.setText(guest.getFullName());
//			inputGender.setText(Integer.toString(guest.getGender()));
			cbGender.setSelectedIndex(guest.getGender());
			inputDOB.setText(guest.getDOB());
			inputPhone.setText(guest.getPhoneNo());
			inputEmail.setText(guest.getEmail());
			inputAddress.setText(guest.getAddress());
			inputCard.setText(guest.getIndentityCard());
			inputStarPoints.setText(Float.toString(guest.getStarPoints()));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			inputNumOfAdults.setText(bookingItem[2].toString());
			inputNumOfChildren.setText(bookingItem[3].toString());
			if (bookingItem[8] != null) {
				String formattedDateBooking = sdf.format(bookingItem[8]);
				inputTimeOfBooking.setText(formattedDateBooking);
			}
			if (bookingItem[9] != null) {
				String formattedDateArrival = sdf.format(bookingItem[9]);
				inputTimeOfArrival.setText(formattedDateArrival);
			}
			if (bookingItem[10] != null) {
				String formattedDeinputarture = sdf.format(bookingItem[10]);
				inputTimeOfDeinputarture.setText(formattedDeinputarture);
			}
			aNote.setText(bookingItem[4].toString());
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnCheckIn)) {
			int notice = JOptionPane.showConfirmDialog(null, "Checkin this room?", "Checkin",
					JOptionPane.YES_NO_OPTION);
			if(notice == JOptionPane.YES_OPTION) {
				try {
					if(validData()) {
						CheckIn();
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(btnFind)) {
			FindGuest();
		} else if (e.getSource().equals(btnOrder)) {
			int notice = JOptionPane.showConfirmDialog(null, "Order this room?", "Order",
					JOptionPane.YES_NO_OPTION);
			if(notice == JOptionPane.YES_OPTION) {
				try {
					if(validData()) {
						OrderBefore();
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(btnCheckOut)) {
			try {
				CheckOut();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(btnReady)) {
			Ready();
		}
	}

	public void FindGuest() {
		String indentityCard = inputCard.getText();
//		JOptionPane.showConfirmDialog(null, guestList.findGuest(indentityCard));

		if (guestList.findGuest(indentityCard) == -1) {
			inputName.requestFocus();
		} else {
			guest = guestList.getOneGuest(indentityCard);

			inputName.setText(guest.getFullName());
			cbGender.setSelectedIndex(guest.getGender());
			inputDOB.setText(guest.getDOB());
			inputPhone.setText(guest.getPhoneNo());
			inputEmail.setText(guest.getEmail());
			inputAddress.setText(guest.getAddress());
			inputCard.setText(guest.getIndentityCard());
			inputStarPoints.setText(Float.toString(guest.getStarPoints()));
		}
	}

	public void CheckIn() throws ParseException {
		String inputTimeOfBooking1 = inputTimeOfBooking.getText();
		String indentityCard = inputCard.getText();
		int idBookingGet = 0;
		//Nếu chưa ofder trước
		if (inputTimeOfBooking1.equalsIgnoreCase("")) {
			//nếu khách mới thì tạo khách mới
			if (guestList.findGuest(indentityCard) == -1) {
				String inputName1 = inputName.getText();
				int inputGender1 = cbGender.getSelectedIndex();
				String inputDOB1 = inputDOB.getText();
				String inputPhone1 = inputPhone.getText();
				String inputAddress1 = inputAddress.getText();
				String inputEmail1 = inputEmail.getText();
				float inputStarPoints1 = Float.parseFloat(inputStarPoints.getText());

				Guest newGuest = new Guest(indentityCard, inputName1, inputDOB1, inputGender1, inputPhone1, inputEmail1,
						indentityCard, inputAddress1, inputStarPoints1);
				guestList.addGuest(newGuest);
			}
			// Booking
			int inputNumOfAdults1 = Integer.parseInt(inputNumOfAdults.getText());
			int inputNumOfChildren1 = Integer.parseInt(inputNumOfChildren.getText());
			String aNote1 = aNote.getText();

			Booking newBooking = new Booking(1, 1, inputNumOfAdults1, inputNumOfChildren1, aNote1, indentityCard,
					"E001", idRoom);
			bookingList.addBooking(newBooking);
			
			idBookingGet = bookingList.findRoomBooking1();
		//Nếu order trước
		} else {
			idBookingGet = bookingList.findRoomBooking(idRoom);
			bookingList.updateBookingToArr(idBookingGet);
		}
		
		BillEntity billEntity = new BillEntity(0,0, null, 0, indentityCard);
		billList.addBill(billEntity);
		
		
		int idLastBill = billList.findBillLastDetail();
		
		
		BillDetail billDetail = new BillDetail(0, "S002", idLastBill, 1, idBookingGet);
		billDetailList.addBillDetail(billDetail);
		
		JOptionPane.showMessageDialog(null, "Checkin successfull!");

		roomList.updateRoom(idRoom, "Occupied");

		this.setVisible(false);
	}

	public void OrderBefore() throws ParseException {
		String indentityCard = inputCard.getText();
		if (guestList.findGuest(indentityCard) == -1) {
			String inputName1 = inputName.getText();
			int inputGender1 = cbGender.getSelectedIndex();
			String inputDOB1 = inputDOB.getText();
			String inputPhone1 = inputPhone.getText();
			String inputAddress1 = inputAddress.getText();
			String inputEmail1 = inputEmail.getText();
			float inputStarPoints1 = Float.parseFloat(inputStarPoints.getText());

			Guest newGuest = new Guest(indentityCard, inputName1, inputDOB1, inputGender1, inputPhone1, inputEmail1,
					indentityCard, inputAddress1, inputStarPoints1);
			guestList.addGuest(newGuest);
		}
		// order
		int inputNumOfAdults1 = Integer.parseInt(inputNumOfAdults.getText());
		int inputNumOfChildren1 = Integer.parseInt(inputNumOfChildren.getText());
		String aNote1 = aNote.getText();

		Booking newBooking = new Booking(1, 1, inputNumOfAdults1, inputNumOfChildren1, aNote1, indentityCard, maNhanVien,
				idRoom);
		bookingList.addOrder(newBooking);

		JOptionPane.showMessageDialog(null, "Order successfull!");

		roomList.updateRoom(idRoom, "Ordered");

		this.setVisible(false);
	}
	
	private void showMessage(String txt, JTextField focus) {
		JOptionPane.showMessageDialog(null, txt);
		focus.requestFocus();
	}

	private boolean validData() {
		String numChild = inputNumOfChildren.getText().trim();
		String numAdu = inputNumOfAdults.getText().trim();
		String inputName1 = inputName.getText();
		String inputDOB1 = inputDOB.getText();
		String inputPhone1 = inputPhone.getText();
		String inputEmail1 = inputEmail.getText();
		String inputStarPoints1 = inputStarPoints.getText();
		String card = inputCard.getText();
		
		if (!(card.length() > 0)) {
			showMessage("Vui lòng nhập CCCD", inputCard);
			return false;
		}
		if (!(inputName1.length() > 0 && inputName1.matches(
				"[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ']+"))) {
			showMessage("Họ tên phải là chữ hoa hoặc chữ thường và không chứa các ký tự đặc biệt",
					inputName);
			return false;
		}
		if (!(inputDOB1.length() > 0 && inputDOB1.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*"))) {
			showMessage("Ngày sinh phải đúng định dạng yyyy-mm-dd", inputDOB);
			return false;
		}
		if (!(inputPhone1.length() > 0 && inputPhone1.matches("\\d{10}"))) {
			showMessage("Số điện thoại phải là 10 số", inputPhone);
			return false;
		}
		if (!(inputEmail1.length() > 0 && inputEmail1.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
			showMessage("Vui lòng nhập đúng định dạng email", inputEmail);
			return false;
		}
		if (!(numAdu.length() > 0 && numAdu.matches("[0-9']+"))) {
			showMessage("Số người lớn phải là số", inputNumOfAdults);
			return false;
		}
		if (!(numChild.length() > 0 && numChild.matches("[0-9']+"))) {
			showMessage("Số trẻ em phải là số", inputNumOfChildren);
			return false;
		}
		return true;
	}
	
	public void CheckOut() throws ParseException {
		int idBookingGet = 0;
		String inputTimeOfArr1 = inputTimeOfArrival.getText();
		if(!inputTimeOfArr1.equalsIgnoreCase("")) {
			
			idBookingGet = bookingList.findRoomBooking(idRoom);
			
			Object[] bookingItem = bookingList.getOneBooking(idBookingGet);

			JFrame bill = new JFrame();
			bill = new Bill(bookingItem);
			bill.setVisible(true);
			
			
		} else {
			JOptionPane.showMessageDialog(null, "room is not checkin");
		}
		
	}
	
	public void Ready() {
		roomList.updateRoom(idRoom, "Ready");
		this.setVisible(false);
	}

//	public static void main(String[] args) {
//		new frmInforBooking().setVisible(true);
//	}
}
