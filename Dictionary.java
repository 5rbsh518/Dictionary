import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.text.html.HTMLDocument.BlockElement;
import javax.xml.catalog.Catalog;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Dictionary {
    AVLTree<String> dictionary;

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        Scanner scanner = new Scanner(System.in);
        int numInput;
        String stringInput;
        String[] similarWords;
        System.out.println("Please selecte one of these methods:\n1.  Load the dictionary from a file\n2.  find a word in the dictionary.\n3.  add words to the dictionary.\n4.  Remove words to the dictionary. \n5.  search for similar words to a word in the dictionary.\n6.  save the updated dictionary as a text file.\n7.  Exit. \n (Enter the digit left to the method to use it)");
        numInput = scanner.nextInt();
        while(numInput != 7){
            switch(numInput){
                case 1:
                    
                    System.out.println("Enter filename> ");
                    stringInput = scanner.next();
                    File f = new File(stringInput);
                    long startTime = System.nanoTime();
                    dictionary.loadFile(f);
                    long endTime = System.nanoTime();
                    System.out.println("dictionary loaded successfully.");
                    long timeElapsed = endTime - startTime;
                    System.out.println(timeElapsed/1000000000);
                    break;
                case 2:
                    System.out.println("check word>  ");
                    stringInput = scanner.next();
                    stringInput = (dictionary.findWord(stringInput)) ? "%s, was found".formatted(stringInput): "%s, wasn't found".formatted(stringInput);
                    System.out.println(stringInput);
                    break;
                case 3:
                    System.out.println("add new word> ");
                    stringInput = scanner.next();
                    try{
                        dictionary.addWord(stringInput);
                        System.out.println("%s added successfully.".formatted(stringInput));
                    }catch(WordAlreadyExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("remove word>  ");
                    stringInput = scanner.next();
                    try{
                        dictionary.deleteWord(stringInput);
                        System.out.println("%s removed successfully.".formatted(stringInput));
                    }catch(WordNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("search for similar words>   ");
                    stringInput = scanner.next();
                    similarWords = dictionary.findSimilar(stringInput);
                    System.out.println(Arrays.toString(similarWords));
                    break;
                case 6:
                    System.out.println("Save Updated Dictionary (Y/N)>   ");
                    stringInput = scanner.next();
                    if(stringInput.equals("Y")){
                        System.out.println("Enter filename> ");
                        stringInput = scanner.next();
                        dictionary.saveDictionary(stringInput);
                        System.out.println("Dictionary saved successfully.");
                        break;
                    }else{
                        System.out.println("Returning");
                        break;
                    }
            }
            System.out.println("Please selecte one of these methods:\n1.  Load the dictionary from a file\n2.  find a word in the dictionary.\n3.  add words to the dictionary.\n4.  Remove words to the dictionary. \n5.  search for similar words to a word in the dictionary.\n6.  save the updated dictionary as a text file.\n7.  Exit. \n (Enter the digit left to the method to use it)");
            numInput = scanner.nextInt();
        }
        scanner.close();
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
        loadFile(f);

    }

    public void loadFile(File f){// overwrite the exisitng dictionary.
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
        Queue<BTNode> queue = new Queue<>();
        BTNode node = dictionary.root;
        queue.enqueue(node); 
        while(! queue.isEmpty()){
           node = queue.dequeue();
           if(this.similarWords(s,(String) node.data)) similarWords += " " + node.data;
           if(node.left != null)
              queue.enqueue(node.left);
           if(node.right != null)
              queue.enqueue(node.right);
        } 
        return similarWords.substring(1).split(" ");
    }

    private boolean similarWords(String s1, String s2) {
        if(s1.equals(s2)){//Inital conditions *These conditions fast up the alogrithim*U 
            return false;
        }
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }
        boolean diff = false;
        String longer = (s1.length() > s2.length()) ? s1.toLowerCase() : s2.toLowerCase();
        String shorter = (s1.length() > s2.length()) ? s2.toLowerCase() : s1.toLowerCase();
        int sizediff = longer.length() - shorter.length();
        for (int i = 0; i < longer.length(); ++i) {
                if (diff){
                    if (longer.charAt(i) != shorter.charAt(i - sizediff)) return false;
                } 
                else{
                    try{
                        if(longer.charAt(i) != shorter.charAt(i)) diff = true;
                    }catch(Exception exception){
                        return true;
                    }
                }
                    
        }
        return true;
    }
    public void saveDictionary(String fileName){
        Queue<BTNode> queue = new Queue<>();
        File f = new File(fileName);
        try(PrintWriter printWriter = new PrintWriter(f);){
            BTNode node = dictionary.root;
            queue.enqueue(node); 
            while(! queue.isEmpty()){
               node = queue.dequeue();
               printWriter.printf("%s\n", node.data);
               if(node.left != null)
                  queue.enqueue(node.left);
               if(node.right != null)
                  queue.enqueue(node.right);
            } 
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
