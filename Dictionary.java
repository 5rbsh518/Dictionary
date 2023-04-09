import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;

public class Dictionary {
    AVLTree<String> dictionary;

    public static void main(String[] args) {
        File f = new File("Dictionary.txt");
        Dictionary dictionary = new Dictionary(f);
        try{
            dictionary.addWord("printer");
            dictionary.addWord("painter");
            dictionary.addWord("pointer");
            dictionary.addWord("punter");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        String[] similar = dictionary.findSimilar("puinter");
        System.out.println(Arrays.toString(similar));
        
    }

    public Dictionary(String s) {
        dictionary = new AVLTree<>();
        dictionary.insert(s);
    }

    public Dictionary() {
        dictionary = new AVLTree<>();
    }

    public Dictionary(File f) {
        dictionary = new AVLTree<>();
        try {
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNext()) {
                String word = fileInput.next();
                try {
                    addWord(word);
                } catch (WordAlreadyExistsException exception) {
                    continue;
                }

            }
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        dictionary.levelOrderTraversal();
    }

    public void addWord(String s) throws WordAlreadyExistsException {
        try {
            dictionary.insertAVL(s);
        } catch (Exception e) {
            throw new WordAlreadyExistsException("The word %s is already inside the dictionary".formatted(s));
        }
    }

    public boolean findWord(String s) {
        return dictionary.search(s);
    }

    public void deleteWord(String s) throws WordNotFoundException {
        try {
            dictionary.deleteAVL(s);
        } catch (Exception e) {
            throw new WordNotFoundException("The word %s is not found in the dictionary".formatted(s));
        }

    }

    public String[] findSimilar(String s) {
        String similarWords = "";
        Queue<BTNode> queue = new LinkedList<BTNode>();
        BTNode node = dictionary.root;
        queue.add(node); 
        while(! queue.isEmpty()){
           node = queue.poll();
           if(this.similarWords(s,(String) node.data)) similarWords += " " + node.data;
           if(node.left != null)
              queue.add(node.left);
           if(node.right != null)
              queue.add(node.right);
        } 
        return similarWords.split(" ");
    }

    private boolean similarWords(String s1, String s2) {
        boolean diff = false;
        if(s1.equals(s2)){
            return false;
        }
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); ++i) {
                if (diff) {
                    if (s1.charAt(i) != s2.charAt(i))
                        return false;
                } else {
                    if (s1.charAt(i) != s2.charAt(i))
                        diff = true;
                }
            }
        } else {
            String longer = (s1.length() > s2.length()) ? s1 : s2;
            String shorter = (s1.length() > s2.length()) ? s2 : s1;
            for (int i = 0; i < shorter.length(); ++i) {
                if (diff) {
                    if(longer.charAt(i) != shorter.charAt(i-1)) return false;
                } 
                else {
                    if(longer.charAt(i) != shorter.charAt(i)) diff = true;
                }

            }
        }
        return true;
    }
    public void saveDictionary(){

    }
}
