package ubung_6;

public interface Stack<E> {
	 public void push (E item);
	 public void pop () throws StackUnderflow;
	 public Object top () throws StackUnderflow;
	 public boolean isEmpty ();
	 public void Empty ();
	 public void print();
	 public String toString();
	}