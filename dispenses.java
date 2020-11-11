package AtmTransactions;
import java.util.*;
import java.util.Random;
import java.util.Base64;
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
class atm
{
            public int no_of_100;
            public int no_of_200;
            public int no_of_500;
            public int total_money;
//setting number of 100s,200s and 500s
           public void set_100s(int no_100) {
                 no_of_100=no_100;
           }
           public void set_200s(int no_200) {
                 no_of_200=no_200;
           }
           public void set_500s(int no_500) {
                 no_of_500=no_500;
           }
           public int get_total_money()
           {
               total_money=100*no_of_100+200*no_of_200+500*no_of_500;
               return total_money;
           }
}
class deposit extends atm
{
//set function for denominations when there is a transaction
    public void set_cash(int no_100,int no_200,int no_500)
    {
        set_100s(no_100+no_of_100);
        set_200s(no_200+no_of_200);
        set_500s(no_500+no_of_500);
    }
}
public class dispenses extends deposit
{
    private int l,m,n,o,p,no_500s,no_100s,no_200s;
//boolean function for checking whether the cash entered is valid
    public boolean check_cash(int cash,customer c1){
         if(cash<=c1.get_balance() && get_total_money()>=cash)
         {
             return true;
         }
         return false;
    }
    public boolean check_money(int cash,customer c1){
         if(cash<=c1.get_balance())
         {
             return true;
         }
         return false;
    }
//function for getting the number of denominations the bank will give to the customer during withdraw
    public void set_number(int i,int k)
    {
        l=i/500;
        if(l<=no_of_500)
        {
              no_500s=l;
        }
        else
        {
              no_500s=no_of_500;
        }
        
              m=i-500*(no_500s);
              n=m/200;
              if(n<=no_of_200)
              {
                  no_200s=n;
              }
              else
              {
                  no_200s=no_of_200;
              }
                o=i-500*(no_500s)-200*(no_200s);
                p=o/100;
                no_100s=p;
                System.out.println("You will recieve "+no_500s+" 500 rupee notes,"+no_200s+" 200 rupee notes and "+no_100s+" 100 rupee notes");
            set_100s(no_of_100-no_100s);
            set_200s(no_of_200-no_200s);
            set_500s(no_of_500-no_500s);
    }
//get function for number of 100s,200s and 500s
    public int get_100s(){
        return no_of_100;
    }
    public int get_200s(){
        return no_of_200;
    }
    public int get_500s(){
        return no_of_500;
    }
}
