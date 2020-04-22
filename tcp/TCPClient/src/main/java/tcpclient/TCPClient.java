/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import util.FileUtility;

/**
 *
 * @author HP
 */
public class TCPClient {

    public static void main(String[] args) throws Exception {
        Socket clientSocket = new Socket("localhost", 4445);
        OutputStream outputStream = clientSocket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        byte[] bytes = FileUtility.readBytes("C:\\Users\\HP\\Desktop\\Desktop\\java.jpg");
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
        clientSocket.close();
    }
}
