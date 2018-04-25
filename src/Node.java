public class Node {
	char character;
	int frequency;
	Node leftChild = null;
	Node rightChild = null;
	
	Node(char elem, int freq){ 
		character = elem; frequency = freq; 
	}
	
	Node(int freq, Node lftChild, Node rghtChild){
		frequency = freq;
		leftChild = lftChild;
		rightChild = rghtChild;
	}
	
	public char getValue(){
		return character;
	}
	
	public int getWeight(){
		return frequency;
	}
	
	public Node getLeftChild(){
		return leftChild;
	}
	
	public Node getRightChild(){
		return rightChild;
	}

	public String toString(){
		return character + " " + frequency;
	}
}
