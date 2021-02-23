
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*A class for the token list, getting player tokens from this list.
* If there is no tokens in the list left that a player can use,
* it gets a random character from the possible lower case characters in
* the alphabet. */
public class Token_list {

    private List<Character> token_list = new ArrayList<>();
    //private List<Player> players = new ArrayList<>();
    private int token_head; //Keep track of how far along the list we are
    private Random rand = new Random();
    /*Make the initial list, and fill it with some basic tokens.*/
    public Token_list(){
        token_list.add('r');
        token_list.add('y');
        token_list.add('x');
        token_list.add('o');
        token_list.add('m');
        token_list.add('g');
        token_head = 0;
    }

    /*Get a token to use for a player, if we need to get a new one (ie the list
    * has run out), we use a different method*/
    public char get_next_token(){
        char to_return;
        //System.out.println(token_head + "   " + token_list.size());
        if (token_head >= token_list.size()){
            add_random_char();
        }
        to_return = token_list.get(token_head);
        token_head++;
        return to_return;
    }

    /*Add a random lowercase char to the list of tokens. */
    public void add_random_char(){
        int rand_int;
        boolean in_list_flag = true;
        while(in_list_flag){
            rand_int = rand.nextInt(26) + 97;
            if (!token_list.contains((char)rand_int)){
                token_list.add((char)rand_int);
                in_list_flag = false;
            }
        }
    }

}
