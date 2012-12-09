package inf.pucrio.modelagem.t3.utils;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.card.LuckCard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class DeckBuilder {

	public static Queue<LuckCard> buildLuckDeck(Game game) {
		LinkedList<LuckCard> deck = new LinkedList<LuckCard>();
		LuckCard card;

		/* Cartas de sorte /
		card = new LuckCard(game, true, false, 25, "Sorte:\n A prefeitura mandou abrir uma nova avenida, para o que desapropriou vários prédios. Em consequência seu terreno valorizou.");
		deck.add(card);
		card = new LuckCard(game, true, false, 150, "Sorte:\n Houve um assalto à sua loja, mas você estava segurado.");
		deck.add(card);
		card = new LuckCard(game, true, false, 80, "Sorte:\n Um amigo tinha lhe pedido um empréstimo e se esqueceu de devolver. Ele acaba de se lembrar.");
		deck.add(card);
		card = new LuckCard(game, true, false, 200, "Sorte:\n Você está com sorte. Suas ações na Bolsa de Valores estão em alta.");
		deck.add(card);
		card = new LuckCard(game, true, false, 50, "Sorte:\n Você trocou seu carro usado com um amigo e ainda saiu lucrando.");
		deck.add(card);
		card = new LuckCard(game, true, false, 50, "Sorte:\n Você acaba de receber uma parcela do seu 13º salário.");
		deck.add(card);
		card = new LuckCard(game, true, false, 100, "Sorte:\n Você tirou o primeiro lugar no Torneio de Tênis do seu clube. Parabéns!");
		deck.add(card);
		card = new LuckCard(game, true, false, 100, "Sorte:\n O seu cachorro policial tirou o 1º prêmio na exposição do Kennel Club.");
		deck.add(card);
		card = new LuckCard(game, true, false, 0, "Sorte:\n Avance até o ponto de partida (ganha 200!)", false, true);
		deck.add(card);
		card = new LuckCard(game, true, false, 0, "Sorte:\n Você apostou com os parceiros deste jogo e ganhou.");
		deck.add(card);
		card = new LuckCard(game, true, false, 45, "Sorte:\n Você saiu de férias e se hospedou na casa de um amigo. Você economizou com o hotel.");
		deck.add(card);
		card = new LuckCard(game, true, false, 100, "Sorte:\n Inesperadamente você recebeu uma herança que já estava esquecida.");
		deck.add(card);
		card = new LuckCard(game, true, false, 100, "Sorte:\n Você foi promovido a diretor da sua empresa.");
		deck.add(card);
		card = new LuckCard(game, true, false, 20, "Sorte:\n Você jogou na Loteria Esportiva com um grupo de amigos. Ganharam!");
		deck.add(card);

		
		/* Cartas de revés /
		card = new LuckCard(game, false, false, -15, "Revés:\n Um amigo pediu-lhe um empréstimo. Você não pode recusar.");
		deck.add(card);
		card = new LuckCard(game, false, false, -25, "Revés:\n Você vai casar e está comprando um apartamento novo.");
		deck.add(card);
		card = new LuckCard(game, false, false, -45, "Revés:\n O médico lhe recomendou repouso num bom hotel de montanha.");
		deck.add(card);
		card = new LuckCard(game, false, false, -30, "Revés:\n Você achou interessante assistir à estréia da temporada de ballet. Compre os ingressos.");
		deck.add(card);
		card = new LuckCard(game, false, false, -100, "Revés:\n Parabéns! Você convidou seus amigos para festejar o aniversário.");
		deck.add(card);
		card = new LuckCard(game, false, false, -100, "Revés:\n Você é papai outra vez! Despesas de maternidade.");
		deck.add(card);
		card = new LuckCard(game, false, false, -40, "Revés:\n Papai, os livros do ano passado não servem mais. Preciso de livros novos.");
		deck.add(card);
		card = new LuckCard(game, false, false, -30, "Revés:\n Você estacionou seu carro em um lugar proibido e entrou na contra mão.");
		deck.add(card);
		card = new LuckCard(game, false, false, -50, "Revés:\n Você acaba de receber a comunicação do Imposto de Renda.");
		deck.add(card);
		card = new LuckCard(game, false, false, -25, "Revés:\n Seu clube está ampliando as piscinas. Os sócios devem contribuir.");
		deck.add(card);
		card = new LuckCard(game, false, false, -30, "Revés:\n Renove a tempo a licença do seu automóvel.");
		deck.add(card);
		card = new LuckCard(game, false, false, -45, "Revés:\n Seus parentes do interior vieram passar umas \"férias\" na sua casa.");
		deck.add(card);
		card = new LuckCard(game, false, false, -50, "Revés:\n Seus filhos já vão para a escola. Pague a primeira mensalidade.");
		deck.add(card);
		card = new LuckCard(game, false, false, -50, "Revés:\n A geada prejudicou a sua safra de café.");
		deck.add(card);*/

		
		/* Carta de saída livre da prisão - sorte */
		card = new LuckCard(game, true, true, 0, "Sorte:\n Saída livre da prisão. (Conserve este cartão para quando lhe for preciso ou negocie-o em qualquer ocasião, por preço a combinar.)");
		deck.add(card);

		
		/* Carta de ida para prisão - revés */
		card = new LuckCard(game, false, true, 0, "Revés:\n Vá para a prisão sem receber nada. (Talvez eu lhe faça uma visita...)");
		deck.add(card);
		
		
		Collections.shuffle(deck);
		
		return deck;		
	}
	
}
