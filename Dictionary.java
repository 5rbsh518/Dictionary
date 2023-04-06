import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.TypeDescriptor;
import java.util.Scanner;

public class Dictionary {
    AVLTree<String> dictionary;

    public static void main(String[] args) {
        File f = new File("Dictionary.txt");
        Dictionary dictionary = new Dictionary(f);
        dictionary.print();
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
        try{
            Scanner fileInput = new Scanner(f);
            while(fileInput.hasNext()){
                String word = fileInput.next();
                try{
                    addWord(word);
                }catch(WordAlreadyExistsException exception){
                    continue;
                }
                
            }
            fileInput.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void print() {
       dictionary.levelOrderTraversal();
    }

    public void addWord(String s) throws WordAlreadyExistsException{
        try{
            dictionary.insert(s);
        }catch(Exception e){
            throw new WordAlreadyExistsException("The word %s is already inside the dictionary".formatted(s));
        }
    }
    public boolean findWord(String s){
        return dictionary.search(s);
    }
    public void deleteWord(String s) throws WordNotFoundException{
        try{
            dictionary.deleteAVL(s);
        }catch(Exception e){
            throw new WordNotFoundException("The word %s is not found in the dictionary".formatted(s));
        }
        
    }
}
