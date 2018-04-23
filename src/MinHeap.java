public class MinHeap {
	
	Node array[];
	int last;
	Node treeHead;
		
	public MinHeap(){
		array = new Node[1];
		int last = 0;
		treeHead = array[0];
	}
	
	void bubbUp(){
		Node temp;
		int child = last;
		int parent = (child-1)/2;
		while (child != 0 && array[child].frequency < array[parent].frequency){
			temp = array[parent];
			array[parent] = array[child];
			array[child] = temp;
			child = parent;
		}

	}
	
	void bubbDown(){
		Node temp;
		int parent = 0;
		
		int leftChild = (2 * parent) + 1;
		int rightChild = (2 * parent) + 2;
		int smallestChild = -1;
		
		//add conditions for null pointer exceptions (possible do function)
		while ((array[leftChild] != null || array[rightChild] != null) && ((array[leftChild] != null && (array[parent].frequency > array[leftChild].frequency)) 
				|| (array[rightChild] != null && (array[parent].frequency > array[rightChild].frequency)))){
			
			if (array[leftChild] != null && array[leftChild].frequency < array[rightChild].frequency){
				smallestChild = leftChild;
			} else if (array[rightChild] != null && array[leftChild].frequency > array[rightChild].frequency){
				smallestChild = rightChild;
			}
			
			temp = array[smallestChild];
			array[smallestChild] = array[parent];
			array [parent] = temp;
			parent = smallestChild;
			
			leftChild = (2 * parent) + 1;
			rightChild = (2 * parent) + 2;
		}
	}
		
	void insert(Node node){
		if (last == array.length){
			Node[] tempArray = new Node[array.length*2];
			for (int x = 0; x < array.length; x++){
				tempArray[x] = array[x];
			}
			array = tempArray;
		}
		
		array[last] = node;
		bubbUp();
		last ++;
	}
		
	Node remove(){
		Node temp = treeHead;
		treeHead = array[last];
		bubbDown();
		last --;
		
		return temp;	
	}
	
	void arrayToTree(){
		
	}
}
