package AtmTransactions;
import java.util.*;
import java.util.Random;
import java.util.Base64;
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
abstract class common
{
         public int acc_num;
         public int balance;
//set function for account number
         public void set_acc_num(int acc_num){
              this.acc_num=acc_num;
          }
//get function for account number
       public int get_acc_num(){
               return acc_num;
       }
//set function for balance
          public void set_balance(int balance){
              this.balance=balance;
          }
//get function for balance
       public int get_balance(){
               return balance;
       }
}
class customer extends common
{ 
          private String pin_num;
//constructor for customer
          customer(int acc_num,String pin_num,int balance)
          {
              this. acc_num=acc_num;
              this. pin_num=pin_num;
              this. balance=balance;
          }
//set function for encoded pin number
          public void set_pin_num(String pin_num){
              this.pin_num=pin_num;
          }
//get function for encoded pin number
          public String get_pin_num()
          {
              return pin_num;
          }
//get function for real pin number
          public int get_pin(){
              return Integer.valueOf(getDecoded(pin_num));
          }
//function for decoding a string
	       private String getDecoded(String a) {
		      return new String(Base64.getMimeDecoder().decode(a));
	         }
//function to check whether the pin is correct or not
	         public boolean checkpin(int i,String a)
	         {
	             if(Integer.valueOf(getDecoded(a))==i)
	             {
	                 return true;
	             }
	             return false;
	         }
}
class details extends common
{
    private int type,amount;
//details constructor
    details(int acc_num,int balance,int amount,int type)
   {
            this. acc_num=acc_num;
            this. balance=balance;
            this.amount=amount;
            this.type=type;
    }
//set function for amount
    public void set_amount(int amount)
    {
        this.amount=amount;
    }
//get function for amount
       public int get_amount(){
               return amount;
       }
//set function for type of transaction
    public void set_type(int type)
    {
        this.type=type;
    }
//get function for type of transaction
       public int get_type(){
               return type;
       }
}
