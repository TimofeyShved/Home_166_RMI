package com.company;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) throws NamingException, RemoteException, MalformedURLException, AlreadyBoundException {
	    // Server (для запуска нужно включить RMI, через командную строку)
        // этот контекст мы добовляем в RMI
        Context context = new InitialContext();
        // а так же указываем путь где можно найти наш класс, (rebind - отключит другие программы и включит нашу)
        context.bind("rmi://127.0.0.1:1099/imath", new ImathImpl());
        //ещё можно так:
        //context.bind("rmi:imath", new ImathImpl());
        //Naming.bind("imath", new ImathImpl());
    }
}

// создаём интерфейс и extends Remote, что бы работал RMI
interface IMath extends Remote{
    int add(int a, int b) throws RuntimeException;
}

// создаём класс с которым будет работать RMI
class ImathImpl extends UnicastRemoteObject implements IMath{

    protected ImathImpl() throws RemoteException {
        super();
    }

    // то с чем он будет рабоатать
    @Override
    public int add(int a, int b) throws RuntimeException {
        return a+b;
    }
}
