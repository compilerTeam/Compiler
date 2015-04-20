
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
}
