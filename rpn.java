//	Created by Raymond Ho on March 26th, 2014.
//	This is a reverse polish notation calculator.
//	It works by using a tree and stack data structures.
//	Does not check for errors, assumes user knows what they're doing.
//	Possible operations : +, -, *, /, ^(power).
//	'p' is to peek at the top of the stack
//	'c' is to clear the entire stack
//	CONTROL+D is to break out of the loop & print final answer.

import java.util.Scanner;
import java.util.NoSuchElementException;

public class rpn {
	public static void main (String[] args) {
		//	Create a stack to hold the trees
		Stack stack = new Stack();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String s = in.next();
			//	If s is an operation character..
			if (s.equals("+") || s.equals("-") || s.equals("*") 
				|| s.equals("/") || s.equals("^")) {

				//	Create a Tree of constructor type 1.
				Tree mathTree = new Tree(s.charAt(0), stack.pop(), stack.pop());
				//	Create a Tree of constructor type 2.
				//	Evaluate the first tree and add it onto the stack.
				Tree newTree = new Tree(mathTree.evalTree());
				stack.push(newTree);
			}
			//	If 'p', print the Tree at top of stack.
			else if (s.equals("p")) {
				System.out.println(stack.peek() + " is at the top of the stack.");
			}
			//	If 'c', clear the entire stack.
			else if (s.equals("c")){
				stack.clear();
				System.out.println("Cleared the stack.");
			}
			// Assume it's a number, and push onto stack.
			else {
				Tree newT = new Tree(Double.parseDouble(s));
				stack.push(newT);
			}
		}
		in.close();

		//	Print out the last item on the stack.
		System.out.println(stack.pop() + " is the final answer.");
	}
}

//	A tree for operations and numbers.
class Tree {

	char operation;
	double value;
	Tree left, right;

	//	Constructor type 1.
	public Tree(char operation_, Tree left_, Tree right_) {
		operation = operation_;
		left = left_;
		right = right_;
	}

	//	Constructor type 2.
	public Tree(Double value_) {
		value = value_;
		left = right = null;
	}

	//	Returns the actual value the Tree is holding
	public String toString() {
		return Double.toString(value);
	}

	//	Evaluate tree in left, right, visit order (postorder)
	public double evalTree() {
		if (left == null && right == null)
			throw new NoSuchElementException();
		switch(operation) {
			case '+':
				return right.value + left.value;
			case '-':
				return right.value - left.value;
			case '*':
				return right.value * left.value;
			case '/':
				return right.value / left.value;
			case '^':
				return Math.pow(right.value, left.value);
		}
		return Double.NaN;
	}
}

//	A stack for trees
class Stack {

	private Node top = null;

	private class Node{
		Tree tree;
		Node link;
	}

	// isEmpty checks to see if the stack is empty..
	private boolean isEmpty() {
		return top == null;
	}

	//	Peek returns the top Tree without popping it.
	public Tree peek() {
		if (!isEmpty()) return top.tree;
		return null;
	}

	//	Clear removes every Tree from the stack.
	public void clear() {
		while (!isEmpty()) {
			Tree tmp = pop();
			tmp = null;
		}
	}

	//	Push adds a new Tree to the stack
	public void push(Tree tree) {
		Node newNode = new Node();
		newNode.tree = tree;
		newNode.link = top;
		top = newNode;
	}

	//	Pop returns a Tree and removes it.
	public Tree pop() {
		if (isEmpty()) throw new NoSuchElementException();
		Tree tmpTree = top.tree;
		top.tree = null;
		top = top.link;
		return tmpTree;

	}
}
