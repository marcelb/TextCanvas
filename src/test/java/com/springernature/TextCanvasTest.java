package com.springernature;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class TextCanvasTest {
    protected String getFile(String fileName){
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
