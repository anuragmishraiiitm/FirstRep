package com.actiance;

public class MessageUtil {

	   private String message;

	   //Constructor
	   //@param message to be printed
	   public MessageUtil(String message){
	      this.message = message; 
	   }

	   // prints the message
	   public String printMessage(){
		   System.out.println("inside printmessage");
	      return message;
	   }   

	   // add "Hi!" to the message
	   public String salutationMessage(){
		   System.out.println("inside testsalutation");
	     message = "tutorialspoint" + message;
	      return message;
	   } 
	   
	   public String exitMessage(){
		   System.out.println("inside testexit");
		   message="www."+message;
		   return message;
	   }
	}  
