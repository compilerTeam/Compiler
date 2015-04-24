
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
    //sarah speaks B-) : this method is extra, so commented it!
    //public static void Mulop()
    //{
//
    //}


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
       if(input.equals("*")||input.equals("&")||input.equals("%")){
           getInput();
           return true;
       }
       System.out.print("not true operator");
        return false;
    }

    public static boolean Relop()
    {
        if(input.equals(" =")||input.equals(" ==")||input.equals("!=")||input.equals("<")||input.equals("<=")||input.equals(">")||input.equals(">=")){
            getInput();
            return true;}
        System.out.print("not true operator");    
        return false;
    }

    public static boolean CondTerm(){
        if(CondFact()){
            getInput();
            while(input.equals("&&")) {
                getInput();
                if(CondFact())
                    getInput();
                else{
                    System.out.println("some fact is missing in condition line"+token.getLine()+"and col"+token.getCol());
                    return false;
                }
            }
            return true;
        }
        System.out.println("there must be a condition fact here in line"+token.getLine()+"and col"+token.getCol());
        return false;
        
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
                    if(input.equals("identifier") || input.equals("-") || input.equals("number") ||input.equals("new") || input.equals("("))
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
            if(input.equals("identifier") ||  input.equals("-") || input.equals("number") ||input.equals("new") || input.equals("("))
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
                    return true;
                }
            }
            else
            {
                /////////////////what to DO?inja age designator qalat bashe!tokenemun az dast mire!
                System.out.println("wrong designator!");
                return false;
            }
        }
        System.out.println("what R U doing in factor?!");
        return false;
    }
    //end !;p
    //3vom ordibehesht!
    private static boolean Expression()
    {
        if(input.equals("-"))
        {
            getInput();
            if(input.equals("identifier")||input.equals("charConst") || input.equals("number") ||input.equals("new") || input.equals("("))
            {
                if(Term())
                {
                    getInput();
                    if(input.equals("+") || input.equals("-")) //AddOp!
                    {
                        while(Addop())
                        {
                            if(input.equals("identifier")||input.equals("charConst") || input.equals("number") ||input.equals("new") || input.equals("("))
                            {
                                if(Term())
                                {
                                    if(input.equals("+") || input.equals("-"))
                                    {
                                        continue;
                                    }
                                    else if(input.equals(";") || input.equals(",") || input.equals("==") ||
                                            input.equals("!=")||input.equals("<")||input.equals("<=")||input.equals(">")||input.equals(">="))//follow(Expression)
                                    {
                                        return true;
                                    }
                                    else
                                    {
                                        System.out.println("wrong exp");
                                        return false;
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("missing term!");
                                return false;
                            }
                        }
                        return false;
                    }
                    else
                    {
                        System.out.println("wrong exp");
                        return false;
                    }
                }
                else
                {
                    System.out.println("wrong Term in expression!");
                    return false;
                }
            }
            else
            {
                System.out.println("no term in expression");
                return false;
            }
        }
        else
        {
            getInput();
            if(input.equals("identifier")||input.equals("charConst") || input.equals("number") ||input.equals("new") || input.equals("("))
            {
                if(Term())
                {
                    getInput();
                    if(input.equals("+") || input.equals("-")) //AddOp!
                    {
                        while(Addop())
                        {
                            if(input.equals("identifier")||input.equals("charConst") || input.equals("number") ||input.equals("new") || input.equals("("))
                            {
                               if(Term())
                               {
                                   if(input.equals("+") || input.equals("-"))
                                   {
                                       continue;
                                   }
                                   else if(input.equals(";") || input.equals(",") || input.equals("==") ||
                                        input.equals("!=")||input.equals("<")||input.equals("<=")||input.equals(">")||input.equals(">="))//follow(Expression)
                                   {
                                       return true;
                                   }
                                   else
                                   {
                                       System.out.println("wrong exp");
                                       return false;
                                   }
                               }
                            }
                            else
                            {
                                System.out.println("missing term!");
                                return false;
                            }
                        }
                        return false;
                    }
                    else
                    {
                        System.out.println("wrong exp");
                        return false;
                    }
                }
                else
                {
                    System.out.println("wrong Term in expression!");
                    return false;
                }
            }
            else
            {
                System.out.println("no term in expression");
                return false;
            }
        }
    }
     private static boolean CondFact()
    {
        if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                || input.equals("number") ||input.equals("new") || input.equals("("))//first(exp)
        {
            if(Expression())
            {   getInput();
                if(Relop())
                {
                    if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                            || input.equals("number") ||input.equals("new") || input.equals("("))//first(exp)
                    {
                        if(Expression())
                        {
                            return true;
                        }
                        else
                        {
                            System.out.println("wrong 2'nd exp in condition!");
                            return false;
                        }
                    }
                    else
                    {
                        System.out.println("missing 2'nd conditional exp!");
                        return false;
                    }
                }
                else
                {
                    System.out.println("missing/wrong Relop!");
                    return false;
                }
            }
            else
            {
                System.out.println("wrong 1'st exp in condition!");
                return false;
            }
        }
        else
        {
            System.out.println("missing conditional expression!");
            return false;
        }
    }
    private static boolean Condition()
    {
        if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                || input.equals("number") ||input.equals("new") || input.equals("("))//first(CondTerm)
        {
            if(CondTerm())
            {
                if(input.equals("||"))//||
                {
                    getInput();
                    if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                            || input.equals("number") ||input.equals("new") || input.equals("("))//first(CondTerm)
                    {
                        while(CondTerm())
                        {
                            if(input.equals("||"))
                            {
                                getInput();
                                if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                                        || input.equals("number") ||input.equals("new") || input.equals("("))//first(CondTerm)
                                {
                                    continue;
                                }
                                else
                                {
                                    System.out.println("no CondTerm after||!");
                                    return false;
                                }
                            }
                            else if(input.equals(")"))//follow(Condition)
                            {
                                return true;
                            }
                            else
                            {
                                System.out.println("wrong conditon!/no condterm");
                                return false;
                            }
                        }
                        System.out.println("wrong CondTerm!");
                        return false;
                    }
                    else
                    {
                        System.out.println("no CondTerm after ||!");
                        return false;
                    }
                }
                else if(input.equals(")"))//follow(Condition)
                {
                    return true;
                }
                else
                {
                    System.out.println("wrong CondTerm");
                    return false;
                }
            }
            else
            {
                System.out.println("wrong CondTerm!");
                return false;
            }
        }
        else
        {
            System.out.println("wrong Condition/no CondTerm!");
            return false;
        }
    }
    private static boolean Statement()
    {
        if(input.equals("identifier"))
        {
            if(Designator())
            {
                if(input.equals("="))
                {
                    getInput();
                    if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                            || input.equals("number") ||input.equals("new") || input.equals("("))//first(exp)
                    {
                        if(Expression())
                        {
                            if(input.equals(";"))
                            {
                                getInput();
                                return true;
                            }
                            else
                            {
                                System.out.println("missing ; in statement!");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("wrong exp in satement!");
                            return false;
                        }
                    }
                    else
                    {
                        System.out.println("missing exp");
                        return false;
                    }
                }
                else if(input.equals("("))
                {
                    getInput();
                    if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                            || input.equals("number") ||input.equals("new") || input.equals("("))//first(actParts)
                    {
                        if(Expression())
                        {
                            if(input.equals(")"))
                            {
                                getInput();
                                if(input.equals(";"))
                                {
                                    getInput();
                                    return true;
                                }
                                else
                                {
                                    System.out.println("missing ;");
                                    return false;
                                }
                            }
                            else
                            {
                                System.out.println("missing )");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("wrong expression");
                            return false;
                        }
                    }
                    else
                    {
                        if(input.equals(")"))
                        {
                            getInput();
                            if(input.equals(";"))
                            {
                                getInput();
                                return true;
                            }
                            else
                            {
                                System.out.println("missing ;");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("missing )");
                            return false;
                        }
                    }
                }
                else if(input.equals("++"))
                {
                    getInput();
                    if(input.equals(";"))
                    {
                        getInput();
                        return true;
                    }
                    else
                    {
                        System.out.println("missing ;");
                        return false;
                    }

                }
                else if(input.equals("--"))
                {
                    getInput();
                    if(input.equals(";"))
                    {
                        getInput();
                        return true;
                    }
                    else
                    {
                        System.out.println("missing ;");
                        return false;
                    }
                }
            }
            else
            {
                System.out.println("wrong designator in statement!");
                return false;
            }
        }
        else if(input.equals("if"))
        {
            getInput();
            if(input.equals("("))
            {
                getInput();
                if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                        || input.equals("number") ||input.equals("new") || input.equals("("))//first(Condition)
                {
                    if (Condition())
                    {
                        if(input.equals(")"))
                        {
                            getInput();
                            if(input.equals("while") ||input.equals("break")||input.equals("return")
                                    || input.equals(";") ||input.equals("print") || input.equals("read")
                                    || input.equals("{") || input.equals("if") || input.equals("identifier"))//first(statement)
                            {
                               if(Statement())
                               {
                                   if(input.equals("else"))
                                   {
                                       if(input.equals("while") ||input.equals("break")||input.equals("return")
                                               || input.equals(";") ||input.equals("print") || input.equals("read")
                                               || input.equals("{") || input.equals("if") || input.equals("identifier"))//first(statement)
                                       {
                                           if(Statement())
                                           {
                                               return true;
                                           }
                                       }
                                       else
                                       {
                                           System.out.println("missing statement on else if!");
                                           return false;
                                       }
                                   }
                                   else
                                   {   //check follow?!
                                       return true;
                                   }
                               }
                            }
                            else
                            {
                                System.out.println("missing statement!in if!");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("missing ) in if!");
                            return false;
                        }
                    }
                    else
                    {
                        System.out.println("wrong condition in if");
                        return false;
                    }
                }
                else
                {
                    System.out.println("missing condition in if");
                    return false;
                }
            }
            else
            {
                System.out.println("missing ( in if");
                return false;
            }
        }
        else if(input.equals("while"))
        {
            getInput();
            if(input.equals("("))
            {
                getInput();
                if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                        || input.equals("number") ||input.equals("new") || input.equals("("))//first(exp)
                {
                    if(Condition())
                    {
                        if(input.equals(")"))
                        {
                            getInput();
                            if(input.equals("while") ||input.equals("break")||input.equals("return")
                                    || input.equals(";") ||input.equals("print") || input.equals("read")
                                    || input.equals("{") || input.equals("if") || input.equals("identifier"))//first(statement)
                            {
                                if(Statement())
                                {
                                    return true;
                                }
                                else
                                {
                                    System.out.println("wrong while statement!");
                                    return false;
                                }
                            }
                            else
                            {
                                System.out.println("no statement?! in while");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("missing ) on while");
                        }
                    }
                    else
                    {
                        System.out.println("wrong condition im while!");
                        return false;
                    }
                }
                else
                {
                    System.out.println("missing Condition on while");
                    return false;
                }

            }
            else
            {
                System.out.println("missing (in while");
                return false;
            }
        }
        else if(input.equals("break"))
        {
            getInput();
            if(input.equals(";"))
            {
                getInput();
                return true;
            }
            else
            {
                System.out.println("missing ; in break");
                return false;
            }
        }
        else if(input.equals("return"))
        {
            getInput();
            if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                    || input.equals("number") ||input.equals("new") || input.equals("("))//first(exp)
            {
                if(Expression())
                {
                    if(input.equals(";"))
                    {
                        getInput();
                        return true;
                    }
                    else
                    {
                        System.out.println("miising ; in return!");
                        return false;
                    }
                }
                else
                {
                    System.out.println("wrong return exp!");
                    return false;
                }
            }
            else
            {
                if(input.equals(";"))
                {
                    getInput();
                    return true;
                }
                else
                {
                    System.out.println("miising ; in return!");
                    return false;
                }
            }
        }
        else if(input.equals("print"))
        {
            getInput();
            if(input.equals("("))
            {
                getInput();
                if(input.equals("-") ||input.equals("identifier")||input.equals("charConst")
                        || input.equals("number") ||input.equals("new") || input.equals("("))//first(exp)
                {
                    getInput();
                    if(Expression())
                    {
                        if(input.equals(","))
                        {
                            getInput();
                            if(input.equals("number"))
                            {
                                getInput();
                                if(input.equals(")"))
                                {
                                    getInput();
                                    if(input.equals(";"))
                                    {
                                        getInput();
                                        return true;
                                    }
                                    else
                                    {
                                        System.out.println("missing ;");
                                        return false;
                                    }
                                }
                                else
                                {
                                    System.out.println("missing ) in print!");
                                    return false;
                                }
                            }
                            else
                            {
                                System.out.println("missing num in print!");
                                return false;
                            }
                        }
                        else
                        {
                            getInput();
                            if(input.equals(")"))
                            {
                                getInput();
                                if(input.equals(";"))
                                {
                                    getInput();
                                    return true;
                                }
                                else
                                {
                                    System.out.println("missing ;");
                                    return false;
                                }
                            }
                            else
                            {
                                System.out.println("missing ) in print!");
                                return false;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("wrong exp in print!");
                        return false;
                    }
                }
                else
                {
                    System.out.println("missing exp while print!");
                    return false;
                }
            }
            else
            {
                System.out.println("missing ( in print!");
                return false;
            }
        }
        else if(input.equals("read"))
        {
            getInput();
            if(input.equals("("))
            {
                getInput();
                if(input.equals("identifier"))
                {
                    if(Designator())
                    {
                        if(input.equals(")"))
                        {
                            getInput();
                            if(input.equals(";"))
                            {
                                getInput();
                                return true;
                            }
                            else
                            {
                                System.out.println("missing ; in statement!");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("missing ) in statement!");
                            return false;
                        }
                    }
                    else
                    {
                        System.out.println("wrong Designator in statement!");
                    }
                }
                else
                {
                    System.out.println("no Designator!");
                    return false;
                }
            }
            else
            {
                System.out.println("missing (");
                return false;
            }
        }
        else if(input.equals(";"))
        {
            getInput();
            return true;
        }
        else if(input.equals("{"))//first(Block)
        {
            if(Block())
                return true;
        }
        else
        {
            System.out.println("wrong statement sig!");
            return false;
        }
    }
    //end sevom ordibehesht!!
    
     //$$sarah

    private static boolean ClassDecl(){
          if(input.equals("identifier")){
              getInput();
              if(input.equals("{")){
                  while(VarDecl()){

                  }
                  if(input.equals("}"))
                      return true;
                  else{
                      System.out.println("not matching {} in line"+token.getLine()+"an col"+token.getCol());
                      return false;
                  }
              }
              else{
                  System.out.println("lost { in line"+token.getLine()+"an col"+token.getCol());
                  return false;
              }
          }
        else{
              System.out.println("enter identifier in line" + token.getLine() + " and col" + token.getCol());
              return false;
          }
    }
    private static boolean ArgsForm(){
        boolean result;
        if(Type()){
            if(input.equals("identifier")){
                result=true;
                getInput();
                if(input.equals(",")){
                    getInput();
                    while(Type()){
                        if(input.equals("identifier")) {
                            getInput();
                        }
                        else{
                            result=false;
                            System.out.println("missing identifier in line"+token.getLine()+"and col"+token.getCol());
                            break;
                        }
                    }
                    return result;
                }
                return result;

            }
            else{
                System.out.println("missing identifier in line"+token.getLine()+"and col"+token.getCol());
                return false;
            }
        }
        else{
            System.out.println("missing type on identifier in line"+token.getLine()+"and col"+token.getCol());
            return false;
        }
    }

    private static boolean ActParse(){
         boolean result;
        if(Expression()){
            result=true;
            if(input.equals(",")) {
                while(input.equals(",")){
                    if(Expression()){
                        continue;
                    }
                    else{
                        result=false;
                        System.out.println("expression expected in line"+token.getLine()+ "and col"+token.getCol());
                        break;
                    }
                }
                return result;
            }
            else{
                return result;
            }
        }
        else{
            result=false;
        }

        return result;
    }

    private static boolean Term(){
          boolean result=false;
          if(Factor()){
              result=true;
              getInput();
              if(input.equals("/")||input.equals("%")||input.equals("*")){
                  if(Mulop()){
                      while(Mulop()){
                          if(Factor()){
                              continue;
                          }
                          else{
                              //error occures:|
                          }
                      }
                  }
                  else{
                      //error occures:|
                  }
              }
              else{
                  //do nothing. no loop occures here.
              }
          }
        else{
              result=false;
          }
        getInput();
        return result;
    }

    private static boolean Designator(){
        if(input.equals("identifier")){
           boolean result=true;
            getInput();
            while(input.equals(".")||Expression()){
                if(input.equals(".")){
                    getInput();
                    if(input.equals("identifier")){
                        continue;
                    }
                    else{
                        System.out.println("missing identifier in line"+token.getLine()+"and col"+token.getCol());
                        result=false;
                        break;
                    }
                }
                else{//expression is true!
                    continue;
                }
            }
            getInput();
            return result;
        }
        return false;
    }

    //$$end sarah

    
}//end class
