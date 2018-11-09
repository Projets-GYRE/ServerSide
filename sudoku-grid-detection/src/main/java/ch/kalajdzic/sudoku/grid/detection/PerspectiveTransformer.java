package ch.kalajdzic.sudoku.grid.detection;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class PerspectiveTransformer {


    public byte[] redress(byte[] content, Corners corners) {
        Mat imageSrc = Imgcodecs.imdecode(new MatOfByte(content), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        Mat src_mat = new Mat(4, 1, CvType.CV_32FC2);
        Mat dst_mat = new Mat(4, 1, CvType.CV_32FC2);

        int correction = 12;
        src_mat.put(0, 0, corners.getP1().x + correction, corners.getP1().y + correction, corners.getP2().x - correction, corners.getP2().y + correction, corners.getP3().x - correction, corners.getP3().y - correction, corners.getP4().x + correction, corners.getP4().y - correction);
        dst_mat.put(0, 0, 0.0, 0.0, 900.0, 0.0, 900.0, 900.0, 0.0, 900.0);
        Mat perspectiveTransform = Imgproc.getPerspectiveTransform(src_mat, dst_mat);
        Mat dst = imageSrc.clone();

        Imgproc.warpPerspective(imageSrc, dst, perspectiveTransform, new Size(900.0, 900.0));

        return MatUtility.matToByte(dst);
    }

}
