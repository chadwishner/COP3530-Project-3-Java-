public class MinHeap {
	
	Node array[];
	int last;
	Node treeHead;
		
	public MinHeap(){
		array = new Node[1];
		int last = 0;
	}
	
	Node getLeftChild(int parent){
		if (parent < array.length && array[(2 * parent) + 1] != null){
			return array[(2 * parent) + 1];
		} else {
			return null;
		}
	}
	
	Node getRightChild(int parent){
		if (parent < array.length && array[(2 * parent) + 2] != null){
			return array[(2 * parent) + 2];
		} else {
			return null;
		}
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

//ASK ZACH		
		Node smallestChild = null;
		int smallestIndex = -1;
		
		while(parent < last){
			if (getLeftChild(parent) != null && getRightChild(parent) != null){
				if (getLeftChild(parent).frequency <= getRightChild(parent).frequency){
					smallestChild = getLeftChild(parent);
					smallestIndex = (parent* 2) + 1;
				} else if (getLeftChild(parent).frequency > getRightChild(parent).frequency){
					smallestChild = getRightChild(parent);
					smallestIndex = (parent* 2) + 2;
				}
			} else if (getLeftChild(parent) != null && getRightChild(parent) == null){
				smallestChild = getLeftChild(parent);
				smallestIndex = (parent* 2) + 1;
			} else if (getLeftChild(parent) == null && getRightChild(parent) != null){
				smallestChild = getRightChild(parent);
				smallestIndex = (parent* 2) + 2;
			} else {
				break;
			}
			
			if (smallestChild.frequency < array[parent].frequency){
				temp = smallestChild;
				smallestChild = array[parent];
				array[parent] = temp;
				parent = smallestIndex;
		
			} else {
				break;
			}
			
		}
		
//		int leftChild = (2 * parent) + 1;
//		int rightChild = (2 * parent) + 2;
//		int smallestChild = -1;
//		
//		//add conditions for null pointer exceptions (possible do function)
//		while(parent < last){
//			if (array[leftChild] != null && array[rightChild] != null){
//				if (array[leftChild].frequency <= array[rightChild].frequency){
//					smallestChild = leftChild;
//				} else if (array[leftChild].frequency > array[rightChild].frequency){
//					smallestChild = rightChild;
//				}
//			} else if (array[leftChild] != null && array[rightChild] == null){
//				smallestChild = leftChild;
//			} else if (array[leftChild] == null && array[rightChild] != null){
//				smallestChild = rightChild;
//			} else {
//				break;
//			}
//			
//			if (array[smallestChild].frequency < array[parent].frequency){
//				temp = array[smallestChild];
//				array[smallestChild] = array[parent];
//				array [parent] = temp;
//				parent = smallestChild;
//			
//				leftChild = (2 * parent) + 1;
//				rightChild = (2 * parent) + 2;
//			} else {
//				break;
//			}
//			
//		}
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
		Node temp = array[0];
		array[0] = array[last-1];
		array[last-1] = null;
		bubbDown();
		last --;
		
		return temp;	
	}
}
