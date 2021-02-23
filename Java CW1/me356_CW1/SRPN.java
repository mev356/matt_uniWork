import org.omg.CORBA.FloatSeqHolder;
//import java.lang.Math;
/**
 * Program class for an SRPN calculator. This mimics the calculator specified in the coursework. It can translate inflix sentences when there is no spaces. It splits up strings with spaces to use them as single commands. 

 It can also convert an expression starting with 0xx into its base 8 counterpart. 

 I didnt use the stack object, but rather did all the methods from scratch 

 You can also Use # comments in your commands. 

 The operators it uses are: +,-,*,/,%,^,d,=,#,
 */
  

public class SRPN {

    // Initialise the variables 
    private int[] srpn_stack = new int[25];
    private int head;
    private int max_size = 24; // Default size 
    private boolean comment_mode = false;

    // The constructor for the SRPN stack
    public SRPN(int max_size){
      this.head = 0;
      this.max_size = max_size;
    }

  /*
  This pushes a string which is an int onto the stack
  */
  public void push_int(int k){
      if (this.head < this.max_size){
        this.srpn_stack[this.head] = k;
        this.head++;
      }else{
        System.out.println("Stack overflow.");
      }
    }

  /*
  This chedks to see if the string is numeric  
  */
  private static boolean is_numeric(String str) { 
    try {  
      Double.parseDouble(str);  
      return true;
    } catch(NumberFormatException e){  
      //System.out.println(str + " " + "F");
      return false;  
    }  
  }

  /*
    Process the command inputted by the user. Depending on the Input string it does the operation or passes the operand onto the stack. 

    Passing an operator pops 2 numbers on the stack and 
    performs that operand on them 
    "d" prints the stack  
  */
    public void processCommand(String s){
      int i;
      //System.out.println(s);

      // Split the string by spaces
      String[] splitted = s.split(" ");
     
      // Loop round the splitted space string, processing each "part"
      //System.out.println(
      for (i=0;i<splitted.length;i++){
        process_spaced_part(splitted[i].trim());
      }  
       
    }

