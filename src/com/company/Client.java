package com.company;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.Enumeration;

public class Client {
    public static void main(String[] args) throws NamingException, RemoteException, MalformedURLException, AlreadyBoundException {
        // Client
        // этот контекст мы добовляем в RMI
        Context context = new InitialContext();

        // а так же указываем путь где можно найти наш Server
        Enumeration<NameClassPair> e = context.list("rmi://127.0.0.1:1099/");
        while (e.hasMoreElements()){
            System.out.println(e.nextElement().getName()); // выводим список классов
        }

        // вытащим наш класс и найдем значение
        IMath iMath = (IMath)context.lookup("rmi://127.0.0.1:1099/imath");
        int result = iMath.add(1,2);
        System.out.println(result);

    }
}
