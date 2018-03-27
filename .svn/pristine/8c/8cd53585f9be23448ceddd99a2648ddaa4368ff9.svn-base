package com.risenb.quene;

public class QueueMain {  
    public static void main(String[] args) {  
        InQueue inq1 = new InQueue("1");  
        InQueue inq2 = new InQueue("2");  
        InQueue inq3 = new InQueue("3");  
        InQueue inq4 = new InQueue("4");  
        OutQueue out = new OutQueue("1");
        OutQueue out1 = new OutQueue("4");
        OutQueue out2 = new OutQueue("4");
        new Thread(inq1).start();  
        new Thread(inq2).start();  
        new Thread(inq3).start();  
        new Thread(inq4).start();  
        new Thread(out).start();  
        new Thread(out1).start();  
        new Thread(out2).start();  
        
    }  
  
}  
