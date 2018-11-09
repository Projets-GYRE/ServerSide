package ch.kalajdzic.sudoku.grid.detection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageFactory {

    public Photo create(String base64) {
        byte[] decoded = Base64.getDecoder().decode(base64.getBytes());
        return new Photo(decoded);
    }


    public Photo create(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return create(encodedfile);
    }

}
