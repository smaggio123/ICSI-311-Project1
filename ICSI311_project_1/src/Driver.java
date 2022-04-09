import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * This class tests the methods found in the Node and ExpressionTree class
 * @author Steven Maggio
 *
 */
public class Driver {

	/**
	 * Calls start method
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		start();
	}
	
	/**
	 * Reads the test cases from a file, initializes an ExpressionTree, and prints the tree
	 * @throws FileNotFoundException
	 */
	public static void start() throws FileNotFoundException {
		String path = "src/testCases.txt";
		File file = new File(path);
	    Scanner scan = new Scanner(file);
	    while(scan.hasNextLine()!=false) {
	    	String expression = scan.nextLine();
	    	ExpressionTree tree=new ExpressionTree(expression);
	    	System.out.println(tree);
	    	getRepeats(tree);
	    	System.out.println();
	    }
	    compareTreesTest();
	    
	    scan.close();
	    System.out.println("Finished Execution");
	}
	
	/**
	 * Uses the compare values method in the node class to count the amount of each operator found in each expression.
	 * @param tree The tree in which the operators are being counted
	 */
	public static void getRepeats(ExpressionTree tree) {
		tree.setInorder();
		LinkedList<Node<String>> list = tree.getList();
		//Initializing nodes to have operators as value
		Node<String> multNode = new Node<String>("*");
		Node<String> divNode = new Node<String>("/");
		Node<String> addNode = new Node<String>("+");
		Node<String> minNode = new Node<String>("-");

		//These variables are used to keep track of the operator count
		int multCount=0;
		int divCount=0;
		int addCount=0;
		int minCount=0;
		//traversing each node in the list and determining which operator it is (if it is an operator).
		for(Node<String> n:list) {
			if(n.compareValues(multNode)) {
				multCount++;
			}
			else if(n.compareValues(divNode)) {
				divCount++;
			}
			else if(n.compareValues(addNode)) {
				addCount++;
			}
			else if(n.compareValues(minNode)) {
				minCount++;
			}
		}
		
		System.out.println("The following is calculated using the compareValues method in the node class:");
		System.out.println("number of * nodes found: "+multCount);
		System.out.println("Number of / nodes found: "+divCount);
		System.out.println("number of + nodes found: "+addCount);
		System.out.println("Number of - nodes found: "+minCount);
		
	}
	
	/**
	 * Makes the trees that are to be compared. Then calls the comparison method.
	 */
	public static void compareTreesTest() {
		ExpressionTree comparingThisTree= new ExpressionTree("(234+432*5+4-(432*5/4/2+4)-(4435+663/54)+(45443)+4+(54-4))");
	    ExpressionTree comparingToTree = new ExpressionTree("(234+432*5+4-(432*5/4/2+4)-(4435+663/54)+(45443)+4+(54-4))");
	    compareTrees(comparingThisTree,comparingToTree);
	    comparingToTree = new ExpressionTree("(344*54+34+(94+39+3)/(34+2+4+5)/(32))");
	    compareTrees(comparingThisTree,comparingToTree);
	}
	/**
	 * Compares 2 trees. Prints whether they are equal or not.
	 * @param thisTree The first tree being compared
	 * @param otherTree The second tree being compared
	 */
	public static void compareTrees(ExpressionTree thisTree, ExpressionTree otherTree) {
		System.out.println();
		System.out.println("Comparing the following two trees:");
		System.out.println(thisTree);
		System.out.println(otherTree);
		
		if(thisTree.equals(otherTree)) {
			System.out.println("These two trees are equal");
		}
		else {
			System.out.println("These two trees are not equal");
		}
	}
	

}
