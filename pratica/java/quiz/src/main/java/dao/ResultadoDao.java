package dao;

import java.util.List;

import javax.persistence.EntityManager;

import exceptions.SemJogadoresException;
import modelo.Resultado;

public class ResultadoDao {
	

	private EntityManager em;

	public ResultadoDao(EntityManager em) {
		this.em = em;
	}
	
	public void salvar(Resultado resultado) {
		this.em.persist(resultado);
	}

	public List<Resultado> buscarTodos() throws SemJogadoresException{
		String jpql = "SELECT r FROM Resultado r ORDER BY r.acertos DESC";
		List<Resultado> listaDeJogadores = em.createQuery(jpql, Resultado.class).getResultList();
		if(listaDeJogadores.isEmpty())
			throw new SemJogadoresException("Nenhum jogador cadastrado no momento");			
		return listaDeJogadores;
	}
}
