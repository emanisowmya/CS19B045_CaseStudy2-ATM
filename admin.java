package AtmTransactions;
import java.util.*;
import java.util.Random;
import java.util.Base64;
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
public class admin{
    private int password=12345;
    private int maxamount=1500000; 
//boolean function which keeps a check on max amount an atm can store
    boolean checkamount(int balance)
    {
        if(balance<=maxamount)
        {
            return true;
        }
        return false;
    }
//boolean function to check the password of the admin
    public boolean check_password(int pass){
        if(pass==password)
           return true;
           return false;
    }
}
