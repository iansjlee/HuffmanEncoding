package Trees;

import java.util.ArrayList;



public class Heap <T extends Comparable<T>> {

	ArrayList<T> heap;
	//constructor for heap
	public Heap() {
		heap = new ArrayList<T>();
	}

	//adds item to arraylist and calls heapify method	
	public void add(T node) {
		heap.add(node);
		int pos = heap.size()-1;
		heapifyUp(pos);
	}

	// recursive method that checks if the parent is greater than the child and swaps if it is
	public void heapifyUp (int index) {
		if( index == 0 ) {
			return;
		}
		int parent = (index-1)/2;
		if(heap.get(index).compareTo(heap.get(parent)) < 0) {
			T toMove = heap.get(index);
			heap.set(index, heap.get(parent));
			heap.set(parent, toMove);
			heapifyUp(parent);
		}
	}

	// removes and returns first item, makes the last item the first item, then calls heapifyDown
	public T remove() {
		if( heap.size() == 0 ) {
			return null;
		}
		T root = heap.get(0);
		T rem = heap.remove(heap.size()-1);
		if( heap.size() > 0 ) {
			heap.set(0,rem);
			heapifyDown(0);
		}
		return root;

	}

	// recursive method that checks if a child is less than the parent and swaps if it is. 
	// If both children are less than the parent, the lesser child is swapped; if they are equal, the left child will swap

	public void heapifyDown(int i)  
	{
		int left, r, min;		
		left = 2 * i + 1;  			
		r = 2 * i + 2;       		
		if(left < heap.size() && heap.get(left).compareTo(heap.get(i)) == -1)		
			min = left;             	
		else
			min = i;
		if(r < heap.size() && heap.get(r).compareTo(heap.get(min)) == -1)
			min = r;           		
		if(min != i)	 			
		{
			T tmp = heap.get(i);      		
			heap.set(i, heap.get(min));
			heap.set(min, tmp);
			heapifyDown(min); 			
		}
	}


	// checks if arraylist is empty
	public boolean IsEmpty() {
		if(heap.size()==0) {
			return true;
		}
		else {
			return false;
		}

	}
}