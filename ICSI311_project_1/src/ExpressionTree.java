import java.util.LinkedList;
import java.util.Stack;
/**
 * This class represents an ExpressionTree object
 * @author Steven Maggio
 *
 */
public class ExpressionTree {

	private Node<String> root; //Stores the root of the tree
	
	private String infix; //Stores the infix expression
	
	private String postfix=""; //Stores the postfix expression
	
	LinkedList<Node<String>> listOfNodes; //Stores the traversed nodes in the expression tree
	
	
	/**
	 * Sets field values if there are parameter values, null otherwise.
	 */
	public ExpressionTree() {
		root = null;
		infix=null;
		listOfNodes=new LinkedList<Node<String>>();
	}
	
	/**
	 * Sets field values if there are parameter values, null otherwise. Also converts the infix expression to postfix.
	 * @param expression The infix expression
	 */
	public ExpressionTree(String expression) {
		root=null;
		infix=expression;
		listOfNodes=new LinkedList<Node<String>>();
		//Gets rid of all white space to properly add white space in spaceOutInfix()
		//If user accidently spaces the numbers of a double digit number, this fixes that issue
		//Also makes the infix look nicer when printed
		infix = infix.replaceAll("\\s", "");
		//Adds white spaces near operators in the string.
		//Adding white space will allow the string to split accurately so double digit numbers can be identified
		spaceOutInfix();
		//converts infix to postfix
		infixToPostfix();
		//Converts postfix expression into expressionTree
		convertPostfixToTree();
	}
	
	/**
	 * Puts a space before operators ( '(' and ')' included) to easily pick out if there are double digit numbers.
	 */
	public void spaceOutInfix() {
		//This string holds the updated infix expression to prevent the complications of editing the original expression
		String tempStr="";
		for(int i=0;i<infix.length();i++) {
			//The part of the string being checked
			String partOfStringBeingChecked=infix.substring(i,i+1);
			//If the piece of the string is an operator
			if(partOfStringBeingChecked.equals("+")||partOfStringBeingChecked.equals("-")||partOfStringBeingChecked.equals("*")||partOfStringBeingChecked.equals("/")||partOfStringBeingChecked.equals("(")||partOfStringBeingChecked.equals(")")) {
				//Adds space which varies depending on the position
				if(i==0) {
					//Only adds space on right side since it is the beginning of the string.
					tempStr+=partOfStringBeingChecked+" ";
				}
				else if(i==infix.length()-1) {
					//Only adds space on left side since it is the end of the string.
					tempStr+=" "+partOfStringBeingChecked;
				}
				else {
					//Adds space on both sides since it is in the middle of the string.
					tempStr+=" "+partOfStringBeingChecked+" ";
				}
			}
			else {
				//Appends the spaced character to the string.
				tempStr+=partOfStringBeingChecked;
			}
		}
		//Sets the infix expression to the spaced out infix expression.
		infix = tempStr;
	}

	/**
	 * Converts infix to postfix
	 */
	public void infixToPostfix() {
		Stack<String> stack = new Stack<String>();
		//Splitting the string into an array makes it easier to traverse since double digit numbers will be split together
		String [] strArray = infix.split(" ");
		
		for(int i=0;i<strArray.length;i++) {
			//The piece of string being checked
			String pieceOfString=strArray[i];
			//Determining whether piece of string is a number
			try {
				//Converting the string to an int tests if the string is an int.
				//If parseInt() throws NumberFormatException, then it is not a number.
				Integer.parseInt(pieceOfString);
				postfix+=" "+pieceOfString;
			}
			//If NumberFormatException is thrown, piece of string is not a number.
			catch(NumberFormatException e) {
				if(pieceOfString.equals("(")) {
					stack.push(pieceOfString);
				}
				else if(pieceOfString.equals(")")) {
					while(stack.size()>0&&!(stack.peek().equals("("))){
						postfix+=" "+stack.pop();
					}
					//pops (
					stack.pop();
				}
				else if(pieceOfString.equals("+")||pieceOfString.equals("-")) {
					//Testing the precedence in the stack
					if(stack.size()>0&&(stack.peek().equals("+")||stack.peek().equals("-")||stack.peek().equals("*")||stack.peek().equals("/"))) {
						while(stack.size()>0&&!(stack.peek().equals("("))){
							postfix+=" "+stack.pop();
						}
					}
					stack.push(pieceOfString);
				}
				else if(pieceOfString.equals("*")||pieceOfString.equals("/")) {
					//Testing the precedence in the stack
					if(stack.size()>0&&(stack.peek().equals("*")||stack.peek().equals("/"))) {
						postfix+=" "+stack.pop();
						
					}
					stack.push(pieceOfString);
				}
			}
		}
		//getting whatever is left on the stack
		while(stack.size()>0&&!(stack.peek().equals("("))) {
			postfix+=" "+stack.pop();
		}
	}
	
	
	
