import java.lang.*;
import java.util.*;
import java.io.File; 
/* Build a trie to store the words in dictionary. */
 
public class Test { 
    // Becuase of case insensitive search, a-z and A-Z are stored for each node.
    private static final int SIZE = 52; 
    public static class TrieNode { 
        TrieNode[] children = new TrieNode[SIZE]; 
        boolean isLeaf; // means this letter is last letter of a word.
        public TrieNode() { 
            isLeaf = false; 
            for (int i = 0; i< SIZE; i++) {
                children[i] = null;
            }
        } 
    } 
       
    public static void insertNode(TrieNode root, String word) { 
        for (int i = 0; i < word.length(); i++) {
            //if there is special character in one word. Don't store this word in trie.
            if (!Character.isLetter(word.charAt(i))) {
                return;
            }
            //get the insert index. 
            int index = Character.isLowerCase(word.charAt(i)) ? word.charAt(i) - 'a' : word.charAt(i) - 'A' + 26; 
            if (root.children[index] == null) {
                root.children[index] = new TrieNode(); 
            }
            root = root.children[index]; 
        } 
        root.isLeaf = true; 
    } 
       
    public static void searchWord(TrieNode root, boolean existLetters[], String str) { 
        if (root.isLeaf == true) { // print out result
            System.out.println(str); 
        } 
        for (int i = 0; i < SIZE; i++) { 
            if (existLetters[i % 26] == true && root.children[i] != null) { 
                char c = i < 26 ? (char)(i + 'a') : (char)(i - 26 + 'A'); 
                searchWord(root.children[i], existLetters, str + c); 
            } 
        } 
    } 
       
    // Prints all words present in dictionary. 
    public static void getWords(char composed[], TrieNode root) { 
        boolean[] existLetters = new boolean[26]; 
        for (int i = 0 ; i < composed.length; i++) {
            existLetters[Character.toLowerCase(composed[i]) - 'a'] = true; 
        }
        String str = ""; 
        for (int i = 0 ; i < SIZE ; i++) { 
            // specific letter exists in the input array and dictionary.
            if (existLetters[i % 26] == true && root.children[i] != null ) { 
                char c = i < 26 ? (char)(i + 'a') : (char)(i - 26 + 'A'); 
                searchWord(root.children[i], existLetters, str + c); 
            } 
        } 
    } 

    public static void main(String args[]) throws Exception { 
        TrieNode root = new TrieNode(); 
        Scanner sc = new Scanner(new File("/usr/share/dict/words"));      
        while (sc.hasNextLine()) {
            insertNode(root, sc.nextLine()); 
        }
        Set<Character> s = new HashSet<Character>();
        Scanner scanner = new Scanner(new File("testcases.txt")); 
        String line = "";     
        if (scanner.hasNextLine()) {
           line = scanner.nextLine();
        }
        char composed[] = line.toCharArray();      
        System.out.println("*******************************************");
        System.out.println("*** Words Composed By these Characters: ***");
        System.out.println("*******************************************");
        getWords(composed, root); 
    } 
} 