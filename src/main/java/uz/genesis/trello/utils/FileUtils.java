package uz.genesis.trello.utils;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileUtils {

    public File createDir(File file) {
        try {
            if (!file.exists()) {
                if (file.mkdirs()) {
                    if (file.isDirectory()) {
                        return file;
                    }
                }
            } else {
                return file;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
