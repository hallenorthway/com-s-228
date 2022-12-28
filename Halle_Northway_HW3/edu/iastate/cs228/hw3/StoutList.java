package edu.iastate.cs228.hw3;

import java.util.AbstractCollection;
import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes that store
 * multiple items per node. Rules for adding and removing elements ensure that
 * each node (except possibly the last one) is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {

	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	/**
	 * Constructs an empty list with the default node size.
	 */
	public StoutList() {
		this(DEFAULT_NODESIZE);
	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize number of elements that may be stored in each node, must be
	 *                 an even number
	 */
	public StoutList(int nodeSize) {
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public StoutList(Node head, Node tail, int nodeSize, int size) {
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	/**
	 * Size of the StoutList.
	 * 
	 * @return size number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds item to end of the StoutList.
	 * 
	 * @param item item to be added into list
	 * @return true if item is added, false otherwise
	 */
	@Override
	public boolean add(E item) {
		if (item == null) {
			throw new NullPointerException();
		}

		// if list is empty, create new node
		if (head.next == tail) {
			Node n = new Node();

			head.next = n;
			n.previous = head;

			n.next = tail;
			tail.previous = n;

			n.addItem(item);
			size++;
		// or if most recent node is full, create new node
		} else if (tail.previous.count == nodeSize) {
			Node n = new Node();

			tail.previous.next = n;
			n.previous = tail.previous;

			n.next = tail;
			tail.previous = n;

			n.addItem(item);
			size++;
		// otherwise, add element into node
		} else {
			tail.previous.addItem(item);
			size++;
		}

		return true;
	}

	/**
	 * Adds item to a specific position in StoutList.
	 * 
	 * @param pos position for item to be added at
	 * @param item item to be added into list
	 */
	@Override
	public void add(int pos, E item) {
		if (item == null) {
			throw new NullPointerException();
		}

		// if list is empty, create new node
		if (head.next == tail) {
			Node n = new Node();

			this.head.next = n;
			n.previous = this.head;

			n.next = this.tail;
			this.tail.previous = n;

			n.addItem(item);
			size++;
		// otherwise...
		} else {
			Node target = find(pos).node;
			int offset = find(pos).offset;
			
			// if offset is zero...
			if (offset == 0 && target.previous != this.head) {
				// and target node has a predecessor with fewer than nodeSize elements, put item
				// in target nodes predecessor
				if (target.previous != null) {
					if (target.previous.count < nodeSize && target.previous != this.head) {
						Node current = target.previous;
						current.addItem(item);
						size++;
					}
				// otherwise, if target node is tail and its predecessor has nodeSize elements, create a
				// new node and put item at offset zero
				} else if (target == tail && target.previous.count == nodeSize) {
					Node n = new Node();
	
					tail.previous.next = n;
					n.previous = tail.previous;
	
					n.next = tail;
					tail.previous = n;
	
					n = add(target, 0, item).node;
					size++;
				}
			// otherwise, if there is space in the target node, put item in at offset zero,
			// shifting elements as necessary
			} else if (target.count < nodeSize) {
				target.addItem(offset, item);
				size++;
			// otherwise, perform a split operation
			} else {
				// create a new node
				Node n = new Node();
	
				n.next = target.next;
				target.next.previous = n;
	
				target.next = n;
				n.previous = target;
	
				// add last two items into new node
				n.addItem(target.data[(nodeSize / 2)]);
				n.addItem(target.data[(nodeSize / 2) + 1]);

				// remove last two items from target node
				target.removeItem((nodeSize / 2));
				target.removeItem((nodeSize / 2) + 1);
	
				// if offset <= nodeSize / 2, put item in target node at offset
				if (offset <= nodeSize / 2) {
					target.addItem(offset, item);
					size++;
				// if offset > nodeSize / 2, put item in new node at offset (offset - nodeSize / 2)
				} else if (offset > nodeSize / 2) {
					n.addItem(offset - (nodeSize / 2), item);
					size++;
				}
			}
		}
	}

	/**
	 * Removes item at a specific position in StoutList.
	 * 
	 * @param pos position for item to be removed form
	 * @return E item removed from list
	 */
	@Override
	public E remove(int pos) {
		Node target = find(pos).node;
		int offset = find(pos).offset;
		E removed = target.data[offset];

		// if target node is last node and has only one element, remove it
		if (target.next == tail && target.count == 1) {
			tail.previous = target.previous;
			target.previous.next = tail;
			--size;
		// otherwise, if target node is the last node or has more than nodeSize / 2
		// elements, remove item from target node and shift as necessary
		} else if (target.next == tail || target.count > nodeSize / 2) {
			target.removeItem(offset);
			--size;
		// otherwise, perform a merge operation
		} else {
			// create successor node
			Node n = target.next;

			// if successor node has more than nodeSize / 2 elements, move first
			// element from successor node into target node (mini-merge)
			if (n.count > nodeSize / 2) {
				target.removeItem(offset);
				target.addItem(n.data[0]);
				n.removeItem(0);
				size--;
			// if successor node has nodeSize / 2 or fewer elements, move all elements
			// from successor node into target node and delete successor
			} else if (n.count <= nodeSize / 2) {
				target.removeItem(offset);
				
				for (int i = 0; i <= n.count - 1; i++) {
					target.addItem(n.data[i]);
				}
				
				n.previous.next = n.next;
				n.next.previous = n.previous;
				--size;
			}
		}
		return removed;
	}

	/**
	 * Comparator used by insertionSort() method.
	 */
	class InsertionComparator<E extends Comparable<E>> implements Comparator<E> {
		@Override
		public int compare(E o1, E o2) {
			// TODO Auto-generated method stub
			return o1.compareTo(o2);
		}
	}

	/**
	 * Sort all elements in the stout list in the NON-DECREASING order. You may do
	 * the following. Traverse the list and copy its elements into an array,
	 * deleting every visited node along the way. Then, sort the array by calling
	 * the insertionSort() method. (Note that sorting efficiency is not a concern
	 * for this project.) Finally, copy all elements from the array back to the
	 * stout list, creating new nodes for storage. After sorting, all nodes but
	 * (possibly) the last one must be full of elements.
	 * 
	 * Comparator<E> must have been implemented for calling insertionSort().
	 */
	public void sort() {
		int i = 0;
		Node current = head.next;
		E[] list = (E[]) new Comparable[size];
		
		// while the next node is available, iterate through each node and their data
		while (current.next != null) {
			for (int j = 0; j < nodeSize; j++) {
				list[i] = current.data[j];
				i++;
			}
			current = current.next;
		}
		// delete list
		head.next = tail;
		tail.previous = head;
		
		// call insertion sort
		insertionSort(list, new InsertionComparator());
		
		// copy all elements from list back to stout list
		for (int j = 0; j < list.length; j++) {
			this.add(list[j]);
		}
	}

	/**
	 * Sort all elements in the stout list in the NON-INCREASING order. Call the
	 * bubbleSort() method. After sorting, all but (possibly) the last nodes must be
	 * filled with elements.
	 * 
	 * Comparable<? super E> must be implemented for calling bubbleSort().
	 */
	public void sortReverse() {
		int i = 0;
		Node current = head.next;
		E[] list = (E[]) new Comparable[size];
		
		// while the next node is available, iterate through each node and their data
		while (current.next != null) {
			for (int j = 0; j < nodeSize; j++) {
				list[i] = current.data[j];
				i++;
			}
			current = current.next;
		}
		// delete list
		head.next = tail;
		tail.previous = head;
		
		// call bubble sort
		bubbleSort(list);
		
		// copy all elements from list back to stout list
		for (int j = 0; j < list.length; j++) {
			this.add(list[j]);
		}
		
	}

	@Override
	public Iterator<E> iterator() {
		return new StoutIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new StoutListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new StoutListIterator(index);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes.
	 */
	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes and the position of the iterator.
	 *
	 * @param iter an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail) {
			sb.append('(');
			E data = current.data[0];
			if (data == null) {
				sb.append("-");
			} else {
				if (position == count) {
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < nodeSize; ++i) {
				sb.append(", ");
				data = current.data[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements in an
	 * array. Empty slots are null.
	 */
	private class Node {
		/**
		 * Array of actual data elements.
		 */
		// Unchecked warning unavoidable.
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the number of
		 * elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available offset. Precondition: count
		 * < nodeSize
		 * 
		 * @param item element to be added
		 */
		void addItem(E item) {
			if (count >= nodeSize) {
				return;
			}
			data[count++] = item;
		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements to the
		 * right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset array index at which to put the new element
		 * @param item   element to be added
		 */
		void addItem(int offset, E item) {
			if (count >= nodeSize) {
				return;
			}
			for (int i = count - 1; i >= offset; --i) {
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;
		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting elements
		 * left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset) {
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i) {
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}
	
	/**
	 * Helper class that represents a node and offset.
	 */
	private class NodeInfo {
		public Node node;
		public int offset;
		public E item;

		public NodeInfo(Node targetNode, int offset) {
			this.node = targetNode;
			this.offset = offset;
		}

		public NodeInfo(Node targetNode, int offset, E item) {
			this.node = targetNode;
			this.offset = offset;
			this.item = item;
		}
	}

	/**
	 * Finds the node and offset the position is located at.
	 * 
	 * @param pos position item is at
	 * @return NodeInfo contains target node and offset pos is at
	 */
	private NodeInfo find(int pos) {
		Node target = null;
		Node current = head.next;
		int prevCount = 0;
		int currCount = current.count;
		int offset = 0;
		
		while (target == null) {
			// edge case: at first node
			if (currCount == pos && current.next == tail) {
				target = current;
				offset = pos - prevCount;
			}
			// first case: pos is within node
			else if (prevCount - 1 < pos && pos <= currCount - 1) {
				target = current;
				offset = pos - prevCount;
			// second case: pos is not within node
			} else {
				prevCount += current.count;
				current = current.next;
				currCount += current.count;
			}
		}
		
		NodeInfo node = new NodeInfo(target, offset);
		return node;
	}

	/**
	 * Adds item at the given node and offset.
	 * 
	 * @param node node item is at
	 * @param offset offset item is at
	 * @param item item to be added
	 * @return NodeInfo contains new item at given node and offset
	 */
	private NodeInfo add(Node node, int offset, E item) {
		node.data[offset] = item;
		NodeInfo n = new NodeInfo(node, offset, item);

		return n;
	}

	/**
	 * A singly linked iterator to help fully develop
	 * doubly linked iterator for StoutList.
	 */
	public class StoutIterator implements Iterator<E> {

		// direction for remove() and set()
		private static final int BEHIND = -1;
		private static final int AHEAD = 1;
		private static final int NONE = 0;

		private Node cursor;
		private int direction;
		private int nodeCount;
		private int offset;
		private int position;

		public StoutIterator() {
			cursor = StoutList.this.head.next;
		}

		@Override
		public boolean hasNext() {
			return position <= size;
		}

		@Override
		public E next() {
			if (!(hasNext())) {
				throw new NoSuchElementException();
			}

			E data = cursor.data[offset];

			// if at last element within node, go to next node
			if (offset == nodeSize - 1) {
				offset = 0;
				nodeCount++;
				cursor = cursor.next;

			} else {
				offset++;
			}

			position++;

			direction = BEHIND;

			return data;
		}
	}
	
	/**
	 * Doubly linked list iterator for StoutList.
	 */
	private class StoutListIterator implements ListIterator<E> {
		// direction for remove() and set()
		private static final int BEHIND = -1;
		private static final int AHEAD = 1;
		private static final int NONE = 0;

		private Node current;
		private int direction;
		private int offset;
		private int position;

		/**
		 * Default constructor
		 */
		public StoutListIterator() {
			this(0);
		}

		/**
		 * Constructor finds node at a given position.
		 * 
		 * @param pos
		 */
		public StoutListIterator(int pos) {
			if (pos < 0 || pos > size * nodeSize) {
				throw new IndexOutOfBoundsException();
			}
			
			if (pos == 0) {
				current = head.next;
			} else {
				current = find(pos).node;
				offset = find(pos).offset;
			}
			position = pos;
			direction = NONE;
		}

		/**
		 * Returns next index.
		 * 
		 * @return index of element after the cursor, size() if at the end
		 */
		@Override
		public int nextIndex() {
			return position;
		}

		/**
		 * Returns previous index.
		 * 
		 * @return index of element before the cursor, -1 if at the beginning
		 */
		@Override
		public int previousIndex() {
			return position - 1;
		}

		/**
		 * Checks if there is a next element for iterator to access.
		 * 
		 * @return true if iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			//return current.next != tail || current.data[offset] != null;
			return position <= size;
		}

		/**
		 * Checks if there is a previous element for iterator to access.
		 * 
		 * @return true if iteration has more elements
		 */
		@Override
		public boolean hasPrevious() {
			return position > 0;
		}

		/**
		 * Accesses the next available element.
		 * 
		 * @return next element in the list and advance cursor forward
		 */
		@Override
		public E next() {
			if (!(hasNext())) {
				throw new NoSuchElementException();
			}

			E data = current.data[offset];

			// if at last index in node, go to first index in next node
			if (offset == nodeSize - 1) {
				offset = 0;
				current = current.next;
				// else, go to the next index in the nodes array
			} else {
				offset++;
			}

			position++;

			direction = BEHIND;

			return data;
		}

		/**
		 * Accesses the previous available element.
		 * 
		 * @return element before the cursor and moves cursor backward
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}

			E data;

			// if at first index in node, go to last index in previous node
			if (offset - 1 < 0) {
				offset = nodeSize - 1;
				current = current.previous;
				data = current.data[offset];
				// else, go to the previous index in the nodes array
			} else {
				data = current.data[offset - 1];
				offset--;
			}

			position--;

			direction = AHEAD;

			return data;
		}

		/**
		 * Removes the last element returned by next() or previous() and
		 * can only be called once after these methods.
		 */
		@Override
		public void remove() {
			if (direction == NONE) {
				throw new IllegalStateException();
				
			} else if (direction == AHEAD) {
				StoutList.this.remove(position);
				
			} else if (direction == BEHIND){
				StoutList.this.remove(position - 1);
				position--;
				
				if (position < 0) {
					position = 0;
				}
			}
			
			direction = NONE;
		}

		/**
		 * Adds an element at the position of the iterator.
		 * 
		 * @param item item to be added
		 */
		@Override
		public void add(E item) {
			// throw exception if element is null
			if (item == null) {
				throw new NullPointerException();
			}
			
			StoutList.this.add(position, item);
			if (current == head) {
				current = current.next;
			}
			
			position++;
			direction = NONE;
		}

		/**
		 * Replaces the element at the iterators current position.
		 * 
		 * @param item item to be replacing element
		 */
		@Override
		public void set(E item) {
			if (direction == NONE) {
				throw new IllegalStateException();
			}

			if (direction == AHEAD) {
				current.data[offset] = item;
			} else {
				// if setting the previous index is beyond boundaries of the data
				// array, go to previous node
				if (offset - 1 < 0) {
					current.previous.data[nodeSize - 1] = item;
				} else {
					current.data[offset - 1] = item;
				}
			}
		}
	}

	/**
	 * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING
	 * order.
	 * 
	 * @param arr  array storing elements from the list
	 * @param comp comparator used in sorting
	 */
	private void insertionSort(E[] arr, Comparator<? super E> comp) {
		int length = arr.length;
		
		for (int i = 1; i < length; i++) {
			E temp = arr[i];
			int j = i - 1;
			
			while (j >= 0 && comp.compare(arr[j], temp) > 0) {
				arr[j + 1] = arr[j];
				--j;
			}
			
			arr[j + 1] = temp;
		}
	}

	/**
	 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
	 * description of bubble sort please refer to Section 6.1 in the project
	 * description. You must use the compareTo() method from an implementation of
	 * the Comparable interface by the class E or ? super E.
	 * 
	 * @param arr array holding elements from the list
	 */
	private void bubbleSort(E[] arr) {
		int i;
		int j;
		int length = arr.length;
		
		for (i = 0; i < length - 1; i++) {
			for (j = 0; j < length - i - 1; j++) {
				if ((arr[j].compareTo(arr[j + 1]) < 0)) {
					E temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
}