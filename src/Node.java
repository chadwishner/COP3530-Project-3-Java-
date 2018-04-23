public class Node {
	char character;
	int frequency;
	Node leftChild = null;
	Node rightChild = null;
	
	Node(char elem, int freq){ 
		character = elem; frequency = freq; 
	}
	
	Node(int freq, Node leftChild, Node rightChild){
		frequency = freq;
		leftChild = leftChild;
		rightChild = rightChild;
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
