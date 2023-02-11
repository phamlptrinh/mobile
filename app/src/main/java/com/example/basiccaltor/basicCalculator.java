package com.example.basiccaltor;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class basicCalculator {
    public static byte order(char c) {
        switch (c) {
            case '(':
                return 3;
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    public static double cal(double opd2, double opd1, Character c) {
        switch (c) {
            case '+':
                return (opd1 + opd2);
            case '-':
                return (opd1 - opd2);
            case '*':
                return (opd1 * opd2);
            case '/':
                return (opd1 / opd2);
            default:
                return 0;
        }
    }

    public static List postfix(String input) {
        char[] infix = input.toCharArray();
        Stack<Character> opSt = new Stack<>();
        List<Object> pf = new LinkedList<>();
        double num = 0;
        boolean op = true, pos = true;

        outer1:
        for (short i = 0; i < infix.length; i++) {
            if (Character.isDigit(infix[i])) {
                outer:
                while (Character.isDigit(infix[i]) || infix[i] == '.') {
                    if (infix[i] == '.') {
                        i++;
                        byte dec = 1;
                        while (Character.isDigit(infix[i])) {
                            num += ((infix[i] - '0') * Math.pow(0.1, dec));
                            i++;
                            dec++;
                            if (i >= infix.length) break outer;
                        }
                    } else while (Character.isDigit(infix[i])) {
                        num = num * 10 + (infix[i] - '0');
                        i++;
                        if (i >= infix.length) break outer;
                    }
                }
                if (!pos) {
                    num = 0 - num;
                }
                pf.add(num);
                num = 0;
                op = false;
                pos = true;
            }
            if (i >= infix.length) break outer1;
            if (!Character.isDigit(infix[i])) {
                if (op && infix[i] == '-') {
                    pos = false;
                } else if (opSt.empty() || opSt.peek() == '(') {
                    opSt.push(infix[i]);
                } else if (infix[i] == ')') {
                    while (opSt.peek() != '(')/*co thuc su can !empty*/ {
                        pf.add(opSt.pop());
                        /* if(opSt.empty()) break; */
                    }
                    opSt.pop();
                } else if (order(infix[i]) > order(opSt.peek())) {
                    opSt.push(infix[i]);
                } else {
                    while (order(infix[i]) <= order(opSt.peek())) {
                        pf.add(opSt.pop());
                        if (opSt.empty()) break;
                    }
                    opSt.push(infix[i]);
                }
                op = true;
            }
        }
        while (!opSt.empty()) {
            pf.add(opSt.pop());
        }
        return pf;
    }

    public static double calPostfix(List<Object> postfix) {
        Stack<Double> num = new Stack<>();
        for (Object o : postfix) {
            /*if(Character.isDigit((char)o)) khong duoc vi double khong ve char dc*/
            if (o instanceof Double) {
                num.push((double) o);
            } else {
                num.push(cal(num.pop(), num.pop(), (Character) o));
            }
        }
        if(!num.empty())
        {return num.pop();}
        else return 0;
    }
}
