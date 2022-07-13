package com.huang.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Huffmantree {

    public static void main(String[] args) {

        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        preOrder(createHuffmanTree(arr));


    }
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("是空树，不能遍历");
        }
    }
    //创建赫夫曼树方法
    public static Node createHuffmanTree(int[] arr) {
        //第一步为了操作方便
        //遍历arr数组
        //让arr的每个元素构成一个node
        //让node放入arrlist
        List<Node> nodeList = new ArrayList<Node>();
        for (int i : arr) {
            nodeList.add(new Node(i));
        }
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            System.out.println("node = " + nodeList);

            //取出权值最小的节点（二叉树）
            Node node = nodeList.get(0);
            //取出第二小的节点
            Node node1 = nodeList.get(1);
            //构建一个新的二叉树(看来二叉树的基础知识还有问题)，左右权值相加
            Node node2 = new Node(node.value + node1.value);
            node2.left = node;
            node2.right = node1;
            //从arraylist中删除处理过的二叉树
            nodeList.remove(node);
            nodeList.remove(node1);
            //将parent加入到删除的二叉树中
            nodeList.add(node2);
        }
        //返回root节点
        return nodeList.get(0);
    }

}


//为了让node对象支持排序，collections集合排序
//让node实现comparable接口
class Node implements Comparable<Node>{

    int value;
    Node left;
    Node right;



    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node){
        //对权值进行比较 从小到大排序
        return this.value - node.value;
    }

}


