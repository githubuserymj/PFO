package com.offer.advice;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by YMJ on 2019-10-17.
 */
@Component
public class WriteAspectInfo {

    private static File methodRunInfoFile = new File("../methodRunInfoFile.info");;

    public File getFile() throws IOException {
        if(null == methodRunInfoFile || !methodRunInfoFile.exists()){
            methodRunInfoFile.createNewFile();
        }
        return methodRunInfoFile;
    }

    public void writeFile(String info) {
        FileOutputStream outputStream = null;
        try {
            outputStream= new FileOutputStream(methodRunInfoFile,true);
            outputStream.write(info.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
