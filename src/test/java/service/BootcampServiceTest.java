package service;

import app.domain.Aluno;
import app.domain.Avaliacao;
import app.domain.Correcao;
import app.domain.Resposta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BootcampServiceTest {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Bootcamp-PU");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Test
    @DisplayName("Salvar aluno no banco de dados.")
    void saveAlunoTest(){
        Aluno aluno = new Aluno("Flash", "flash@gmail.com", 23);

        entityManager.getTransaction().begin();
        entityManager.persist(aluno);
        entityManager.getTransaction().commit();

        Aluno alunoResult = entityManager.createQuery("select a from Aluno a where a.email = :email", Aluno.class)
                .setParameter("email", aluno.getEmail()).getSingleResult();
        Assertions.assertEquals(aluno.getEmail(), alunoResult.getEmail());

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("Salvar avaliação no banco de dados.")
    void saveAvaliacaoTest(){
        Avaliacao avaliacao = new Avaliacao("The Hibernate","hibernate hibernate hibernate");

        entityManager.getTransaction().begin();
        entityManager.persist(avaliacao);
        entityManager.getTransaction().commit();

        Avaliacao avaliacaoResult = entityManager.createQuery("select a from Avaliacao a where a.titulo = :titulo", Avaliacao.class)
                .setParameter("titulo", avaliacao.getTitulo()).getSingleResult();
        Assertions.assertEquals(avaliacao.getTitulo(), avaliacaoResult.getTitulo());

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("Salvar resposta no banco de dados.")
    void saveRespostaTest(){
        Aluno aluno = entityManager.createQuery("select a from Aluno a where a.id = :id", Aluno.class)
                .setParameter("id", 5L).getSingleResult();
        Avaliacao avaliacao = entityManager.createQuery("select a from Avaliacao a where a.id = :id", Avaliacao.class)
                .setParameter("id", 2L ).getSingleResult();
        Resposta resposta = new Resposta("Resposta 2", avaliacao, aluno);

        entityManager.getTransaction().begin();
        entityManager.persist(resposta);
        entityManager.getTransaction().commit();

        Resposta respostaResult = entityManager.createQuery("select r from Resposta r where r.id = :id", Resposta.class)
                .setParameter("id", 1L).getSingleResult();
        Assertions.assertEquals(resposta.getSolucao(), respostaResult.getSolucao());
    }

    @Test
    @DisplayName("Salvar correção no banco de dados.")
    void saveCorrecaoTest(){
        Resposta resposta = entityManager.createQuery("select r from Resposta r where r.id = :id", Resposta.class)
                .setParameter("id", 2L).getSingleResult();
        Correcao correcao = new Correcao(9.50F, resposta);

        entityManager.getTransaction().begin();
        entityManager.persist(correcao);
        entityManager.getTransaction().commit();

        Correcao correcaoResult = entityManager.createQuery("select c from Correcao c where c.id = :id", Correcao.class)
                .setParameter("id", 2L).getSingleResult();
        Assertions.assertEquals(correcao.getResposta(), correcaoResult.getResposta());

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("Buscar aluno por correção.")
    void findAlunoByCorrecaoTest(){
        List<Correcao> result = entityManager.createQuery("select c from Correcao c", Correcao.class)
                .getResultList();
        String nomeAluno = result.get(0).getResposta().getAluno().getNome();
        Assertions.assertNotNull(nomeAluno);
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("Buscar respostas por aluno.")
    void findAllRespostaByAlunoTest(){
        Aluno aluno = new Aluno("Flash", "flash@gmail.com", 23);
        aluno.setId(5L);

        entityManager.getTransaction().begin();
        aluno = entityManager.merge(aluno);

        List<Resposta> respostas = entityManager.createQuery("select r from Resposta r where r.aluno = :aluno", Resposta.class)
                .setParameter("aluno", aluno).getResultList();

        entityManager.close();
        entityManagerFactory.close();

        respostas.forEach(System.out::println);
    }

}
