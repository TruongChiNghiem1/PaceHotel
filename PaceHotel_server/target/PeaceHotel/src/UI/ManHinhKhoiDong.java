package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ManHinhKhoiDong extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public JLabel lblLoad;
	public JProgressBar progressBar;
	JLabel lblBackground;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				try
				{
					ManHinhKhoiDong manHinhKhoiDong = new ManHinhKhoiDong();
					manHinhKhoiDong.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public ManHinhKhoiDong()
	{
		setSize(800, 700);
		setUndecorated(true);
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(225, 255, 255));
		progressBar.setForeground(new Color(255, 153, 51));
		progressBar.setBorder(BorderFactory.createLineBorder(Color.black));
		progressBar.setPreferredSize(new Dimension(200, 35));
		progressBar.setStringPainted(true);
		add(progressBar, BorderLayout.SOUTH);
		
		lblBackground = new JLabel("");
		JLabel lblNoiDung = new JLabel("PeaceHotel App");
		
		String pathiconLogo = "/image/Logo1.png";
		URL iconSTURL = getClass().getResource(pathiconLogo);
		ImageIcon iconLogo = new ImageIcon(iconSTURL);
		
		String img = "C:\\Users\\Dinh Phong\\Desktop\\HOC\\Huong Su kien java\\BaiTap\\Nhom2\\src\\image\\Login_BG.jpeg";
		lblBackground.setIcon(ResizeImage(img));
		lblBackground.setLayout(new BoxLayout(lblBackground, BoxLayout.Y_AXIS));
		lblNoiDung.setFont(new Font("Arial", Font.BOLD, 30));
		lblNoiDung.setForeground(new Color(255, 153, 51));
		lblNoiDung.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBackground.add(lblNoiDung);
		add(lblBackground);
	}
	
	public ImageIcon ResizeImage(String ImagePath)
	{
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(800, 700, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
}
