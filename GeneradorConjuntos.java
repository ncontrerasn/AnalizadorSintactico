package GeneradorConjuntos;

import java.util.*;
import java.io.*;

public class GeneradorConjuntos
{
    static char ntermnl[],termnl[];
    static int ntlen,tlen;
    static String grmr[][],fst[],flw[];

    public static class translation {

        private String original;
        private char trad;

        public translation (String original, char trad)
        {
            this.original=original;
            this.trad=trad;
        }

        public String getoriginal()
        {
            return original;
        }

        public char gettrad()
        {
            return trad;
        }
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

        int Ascii = 64;
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
            if (Ascii==97)
            {
                Ascii = 160;
            }else if (Ascii==92)
            {
                Ascii++;
            }
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
        for (int z = 0; z<2; z++)
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

        //Calculo de predicciones
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
                            if (k+1==str2.length())
                            {
                                System.out.println(index_NT);
                                strPred = strPred + flw[index_NT];
                            }
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
            for(j = 0; j<Pred.get(i).length(); j++)
            {
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

        String grammarToPrint;

        for(int z = 0; z<grammar.size(); z++)
        {
            grammarToPrint = "";
            str = grammar.get(z);
            Traduccion = "";
            for (int m = 0; m<str.length(); m++)
            {
                Traduccion = "";
                for (int k=0;k<TradT.size();k++)
                {
                    if (TradT.get(k).gettrad()==str.charAt(m))
                    {
                        Traduccion = TradT.get(k).getoriginal();
                        if (grammarToPrint.length()==0)
                        {
                            grammarToPrint = Traduccion;
                        }else
                        {
                            grammarToPrint = grammarToPrint + " " + Traduccion;
                        }
                        break;
                    }
                }
                for (int k=0;k<TradNT.size();k++)
                {
                    if (TradNT.get(k).gettrad()==str.charAt(m))
                    {
                        Traduccion = TradNT.get(k).getoriginal();
                        grammarToPrint = grammarToPrint + " " + Traduccion;
                        break;
                    }
                }
            }
            System.out.println(grammarToPrint + "\t\t\t\t\t{ " + Pred.get(z) + "}");
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
                    { //here epsilon will lead to next nonterminal’s first set
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
                            if(j!=i && flw[j]!=null)
                            {
                                temp=temp+flw[j];
                            }
                        }
                        else
                        {
                            for(m=0;m<ntlen;m++)
                            {
                                if(pro[l+1]==ntermnl[m]) //first of next non-terminal otherwise (else later…)
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