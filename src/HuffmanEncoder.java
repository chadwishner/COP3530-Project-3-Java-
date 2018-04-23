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
			File f = new File("testfile.txt");
			HuffmanEncoder myTree = new HuffmanEncoder();
			HuffTree huffTree = myTree.buildTree(f);
			
			System.out.println(myTree.traverseHuffmanTree(huffTree));
			
//			//testing adding
//			Node test1 = new Node('a',2);
//			Node test2 = new Node ('b',2);
//			Node test3 = new Node('c',2);
//			MinHeap testingHeap = new MinHeap();
//			testingHeap.insert(test1);
//			testingHeap.insert(test2);
//			testingHeap.insert(test3);
//
//			while (testingHeap.last > 0){
//				System.out.println(testingHeap.remove());
//			}
			

		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	HashMap<Character, String> hashMap = new HashMap<Character, String>();
	
	public int[] freqArray(File inputFile) throws FileNotFoundException{
		int [] charFreqArray = new int[255];
		
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

		for (int x = 0; x < 255; x++){
			charFreq += (char) x + " " + charFreqArray[x] + "\n";  
		}
		
		// TODO Auto-generated method stub
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
		
			System.out.println(temp1);
			System.out.println(temp2);
			temp3 = new Node(temp1.frequency + temp2.frequency, temp1, temp2);
		
			minHeap.insert(temp3);
		}
		
		Node temp4 = minHeap.remove();
		
		return  new HuffTree(temp4);
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
		String testing = "";
		
		for(Character c:hashMap.keySet()){
			testing += c + " " + hashMap.get(c) + "\n";
		}
		
		// TODO Auto-generated method stub
		return returnString;
	}

}
