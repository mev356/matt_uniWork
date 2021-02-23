import java.io.*;

class Main {

 /*  
    main method   reads in input from the command line
    and passes this input to the processCommand method in SRPN 
 */
  public static void main(String[] args) {
    // Code to take input from the command line 
    // This input is passed to the processCommand
    // method in SRPN.java 
    SRPN sprn = new SRPN(23);
    
    
    /* 

    The below code block is for testing the SRPN calculator, so i dont have to 
    type it in everytime. 
    
    int i;
    i = 40;

    switch(i){
      case 0:
        break;
      case 1:
        sprn.processCommand("10");
        //System.out.println(sprn.peek());
        sprn.processCommand("2");
        //sprn.print_stack();
        //System.out.println(sprn.peek());
        sprn.processCommand("+");
        //System.out.println(sprn.peek());
        sprn.processCommand("=");
        break;
      case 2:
        sprn.processCommand("9");
        sprn.processCommand("4");
        sprn.processCommand("*");
        sprn.processCommand("=");
        break;
      case 3:
        sprn.processCommand("11");
        sprn.processCommand("3");
        sprn.processCommand("/");
        sprn.processCommand("=");
        break;
      case 4:
        sprn.processCommand("11");
        sprn.processCommand("3");
        sprn.processCommand("%");
        sprn.processCommand("=");
        break;
      case 5:
        sprn.processCommand("11");
        sprn.processCommand("3");
        sprn.processCommand("-");
        sprn.processCommand("=");
        break;
      case 6:
        sprn.processCommand("3");
        sprn.processCommand("3");
        sprn.processCommand("*");
        sprn.processCommand("4");
        sprn.processCommand("4");
        sprn.processCommand("*");
        sprn.processCommand("+");
        sprn.processCommand("=");
        break;
      case 7:
        sprn.processCommand("1234");
        sprn.processCommand("2345");
        sprn.processCommand("3456");
        sprn.processCommand("d");
        sprn.processCommand("+");
        sprn.processCommand("d");
        sprn.processCommand("+");
        sprn.processCommand("d");
        sprn.processCommand("=");
        break;
      case 8:
        sprn.processCommand("2147483647");
        sprn.processCommand("1");
        sprn.processCommand("+");
        sprn.processCommand("=");
        break;
      case 9:
        sprn.processCommand("-2147483647");
        sprn.processCommand("1");
       // sprn.processCommand("d");
        sprn.processCommand("-");
        sprn.processCommand("=");
        sprn.processCommand("20");
        //sprn.processCommand("d");
        sprn.processCommand("-");
        sprn.processCommand("=");
        break;
      case 10:
        sprn.processCommand("100000");
        sprn.processCommand("0");
        sprn.processCommand("-");
        sprn.processCommand("d");
        sprn.processCommand("*");
        sprn.processCommand("=");
        break;
      case 11:
        sprn.processCommand("1");
        sprn.processCommand("+");
        break;
      case 12:
        sprn.processCommand("10");
        sprn.processCommand("5");
        sprn.processCommand("-5");
        sprn.processCommand("+");
        sprn.processCommand("/");
        break;
      case 13:
        sprn.processCommand("11+1+1+d");
        break;
      case 32:
        sprn.processCommand("11*2+3+d");
        break;
      case 39:
        sprn.processCommand("11-2*3+d");
        break;
      case 14:
        sprn.processCommand("# This i s a comment #");
        sprn.processCommand("1 2 + # And so i s t h i s #");
        sprn.processCommand("d");
        sprn.processCommand("3");
        break;
      case 15:
        sprn.processCommand("3 3 ^ 3 ^ 3 ^=");
        break;
      case 16:
        sprn.processCommand("1+1");
        sprn.processCommand("=");
        break;
      case 17:
        sprn.processCommand("1 + 1");
        break;
      case 18:
        sprn.processCommand("1 + 1");
        sprn.processCommand("d + 1");
        sprn.processCommand("+ =");
        break;
      case 19:
        sprn.processCommand("3 2 ^");
        sprn.processCommand("=");
        break;
      case 20:
        sprn.processCommand("#This i s a comment #3 4 *");
        sprn.processCommand("d1 2+#And so i s t h i s#");
        sprn.processCommand("d");
        sprn.processCommand("3");
        sprn.processCommand("=");
        sprn.processCommand("d");
        break;
      case 21:
        sprn.processCommand("3 4 *");
        sprn.processCommand("d 1 2 +");
        sprn.processCommand("d");
        sprn.processCommand("3");
        sprn.processCommand("=");
        sprn.processCommand("d");
        break;
      case 22:
        sprn.processCommand("--1");
        sprn.processCommand("d");
        break;
      case 23:
        sprn.processCommand("-1+-1");
        sprn.processCommand("d");
        break;
      case 24:
        sprn.processCommand("-1+--1");
        sprn.processCommand("d");
        break;
      case 25:
        sprn.processCommand("-1*--1");
        sprn.processCommand("d");
        break;
      case 26:
        sprn.processCommand("-1*-1");
        sprn.processCommand("d");
        break;
      case 27:
        sprn.processCommand("#[]#");
        sprn.processCommand("d");
        break;
      case 30:
        sprn.processCommand("d");
        break;
      case 31:
        sprn.processCommand("2 + 2 +=");
        break;
      case 28:
        sprn.processCommand("r r r r r r r r r r r r r r r r r r r r r r d r r r d");
        break;
      case 29:
        sprn.processCommand("a q w e r t y u i o p a s d f g h j k l z x c v b n m ! £ $ % ^ & * ( ) - _ + = . ¬ : ; ' @ { } [ ] , < > ? | ~ ");
        break;
      case 34:
        sprn.processCommand("01 d");
        sprn.processCommand("012 d");
        sprn.processCommand("0123 d");
        sprn.processCommand("01234 d");
        sprn.processCommand("012345 d");
        break;
      case 35:
        sprn.processCommand("01 d");
        sprn.processCommand("010 d");
        sprn.processCommand("0101 d");
        sprn.processCommand("0123 d");
        sprn.processCommand("01234 d");
        sprn.processCommand("012 d");
        break;
      case 37:
        sprn.processCommand("3-4+");
        sprn.processCommand("d");
        break;
      case 38:
        sprn.processCommand("0100");
        sprn.processCommand("0101");
        sprn.processCommand("-01234d");
        sprn.processCommand("d");
        break;
      case 36:
        sprn.processCommand("0123456789abcdefghijklmnopqrstuvwxyz");
        break;
    }
 */    
    System.out.println("You can now start interacting with the SRPN calculator");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      //Keep on accepting input from the command-line
     while(true) {
        String command = reader.readLine();
        //Close on an End-of-file (EOF) (Ctrl-D on the terminal)
        if(command == null){
          //Exit code 0 for a graceful exit
          System.exit(0);
        }        
        //Otherwise, (attempt to) process the character
        sprn.processCommand(command);          
      }
    } 
    catch(IOException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  } 
}