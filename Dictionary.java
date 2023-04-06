import java.io.File;
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
                dictionary.insert(word);
            }
            fileInput.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void print() {
       dictionary.printTree();
    }
}
