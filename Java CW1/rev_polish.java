 
/* 
 * This class is made as we need to convert from a inflix expression into a  reverse polish expression. 
 * 
 * We use an algorithm that uses a stack to do this conversion. The 
 * algorithm is the shunting yard algorithm. 
 * The minor alteration is that when there is an operation of less precedence it pops the entire stack instead of the one on the top. 
 * 
 */
public class rev_polish {

    // Make a stack for the operators and the output expression
    private String[] operator_stack = new String[99];
    public String[] reverse_polish_out = new String[99];
    private int out_head; //The pointer for the end of the output
    private int op_stack_head; // The pointer for the end of the operator stack
    private int max_size = 99; 
    private String precedence_list = "d - + / * % ^ # =";
   // private String precedence_levels = "0 1 2 3 3 4 4 4 9"; // DEPRECATED

  // initialise the 2 stack's one for the operators, one for the output 
    public rev_polish(int max_size){
      this.op_stack_head = 0;
      this.out_head = 0;
      this.max_size = max_size;
    }

  // Pop the operator from the operator stack to the output,
    private String pop_op(){
      if(!isEmpty()){
        String top;
        top = this.operator_stack[this.op_stack_head - 1];
        this.op_stack_head--;
        return top;
      }else{
        System.out.println("Stack underflow.");
        return "";
      }
    }

  // Push the input to the top of the output stack
    public void push(String inp){

      if (this.out_head < this.max_size){
        this.reverse_polish_out[this.out_head] = inp;
        this.out_head++;
      }
    }

    /*  
    Push an operator to the output stack. If there is an operator on the operator stack, we first pop that one to the output stack. 

    The precdence of each operator is slighty different to normal, where + has greater precdence than -

    The list of precdences is at the top of the class.
    
    If there isnt any operator on the op stack , then just place the operator onto the operator stack. 

    We do this as we need to store the operations before we see the 2nd
    number it will be operating on. 
     */
    public void push_op(String this_op){
      int shunting_flag = 0; // If we need to pop multiple operators with precedence 

      if (this_op!=""){

        if (this.op_stack_head < this.max_size){
          
          if (op_stack_head > 0){
            // If we have something on the stack 
            while (shunting_flag==0){
              // Check if the current op's precedence than the one on the top of the stack
              // if lower precedence: pop the op_stack top, and continue till the op_stack top has equal or greater precedence
              
              if (check_precedence(this_op)==1){
                if (op_stack_head==0){
                  shunting_flag = 1; 
                } else{
                  //this.push(this.pop_op());
                  this.pop_stack();
                } 
              }else{
                // If higher/equal precedence, place the new op on the op stack 
                this.operator_stack[this.op_stack_head] = this_op;
                this.op_stack_head++; 
                shunting_flag = 1; 
              }
            }     
          }else{
             // Nothing on the stack, so push whatever 
            this.operator_stack[this.op_stack_head] = this_op;
            this.op_stack_head++;   

          }      
        }
      }
    }

    /*
      Check the precdence of the input operator with the one on the top of the stack

      Check if the current op's precedence than the one on the top of the stack
      
      if lower precedence: pop the op_stack top, and continue till the op_stack top has equal or greater precedence
      
      If higher/equal precedence, place the new op on the op stack 
    */
    private int check_precedence(String this_op){

      if (precedence_list.indexOf(this_op) <= precedence_list.indexOf(peek_op_stack())){
        return 1;
      }else{
        return -1;
      }
      
    }
      // Check if the stack is empty
    private boolean isEmpty() {
      return (this.op_stack_head == 0);
    } 

    // Push all of the operators onto the output stack
    public void pop_stack(){
      while (op_stack_head>0){
        this.push(pop_op());
      }
    }
    // Look at the operator on the top of the operator stack 
    public String peek_op_stack(){
      if(!isEmpty()){
        String top;
        top = this.operator_stack[this.op_stack_head - 1];
        return top;
      }else{
        //System.out.println("Stack empty.");
        return "Stack Empty";
      } 
    }  
    // Get the top pointer of the stack
    public int get_out_head(){
      return this.out_head;
    }

  // Print the output stack
    public void print_stack(){
      int i;
      for (i=0;i<this.out_head;i++){
        //System.out.println(i + "  " + reverse_polish_out[i]);
        System.out.println(reverse_polish_out[i]);
      }
    }

}