  /*
    Process the spaced part of the splitted string. We first split on " " Then we process each command as if they are sent individually

    If the command is say 1+1, we first translate the inflix expression into reverse polish using the shunting yard algorithm
  */
    private void process_spaced_part(String s){
      int i;
      int j=0;
      int op_in_row_count; //To check how many operators are in a row
      int int_flag = 0; //To break from a while loop 
      String s2 = "";

      // tokens will contain the splitted string command sent
      String tokens[] =  new String[99];

      // We need this incase the user submits 1+1+1 instead of 1 + 1 + 1. We first convert the inflix expression to be reverse polish
      rev_polish reverse_polish = new rev_polish(99);
      int token_counter = 0;

      //Loop round the string, we take each character at a time. If the number is more than one digit, we build it using the digit before. If it is a operation (except '-'), we place the number onto the token stack
      
      if (s.trim()=="-"){
      // Need to handle "-" seperately, as it could come in as "-1" or the minus operation (handling both in different ways) 
        process_part(s.trim());
      }else{
        //System.out.println("length of input string: " + s.length());
        
        //Loop round every character in the String 
        while (j<s.length()){

        //System.out.println(j + "  " + s.charAt(j));
          if (Character.isDigit(s.charAt(j))){
            //if its a number 
            if (j == s.length()-1){
              // if its the last number in string, needs to be added to the stack
              //System.out.println("placing token: " + (s2 + s.charAt(j)) + " t_counter: " + token_counter + " j value: " + j);

              tokens[token_counter] = s2 + s.charAt(j);
              token_counter = token_counter+1;
              j++;
            }else{
              // Else - Add digit to current "number" 
              s2 = s2 + s.charAt(j);
              j++;
            }
          }else{
            // If the current char is not a number (or could be a negative...)
            //s.charAt(j) is not a number
            int_flag=0;
            op_in_row_count=1;
            // If its the last character in the string, push both the built 'int' and the operation
            if (j==s.length()-1){
              tokens[token_counter] = s2;
              token_counter = token_counter+1;
              s2="";            
              tokens[token_counter] = String.valueOf(s.charAt(j));
              token_counter = token_counter+1;
              j++;
            }else{
              //Its not the last char in the string 

              // Loop round the next characters after the current operation, to check for the next number
              while(int_flag==0){
                if ((Character.isDigit(s.charAt(j+op_in_row_count)))|((j+op_in_row_count)>=s.length()-1)){
                  int_flag=1;
                }else{
                  op_in_row_count = op_in_row_count+1;
                }
              }
              
              // Place the built 'int' onto the token stack 
              tokens[token_counter] = s2;
              token_counter = token_counter+1;
              s2="";
              
              // Now deal with the number of operations in a row you have 
              if (op_in_row_count == 1){
                
                // If we are the first char and its '-', atatch to the string. (As the next char will be a number )
                if ((s.charAt(j) == '-')&&(j==0)){
                  s2=s2+"-";
                  j=j+1;
                }else{
                  // Push the operation to the token stack 
                  tokens[token_counter] = String.valueOf(s.charAt(j));
                  token_counter = token_counter+1;
                  j++;
                } 
              }else if (op_in_row_count == 2){
                // Else we have 2 operations in a row, and need to deal with them depending on which operations we have
                // Push the first of the 2 ops onto the stack 
                // Special case if '--' are the first chars
                  if (((s.charAt(0) == '-')&&(s.charAt(1)=='-'))&&(j==0)){
                    tokens[token_counter] = String.valueOf(s.charAt(0));
                    token_counter = token_counter+1;
                    tokens[token_counter] = String.valueOf(s.charAt(1));
                    token_counter = token_counter+1;
                    j=j+2;
                  }else{
                    tokens[token_counter] = String.valueOf(s.charAt(j));
                    token_counter = token_counter+1;
                    if (s.charAt(j+1) == '-'){
                      // If the 2nd op is a '-', attach to the string. (As the next char will be a number )
                      s2=s2+"-";
                      j=j+2;
                    }else{            
                      // The 2nd char is not '-', so we place on the stack
                      tokens[token_counter] = String.valueOf(s.charAt(j+1));
                      token_counter = token_counter+1;
                      j=j+2;
                    }
                  }
              }else{
                // We have more than 2 operations in a row, so we place everything onto the stack apart from the last 2 chars, which we deal with separately.
                for (i=0;i<op_in_row_count-2;i++){
                  // Place all but last 2 onto stack 
                  tokens[token_counter] = String.valueOf(s.charAt(j+i));
                  token_counter = token_counter+1;
                }

                // Place 2nd to last onto stack 
                tokens[token_counter] = String.valueOf(s.charAt(j+op_in_row_count-2));
                token_counter = token_counter+1;


                if (s.charAt(j+op_in_row_count-1) == '-'){
                // If last 'op_in_row' before number is '-'    
                  if (s.charAt(j+op_in_row_count-2) == '-'){
                    // If the 2nd to last is also negative, then we push both onto the stack (i didnt make the rules...)
                    tokens[token_counter] = String.valueOf(s.charAt(j+op_in_row_count-1));
                    token_counter = token_counter+1;               
                  }else{
                    // If the 2nd to last is not '-', we attach the final '-' to the number we will build.
                    s2=s2+"-";
                  }
                }else{            
                  // If the last "op_in_row" isnt '-', we place onto token stack 
                  tokens[token_counter] = String.valueOf(s.charAt(j+op_in_row_count-1));
                  token_counter = token_counter+1;
                }

                j=j+op_in_row_count; //Move ahead in the string to the next char we need to deal with 
              }

            }
          }
      }
    
    
      /*
        We now convert the tokens into the correct reverse polish expression, by pushing them to the class which will deal with it 
      */
      for (i=0;i<token_counter;i++){
        //System.out.println(i + "  " + tokens[i] + "   " + is_numeric(tokens[i]));
        if ((is_numeric(tokens[i]))|(tokens[i].equals("#"))){
          reverse_polish.push(tokens[i]);

        }else{
          reverse_polish.push_op(tokens[i]);
        }
      }
      reverse_polish.pop_stack(); // In case the last thing on the stack to convert into reverse polish is an operator, we need to pop it. 

      // Finally... We now process the tokens as if they were individual parts or if they were entered separated by a space.
      for (i=0;i<reverse_polish.get_out_head();i++){
        //System.out.println("part: " + i + " " + reverse_polish.reverse_polish_out[i]);
        process_part(reverse_polish.reverse_polish_out[i]);
      }
      }
      
    }


    /*
         Function to do the reverse polish operation based on what is passed into it. 
    */
    private void do_rev_p_op(String operation){
      int x;
      int y;
      long xy_out = 0; //To check if its over or underflow. As we can check if the result is bigger than an int using a "long"
      int out_result = -1; // In case the result errors
      int error_flag = 0;
      
      // If there is less than 2 items on the stack, then print an underflow message
      if (this.get_head()<2){
        System.out.println("Stack underflow.");   
      }else{
        // Get both of the numbers to do the operation
        x = this.pop();
        y = this.pop();
        switch(operation){
          case("+"):
            xy_out = (long)x + (long)y;
            out_result = x+y;
          break;
          case("-"):
            // Need to do y-x as "-" is not commutative
            xy_out = (long)y - (long)x;
            out_result = y-x;
          break;
          case("*"):
            xy_out = (long)y * (long)x;
            out_result = y*x;
          break;
          case("/"):
          // Remember "/" is not commutative
            if (x == 0){
              System.out.println("Divide by 0.");
              error_flag=1;
            }else{
              xy_out = (long)y / (long)x;
              out_result = y/x;
            }
          break;
          // The remainder function.
          case("%"):
           if (x == 0){
              System.exit(0);
              error_flag=1;
            }else{
              xy_out = (long)y % (long)x;
              out_result = y%x;
            }
          break;
          case("^"):
          // The power function, ie 3^2 = 9
          if (x<0){
            System.out.println("Negative power.");
            error_flag=1;
          } else{
            xy_out = (int)Math.pow((long)y,(long)x);
            out_result = (int)Math.pow(y,x);
          }
          break;
          
        }

        //Now check for saturation - Ie if your result hits the max/min integer value. 
        if (error_flag==1){
          this.push_int(y);
          this.push_int(x);
        }else{
          if (xy_out > Integer.MAX_VALUE){
          //System.out.println(Integer.MAX_VALUE);
            this.push_int(Integer.MAX_VALUE);
          }else if (xy_out < Integer.MIN_VALUE){
            this.push_int(Integer.MIN_VALUE);
          }else{
            this.push_int(out_result);  
          }
        }
      }
    }

