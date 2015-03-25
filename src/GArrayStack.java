

import java.util.EmptyStackException;

public class GArrayStack<T> implements GStack<T> {
	private T[] array;
	private int size;
	public static final int INITIAL = 10;
	
	public GArrayStack(){
		array = (T[])new Object[INITIAL];
		size = -1;
	}
	
	// checks whether the array is empty
	public boolean isEmpty() {
		return size == -1;
	}

	// adds an item to the end of the array and resizes whenever the array becomes full
	public void push(T d) {
		if (size + 1 == array.length) {
			resize();
		}
		size++;
		array[size] = d;
	}

	// returns and removes the last item of the array 
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			T value = array[size];
			size--;
			return value;
		}
	}

	// returns the last item of the array
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return array[size];
		}
	}
	
	// resizes to use an array twice as large
	private void resize() {
		T[] array2 = (T[])new Object[(size + 1) * 2];
		for (int i = 0; i < array.length; i++) {
			array2[i] = array[i];
		}
		array = array2;
	}
}
