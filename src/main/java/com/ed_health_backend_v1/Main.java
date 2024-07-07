package com.ed_health_backend_v1;
class A extends Thread{
    public void run(){
        System.out.println("Running");
    }
}
public class Main {

public static void main(String[] args) {
    A a=new A();
    a.start();
    System.out.println(a.getId());
}
}
