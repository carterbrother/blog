package com.carter.demo.niuke;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author Carter.li
 * @createtime 2023/4/6 12:05
 */
public class Test3 {


    public static void main(String args[]){

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int qty = in.nextInt();

        TreeSet<Integer> treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer)o1-(Integer) o2;
            }
        });
        for (int i=1;i<=qty;i++){

            int input = in.nextInt();
            treeSet.add(input);

        }

       for (Integer n: treeSet){
           System.out.println(n);
       }

    }


}
