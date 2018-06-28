package clases;

import javax.persistence.*;

@Entity
public class Comentario {

    @Id
    @GeneratedValue
    private long id_comentario;
    private String comentario;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Articulo articulo;

    public Comentario(String comentario, Usuario autor) {
        this.comentario = comentario;
        this.autor = autor;
    }

    public Comentario() {
    }

    public long getId() {

        return id_comentario;
    }

    public void setId(long id) {
        this.id_comentario = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

}