	/**
	 * Converts the postfix to the tree
	 */
	public void convertPostfixToTree() {
		Stack<Node<String>> stack = new Stack<Node<String>>();
		//Splitting the string into an array makes it easier to traverse since double digit numbers will be split together
		String [] strArray = postfix.split(" ");
		for(int i=0;i<strArray.length;i++) {
			//The piece of the string being checked
			String pieceOfString=strArray[i];
			//if piece of string is a number
			try {
				//If piece of string is a number, makes node and pushes it to stack
				Integer.parseInt(pieceOfString);
				Node<String> tempNode = new Node<String>(pieceOfString);
				stack.push(tempNode);
			}
			catch(NumberFormatException e) {
				//If piece of string is an operator
				if(pieceOfString.equals("+")||pieceOfString.equals("-")||pieceOfString.equals("*")||pieceOfString.equals("/")) {
					Node<String> tempNode = new Node<String>(pieceOfString);
					if(stack.size()>=2) {
						tempNode.setRightNode(stack.pop());
						tempNode.setLeftNode(stack.pop());
						stack.push(tempNode);
					}
				}
			}
		}
		//pops off any elements left on the stack
		if(stack.size()==1) {
			root=stack.pop();
		}
	}
	
	/**
	 * Sets the traversal type to be preorder.
	*/
	public void setPreorder() {
	//clears linked list
	if(listOfNodes!=null&&listOfNodes.size()>0)this.listOfNodes.clear();
	//calls preorder method
	this.preorder(root);
	}
	
	/**
	   * Sets the traversal type to be inorder.
	   */
	  public void setInorder() {
		  //clears linked list
		  if(listOfNodes!=null&&listOfNodes.size()>0) this.listOfNodes.clear();
		  //calls inorder method
	      this.inorder(root);
	  }
	
	
	/**
	 * Sets the traversal type to be postorder.
	*/
	public void setPostorder() {
		//clears linked list
		if(listOfNodes!=null&&listOfNodes.size()>0) this.listOfNodes.clear();
		//calls postorder method
		this.postorder(root);
	}

	/**
	 * Traverses through the tree in preorder.
	 * @param treeNode Node in tree
	*/
	public void preorder(Node<String> treeNode) {
		//traversing through linked list
		if(treeNode != null){
			//adds to linked list
			this.listOfNodes.add(treeNode);
			//calls preorder recursively with left node
			preorder(treeNode.getLeftNode());
			//calls preorder recursively with right node
			preorder(treeNode.getRightNode());
			}
		}
	/**
	 * Traverses through the tree in inorder.
	 * @param treeNode Node in tree
	*/
	public void inorder(Node<String> treeNode) {
		//traversing through linked list
		if(treeNode != null){
			//calls inorder recursively with left node  
			inorder(treeNode.getLeftNode());
			//adds to the linked list
			this.listOfNodes.add(treeNode);
			//calls inorder recursively with right node
			inorder(treeNode.getRightNode());
			}
		}
	/**
	 * Traverses through the tree in postorder.
	 * @param treeNode Node in tree
	*/
	public void postorder(Node<String> treeNode) {
		//traversing through tree
		if(treeNode != null){
			//calls post order recursively with left node  
			postorder(treeNode.getLeftNode());
			//calls post order recursively with right node
			postorder(treeNode.getRightNode());
			//adds to linked list
			this.listOfNodes.add(treeNode);
			}
		}
	
	/**
	 * Returns the linked list of nodes collected during traversal
	 * @return listOfNode The linked list of nodes collected during traversal
	 */
	public LinkedList<Node<String>> getList(){
		  return this.listOfNodes;
	  }
	
	/**
	 * Converts the linked list to a string
	 * @param listToConvert The linked list containing the nodes from traversal.
	 * @return tempStr The converted list
	 */
	public String convertListToString(LinkedList<Node<String>> listToConvert) {
		//The string that will hold the converted list
		String tempStr="";
		for(int i=0;i<listOfNodes.size();i++) {
			//Concatenating white space and the value of node based on where it is in the list
			if(i==0) {
				tempStr+=listOfNodes.get(i);
			}
			else {
				tempStr+=" "+listOfNodes.get(i);
			}
		}
		return tempStr;
	}
	
	/**
	 * Returns the root node of the tree
	 * @return root The root node of the tree
	 */
	public Node<String> getRoot(){
		return root;
	}
	
	/**
	 * Traverses through both trees inorder and then compares their linked lists to see if they are equivalent
	 * @param other The other ExpressionTree being compared.
	 * @return true if the linked lists are the same, false otherwise
	 */
	public boolean equals(ExpressionTree other) {
		this.setInorder();
		other.setInorder();
		//Get the linked list of nodes for this tree
		LinkedList<Node<String>> thisList = this.getList();
		//Get the linked list of nodes for the other tree
		LinkedList<Node<String>> otherList = other.getList();
		if(thisList.size()==otherList.size()) {
			for(int i=0;i<thisList.size();i++) {
				if((thisList.get(i).equals(otherList.get(i)))) {
				}
				else {
					return false;
					
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Creates a string containing the class name, infix expression, inorder, preorder, postorder and returns the string
	 * @return str The string containing all of the info
	 */
	public String toString() {
		String str="Class: "+this.getClass().getSimpleName();
		str+="\ninfix: "+infix;
		setInorder();
		str+="\ninorder: "+convertListToString(getList());
		setPreorder();
		str+="\npreorder:"+convertListToString(getList());
		setPostorder();
		str+="\npostOrder:"+convertListToString(getList());
		return str;
	}
	
	
}
