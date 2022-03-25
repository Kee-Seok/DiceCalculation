package minigame;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Model {

	public static ArrayList<String> num = new ArrayList<>();
	public static String[] a = new String[4];
	public static int strike, ball, out;
	public static String result;
	public static boolean isExistSameNum = false;
	public static boolean isEmptyNum = false;
	public static JOptionPane op = new JOptionPane();
	//ArrayList에 숫자 1~9까지 넣어준다.
	public static void setNum() {
		for(int i = 1; i <= 9; i++) {
			num.add(i+"");
		}
	}
	//a,b,c,d에 숫자를 하나씩 대입해준다.
	public static void substituteNum() {
		Collections.shuffle(num);
		a[0] = num.get(0);
		a[1] = num.get(1);
		a[2] = num.get(2);
		a[3] = num.get(3);
	}
	//ArrayList를 모두 비운다.
	public static void clearNum() {
		num.removeAll(num);
	}
	public static void init() {
		clearNum();
		setNum();
		substituteNum();
	}
	public static void clearTf() {
		for(int i = 0; i < BaseballGame.tf.length; i++) {
		BaseballGame.tf[i].setText("");
		}
	}
	//텍스트필드에 입력된 값과 초기 세팅된 번호와 비교 후 결과 출력
	public static void comparison(JTextField[] tf) {
//		substituteNum();
		String b = tf[0].getText()+tf[1].getText()+tf[2].getText()+tf[3].getText();
		if(b.length()<4) { //텍스트필드에 숫자가 비었을때 체크
			isEmptyNum = true;
			JOptionPane.showMessageDialog(Main.baseball, "숫자를 모두 입력하세요.");
			return;
		}
	for(int i = 0; i < 3; i++) {
		for(int j = i+1; j <= 3; j++) {
			if(tf[i].getText().equals(tf[j].getText())){
				isExistSameNum = true;
				System.out.println(isExistSameNum);
				JOptionPane.showMessageDialog(Main.baseball, "중복된 숫자가 있습니다.");
				return;
			}
		}
	}
		for(int j = 0; j < a.length; j++) {
		for(int i = 0; i < BaseballGame.tf.length; i++) {
		if(a[j].equals(BaseballGame.tf[i].getText())&&j==i) {
			strike++;
		}else if(a[j].equals(BaseballGame.tf[i].getText())&&j!=i) {
			ball++;
		}
		}
		}
      out = 4 - strike - ball;
   	  result = strike + "Strike "+ball+"Ball "+out+"Out";
   	  BaseballGame.gameCount++;
   	  if(strike == 4) { //정답이면 출력될 요소들.
   		  for(int i = 0; i < BaseballGame.str.length; i++) {
   			  BaseballGame.str[i] = BaseballGame.tf[i].getText();
   		  }
   		  Main.baseball.repaint();
   		  BaseballGame.ta.append("정답입니다!");
   		  JOptionPane.showMessageDialog(Main.baseball, BaseballGame.gameCount+"회차 정답입니다!");
   		  BaseballGame.ta.setText("");
   		  for(int i = 0; i < BaseballGame.str.length; i++) {
   			  BaseballGame.str[i] = "?";
   			  BaseballGame.tf[i].setText("");
   		  }
   		BaseballGame.gameCount = 0;
   		Main.baseball.repaint();
     	  strike = 0;
       	  ball = 0;
       	  out = 0;
       	  Model.init();
  		for(int i = 0; i < Model.a.length; i++) {
  			System.out.println(Model.a[i]);
  			}
   		return;
   	  }
   	  JOptionPane.showMessageDialog(Main.baseball, result+" "+tf[0].getText()+
   			tf[1].getText()+tf[2].getText()+tf[3].getText());
   	  BaseballGame.ta.append(" "+BaseballGame.gameCount+"회차 : "+result+" ("+tf[0].getText()+
     			tf[1].getText()+tf[2].getText()+tf[3].getText()+")\n");
   	  strike = 0;
   	  ball = 0;
   	  out = 0;
	}
	
}
