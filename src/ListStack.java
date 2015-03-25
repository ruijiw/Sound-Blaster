// Ruijia Wang(1360765)
// 01/11/15
// Project 1 Phase A
// CSE332 BA 
// TA: GILLETTE, MATTHEW LEE
//
// This class implements DStack interface. 
// It uses linked list to push, pop, peek data just like stack.

import java.util.EmptyStackException;

public class ListStack implements DStack {
	private ListNode list;
	private ListNode back;
	
	public ListStack() {
		list = null;
	}
	
	// checks whether the list is empty
	public boolean isEmpty() {
		return list == null;
	}

	// adds and item to the end of the list
	public void push(double d) {
		if (isEmpty()) {
			list = new ListNode(d);
			back = list;
		} else {
			back.next = new ListNode(d);
			back.next.prev = back;
			back = back.next;
		}
	}

	// returns and removes the last item of the list
	public double pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			double value = back.data;
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
	public double peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return back.data;
		}
	}
}
