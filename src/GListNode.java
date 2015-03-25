// Ruijia Wang(1360765)
// 01/12/15
// Project 1 Phase B
// CSE332 BA 
// TA: GILLETTE, MATTHEW LEE

// This is a node class for GListStack. 

public class GListNode<T> {
	T data;
	GListNode<T> next;
	GListNode<T> prev;
	 
	public GListNode(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
}
