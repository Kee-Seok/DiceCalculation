package minigame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class BaseballGame extends JPanel{

	private static int objSize = 200; //공, 숫자, 텍스트필드 가로 세로 크기
	private static Font font = new Font("Serif",Font.BOLD,100);
	private static Font taFont = new Font("Serif",Font.BOLD,18);
	public static int gameCount = 0; //게임 회차
	public static Image[] img = {
			new ImageIcon("./temp/ball.png").getImage().getScaledInstance(objSize, objSize, Image.SCALE_SMOOTH),
	};
	
	public static Image background = new ImageIcon("./temp/background.jpg").getImage();
	
	public static String[] str= {"?","?","?","?"}; //공에 그려질 숫자
	public static JTextField[] tf = {
			new JTextField(),
			new JTextField(),
			new JTextField(),
			new JTextField()
	};
	
	public static JTextArea ta = new JTextArea();
	public static JScrollPane scroll = new JScrollPane(ta);
	
	public BaseballGame() {
		setLayout(null);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setEditable(false);
		ta.setBackground(C.green);
		ta.setFont(taFont);
		scroll.setBounds((objSize+10)*4+110,100,300,600);
		for(int i = 0; i < tf.length; i++) {
			tf[i].setBounds(50+((objSize+10)*i),500,objSize,objSize);
			tf[i].setBackground(Color.black);
			tf[i].setForeground(Color.white);
			tf[i].setFont(font);
			tf[i].setHorizontalAlignment(JTextField.CENTER);
			tf[i].setDocument(new JTextFieldLimit(1));
			add(tf[i]);
		}
		add(scroll);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(C.yellow);
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("숫자야구게임!", 120, 150);
		for(int i = 0; i < 4; i++) {
		g.drawImage(img[0], 50+((objSize+10)*i),220, this);
		}
		for(int i = 0; i < 4; i++) {
		g.drawString(str[i], 125+((objSize+10)*i),350);
		}
	}
	                             
	
}
