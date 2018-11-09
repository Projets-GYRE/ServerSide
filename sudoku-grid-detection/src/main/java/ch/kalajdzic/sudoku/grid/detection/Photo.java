package ch.kalajdzic.sudoku.grid.detection;

import nu.pattern.OpenCV;

public class Photo {


    private byte[] content;

    Photo(byte[] content) {
        OpenCV.loadLocally();
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public FlatImage unwrap(CornerDetector cornerDetector, PerspectiveTransformer perspectiveTransformer) {
        Corners corners = detectCorners(cornerDetector);
        FlatImage flatImage = transform(corners, perspectiveTransformer);
        return flatImage;
    }

    public Corners detectCorners(CornerDetector cornerDetector) {
        return cornerDetector.detect(content);
    }

    public FlatImage transform(Corners corners, PerspectiveTransformer perspectiveTransformer) {
        byte[] bytes = perspectiveTransformer.redress(content, corners);
        return new FlatImage(bytes);
    }

}
