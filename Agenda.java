import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato c) {
        contatos.add(c);
        System.out.println("Contato adicionado com sucesso!");
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato na agenda.");
            return;
        }

        System.out.println("=== Lista de Contatos ===");
        for (Contato c : contatos) {
            System.out.println(c);
        }
    }

    public void buscarContato(String nome) {
        boolean encontrado = false;

        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Contato encontrado:");
                System.out.println(c);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Contato não encontrado.");
        }
    }
}
