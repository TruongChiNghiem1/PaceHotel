package UIold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dao.BillDetail_DAO;
import dao.Bill_DAO;
import dao.Booking_DAO;
import dao.Guest_DAO;
import dao.Room_DAO;
import entity.BillEntity;
import entity.Guest;
import entity.Room;

public class Bill extends JFrame implements ActionListener, MouseListener, Serializable{
	private JLabel lbName, lbLogo, lbGender, lbCard, lbDOB, lbPhone, lbPoint, lbAdult, lbChild, lbArrival, lbDeparture, lbDetail, lbTotal,lbStart;
	private JTextField txtTitle,txtGender,txtName, txtCard, txtDOB, txtPhone, txtPoint, txtAdult, txtChild, txtArrival, txtDeparture, txtTotal;
	private JTable tableBill;
	private DefaultTableModel modelBill;
	private JButton btnCancle, btnInvoice;
	private JPanel pnTitle, pnContent,pnTail;
	private Guest guest;
	private Guest_DAO guestList;
	private BillDetail_DAO billDetailList;
	private Booking_DAO listBooking;
	private Room_DAO roomList;
	private Object[] objectItem;
	private Bill_DAO billList;
	private BillEntity billEntity;
	
	public Bill(Object[] bookingItem) throws ParseException {
		setTitle("Bill");
		setSize(900,820);
//		setResizable(false);
		setLocationRelativeTo(null);
//		setVisible(true);
		objectItem = bookingItem;
		roomList = new Room_DAO();
		billDetailList = new BillDetail_DAO();
		
		pnTitle = new JPanel();
		String imageLogoPath = "/image/3.png";
		URL imageLogoBillURL = getClass().getResource(imageLogoPath);
		Image IconLogoBill = Toolkit.getDefaultToolkit().createImage(imageLogoBillURL);
		lbLogo = new JLabel(new ImageIcon(IconLogoBill));
		pnTitle.setBackground(new Color(23, 38, 47));
		pnTitle.setPreferredSize(new Dimension(900, 120));
		
//		String imageStart = "/image/Star.png";
		String imageStart = "";
		URL imageStartURL = getClass().getResource(imageStart);
		Image IconStart = Toolkit.getDefaultToolkit().createImage(imageStartURL);
		lbStart = new JLabel(new ImageIcon(IconStart));
		Dimension sizeImgStart = lbStart.getPreferredSize();
		lbStart.setBounds(388, 100, sizeImgStart.width, sizeImgStart.height);
		pnContent = new JPanel();
		pnContent.setBackground(new Color(166, 180, 184));
		
		txtTitle = new JTextField("RECEIPT");
		txtTitle.setFont(new Font("Arial", Font.BOLD, 24));
		txtTitle.setBackground(new Color(166, 180, 184));
		txtTitle.setBorder(new LineBorder(new Color(166, 180, 184)));
		Box box1 = Box.createHorizontalBox();
		box1.setMaximumSize(txtTitle.getPreferredSize());
		box1.add(txtTitle);
		
		
		lbName = new JLabel("Full Name:");
		txtName = new JTextField();
		txtName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtName.setBackground(new Color(166, 180, 184));
		lbGender = new JLabel("Gender:");
		txtGender = new JTextField();
		txtGender.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtGender.setBackground(new Color(166, 180, 184));
		Box box2 = Box.createHorizontalBox();
		box2.add(lbName);
		box2.add(Box.createHorizontalStrut(5));
		box2.add(txtName);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(lbGender);
		box2.add(Box.createHorizontalStrut(5));
		box2.add(txtGender);
		
		lbCard = new JLabel("Indentity Card:");
		txtCard = new JTextField();
		txtCard.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtCard.setBackground(new Color(166, 180, 184));
		lbDOB = new JLabel("DOB:");
		txtDOB = new JTextField();
		txtDOB.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtDOB.setBackground(new Color(166, 180, 184));
		Box box3 = Box.createHorizontalBox();
		box3.add(lbCard);
		box3.add(Box.createHorizontalStrut(5));
		box3.add(txtCard);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(lbDOB);
		box3.add(Box.createHorizontalStrut(5));
		box3.add(txtDOB);
	
		lbPhone = new JLabel("Phone Number:");
		txtPhone= new JTextField();
		txtPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtPhone.setBackground(new Color(166, 180, 184));
		lbPoint= new JLabel("Star Points:");
		txtPoint= new JTextField();
		txtPoint.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtPoint.setBackground(new Color(166, 180, 184));
		Box box4 = Box.createHorizontalBox();
		box4.add(lbPhone);
		box4.add(Box.createHorizontalStrut(5));
		box4.add(txtPhone);
		box4.add(Box.createHorizontalStrut(10));
		box4.add(lbPoint);
		box4.add(Box.createHorizontalStrut(5));
		box4.add(txtPoint);
		
		lbAdult = new JLabel("Number of Adults:");
		txtAdult= new JTextField(30);
		txtAdult.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtAdult.setBackground(new Color(166, 180, 184));
		lbChild= new JLabel("Number of Children:");
		txtChild= new JTextField(10);
		txtChild.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtChild.setBackground(new Color(166, 180, 184));
		Box box5 = Box.createHorizontalBox();
		box5.add(lbAdult);
		box5.add(Box.createHorizontalStrut(5));
		box5.add(txtAdult);
		box5.add(Box.createHorizontalStrut(10));
		box5.add(lbChild);
		box5.add(Box.createHorizontalStrut(5));
		box5.add(txtChild);
		
		lbArrival = new JLabel("Time of arrival:");
		txtArrival= new JTextField();
		txtArrival.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtArrival.setBackground(new Color(166, 180, 184));
		lbDeparture= new JLabel("Time of departure:");
		txtDeparture= new JTextField();
		txtDeparture.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtDeparture.setBackground(new Color(166, 180, 184));
		Box box6 = Box.createHorizontalBox();
		box6.add(lbArrival);
		box6.add(Box.createHorizontalStrut(5));
		box6.add(txtArrival);
		box6.add(Box.createHorizontalStrut(10));
		box6.add(lbDeparture);
		box6.add(Box.createHorizontalStrut(5));
		box6.add(txtDeparture);
		
		pnTail = new JPanel();
		pnTail.setPreferredSize(new Dimension(800, 400));
		pnTail.setBackground(new Color(166, 180, 184));
		
		lbDetail = new JLabel("Detail");
		lbDetail.setBounds(0, 0, 40, 15);
		
		modelBill = new DefaultTableModel();
		JScrollPane sc = new JScrollPane();
		String[] tieuDe = {"STT","Room ID","Service Name","Qty","Price","Amount"};
		modelBill= new DefaultTableModel(tieuDe, 0);
		sc.setViewportView(tableBill = new JTable(modelBill));
		tableBill.getTableHeader().setBackground(new Color(227, 219, 176));
		TableColumnModel columnModel = tableBill.getColumnModel();
		TableColumn STT = columnModel.getColumn(0);
		STT.setPreferredWidth(10);
		TableColumn Qty = columnModel.getColumn(3);
		Qty.setPreferredWidth(40);
		TableColumn ServiceName = columnModel.getColumn(2);
		ServiceName.setPreferredWidth(100);
		TableColumn Amount = columnModel.getColumn(5);
		Amount.setPreferredWidth(100);
		sc.setBounds(0,20,800,300);
		
		lbTotal= new JLabel("Total:");
		txtTotal= new JTextField(10);
		txtTotal.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtTotal.setBackground(new Color(166, 180, 184));
		lbTotal.setBounds(600, 330, 40, 15);
		txtTotal.setBounds(640, 330, 80, 15);
		
		btnCancle = new JButton("Cancel");
		btnCancle.setBackground(new Color(169,169,169));
		btnCancle.setForeground(Color.white);
		btnCancle.setBorder(new LineBorder(new Color(169,169,169)));
		btnCancle.setBounds(600, 350, 80, 30);
		
		btnInvoice = new JButton("Invoice");
		btnInvoice.setBackground(new Color(6, 111, 23));
		btnInvoice.setForeground(Color.white);
		btnInvoice.setBorder(new LineBorder(new Color(6, 111, 23)));
		btnInvoice.setBounds(700, 350, 80, 30);
		
		add(lbStart);
		pnTitle.add(lbLogo,BorderLayout.NORTH);
		add(pnTitle,BorderLayout.NORTH);
		add(pnContent);
		Box box = Box.createVerticalBox();
		pnContent.add(box);
		box.add(Box.createVerticalStrut(30));
		box.add(box1);
		box.add(Box.createVerticalStrut(20));
		box.add(box2);
		box.add(Box.createVerticalStrut(20));
		box.add(box3);
		box.add(Box.createVerticalStrut(20));
		box.add(box4);
		box.add(Box.createVerticalStrut(20));
		box.add(box5);
		box.add(Box.createVerticalStrut(20));
		box.add(box6);
		box.add(Box.createVerticalStrut(20));
		box.add(pnTail);
		pnTail.add(lbDetail);
		pnTail.add(sc);
		pnTail.add(lbTotal);
		pnTail.add(txtTotal);
		pnTail.add(btnCancle);
		pnTail.add(btnInvoice);
		
		pnTail.setLayout(null);
		
		btnInvoice.addActionListener(this);
		renderData(bookingItem);
	}
	
