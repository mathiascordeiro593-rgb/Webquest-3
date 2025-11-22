// Integrantes da equipe:
// Mathias Falcão Rico da Silva Cordeiro de Oliveira
// Márcia de Oliveira Brito Santos

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AgendaManager implements GerenciadorContatos {

    private List<Contato> contatos;

    public AgendaManager() {
        this.contatos = new ArrayList<>();
    }

    @Override
    public void adicionarContato(Contato contato) throws ContatoExistenteException {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(contato.getNome())) {
                throw new ContatoExistenteException("Contato já existe: " + contato.getNome());
            }
        }
        contatos.add(contato);
    }

    @Override
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        throw new ContatoNaoEncontradoException("Contato não encontrado: " + nome);
    }

    @Override
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        Contato contato = buscarContato(nome);
        contatos.remove(contato);
    }

    @Override
    public List<Contato> listarTodosContatos() {
        return contatos;
    }

    // --------------------------
    // Salvar contatos em CSV
    // --------------------------
    public void salvarContatosCSV(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Contato c : contatos) {
                writer.write(c.getNome() + ";" + c.getTelefone() + ";" + c.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    // --------------------------
    // Carregar contatos de CSV
    // --------------------------
    public void carregarContatosCSV(String nomeArquivo) {
        contatos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    contatos.add(new Contato(dados[0], dados[1], dados[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }

    // --------------------------
    // Contatos ordenados
    // --------------------------
    public List<Contato> listarContatosOrdenados() {
        List<Contato> ordenada = new ArrayList<>(contatos);
        ordenada.sort(Comparator.comparing(Contato::getNome));
        return ordenada;
    }

    // --------------------------
    // Buscar por domínio do email
    // --------------------------
    public List<Contato> buscarPorDominioEmail(String dominio) {
        List<Contato> resultado = new ArrayList<>();
        for (Contato c : contatos) {
            if (c.getEmail().endsWith(dominio)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
          }
