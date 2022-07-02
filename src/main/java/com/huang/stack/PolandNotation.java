package com.huang.stack;

import com.beust.ah.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        //将一个中缀表达式转成后缀表达式
        //因为直接对str操作不方便，将中缀表达式存入list中
        //
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);
        List<String> list1 = parseSuffixExpreesion(list);
        System.out.println(list1);
        System.out.printf("计算结果为：%d", calculate(list1));

        //先定义一个逆波兰表达式
        String suffixExpression = "3 4 6 + 5 * 6 -";
        //先将suffixExpression放入arraylist中
        //将arraylist传递给一个方法， 配合栈完成计算
        List<String> rpnlist = getListString(suffixExpression);

        System.out.println("rnplist = " + rpnlist);
        int calculate = calculate(rpnlist);
        System.out.println("计算结果为" + calculate);
    }

    public static List<String> toInfixExpressionList(String s ){
        ArrayList<String> ls = new ArrayList<>();
        int i = 0; //这是一个指针用于遍历表达式字符串
        String str;
        char c;
        do{
            //如果是一个非数字
            if((c = s.charAt(i)) <48 || (c = s.charAt(i))>57){
                ls.add("" + c);
                i++;
            }else {
                str = ""; //先将str置成空串
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c = s.charAt(i))<= 57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    //将arraylist中中缀表达式变成后缀表达式】
    public static List<String> parseSuffixExpreesion(List<String> ls){
        Stack<String> stack1 = new Stack<>();
        //因为2栈没有pop操作，而且我们还需要逆序输出，使用list要方便一点
//        Stack<String> stack2 = new Stack<>();

        ArrayList<String> s2 = new ArrayList<>();

        //遍历ls
        for (String item: ls){
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                stack1.add(item);
            }else if(item.equals(")")){
                //当栈顶元素不等于（，则一直出栈放入第二个存储容器
                while (!stack1.peek().equals("(")){
                    s2.add(stack1.pop());
                }
                stack1.pop();
            }else {
                //栈一不为空，判断栈一中的运算符的优先级,如果栈顶的优先级大于等于添加元素的优先级，就将栈顶元素放入容器二
                //不然就直接放入栈中
                while (stack1.size() != 0 && Operation.getValues(stack1.peek())>= Operation.getValues(item)){
                    s2.add(stack1.pop());
                }
                stack1.push(item);
            }
        }
        //将栈中所有元素放入容器二
        while (stack1.size() != 0){
            s2.add(stack1.pop());
        }
        return s2;

    }

    //将一个逆波兰表达式，依次将数据和运算符放入arraylist中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> arrayList = new ArrayList<>();
        for (String els: split) {
            arrayList.add(els);
        }
        return arrayList;
    }

    public static int calculate(List<String> ls){
        //创建栈
        Stack<String> stack = new Stack<>();
        for(String item: ls){
            if(item.matches("\\d+")){
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals(("+"))){
                    res = num1 +num2;
                }else if(item.equals(("-"))){
                    res = num1 - num2;
                }else if(item.equals(("*"))){
                    res = num1 * num2;
                }else if(item.equals(("/"))){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");//转换字符串
            }
        }
        //最后放入stack中的就是运算结果
        return Integer.parseInt(stack.pop());

    }

}
class Operation{
    private static  int ADD = 1;
    private static  int SUB = 1;
    private static  int MUL = 2;
    private static  int DIV = 2;

    //返回对应的优先级数字
    public static int getValues(String operation){
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在运算符" + operation);
                break;
        }
        return result;

    }

}