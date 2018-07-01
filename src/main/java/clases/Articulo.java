package clases;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ARTICULO")
public class Articulo {

    public Articulo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    @Column(name = "cuerpo",columnDefinition = "TEXT")
    private String cuerpo;

    @OneToOne
    private Usuario autor;

    private Date fecha;
    @OneToMany(mappedBy = "articulo",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comentario> listaComentarios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ARTICULO_ETIQUETA", joinColumns = { @JoinColumn(name = "ARTICULO_ID") }, inverseJoinColumns = { @JoinColumn(name = "LISTAETIQUETA_ID_ETIQUETA") })
    private List<Etiqueta> listaEtiqueta;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    Set<Reaccion> reaccionSet = new HashSet<>();

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    private boolean activo;


    public Articulo(String titulo, String cuerpo, Usuario autor, Date fecha, List<Comentario> listaComentarios, List<Etiqueta> listaEtiqueta) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
        this.listaComentarios = listaComentarios;
        this.listaEtiqueta = listaEtiqueta;
    }

    public List<Comentario> getListaComentarios() {

        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public List<Etiqueta> getListaEtiqueta() {
        return listaEtiqueta;
    }

    public void setListaEtiqueta(List<Etiqueta> listaEtiqueta) {
        this.listaEtiqueta = listaEtiqueta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}