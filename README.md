# CS19B045_CaseStudy2-ATM
README file

NOTE:-ALL THE INPUTS SHOULD BE INTERGERS(<10^9).
      THE PASSWORD OF ADMIN IS:12345
      THE MAXIMUM AMOUNT THE ATM CAN STORE IS 15LAKH


The code contains 5 class files:
1)AtmTransactions:Its the main class
2)Display:The display class contains the interface of both screen and keyapd.
3)common:Its an abstract class which contains customer class and details class.
4)admin:admin class contains the password and checking the password functionalities.
5)dispenses:its contains inheritance from the atm class and deposits class.

And contains 3 text files:
1)customerData.txt:It contains the data of all the customers.
2)transactionData.txt:It contains the data of all the transactions
3)setCash.txt:It conatins the number of 100s,200s and 500s present in the ATM.

Changes made:-
Added files to save data.
Added the tranfer of money option
Added encoder and decoder for pin number
Added the otp process
Added option of banks,like if the card is of same bank,processing charges wont be taken else 20/- will be taken.
Kept a check on the admin class regarding the maximum money an atm can hold
Max transaction an user can do.
Removed the adding and removing the account can be done by the customer by activating or deactivating the account


ATM design:-
The ATM has 3 types of notes 100s,200s and 500s.Before any transaction,the bank will send otp to the registered mobile,then The money will  be withdrawn giving highest priority to 500,then 200,then 100.
When a customer deposits money,the money gets added in the atm and the number of 100s,200s and 500s will increase accordingly.
The admin has the power to add money to the ATM and shut down the ATM.


How program works:-
Firstly the information regarding the customers will be stored in a file names customerData.txt.
Firstly,then atm will ask 2 options whether we are a customer or an admin.Then select accordingly.
If we enter the customer it will ask us to enter the account number and pin number.If any of the one is wrong,the program return to the welcome to atm thing.
If the password is current then it has the following options:-withdraw money,deposit money,balance enquiry,mini statement,transfer money and pin change.The customer will select their opyion according to their wish.
If the customer want to withdraw an amount which is more than their balance or more than the money in the ATM then the machine will show that their is no enought money.
Then if he enters admin,then the machine acts to enter the admin password.If the password is wrong then it will prompt an error message.
Else if the password is correct the admin have the options like know the amount in the ATM,add money to the atm and shut down.
So he can type his interests accordingly.
