package Database;

import java.util.Date;

public class ActivitatiCurs {
    private int idActivitate;
    private String tipActivitate;
    private Date data;
    private int nrMaxParticipanti;
    private int idProfesor;

    public ActivitatiCurs(int id_activitate, String tip_activitate, Date data, int nr_max_participanti, int id_profesor) {
        this.idActivitate = id_activitate;
        this.tipActivitate = tip_activitate;
        this.data = data;
        this.nrMaxParticipanti = nr_max_participanti;
        this.idProfesor = id_profesor;
    }
    public ActivitatiCurs() {
    }

    public int getIdActivitate() {
        return idActivitate;
    }

    public void setIdActivitate(int idActivitate) {
        this.idActivitate = idActivitate;
    }

    public String getTipActivitate() {
        return tipActivitate;
    }

    public void setTipActivitate(String tipActivitate) {
        this.tipActivitate = tipActivitate;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNrMaxParticipanti() {
        return nrMaxParticipanti;
    }

    public void setNrMaxParticipanti(int nrMaxParticipanti) {
        this.nrMaxParticipanti = nrMaxParticipanti;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

}
