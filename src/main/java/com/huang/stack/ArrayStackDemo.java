package com.huang.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; // 退出一个菜单
        Scanner scanner = new Scanner((System.in));
        while ((loop)){
            System.out.println("show: ");
            System.out.println("exit: ");
            System.out.println("push: ");
            System.out.println("pop: 出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("取出 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize] ; //这样新建一个数组并进行赋值
    }
    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈，遍历时，需要从栈顶显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("没有数据，栈为空");
        }
//        while (true){
//            System.out.println(stack[top]);
//            top--;
//            if(top == -1){
//                break;
//            }
//        }这里有问题不能用top来弄，因为会直接删除数据
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d] = %d\n", i ,stack[i]);

        }

    }
}
