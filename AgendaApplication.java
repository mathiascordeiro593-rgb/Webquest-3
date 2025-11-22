// Integrantes da equipe:
// Mathias Falcão Rico da Silva Cordeiro de Oliveira
// Márcia de Oliveira Brito Santos

import java.util.List;
import java.util.Scanner;

public class AgendaApplication {

    public static void main(String[] args) {

        AgendaManager agenda = new AgendaManager();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n====== AGENDA DE CONTATOS ======");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Buscar Contato");
            System.out.println("3. Remover Contato");
            System.out.println("4. Listar Todos os Contatos");
            System.out.println("5. Salvar em CSV");
            System.out.println("6. Carregar de CSV");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    try {
                        Contato novo = new Contato(nome, telefone, email);
                        agenda.adicionarContato(novo);
                        System.out.println("Contato adicionado com sucesso!");
                    } catch (ContatoExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Digite o nome do contato: ");
                    String nomeBusca = scanner.nextLine();

                    try {
                        Contato encontrado = agenda.buscarContato(nomeBusca);
                        System.out.println("Contato encontrado:");
                        System.out.println(encontrado);
                    } catch (ContatoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do contato a remover: ");
                    String nomeRemover = scanner.nextLine();

                    try {
                        agenda.removerContato(nomeRemover);
                        System.out.println("Contato removido com sucesso!");
                    } catch (ContatoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\n---- Lista de Contatos ----");
                    List<Contato> lista = agenda.listarTodosContatos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum contato cadastrado.");
                    } else {
                        for (Contato c : lista) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 5:
                    System.out.print("Nome do arquivo CSV para salvar: ");
                    String arquivoSalvar = scanner.nextLine();
                    agenda.salvarContatosCSV(arquivoSalvar);
                    System.out.println("Arquivo salvo com sucesso!");
                    break;

                case 6:
                    System.out.print("Nome do arquivo CSV para carregar: ");
                    String arquivoCarregar = scanner.nextLine();
                    agenda.carregarContatosCSV(arquivoCarregar);
                    System.out.println("Arquivo carregado com sucesso!");
                    break;

                case 7:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}
