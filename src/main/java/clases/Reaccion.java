package clases;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reaccion {

    @Id
    @GeneratedValue
    @Column(name="LIKESARTICULO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ARTICULO_ID")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @Column(name="reaccion")
    boolean reaccion;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
