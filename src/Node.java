public class Node {
	char character;
	int frequency;
	
	Node(char elem, int freq){ 
		character = elem; frequency = freq; 
	}
	
	char getValue(){
		return character;
	}
	
	public int getWeight(){
		return frequency;
	}

}