	public void renderData(Object[] bookingItem) throws ParseException {
		
		guestList = new Guest_DAO();
		listBooking = new Booking_DAO();
		guest = guestList.getOneGuest((String) bookingItem[5]);

		txtName.setText(guest.getFullName());
		txtGender.setText(guest.getGender() == 1 ? "Female" : "Male");
		txtDOB.setText(guest.getDOB());
		txtPhone.setText(guest.getPhoneNo());
//		txt.setText(guest.getEmail());
//		inputAddress.setText(guest.getAddress());
		txtCard.setText(guest.getIndentityCard());
		txtPoint.setText(Float.toString(guest.getStarPoints()));
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		txtAdult.setText(bookingItem[2].toString());
		txtChild.setText(bookingItem[3].toString());
//		if (bookingItem[8] != null) {
//			String formattedDateBooking = sdf.format(bookingItem[8]);
//			inputTimeOfBooking.setText(formattedDateBooking);
//		}
		
		String formattedDateArrival = sdf.format(bookingItem[9]);
		txtArrival.setText(formattedDateArrival);
		String formattedDeinputarture;
		if(bookingItem[10] != null) {
			formattedDeinputarture = sdf.format(bookingItem[10]);
			txtDeparture.setText(formattedDeinputarture);
		} else {
			Date now = new Date();
			Timestamp timestamp = new Timestamp(now.getTime());
			formattedDeinputarture = timestamp.toString();
			txtDeparture.setText(formattedDeinputarture);
		}
//		aNote.setText(bookingItem[4].toString());
		
		int index = 1;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = format.parse(bookingItem[9].toString());
        Date date2 = format.parse(formattedDeinputarture);
        long diffInMillis = date2.getTime() - date1.getTime();
        
        long diffHours = diffInMillis / (60 * 60 * 1000) % 24;
        long diffDays = diffInMillis / (24 * 60 * 60 * 1000);
        
        Object[] room = listBooking.getOneRoom((int) bookingItem[0]);
        float totalRoom = 0;
        if(diffDays == 0) {
        	totalRoom = (float) room[2];
        	if(diffHours > 1) {
            	totalRoom += (float) room[3];
            	if(totalRoom > (float) room[4]) {
            		totalRoom = (float) room[4];
            	}
            }
        } else {
        	for (int i = 0; i < diffDays - 1; i++) {
        		totalRoom += (float) room[4];
    		}
        	float totalRoomHour = 0;
        	totalRoomHour = (float) room[2];
        	if(diffHours > 1) {
        		totalRoomHour += (float) room[3];
            	if(totalRoomHour > (float) room[4]) {
            		totalRoomHour = (float) room[4];
            	}
            }
    		totalRoom += totalRoomHour;
        }
        
        long diffHoursRender = diffHours;
        if(diffHoursRender == 0) {
        	diffHoursRender = 1;
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(localeVN);
        String priceVN = vnFormat.format(totalRoom);
        
        float total = 0;
		Object[] rowData1 = {index, bookingItem[7], bookingItem[7], Long.toString(diffDays) + " Day " + Long.toString(diffHoursRender) + " Hour", 0, priceVN};
		total += totalRoom;
		modelBill.addRow(rowData1);
		index++;
		for (Object[] o : billDetailList.getAllServiceBill(Integer.parseInt(bookingItem[0].toString()))) {
			double price = Double.parseDouble(o[3].toString());
	        String priceVNFormat = vnFormat.format(price);
	        
	        double amount = Double.parseDouble(o[4].toString());
	        String priceVNAmount = vnFormat.format(amount);

			total += Float.parseFloat(o[4].toString());
			Object[] rowData = {index, o[0], o[1], o[2], priceVNFormat, priceVNAmount};
			modelBill.addRow(rowData);
			index++;
		}
		txtTotal.setText("" + total);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnInvoice)) {
			Invoice();
		}
	}
	
	
	public void Invoice() {
		billList = new Bill_DAO();
		int idBookingGet = 0;
		int notice = JOptionPane.showConfirmDialog(null, "Are you check out this room", "Invoice",
				JOptionPane.YES_NO_OPTION);
		if (notice == JOptionPane.YES_OPTION) {
			idBookingGet = listBooking.findRoomBooking(objectItem[7].toString());
			listBooking.updateBookingToDep(idBookingGet);
			
			int billID = billDetailList.findBillID(idBookingGet);
			float subTotal = Float.parseFloat(txtTotal.getText());
			
			billEntity = new BillEntity(billID, subTotal, null, 0, objectItem[7].toString());
			
			billList.updateBillCheckOut(billEntity);
			
			roomList.updateRoom(objectItem[7].toString(), "Dirty");
			
			this.setVisible(false);
		}
	}
//	public static void main(String[] args) {
//		new Bill();
//		
//	}
	
	
		
}
