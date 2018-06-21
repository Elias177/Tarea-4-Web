package clases;

public class Comentario {
    private long id_comentario;
    private String comentario;
    private Long autor;

    public Comentario(String comentario, Long autor) {
        this.comentario = comentario;
        this.autor = autor;
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

    public Long getAutor() {
        return autor;
    }

    public void setAutor(Long autor) {
        this.autor = autor;
    }

}
