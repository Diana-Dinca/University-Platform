package Database;

public class Procentaje {
    private int idProcentaje;
    private int seminar;
    private int laborator;
    private int curs;
    private int idCurs;

    public Procentaje(int idProcentaje, int seminar, int laborator, int curs, int idCurs) {
        this.idProcentaje = idProcentaje;
        this.seminar = seminar;
        this.laborator = laborator;
        this.curs = curs;
        this.idCurs = idCurs;
    }

    public Procentaje() {
    }

    public int getIdProcentaje() {
        return idProcentaje;
    }

    public void setIdProcentaje(int idProcentaje) {
        this.idProcentaje = idProcentaje;
    }

    public int getSeminar() {
        return seminar;
    }

    public void setSeminar(int seminar) {
        this.seminar = seminar;
    }

    public int getLaborator() {
        return laborator;
    }

    public void setLaborator(int laborator) {
        this.laborator = laborator;
    }

    public int getCurs() {
        return curs;
    }

    public void setCurs(int curs) {
        this.curs = curs;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }
}
