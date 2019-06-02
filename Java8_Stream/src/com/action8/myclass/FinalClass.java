package com.action8.myclass;

/**
 * @ClassName : FinalClass
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-10-28 23:11
 * @Description :
 */
final class FinalClass {
    int a = 3;
    void doit(){}


    public static void main(String[] args) {
        FinalClass f = new FinalClass();
        f.a++;

        System.out.println(f.a);


    }
}
