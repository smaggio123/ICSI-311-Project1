/**
 * 
 * @author Steven Maggio
 * This class represents a node object.
 * @param <E>
 */
public class Node<E> {
	
	private E value; //Stores value of node
	private Node<E> leftNode; //Stores left node pointer
	private Node<E> rightNode; //Stores right node pointer
	
	/**
	 * Sets field values to null.
	 */
	public Node() {
		this.value=null;
		this.leftNode=null;
		this.rightNode=null;
	}
	
	/**
	 * Sets field values to parameter values, null otherwise.
	 * @param val Value of the node
	 */
	public Node(E val) {
		this.value = val;
		this.leftNode = null;
		this.rightNode = null;
	}
	
	/**
	 * Sets field values to parameter values, null otherwise.
	 * @param val Value of the node
	 * @param ln Reference to left node
	 * @param rn Reference to right node
	 */
	public Node(E val, Node<E> ln, Node<E> rn) {
		this.value = val;
		this.leftNode= ln;
		this.rightNode = rn;
	}
	
	/**
	 * Returns value of this node.
	 * @return value The value of the node
	 */
	public E getValue() {
		return this.value;
	}
	
	/**
	 * Sets value of node.
	 * @param val The value of the node
	 */
	public void setValue(E val) {
		this.value=val;
	}
	
	/**
	 * Gets pointer to the left node.
	 * @return leftNode Reference to the left node
	 */
	public Node<E> getLeftNode(){
		return this.leftNode;
	}
	
	/**
	 * Sets the left node pointer.
	 * @param ln reference to the left node
	 */
	public void setLeftNode(Node<E> ln) {
		this.leftNode = ln;
	}
	
	/**
	 * Gets pointer to the right node. 
	 * @return rightNode
	 */
	public Node<E> getRightNode(){
		return this.rightNode;
	}
	 /**
	  * Sets the right node pointer.
	  * @param rn Reference to the right node
	  */
	public void setRightNode(Node<E> rn) {
		this.rightNode=rn;
	}
	/**
	 * Checks that two nodes have equal values and pointer values
	 * @param other The other node being compared to
	 * @return true if equal, false otherwise
	 */
	public boolean equals(Node<E> other) {
		boolean sameValue=false;
		boolean sameRight=false;
		boolean sameLeft=false;
		//if both objects are nodes
		if(other.getClass().getSimpleName().equalsIgnoreCase(this.getClass().getSimpleName())) {
			if(this.getValue().getClass().getSimpleName().equals("String")) {
				//compares the values if they are string nodes
				sameValue= this.getValue().equals(other.getValue());
				
			}
			else {
				//compare values
				sameValue= this.getValue()==other.getValue();
				}
			if(sameValue==true) {
				//determinging if the right nodes are equivalent
				if(this.getRightNode()==null&&other.getRightNode()==null) {
					sameRight=true;
				}
				else if(this.getRightNode()==null) {
					return false;
				}
				else {
					sameRight=this.getRightNode().compareValues(other.getRightNode());					
				}
				//determinging if the left nodes are equivalent
				if(this.getLeftNode()==null&&other.getLeftNode()==null) {
					sameLeft=true;
				}
				else if(this.getLeftNode()==null) {
					return false;
				}
				else {
					sameLeft=this.getLeftNode().compareValues(other.getLeftNode());					
				}
				
			}
			//If the values, right nodes, and left nodes are equivalent, then the nodes are equal
			if(sameValue&&sameLeft&&sameRight) {
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}
	}
	
	/**
	 * Compares the value of the nodes
	 * @param other
	 * @return true if values are equal, false otherwise
	 */
	public boolean compareValues(Node<E> other) {
		if(this.getValue().getClass().getSimpleName().equals("String")) {
			//compares the values if they are string nodes
			return this.getValue().equals(other.getValue());
			
		}
		else {
			//compare values
			return this.getValue()==other.getValue();
			}
	}
	
	/**
	 * Overrides the toString function. Returns the value of the node in the form of a string.
	 * @return str The value of the node in the form of a string
	 */
	public String toString() {
		String str=""+this.value;
		return str;
	}
	
}
