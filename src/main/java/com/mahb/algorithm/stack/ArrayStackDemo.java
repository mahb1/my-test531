package com.mahb.algorithm.stack;
import javax.security.auth.Subject;
// import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @program: algorithm
 * @description: 数组模拟栈 ， 自己用链表实现栈；
 *   前缀表达式 叫 波兰表达式 、 运算符都 位于操作符之前；从右到左 扫描
 *   中缀表达式 就是根据优先级 来进行计算；
 *   后缀表达式 叫 逆波兰表达式  、 运算符 位于操作数之后；从左到右 扫描
 * @author: Mr.Mahongbin
 * @create: 2019-10-01 14:03
 **/
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStackDemo demo = new ArrayStackDemo();
        demo.calculatorRealize();
    }

    // 计算器实现
    public void calculatorRealize(){
        String expression = "130+2*6-20";

        // 创建数栈和 符号栈
        ArrayStack numStack =  new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);

        //定义相关变量；
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 每次扫描得到 char 保存到ch ;
        String keepNum = "";

        // 开始while 循环的 扫描 expression

        while(true) {
            // 依次得到expression 的每一个字符 , 每次只截取 一位
            ch = expression.substring(index, index+1).charAt(0);
            // 判断 ch 是什么，然后 做 判断
            if(operStack.isOper(ch)){
                // 判断符号栈 是否为空
                if(!operStack.isEmpty()){
                    /* 如果符号栈 有操作符，比较 ，如果当前的操作符的优先级 小于或者 等于栈中 的操作符,
                     *   就需要从数栈中 pop出两个数，在符号栈中 pop出一个符号，进行运算，将得到结果入数栈，
                     *   然后 将当前的操作符 入符号栈
                     */
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res); // 把 运算结果入数栈
                        operStack.push(ch); // 把 当前操作符 入符号栈
                    } else {
                        //  如果当前 的操作符优先级大于栈中的操作符， 就直接 入符号栈
                        operStack.push(ch);
                    }
                }else {
                    // 如果为空 入符号栈
                    operStack.push(ch);
                }
            } else{
                // 如果是整数，则直接 入数栈
                //  numStack.push(ch - 48); // 进行 asci 计算
                // 1，当处理多位数时，不能发现是一个数，立即入栈，因为可能是多位数；
                // 2，在处理数，需要向expression的表达式index 再看一位，如果是 符号在入栈；
                // 3，需要定义 字符串变量，用于拼接
                keepNum += ch;

                // 如果 ch 已经是 expression 的最后一位，就直接入栈
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                } else{
                    // 判断下一个字符是不是数字, 如果是数字 就继续扫描，是运算符则入栈
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        // 如果后一位 是运算符， 则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }

            // 让 index +1 ，并判断是否扫描 到 expression 最后；
            index ++ ;

            //  结束；
            if(index >= expression.length()){
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从 数栈和符号栈中 pop 出相应的数和符号，并运行；
        while (true) {
            // 如果 符号栈为空，则计算到最后的结果，数栈中只有一个数字 [ 结果 ]
            if(operStack.isEmpty()){ break; }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res); // 把 运算结果入数栈
        }

        System.out.println("表达式 ： "+expression+" 的结果："+ numStack.pop() );
    }

    // 栈的 测试；
    public void showStack(){
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;  // 是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("show: 表示显示栈" );
            System.out.println("exit: 表示退出栈" );
            System.out.println("push: 表示添加栈" );
            System.out.println("pop: 表示取出栈数据" );

            System.out.println(" 请输入你的选择：....");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println(" 请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res= stack.pop();
                        System.out.println("出栈数据为：...." + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println(" 程序退出。。。");
    }
}

class ArrayStack{
    private int maxSize; // 栈的大小
    private int[] stack; // 数组模拟栈， 存储数据
    private int top =-1; // top 表示栈顶，初始化为 -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int [this.maxSize];
    }

    // 是否栈满
    public boolean isFull(){ return top == maxSize -1; }

    //  是否为空
    public boolean isEmpty (){ return  top == -1; }

    // 添加
    public void push(int value){
        if(isFull()){ System.out.println(" 栈满！");return; }
        top++;
        stack[top] = value;
    }

    // 将栈顶数据返回；
    public int pop(){
        if(isEmpty()){throw new RuntimeException("栈空。。。。"); }

        int value = stack[top];
        top--;
        return value;
    }

    // 显示 栈的情况，遍历时，需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){System.out.println("栈空。。。。");  return; }

        for(int i=top; i >= 0; i--){
            System.out.println("栈出数据：...stack["+i+"]..."+ stack[i]);
        }
    }

    /**
     *  实现计算器 需要 新增以下 几个方法
     * @param oper
     * @return
     */
    // 获取优先级
    public int priority(int oper){
        if(oper == '*' || oper == '/'){return  1;}
        else if(oper == '+' || oper == '-'){return  0;}
        else {return  -1; }
    }
    // 判断 运算符
    public boolean isOper(char val){ return val == '+' || val =='-' || val == '*' || val =='/' ; }
    // 进行计算
    public int cal(int num1, int num2, int oper){
        int res = 0; // res 用于存放计算 结果；
        switch (oper){
            case '+':
                res = num1 +num2;
                break;
            case '-':
                res = num2 - num1 ;
                break;
            case '*':
                res =   num1 * num2;
                break;
            case '/':
                res =    num2 / num1 ;
                break;
        }

        return  res;
    }
    // 增加一个方法，返回当前栈顶的值，不是真正的 pop
    public int peek(){ return stack[top]; }


}
