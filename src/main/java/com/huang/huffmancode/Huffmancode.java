package com.huang.huffmancode;

import java.util.*;

public class Huffmancode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println("长度为" + bytes.length);
        System.out.println("压缩前" + Arrays.toString(bytes));
        byte[] bytes1 = huffmanZip(bytes);
        System.out.println("压缩后" + Arrays.toString(bytes1));
//        List<Node> nodes = getNodes(bytes);
//        System.out.println("nodes" + nodes);
//        //测试
//        System.out.println("赫夫曼是");
//        Node huffmanTree = createHuffmanTree(nodes);
//        System.out.println(huffmanTree);
//        System.out.println("前序遍历");
////        preOrder(huffmanTree);
//        huffmanTree.preOrder();
//        //测试生成的哈夫曼编码表
////        getCode(huffmanTree,"",stringBuilder);
//        getCode(huffmanTree);
//        System.out.println("生成的哈夫曼表" + huffmanCode);
//
//        byte[] zip = zip(bytes, huffmanCode);
//        System.out.println("哈夫曼=" + Arrays.toString(zip));
//        for (int i = 0; i < zip.length; i++) {
//            System.out.print(zip[i]);
//        }

    }

    //编写一个方法进行解码

    /**
     *
     * @param huffmanCode
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCode , byte[] huffmanBytes){

        //先得到huffmanbytes对应的二进制字符串，形式是
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i]; //这里就是传的一个比特，而不是一位
            //遍历到最后一位就需要将flag置为1
            boolean flag = (i == huffmanBytes.length - 1);
            //转换的字符加入string builder
            stringBuilder.append(byteToBitString(flag,b));
        }
        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String>entry: huffmanCode.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> bytes = new ArrayList<>();
        //使用滑动窗口的方法
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; //滑动窗口的另外一个指针
            boolean flag = true;
            Byte b = null;
            while (flag){
                String substring = stringBuilder.substring(i, i + count);
                b = map.get(substring);
                if(b == null){
                    count++;
                }else {
                    flag = false;
                }
            }
            bytes.add((byte)Integer.parseInt(stringBuilder.substring(i, i + count)));
            i += count;
        }
        byte[] b = new byte[bytes.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = bytes.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转为一个二进制的字符串
     * @param flag 标志是否需要补高位,最后一个字节无需补高位
     * @param b 传入的压缩字符串
     * @return 按补码返回的字符串
     */
    private static String byteToBitString(boolean flag, byte b){
        //
        int temp = b;
        if(flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }

    //使用一个方法将上面的都封装起来，方便调用
    /**
     *
     * @param bytes 原始数组
     * @return 返回压缩的数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建Huffman树
        Node huffmanTree = createHuffmanTree(nodes);
        //创建Huffman编码表
        getCode(huffmanTree);
        //根据Huffman编码压缩传入字节数
        byte[] zip = zip(bytes, huffmanCode);
        return zip;

    }

    /**
     *
     * @param bytes
     * @param huffmanCode
     * @return
     * 举例：“i like like ...” =>byte[] = "".getByte
     * 对应字符串编码101010001010010100001
     * 对应的byte[] huffmanBytes ,即8位对应一个byte，放入huffmancodeBytes
     * huffmanCodeBytes[0] = 10101000（补码）=》 byte [推导 10101000=》10101000 -1 => 10100111(对应的反码) =》 11011000（第一位是符号位不变）最后等于-88 ]
     */
    //编写一个方法将字符串对应的byte[]数组，通过生成赫夫曼编码表，返回一个赫夫曼编码压缩的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String>huffmanCode){
        //利用huffmancodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCode.get(aByte));
        }
        //将对应字符串转换为一个byte数组
        //统计返回的 byte[] huffmanCodeBytes的长度
        //int len = (stringBuilder.length() + 7)/8 下面的可读性强一些
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length() /8 +1 ;
        }
        //创建一个存储压缩后的byte数组
        byte[] bytes1 = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+= 8) {
            String substring;
            //要处理越界的问题,不够8位
            if (i + 8 > stringBuilder.length()) {
//                给已经赋值的变量再添加类型会报错，相当于是新的变量 在变量钱进行赋值操作
//                String substring = stringBuilder.substring(i);
                substring = stringBuilder.substring(i);
            }else {
                substring = stringBuilder.substring(i, i + 8);
            }
            //将substring 转成byte，放入byte数组
            bytes1[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return bytes1;
    }

    //生成赫夫曼编码
    //将赫夫曼编码表存放在Map<>形式中
    static Map<Byte,String> huffmanCode = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    //32(空格对应的asc码值)->01
    //在生成赫夫曼编码中，需要去拼接路径，定义一个stringbuilder存储某个叶子节点的路径

    //重载方法，调用方便
    private static void getCode(Node node){
//        if (node != null){
//            getCode(node.left,"",stringBuilder);
//            getCode(node.right,"",stringBuilder);
//        }
        //上面是少写了最开始传入的0和1
        if (node != null){
            getCode(node.left,"0",stringBuilder);
            getCode(node.right,"1",stringBuilder);
        }
//        if(root == null) {
//            return null;
//        }
//        getCode(root.left, "0", stringBuilder);
//        getCode(root.right, "1", stringBuilder);
//        return huffmanCode;

    }

    /**
     *
     * @param node 传入节点
     * @param code 编码路径 右子节点1 左子节点 0
     * @param stringBuilder 拼接路径
     */
    private static void getCode(Node node, String code, StringBuilder stringBuilder){

        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if(node != null  ){
            if(node.data == null) {
                getCode(node.left, "0", stringBuilder1);
                getCode(node.right, "1",stringBuilder1);
            }else{
                huffmanCode.put(node.data,stringBuilder1.toString());
            }
        }

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
