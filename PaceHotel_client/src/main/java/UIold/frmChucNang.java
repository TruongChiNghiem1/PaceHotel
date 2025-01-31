package UIold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class frmChucNang extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6224401903802245074L;
	private JButton btnAllRoom, btnService, btnSummary, btnAdministration, btnLogout;
	private JPanel pnService, pnCenter, pnEmployee, pnAdministation, pnSummary, pnAllRoom;
	private Registry registry = LocateRegistry.getRegistry("192.168.1.4", 2000);
	
	public frmChucNang() throws MalformedURLException, RemoteException, ClassNotFoundException, NotBoundException {
		super("");

		createUI();
	}

	public void createUI() throws MalformedURLException, RemoteException, ClassNotFoundException, NotBoundException {

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());

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
		pnC1.add(btnSummary = new JButton("Summary"));
		pnC1.add(btnAdministration = new JButton("Administration"));
		pnC1.add(btnLogout = new JButton("Log out"));

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
		btnLogout.setFocusable(false);

		btnAllRoom.addActionListener(this);
		btnService.addActionListener(this);
		btnLogout.addActionListener(this);
		btnSummary.addActionListener(this);
		btnAdministration.addActionListener(this);

		pnService = new JPanel();
		pnService = new frmService(registry);
		pnEmployee = new JPanel();
//		pnEmployee = new frmEmployeeManager(registry);
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
		pnCenter.add(pnAllRoom = new frmAllRoom(registry), BorderLayout.CENTER);
		pnCenter.revalidate();
		pnCenter.repaint();
	}


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
				pnCenter.add(pnAllRoom = new frmAllRoom(registry), BorderLayout.CENTER);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
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
			pnCenter.add(pnService = new frmService(registry), BorderLayout.CENTER);
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
			pnCenter.add(pnSummary = new frmSummary(registry), BorderLayout.CENTER);
			pnCenter.revalidate();
		}
		else if (o.equals(btnLogout)) {
			dispose();
			new frmDangNhap();
		}
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, ClassNotFoundException, NotBoundException {
		new frmChucNang();
	}
}