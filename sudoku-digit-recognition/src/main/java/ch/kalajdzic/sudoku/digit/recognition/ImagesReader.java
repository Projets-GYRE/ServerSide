package ch.kalajdzic.sudoku.digit.recognition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ImagesReader {

    private final File file;

    ImagesReader(File file) {
        this.file = file;
    }

    private static List<Float> parseImageData(DataInputStream dataInputStreamImages) throws IOException {
        final List<Float> imageData = new ArrayList<>();
        for (int r = 0; r < 28; r++) {
            for (int c = 0; c < 28; c++) {
                float value = (dataInputStreamImages.readUnsignedByte() / 255.0f);// normalise data  ( 0 - 255 )/255.0  -> ( 0 - 1)
                imageData.add(value);
//                if (value < 127){
//                    imageData.add(0);
//                } else {
//                    imageData.add(1);
//                }
            }
        }
        return imageData;
    }

    public List<List<Float>> read() throws IOException {
        final List<List<Float>> images = new ArrayList<>();

        try (final DataInputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

            int magicNumber = stream.readInt();
            int numberOfImages = stream.readInt();
            int row = stream.readInt(); // 28
            int col = stream.readInt(); // 28


            for (int i = 0; i < numberOfImages; i++) {
                List<Float> imageData = parseImageData(stream);
                images.add(imageData);
            }
        }
        return images;
    }
}
