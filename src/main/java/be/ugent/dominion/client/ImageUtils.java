package be.ugent.dominion.client;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageUtils {

    public static Image convertToGrayScale(Image image) {
        // Letterlijke kopie van
        // https://stackoverflow.com/questions/70466630/javafx-convert-image-to-greysacale
        WritableImage result = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader preader = image.getPixelReader();
        PixelWriter pwriter = result.getPixelWriter();

        for (int i = 0; i < result.getWidth(); i++)
            for (int j = 0; j < result.getHeight(); j++)
                pwriter.setColor(i , j, preader.getColor(i, j).grayscale());
        return result;
    }

}
