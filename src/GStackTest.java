// Ruijia Wang(1360765)
// 01/12/15
// Project 1 Phase B
// CSE332 BA 
// TA: GILLETTE, MATTHEW LEE
//
// This is a test code for GArrayStack and GListStack.

public class GStackTest {
	public static void main(String[] args){
		GStack<Double> gs;
		if (args[0].equals("array")) {
			gs = new GArrayStack<Double>();
		} else {
			gs = new GListStack<Double>();
		}
		double[] test = {1.0, 2.0, 3.0, 4.0, 5.0};
		if (gs.isEmpty() == false) {
			System.err.println("Incorrect empty method");
			return;
		}
		for (double d: test) {
			gs.push(d);
		}
		if (gs.peek() != 5.0) {
			System.err.println("Incorrect peek method");
			return;
		} else if (gs.peek() != 5.0) {
			System.err.println("Incorrect peek method");
			return;
		}
		if (gs.pop() != 5.0) { 
			System.err.println("Incorrect pop method");
			return;
		} else if (gs.pop() != 4.0) {
				System.err.println("Incorrect pop method");
				return;
		}
		System.out.println("Success");
	}
}

