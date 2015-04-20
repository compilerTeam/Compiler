package com.company;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 3/8/15
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyScanner
{
    private static String inputString = new String();
    private static int stringIndexCounter = 0;
    private static int stringLineCounter = 0;
    private static int stringColCounter = 0;

    static public void start()
    {
        //open the file
        File name = new File("scanner.txt");
        try
        {
            FileInputStream fileInput = new FileInputStream("scanner.txt");
            int r;
            while ((r = fileInput.read()) != -1)
            {
                char c = (char) r;
                inputString += c;
            }
            fileInput.close();

        }
        catch (Exception e)
        {
            System.out.println("error");
        }

    }
    public static Token advance()
    {
        Token rToken = new Token();
        char ch;
        ch = getInput();
        String number="";
        String literal;
        char constChar;
        //start
        ////////////////////////////////////////////////////////////new
        if(stringIndexCounter >= (inputString.length()))
        {
            rToken . setString("eof");
        }
        ///////////////////////////////////////////////////////////////



        if(ch==' '){
            while(ch==' '|| ch=='\t'){
                ch=getInput();
                //ch=glanceInput();
            }
        }
        while(ch==13|| ch==10)
        {
            if(ch==13){
                ch=getInput() ;
                if(ch==10)
                    ch=getInput();
                stringColCounter=0;
                stringLineCounter++;

            }
            else if(ch==10){
                ch=getInput() ;
                if(ch==13)
                    ch=getInput();
                stringColCounter=0;
                stringLineCounter++;

            }
        }
        switch(ch){

            case '=':    //=
                //advance()

                ch =glanceInput();

                if(ch=='=')
                {
                    ch=getInput();
                    rToken.setString(" ==");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                //add '==' to symbol table
                else if(ch !='=')
                {
                    rToken.setString(" =");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                break;
            case '<':    //<
                ch =getInput();
                if(ch=='=')
                {
                    rToken.setString("<=");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                //add '<=' to symbol table
                else
                {
                    rToken.setString("<");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                break;
            case '>':
                ch =getInput();
                if(ch=='=')
                {
                    rToken.setString(">=");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                //add '>=' to symbol table
                else
                {
                    rToken.setString(">");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                break;
            case '%':

                rToken.setString("%");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator
                //mod %
                //add ch to file
                //advance
                break;
            case '*':
                rToken.setString("*");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator
                //add ch to file
                //advance
                break;
            case '/':
                ch =glanceInput();
                if(ch=='/')
                {
                    rToken.setString("//");
                    rToken.setLine(stringLineCounter);
                    rToken.setCol(stringColCounter);

                    while(ch!=10 && ch!=13 && stringIndexCounter!=inputString.length())
                        ch=getInput();
                    //read to the end of line, it's comment!
                    //advance
                }
                else
                {
                    //add / to file
                    rToken.setString("/");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                    //advance
                }
                break;
            case '+':

                if(ch=='+')
                {
                    ch=getInput();
                    rToken.setString(" ++");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                //add '==' to symbol table
                else if(ch !='+')
                {
                    rToken.setString(" +");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                }
                break;

            case '-':
                ch =getInput();
                if(ch=='-')
                {
                    //add -- to file
                    rToken.setString("--");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                    //advance
                }
                else
                {
                    //add - to file
                    rToken.setString("-");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator
                    //advance
                }
                break;
            case '0':    //numbers 0-9
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                //read character
                number+=ch;
                ch=glanceInput();
                while(ch>=48 && ch<=57)
                {
                    //get next char at first kho!
                    number+=ch; //value is saved as an integer
                    ch=getInput();
                    rToken.setString("number");
                }
                //add number to symbol table
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(1);//operator
                rToken.setVal(number);
                number="";
                break;



            case ')':
                rToken.setString(")");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case '(':
                rToken.setString("(");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case '[':
                rToken.setString("[");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case ']':
                rToken.setString("]");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case '{':
                rToken.setString("{");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case '}':
                rToken.setString("}");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;

            case ':':
                rToken.setString(":");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case '.':
                rToken.setString(".");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case ';':
                rToken.setString(";");
                rToken.setCol(stringColCounter);
                rToken.setLine(stringLineCounter);
                rToken.setType(4);//operator

                //add ch to file
                //advance
                break;
            case 38:        //and
                ch=glanceInput();
                if(ch=='&')
                {
                    getInput();
                    rToken.setVal(" ");
                    rToken.setString("&&");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator  //add && to file
                    //advance
                }
                else
                {
                    //error
                    stringIndexCounter--;
                    rToken.setString("error");
                    rToken.setString(""+ch);
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    //advance
                }
                break;
            case '|':
                ch =glanceInput();
                if(ch=='|')
                {
                    getInput();
                    rToken.setString("||");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setType(4);//operator   //add || to file
                    //advance
                }
                else
                {
                    //error
                    rToken.setString("error");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    //advance
                }
                break;

//                case ' ':
//                    ch=glanceInput();
//                    while(ch==' '|| ch=='\t')
//                    {
//                        ch=getInput();
//                        ch=glanceInput();
//                    }
//                    return null;
            case '\'':        //this is ' !
                ch=glanceInput();
                String charConst = "'";
                if((ch>='A' && ch<='Z') || (ch>='a' && ch<='z') || (ch>='0'&& ch<='9'))
                {
                    charConst += ch;
                    getInput();
                    ch = glanceInput();
                    while((ch>='A' && ch<='Z') || (ch>='a' && ch<='z') ||( ch=='_')|| (ch>='0'&& ch<='9'))
                    {

                        charConst += ch;
                        getInput();
                        //read next character
                        ch = glanceInput();
                    }
                    if(ch == '\'')
                    {
                        rToken.setLine(stringLineCounter);
                        rToken.setCol(stringColCounter);
                        rToken.setType(2);
                        rToken.setString("Terminal");
                        rToken.setVal(charConst.substring(1,charConst.length()));
                    }
                    else
                    {
                        rToken.setLine(stringLineCounter);
                        rToken.setCol(stringColCounter);
                        rToken.setType(-1);
                        rToken.setString("none");
                        rToken.setVal(charConst);
                    }
                }
                break;

            default:
                if((ch>='A' && ch<='Z') || (ch>='a' && ch<='z'))
                {
                    String name=""+ch;
                    ch = glanceInput();
                    while((ch>='A' && ch<='Z') || (ch>='a' && ch<='z') ||( ch=='_')|| (ch>='0'&& ch<='9')){
                        name+=ch;
                        ch= getInput();
                        ch = glanceInput();
                        //read next character
                    }
                    if(isKeyword(name))
                    {
                        rToken.setString(name);
                        rToken.setLine(stringLineCounter);
                        rToken.setCol(stringColCounter);
                        rToken.setType(3);  //keyword
                    }
                    else
                    {
                        rToken.setVal(name);
                        rToken.setLine(stringLineCounter);
                        rToken.setCol(stringColCounter);
                        rToken.setType(0);
                        rToken.setString("identifier");
                    }
                    name="";
                }
                else
                {
                    //System.out.println("SIC:" + stringIndexCounter);
                    number="";
                    rToken.setString("Error");
                    rToken.setCol(stringColCounter);
                    rToken.setLine(stringLineCounter);
                    rToken.setVal(""+ch);
                    return rToken;
                    // add null in symbol table, error has occured
                }

                break;
        }

        return rToken;
    }
    public void scan()
    {
        while(stringIndexCounter < (inputString.length()))
        {
            System.out.println(advance());
        }
    }
    private static char getInput()
    {
        //System.out.println("string counter:" +stringIndexCounter + "length " + inputString.length());
        char c=' ';
        stringColCounter++;
        if(stringIndexCounter <= inputString.length()-1)
        {

            c = inputString.charAt(stringIndexCounter++);
            //System.out.println("the character is:" + c);
        }

        return c;
    }
    private static char glanceInput()
    {

        char c=' ';

        if(stringIndexCounter <= inputString.length()-1)
        {

            c = inputString.charAt(stringIndexCounter);

        }

        return c;
    }
    private static boolean isKeyword(String tokenName)
    {
        if(tokenName.length()>=4)
            switch(tokenName.charAt(3))
            {
                case 'a':
                    if(tokenName.equals("break") || tokenName.equals("float") || tokenName.equals("final"))
                        return true;
                    else
                        return false;
                case 'r':
                    if(tokenName.equals("char"))
                        return true;
                    return false;

                case 's':
                    if(tokenName.equals("class"))
                        return true;
                    else
                        return false;

                case 'e':
                    if(tokenName.equals("else"))
                        return true;
                    else
                        return false;

                case 'd':
                    if(tokenName.equals("read") || tokenName.equals("void"))
                        return true;
                    else
                        return false;

                case ' ':
                    if(tokenName.equals("if") || tokenName.equals("new") || tokenName.equals("int"))
                        return true;
                    else
                        return false;

                case 'n':
                    if(tokenName.equals("print"))
                        return true;
                    return false;

                case 'l':
                    if(tokenName.equals("while"))
                        return true;
                    return false;

                case 'c':
                    if(tokenName.equals("function"))
                        return true;
                    return false;

                case 'u':
                    if(tokenName.equals("return"))
                        return true;
                    return false;

                default:
                    return false;

            }
        else{
            if(tokenName.equals("if")|| tokenName.equals("int")||tokenName.equals("new"))
                return true;
            else
                return false;
        }
    }
    public void writeOnFile(Token token)
    {
        ObjectOutputStream output;
        try {
            output = new ObjectOutputStream(new FileOutputStream("output.ser"));
            output.writeObject(token);
            //output.flush();
            output.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public void readFromFile() throws IOException
    {
        Token token = new Token();
        ObjectInputStream input;
        input = new ObjectInputStream(new FileInputStream("output.ser"));
        try
        {
            token = (Token) input.readObject();
            while(token!= null)
            {
                token = (Token)input.readObject();
                System.out.println(token);
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
