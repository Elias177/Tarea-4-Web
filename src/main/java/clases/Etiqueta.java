package clases;

import javax.persistence.*;

@Entity
public class Etiqueta {

    public Etiqueta() {
    }

    public Long getId_etiqueta() {
        return id_etiqueta;
    }

    public void setId_etiqueta(Long id_etiqueta) {
        this.id_etiqueta = id_etiqueta;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "et_generator")
    @SequenceGenerator(name="et_generator", sequenceName = "et_seq", allocationSize=1)
    private Long id_etiqueta;
    private String etiqueta;



    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    private boolean activo;

    public Etiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
