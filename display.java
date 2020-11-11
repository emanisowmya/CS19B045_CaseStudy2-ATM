package AtmTransactions;
import java.util.*;
import java.util.Random;
import java.util.Base64;
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
interface screen{
    public String showMessage(String s);
    public int showInt(int x);
}
interface keyboard{
    Scanner scan=new Scanner(System.in);
    public int sendInt();
}
public class display implements screen,keyboard{
    private int send;
    private String send1;
//shows the message on the screen
    public String showMessage(String s){
        return s;
    }
//shows the integer on the screen
    public int showInt(int value)
    {
        return value;
    }
//scans the input integer and sends to main function
    public int sendInt(){
        send=scan.nextInt();
        return send;
    }
//scans the input string and sends to main function
    public String sendString(){
        send1=scan.next();
        return send1;
    }
}
