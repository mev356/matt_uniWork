    public void check_flow(int x, int y,String flow_type, String c_operator){
      long xy_out;

      char s_current_char;
      int last_token = 0;
      String last_char = "";


      int rev_polish_idx = 0;
      int op_stack_idx = 0;
      SRPN mini_sprn = new SRPN(99);
      int last_number_idx = 0;
      int flag = 0;

      int x = 0;
      int y = 0;

      String s3 = "";

      //System.out.println("precedence of this op "+ this_op + "   " + precedence_list.indexOf(this_op));
      //int x = precedence_list.indexOf(this_op);
      //int y = precedence_list.indexOf(peek_op_stack());

     // if ((x<0) | (y<0)){
      //  return -1;
      //}
      //int precedence_lvl_this_op = Integer.parseInt(String.valueOf(precedence_levels.charAt(x)));
      //int precedence_lvl_stack_op = Integer.parseInt(String.valueOf(precedence_levels.charAt(y)));

      //System.out.println("op " + this_op + " prec level: " + Integer.parseInt(String.valueOf(precedence_levels.charAt(x))) + " stack op: " + peek_op_stack() + " prec lvl: " + Integer.parseInt(String.valueOf(precedence_levels.charAt(y))));



                      //System.out.println("placing token: " + String.valueOf(s.charAt(j)) + " t_counter: " + token_counter);   

      if (s.charAt(j)=='-'){
        if (j==0){
          if (Character.isDigit(s.charAt(j+1))){
            // If first character in string, the first number will be negative
            s2 = s2 + s.charAt(j);
          }else{
            tokens[token_counter] = String.valueOf(s.charAt(j));
            token_counter = token_counter+1;
          }

        }else if (!Character.isDigit(s.charAt(j-1))){
          // If j is not 0, 
          //s2 = s2 + s.charAt(j);
         //if (s.charAt(j-1) == '-'){
         
           if (Character.isDigit(s.charAt(j+1))){
           // tokens[token_counter] = String.valueOf(s.charAt(j));
            //token_counter = token_counter+1;
             s2 = s2 + "-";
           }else{
            tokens[token_counter] = String.valueOf(s.charAt(j));
            token_counter = token_counter+1;
           }
        }else{
          // I dont think this does anything?
          tokens[token_counter] = s2;
          token_counter = token_counter+1;
          tokens[token_counter] = String.valueOf(s.charAt(j));
          token_counter = token_counter+1;
          s2 = "";
        }
      }else{
          // If we get a operation, we place the last number onto the token stack
          // We then place the operation onto the stack
          tokens[token_counter] = s2;
          token_counter = token_counter+1;
          tokens[token_counter] = String.valueOf(s.charAt(j));
          token_counter = token_counter+1;
          s2 = ""; 
      }


      if (flow_type == "over"){
          switch(c_operator){
            case "+" :
            xy_out = (long)x + (long)y;
            if (xy_out > Integer.MAX_VALUE){
              return Integer.MAX_VALUE;
            }
            break;
          }
      }else if (flow_type == "under"){

      }
    }

    public int check_flow(long xy_out){
        int result;
        if (xy_out > Integer.MAX_VALUE){
  
        }else if (){
  
        }else(){
          
        } 
      }

      if ((s!="+") & (s.indexOf('+')>=0)){
         
        String[] splitted = s.split("[+]");
         for (i=0;i<splitted.length;i++){
           //System.out.println("part: " + splitted[i]);
           process_part(splitted[i]);
         }
     }

     case "+" :
     do_rev_p_op(s);
     break;
     //System.out.println(x);
   case "-":
     do_rev_p_op(s);
     break;
   case "*":
     do_rev_p_op(s);
     //this.push_int(this.pop() * this.pop());
     break;
   case "%":
     do_rev_p_op(s);
     break;
   case "/":
     do_rev_p_op(s);
     break;
   case "^":
     //System.out.println((int)Math.pow(3,2));
     do_rev_p_op(s);
     break;


     process_part(ints[0]);
     process_part(ints[1]);
     process_part(strs[0]);
     process_part(ints[2]);
     process_part(strs[1]);

     if (s_current_char == '+'){
        x = x + Integer.parseInt(s2.trim());
        System.out.println( i + ": " + x);
        s2 = "";
      }else if (flag==0){
        
      }

      for (i=0;i<=op_stack_idx;i++){
        reverse_polish[rev_polish_idx] = operator_stack[i];
        rev_polish_idx +=1;
      }

      for (j=0;j<rev_polish_idx-1;j++){
            this.process_part(reverse_polish[j]);
            System.out.println(j + " " + reverse_polish[j]);
      }
      this.process_part("=");

      strs[str_counter] = "*";
      ints[ints_counter] = s2;
      //ints[ints_counter] = Integer.parseInt(s2.trim());
      str_counter+=1;
      ints_counter+=1;

      strs[str_counter] = "+";
      ints[ints_counter] = s2;
      //ints[ints_counter] = Integer.parseInt(s2.trim());
      str_counter+=1;
      ints_counter+=1;

      strs[str_counter] = "-";
                ints[ints_counter] = s2;
                //ints[ints_counter] = Integer.parseInt(s2.trim());
                str_counter+=1;
                ints_counter+=1;

      System.out.println(x + " + " + Integer.parseInt(s2.trim()));
                  x = x + Integer.parseInt(s2.trim());
                  
                  System.out.println(x + " - " + Integer.parseInt(s2.trim()));
                  x = x - Integer.parseInt(s2.trim());
                  
                  System.out.println(x + " * " + Integer.parseInt(s2.trim()));
                x = x * Integer.parseInt(s2.trim());
                


                if ((s.indexOf('+')>=0) | (s.indexOf('-')>=0) | (s.indexOf('%')>=0)| (s.indexOf('*')>=0)| (s.indexOf('+')>=0)| (s.indexOf('/')>=0)){
                  for (i=0;i<=last_number_idx;i++){
                    s_current_char = s.charAt(i);
                    System.out.println("c_char: " + s_current_char + " last_char: " + last_char + " s2: " + s2 + " s2? " + (s2 == ""));
          
                      switch (s_current_char){
                        case ('+'):
                          if  (last_char != ""){
                            if (s2 != ""){
                              y = Integer.parseInt(s2.trim());
                            //System.out.println(y);
                              x = use_last_char(x,y,last_char);
                              last_char = "+";
                              s2 = "";
                            }else{
                              s2 = last_char + s2;
                            }
                          }else{
                            x = x + Integer.parseInt(s2.trim());
                            last_char = "+";
                            s2 = "";
                          }
                        //System.out.println( i + ": " + x);
                          
                          break;
                        case ('-'):
                          if  (last_char != ""){
                            if (s2 != ""){
                              y = Integer.parseInt(s2.trim());
                              //System.out.println(y);
                              x = use_last_char(x,y,last_char);
                              last_char = "-";
                            }
                          }else{
                            x = x + Integer.parseInt(s2.trim());
                            last_char = "-";
                          }
                          s2 = "";
                          break;
                        case ('*'):
                          if  (last_char != ""){
                            if (s2 != ""){
                              y = Integer.parseInt(s2.trim());
                              //System.out.println(y);
                              x = use_last_char(x,y,last_char);
                              last_char = "*";
                            }
                          }else{
                            x = x + Integer.parseInt(s2.trim());
                            last_char = "*";
                          }
                          s2 = "";
                          break;
                        default:
                          s2 = s2 + s.charAt(i);
                      }
                    
                    
                    
                  }
                  System.out.println(s2 + ": " + x);




                  if (this.get_head()<2){
                    System.out.println("Stack underflow.");
                  }else{
                    x = this.pop();
                    y = this.pop();
                  // need to get x and y as the "-" operation isnt 
                  // commutative i.e x-y != y-x
                    xy_out = (long)y - (long)x;
                    if (xy_out > Integer.MAX_VALUE){
                      this.push_int(Integer.MAX_VALUE);
                    }else if (xy_out < Integer.MIN_VALUE){
                      this.push_int(Integer.MIN_VALUE);
                    }else{
                      this.push_int(y-x);
                    }
                  }

                  if (this.get_head()<2){
                    System.out.println("Stack underflow.");
                  }else{
                    x = this.pop();
                    y = this.pop();
                  // need to get x and y as the "-" operation isnt 
                  // commutative i.e x-y != y-x
                    xy_out = (long)y * (long)x;
                    if (xy_out > Integer.MAX_VALUE){
                      this.push_int(Integer.MAX_VALUE);
                    }else if (xy_out < Integer.MIN_VALUE){
                      this.push_int(Integer.MIN_VALUE);
                    }else{
                      this.push_int(y*x);
                    }
                  }

                  if (this.get_head()<2){
                    System.out.println("Stack underflow.");
                  }else{
                // need to get x and y as the "%" operation isnt 
                  // commutative i.e x%y != y%x 
                    x = this.pop();
                    y = this.pop();
                    this.push_int(y%x);
                    break;
                  }

                            // need to get x and y as the "/" operation isnt 
          // commutative i.e x/y != y/x
            if (this.get_head()<2){
              System.out.println("Stack underflow.");
            }else{
              x = this.pop();
              y = this.pop();
              if (x == 0){
                System.out.println("Divide by 0.");
              }else{
            // System.out.println("x = " + y/x);
                this.push_int(y/x);
              }
            }