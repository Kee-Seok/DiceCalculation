package minigame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
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
		BaseballGame.tf[0].requestFocus();
		Timer tfCheckTimer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(BaseballGame.tf[0].isFocusOwner()) {
				if(BaseballGame.tf[0].getText().length()>0) {BaseballGame.tf[1].requestFocus();}else{return;}
			}
			if(BaseballGame.tf[1].isFocusOwner()) {
				if(BaseballGame.tf[1].getText().length()>0) {BaseballGame.tf[2].requestFocus();}else{return;}
			}
			if(BaseballGame.tf[2].isFocusOwner()) {
				if(BaseballGame.tf[2].getText().length()>0) {BaseballGame.tf[3].requestFocus();}else{return;}
			}
		}
	});
		tfCheckTimer.start();
	}
	
	public void gameStart() {
		Model.init();
		for(int i = 0; i < Model.a.length; i++) {
		System.out.println(Model.a[i]);
		}
		for(int i = 0; i < BaseballGame.tf.length; i++) {
		BaseballGame.tf[i].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					Model.comparison(BaseballGame.tf);
					if(Model.isEmptyNum!=true) { //텍스트필드에 숫자가 모두 입력돼있으면  처리 후 텍스트필드 지우고 포커스 첫번째 텍스트필드로 이동
					Model.clearTf();
					BaseballGame.tf[0].requestFocus();
					}else {//텍스트필드가 하나라도 비었을때 아무것도 연산하지 않음. JOptionPane만 띄움
						Model.isEmptyNum = false;
					}
					break;
				case KeyEvent.VK_ESCAPE :
					System.exit(0);
					break;
				}
			}
		});
		}
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
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
