package dao;

import java.util.List;

import javax.persistence.EntityManager;

import Exception.SemPerguntasException;
import modelo.Questao;

public class QuestaoDao {
	
	private EntityManager em;

	public QuestaoDao(EntityManager em) {
		this.em = em;
	}
	
	public void salvar(Questao questao) {
		this.em.persist(questao);
	}
	
	public List<Questao> buscarTodos() throws SemPerguntasException{
		String jpql = "SELECT q FROM Questao q";
		List<Questao> listaDePerguntas = em.createQuery(jpql, Questao.class).getResultList();
		if(listaDePerguntas.isEmpty())
			throw new SemPerguntasException("Nenhuma pergunta cadastrada no momento");
		return listaDePerguntas;
	}
}
