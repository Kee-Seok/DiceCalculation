package minigame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main extends JFrame{

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 800;
	public static BaseballGame baseball = new BaseballGame();
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(null);
		setUndecorated(true);
		baseball.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
		add(baseball);
		setKey();
		setVisible(true);
		gameStart();
		baseball.tf[0].requestFocus();
	}
	
	public void gameStart() {
		Model.init();
		for(int i = 0; i < Model.a.length; i++) {
		System.out.println(Model.a[i]);
		}
		BaseballGame.tf[3].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					Model.comparison(BaseballGame.tf);
					Model.clearTf();
					baseball.tf[0].requestFocus();
					break;
				}
			}
		});
	}
	
	public void setKey() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE :
					System.exit(0);
					break;
				}
			}
		});
		baseball.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE :
					System.exit(0);
					break;
				}
			}
		});
		BaseballGame.tf[0].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE :
					System.exit(0);
					break;
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
