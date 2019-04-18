package Trees;

import java.util.HashMap;
import java.util.Map;

public class Huffman {

	/** this method creates a frequency table hashmap given an input string
	 * 
	 * @param data: the input file converted into a string 
	 * @return freqs: hashmap that is the frequency table 
	 */

	private HashMap<Character,Integer> calcFreqs(String data ){
		HashMap<Character,Integer> freqs = new HashMap<Character,Integer>();
		int length = data.length();
		for(int t= 0; t<length; t++) {

			char str = data.charAt(t);
			if(!freqs.containsKey(str)) {
				freqs.put(str, 1);
			}
			else {
				int a = freqs.remove(str); 
				freqs.put(str,  a+1);
			}
		}
		return freqs;

	}

	/** this method creates nodes that have the character and frequency, then adds them to the heap 
	 * it then goes through the process of building a huffman tree
	 * @param hmap: frequency table hashmap
	 * @return root: returns the root of the huffman tree
	 */
	private HuffNode createHuffTree(HashMap<Character,Integer> hmap ) {
		HuffNode root = null;
		Heap<HuffNode> heap = new Heap<HuffNode>();

		for (Map.Entry<Character, Integer> entry : hmap.entrySet()) {
			Character key = entry.getKey();
			Integer value = entry.getValue();
			String input = Character.toString(key);
			HuffNode node = new HuffNode(input, value);
			heap.add(node);

		}
		while(!heap.IsEmpty()) {
			HuffNode temp = heap.remove();
			if(heap.IsEmpty()) {
				root = temp;
				break;
			}
			HuffNode temp2 = heap.remove();

			String cname = temp.name + temp2.name;
			Integer cfreq = temp.freq + temp2.freq;
			HuffNode combine = new HuffNode(cname,cfreq );
			combine.left = temp;
			combine.right = temp2;
			heap.add(combine);
		}
		System.out.println(root.freq);
		return root;
	}


	/** this method creates the dictionary hashmap and calls preOrder method
	 * @param root: root of the huffman tree so we can access every single node
	 * @return dict: hashmap with all the characters and their binary representation
	 */
	private HashMap<String,String> createHuffDictionary(HuffNode root){
		HashMap<String, String> dict = new HashMap<String,String>();
		dict = preOrder(root, "" , dict);
		return dict;
	}

	/** this recursive method preorder traverses the huffman tree given the root, and adds every leave to the dictionary hashmap with the appropriate binary (as a String) representation
	 * @param root: node that will be checked
	 * @param i: binary representation up to where that node is in the tree
	 * @param hmap: dictionary hashmap
	 * @return hmap
	 */
	private HashMap <String, String> preOrder(HuffNode root, String i, HashMap <String, String> hmap){
		if(root==null){
			return hmap;
		}
		if(root.left == null && root.right ==null) {
			hmap.put(root.name, i);
		}
		preOrder(root.left,i + "0", hmap);
		preOrder(root.right, i + "1", hmap);
		return hmap;
	}

	// this method calls the other three methods and returns the final dictionary
	public HashMap<String,String> compress(String data) {

		HashMap<Character,Integer> freqs = calcFreqs(data);
		HuffNode root = createHuffTree(freqs);
		HashMap<String, String> dict = createHuffDictionary(root);

		return dict;
	}



	// Node class with necessary attributes and compareTo method
	private class HuffNode implements Comparable<HuffNode> {
		private HuffNode left;
		private HuffNode right;
		private String name;
		private Integer freq;

		public HuffNode(String str, int freq) {
			this.name = str;
			this.freq = freq;
		}
		public int compareTo(HuffNode other) {
			if(this.freq < other.freq) {
				return -1;
			}
			if(this.freq > other.freq) {
				return 1;
			}
			return 0;
		}
	}
}


