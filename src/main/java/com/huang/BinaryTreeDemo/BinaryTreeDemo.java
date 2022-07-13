package com.huang.BinaryTreeDemo;

import javax.print.attribute.standard.PresentationDirection;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode root1 =new HeroNode(2,"无用");
        HeroNode root2 =new HeroNode(3,"卢俊义");
        HeroNode root3 =new HeroNode(4,"林冲");
        HeroNode root4 =new HeroNode(5,"公孙胜");
        //先手动创建
        root.setLeft(root1);
        root.setRight(root2);
        root2.setLeft(root3);
        root2.setRight(root4);
        binaryTree.setRoot(root);
        //测试前序遍历
//        binaryTree.preOrder();
        //测试中序遍历
//        binaryTree.infixOrder();
        //测试后续遍历
//        binaryTree.postOrder();
        //前序遍历查询
//        HeroNode heroNode = binaryTree.preOrderSearch(4);
        HeroNode heroNode = binaryTree.indexOrderSearch(4);
//        HeroNode heroNode1 = binaryTree.postOrderSearch(4);

//        if(heroNode != null){
//            System.out.printf("找到信息为 no = %d, name = %s", heroNode.getNo(),heroNode.getName());
//        }else {
//            System.out.println("没有找到英雄");
//        }
        //这里如果删除就会将这个节点整体删除
        binaryTree.delNode(3);
        binaryTree.preOrder();



    }

}
//定义binaryTree二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        //这样能够避免为每个方法传入root节点
        this.root = root;
    }
    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("无法遍历二叉树");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("无法遍历二叉树");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("无法遍历二叉树");
        }
    }
    //前序遍历
    public HeroNode preOrderSearch(int no ){
        if(root != null){
            return root.preOrdersearch(no);
        }else {
            return null;
        }
    }
    //中序遍历
    public HeroNode indexOrderSearch(int no ){
        if(root != null){
            return root.indexOrdersearch(no);
        }else {
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no ){
        if(root != null){
            return root.postOrdersearch(no);
        }else {
            return null;
        }
    }
    public void delNode(int no){
        if(root != null){
            root.delNode(no);
        }else {
            System.out.println("没有找到删除的节点");
        }
    }

}


//创建HeroNode
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
//                ", left=" + left +
//                ", right=" + right +
                '}';
    }
    //删除一个节点
    public void delNode(int no){
        //删除节点
        if(this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //这里就相当于是没有找到节点就继续遍历
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null){
            this.right.delNode(no);
        }


    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归左子树遍历
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //先递归向左子树中序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        //遍历父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历 最后遍历中节点
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);

    }
    //前序遍历查找
    public HeroNode preOrdersearch(int no){
        //比较当前节点
        if (this.no == no ){
            return this;
        }
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrdersearch(no);
        }
        if(resNode != null){
            return resNode;
        }

        if(this.right != null){
            resNode = this.right.preOrdersearch(no);
        }

        return resNode;
    }

//    //中序遍历查找
//    public HeroNode indexOrdersearch(int no){
//        //比较当前节点
//        if (this.left.no == no ){
//            return this.left;
//        }
//        HeroNode resNode = null;
//        //这么写这里会一直递归
//        if(this.no != no){
//            resNode = this.indexOrdersearch(no);
//        }
//        if(resNode != null){
//            return resNode;
//        }
//
//        if(this.right != null){
//            resNode = this.right.indexOrdersearch(no);
//        }
//
//        return resNode;
//    }
//中序遍历查找
public HeroNode indexOrdersearch(int no){
        //比较当前节点
    HeroNode resNode = null;
    if (this.left != null ){
        return this.left.indexOrdersearch(no);
    }
    if(resNode != null){
        return resNode;
    }
    //这么写这里会一直递归
    if(this.no == no){
        resNode = this;
    }

    if(this.right != null){
        resNode = this.right.indexOrdersearch(no);
    }

    return resNode;
}

    //后续遍历
    public HeroNode postOrdersearch(int no){
        //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrdersearch(no);

        }
        if(resNode != null){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrdersearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.no == no){
            return this;
        }
        return resNode;

    }



}


