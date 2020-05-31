package com.mahb.algorithm.stack;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: algorithm
 * @description: 逆波兰表达式 计算器实现 ; 42 课 有小数点代码实现；
 * @author: Mr.Mahongbin
 * @create: 2019-10-01 23:06
 **/
public class PolandNotation {

    public static void main(String[] args) {

        // 完成 将 一个中缀表达式 转成后缀表达式的 功能
        // 1，1+(2+3)*4)-5 => 转成 1 2 3 + 4 * + 5 -
        // 2，因为 直接 对str 进行操作不方便，因此先将 "1+(2+3)*4)-5" => 中缀的表达式对应的 list
        //  即 "1+(2+3)*4)-5" => ArrayList [1,+,(,(,2,+,3,),*,),-,5]
        String expression =  "1+((2+3)*4)-5";

        // 中缀表达式输出
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);

        // 后缀表达式输出
        List<String> li = parseSuffixExpressionList(list) ;
        System.out.println(li);

        /*
        // 先定义给 逆波兰表达式
        // (3+4)*5-6  =》 3 4 + 5 * 6 -
        // String suffixExpression = "30 4 + 5 * 6 - "; // 为了方便，逆波兰表达式的数字和符号 用空格隔开
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        // 思路：
        // 1 , 先将 "3 4 + 5 * 6 - " => 放到 ArrayList 中
        // 2 , 将 ArrayList 传递给一个 方法， 遍历 配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList==" + rpnList);

        System.out.println(calculate(rpnList));
        */

    }


    /**
     * @description:  完成 将 一个中缀表达式 转成后缀表达式的 功能 思路分析：
     *   1： 初始化 两个栈，运算符栈 s1 和 储存中间结果的 栈 s2
     *   2： 从左至右 扫描中缀表达式；
     *   3： 遇到操作数 进行压栈 s 2;
     *   4： 遇到运算符，比较 其 与 运算符 s 1 栈中 运算符的优先级
     *      4-1: 如果运算符栈 s 1 为空，或 栈顶运算符 为 左括号 ( ,则 直接将此 运算符 入栈；
     *      4-2: 否则 ，若优先级 比栈顶运算符 的高，也将运算符 压入栈 s 1；
     *      4-3: 否则， 将 s1 栈顶 运算符弹出 并 压入到 s2 中， 再次转到 ( 4 -1) 与 s 1 中新的 栈顶运算符 相比较
     *   5：遇到括号时：
     *      5-1 : 如果是 左括号 ( , 则直接 压入  s 1;
     *      5-2 : 如果是 右括号 ) , 则依次弹出 s 1 栈顶的运算符， 并压入 s 2  ， 直到遇到左括号为止， 此时将这一对括号丢弃
     *   6： 重复 2-5 步， 直到表达式 的最右边
     *   7： 将 s 1 中剩余的运算符 依次弹出 并压入 s 2
     *   8： 依次弹出 s 2 中的 元素 并输出， 结果的 逆序 即为 中缀表达式 对应的 后缀表达式
     *
     *   如 中缀表达式为： 1 + (( 2+3 ) * 4 ) -5  ==> 后缀表达式 s 2 出栈为：- 5 + * 4 + 3 2 1  => 1 2 3 + 4 * + 5 -
     **/
    // 将 中缀表达式list  转成 后缀表达式 list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<>(); // 符号栈
        // 说明，因为s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面 我们还需要逆序输出
        // 因此 比较麻烦，这里我们就不用 Stack ， 直接 使用 List s2
        List<String> s2 = new ArrayList<>(); // 储存 中间结果的List s2
        for(String item : ls){
            // 如果是 一个数，就入栈 s2
            if(item.matches("\\d+")){
                s2.add(item) ;
            } else if(item.equals("(")){
                s1.push(item);
            } else if(item.equals(")")){
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); // 将 ( 弹出 s1 栈，消除小括号；
            }else {
                // 当 item 优先级 小于等于 s1 栈顶 运算符，将s1 栈顶运算符弹出 并加入到 s2 中；
                // 再次 转到 (4-1) 与 s1 中新的 栈顶 运算符相比较
                while(s1.size() !=0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item) ; // 将 item 入站；
            }
        }
        // 将 s1 中剩余 运算符 依次弹出并加入 s2
        while(s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    public static List<String> toInfixExpressionList(String s){

        List<String> ls = new ArrayList<>();
        int i = 0;   // 指针，用于遍历中缀表达式字符串
        String str;  // 对多位数的拼接
        char c ;     // 每遍历到一个字符，就放入到 c
        do{
            // 如果 c 是一个 非数字；就需要加入到 ls 去；
            c=s.charAt(i);
            if(c < 48 || c> 57){
                ls.add(""+c);
                i++;
            }else {
                // 是一个数字， 考虑多位数的问题
                str = ""; // 先将 str 置成""，
                while(i < s.length() && ( c=s.charAt(i)) >= 48 &&  (c=s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;
    }


    // 将 一个 逆波兰表达式，依次 将数据和运算符 放入到 ArrayList 中
    public static List<String> getListString(String suffixExpression){
        // 将 suffixExpression  分割
        String[] split = suffixExpression .split(" ");
        List<String> list = new ArrayList<>();
        for(String ele : split){
            list.add(ele);
        }
        return list;

    }

    // 完成 对逆 波兰表达式的计算
    /**
     *  从左至右 扫描， 将 3 和 4 压入堆栈；
     *    遇到 + 运算符， 因此弹出 4 和 3 (4 为 栈顶元素，3 为次顶运算)， 计算出 3+4 的值，得 7 ，再将 7 入栈
     *    将 5 入栈；
     *    接下来是 * 运算符， 因此弹出 5 和 7， 计算出 7*5 = 35， 将 35 入栈；
     *    将 6 入栈；
     *    最后 是 - 运算符， 计算出 35 - 6 的值， 即 29 ，由此得出 最终结果；
     */
    public static int calculate (List<String> ls){
        Stack<String> stack = new Stack<>();
        for(String item : ls){
            if(item.matches("\\d+")){
                stack.push(item) ;
            }else {
                // pop 出两个数， 并运算，再将数 入栈；
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res= 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num2 - num1 ;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res =  num2 / num1 ;
                }else{
                    throw new RuntimeException("运算符 有误。。。。") ;
                }
                stack.push(""+res);
            }
        }
        // 最后留在 stack中的数据就是 运算结果
        return Integer.parseInt(stack.pop());
    }

}

// Operation ， 返回一个运算符对应的 优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
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
                    System.out.println(" 不存在该运算符...");
                    break;
        }
        return result;
    }
}