      /*
      Process a single part of the string command. The parts are gathered from the splitted "space" string or a longer inflix expression in one string. 
      */
    private void process_part(String s){ 
      int x;
      int y;
      // Operators that produce numbers, to then pass to the specific function
      String[] rp_operators = new String[]{"+", "-", "*", "%", "/", "^", "*"};
     
      // If empty do nothing 
      if (s!=""){

        if (this.comment_mode==true){
          // If theres a "#" activate the comment mode, so no commands until the next "#"
          if (s.equals("#")){
            this.comment_mode=false;
            //System.out.println("COMMENT MODE DE-ACTIVATED");
          }
        
        }else{
          if(contains(rp_operators,s.trim())){
            //If we have an operator that produces a number, then do the operation
            do_rev_p_op(s);
          }else{
            switch(s) {
            case "r":          
              // Get a random integer
              y = Integer.MAX_VALUE;
              x = (int)(Math.random()*(long)y);
              this.push_int(x);
              break;
            case "=" :
              if (this.peek()==-1){     
                System.out.println("Stack empty.");
              }else{
                System.out.println(this.peek());
              }
              break; 
            case "d":
              this.print_stack();
              break;
            case "#":
              this.comment_mode = true;
              //System.out.println("COMMENT MODE ACTIVATED");
            break;
            default : // The default option when the string isnt 
            // any of the special operations 

            // If the number is an int, push it, else we dont recognise i, and print a message.           
            if (is_numeric(s.trim())){
              // If the number starts with a '0' and has a length > 2, we convert it to base 8 form before pushing to the stack
              if (s.length()>2){
                if (s.charAt(0)=='0'){
                  this.push_int(process_base_eight_number(s.trim()));
                }else if ((s.charAt(1)=='0')&&(s.charAt(0)=='-')){
                  // Deal with "-"
                  this.push_int((-1)*process_base_eight_number(s.trim()));
                }else{
                  // Push the normal number 
                  this.push_int(Integer.parseInt(s.trim()));
                }
              }else{
                // Push the normal number 
                this.push_int(Integer.parseInt(s.trim()));
              }

            }else{
              System.out.println("Unrecognised operator or operand: \"" + s + "\".");
            }
            } // End of switch 
          } //End of if we have an operator 
        } // End of if_comment_mode
      } // End of if empty
    }


      /*
      Process a number which is a "0" followed by at least 2 characters. 
      We then process the number in base 8 form.
      e.g 0103 = 3*8^0 + 0*8^1 + 1*8^2 = 3 + 0 + 64 = 67
      */
    private int process_base_eight_number(String x){
      int base = 8;
      int i;
      int base_8_out = 0;
      
      // Loop round the numbers, to get the right end number 
      for (i=1;i<x.length();i++){
        base_8_out = base_8_out + (int)Math.pow(base,x.length()-i-1)*(Integer.parseInt(String.valueOf(x.charAt(i))));
      }
      return base_8_out;
    }
            
    // Get the top pointer of the stack
    public int get_head(){
      return this.head;
    }

    // Check if the stack is empty
    private boolean isEmpty() {
      return (this.head == 0);
    } 

    // Get the max size of the stack 
    public int get_max_size(){
      return max_size;
    }

  // To search through the operator list.  Taken from  https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java
    private static <T> boolean contains(final T[] array, final T v) {
      for (final T e : array){
        if (e == v || v != null && v.equals(e)){
            return true;
        }
      }
      return false;
    }

    // For when the user commands "d", print the stack. If theres nothing in the stack, produce the integer min value 
    public void print_stack(){
      int i;
      if (this.head == 0){
        System.out.println(Integer.MIN_VALUE);
      }else{
        for (i=0;i<this.head;i++){
          System.out.println(srpn_stack[i]);
        }
      }
    }

      /*
        Have a look whats at the top of the stack without popping it
      */
    public int peek(){
      if(!isEmpty()){
        int top;
        top = this.srpn_stack[this.head - 1];
        return top;
      }else{
        //System.out.println("Stack empty.");
        return -1;
      } 
    }  

      /*
        Take the int at the top of the stack and return it 
      */
    public int pop(){
      if(!isEmpty()){
        int top;
        top = this.srpn_stack[this.head - 1];
        this.head--;
        return top;
      }else{
        System.out.println("Stack underflow.");
        return 1;
      }
    }  


   // public void processCommand(String s) {
      //If an "=" is received, print zero always
    //  if (s.charAt(s.length()-1) == '=') {
     //   System.out.println(0);
    //  }
    //}

}
