package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.EmployeeIDao;
import entity.Employee;

public class frmChucNang extends JFrame implements ActionListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6793627072926375358L;
	private JButton btnAllRoom, btnService, btnSummary, btnAdministration, btnLogout;
	private JPanel pnService, pnCenter, pnEmployee, pnAdministation, pnSummary, pnAllRoom;
	private JTextField txtTenNV;
	private Registry registry = LocateRegistry.getRegistry("192.168.1.19", 2000);


	public EmployeeIDao employee_dao;

	boolean flag = true;

	public static String maNhanVien = null;
	private EmployeeIDao listEmployee = (EmployeeIDao) registry.lookup("employeeIDao");

	public frmChucNang(String maNV) throws RemoteException, NotBoundException {
		employee_dao = (EmployeeIDao) registry.lookup("employeeIDao");

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());

		maNhanVien = maNV;

		JPanel pnWeast = new JPanel();
		pnWeast.setLayout(new BorderLayout());
		pnWeast.setBackground(new Color(23, 38, 47));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int h = (int) screenSize.getHeight();
		pnWeast.setPreferredSize(new Dimension(300, h));

		String pathiconLG = "/image/Logo.png";
		URL iconURL = getClass().getResource(pathiconLG);

		ImageIcon logoIcon = new ImageIcon(iconURL);

		JPanel pnN = new JPanel();

		JLabel lblLogo = new JLabel(logoIcon);
		lblLogo.setPreferredSize(new Dimension(300, 150));

		pnN.add(lblLogo);

		JPanel pnC1 = new JPanel();
		pnC1.setLayout(null);

		Color mauBT = new Color(228, 201, 139);

		String pathiconST = "/image/IconSetting.png";
		URL iconSTURL = getClass().getResource(pathiconST);
		ImageIcon iconST = new ImageIcon(iconSTURL);

		JButton bts = new JButton(iconST);
		pnC1.add(bts);
		bts.setBounds(250, h - 264, 50, 40);
		bts.setBackground(mauBT);
		bts.setFocusable(false);
		bts.setFocusPainted(false);
		bts.setBorderPainted(false);

		pnC1.add(btnAllRoom = new JButton("All Room"));
		pnC1.add(btnService = new JButton("Service"));
		pnC1.add(btnSummary = new JButton("Statistic"));
		pnC1.add(btnAdministration = new JButton("Administration"));
		pnC1.add(btnLogout = new JButton("Log out"));

		pnC1.add(txtTenNV = new JTextField(12));
		txtTenNV.setText(employee_dao.layNhanVienTheoMa(maNV).getFullName());

		btnAllRoom.setBounds(0, 0, 300, 40);
		btnAllRoom.setFont(new Font("Arial", Font.BOLD, 20));
		btnService.setBounds(0, btnAllRoom.getY() + 50, 300, 40);
		btnService.setFont(btnAllRoom.getFont());
		btnSummary.setBounds(0, btnService.getY() + 50, 300, 40);
		btnSummary.setFont(btnService.getFont());
		btnAdministration.setBounds(0, btnSummary.getY() + 50, 300, 40);
		btnAdministration.setFont(btnSummary.getFont());
		btnLogout.setBounds(0, h - 264, 300 - 50, 40);
		btnLogout.setFont(btnAdministration.getFont());
		btnService.setBorderPainted(false);
		btnAllRoom.setBorderPainted(false);
		btnLogout.setBorderPainted(false);
		btnAdministration.setBorderPainted(false);
		btnSummary.setBorderPainted(false);

		pnC1.setPreferredSize(new Dimension(300, h));
		pnC1.setBackground(new Color(23, 38, 47));
		pnWeast.add(lblLogo, BorderLayout.NORTH);
		pnWeast.add(pnC1, BorderLayout.CENTER);

		JPanel pnS1 = new JPanel();

		pnWeast.add(pnS1, BorderLayout.SOUTH);

		add(pnWeast, BorderLayout.WEST);
		btnAllRoom.setBackground(mauBT);
		btnService.setBackground(mauBT);
		btnSummary.setBackground(mauBT);
		btnAdministration.setBackground(mauBT);
		btnLogout.setBackground(mauBT);

		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setFocusPainted(false);

		btnAllRoom.setFocusable(false);
		btnService.setFocusable(false);
		btnSummary.setFocusable(false);
		btnAdministration.setFocusable(false);
		txtTenNV.setBounds(0, 580, 150, 20);
		txtTenNV.setEditable(false);
		btnLogout.setFocusable(false);

		btnAllRoom.addActionListener(this);
		btnService.addActionListener(this);
		btnSummary.addActionListener(this);
		btnAdministration.addActionListener(this);
		btnLogout.addActionListener(this);

		pnService = new JPanel();
		pnService = new frmService(registry);
		pnEmployee = new JPanel();
		pnEmployee = new frmEmployeeManager(registry);
		pnAdministation = new JPanel();
		pnAdministation = new frmAdministration(registry);
		pnSummary = new JPanel();
		pnSummary = new frmSummary(registry);

		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		add(pnCenter, BorderLayout.CENTER);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnCenter.removeAll();
		pnCenter.validate();
		pnCenter.add(pnAllRoom = new frmAllRoom(maNV, registry), BorderLayout.CENTER);
		pnCenter.revalidate();
		pnCenter.repaint();

		for (Employee e : listEmployee.getAllEmployees()) {
			if (e.getEmployeeId().equalsIgnoreCase(maNhanVien)) {
				if (e.getRoleId().getRoleTitle().equalsIgnoreCase("Admin")) {
					btnSummary.setVisible(true);
					btnAdministration.setVisible(true);
				} else {
					btnSummary.setVisible(false);
					btnAdministration.setVisible(false);
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnAllRoom)) {
			btnAdministration.setBackground(new Color(228, 201, 139));
			btnAllRoom.setBackground(new Color(100, 92, 52));
			btnService.setBackground(new Color(228, 201, 139));
			btnSummary.setBackground(new Color(228, 201, 139));

			pnCenter.removeAll();
			pnCenter.validate();
			try {
				pnCenter.add(pnAllRoom = new frmAllRoom(maNhanVien, registry), BorderLayout.CENTER);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pnCenter.revalidate();
			pnCenter.repaint();
		} else if (o.equals(btnService)) {
			btnService.setBackground(new Color(100, 92, 52));
			btnAllRoom.setBackground(new Color(228, 201, 139));
			btnSummary.setBackground(new Color(228, 201, 139));
			btnAdministration.setBackground(new Color(228, 201, 139));

//			btnService.setEnabled(false);

			pnCenter.removeAll();
			pnCenter.validate();
			try {
				pnCenter.add(pnService = new frmService(registry), BorderLayout.CENTER);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pnCenter.revalidate();
			pnCenter.repaint();
		}

		else if (o.equals(btnAdministration)) {
			btnAdministration.setBackground(new Color(100, 92, 52));
			btnAllRoom.setBackground(new Color(228, 201, 139));
			btnService.setBackground(new Color(228, 201, 139));
			btnSummary.setBackground(new Color(228, 201, 139));

			pnCenter.removeAll();
			pnCenter.validate();
			pnCenter.add(pnAdministation = new frmAdministration(registry), BorderLayout.CENTER);
			pnCenter.revalidate();
			pnCenter.repaint();
		}

		else if (o.equals(btnSummary)) {
			btnAdministration.setBackground(new Color(228, 201, 139));
			btnAllRoom.setBackground(new Color(228, 201, 139));
			btnService.setBackground(new Color(228, 201, 139));
			btnSummary.setBackground(new Color(100, 92, 52));

			pnCenter.removeAll();
			pnCenter.validate();
			try {
				pnCenter.add(pnSummary = new frmSummary(registry), BorderLayout.CENTER);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pnCenter.revalidate();
		} else if (o.equals(btnLogout)) {
			int notice = JOptionPane.showConfirmDialog(null, "Log out ?", "Log out", JOptionPane.YES_NO_OPTION);
			if (notice == JOptionPane.YES_OPTION) {
				this.setVisible(false);
				frmDangNhap frm = new frmDangNhap();
				frm.setVisible(true);
			}
		}
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		new frmChucNang(maNhanVien).setVisible(true);
	}
}