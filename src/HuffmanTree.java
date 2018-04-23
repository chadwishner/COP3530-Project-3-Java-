import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree implements HuffmanCoding {

	//just for testing
	public static void main(String[] args){
		try{
			//File f = new File("testfile.txt");
			HuffmanTree myTree = new HuffmanTree();
			//MinHeap minHeap = myTree.buildTree(f);
			MinHeap minHeap = new MinHeap();
			Node node1 = new Node('a', 1);
			Node node2 = new Node('b', 2);
			Node node3 = new Node('c', 3);
			minHeap.insert(node3);
			minHeap.insert(node2);
			minHeap.insert(node1);

			for(int x = 0; x < minHeap.array.length; x++){
				System.out.println(minHeap.array[x]);
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
