import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HuffmanEncoder implements HuffmanCoding {

	//instantiate global hash map
	HashMap<Character, String> hashMap = new HashMap<Character, String>();
	
	/**	freqArray
	 * 	takes in an input file and returns an int[] of the frequencies of every character in the input file
	 * 	O(n) where n is the length of the input file
	 * @param File inputFile
	 * @return int[] of frequencies
	 * @throws FileNotFoundException
	 */
	public int[] freqArray(File inputFile) throws FileNotFoundException{
		
		//instantiate array to store frequencies
		int [] charFreqArray = new int[256];
		
		//create character int to know what index to go to
		int characterInt;	
		
		//create FileReader and BufferedReader
		FileReader fileReader = new FileReader(inputFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
			
		try {
			//traverse through input file, if it returns a -1, then we are at end of the file
			while((characterInt = bufferedReader.read()) != -1){
				//increment index when character is found
				charFreqArray[characterInt]++;
			}

		} catch (IOException error) {
			error.printStackTrace();
		}
		
		try {
			//close BufferedReader
			bufferedReader.close();
		} catch (IOException error1) {
			error1.printStackTrace();
		}
		
		//return character frequency array
		return charFreqArray;
	}
	
	/**	transverse helper
	 * recursive algorithm starts the root node and creates a hashmap of coding for every character
	 * O(n) where n is the number of nodes
	 * @param Node root, String string
	 */
	public void transverseHelper(Node root, String string){
		
		//if the node is a child, then put that character and string into hashMap
		if (root.leftChild == null && root.rightChild == null){
			hashMap.put(root.character, string);
			return;
		}
		
		//if there is a left child, then add '0' to string
		if (root.leftChild != null){
			transverseHelper(root.leftChild, string + "0");
		}
		
		//if there is a right child, then add '1' to string
		if (root.rightChild != null){
			transverseHelper(root.rightChild, string + "1");
		}
		
	}
	
	/**	get frequencies
	 *  takes an input file and creates a formatted string for the character frequencies
	 *  O(n + 256) where n is the length of the input file and 256 is the maximum amount of character types
	 *  @param File input file to transverse through
	 *  @return String formatted string
	 *  @throws FileNotFoundException
	 */
	@Override
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		
		//create return string
		String charFreq = "";
		
		//create array of frequencies
		int [] charFreqArray = freqArray(inputFile);

		//format and add characters and frequency to array (when frequencies are >= 1
		for (int x = 0; x < 256; x++){
			if (charFreqArray[x] != 0){
				charFreq += (char) x + " " + charFreqArray[x] + "\n";  
			}
		}
		
		//return formatted string
		return charFreq;
	}

	/**	build tree
	 * 	takes in an input file and creates a huffman tree for the corresponding characters
	 * 	O(nlogn + m) where n is the number of distinct characters n the input file, and m is the length of the file
	 * 	@param File input file to build the tree off of
	 * 	@return HuffmanTree
	 * 	@throws FileNotFoundException
	 */
	@Override
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		
		//create array for the frequencies of the characters
		int[] charFreqArray = freqArray(inputFile);
		
		//instantiate priority queue
		Queue<Node> queue = new PriorityQueue<Node>();
		
		//go through frequencies array and add a node for frequencies >= 1 to the queue
		for (int x = 0; x < charFreqArray.length; x++){
			
			//create node and add to queue
			Node node = new Node((char) x, charFreqArray[x]);
			if (node.frequency >= 1){
				queue.add(node);
			}
		}
		
		//create 3 temp nodes
		Node nodeToAdd1;
		Node nodeToAdd2;
		Node nodeToAdd3;
		
		//loop until queue has 1 item left
		while (queue.size() > 1){
			
			//pop off the smallest 2 nodes
			nodeToAdd1 = queue.poll();
			nodeToAdd2 = queue.poll();
			
			//create a parent node
			nodeToAdd3 = new Node(nodeToAdd1.frequency + nodeToAdd2.frequency, nodeToAdd1, nodeToAdd2);
			
			//add parent node to queue
			queue.add(nodeToAdd3);
		}
		
		//return huffman tree of last node in queue
		return new HuffTree(queue.peek());
	}

	/** encode file
	 * 	encodes an input file into a bit string
	 * 	O(n +m) where n is the number of characters in the input file and m is the number of distinct characters in the string.
	 * 	@param File input file, huffmanTree to use for mapping
	 * 	@return String bit string of encoded file
	 * 	@throws FileNotFoundException
	 */
	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) throws FileNotFoundException {
		
		//create a hash map for character to code
		hashMap.clear();
		transverseHelper(huffTree.root, "");	
		
		//create return string, character int (to store the int value for the character)
		String returnString = "";
		int characterInt;	
		
		//create FileReader and BufferedReader
		FileReader fileReader = new FileReader(inputFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
			
		//traverse through input file, if it returns a -1, then we are at end of the file
		try {
			while((characterInt = bufferedReader.read()) != -1){
				//add the code to the return string for the corresponding char
				returnString += hashMap.get((char) characterInt);
			}

		} catch (IOException error) {
			error.printStackTrace();
		}
		
		//close bufferedReader
		try {
			bufferedReader.close();
		} catch (IOException error1) {
			error1.printStackTrace();
		}
		
		//return encoded file string
		return returnString;
	}

	/** decode file
	 * 	traverse the huffman string bit by bit and when you get to a leaf, add that character to the decode string
	 * 	O(n) where n is the length of the bit string parameter
	 * 	@param String code to decode, huffmanTree to use as mapping for decode
	 *  @return String conversion of bit string to Ascii values
	 *  @throws Exception
	 */
	@Override
	public String decodeFile(String code, HuffTree huffTree) throws Exception {
		
		//create string to return
		String decodeString = "";
		
		//get node to be the current
		Node node = huffTree.root();
		
		//for each character in String code
		for (char c: code.toCharArray()){
			
			//go left child
			if (c == '0'){
				node = node.getLeftChild();
			
			//got right child
			} else if (c == '1'){
				node = node.getRightChild();
			}
			
			//if leaf, add character to return string, make node go back to root
			if (node.getLeftChild() == null && node.getRightChild() == null){
				decodeString += node.character;
				node = huffTree.root();
			}
		}
		
		//return decoded string
		return decodeString;
	}

	/** traverse huffman tree
	 *  traverses huffman tree and outputs a string formatted for as "character code\n"
	 *  O(n) where n is the number of distinct characters
	 *  @param huffTree
	 *  @return String 
	 *  @throws Exception
	 */
	@Override
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {
		
		//create a hash map for character to code
		hashMap.clear();
		transverseHelper(huffTree.root, "");
		
		//create return string and array for temp storage
		String returnString= "";
		String[] asciiMapping = new String[256];
		
		//go through each character in hashmap and add to array
		for(Character c:hashMap.keySet()){
			asciiMapping[c] = hashMap.get(c);
		}
		
		//go through array and only add non null mappings to return string
		for (int x = 0; x < 256; x++){
			if (asciiMapping[x] != null){
				returnString += (char) x + " " + asciiMapping[x] + "\n";
			}
		}
		
		//return formatted string
		return returnString;
	}
}