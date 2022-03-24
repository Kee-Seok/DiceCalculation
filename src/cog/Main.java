package cog;

import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) {
//		new Cognition();
		DecimalFormat d = new DecimalFormat("###,###,###.####");
		double a = 5/218d+1300d;
		System.out.println(d.format(a));
		System.out.println(a);
	}
}
