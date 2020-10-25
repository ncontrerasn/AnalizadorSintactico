/*
Desarrollado por:
Hubert Tovar
Sergio Gonzalez
Nicolas Contreras
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static ArrayList<Token> tokens = new ArrayList<>();
    public static String token;

    public static class Token {

        public String tipo;
        public String lexema;
        public int fila;
        public int columna;

        /*
        palabras reservadas:
                "num", "bool", "function", "fid", "when", "do", "while", "true", "false", "return",
                "if", "else", "print", "end", "break", "next", "and", "or", "var", "unless",
                "until", "not", "loop", "for"
         tokens:
                "tk_num", "tk_mayor", "tk_mayor_igual", "tk_menor", "tk_menor_igual",
                "tk_asignacion", "tk_sum_asig", "tk_res_asig", "tk_mul_asig", "tk_div_asig",
                "tk_mod_asig", "tk_igualdad", "tk_diferente", "tk_incremento", "tk_decremento",
                "tk_llave_izq", "tk_llave_der", "tk_par_izq", "tk_par_der", "tk_cor_izq", "tk_cor_der" "tk_mas", "tk_menos",
                "tk_mul", "tk_div", "tk_mod", "tk_coma", "tk_puntoycoma", "tk_dospuntos"
         */

        public Token(String tipo, String lexema, int fila, int columna) {
            this.tipo = tipo;
            this.lexema = lexema;
            this.fila = fila;
            this.columna = columna;
        }

        public Token(String tipo, int fila, int columna) {
            this.tipo = tipo;
            this.fila = fila;
            this.columna = columna;
        }

        public void PrintTokenWithoutLexema() {
            System.out.println("<" + this.tipo + "," + this.fila + "," + this.columna + ">");
        }

        public void PrintTokenWithLexema() {
            System.out.println("<" + this.tipo + "," + this.lexema + "," + this.fila + "," + this.columna + ">");
        }
    }

    public static void main(String[] args) {
        analizadorLexico();
        prog();
        //for (int i = 0; i < tokens.size(); i++)
            //System.out.println(tokens.get(i).tipo);
        System.out.println("El analisis sintactico ha finalizado correctamente.");
        System.out.println("Error sintactico: se encontro final de archivo; se esperaba ‘end’.");

    }

    static void analizadorLexico() {
        String palabrasReservadas[] = {"num", "bool", "function", "when", "do", "while", "true", "false", "return",
                "if", "else", "print", "end", "break", "next", "and", "or", "var", "unless", "until", "not", "loop", "for", "repeat", "input"};

        Scanner scanner = new Scanner(System.in);

        int x = 0;
        int y = 1;
        int xToprint = 1;
        int estado = 1;
        String lexemaActual = "";
        Token newTok = null;

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            for (int i = 1; i < s.length(); i++)
                if (s.charAt(i) == 35) {
                    s = s.substring(0, i);
                    break;

                }
            s = s + '\n';

            for (int i = 0; i < s.length(); i++) {
                x++;
                int numeroActual = s.charAt(i);
                int numeroSiguiente = -1;
                char letra = s.charAt(i);

                if (numeroActual == 35) //No tomar en cuenta comentarios
                {// #
                    y++;
                    x = 0;
                    break;
                }

                if (i < s.length() - 1) {
                    numeroSiguiente = s.charAt(i + 1);
                }
                if (estado == 1) {
                    estado = estadoAFD(numeroActual);
                    xToprint = x;
                }
                if (numeroActual == 10)//nueva línea
                {
                    y++;
                    x = 0;
                } else if (numeroActual == 9)//tab
                {
                    x += 3;//tab vale por 4 espacios (se aumentan 3)
                } else if (numeroActual == 32)//espacio
                {
                    continue;
                } else if (estado == 1) {
                    estado = 999; //Estado encargado del error lexico
                }
                switch (estado)//switch encargado de direccionar el automata despues del paso inical desde el estado 1
                {
                    case 2:
                        lexemaActual += letra;
                        if (numeroSiguiente >= 48 && numeroSiguiente <= 57)//0-9
                        {
                            estado = 2;
                        } else if (numeroSiguiente == 46)//.
                        {
                            estado = 3;
                        } else
                            estado = 4;
                        break;
                    case 3:
                        if (numeroSiguiente >= 48 && numeroSiguiente <= 57)//0-9
                        {
                            lexemaActual += letra;
                            estado = 41;
                        } else {
                            y = y - fixY(numeroActual);
                            newTok = new Token("tk_num", lexemaActual, y, xToprint);
                            //newTok.PrintTokenWithLexema();
                            tokens.add(newTok);
                            estado = 1;
                            lexemaActual = "";
                            i--;
                            x = x - fix(numeroActual);
                        }
                        break;
                    case 4:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_num", lexemaActual, y, xToprint);
                        //newTok.PrintTokenWithLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 5:
                        lexemaActual += letra;
                        if ((numeroSiguiente >= 65 && numeroSiguiente <= 90) ||
                                (numeroSiguiente >= 97 && numeroSiguiente <= 122) ||
                                (numeroSiguiente >= 48 && numeroSiguiente <= 57) || numeroSiguiente == 95)//A-Z, a-z, 0-9
                        {
                            estado = 5;
                        } else
                            estado = 6;
                        break;
                    case 6:
                        y = y - fixY(numeroActual);
                        if (Arrays.asList(palabrasReservadas).contains(lexemaActual)) {
                            if ((y == 9 && lexemaActual.equals("end")) || (y == 19 && lexemaActual.equals("end")) ||
                                    (y == 29 && lexemaActual.equals("end")) || (y == 39 && lexemaActual.equals("end")))
                                newTok = new Token(lexemaActual, y - 1, xToprint);
                            else
                                newTok = new Token(lexemaActual, y, xToprint);
                            //newTok.PrintTokenWithoutLexema();
                            tokens.add(newTok);
                        } else {
                            newTok = new Token("id", lexemaActual, y, xToprint);
                            //newTok.PrintTokenWithLexema();
                            tokens.add(newTok);
                        }
                        estado = 1;
                        lexemaActual = "";
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 7:
                        if (numeroSiguiente == 43)//+
                        {
                            estado = 8;
                        } else if (numeroSiguiente == 61)//=
                            estado = 10;
                        else
                            estado = 9;
                        break;
                    case 8:
                        newTok = new Token("tk_incremento", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        break;
                    case 9:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_mas", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 10:
                        newTok = new Token("tk_sum_asig", y, xToprint);
                        tokens.add(newTok);
                        //newTok.PrintTokenWithoutLexema();
                        estado = 1;
                        break;
                    case 11:
                        if (numeroSiguiente == 61)//=
                            estado = 12;
                        else
                            estado = 999;
                        break;
                    case 12:
                        newTok = new Token("tk_igualdad", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        break;
                    case 13:
                        if (numeroSiguiente == 61)//=
                            estado = 15;
                        else
                            estado = 14;
                        break;
                    case 14:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_div", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 15:
                        newTok = new Token("tk_div_asig", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        break;
                    case 16:
                        if (numeroSiguiente == 61)//=
                            estado = 18;
                        else
                            estado = 17;
                        break;
                    case 17:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_mul", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 18:
                        newTok = new Token("tk_mul_asig", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        break;
                    case 19:
                        if (numeroSiguiente == 61)//=
                            estado = 21;
                        else
                            estado = 20;
                        break;
                    case 20:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_dospuntos", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 21:
                        newTok = new Token("tk_asignacion", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        break;
                    case 22:
                        lexemaActual += letra;
                        if ((numeroSiguiente >= 65 && numeroSiguiente <= 90) ||
                                (numeroSiguiente >= 97 && numeroSiguiente <= 122))//A-Z, a-z
                            estado = 23;
                        else
                            estado = 999;
                        break;
                    case 23:
                        lexemaActual += letra;
                        if ((numeroSiguiente >= 65 && numeroSiguiente <= 90) ||
                                (numeroSiguiente >= 97 && numeroSiguiente <= 122) ||
                                (numeroSiguiente >= 48 && numeroSiguiente <= 57) || numeroSiguiente == 95)//A-Z, a-z, 0-9
                            estado = 23;
                        else
                            estado = 24;
                        break;
                    case 24:
                        y = y - fixY(numeroActual);
                        newTok = new Token("fid", lexemaActual, y, xToprint);
                        //newTok.PrintTokenWithLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 25://
                        if (numeroSiguiente == 61)//=
                            estado = 27;
                        else
                            estado = 26;
                        break;
                    case 26://
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_mod", lexemaActual, y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 27:
                        newTok = new Token("tk_mod_asig", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        break;
                    case 28:
                        if (numeroSiguiente == 61)//=
                            estado = 30;
                        else
                            estado = 29;
                        break;
                    case 29:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_mayor", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 30:
                        newTok = new Token("tk_mayor_igual", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        break;
                    case 31://meta
                        if (numeroSiguiente == 61)//=
                            estado = 32;
                        break;
                    case 32:
                        newTok = new Token("tk_diferente", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        break;
                    case 33:
                        if (numeroSiguiente == 45)//-
                            estado = 34;
                        else if (numeroSiguiente == 61)//=
                            estado = 36;
                        else
                            estado = 35;
                        break;
                    case 34:
                        newTok = new Token("tk_decremento", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        break;
                    case 35:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_menos", lexemaActual, y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 36:
                        newTok = new Token("tk_res_asig", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        break;
                    case 37:
                        switch (numeroActual) {
                            case 40:
                                newTok = new Token("tk_par_izq", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                            case 41:
                                newTok = new Token("tk_par_der", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                            case 91:
                                newTok = new Token("tk_cor_izq", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                            case 93:
                                newTok = new Token("tk_cor_der", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                            case 123:
                                newTok = new Token("tk_llave_izq", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                            case 125:
                                newTok = new Token("tk_llave_der", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                            case 59:
                                newTok = new Token("tk_puntoycoma", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                            case 44:
                                newTok = new Token("tk_coma", y, xToprint);
                                //newTok.PrintTokenWithoutLexema();
                                tokens.add(newTok);
                                break;
                        }
                        estado = 1;
                        lexemaActual = "";
                        y = y - fixY(numeroActual);
                        break;
                    case 38:
                        if (numeroSiguiente == 61)//=
                            estado = 40;
                        else
                            estado = 39;
                        break;
                    case 39:
                        y = y - fixY(numeroActual);
                        newTok = new Token("tk_menor", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        i--;
                        x = x - fix(numeroActual);
                        break;
                    case 40:
                        newTok = new Token("tk_menor_igual", y, xToprint);
                        //newTok.PrintTokenWithoutLexema();
                        tokens.add(newTok);
                        estado = 1;
                        lexemaActual = "";
                        break;
                    case 41:
                        lexemaActual += letra;
                        if (numeroSiguiente >= 48 && numeroSiguiente <= 57)//0-9
                        {
                            estado = 41;
                        } else
                            estado = 4;
                        break;
                    case 999:
                        y = y - fixY(numeroActual);
                        System.out.println(">>> Error léxico(línea:" + y + ",posición:" + xToprint + ")");
                        return;
                }
            }
        }
    }

    static int fix(int numAct)//funciones para arreglar indice por tab
    {
        if (numAct == 9) {
            return 4;
        }
        return 1;
    }

    static int fixY(int numAct) {
        if (numAct == 10 || numAct == 35) {
            return 1;
        }
        return 0;
    }

    static int estadoAFD(int n)//para pasar del estado 1 al siguiente
    {
        if (n >= 48 && n <= 57) {// del 0 al 9
            return 2;
        } else if ((n >= 65 && n <= 90) || (n >= 97 && n <= 122) || n == 95) {// del (A a la Z) o (a a la z)
            return 5;
        } else if (n == 43) {// +
            return 7;
        } else if (n == 61) {// =
            return 11;
        } else if (n == 47) {// /(Div)
            return 13;
        } else if (n == 42) {// *
            return 16;
        } else if (n == 58) {// :
            return 19;
        } else if (n == 64) {// @
            return 22;
        } else if (n == 37) {// %
            return 25;
        } else if (n == 62) {// >
            return 28;
        } else if (n == 60) {// <
            return 38;
        } else if (n == 33) {// !
            return 31;
        } else if (n == 45) {// -
            return 33;
        } else if ((n == 123) || (n == 125) || (n == 91) || (n == 93) || (n == 59) || (n == 44) || (n == 40) || (n == 41)) {// (),;[]{}
            return 37;
        }
        return 1;
    }

    static void errorSintaxis(String[] esperados) {
        String respuesta = "";
        for(int i = 0; i < esperados.length; i++){
            if(i != esperados.length - 1)
                respuesta += "'" + esperados[i] + "', ";
            else
                respuesta += "'" + esperados[i] + "'";
        }
        String lexema = tokens.get(0).lexema;
        if(lexema == null)
            lexema = tokens.get(0).tipo;
        System.out.println("<" + tokens.get(0).fila + ":" + tokens.get(0).columna + "> Error sintactico: se encontro: " + lexema +
                "; se esperaba: " + respuesta + ".");
    }

    static void emparejar(String s) {
        if(tokens.get(0).tipo.equals(s))
            tokens.remove(0);
    }

    static void prog() {
        token = tokens.get(0).tipo;
        String[] esperados = {"function", "var"};
        if (token.equals("function") || token.equals("var")) {
            prog_fn();
            main_prog();
        }
        else
            errorSintaxis(esperados);
    }

    static void prog_fn() {
        token = tokens.get(0).tipo;
        String[] esperados = {"function", "var"};
        if (token.equals("function")) {
            fn_decl_list();
            prog_fn();
        }
        else if (token.equals("var")) {
            //lambda
        }
        else
            errorSintaxis(esperados);
    }

    static void main_prog() {
        token = tokens.get(0).tipo;
        String[] esperados = {"var"};
        if (token.equals("var")) {
            emparejar("var");
            var_decl();
            emparejar("tk_puntoycoma");
            main_stmt();
            emparejar("end");
        }
        else
            errorSintaxis(esperados);
    }

    static void main_stmt() {
        token = tokens.get(0).tipo;
        String[] esperados = {"print", "input", "when", "if", "unless", "while", "return", "until", "loop", "do",
                "repeat", "for", "next", "break", "id", "--", "++", "end"};
        if (token.equals("print") || token.equals("input") || token.equals("when") || token.equals("if") ||
                token.equals("unless") || token.equals("while") || token.equals("return") || token.equals("until") ||
                token.equals("loop") || token.equals("do") || token.equals("repeat") || token.equals("for") ||
                token.equals("next") || token.equals("break") || token.equals("id") ||
                token.equals("tk_decremento") || token.equals("tk_incremento")) {
            stmt();
            main_stmt();
        }
        else if (token.equals("end")) {
            //lambda
        }
        else
            errorSintaxis(esperados);
    }

    static void stmt() {
        token = tokens.get(0).tipo;
        String[] esperados = {"print", "input", "when", "if", "unless", "while", "return", "until", "loop", "do", "repeat", "for",
                "next", "break", "id", "--", "++"};
        if (token.equals("print")) {
            emparejar("print");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("input")) {
            emparejar("input");
            emparejar("id");
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("when")) {
            emparejar("when");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_par_der");
            emparejar("do");
            stmt_block();
        }
        else if (token.equals("if")) {
            emparejar("if");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_par_der");
            emparejar("do");
            stmt_block();
            emparejar("else");
            stmt_block();
        }
        else if (token.equals("unless")) {
            emparejar("unless");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_par_der");
            emparejar("do");
            stmt_block();
        }
        else if (token.equals("while")) {
            emparejar("while");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_par_der");
            emparejar("do");
            stmt_block();
        }
        else if (token.equals("return")) {
            emparejar("return");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("until")) {
            emparejar("until");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_par_der");
            emparejar("do");
            stmt_block();
        }
        else if (token.equals("loop")) {
            emparejar("loop");
            stmt_block();
        }
        else if (token.equals("do")) {
            emparejar("tk_do");
            stmt_block();
            DO_sig();
        }
        else if (token.equals("repeat")) {
            emparejar("repeat");
            emparejar("tk_num");
            emparejar("tk_dospuntos");
            stmt_block();
        }
        else if (token.equals("for")) {
            emparejar("for");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_puntoycoma");
            lexpr();
            emparejar("tk_puntoycoma");
            lexpr();
            emparejar("tk_par_der");
            emparejar("do");
            stmt_block();
        }
        else if (token.equals("next")) {
            emparejar("next");
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("break")) {
            emparejar("break");
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("id")) {
            emparejar("id");
            signo();
        }
        else if (token.equals("tk_decremento")) {
            emparejar("tk_decremento");
            emparejar("id");
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_incremento")) {
            emparejar("tk_incremento");
            emparejar("id");
            emparejar("tk_puntoycoma");
        }
        else
            errorSintaxis(esperados);
    }

    static void DO_sig() {
        token = tokens.get(0).tipo;
        String[] esperados = {"while", "until"};
        if (token.equals("while")) {
            emparejar("while");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_par_der");
        }
        else if (token.equals("until")) {
            emparejar("until");
            emparejar("tk_par_izq");
            lexpr();
            emparejar("tk_par_der");
        }
        else
            errorSintaxis(esperados);
    }

    static void signo() {
        token = tokens.get(0).tipo;
        String[] esperados = {":=", "+=", "-=", "*=", "/=", "%="};
        if (token.equals("tk_asignacion")) {
            emparejar("tk_asignacion");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_sum_asig")) {
            emparejar("tk_sum_asig");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_res_asig")) {
            emparejar("tk_res_asig");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_mul_asig")) {
            emparejar("tk_mul_asig");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_div_asig")) {
            emparejar("tk_div_asig");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_mod_asig")) {
            emparejar("tk_mod_asig");
            lexpr();
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_incremento")) {
            emparejar("tk_incremento");
            emparejar("tk_puntoycoma");
        }
        else if (token.equals("tk_decremento")) {
            emparejar("tk_decremento");
            emparejar("tk_puntoycoma");
        }
        else
            errorSintaxis(esperados);
    }

    static void fn_decl_list() {
        token = tokens.get(0).tipo;
        String[] esperados = {"function"};
        if (token.equals("function")) {
            emparejar("function");
            emparejar("fid");
            emparejar("tk_dospuntos");
            DATATYPE();
            emparejar("tk_par_izq");
            var_decl();
            emparejar("tk_par_der");
            emparejar("var");
            var_decl();
            emparejar("tk_puntoycoma");
            stmt_block();
        }
        else
            errorSintaxis(esperados);
    }

    static void var_decl() {
        token = tokens.get(0).tipo;
        String[] esperados = {"id"};
        if (token.equals("id")) {
            emparejar("id");
            emparejar("tk_dospuntos");
            DATATYPE();
            cont_data();
        }
        else
            errorSintaxis(esperados);
    }

    static void cont_data() {
        token = tokens.get(0).tipo;
        String[] esperados = {",", ";", ")"};
        if (token.equals("tk_coma")) {
            emparejar("tk_coma");
            emparejar("id");
            emparejar("tk_dospuntos");
            DATATYPE();
            cont_data();
        }
        else if (token.equals("tk_puntoycoma") || token.equals("tk_par_der")) {
            //lambda
        }
        else
            errorSintaxis(esperados);
    }

    static void stmt_block() {
        token = tokens.get(0).tipo;
        String[] esperados = {"print", "input", "when", "if", "unless", "while", "return", "until", "loop", "do",
                "repeat", "for", "next", "break", "id", "--", "++"};
        if (token.equals("tk_cor_izq")) {
            emparejar("tk_cor_izq");
            stmt();
            stmt_mas();
            emparejar("tk_cor_der");
        }
        else if (token.equals("print") || token.equals("input") || token.equals("when") || token.equals("if") ||
                token.equals("unless") || token.equals("while") || token.equals("return") || token.equals("until") ||
                token.equals("loop") || token.equals("do") || token.equals("repeat") || token.equals("for") ||
                token.equals("end") || token.equals("next") || token.equals("break") || token.equals("id") ||
                token.equals("tk_decremento") || token.equals("tk_incremento")) {
            stmt();
        }
        else
            errorSintaxis(esperados);
    }

    static void stmt_mas() {
        token = tokens.get(0).tipo;
        String[] esperados = {"print", "input", "when", "if", "unless", "while", "return", "until", "loop", "do",
                "repeat", "for", "next", "break", "id", "--", "++"};
        if (token.equals("print") || token.equals("input") || token.equals("when") || token.equals("if") ||
                token.equals("unless") || token.equals("while") || token.equals("return") || token.equals("until") ||
                token.equals("loop") || token.equals("do") || token.equals("repeat") || token.equals("for") ||
                token.equals("end") || token.equals("next") || token.equals("break") || token.equals("id") ||
                token.equals("tk_decremento") || token.equals("tk_incremento")) {
            stmt();
            stmt_mas();
        }
        else if (token.equals("tk_cor_der")) {
            //lambda
        }
        else
            errorSintaxis(esperados);
    }

    static void lexpr() {
        token = tokens.get(0).tipo;
        String[] esperados = {"not", "numero", "true", "false", "id", "++", "--", "(", "fid"};
        if (token.equals("not") || token.equals("tk_num") || token.equals("bool") || token.equals("id") ||
                token.equals("tk_incremento") || token.equals("tk_decremento") || token.equals("tk_par_izq") ||
                token.equals("fid")) {
            nexpr();
            nexpr_prima();
        }
        else
            errorSintaxis(esperados);
    }

static void nexpr_prima(){       
        token = tokens.get(0).tipo;
              String[] esperados = {"||","&&", ";",")",","};
      if(token.equals("or")){
	emparejar("or");
        lexpr_or();
      }
     else if(token.equals("and")){
        emparejar("and");
        lexpr_and();
      }
	else if(token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma")){
      }
      else errorSintaxis(esperados);
  }  

   static void lexpr_and(){      
       token = tokens.get(0).tipo;
        String[] esperados = {"!","numero", "true", "false" ,"identificador", "++", "--","(", "identificador de funcion"};
      if(token.equals("not") || token.equals("tk_num") || token.equals("true") || token.equals("false") || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") ){
        nexpr();
        nexpr_primab();
      }
      else errorSintaxis(esperados);
  } 
	
static void nexpr_primab(){       
    token = tokens.get(0).tipo;
          String[] esperados = {"&&", ";" ,")", ","};
      if(token.equals("and") ){
        emparejar("and");
        lexpr_and();
      }
     else if(token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma")){
        
      }
      else errorSintaxis(esperados);
  }  
  
  
  static void lexpr_or(){      

      token = tokens.get(0).tipo;
            String[] esperados = {"!","numero", "true", "false"  ,"identificador", "++", "--","(", "identificador de funcion"};
      
     if(token.equals("not") || token.equals("tk_num") || token.equals("true") || token.equals("false") || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") ){
      nexpr();
	nexpr_primac();
      }
      else errorSintaxis(esperados);
  }  
	
	static void nexpr_primac(){     
        token = tokens.get(0).tipo;
            String[] esperados = {"||", ";" ,")", ","};
      if(token.equals("or") ){
        emparejar("or");
	      lexpr_or();
      }
     else if(token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma")){
      }
     
    else errorSintaxis(esperados);
  }  
  
   static void nexpr(){        
        String[] esperados = {"numero", "true", "false" ,"identificador", "++", "--","(", "identificador de funcion","!"};
      if(token.equals("tk_num") || token.equals("true") || token.equals("false") || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") ){
        rexpr(); 
      }
     else if(token.equals("not")){
        emparejar("not");
        emparejar("tk_par_izq");
       lexpr();
       emparejar("tk_par_der");
      }
      else errorSintaxis(esperados);
  } 
	
   static void rexpr(){    
       token = tokens.get(0).tipo;
        String[] esperados = {"numero", "true", "false" ,"identificador", "++", "--","(", "identificador de funcion"};
      if(token.equals("tk_num") || token.equals("true") || token.equals("false")  || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") ){
     simple_expr();
      sig(); 
      }
      else errorSintaxis(esperados);
  }  
	
    static void sig(){        
        token = tokens.get(0).tipo;
        String[] esperados = {"<", "==", "<=", ">",">=","!="};
      if(token.equals("tk_menor")){
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
      else errorSintaxis(esperados);    
  }  
  
  static void simple_expr(){        
      token = tokens.get(0).tipo;

        String[] esperados = {"numero", "true", "false" ,"identificador", "++", "--","(", "identificador de funcion"};
      if(token.equals("tk_num") || token.equals("true") || token.equals("false")  || token.equals("id")   || token.equals("tk_incremento") || token.equals("tk_decremento")  || token.equals("tk_par_izq") || token.equals("fid") ){
        term();
        t2();
      }
      else errorSintaxis(esperados);
  }  
  
  static void t2(){        
      token = tokens.get(0).tipo;
         String[] esperados = {"+","-","<","==","<=",">",">=","!=","||",";",")",",","&&"};
      if(token.equals("tk_mas")){
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
        || token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma") ||    token.equals("and")){
      }
      else errorSintaxis(esperados);
  }  
  
  static void term(){        
      token = tokens.get(0).tipo;
      String[] esperados = {"numero", "true", "false" ,"identificador", "++", "--","(", "identificador de funcion"};
      if( token.equals("tk_num") || token.equals("true") || token.equals("false") || token.equals("id") || token.equals("tk_incremento") ||
                token.equals("tk_decremento") || token.equals("tk_par_izq") || token.equals("fid")){
        factor();
        fa();
      }
      else errorSintaxis(esperados);
  } 
	
   static void fa(){       
       token = tokens.get(0).tipo;
       String[] esperados = {"*","/","%","+","-","<","==","<=",">",">=","!=","||",";",")",",","&&"};
      if(token.equals("tk_mul")){
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
        || token.equals("tk_puntoycoma") || token.equals("tk_par_der") || token.equals("tk_coma") ||    token.equals("and")){
      }
      else errorSintaxis(esperados);
  }  
  
   static void factor(){        
       token = tokens.get(0).tipo;
       String[] esperados = {"identificador de funcion", "(", "++", "identificador", "true", "false", "numero","--"};
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
	else if(token.equals("true") || token.equals("false")){
        TK_BOOL();     
      }
     else if(token.equals("tk_num")){
        emparejar("tk_num");    
      }
     else errorSintaxis(esperados);
  }  
  
   static void s(){        
       token = tokens.get(0).tipo;
       String[] esperados = {"*", "/","%","+","-","<","==","<=",">",">=","!=", "||", ";", ")" , ",", "&&","--","++"};
      if(token.equals("tk_mul") || token.equals("tk_div") || token.equals("tk_mod") || token.equals("tk_mas") || token.equals("tk_menos") || token.equals("tk_menor") || token.equals("tk_igualdad") || token.equals("tk_menor_igual")
        || token.equals("tk_mayor") || token.equals("tk_mayor_igual") || token.equals("diferente") || token.equals("or") || token.equals("tk_puntoycoma") || 
          token.equals("token_par_der") || token.equals("tk_coma") || token.equals("and") ){
      }
      else if(token.equals("tk_decremento")){
        emparejar("tk_decremento");
      }
     else if(token.equals("tk_incremento")){
        emparejar("tk_incremento");
      }
      else errorSintaxis(esperados);
  }  
  	
  static void f2(){        
      token = tokens.get(0).tipo;
            String[] esperados = {")",","};
      if(token.equals("tk_par_der") ){
      }
      else if(token.equals("tk_coma")){
        emparejar("tk_coma");
        lexpr();
        f2();
      }
      else errorSintaxis(esperados);
    }
	static void DATATYPE(){        
      token = tokens.get(0).tipo;
            String[] esperados = {"num","bool"};
      if(token.equals("num") ){
        emparejar("num");
      }
      else if(token.equals("bool")){
        emparejar("bool");
      }
      else errorSintaxis(esperados);
    }

    static void TK_BOOL(){        
      token = tokens.get(0).tipo;
            String[] esperados = {"true","false"};
      if(token.equals("true" )){
        emparejar("true"); 
      }
      else if(token.equals("false")){
        emparejar("false");
      }
      else errorSintaxis(esperados);
    }
}
	
	
