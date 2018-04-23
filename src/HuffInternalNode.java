public class HuffInternalNode implements HuffBaseNode{
		int weight;
		HuffBaseNode left;
		HuffBaseNode right;
		
		HuffInternalNode(HuffBaseNode lft, HuffBaseNode rght, int wght){
			left = lft; 
			right = rght; 
			weight = wght;
		}

		HuffBaseNode left() { 
			return left; 
		}

		HuffBaseNode right() {
			return right; 
		}

		public int getWeight() {
			return weight;
		}

		public boolean isLeaf() {
			return false; 
		}
		
	}