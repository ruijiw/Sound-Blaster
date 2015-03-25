// Ruijia Wang(1360765)
// 01/11/15
// Project 1 Phase A
// CSE332 BA 
// TA: GILLETTE, MATTHEW LEE
//
// This class implements DStack interface. It uses array to push, pop, peek data just like stack.

import java.util.EmptyStackException;

public class ArrayStack implements DStack {
	private double[] array;
	private int size;
	public static final int INITIAL = 10;
	
	public ArrayStack(){
		array = new double[INITIAL];
		size = -1;
	}
	
	// checks whether the array is empty
	public boolean isEmpty() {
		return size == -1;
	}

	// adds an item to the end of the array and resizes whenever the array becomes full
	public void push(double d) {
		if (size + 1 == array.length) {
			resize();
		}
		size++;
		array[size] = d;
	}

	// returns and removes the last item of the array 
	public double pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			double value = array[size];
			size--;
			return value;
		}
	}

	// returns the last item of the array
	public double peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return array[size];
		}
	}
	
	// resizes to use an array twice as large
	private void resize() {
		double[] array2 = new double[(size + 1) * 2];
		for (int i = 0; i < array.length; i++) {
			array2[i] = array[i];
		}
		array = array2;
	}
}
