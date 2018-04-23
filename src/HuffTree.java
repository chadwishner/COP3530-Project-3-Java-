public class HuffTree{
		HuffBaseNode root;
		
		HuffTree(char elem, int wght){
			root = new HuffLeaf(elem, wght); 
		}
	  
		HuffTree(HuffBaseNode lft, HuffBaseNode rght, int wght){
			root = new HuffInternalNode(lft, rght, wght);
		}
		
		HuffBaseNode root() {
			return root;
		}
			
		int getWeight(){
			return root.getWeight();
		}
		
		int compareTo(Object t) {
			HuffTree that = (HuffTree)t;
		    
			if (root.getWeight() < that.getWeight()){ 
				return -1;
			} else if (root.getWeight() == that.getWeight()){ 
				return 0;
			} else{ 
				return 1;
			}
		 }
		
		
	}