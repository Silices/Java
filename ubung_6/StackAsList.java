package ubung_6;

public class StackAsList implements Stack {
	private Node myStack;
	private class Node {
			
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
		public Node() {
			data = null;
			next = null;
		}
		Object data;
		Node next;
		public String toString() {
			return data.toString();
		}
	}
	
	

	public StackAsList() {
		myStack=null;
	}

	@Override
	public void push(Object item) {
				
		Node temp = new Node(item, myStack);
		myStack = temp;		
	}

	@Override
	public void pop() throws StackUnderflow {
		if(!isEmpty())
		myStack = myStack.next;
	}

	@Override
	public Object top() throws StackUnderflow {
		// TODO Auto-generated method stub
		return myStack.data;
	}

	@Override
	public boolean isEmpty() {
		if(myStack == null) {
			return true;
		}
		else
			return false;
	}

	@Override
	public void Empty() {
		myStack = null;
	}

	@Override
	public void print() {
		System.out.println(this.toString());
	}
	@Override
	public String toString() {
		String S_myString = "";
		Node temp = new Node();
		temp = myStack;
		while (isEmpty()) {
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
			S_myString += temp.data + "\r\n";
		}
		return S_myString;
	}
}