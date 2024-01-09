/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerSeite;

import ServerSeite.SenderEmpfanger;
import ServerSeite.ServerClientThread;
import ServerSeite.Prozess;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alisi
 */

public class DataServer {

    SenderEmpfanger se;
    Prozess prozess; 
    

    public DataServer(SenderEmpfanger se, Prozess prozess) {
        this.se = se;
        this.prozess=prozess;
        startServer();
    }

    public void startServer() {
        try {
            ServerSocket server = new ServerSocket(8888);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ServerClientThread sct = new ServerClientThread(serverClient, counter, se,prozess); //send  the request to a separate thread
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public static void main(String[] args) {
//        MultithreadedSocketServerOhneMain mtss = new MultithreadedSocketServerOhneMain();
//    }
}
