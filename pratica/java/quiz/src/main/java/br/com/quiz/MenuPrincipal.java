package br.com.quiz;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import Exception.SemJogadoresException;
import Exception.SemPerguntasException;
import dao.QuestaoDao;
import dao.ResultadoDao;
import modelo.Questao;
import modelo.Resultado;
import util.JPAUtil;
import java.util.Scanner;

public class MenuPrincipal {

	public static void main(String[] args) {
		
		int escolha = 3;
		int escolhaNumero;
		Boolean verdadeiroOuFalso = false;
		int acertou = 0;
		int errou = 0;
		String nome;
		try (Scanner leitura = new Scanner(System.in)) {
			Resultado resultado;
			EntityManager em = JPAUtil.getEntityManager();
			ResultadoDao resultadoDao = new ResultadoDao(em);
			QuestaoDao questaoDao = new QuestaoDao(em);
			

			//Entra no looping do menu	
			while(escolha != 0 ) {
				System.out.println("1 - Jogar novamente \n2 - Ver placar \n0 - Sair");
				escolha = leitura.nextInt();
				leitura.nextLine();
				if(escolha != 0 && escolha != 1 && escolha != 2) {
					System.out.println("\n||Escolha incorreta||\n\n");
				}else {
					
					//Escolha da op��o 1 - JOGAR NOVAMENTE
					if(escolha == 1) {
						errou = 0;
						acertou = 0;
						resultado = new Resultado();
						System.out.println("Digite o nome do jogador: ");
						nome = leitura.nextLine(); 
						resultado.setJogador(nome);
						try {
							List<Questao> todasQuestoes = questaoDao.buscarTodos();			
							
							
							for(Questao pergunta : todasQuestoes) {
								if(pergunta.isAtiva()) {
									System.out.println(pergunta.getPergunta());
									System.out.println("1 - Verdadeiro\t 2 - Falso ?");
									escolhaNumero = leitura.nextInt();
									if(escolhaNumero == 1) {
										verdadeiroOuFalso = true;
									}else if(escolhaNumero == 2){
										verdadeiroOuFalso = false;
									}
									if(verdadeiroOuFalso.equals(pergunta.isVerdadeira())) {
										acertou++;
										System.out.println("-------------------------");
										System.out.println("Parabens, voc� acertou!!");
										System.out.println("-------------------------\n");
									}else{
										errou++;
										System.out.println("-------------------------");
										System.out.println("Infelizmente voc� errou!!");
										System.out.println("-------------------------\n");
									}
								}
							}
							em.getTransaction().begin();					
							resultado.setAcertos(acertou);
							resultado.setErros(errou);
							resultadoDao.salvar(resultado);
							em.getTransaction().commit();	
						}catch(SemPerguntasException e) {
							System.out.println("\n-----------------------------------");
							System.out.println("Desculpe " + nome + " - " + e.getMessage());
							System.out.println("-----------------------------------\n");
						}
									
						
					//Escolha da op��o 2 - VER O PLACAR
					}else if(escolha == 2) {
						
						try {
							List<Resultado> todosJogadores = resultadoDao.buscarTodos();	
							System.out.println("PLACAR");
							todosJogadores.forEach(r -> System.out.println("JOGADOR: " + r.getJogador() + "\t    ACERTOS: " + r.getAcertos() + "\tDATA: " + r.getDataDaPartida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
							System.out.println("\n");
						}catch(SemJogadoresException e) {
							System.out.println("\n-----------------------------------");
							System.out.println(e.getMessage());
							System.out.println("-----------------------------------\n");
						}
										
						
						
					}
				}
			}
			em.close();
		}
		System.out.println("Saindo do programa...");
		
	}
}
