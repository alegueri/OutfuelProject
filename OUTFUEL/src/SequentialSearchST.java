import cas2xb3_Lab9.Queue;
import cas2xb3_Lab9.SequentialSearchST;
import cas2xb3_Lab9.SequentialSearchST.Node;

public class SequentialSearchST<Key extends Comparable<Key>, Value> {

	private Node first; // first node in the linked list
	private int N;
	private class Node { // linked-list node
		Key key;
		Value val;
		Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key))
				return x.val; // search hit
		return null; // search miss
	} // Search for key, return associated

	public void put(Key key, Value val) {
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key)) {
				x.val = val;
				return;
			} // Search hit: update val.
		first = new Node(key, val, first); // Search miss: add new node.
		N++;
	} // Search for key. Update value if found; grow table if new.

	public int size() {
		return N;
	}

	public void delete(Key key) {
		first = delete(first, key);
	}

	private Node delete(Node x, Key key) {
		if (x==null) return null;
		if (x.key.compareTo(key) ==0){
			N--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (Node x = first; x!=null;x=x.next){
			queue.enqueue(x.key);
		}
		return queue;
	}

	public static void main(String[] args) {
		SequentialSearchST<String, Integer> st=new SequentialSearchST<String, Integer>();
		st.put("A", 1);
		st.put("B", 2);
		st.put("C", 3);
		st.delete("B");
		
		for (String s: st.keys()){
			System.out.println(String.format("(%s,%d)", s,st.get(s)));
		}
		System.out.println("Size:" + st.size());
	}

}