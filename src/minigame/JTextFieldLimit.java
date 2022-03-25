package minigame;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class JTextFieldLimit extends PlainDocument {

	private int limit;
	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}
	
	//텍스트 필드를 채우는 메서드 : 오버라이드
	public void insertString(int offset, String str, AttributeSet attr)throws BadLocationException{
		if(str == null)return;
		if(getLength()+str.length() <= limit)super.insertString(offset, str, attr);
	}
	
}
