package model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by gomes on 28/07/2016.
 */
public class Galeria implements Serializable{
    public static final long serialVersionUID = 1L;
    public Integer gal_id, esp_id;
    public byte[] imagem;

    public Galeria() {
       super();
    }

    @Override
    public String toString() {
        return "Galeria{" +
                "gal_id=" + gal_id +
                ", esp_id=" + esp_id +
                ", imagem=" + Arrays.toString(imagem) +
                '}';
    }
}
