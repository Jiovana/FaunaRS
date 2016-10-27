package model;

import java.io.Serializable;

/**
 * Created by gomes on 28/07/2016.
 */
public class Especie implements Serializable{
    public static final long serialVersionUID = 1L;
    public String nome;//nome comum
    public String caracter2, caracter3, caracter4, caracter5, caracter6;
    public Integer id;
    public String caracteristicas, habitos, localizacao;
    public String filo, classe, ordem, familia, especie;//especie = nome cientifico
    public byte[] img1, img2;



    @Override
    public String toString(){
        return "Espécie{"
                + "id='" + id + '\''
                + ", nome='" + nome + '\''
                + ", características='" + caracteristicas + '\''
                + ", hábitos='" + habitos + '\''
                + ", localização='" + localizacao + '\''
                //+ ", urlImg1='" + urlImg1 + '\''
                //+ ", urlImg2='" + urlImg2 + '\''
                + ", caracter2='" + caracter2 + '\''
                + ", caracter3='" + caracter3 + '\''
                + ", caracter4='" + caracter4 + '\''
                + ", caracter5='" + caracter5 + '\''
                + ", caracter6='" + caracter6 + '\''
                + ", classe='" + filo + '\''
                + ", classe='" + classe + '\''
                + ", ordem='" + ordem + '\''
                + ", família='" + familia + '\''
                + ", espécie='" + especie + '\''
                + '}';
    }


    public Especie() {
        super();
    }


}
