package model;

/**
 * Created by gomes on 28/07/2016.
 */
public class Galeria {
    private Integer gal_id, esp_id;
    private byte[] imagem;

    public Integer getGal_id() {
        return gal_id;
    }

    public void setGal_id(Integer gal_id) {
        this.gal_id = gal_id;
    }

    public Integer getEsp_id() {
        return esp_id;
    }

    public void setEsp_id(Integer esp_id) {
        this.esp_id = esp_id;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
