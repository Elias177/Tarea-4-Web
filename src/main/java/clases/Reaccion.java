package clases;

import javax.persistence.*;

@Entity
public class Reaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rea_generator")
    @SequenceGenerator(name="rea_generator", sequenceName = "rea_seq", allocationSize=1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ARTICULO_ID")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @Column(name="reaccion")
    boolean reaccion;

    public Reaccion(Articulo articulo, Usuario usuario, boolean reaccion) {
        this.articulo = articulo;
        this.usuario = usuario;
        this.reaccion = reaccion;
    }

    public Reaccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isReaccion() {
        return reaccion;
    }

    public void setReaccion(boolean reaccion) {
        this.reaccion = reaccion;
    }
}
