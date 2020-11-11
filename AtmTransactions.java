import java.util.*;
import java.util.Random;
import java.util.Base64;
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
public class AtmTransactions{
//setting up array list for customers and transactions
     static ArrayList<customer> c1 = new ArrayList<customer>(); 	
      static ArrayList<details> bank1 = new ArrayList<details>();
       public static void main (String args[]) {
               dispenses dispenses1=new dispenses();
               admin admin1=new admin();
               display display1=new display();
                try
              {
                   File file=new File("setCash.txt");
                   Scanner sc=new Scanner(file);
                   while(sc.hasNextInt())
                   {
                        int money_100=sc.nextInt();
                        int money_200=sc.nextInt();
                        int money_500=sc.nextInt();
                        dispenses1.set_100s(money_100);
                        dispenses1.set_200s(money_200);
                       dispenses1.set_500s(money_500);
                   }
                }
               catch(Exception e){}
                int i,j,k,l,acc_num,pin_num,balance;
                 try
              {
                   File file=new File("customerData.txt");
                   Scanner sc=new Scanner(file);
                   while(sc.hasNextInt())
                   {
                        acc_num=sc.nextInt();
                        pin_num=sc.nextInt();
                        balance=sc.nextInt();
                        c1.add(new customer(acc_num,getEncoded(Integer.toString(pin_num)),balance)); 
                   }
                }
               catch(Exception e){}
                try
              {
                   File file=new File("transactionData.txt");
                   Scanner sc=new Scanner(file);
                   while(sc.hasNextInt())
                   {
                        int acc_num1=sc.nextInt();
                        int balance1=sc.nextInt();
                        int amount1=sc.nextInt();
                        int type1=sc.nextInt();
                        bank1.add(new details(acc_num1,balance1,amount1,type1)); 
                   }
                }
               catch(Exception e){}
                boolean stop=true;
        while(stop)
        {
             System. out. println(display1.showMessage("Welcome to the ATM.")) ;
             System. out. println(display1.showMessage("Enter 1 if you are a customer\nEnter 2 if you are the admin\nEnter 3 if you want to activate your account \nEnter 4 if you want to deactivate your account")) ;
             int type,random_int,otp;
            int persontype=display1.sendInt();
            switch(persontype) {
             case 1:System.out.println(display1.showMessage("Enter your account number")) ;
                    i=display1.sendInt();
               System.out.println(display1.showMessage("Enter your pin number")) ;
                    j=display1.sendInt();
                     k= helpWithCustomers(i);
               if(k==c1.size()||c1.get(k).get_pin()==0)
               {
                   System.out.println(display1.showMessage("The account number is wrong"));
               }
               else
               {
               if(c1.get(k).checkpin(j,c1.get(k).get_pin_num()))
               {
                  System.out.println(display1.showMessage("Enter 1 if atm card is SBI \nEnter any other number if the atm card is of other bank"));
                  int bank_type=display1.sendInt();
               System.out.println(display1.showMessage("Opening your account........"));
               while(true)
               {
               System.out.println(display1.showMessage("Enter 1 for withdrawing money\nEnter 2 for depositing money\nEnter 3 for balance enquiry\nEnter 4 for pin change\nEnter 5 for getting mini statemen\nEnter 6 if you want to transfer money")) ;
                         type=display1.sendInt();
                   switch(type){
                       case 1:System.out.println(display1.showMessage("Enter the money you want to withdraw"));
                              int money=display1.sendInt();
                              if(money<=100000)
                              {
                              if(bank_type==1)
                              {
                              if(dispenses1.check_cash(money,c1.get(k)))
                              {
                                  random_int = (int)(Math.random() * (10000));
                                  System.out.println(display1.showMessage("An otp is sent to your registered number.The otp is:")+display1.showInt(random_int));
                                  System.out.println(display1.showMessage("Please enter the otp"));
                                  otp=display1.sendInt();
                                  if(otp==random_int)
                                  {
                                  System.out.println(display1.showMessage("The money is being processed"));
                                  dispenses1.set_number(money,k);
                                     c1.get(k).set_balance(c1.get(k).get_balance()-money);
                                     bank1.add(new details(i,c1.get(k).get_balance(),money,1));
                                     try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(i+"\n"+c1.get(k).get_balance()+"\n"+money+"\n"+"1\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+i+"\n"+j+"\n"+c1.get(k).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                  }
                                  else
                                  {
                                      System.out.println(display1.showMessage("The otp you entered is wrong.Sorry couldn't proccess"));
                                  }
                              }
                              else
                              {
                                  System.out.println(display1.showMessage("The money you entered is more than the balance or else the atm doesn't have the suficient money.Sorry...couldn't process"));
                              }
                              }
                              else
                              {
                                 if(dispenses1.check_cash(money+20,c1.get(k)))
                              {
                                  random_int = (int)(Math.random() * (10000));
                                  System.out.println(display1.showMessage("An otp is sent to your registered number.The otp is:")+display1.showInt(random_int));
                                  System.out.println(display1.showMessage("Please enter the otp"));
                                  otp=display1.sendInt();
                                  if(otp==random_int)
                                  {
                                  System.out.println(display1.showMessage("The money is being processed"));
                                  dispenses1.set_number(money,k);
                                  System.out.println(display1.showMessage("20/- have been charged as processing fee"));
                                     c1.get(k).set_balance(c1.get(k).get_balance()-money-20);
                                     try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(i+"\n"+c1.get(k).get_balance()+"\n"+money+"\n"+"1\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                     bank1.add(new details(i,c1.get(k).get_balance(),money,1));
                                     try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+i+"\n"+j+"\n"+c1.get(k).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                  }
                                  else
                                  {
                                      System.out.println(display1.showMessage("The otp you entered is wrong.Sorry couldn't proccess"));
                                  }
                              }
                              else
                              {
                                  System.out.println(display1.showMessage("The money you entered is more than the balance or else the atm doesn't have the suficient money.Sorry...couldn't process"));
                              }
                              }
                              }
                              else
                              {
                                  System.out.println(display1.showMessage("The maximum transaction you make should be less than 1 lakh.Sorry couldn't process"));
                              }
                              break;
                        case 2:System.out.println(display1.showMessage("Enter the number of 100s you want to deposit"));
                               int no_100s=display1.sendInt();
                               System.out.println(display1.showMessage("Enter the number of 200s you want to deposit"));
                               int no_200s=display1.sendInt();
                               System.out.println(display1.showMessage("Enter the number of 500s you want to deposit"));
                               int no_500s=display1.sendInt();
                               int total=100*(no_100s)+200*(no_200s)+500*(no_500s);
                               if(total<=100000)
                               {
                                 random_int = (int)(Math.random() * (10000));
                                  System.out.println(display1.showMessage("An otp is sent to your registered number.The otp is:")+display1.showInt(random_int));
                                  System.out.println(display1.showMessage("Please enter the otp"));
                                  otp=display1.sendInt();
                                  if(otp==random_int)
                                  {
                                      if(bank_type==1)
                                      {
                               c1.get(k).set_balance(c1.get(k).get_balance()+total);
                               dispenses1.set_cash(no_100s,no_200s,no_500s);
                               System.out.println(display1.showMessage("The money is deposited"));
                               bank1.add(new details(i,c1.get(k).get_balance(),total,2));
                               try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(i+"\n"+c1.get(k).get_balance()+"\n"+total+"\n"+"2\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+i+"\n"+j+"\n"+c1.get(k).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                      }
                                      else
                                      {
                                c1.get(k).set_balance(c1.get(k).get_balance()+total-20);
                               dispenses1.set_cash(no_100s,no_200s,no_500s);
                               System.out.println(display1.showMessage("The money is deposited"));
                               System.out.println(display1.showMessage("20/- have been charged as processing fee"));
                               bank1.add(new details(i,c1.get(k).get_balance(),total,2));
                               try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(i+"\n"+c1.get(k).get_balance()+"\n"+total+"\n"+"2\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+i+"\n"+j+"\n"+c1.get(k).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                      }
                                  }
                                  else
                                  {
                                       System.out.println(display1.showMessage("The otp you entered is wrong.Sorry couldn't proccess"));
                                  }
                               }
                               else
                               {
                                   System.out.println(display1.showMessage("The maximum transaction you make should be less than 1 lakh.Sorry couldn't process"));
                               }
                               break;
                       case 3:System.out.println(display1.showMessage("The balance in your account is:")+display1.showInt(c1.get(k).get_balance()));
                               break;
                        case 4:System.out.println(display1.showMessage("Please enter your old pin number"));
                                l=display1.sendInt();
                                if(c1.get(k).checkpin(l,c1.get(k).get_pin_num()))
                                {
                                    System.out.println(display1.showMessage("Enter the new password."));
                                    int pass=display1.sendInt();
                                    System.out.println(display1.showMessage("Re-enter the password you have typed"));
                                    int pass1=display1.sendInt();
                                    if(pass==pass1)
                                    {
                                        c1.get(k).set_pin_num(getEncoded(Integer.toString(pass)));
                                        try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+i+"\n"+pass+"\n"+c1.get(k).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                        System.out.println(display1.showMessage("Successfully changed password"));
                                    }
                                    else
                                    {
                                        System.out.println(display1.showMessage("Sorry unable to change password.This password did not match with the password you enetered before."));   
                                    }
                                }
                                else
                                {
                                    System.out.println(display1.showMessage("Sorry unable to change password.The old password you typed was wrong"));
                                }
                                break;
                        case 5:System.out.println(display1.showMessage("These were your last 3 transactions"));
                               int least=0;
                                for(int num=bank1.size()-1;num>=0;num--)
                                {
                                    if(bank1.get(num).get_acc_num()==i)
                                    {
                                        if(least<3)
                                        {
                                        if(bank1.get(num).get_type()==1)
                                        System.out.println(display1.showMessage("Withdrawed amount of ")+display1.showInt(bank1.get(num).get_amount())+display1.showMessage(" .Balance at that time:"+bank1.get(num).get_balance()));
                                        else if(bank1.get(num).get_type()==2)
                                        System.out.println(display1.showMessage("Deposited amount of ")+display1.showInt(bank1.get(num).get_amount())+display1.showMessage(" .Balance at that time:")+display1.showInt(bank1.get(num).get_balance()));
                                         else if(bank1.get(num).get_type()==3)
                                        System.out.println(display1.showMessage("Transferred amount of ")+display1.showInt(bank1.get(num).get_amount())+display1.showMessage(" .Balance at that time:")+display1.showInt(bank1.get(num).get_balance()));  
                                         else if(bank1.get(num).get_type()==4)
                                        System.out.println(display1.showMessage("Amount was transferred to your account ")+display1.showInt(bank1.get(num).get_amount())+display1.showMessage(" .Balance at that time:")+display1.showInt(bank1.get(num).get_balance()));   
                                            least++;
                                        }
                                        else
                                        {
                                            break;
                                        }
                                    }
                                }
                                break;
                        case 6:System.out.println(display1.showMessage("Enter the account number of the customer to whom you want to transfer"));
                               int account=display1.sendInt();
                               if(helpWithCustomers(account)<c1.size())
                               {
                                   System.out.println(display1.showMessage("Enter the ifsc number of the customer"));
                                   String a=display1.sendString();
                                   System.out.println(display1.showMessage("Enter the amount you want to transfer"));
                                   int transfer=display1.sendInt();
                                   if(transfer<=100000)
                              {
                              if(bank_type==1)
                              {
                              if(dispenses1.check_money(transfer,c1.get(k)))
                              {
                                  random_int = (int)(Math.random() * (10000));
                                  System.out.println(display1.showMessage("An otp is sent to your registered number.The otp is:")+display1.showInt(random_int));
                                  System.out.println(display1.showMessage("Please enter the otp"));
                                  otp=display1.sendInt();
                                  if(otp==random_int)
                                  {
                                  System.out.println(display1.showMessage("The money is being transferred"));
                                     c1.get(k).set_balance(c1.get(k).get_balance()-transfer);
                                     bank1.add(new details(i,c1.get(k).get_balance(),transfer,3));
                                     try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(i+"\n"+c1.get(k).get_balance()+"\n"+transfer+"\n"+"3\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+i+"\n"+j+"\n"+c1.get(k).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                     c1.get(helpWithCustomers(account)).set_balance(c1.get(helpWithCustomers(account)).get_balance()+transfer);
                                     bank1.add(new details(account,c1.get(helpWithCustomers(account)).get_balance(),transfer,4));
                                     try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(account+"\n"+c1.get(helpWithCustomers(account)).get_balance()+"\n"+transfer+"\n"+"4\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+account+"\n"+c1.get(helpWithCustomers(account)).get_pin()+"\n"+c1.get(helpWithCustomers(account)).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                  }
                                  else
                                  {
                                      System.out.println(display1.showMessage("The otp you entered is wrong.Sorry couldn't proccess"));
                                  }
                              }
                              else
                              {
                                  System.out.println(display1.showMessage("The money you entered is more than the balance.Sorry...couldn't process"));
                              }
                              }
                              else
                              {
                                 if(dispenses1.check_money(transfer+20,c1.get(k)))
                              {
                                  random_int = (int)(Math.random() * (10000));
                                  System.out.println(display1.showMessage("An otp is sent to your registered number.The otp is:")+display1.showInt(random_int));
                                  System.out.println(display1.showMessage("Please enter the otp"));
                                  otp=display1.sendInt();
                                  if(otp==random_int)
                                  {
                                  System.out.println(display1.showMessage("The money is being transferred"));
                                  System.out.println(display1.showMessage("20/- have been charged as processing fee"));
                                     c1.get(k).set_balance(c1.get(k).get_balance()-transfer-20);
                                     bank1.add(new details(i,c1.get(k).get_balance(),transfer,3));
                                     try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(i+"\n"+c1.get(k).get_balance()+"\n"+transfer+"\n"+"3\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                     c1.get(helpWithCustomers(account)).set_balance(c1.get(helpWithCustomers(account)).get_balance()+transfer);
                                     bank1.add(new details(account,c1.get(helpWithCustomers(account)).get_balance(),transfer,4));
                                     try
                                    {
                                        File f=new File("transactionData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append(account+"\n"+c1.get(helpWithCustomers(account)).get_balance()+"\n"+transfer+"\n"+"4\n");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                    try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+i+"\n"+j+"\n"+c1.get(k).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+account+"\n"+c1.get(helpWithCustomers(account)).get_pin()+"\n"+c1.get(helpWithCustomers(account)).get_balance());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                  }
                                  else
                                  {
                                      System.out.println(display1.showMessage("The otp you entered is wrong.Sorry couldn't proccess"));
                                  }
                              }
                               }
                              }
                              else
                              {
                                  System.out.println(display1.showMessage("The maximum transaction you make should be less than 1 lakh.Sorry couldn't process"));
                              }
                               }
                               else
                               {
                                   System.out.println(display1.showMessage("The account number you entered does not exist.Couldn't transfer the money"));
                               }
                               break;
                        default:System.out.println(display1.showMessage("Invalid input"));
                   }
                   System.out.println(display1.showMessage("Enter 1 if you want to continue transaction and enter 0 if you want to logout of the account"));
                   int check=display1.sendInt();
                   if(check==0)
                   {
                       break;
                    }
               }
               }
               else
               {
                   System.out.println(display1.showMessage("The pin number is wrong"));
               }
               }
               break;
              case 2:System.out.println(display1.showMessage("Please enter your admin password"));
                     int password1=display1.sendInt();
                     if(admin1.check_password(password1))
                     {
                         while(true)
                         {
                         System.out.println(display1.showMessage("Welcome to admin page"));
                         System.out.println(display1.showMessage("Enter 1 if you want to know the amount present in the atm\nEnter 2 if you want to add some amount into the atm\nEnter 3 if you want to shut down the system"));
                         int get=display1.sendInt();
                         switch(get)
                         {
                            case 1:System.out.println(display1.showMessage("The amount present in atm is ")+display1.showInt(dispenses1.get_total_money()));
                                   break;
                            case 2:System.out.println(display1.showMessage("Enter the number of 100s you want to add"));
                                   int new100=display1.sendInt();
                                   System.out.println(display1.showMessage("Enter the number of 200s you want to add"));
                                   int new200=display1.sendInt();
                                   System.out.println(display1.showMessage("Enter the number of 500s you want to add"));
                                   int new500=display1.sendInt();
                                   int tot=dispenses1.get_total_money()+100*new100+200*new200+500*new500;
                                   if(admin1.checkamount(tot))
                                   {
                                   dispenses1.set_cash(new100,new200,new500);
                                   File file1=new File("setCash.txt");
                                   try
                                    {
                                        File f=new File("setCash.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+dispenses1.get_100s()+"\n"+dispenses1.get_200s()+"\n"+dispenses1.get_500s());
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   System.out.println(display1.showMessage("The money has been added to the account"));
                                   }
                                   else
                                   {
                                     System.out.println(display1.showMessage("Sorry couldn't add the money as the atm can't hold the amount"));  
                                   }
                                   break;
                           case 3:System.out.println(display1.showMessage("The system is shutting down....."));
                                   stop=false;
                                   break;
                            default:System.out.println(display1.showMessage("The input is wrong"));
                                     break;
                         }
                         if(stop==true)
                         {
                          System.out.println(display1.showMessage("Enter 1 if you want to countinue \nEnter 0 if you want to logout of admin page"));
                            int continueOperation=display1.sendInt();
                            if(continueOperation==0)
                            {
                                break;
                            }
                         }
                         else
                         {
                             break;
                         }
                         }
                     }
                     else
                     {
                         System.out.println(display1.showMessage("The password you entered is wrong"));
                     }
                     break;
              case 3:System.out.println(display1.showMessage("Enter the account number"));
                                     int newacc=display1.sendInt();
                                     if(helpWithCustomers(newacc)==c1.size()||c1.get(helpWithCustomers(newacc)).get_pin()==0)
                                     {
                                     System.out.println(display1.showMessage("Please set a pin number"));
                                     int newpin=display1.sendInt();
                                      random_int = (int)(Math.random() * (10000));
                                  System.out.println(display1.showMessage("An otp is sent to your registered number.The otp is:")+display1.showInt(random_int));
                                  System.out.println(display1.showMessage("Please enter the otp"));
                                  otp=display1.sendInt();
                                  if(otp==random_int)
                                  {
                                     System.out.println(display1.showMessage("Enter the balance in your account"));
                                     int newbalance=display1.sendInt();
                                     c1.add(new customer(newacc,getEncoded(Integer.toString(newpin)),newbalance));
                                     try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+newacc+"\n"+newpin+"\n"+newbalance);
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                     System.out.println(display1.showMessage("Your account has been activated"));
                                  }
                                  else
                                  {
                                      System.out.println(display1.showMessage("The otp you entered is wrong"));
                                  }
                                     }
                                     else
                                     {
                                         System.out.println(display1.showMessage("Your account already exists"));
                                     }
                                     break;
                case 4:System.out.println(display1.showMessage("Enter the account number"));
                                   int remacc=display1.sendInt();
                                   int find=helpWithCustomers(remacc);
                                   if(find==c1.size())
                                   {
                                       System.out.println(display1.showMessage("Couldn't find your account for deactivation"));
                                   }
                                   else
                                   {
                                    random_int = (int)(Math.random() * (10000));
                                  System.out.println(display1.showMessage("An otp is sent to your registered number.The otp is:")+display1.showInt(random_int));
                                  System.out.println(display1.showMessage("Please enter the otp"));
                                  otp=display1.sendInt();
                                  if(otp==random_int)
                                  {
                                   c1.remove(find);
                                    try
                                    {
                                        File f=new File("customerData.txt");
                                       PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
                                      pw.append("\n"+remacc+"\n"+"0"+"\n"+"0");
                                        pw.close();
                                    }
                                   catch(Exception e){}
                                   System.out.println(display1.showMessage("Your account has been deactivated"));
                                  }
                                  else
                                  {
                                      System.out.println(display1.showMessage("The otp you entered is wrong"));
                                  }
                                   }
                                   break;                             
              default:System.out.println(display1.showMessage("The input you entered is wrong"));
                      break;
            }
        }
       }
//function which gives the index of the customer
        public static int helpWithCustomers(int i){
       int j,k,l=c1.size();
       for(j=0;j<=c1.size()-1;j++)
       {
           if(i==c1.get(j).get_acc_num())
           {
               l=j;
           }
       }
       return l;
   }
//function which gives encrypted string of pin number
   public static String getEncoded(String password) {
	       String encrypt=Base64.getEncoder().encodeToString(password.getBytes());
	       return encrypt;
	       }
 }
