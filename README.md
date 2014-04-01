RPN-calc
========

Reverse Polish Notation Calculator

This is a simple rpn calculator. It works by using a tree and stack data structures. It reads in from System.in, and uses java.util.Scanner to parse each word separated by spaces. Compile using 'javac rpn.java'. Run using 'java rpn'

The algorigthm used is described in detail here:
http://blog.boyet.com/blog/blog/postfix-to-infix-converting-rpn-to-algebraic-expressions/

This program does not check for errors, and assumes the user knows what they're doing.

Possible operations : + - * / ^ (power).

'p' is to peek at the top of the stack and 'c' is to clear the entire stack

CONTROL+D is to break out of the loop & print final answer.

Sample input: 10 5 +

Followed by CTRL + D.

The output will be : 15 is the final answer.

Sample input: 2 10 ^ 10 + p

The output will be : 1034.0 is at the top of the stack.
