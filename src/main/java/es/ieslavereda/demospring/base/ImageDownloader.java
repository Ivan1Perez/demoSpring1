package es.ieslavereda.demospring.base;

import com.squareup.picasso.Picasso;
import javax.swing.text.html.ImageView;

public class ImageDownloader {

    public static void downloadImage(String url, ImageView imageView){
        Picasso.get().load(url).into(imageView);
    }

}
