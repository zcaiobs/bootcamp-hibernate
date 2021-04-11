package app.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String solucao;
    @NotNull
    @OneToOne
    private Avaliacao avaliacao;
    @NotNull
    @OneToOne
    private Aluno aluno;

    public Resposta() {
    }

    public Resposta(String solucao, Avaliacao avaliacao, Aluno aluno) {
        this.solucao = solucao;
        this.avaliacao = avaliacao;
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "id=" + id +
                ", solucao='" + solucao + '\'' +
                ", avaliacao=" + avaliacao +
                ", aluno=" + aluno +
                '}';
    }
}
