package com.carter.demo.niuke;

import java.util.Scanner;

/**
 *
 * 描述
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 1000
 *
 * 1≤n≤1000
 * 输入描述：
 * 第一行输入一个由字母、数字和空格组成的字符串，第二行输入一个字符（保证该字符不为空格）。
 *
 * 输出描述：
 * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
 *
 * 示例1
 * 输入：
 * ABCabc
 * A
 * 复制
 * 输出：
 * 2
 *
 * @author Carter.li
 * @createtime 2023/4/6 12:05
 */
public class Test2 {


    public static void main(String args[]){

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String str1 = in.nextLine();
        String str2 = in.nextLine();

        int c = 0;
        for(char s: str1.toCharArray()){

            if(str2.equalsIgnoreCase(String.valueOf(s))){
                c+=1;
            }

        }

        System.out.println(c);

    }


}
