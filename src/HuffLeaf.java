class HuffLeaf implements HuffBaseNode{
		char element;
		int weight;
		
		HuffLeaf(char elem, int wght){ 
			element = elem; weight = wght; 
		}
		
		char getValue(){
			return element;
		}
		
		public int getWeight(){
			return weight;
		}
		
		public boolean isLeaf(){
			return true;
		}
		
	}
	