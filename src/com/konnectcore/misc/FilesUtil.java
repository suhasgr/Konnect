package com.konnectcore.misc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class FilesUtil {
 
    public static String saveFile(File file, String fileName, String filesDirectory) throws IOException{
        FileInputStream in = null;
        FileOutputStream out = null;
         
        File dir = new File (filesDirectory);
        if ( !dir.exists() )
           dir.mkdirs();
         
        String targetPath =  dir.getPath() + File.separator + fileName;
        System.out.println("source file path ::"+file.getAbsolutePath());
        System.out.println("saving file to ::" + targetPath);
        File destinationFile = new File ( targetPath);
        try {
            in = new FileInputStream( file );
            out = new FileOutputStream( destinationFile );
            int c;
 
            while ((c = in.read()) != -1) {
                out.write(c);
            }
 
        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return targetPath; 
    }
    
    public static byte[] convertToByteArray(String filePath) throws FileNotFoundException{
    	
    	File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[2097152];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);      
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
        
        return bytes;
    }
}
