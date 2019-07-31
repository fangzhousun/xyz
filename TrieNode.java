public static class TrieNode { 
    TrieNode[] children = new TrieNode[52]; 
    boolean isLeaf; // means this letter is last letter of a word.
    public TrieNode() { 
        isLeaf = false; 
        for (int i = 0; i< 52; i++) {
            children[i] = null;
        }
    } 
} 