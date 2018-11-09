package ch.kalajdzic.sudoku.digit.recognition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class LabelsReader {

    private final File file;

    LabelsReader(File file) {
        this.file = file;
    }

    List<Integer> read() throws IOException {
        try (final DataInputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {

            final int magicNumberLabels = stream.readInt();
            final int numberOfLabels = stream.readInt();

            final List<Integer> labels = new ArrayList<>();
            for (int i = 0; i < numberOfLabels; i++) {
                labels.add(stream.readUnsignedByte());
            }
            return labels;
        }
    }
}