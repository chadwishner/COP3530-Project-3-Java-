public class Node implements Comparable<Node> {
	char character;
	int frequency;
	Node leftChild = null;
	Node rightChild = null;
	
	/** constructor for node with no children
	 * 	O(1)
	 * 	@param char element and int frequency
	 */
	Node(char elem, int freq){ 
		character = elem;
		frequency = freq; 
	}
	
	/** constructor for node with children
	 * 	O(1)
	 * 	@param int frequency, NodeleftChild, Node rightChild
	 */
	Node(int freq, Node lftChild, Node rghtChild){
		frequency = freq;
		leftChild = lftChild;
		rightChild = rghtChild;
	}
	
	/** get character
	 * 	O(1)
	 * 	@return char character
	 */
	public char getChar(){
		return character;
	}
	
	/** get frequency
	 * 	O(1)
	 * 	@return int frequency
	 */
	public int getFreq(){
		return frequency;
	}
	
	/** get leftChild 
	 * 	O(1)
	 * 	@return Node leftChild
	 */
	public Node getLeftChild(){
		return leftChild;
	}
	
	/** get rightChild 
	 * 	O(1)
	 * 	@return Node rightChild
	 */
	public Node getRightChild(){
		return rightChild;
	}

	/** toString for formatting print of Node
	 * 	O(1)
	 * 	@return String
	 */
	public String toString(){
		return character + " " + frequency;
	}

	/** compareTo
	 * 	compares to other node and returns 1 if this.frequency > node.frequency, -1 if this.frequency < node.frequency, and 0 if they are equal
	 * 	O(1)
	 * 	@param Node to compare frequency to
	 * 	@return int positive, negative, or zero depending on comparison
	 */
	@Override
	public int compareTo(Node o) {
		if (this.frequency > o.frequency){
			return 1;
		} else if (this.frequency < o.frequency){
			return -1;
		} else {
			return 0;
		}
	}
}
