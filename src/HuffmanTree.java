import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree implements HuffmanCoding {

	interface HuffBaseNode{
		boolean isLeaf();
		int getWeight();
	}
	
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
	
	@Override
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		String charFreq = "";
		int [] charFreqArray = new int[255];
		
		char character;	
		
		FileReader fileReader = new FileReader(inputFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
			
		try {
			while((character = (char) bufferedReader.read()) != -1){
				charFreqArray[character]++;
			}
		} catch (IOException error) {
			error.printStackTrace();
		}
		
		try {
			bufferedReader.close();
		} catch (IOException error1) {
			error1.printStackTrace();
		}

		for (int x = 0; x < 255; x++){
			charFreq += (char) x + " " + x + "\n";  
		}
		
		// TODO Auto-generated method stub
		return charFreq;
	}

	@Override
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeFile(String code, HuffTree huffTree) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
