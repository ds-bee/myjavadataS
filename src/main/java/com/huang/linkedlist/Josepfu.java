package com.huang.linkedlist;

public class Josepfu {

    public static void main(String[] args) {
        //测试环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.show();
        circleSingleLinkedList.countBoy(1, 2, 3);
        circleSingleLinkedList.show();


    }
}
//创建一个环形的单向链表
class CircleSingleLinkedList{
        //创建一个first节点，当前没有编号
        private Boy first = new Boy(-1);
        //添加小孩，构成一个环形链表
        public void  addBoy(int nums){
            if(nums < 1 ){
                System.out.println("nums值不正确");
                return;
            }
            Boy curBoy = null; //辅助指针构建环形链表
            //使用一个for循环
            for (int i = 1; i <= nums; i++) {
                //根据编号，创建一个小孩节点
                Boy boy = new Boy(i);
                if(i == 1){
                    first = boy;
                    first.setNext(first);
                    curBoy = first;
                }else {
                    curBoy.setNext(boy);
                    boy.setNext(first);
                    curBoy = boy;
                }
            }
        }
        //遍历当前环形链表
        public  void show(){
            //判断链表是否为空
            if(first == null){
                System.out.println("没有任何小孩");
                return;
            }
            Boy curBoy = first;
            while (true){
                System.out.printf("小孩的编号%d \n", curBoy.getNo());
                if(curBoy.getNext() == first){
                    break;
                }
                curBoy = curBoy.getNext();
            }
        }
        //根据用户输入，计算小孩出圈的顺序
    public  void countBoy(int startNo, int countNum, int nums){
        //先对数据进行校验
        if(first == null || startNo<1 || startNo>nums){
            System.out.println("参数输入错误");
            return;
        }
        Boy helper = first;
        while (true){
            if(helper.getNext() == first){
                break; //将helper定位到最后一个节点
            }
            helper = helper.getNext();
        }
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
            helper = helper.getNext();
            
        }
        //当小孩报数，让first移动和helper移动
        //这里是一个循环操作
        while (true){
            if(helper == first){
                break;
            }
            for (int i = 0; i < countNum -1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩出圈%d", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的是%d", first.getNo());

    }


}

//创建一个boy节点
class Boy{

    private int no;
    private Boy next;

    public Boy() {
    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}