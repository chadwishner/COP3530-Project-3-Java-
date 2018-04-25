import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HuffmanEncoder implements HuffmanCoding {

//just for testing
	public static void main(String[] args){
		try{
/* MY TESTING CASE */
			
//			File f = new File("aaabbc.txt");
//			HuffmanEncoder myTree = new HuffmanEncoder();
//			HuffTree huffTree = myTree.buildTree(f);
//			
//			System.out.println(huffTree.root().character + " " + huffTree.root().frequency + "\n");
//			System.out.println(huffTree.root().getLeftChild().character + " " + huffTree.root().getLeftChild().frequency + "\n");
//			System.out.println(huffTree.root().getRightChild().getLeftChild().character + " " + huffTree.root().getRightChild().getLeftChild().frequency + "\n");
//			System.out.println(huffTree.root().getRightChild().getRightChild().character + " " + huffTree.root().getRightChild().getRightChild().frequency + "\n");
//			
//			System.out.println(myTree.getFrequencies(f)); //DOES THIS NEED TO ONLY PRINT OUT NON ZEROS IN ASCII ORDER?
//			System.out.println(myTree.encodeFile(f, huffTree));
//			System.out.println(myTree.decodeFile("000111110", huffTree));			
//			System.out.println(myTree.traverseHuffmanTree(huffTree));

/*ZACH'S TESTING CASE*/
			File f = new File("randTest.txt");
			HuffmanEncoder myTree = new HuffmanEncoder();
			HuffTree huffTree = myTree.buildTree(f);
			
			//System.out.println(huffTree.root().character + " " + huffTree.root().frequency + "\n");
			//System.out.println(huffTree.root().getLeftChild().character + " " + huffTree.root().getLeftChild().frequency + "\n");
			//System.out.println(huffTree.root().getRightChild().getLeftChild().character + " " + huffTree.root().getRightChild().getLeftChild().frequency + "\n");
			//System.out.println(huffTree.root().getRightChild().getRightChild().character + " " + huffTree.root().getRightChild().getRightChild().frequency + "\n");
			
			System.out.println(myTree.getFrequencies(f)); //DOES THIS NEED TO ONLY PRINT OUT NON ZEROS IN ASCII ORDER?
			System.out.println(myTree.encodeFile(f, huffTree));
			System.out.println(myTree.decodeFile(myTree.encodeFile(f, huffTree), huffTree));			
			System.out.println(myTree.traverseHuffmanTree(huffTree));
			
/*HEAP TESTING*/
//			Node test1 = new Node('a',3);
//			Node test2 = new Node ('b',2);
//			Node test3 = new Node('c',1);
//			MinHeap testingHeap = new MinHeap();
//			testingHeap.insert(test1);
//			testingHeap.insert(test2);
//			testingHeap.insert(test3);
			
//
//			testingHeap.remove();
//			while (testingHeap.last > 0){
//				System.out.println(testingHeap.remove());
//			}
			

		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	HashMap<Character, String> hashMap = new HashMap<Character, String>();
	
	public int[] freqArray(File inputFile) throws FileNotFoundException{
		int [] charFreqArray = new int[256];
		
		int characterInt;	
		FileReader fileReader = new FileReader(inputFile);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
			
		try {
			while((characterInt = bufferedReader.read()) != -1){
				charFreqArray[characterInt]++;
			}

		} catch (IOException error) {
			error.printStackTrace();
		}
		
		try {
			bufferedReader.close();
		} catch (IOException error1) {
			error1.printStackTrace();
		}
		
		return charFreqArray;
	}
	
	public void transverseHelper(Node root, String string){
		if (root.leftChild == null && root.rightChild == null){
			hashMap.put(root.character, string);
			return;
		}
		
		if (root.leftChild != null){
			transverseHelper(root.leftChild, string + "0");
		}
		
		if (root.rightChild != null){
			transverseHelper(root.rightChild, string + "1");
		}
		
	}
	
	@Override
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		String charFreq = "";
		
		int [] charFreqArray = freqArray(inputFile);

		for (int x = 0; x < 256; x++){
			if (charFreqArray[x] != 0){
				charFreq += (char) x + " " + charFreqArray[x] + "\n";  
			}
		}
		
		return charFreq;
	}

	@Override
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		String charFreq = getFrequencies(inputFile);
		MinHeap minHeap = new MinHeap();
		
		int[] charFreqArray = freqArray(inputFile);
		
		for (int x = 0; x < charFreqArray.length; x++){
			Node node = new Node((char) x, charFreqArray[x]);
			if (node.frequency >= 1){
				minHeap.insert(node);
			}
		}

		Node temp1;
		Node temp2;
		Node temp3;
		
		while (minHeap.last > 1){
			temp1 = minHeap.remove();
			temp2 = minHeap.remove();
						
			temp3 = new Node(temp1.frequency + temp2.frequency, temp1, temp2);
		
			minHeap.insert(temp3);
		}
		
		Node temp4 = minHeap.remove();
		
		return  new HuffTree(temp4);
	}

	
//CHECK WITH ZACH
	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) throws FileNotFoundException {
		hashMap.clear();
		transverseHelper(huffTree.root, "");	

//testing
//		System.out.println("test " + hashMap.get((char) 97));
		
		String returnString = "";
		int characterInt;	
		FileReader fileReader = new FileReader(inputFile);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
			
		try {
			while((characterInt = bufferedReader.read()) != -1){
				returnString += hashMap.get((char) characterInt);
			}

		} catch (IOException error) {
			error.printStackTrace();
		}
		
		try {
			bufferedReader.close();
		} catch (IOException error1) {
			error1.printStackTrace();
		}
		
		return returnString;
	}

//CHECK WITH ZACH
	@Override
	public String decodeFile(String code, HuffTree huffTree) throws Exception {
		String decodeString = "";
		Node node = huffTree.root();
		
		for (char c: code.toCharArray()){
			if (c == '0'){
				node = node.getLeftChild();
			} else if (c == '1'){
				node = node.getRightChild();
			}
			if (node.getLeftChild() == null && node.getRightChild() == null){
				decodeString += node.character;
				node = huffTree.root();
			}
		}
		
		
		//traverse the huffman string bit by bit and when you get to a leaf, add that character to the decode string
		
//		if (node.getLeftChild() == null && node.getRightChild() == null){
//			return decodeString += node.character;
//		} else if (node.getLeftChild() != null && code.charAt(0) == '0'){
//			return decodeFile(code.substring(1), new HuffTree(node.getLeftChild()));		
//		} else if (node.getRightChild() != null && code.charAt(0) == '1'){
//			return decodeFile(code.substring(1), new HuffTree(node.getRightChild()));		
//		} 
		
		return decodeString;
	}

	@Override
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {
		hashMap.clear();
		transverseHelper(huffTree.root, "");
		
		String returnString= "";
		String[] asciiMapping = new String[256];
		
		for(Character c:hashMap.keySet()){
			asciiMapping[c] = hashMap.get(c);
		}
		
		for (int x = 0; x < 256; x++){
			if (asciiMapping[x] != null){
				returnString += (char) x + " " + asciiMapping[x] + "\n";
			}
		}
		
//testing
//		String testing = "";
//		
//		for(Character c:hashMap.keySet()){
//			testing += c + " " + hashMap.get(c) + "\n";
//		}
//testing
		
		return returnString;
	}

}
