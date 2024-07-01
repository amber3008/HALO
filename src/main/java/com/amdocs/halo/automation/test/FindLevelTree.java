package com.amdocs.halo.automation.test;

import com.amdocs.halo.automation.test.BinarySearchTreeTest.Node;

public class FindLevelTree 
{
	Node root=null;
	int l=0;
	int lh=0;
	int rh=0;
	public class Node
	{
		int data;
		Node right=null;
		Node left=null;
		Node(int data)
		{
			this.data=data;
		}
	}
	
	public Node createTree()
	{
		root= new Node(100);
		Node root_b= new Node(90);
		Node root_c= new Node(80);
		Node root_d= new Node(70);
		Node root_e= new Node(85);
		Node root_f= new Node(60);
		Node root_g= new Node(65);
		Node root_h= new Node(110);	
		Node root_i= new Node(120);
		Node root_j= new Node(130);
		Node root_k= new Node(140);
		Node root_l= new Node(150);
		Node root_m= new Node(160);
		Node root_n= new Node(86);
		Node root_o= new Node(87);
		Node root_p= new Node(88);
		Node root_q= new Node(89);
		Node root_r= new Node(149);
		Node root_s= new Node(147);
		Node root_t= new Node(148);
		
		
		root.right=root_h;
		root.left=root_b;
		
		root_b.right=null;
		root_b.left=root_c;
		
		root_c.right=root_e;
		root_c.left=root_d;
		
		root_d.left=root_f;
		root_d.right=null;
		
		root_e.left=null;
		root_e.right=root_n;
		
		root_f.left=null;
		root_f.right=root_g;
		
		root_g.left=null;
		root_g.right=null;
		
		root_h.left=null;
		root_h.right=root_i;
	
		root_i.left=null;
		root_i.right=root_j;
		
		root_j.left=null;
		root_j.right=root_k;
		
		root_k.left=null;
		root_k.right=root_l;
		
		root_l.left=root_r;
		root_l.right=root_m;
		
		root_m.left=null;
		root_m.right=null;
		
		root_n.left=null;
		root_n.right=root_o;

		root_o.left=null;
		root_o.right=root_p;
		
		root_p.left=null;
		root_p.right=root_q;
		
		root_q.left=null;
		root_q.right=null;
		
		root_r.left=root_s;
		root_r.right=null;
	
		root_s.left=null;
		root_s.right=root_t;
		
		root_m.left=null;
		root_m.right=null;
		
		root_t.left=null;
		root_t.right=null;
		
		return root;	
	}
	
	public int findLevel(Node node, int level)
	{
		
		System.out.println("Current Node is : "+node.data);
		if(root==null)
		{
			return 0;
		}
		else
		{
			if(node.left!=null)
				{findLevel(node.left,level+1);}
				
			
			if(node.right!=null)
				{findLevel(node.right,level+1);}
			
			System.out.println("Left level at last is : "+level);
			if(node.right==null && node.left==null)
			{
				System.out.println("End of the tree");
			}
		}
		
		return 0;
	}
	
	/*public void updateLeft()
	{
		left++;
	}
	
	public void updateRight()
	{
		right++;
	}
	
	public int getLeftLevel()
	{
		return left;
	}
	
	public int getRightLevel()
	{
		return right;
	}*/
	
	public int getHeight(Node n)
	{
		if(n==null)
			return 0;
		
		else
			return max(getHeight(n.left), getHeight(n.right))+1;
	}
	
	public int max(int a, int b)
	{
		return a > b ? a: b;
	}
	public void printLeftNodes(Node node)
	{
		while(node!=null)
		{
			System.out.println(node.data);
			node=node.left;
		}
	}
	
	public static void main(String[] args) 
	{
		FindLevelTree flt=new FindLevelTree();
		Node node=flt.createTree();
		//flt.printLeftNodes(node);
		int level=flt.getHeight(node);
		System.out.println("The Level of node at last is : "+level);
		
		
	}

}
