import java.util.*;
import java.io.*;

public class AnalizadorSintactico
{
    static char ntermnl[],termnl[];
    static int ntlen,tlen;
    static String grmr[][],fst[],flw[];

    public static class translation {

        private String original;
        private char trad;

        public translation(String original, char trad) {
            this.original = original;
            this.trad = trad;
        }

        public String getoriginal() {
            return original;
        }

        public char gettrad() {
            return trad;
        }

    }

    void errorSintaxis(){};

    void emparejar(){};

    void prog(){
        if(token.equals("function") || token.equals("var"))
            prog_fn();
        else
            errorSintaxis("function", "var");
    }

    void prog_fn(){
        if(token.equals("function"))

        else if(token.equals("var"))
            pass
        else
            errorSintaxis("function", "var");
    }

    void main_prog(){
        if(token.equals("var"))
        else
            errorSintaxis("function", "var");
    }


    void main_stmt(){
        if(token.equals("print") || token.equals("input") || token.equals("when") || token.equals("if") || token.equals("unless") || token.equals("while") || token.equals("return") || token.equals("until") || token.equals("loop") || token.equals("do") || token.equals("repeat") || token.equals("for") || token.equals("end") || token.equals("next") || token.equals("break") || token.equals("id") || token.equals("tk_decremento") || token.equals("tk_incremento"))

        else if(token.equals("end"))

        else
            errorSintaxis("print", "input", "when", "if", "unless", "while", "return", "until", "loop", "do", "repeat", "for", "end", "next", "break", "id", "tk_decremento", "tk_incremento", "end");
    }

    void stmt(){
        if(token.equals("print"))

        else if(token.equals("input"))

        else if(token.equals("when"))

        else if(token.equals("if"))

        else if(token.equals("unless"))

        else if(token.equals("while"))

        else if(token.equals("return"))

        else if(token.equals("until"))

        else if(token.equals("loop"))

        else if(token.equals("do"))

        else if(token.equals("repeat"))

        else if(token.equals("for"))

        else if(token.equals("end"))

        else if(token.equals("next"))

        else if(token.equals("break"))

        else if(token.equals("id"))

        else if(token.equals("tk_incremento"))

        else if(token.equals("tk_decremento"))

        else
            errorSintaxis("input", "when", "if", "unless", "while", "return", "until", "loop", "do", "repeat", "for", "end", "next", "break", "id", "tk_decremento", "tk_incremento");

    }

    void DO_sig(){
        if(token.equals("while"))

        else if(token.equals("until"))

    }

    void signo(){
:=
        if(token() == )
+=

-=


*=

/=

%=
        ++
                --

    }

    void fn_decl_list(){
        TK_FUNCTION
        if(token() == )
    }

    void var_decl(){
        ID
        if(token() == )
    }

