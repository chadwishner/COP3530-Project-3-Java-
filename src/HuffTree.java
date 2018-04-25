public class HuffTree{
	Node root;
	
	/** constructor for HuffTree
	 * 	O(1)
	 * 	@param node
	 */
	HuffTree(Node node){			
		root = node; 
	}
	
	/** get root of HuffTree
	 * 	O(1)
	 *	@return Node root
	 */
	Node root() {
		return root;
	}
	
}