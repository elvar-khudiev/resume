/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtility {

    public static void writeBytes(byte[] data, String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(data);
        fos.flush();
        fos.close();

        System.out.println("Done");
    }

    public static byte[] readBytes(String fileName) throws Exception {
        File file = new File(fileName);

        try (FileInputStream fileInputStream = new FileInputStream(file);) {
            byte[] bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream.read(bytesArray);
            return bytesArray;
        }
    }
}
