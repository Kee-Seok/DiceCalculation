package cog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Cognition extends JFrame{

	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 800;
	public static final int DICE_WIDTH = 170;
	public static final int DICE_HEIGHT = 170;
	public static final int GAB = 10; // 주사위 간격 크기임.
	public static final int TITLE_HEIGHT = 150;
	public static final int QUESTION_HEIGHT = 400;
	public static final int ANSWER_HEIGHT = 200;
	
	int fontSize = 60; // 게임 타이틀 글자 크기
	int bigFontSize = 120;
	DecimalFormat form = new DecimalFormat("###,###");
	Image[] dice = new Image[6]; // 카드 이미지 배열
	
	JPanel mainPanel = new JPanel();
	
	JLayeredPane jlp = this.getLayeredPane();
	
	JLabel titleLabel = new JLabel("주사위 뒷면 계산하기",SwingConstants.CENTER); //Game title
	JLabel questionLabel = new JLabel("",SwingConstants.CENTER); //JLabel involved question information
	JPanel bar = new JPanel();
	JLabel[] numb = new JLabel[6];
	JLabel[] eachNumb = new JLabel[6];//정답 나올때 주사위마다 뒷면 숫자 그려줄 라벨
	JLabel opLabel = new JLabel("",SwingConstants.CENTER);
	JLabel answerLabel = new JLabel("정답은?",SwingConstants.RIGHT); //representation of answer
	
	int[] diceNum = new int[6]; //Back number of dices.
	int firstNum, secondNum;
	
	String[] op = {"+", "-", "x"};
	int enterKey = 1; //if you press enter key, this index will be changed to 2, and press once more, index will be changed to 1 again. 
	int opRand; //operator
	public Cognition() {
		init();
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					pressEnterKey();
					if(enterKey==1){
						System.out.println("firstNum: "+firstNum);
						System.out.println("secondNum: "+secondNum);
						enterKey = 2;
					}else {
						enterKey = 1;
					}
					break;
				}
			}
		});
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		mainPanel.setBackground(new Color(238,230,196));
		mainPanel.setLayout(null);
		setTitle("주사위 계산하기");
		setIconImage(new ImageIcon("./temp/dice6.png").getImage());
		setDiceImage(); //This method is to get 'the name with Dice Image' from temp folder and add to ArrayList<Image> variation.
		for(int i = 0; i < dice.length; i++) {
			numb[i] = new JLabel("",SwingConstants.CENTER);
			eachNumb[i] = new JLabel("",SwingConstants.CENTER);
			eachNumb[i].setForeground(Color.white);
			eachNumb[i].setFont(new Font("serif",Font.BOLD,bigFontSize));
		}
		numb[0].setBounds(300,150,DICE_WIDTH,DICE_HEIGHT);
		numb[1].setBounds(300+DICE_WIDTH+GAB,150,DICE_WIDTH,DICE_HEIGHT);
		numb[2].setBounds(300+((DICE_WIDTH+GAB)*2),150,DICE_WIDTH,DICE_HEIGHT);
		numb[3].setBounds(300,150+DICE_HEIGHT+GAB,DICE_WIDTH,DICE_HEIGHT);
		numb[4].setBounds(300+DICE_WIDTH+GAB,150+DICE_HEIGHT+GAB,DICE_WIDTH,DICE_HEIGHT);
		numb[5].setBounds(300+((DICE_WIDTH+GAB)*2),150+DICE_HEIGHT+GAB,DICE_WIDTH,DICE_HEIGHT);
		
		eachNumb[0].setBounds(300,150,DICE_WIDTH,DICE_HEIGHT);
		eachNumb[1].setBounds(300+DICE_WIDTH+GAB,150,DICE_WIDTH,DICE_HEIGHT);
		eachNumb[2].setBounds(300+((DICE_WIDTH+GAB)*2),150,DICE_WIDTH,DICE_HEIGHT);
		eachNumb[3].setBounds(300,150+DICE_HEIGHT+GAB,DICE_WIDTH,DICE_HEIGHT);
		eachNumb[4].setBounds(300+DICE_WIDTH+GAB,150+DICE_HEIGHT+GAB,DICE_WIDTH,DICE_HEIGHT);
		eachNumb[5].setBounds(300+((DICE_WIDTH+GAB)*2),150+DICE_HEIGHT+GAB,DICE_WIDTH,DICE_HEIGHT);
		
		opLabel.setBounds(110,340,DICE_WIDTH,DICE_HEIGHT);
		opLabel.setFont(new Font("serif",Font.BOLD,170));
		titleLabel.setFont(new Font("serif",Font.BOLD,fontSize));
		titleLabel.setBounds(0,0,SCREEN_WIDTH,TITLE_HEIGHT);
		questionLabel.setFont(new Font("serif",Font.BOLD,50));
		questionLabel.setBounds(0,150,SCREEN_WIDTH,QUESTION_HEIGHT);
		questionLabel.setText("<html>"
				+ "이 문제는 주사위 뒷면<br><br>"
				+ "숫자를 계산하는 문제입니다."
				+ "</html>");
		answerLabel.setFont(new Font("serif",Font.BOLD,bigFontSize));
		answerLabel.setBounds(0,570,SCREEN_WIDTH-60,ANSWER_HEIGHT);
		bar.setBounds(50,560,800,20);
		bar.setBackground(Color.black);
		for(int i = 0; i < numb.length; i++) {
			jlp.add(numb[i],new Integer(0));
			jlp.add(eachNumb[i],new Integer(1));
		}
		mainPanel.add(opLabel);
		mainPanel.add(titleLabel);
		mainPanel.add(questionLabel);
		mainPanel.add(answerLabel);
		mainPanel.add(bar);
		add(mainPanel);
		setVisible(true);
		
	}
	public void pressEnterKey() { //Press enter key! Then you can play this game!
		if(enterKey == 1){
			questionLabel.setText("");
			for(int i = 0; i < dice.length; i++) {
				eachNumb[i].setText("");
			}
			answerLabel.setText("정답은?");
			opRand = (int)(Math.random()*op.length);
			int f1 = (int)(Math.random()*dice.length);
			int f2 = (int)(Math.random()*dice.length);
			int f3 = (int)(Math.random()*dice.length);
			int s1 = (int)(Math.random()*dice.length);
			int s2 = (int)(Math.random()*dice.length);
			int s3 = (int)(Math.random()*dice.length);
			int[] randRand = {f1,f2,f3,s1,s2,s3};
			for(int i = 0; i < dice.length; i++) {
				numb[i].setIcon(new ImageIcon(dice[randRand[i]]));
				diceNum[i] = Integer.parseInt(getBackNumberOfDice(dice[randRand[i]])); 
			}
			if(opRand==0) {
				opLabel.setText(op[0]);
			}else if(opRand==1) {
				opLabel.setText(op[1]);
			}else {
				opLabel.setText(op[2]);
			}
			firstNum = Integer.parseInt(getBackNumberOfDice(dice[f1])+getBackNumberOfDice(dice[f2])+getBackNumberOfDice(dice[f3]));
			secondNum = Integer.parseInt(getBackNumberOfDice(dice[s1])+getBackNumberOfDice(dice[s2])+getBackNumberOfDice(dice[s3]));
		}else {
			if(opRand==0) { //arithmetic operation index management.  0 : plus, 1 : subtract, 2 : multiply곱하기
				for(int i = 0; i < dice.length; i++) {
					eachNumb[i].setText(diceNum[i]+"");
				}
				answerLabel.setText(form.format(firstNum+secondNum)+"");
			}else if(opRand==1) { //arithmetic operation index management.  0 : plus, 1 : subtract, 2 : multiply
				for(int i = 0; i < dice.length; i++) {
					eachNumb[i].setText(diceNum[i]+"");
				}
				answerLabel.setText(form.format(firstNum-secondNum)+"");
			}else { //arithmetic operation index management.  0 : plus, 1 : subtract, 2 : multiply
				for(int i = 0; i < dice.length; i++) {
					eachNumb[i].setText(diceNum[i]+"");
				}
				answerLabel.setText(form.format(firstNum*secondNum)+"");
			}
		}
	}
	private void good() {
		System.out.println("메소드가 실행됐어요. ㅋㅋ");
	}
	
	public String getBackNumberOfDice(Image dice) { //This method return String value on the back of dice number.
			String i = "";
			if(dice.equals(this.dice[0])) {
				i = "6";
			}else if(dice.equals(this.dice[1])) {
				i = "5";
			}else if(dice.equals(this.dice[2])) {
				i = "4";
			}else if(dice.equals(this.dice[3])) {
				i = "3";
			}else if(dice.equals(this.dice[4])) {
				i = "2";
			}else if(dice.equals(this.dice[5])) {
				i = "1";
			}
		return i;
	}
	
	public void setDiceImage() { //get dice image from temp folder and include dice Image file dice[] variation. 
		File dir = new File("./temp");
		File[] files = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("dice");
				}
			});
		if(files.length==0) {
			System.out.println("폴더에 파일이 없습니다.");
		}else {
		for(int i = 0; i < dice.length; i++) {
			dice[i] = new ImageIcon(files[i].getPath()).getImage().getScaledInstance(DICE_WIDTH,DICE_HEIGHT,Image.SCALE_SMOOTH); //modify image size and include in dice[] variation.
			System.out.println(files[i].getName());
		}
		}
	}
}