    void cont_data(){
,
        if(token() == )

            ; )
    }

    void stmt_block(){
        TK_PRINT TK_INPUT TK_WHEN TK_IF TK_UNLESS TK_WHILE TK_RETURN TK_UNTIL TK_LOOP TK_DO TK_REPEAT TK_FOR TK_END TK_NEXT TK_BREAK ID -- ++
                //{
        if(token() == )
    }

    void stmt_mas(){
        TK_PRINT TK_INPUT TK_WHEN TK_IF TK_UNLESS TK_WHILE TK_RETURN TK_UNTIL TK_LOOP TK_DO TK_REPEAT TK_FOR TK_END TK_NEXT TK_BREAK ID -- ++
        if(token() == )

    }
    //}

    void lexpr(){
        TK_NOT TK_NUM TK_BOOL ID ++ -- ( FID
        if(token() == )
    }

    public void lexpr_and(){
        if(token.equals())
        {
            emparejar(and);
            nexpr();
            lexpr_and();
        }
        else if(token.equals(tk_puntoycoma) || token.equals(tk_par_der) || token.equals(tk_coma)){
        }
        else errorSintaxis(and, tk_par_der, tk_coma);
    }

  public void nexprComilla(){
      if(token.equals("or"))
        
        {
        lexpr_or();
        
      }
     else if(token.equals("and")){
      
        
        lexpr_and();
        
      }
     
      else errorStintaxis("or","and");
    
    
    
    
  }  
  
   public void lexpr_and(){
      if(token.equals("and"))
        
        {
        emparejar("and");
        nexpr();
        lexpr_and();
      
        
      }
     else if(token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma")){
      
        
        
        
      }
     
      else errorStintaxis("and", "tk_par_der", "tk_coma");
    
    
    
    
  }  
  
  
  public void lexpr_or(){
      if(token.equals("or"))
        
        {
        emparejar("or");
        nexpr();
        lexpr_or();
      
        
      }
     else if(token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma")){
      
        
        
        
      }
     
      else errorStintaxis("or", "tk_par_der", "tk_coma");
    
    
    
    
  }  
  
   public void nexpr(){
      if(token.equals("num") || token.equals("bool") || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") )
        
        {
        
        rexpr();
      
        
      }
     else if(token.equals("not")){
        emparejar("not");
        emparejar("tk_par_izq");
       lexpr();
       emparejar("tk_par_der");
        
        
        
      }
     
      else errorStintaxis("num", "bool" ,"id", "tk_incremento", "tk_decremento","tk_par_izq", "fid,not");
    
    
    
    
  }  
   public void rexpr(){
      if(token.equals("num") || token.equals("bool") || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") )
        
        {
        
     simple_expr();
      sig();
        
      }
     
      else errorStintaxis("num", "bool" ,"id", "tk_incremento", "tk_decremento","tk_par_izq", "fid");
    
    
    
    
  }  
    public void sig(){
      if(token.equals("tk_menor") )
        
        {
        emparejar("tk_menor");
        simple_expr();
        
       
      }
      
      else if(token.equals("tk_igualdad")){
        emparejar("tk_igualdad");
        simple_expr();
        
        
        
      }
       else if(token.equals("tk_menor_igual")){
        emparejar("tk_menor_igual");
        simple_expr();
        
        
        
      }
      else if(token.equals("tk_mayor")){
        emparejar("tk_mayor");
        simple_expr();
        
        
        
      }
      else if(token.equals("tk_mayor_igual")){
        emparejar("tk_mayor_igual");
        simple_expr();
        
        
        
      }
      else if(token.equals("tk_diferente")){
        emparejar("tk_diferente");
        simple_expr();
        
        
        
      }
     
      
     
     
      else errorStintaxis("tk_menor", "tk_igualdad", "tk_menor_igual", "tk_mayor","tk_mayor_igual","tk_diferente");
    
    
    
    
  }  
  
  
  public void simple_expr(){
      if(token.equals("num") || token.equals("bool") || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") )
        
        {
        
        term();
        t2();
      
        
      }
     
      else errorStintaxis("num", "bool" ,"id", "tk_incremento", "tk_decremento","tk_par_izq", "fid");
    
    
    
    
  }  
  
  
  public void t2(){
      if(token.equals("tk_mas") )
        
        {
        emparejar("tk_mas");
        term();
        t2();
       
      }
      
      else if(token.equals("tk_menos")){
        emparejar("tk_menos");
        term();
        t2();
        
        
        
      }
     
      
     
     else if(  token.equals("tk_menor") || token.equals("tk_igualdad") || token.equals("tk_menor_igual")
        || token.equals("tk_mayor") || token.equals("tk_mayor_igual") || token.equals("diferente") || token.equals("or") 
        || token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma") ||    token.equals("and")   
            )
        
        {
        
        
      }
      else errorStintaxis("tk_mas","tk_menos","tk_menor","tk_igualdad","tk_menor_igual","tk_mayor","tk_mayor_igual","diferente","or","tk_puntoycoma","tk_par_der","tk_coma","and");
    
    
    
    
  }  
  
  
  public void term(){
      if(token.equals("num") || token.equals("bool") || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") )
        
        {
        
        factor();
        fa();
      
        
      }
     
      else errorStintaxis("num", "bool" ,"id", "tk_incremento", "tk_decremento","tk_par_izq", "fid");
    
    
    
    
  }  
   public void fa(){
      if(token.equals("tk_mul") )
        
        {
        emparejar("tk_mul");
        factor();
        fa();
        
      }
      
      else if(token.equals("tk_div")){
         emparejar("tk_div");
        factor();
        fa();
        
        
        
        
      }
     
      else if(token.equals("tk_mod")){
         emparejar("tk_mod");
        factor();
        fa();
        
      }
     
     else if( token.equals("tk_mas") || token.equals("tk_menos") || token.equals("tk_menor") || token.equals("tk_igualdad") || token.equals("tk_menor_igual")
        || token.equals("tk_mayor") || token.equals("tk_mayor_igual") || token.equals("diferente") || token.equals("or") 
        || token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma") ||    token.equals("and")   
            )
        
        {
        
        
      }
      else errorStintaxis("tk_mul","tk_div","tk_mod","tk_mas","tk_menos","tk_menor","tk_igualdad","tk_menor_igual","tk_mayor","tk_mayor_igual","diferente","or","tk_puntoycoma","tk_par_der","tk_coma","and");
    
    
    
    
  }  
  
   public void factor(){
      if(token.equals("fid") )
        
        {
        emparejar("fid");
        emparejar("tk_par_izq");
        lexpr();
        f2();
        emparejar("tk_par_der");
        
      }
      
      else if(token.equals("tk_par_izq")){
        emparejar("tk_par_izq");
        lexpr();
        emparejar("tk_par_der");
        
        
        
      }
     
      else if(token.equals("tk_decremento")){
        emparejar("tk_decremento");
       	 emparejar("id");
                      
      }
     else if(token.equals("tk_incremento")){
        emparejar("tk_incremento");
       	 emparejar("id");
                      
      }
     
      else if(token.equals("id")){
        emparejar("id");
       	 s();
                      
      }
      else if(token.equals("bool")){
        emparejar("bool");
       	        
      }
     else if(token.equals("num")){
        emparejar("num");
       	        
      }
      else errorStintaxis("fid", "tk_par_izq", "tk_incremento", "id", "bool", "num","tk_decremento");
    
    
    
    
  }  
  
   public void s(){
      if(token.equals("tk_mul") || token.equals("tk_div") || token.equals("tk_mod") || token.equals("tk_mas") || token.equals("tk_menos") || token.equals("tk_menor") || token.equals("tk_igualdad") || token.equals("tk_menor_igual")
        || token.equals("tk_mayor") || token.equals("tk_mayor_igual") || token.equals("diferente") || token.equals("or") || token.equals("tk_puntoycoma") || 
          token.equals("token_par_der") || token.equals("tk_coma") || token.equals("and") )
        
        {
        
        
      }
      
      else if(token.equals("tk_decremento")){
        emparejar("tk_decremento");
        
        
        
      }
     else if(token.equals("tk_incremento")){
        emparejar("tk_incremento");
        
        
        
      }
      else errorStintaxis("tk_mul", "tk_div","tk_mod","tk_mas","tk_menos","tk_menor","tk_igualdad","tk_menor_igual","tk_mayor","tk_mayor_igual","diferente","or", "tk_puntoycoma", "tk_pard_der" , "tk_coma", "and","tk_decremento","tk_incremento");
    
    
    
    
  }  
  	
  public void f2(){
      if(token == "tk_par_der" ){
        
        
      }
      
      else if(token == "tk_coma"){
        emparejar("tk_coma");
        lexpr();
        f2();
        
      }
      else errorStintaxis("tk_par_der","tk_coma");
      
      
    }
     
  }  
   public void rexpr(){
      if(token.equals(num) || token.equals(bool) || token.equals(id)   || token.equals(tk_incremento) || token.equals(tk_decremento)  || token.equals(tk_par_izq) || token.equals(fid) )
        
        {
        
     simple_expr();
      sig();
        
      }
     
      else errorStintaxis(num, bool ,id, tk_incremento, tk_decremento,tk_par_izq, fid);
    
    
    
    
  }  
    public void sig(){
      if(token.equals(tk_menor) )
        
        {
        emparejar(tk_menor);
        simple_expr();
        
       
      }
      
      else if(token.equals(tk_igualdad)){
        emparejar(tk_igualdad);
        simple_expr();
        
        
        
      }
       else if(token.equals(tk_menor_igual)){
        emparejar(tk_menor_igual);
        simple_expr();
        
        
        
      }
      else if(token.equals(tk_mayor)){
        emparejar(tk_mayor);
        simple_expr();
        
        
        
      }
      else if(token.equals(tk_mayor_igual)){
        emparejar(tk_mayor_igual);
        simple_expr();
        
        
        
      }
      else if(token.equals(tk_diferente)){
        emparejar(tk_diferente);
        simple_expr();
        
        
        
      }
     
      
     
     
      else errorStintaxis(tk_menor, tk_igualdad, tk_menor_igual, tk_mayor,tk_mayor_igual,tk_diferente);
    
    
    
    
  }  
  
  
  public void simple_expr(){
      if(token.equals(num) || token.equals(bool) || token.equals(id)   || token.equals(tk_incremento) || token.equals(tk_decremento)  || token.equals(tk_par_izq) || token.equals(fid) )
        
        {
        
        term();
        t2();
      
        
      }
     
      else errorStintaxis(num, bool ,id, tk_incremento, tk_decremento,tk_par_izq, fid);
    
    
    
    
  }  
  
  
  public void t2(){
      if(token.equals(tk_mas) )
        
        {
        emparejar(mas);
        term();
        t2();
       
      }
      
      else if(token.equals(tk_menos)){
        emparejar(menos);
        term();
        t2();
        
        
        
      }
     
      
     
     else if(  token.equals(tk_menor) || token.equals(tk_igualdad) || token.equals(tk_menor_igual)
        || token.equals(tk_mayor) || token.equals(tk_mayor_igual) || token.equals(diferente) || token.equals(or) 
        || token.equals(tk_puntoycoma) || token.equals(tk_par_der) || token.equals(tk_coma) ||    token.equals(and)   
            )
        
        {
        
        
      }
      else errorStintaxis(tk_mas,tk_menos,tk_menor,tk_igualdad,tk_menor_igual,tk_mayor,tk_mayor_igual,diferente,or,tk_puntoycoma,tk_par_der,tk_coma,and);
    
    
    
    
  }  
  
  
  public void term(){
      if(token.equals(num) || token.equals(bool) || token.equals(id)   || token.equals(tk_incremento) || token.equals(tk_decremento)  || token.equals(tk_par_izq) || token.equals(fid) )
        
        {
        
        factor();
        fa();
      
        
      }
     
      else errorStintaxis(num, bool ,id, tk_incremento, tk_decremento,tk_par_izq, fid);
    
    
    
    
  }  
   public void fa(){
      if(token.equals(tk_mul) )
        
        {
        emparejar(tk_mul);
        factor();
        fa();
        
      }
      
      else if(token.equals(tk_div)){
         emparejar(tk_div);
        factor();
        fa();
        
        
        
        
      }
     
      else if(token.equals(tk_mod)){
         emparejar(tk_mod);
        factor();
        fa();
        
      }
     
     else if( token.equals(tk_mas) || token.equals(tk_menos) || token.equals(tk_menor) || token.equals(tk_igualdad) || token.equals(tk_menor_igual)
        || token.equals(tk_mayor) || token.equals(tk_mayor_igual) || token.equals(diferente) || token.equals(or) 
        || token.equals(tk_puntoycoma) || token.equals(tk_par_der) || token.equals(tk_coma) ||    token.equals(and)   
            )
        
        {
        
        
      }
      else errorStintaxis(tk_mul,tk_div,tk_mod,tk_mas,tk_menos,tk_menor,tk_igualdad,tk_menor_igual,tk_mayor,tk_mayor_igual,diferente,or,tk_puntoycoma,tk_par_der,tk_coma,and);
    
    
    
    
  }  
  
   public void factor(){
      if(token.equals(fid) )
        
        {
        emparejar(fid);
        emparejar(tk_par_izq);
        lexpr();
        f2();
        emparejar(tk_par_der);
        
      }
      
      else if(token.equals(tk_par_izq)){
        emparejar(tk_par_izq);
        lexpr();
        emparejar(tk_par_der);
        
        
        
      }
     
      else if(token.equals(tk_decremento)){
        emparejar(tk_decremento);
       	 emparejar(id);
                      
      }
     else if(token.equals(tk_incremento)){
        emparejar(tk_incremento);
       	 emparejar(id);
                      
      }
     
      else if(token.equals(id)){
        emparejar(id);
       	 s();
                      
      }
      else if(token.equals(bool)){
        emparejar(bool);
       	        
      }
     else if(token.equals(num)){
        emparejar(num);
       	        
      }
      else errorStintaxis(fid, tk_par_izq, tk_incremento, id, bool, num,tk_decremento);
    
    
    
    
  }  
  
   public void s(){
      if(token.equals(tk_mul) || token.equals(tk_div) || token.equals(tk_mod) || token.equals(tk_mas) || token.equals(tk_menos) || token.equals(tk_menor) || token.equals(tk_igualdad) || token.equals(tk_menor_igual)
        || token.equals(tk_mayor) || token.equals(tk_mayor_igual) || token.equals(diferente) || token.equals(or) || token.equals(tk_puntoycoma) || 
          token.equals(token_par_der) || token.equals(tk_coma) || token.equals(and) )
        
        {
        
        
      }
      
      else if(token.equals(tk_decremento)){
        emparejar(tk_decremento);
        
        
        
      }
     else if(token.equals(tk_incremento)){
        emparejar(tk_incremento);
        
        
        
      }
      else errorStintaxis(tk_mul, tk_div,tk_mod,tk_mas,tk_menos,tk_menor,tk_igualdad,tk_menor_igual,tk_mayor,tk_mayor_igual,diferente,or, tk_puntoycoma, tk_pard_der , tk_coma, and,tk_decremento,tk_incremento);
    
    
    
    
  }  
  	
  public void f2(){
      if(token == tk_par_der ){
        
        
      }
      
      else if(token == tk_coma){
        emparejar(tk_coma);
        lexpr();
        f2();
        
      }
      else errorStintaxis(tk_par_der,tk_coma);
      
      
    }

    public static void main(String args[]) throws IOException
    {

        ArrayList<String> grammar = new ArrayList<String>();
        ArrayList<String> NTs = new ArrayList<String>();
        ArrayList<String> expresions = new ArrayList<String>();
        ArrayList<translation> TradT = new ArrayList<translation>();
        ArrayList<translation> TradNT = new ArrayList<translation>();
        ArrayList<String> Pred = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);

        String s = "";

        while(true)
        {
            s = scanner.nextLine();
            if (s.equals("end"))
            {
                break;
            }
            grammar.add(s);
        }

        for (int i=0; i<grammar.size();i++)
        {
            s = grammar.get(i);
            if (s.equals("end"))
            {
                break;
            }
            //System.out.println(s);
            for(int j = 0; j < s.length(); j++)
            {
                if(s.charAt(j) == 58)
                {
                    if (!NTs.contains((s.substring(0, j))))
                    {
                        NTs.add(s.substring(0, j));
                    }
                    break;
                }
            }
        }


        for (int i=0; i<grammar.size();i++)
        {
            s = grammar.get(i);
            if (s.equals("end"))
            {
                break;
            }
            //System.out.println(s);
            for(int j = 0; j < s.length(); j++)
            {
                if(s.charAt(j) == 58)
                {
                    String[] strgs = s.substring(j+2, s.length()).split(" ");

                    for (int k=0;k<strgs.length;k++)
                    {
                        if (!expresions.contains(strgs[k])&&(!NTs.contains((strgs[k]))))
                        {
                            expresions.add(strgs[k]);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("NTs: "+NTs);
        System.out.println("expresions:"+expresions);

        translation trad;

        int Ascii = 65;
        char ch;

        for (int i=0; i<NTs.size();i++)
        {
            ch = (char) Ascii;
            s = NTs.get(i);
            //System.out.println(s);
            trad = new translation(s,ch);
            TradT.add(trad);
            NTs.set(i, String.valueOf(ch));
            Ascii++;
        }

        Ascii = 97;

        for (int i=0; i<expresions.size();i++)
        {
            ch = (char) Ascii;
            s = expresions.get(i);
            if (s.equals("TK_AND"))
            {
                trad = new translation(s,'&');
                TradNT.add(trad);
                expresions.set(i, "&");
                continue;
            }
            if (s.equals("TK_OR"))
            {
                trad = new translation(s,'|');
                TradNT.add(trad);
                expresions.set(i, "|");
                continue;
            }
            if (s.equals("TK_NOT"))
            {
                trad = new translation(s,'!');
                TradNT.add(trad);
                expresions.set(i, "!");
                continue;
            }
            if (s.length()==1)
            {
                trad = new translation(s,s.charAt(0));
                TradNT.add(trad);
                expresions.set(i, s);
                continue;
            }
            if (s.equals("lambda"))
            {
                trad = new translation(s,'9');
                TradNT.add(trad);
                expresions.set(i, "9");
                continue;
            }
            //System.out.println(s);
            trad = new translation(s,ch);
            TradNT.add(trad);
            expresions.set(i, String.valueOf(ch));
            Ascii++;
            if (Ascii>=123)
            {
                Ascii = 48;
            }
        }

        for (int i=0;i<TradT.size();i++)
        {
            System.out.println("TradNT"+i+": "+TradT.get(i).getoriginal()+" , "+TradT.get(i).gettrad());
        }
        for (int i=0;i<TradNT.size();i++)
        {
            System.out.println("TradT"+i+": "+TradNT.get(i).getoriginal()+" , "+TradNT.get(i).gettrad());
        }
        System.out.println("NTs: "+NTs);
        System.out.println("expresions:"+expresions);

        String str1 = "";
        String str2 = "";

        for (int i=0; i<grammar.size();i++)
        {
            s = grammar.get(i);
            if (s.equals("end"))
            {
                break;
            }
            //System.out.println(s);
            for(int j = 0; j < s.length(); j++)
            {
                if(s.charAt(j) == 58)
                {
                    String[] strgs = s.substring(j+2, s.length()).split(" ");
                    str1 = s.substring(0, j);
                    str2 = s.substring(j+2, s.length());
                    for (int k=0;k<strgs.length;k++)
                    {
                        if (!expresions.contains(strgs[k])&&(!NTs.contains((strgs[k]))))
                        {
                            expresions.add(strgs[k]);
                        }
                    }
                    break;
                }
            }

            String[] strgs = str2.split(" ");
            String str2ToRet = "";
            String str1ToRet = "";
            for (int j = 0; j < strgs.length; j++)
            {
                // You may want to check for a non-word character before blindly
                // performing a replacement
                // It may also be necessary to adjust the character class
                for (int k=0;k<TradNT.size();k++)
                {
                    trad = TradNT.get(k);
                    if (trad.getoriginal().equals(strgs[j]))
                    {
                        str2ToRet = str2ToRet+String.valueOf(trad.gettrad());
                        break;
                    }
                }
                for (int f=0;f<TradT.size();f++)
                {
                    trad = TradT.get(f);
                    if (trad.getoriginal().equals(strgs[j]))
                    {
                        str2ToRet = str2ToRet+String.valueOf(trad.gettrad());
                        break;
                    }
                }
            }
            for (int f=0;f<TradT.size();f++)
            {
                trad = TradT.get(f);
                if (trad.getoriginal().equals(str1))
                {
                    str1ToRet = str1ToRet+String.valueOf(trad.gettrad());
                    break;
                }
            }
            grammar.set(i, str1ToRet+": "+str2ToRet);
        }

        System.out.println("grammar: "+grammar);

        System.out.println("//////////////////////");
        System.out.println("/////////////////////");

        String nt,t;
        int i,j;
        int n = 0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String NTsStr=NTs.toString().replaceAll(",", "");
        char[] numberOfChar = NTsStr.substring(1, NTsStr.length()-1).replaceAll(" ", "").toCharArray();
        ntlen=numberOfChar.length;
        ntermnl=new char[ntlen];
        ntermnl= NTsStr.substring(1, NTsStr.length()-1).replaceAll(" ", "").toCharArray();

        String Tstr=expresions.toString().replaceAll(",", "");
        Tstr = Tstr.substring(0, Tstr.length()-2);
        Tstr = Tstr + " , ]";
        numberOfChar = Tstr.substring(1, Tstr.length()-1).replaceAll(" ", "").toCharArray();
        tlen=numberOfChar.length;
        termnl=new char[tlen];
        termnl= Tstr.substring(1, Tstr.length()-1).replaceAll(" ", "").toCharArray();

        grmr=new String[ntlen][];
        String str = "";
        int begin = -1;
        for(i=0;i<ntlen;i++)
        {
            for (int k = 0; k<grammar.size(); k++)
            {
                str = grammar.get(k);
                if (ntermnl[i]==(str.charAt(0)))
                {
                    n++;
                    if (begin == -1)
                    {
                        begin = k;
                    }
                }
            }
            grmr[i]=new String[n];
            for(j=0;j<n;j++)
            {
                str = grammar.get(begin);
                grmr[i][j]=str.substring(3, str.length());
                begin++;
            }
            begin = -1;
            n = 0;
        }

        String Traduccion;
        String TraduccionNT;
        String out;
        fst=new String[ntlen];
        for(i=0;i<ntlen;i++)
            fst[i]=first(i);
        System.out.println("/////////////////////");
        System.out.println("First Set");
        System.out.println("/////////////////////");
        for(i=0;i<ntlen;i++)
        {
            fst[i] =removeDuplicates(fst[i]);
            out = "";
            for(j = 0; j<fst[i].length(); j++)
            {
                Traduccion = String.valueOf(fst[i].charAt(j));
                for (int k=0;k<TradNT.size();k++)
                {
                    if (TradNT.get(k).gettrad()==fst[i].charAt(j)) {
                        Traduccion = TradNT.get(k).getoriginal();
                        break;
                    }
                }
                out = out + Traduccion + " ";
            }
            TraduccionNT = NTs.get(i);
            for (int k=0;k<TradT.size();k++)
            {
                if (TradT.get(k).gettrad()==NTs.get(i).charAt(0)) {
                    TraduccionNT = TradT.get(k).getoriginal();
                    break;
                }
            }
            if (TraduccionNT.length()>7)
            {
                System.out.println(TraduccionNT+"\t-> "+out);
            }else{
                System.out.println(TraduccionNT+"\t\t-> "+out);
            }
        }
        flw=new String[ntlen];
        for (int z = 0; z<10; z++)
        {
            for(i=0;i<ntlen;i++)
            {
                flw[i]=removeDuplicates(follow(i));
            }
        }
        System.out.println("/////////////////////");
        System.out.println("Follow Set");
        System.out.println("/////////////////////");
        for(i=0;i<ntlen;i++)
        {
            flw[i] =removeDuplicates(flw[i]);
            out = "";
            for(j = 0; j<flw[i].length(); j++)
            {
                Traduccion = String.valueOf(flw[i].charAt(j));
                for (int k=0;k<TradNT.size();k++)
                {
                    if (TradNT.get(k).gettrad()==flw[i].charAt(j)) {
                        Traduccion = TradNT.get(k).getoriginal();
                        break;
                    }
                }
                out = out + Traduccion + " ";
            }
            TraduccionNT = NTs.get(i);
            for (int k=0;k<TradT.size();k++)
            {
                if (TradT.get(k).gettrad()==NTs.get(i).charAt(0)) {
                    TraduccionNT = TradT.get(k).getoriginal();
                    break;
                }
            }
            if (TraduccionNT.length()>7)
            {
                System.out.println(TraduccionNT+"\t-> "+out);
            }else{
                System.out.println(TraduccionNT+"\t\t-> "+out);
            }
        }
        for (i=0; i<grammar.size();i++)
        {
            s = grammar.get(i);
            if (s.equals("end"))
            {
                break;
            }
            //System.out.println(s);
            for(j = 0; j < s.length(); j++)
            {
                if(s.charAt(j) == 58)
                {
                    str1 = s.substring(0, j);
                    str2 = s.substring(j+2, s.length());
                    String strPred = "";
                    int index_NT = NTs.indexOf(String.valueOf(str1.charAt(0)));
                    for (int k=0;k<str2.length();k++)
                    {
                        if (!NTs.contains(String.valueOf(str2.charAt(k))))
                        {
                            if (str2.equals("9"))
                            {
                                strPred = strPred + flw[index_NT];
                            }else
                            {
                                strPred = strPred + String.valueOf(str2.charAt(k));
                            }
                            break;
                        }
                        int index = NTs.indexOf(String.valueOf(str2.charAt(k)));
                        if (fst[index].indexOf('9')!=-1)
                        {
                            strPred = strPred + (fst[index].replace("9", ""));
                        }else
                        {
                            strPred = strPred + (fst[index]);
                            break;
                        }
                    }
                    Pred.add(strPred);
                    break;
                }
            }
        }

        System.out.println("/////////////////////");
        System.out.println("Predictions");
        System.out.println("/////////////////////");

        for(i=0;i<Pred.size();i++)
        {
            out = "";
            for(j = 0; j<Pred.get(i).length(); j++) {
                Traduccion = String.valueOf(Pred.get(i).charAt(j));
                for (int k=0;k<TradNT.size();k++)
                {
                    if (TradNT.get(k).gettrad()==Pred.get(i).charAt(j)) {
                        Traduccion = TradNT.get(k).getoriginal();
                        break;
                    }
                }
                out = out + Traduccion + " ";
            }
            Pred.set(i, out);
        }

        for(int z = 0; z<grammar.size(); z++)
        {
            if (grammar.get(z).length()>7)
            {
                System.out.println(grammar.get(z) + "\t{ " + Pred.get(z) + "}");
            }else{
                System.out.println(grammar.get(z) + "\t\t{ " + Pred.get(z) + "}");
            }
        }
    }

    static String first(int i)
    {
        int j,k,l=0,found=0;
        String temp="",str="";
        for(j=0;j<grmr[i].length;j++) //number of productions
        {
            for(k=0;k<grmr[i][j].length();k++,found=0) //when nonterminal has epsilon production
            {
                found = 0;
                for(l=0;l<ntlen;l++) //finding nonterminal
                {
                    if(grmr[i][j].charAt(k)==ntermnl[l]) //for nonterminal in first set
                    {
                        str=first(l);
                        found=1;
                        break;
                    }
                }
                if(found==1)
                {
                    if(str.contains("9"))
                    { //here epsilon will lead to next nonterminals first set
                        if(!(str.length()==1 && str.charAt(0)=='9'))
                        { //when epsilon production is the only nonterminal production
                            if (!(k+1==grmr[i][j].length()))
                            {
                                str = str.replace("9", "");
                            }
                            temp=temp+str;
                        }
                        continue;
                    }else
                    {
                        temp=temp+str;
                    }
                }
                else //if first set includes terminal
                    temp=temp+grmr[i][j].charAt(k);
                break;
            }
        }
        return temp;
    }
    static String follow(int i)
    {
        char pro[],chr[];
        String temp="";
        int j,k,l,m,n,found=0;
        if(i==0)
            temp="$";
        for(j=0;j<ntlen;j++)
        {
            for(k=0;k<grmr[j].length;k++) //entering grammar matrix
            {
                pro=new char[grmr[j][k].length()];
                pro=grmr[j][k].toCharArray();
                for(l=0;l<pro.length;l++) //entering each production
                {
                    if(pro[l]==ntermnl[i]) //finding the nonterminal whose follow set is to be found
                    {
                        if(l==pro.length-1) //if it is the last terminal/non-terminal then follow of current non-terminal
                        {
                            if(j!=i)
                            {
                                temp=temp+flw[j];
                            }
                        }
                        else
                        {
                            for(m=0;m<ntlen;m++)
                            {
                                if(pro[l+1]==ntermnl[m]) //first of next non-terminal otherwise (else later)
                                {
                                    chr=new char[fst[m].length()];
                                    chr=fst[m].toCharArray();
                                    for(n=0;n<chr.length;n++)
                                    {
                                        if(chr[n]=='9') //if first includes epsilon
                                        {
                                            if(l+1==pro.length-1)
                                                temp=temp+follow(j); //when non-terminal is second last
                                            else
                                                temp=temp+follow(m);
                                        }
                                        else
                                            temp=temp+chr[n]; //include whole first set except epsilon
                                    }
                                    found=1;
                                }
                            }
                            if(found!=1)
                                temp=temp+pro[l+1]; //follow set will include terminal(else is here)
                        }
                    }
                }
            }
        }
        return temp;
    }
    static String removeDuplicates(String str)
    {
        int i;
        char ch;
        boolean seen[] = new boolean[256];
        StringBuilder sb = new StringBuilder(seen.length);
        for(i=0;i<str.length();i++)
        {
            ch=str.charAt(i);
            if (!seen[ch])
            {
                seen[ch] = true;
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
