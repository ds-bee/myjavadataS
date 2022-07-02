package com.huang.stack;

public class Calculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "30-2*6-2+5";
        ArrayStack2 numStack2 = new ArrayStack2(10);
        ArrayStack2 operStack3 = new ArrayStack2(10);
        //定义相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//作为多个数字的标志位
        while (true){
            //获得expression的字符
            ch = expression.substring(index, index+1).charAt(0);
            if(operStack3.isOper(ch)){
                //判断是否为字符
                if(!operStack3.isEmpty()){
                    //字符栈不是空
                    //判断优先级，
                    if(operStack3.priority(ch) <= operStack3.priority(operStack3.peek())){
                        num1 = numStack2.pop();
                        num2 = numStack2.pop();
                        oper = operStack3.pop();
                        res = numStack2.cal(num1, num2, oper);
                        //将优先级低和相等的先进行计算再压入栈
                        numStack2.push(res);
                        operStack3.push(ch);
                    }else {
                        //优先级高的直接压入栈中
                        operStack3.push(ch);
                    }
                }else {
                    //这个是栈中没有字符
                    operStack3.push(ch);
                }
            }else {//当下一位不是字符时
                keepNum += ch;

                if (index == expression.length() - 1) {
                    //最后一位直接放入
                    numStack2.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack3.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //不是最后一位就分开放入
                        numStack2.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
                index++;
                if(index >= expression.length()){
                    break;
                }

        }
        while(true) {
            //�������ջΪ�գ�����㵽���Ľ��, ��ջ��ֻ��һ�����֡������
            if(operStack3.isEmpty()) {
                break;
            }
            num1 = numStack2.pop();
            num2 = numStack2.pop();
            oper = operStack3.pop();
            res = numStack2.cal(num1, num2, oper);
            numStack2.push(res);//��ջ
        }
        //����ջ���������pop�������ǽ��
        int res2 = numStack2.pop();
        System.out.printf("表达式为 %s = %d", expression, res2);
    }

}


//需要扩展功能
class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack2(int maxSize) {
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
    //返回栈顶的值
    public int peek(){
        return stack[top];
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
    //返回运算符的优先级，数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*'|| oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return  0;
        }else {
            return -1;
        }
    }

    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res ���ڴ�ż���Ľ��
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// ע��˳��
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}