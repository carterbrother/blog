package com.carter.demo.niuke;


import java.util.Scanner;

/**
 * @author Carter.li
 * @createtime 2023/4/6 11:04
 */
public class getLastInputStrLengthTest {

    public static  void main(String[] args){

        Scanner in = new Scanner(System.in);

        String input="";

        for ( ;!"q".equals(input);)
        {

            System.out.println("请输入一行字符串,q表示结束");

            input=in.nextLine();

            System.out.println("您输入的字符串: "+input+"最后的单词长度是：" + getInputStrLastWordLength(input));
        }


    }


    public static int getInputStrLastWordLength(String str){

        assert null!=str;
        assert str.length()>0&& str.length()<=5000;

        String[] strArray = str.split(" ");
        String lastWord = strArray[strArray.length-1];
        return lastWord.length();
    }



}
