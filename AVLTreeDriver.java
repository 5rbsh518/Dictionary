import java.util.Scanner;

public class AVLTreeDriver {
	public static void main(String[] args) {
		 System.out.println("\nSINGLE LEFT ROTATION EXAMPLE: ");
       System.out.println("Insert 45 in the following AVL tree:");
		 AVLTree<Integer> avlTree2 = new AVLTree<Integer>();
       Integer[]  array1 = new Integer []{30, 5, 35, 32, 40};
       for(int i = 0; i < array1.length ; i++)
			 avlTree2.insertAVL(array1[i]);
       avlTree2.printTree();
       System.out.println("\nInsertion result: ");
       avlTree2.insertAVL(45); 
       System.out.println(); 
       avlTree2.printTree();
		 //========================= TASK01========================================= 
	System.out.println("DOUBLE RIGHT-LEFT ROTATION EXAMPLE: ");
        System.out.println("Insert 15 in the following AVL tree:");
        AVLTree<Integer> avlTree3 = new AVLTree<Integer>();
        Integer[]  array2 = new Integer []{5,2,7,1,4,6,9,3,16};
        for(int i = 0; i < array2.length ; i++)
                          avlTree3.insertAVL(array2[i]);
        avlTree3.printTree();
        avlTree3.insertAVL(15);
        avlTree3.printTree();   
        //================================ TASK02 ================================  		  
	System.out.println("CASE 3A DELETION EXAMPLE: ");
        System.out.println("Delete 1 in the following AVL tree:");
        AVLTree<Integer> avlTree4 = new AVLTree<Integer>();
        Integer[]  array3 = new Integer []{7,2,15,1,3,10,17,5,9,13,18,11};
        for(int i = 0; i < array3.length ; i++)
                          avlTree4.insertAVL(array3[i]);
        avlTree4.printTree();
        avlTree4.deleteAVL(1);
        avlTree4.printTree();
	
	
	
        //=============================== TASK03 =====================================  
        System.out.println("Delete 1 in the following AVL tree:");
        AVLTree<Integer> avlTree5 = new AVLTree<Integer>();
        Integer[]  array4 = new Integer []{32,26,54,14,30,44,27};
        for(int i = 0; i < array4.length ; i++)
                          avlTree5.insertAVL(array4[i]);
        System.out.println("Insert the required maximum key in the following AVL tree:");
        avlTree5.printTree();
        System.out.println("Insertion result: ");
        avlTree5.insertAVL(43);//The maximum key is : 43
        //================================= TASK04 =====================================
        System.out.println("Delete 1 in the following AVL tree:");
        AVLTree<Integer> avlTree6 = new AVLTree<Integer>();
        for(int i = 0; i < array4.length ; i++)
                          avlTree6.insertAVL(array4[i]);
        System.out.println("Insert the required minimum key in the following AVL tree:");
        avlTree6.printTree(); 
        System.out.println("Insertion result: ");
        avlTree6.insertAVL(28);//The minimum key is : 28
	}
}