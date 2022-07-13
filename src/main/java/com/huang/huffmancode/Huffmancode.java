package com.huang.huffmancode;

import java.util.*;

public class Huffmancode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        List<Node> nodes = getNodes(bytes);
        System.out.println("nodes" + nodes);
        //测试
        System.out.println("赫夫曼是");
        Node huffmanTree = createHuffmanTree(nodes);
        System.out.println(huffmanTree);
        System.out.println("前序遍历");
        preOrder(huffmanTree);


    }
    private static void preOrder(Node node){
        if (node != null){
            node.preOrder();
        }else {
            System.out.println("树为空");
        }
    }
    private static List<Node> getNodes(byte[] bytes){
        //创建一个arraylist
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte aByte : bytes) {
            Integer count = counts.get(aByte);
            if(count == null){
                counts.put(aByte, 1);
            }else {
                counts.put(aByte, count + 1);
            }
        }
        //把每个键值对转成一个Node对象，并加入nodes中
        for(Map.Entry<Byte, Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
    //通过list创建一个赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){

            Collections.sort(nodes);
            Node node = nodes.get(0);
            Node node1 = nodes.get(1);
            Node node2 = new Node(null, node.weight + node1.weight);
            node2.left = node;
            node2.right = node1;
            nodes.remove(node);
            nodes.remove(node1);
            nodes.add(node2);

        }
        return nodes.get(0);
    }

}
//创建Node,待 数据和权值
class Node implements Comparable<Node>{
    Byte data; //放数据本身
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node){
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
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


}
