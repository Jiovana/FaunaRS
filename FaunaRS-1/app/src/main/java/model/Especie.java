package model;

/**
 * Created by gomes on 28/07/2016.
 */
public class Especie {
    private String nome;//nome comum
    private String caracter2, caracter3, caracter4, caracter5, caracter6;
    private Integer id;
    private String caracteristicas, habitos, localizacao;
    private String filo, classe, ordem, genero, especie;//especie = nome cientifico
    private String nota;
    private byte[] img1, img2;


    public Especie() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaracter2() {
        return caracter2;
    }

    public void setCaracter2(String caracter2) {
        this.caracter2 = caracter2;
    }

    public String getCaracter3() {
        return caracter3;
    }

    public void setCaracter3(String caracter3) {
        this.caracter3 = caracter3;
    }

    public String getCaracter4() {
        return caracter4;
    }

    public void setCaracter4(String caracter4) {
        this.caracter4 = caracter4;
    }

    public String getCaracter5() {
        return caracter5;
    }

    public void setCaracter5(String caracter5) {
        this.caracter5 = caracter5;
    }

    public String getCaracter6() {
        return caracter6;
    }

    public void setCaracter6(String caracter6) {
        this.caracter6 = caracter6;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getHabitos() {
        return habitos;
    }

    public void setHabitos(String habitos) {
        this.habitos = habitos;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getFilo() {
        return filo;
    }

    public void setFilo(String filo) {
        this.filo = filo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img2) {
        this.img2 = img2;
    }

}
