package com.ds.test;


import java.util.*;

/**
 * @author dongsheng
 *
剑指 Offer 27. 二叉树的镜像

请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
镜像输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 

示例 1：

输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
 

 */

public class Main9 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right=new TreeNode(3);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
//        mirrorTree(root);
        levelOrder(root);
    }
    public static TreeNode mirrorTree(TreeNode root) {
        // 结束条件是节点为null
        if(root ==null){
            return null;
        }
        // 交换左右子节点后返回父节点
        TreeNode left=mirrorTree(root.left);
        TreeNode right=mirrorTree(root.right);
        TreeNode temp=left;
        root.left=right;
        root.right=temp;
        return root;
    }

    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }


    public static List<List<Integer>> levelOrder(TreeNode root) {

        boolean right=true;
        List<List<Integer>> ans=new ArrayList<>();
        Deque<TreeNode> queue=new LinkedList<>();
        Deque<TreeNode> tmp=new LinkedList<>();
        if(root==null){
            return ans;
        }
        queue.add(root);
        List<Integer> first=new ArrayList<>();
        first.add(root.val);
        ans.add(first);
        while(!queue.isEmpty()){
            while(!queue.isEmpty()){
                TreeNode treeNode= queue.poll();
                if(treeNode.left !=null){
                    tmp.offer(treeNode.left);
                }
                if(treeNode.right !=null){
                    tmp.offer(treeNode.right);
                }
            }
            queue.addAll(tmp);

            List<Integer> list=new ArrayList<>();

            while(!tmp.isEmpty()){
                TreeNode treeNode;
                if(right){
                    treeNode= tmp.pollLast();
                }else{
                    treeNode= tmp.pollFirst();
                }
                list.add(treeNode.val);
            }
            right=!right;
            if(list.size()!=0){
                ans.add(list);
            }
        }
        return ans;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if(res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
                else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }

        return res;
    }

}
