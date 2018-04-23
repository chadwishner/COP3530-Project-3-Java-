import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree implements HuffmanCoding {

	public static void main(String[] args){
		try{
			File f = new File("testfile.txt");
			HuffmanTree myTree = new HuffmanTree();
			System.out.println(myTree.getFrequencies(f));

		} catch (Exception e){
			System.out.println("error");
		}
	}
	
	Node array[];
	int last;
	Node treeHead;
		
	void bubbUp(){
		Node temp;
		int child = last;
		int parent = (child-1)/2;
		while (child != 0 && array[child].frequency < array[parent].frequency){
			temp = array[parent];
			array[parent] = array[child];
			array[child] = temp;
			child = parent;
		}
	}
	void bubbDown(){
		Node temp;
		int parent = 0;
		
		int leftChild = (2 * parent) + 1;
		int rightChild = (2 * parent) + 2;
		int smallestChild = -1;
		
		//add conditions for null pointer exceptions (possible do function)
		while ((array[leftChild] != null || array[rightChild] != null) && ((array[leftChild] != null && (array[parent].frequency > array[leftChild].frequency)) 
				|| (array[rightChild] != null && (array[parent].frequency > array[rightChild].frequency)))){
			
			if (array[leftChild] != null && array[leftChild].frequency < array[rightChild].frequency){
				smallestChild = leftChild;
			} else if (array[rightChild] != null && array[leftChild].frequency > array[rightChild].frequency){
				smallestChild = rightChild;
			}
			
			temp = array[smallestChild];
			array[smallestChild] = array[parent];
			array [parent] = temp;
			parent = smallestChild;
			
			leftChild = (2 * parent) + 1;
			rightChild = (2 * parent) + 2;
		}
	}
		
	void insert(Node node){
		array[last] = node;
		bubbUp();
		last ++;
	}
		
	Node remove(){
		Node temp = treeHead;
		treeHead = array[last];
		bubbDown();
		last --;
		
		return temp;	
	}
	
	void arrayToTree(){
		
	}
	
	@Override
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		String charFreq = "";
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

		for (int x = 0; x < 255; x++){
			charFreq += (char) x + " " + charFreqArray[x] + "\n";  
		}
		
		// TODO Auto-generated method stub
		return charFreq;
	}

	@Override
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		String charFreq = getFrequencies(inputFile);
		
		array = new Node[255];
		last = 0;
		
		String[] charFreqArray = charFreq.split("\n");
		for (int x = 0; x < charFreqArray.length; x++){
			Node node = new Node(charFreqArray[x].charAt(0), charFreqArray[x].charAt(2));
			if (node.frequency >= 1){
				insert(node);
			}
		}
		
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
