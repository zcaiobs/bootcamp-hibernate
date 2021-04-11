package app.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Correcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Float nota;
    @NotNull
    @OneToOne
    private Resposta resposta;

    public Correcao() {
    }

    public Correcao(Float nota, Resposta resposta) {
        this.nota = nota;
        this.resposta = resposta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Correcao{" +
                "id=" + id +
                ", nota=" + nota +
                ", resposta=" + resposta +
                '}';
    }
}
