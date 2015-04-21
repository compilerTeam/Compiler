
package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Home
 * Date: 4/19/15
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyParser
{
    private static String input ;
    private static Token token;
    public static void parse()
    {
        getInput();
        if(program() && input.equals("eof"))
            System.out.print("accept!");
        else
            System.out.print("error!");
    }
    public static void error()
    {

    }
    public static boolean program()
    {
        while(!input.equals("eof"))
        {

            if(input.equals("first"))
            {
                getInput();
                return ConstDecl();
            }
            if(input.equals("char") || input.equals("int") || input.equals("float"))
            {
                //getInput(); gozashte nashode chon qarare Type check she!
                return VarDecl();
            }
            if(input.equals("calss"))
            {
                getInput();
                return ClassDecl;
            }
            if(input.equals("function"))
            {
                getInput();
                return FunctuionDecl;
            }
        }
        System.out.println("error!on program!");
        return false;
    }
    public static void Mulop()
    {

    }


    ///////////////////nonterminal's functions   /// input = akharin tokene khande nashode!
    private static void getInput()
    {
        token = MyScanner.advance();
        input = token.getString();
    }
    private static boolean VarDecl()
    {
        int currentLine=token.getLine();
        if(Type())
        {
            if(input.equals("identifier"))
            {
               getInput();
               while((!input.equals(";")))
               {
                  if(input .equals(","))
                  {
                     getInput();
                     if(input.equals("identifier"))
                     {
                        getInput();
                     }
                     else
                     {
                        return false;   //id ,,
                     }
                  }
                  else
                  return false;  //id!
               }
               getInput();
               return true;
            }
        }
        return false;
    }
    private static boolean MethodDecl()
    {
        if(Type())
        {
            if(input.equals("identifier"))
            {
                getInput();
                if(input.equals("("))
                {
                    getInput();
                    if(ArgsForm())
                    {
                        getInput();
                        if(input.equals(")"))
                        {
                            return Block();
                        }
                    }
                }
            }
        }
        else if(input.equals("void"))
        {
            getInput();
            if(input.equals("identifier"))
            {
                getInput();
                if(input.equals("("))
                {
                    getInput();
                    if(ArgsForm())
                    {
                        getInput();
                        if(input.equals(")"))
                        {
                            return Block();
                        }
                    }
                }
            }
        }
        else
        {
            System.out.print("error on MethodDecl");
            return false;
        }
        System.out.print("error on MethodDecl");
        return false;
    }
    private static boolean Type()
    {
        if(input.equals("char") || input.equals("float") || input.equals("int"))
        {
            getInput();
            if(input.equals("["))
            {
                if(input.equals("]"))
                {
                    getInput();
                    return true;
                }
                else
                {
                    System.out.println("] baste nazashtin!");
                    return false;
                }
            }
            else
            {
                return true;
            }
        }
    }
    
     private static boolean Addop()
    {
        if(input.equals("+"))
        {
            getInput();
            return true;
        }
        else if(input.equals("-"))
        {
            getInput();
            return true;
        }
        else
        {
            System.out.print("not true operation!");
            return false;
        }
    }
    
    //&&sarah-added
    public static boolean Mulop()
    {
       if(input.equals("*")||input.equals("&")||input.equals("%"))
           return true;
        return false;
    }

    public static boolean Relop()
    {
        if(input.equals(" =")||input.equals(" ==")||input.equals("!=")||input.equals("<")||input.equals("<=")||input.equals(">")||input.equals(">="))
            return true;
        return false;
    }

    public static boolean CondTerm(){
        //must be done later
        return true;
    }


    //&&end sarah-added
    
   //&&sarah-added
    private static boolean ConstDecl(){
        if(input.equals("final")){
            getInput();
            if(Type()){
               getInput();
                if(input.equals("identifier")){
                    getInput();
                    if(input.equals(" =")){
                        getInput();
                        if(input.equals("number")||input.equals("Terminal")){
                            return true;
                        }
                        System.out.println("initialize via char or integer. error on line"+token.getLine()+"and col"+token.getCol());
                        return false;
                    }
                    System.out.println("initialize needs'=' symbol. error on line"+token.getLine()+"and col"+token.getCol());
                    return false;
                }
                System.out.println("enter identifier after type. error on line"+token.getLine()+"and col"+token.getCol());
                return false;
            }
            System.out.println("enter type at first. error on line"+token.getLine()+"and col"+token.getCol());
            return false;
        }
        System.out.println("declare final const. error on line"+token.getLine()+"and col"+token.getCol());
        return false;
    }


    private static boolean Block() {  //dunno if its right or anything...:|
        if(input.equals("{")){
            int countOpenBlock=0;
            while(input.equals("{")){
                getInput();
                countOpenBlock++;
            }
            if(Statement()){
                if(input.equals("}")){
                    while(input.equals("}")){
                        getInput();
                        countOpenBlock--;
                    }
                }
            }
            if(countOpenBlock==0)
                return true;
            return false;
        }
        return false;  //To change body of created methods use File | Settings | File Templates.
    }
    //&&end sarah-added
    //pegah added ;p 1 ordibehesht!11:30
    private static boolean Factor()
    {
        if(input.equals("number"))
        {
            getInput();
            return true;
        }
        else if(input.equals("charConst"))
        {
            getInput();
            return true;
        }
        else if(input.equals("new"))
        {
            getInput();
            if(input.equals("identifier"))
            {
                getInput();
                if(input.equals("["))
                {
                    getInput();
                    if(input.equals("-") || input.equals("number") ||input.equals("new") || input.equals("("))
                    {
                        if(Expression())
                        {
                           if(input.equals("]"))
                           {
                                getInput();
                                return true;
                           }
                           else
                            {
                                System.out.println("] baste nazashtin!,in FActor");
                                return false;
                            }
                        }
                    }
                    else
                    {
                        System.out.print("missing expression!");
                        return false;
                    }

                }
                else
                {
                    return true;
                }
            }
            else
            {
                System.out.println("wrong in Factor missing id!!");
                return false;
            }
        }
        else if(input.equals("("))
        {
            getInput();
            if(input.equals("-") || input.equals("number") ||input.equals("new") || input.equals("("))
            {
                if(Expression())
                {
                    if(input.equals(")"))
                    {
                        getInput();
                        return true;
                    }
                    else
                    {
                        System.out.print("missing )!in Factor/(");
                        return false;
                    }
                }
            }
                else
                {
                    System.out.println("missing expression!!!in factor(");
                    return false;
                }
        }
        else if(input.equals("identifier"))
        {
            if(Designator())
            {
                if(input.equals("("))
                {
                    getInput();
                    if(input.equals("-") || input.equals("number") ||input.equals("new") || input.equals("("))
                    {
                        if(ActParts())
                        {
                            if(input.equals(")"))
                            {
                                getInput();
                                return true;
                            }
                            else
                            {
                                System.out.println(") baste nazashtin!,in FActor,Design");
                                return false;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("warnning:() with no ActPART");
                        if(input.equals(")"))
                        {
                            getInput();
                            return true;
                        }
                        else
                        {
                            System.out.println(") baste nazashtin!,in FActor,Design");
                            return false;
                        }
                    }
                }
                else
                {
                    System.out.println("warnning: no (), no ActPART");
                    rreturn ture;
                }
            }
            else
            {
                /////////////////what to DO?inja age designator qalat bashe!tokenemun az dast mire!
                System.out.println("wrong designator!");
                return false;
            }
        }
        System.out.println("ehat R U doing in factor?!");
        return false;
    }
    //end !;p
    
}//end class
