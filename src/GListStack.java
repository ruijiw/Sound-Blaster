// Ruijia Wang(1360765)
// 01/12/15
// Project 1 Phase B
// CSE332 BA 
// TA: GILLETTE, MATTHEW LEE
//
// This class implements GStack<T> interface. 
// It uses linked list to push, pop, peek data just like stack.

import java.util.EmptyStackException;

public class GListStack<T> implements GStack<T> {
	private GListNode<T> list;
	private GListNode<T> back;
	
	public GListStack() {
		list = null;
	}
	
	// checks whether the list is empty
	public boolean isEmpty() {
		return list == null;
	}

	// adds and item to the end of the list
	public void push(T d) {
		if (isEmpty()) {
			list = new GListNode<T>(d);
			back = list;
		} else {
			back.next = new GListNode<T>(d);
			back.next.prev = back;
			back = back.next;
		}
	}

	// returns and removes the last item of the list
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			T value = back.data;
			if (back.prev == null) {
				list = null;
				back = list;
			} else {
				back = back.prev;
				back.next = null;
			}
			return value;
		}
	}

	// returns the last item of the list
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return back.data;
		}
	}
}
