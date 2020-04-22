/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HP
 */
public class TCPServer {

    public static void main(String[] args) throws Exception, Throwable {
        readAsByte();
    }

    public static void readAsByte() throws Exception, Throwable {

        ServerSocket serverSocket = new ServerSocket(4445);

        while (true) {
            System.out.println("Yeni sorgu gozlenilir...");
            Socket connection = serverSocket.accept(); // Waiting...
            DataInputStream dataStream = new DataInputStream(connection.getInputStream()); // connection-dan inputu aldi

            String result = readReguest(dataStream);
            System.out.println(result);
            OutputStream outputStream = connection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            writeResponse(dataOutputStream, "Hey, I am working");

//            byte[] bytes = readMessage(dataStream);
//            FileUtility.writeBytes(bytes, "C:\\Users\\HP\\Desktop\\java.jpg");
            connection.close();
        }
    }

    public static void writeResponse(OutputStream out, String s) throws Throwable {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "Connection: close\r\n\r\n";
        String result = response + s;
        out.write(result.getBytes());
        out.flush();
    }

    public static String readReguest(InputStream sin) throws IOException {
        InputStreamReader isr = new InputStreamReader(sin);
        BufferedReader reader = new BufferedReader(isr);
        String msg = "";
        while (true) {
            String s = reader.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            } else {
                msg = msg + s + "\r\n";
            }
            System.out.println("Server raguest: " + s + "\n");
        }
        return msg;
    }

    public static byte[] readMessage(DataInputStream din) throws Exception {
        int msgLen = din.readInt();
        byte[] msg = new byte[msgLen];
        din.readFully(msg);
        return msg;
    }

    public static void readAsString() throws Exception {
        ServerSocket serverSocket = new ServerSocket(4555);

        while (true) {
            System.out.println("Yeni sorgu gozlenilir...");
            Socket connection = serverSocket.accept();
            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bReader = new BufferedReader(reader);

            String messageFromClient = bReader.readLine();
            System.out.println("\n    Sorgu: \"" + messageFromClient + "\"\n");
        }
    }
}
