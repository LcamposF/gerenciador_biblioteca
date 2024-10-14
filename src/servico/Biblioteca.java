package servico;

import modelo.Livro;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livrosDisponiveis;
    private List<Usuario> usuarios;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livrosDisponiveis = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livrosDisponiveis.add(livro);
        System.out.println("Livro " + livro.getTitulo() + " adicionado à biblioteca.");
    }

    public void removerLivro(Livro livro) {
        livrosDisponiveis.remove(livro);
    }

    public void exibirLivrosDisponiveis() {
        System.out.println("Livros disponíveis na biblioteca " + nome + ":");
        for (Livro livro : livrosDisponiveis) {
            livro.exibirInformacoes();
        }
    }

    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    public String emprestarLivro(Usuario usuario, Livro livro) {
        if (livrosDisponiveis.contains(livro)) {
            usuario.emprestarLivro(livro);
            removerLivro(livro);
            return "Livro '" + livro.getTitulo() + "' emprestado com sucesso a " + usuario.getNome() + ".";
        } else {
            return "Livro '" + livro.getTitulo() + "' não está disponível para empréstimo.";
        }
    }
    public String receberDevolucao(Usuario usuario, Livro livro) {
        if (usuario.devolverLivro(livro)) {
            adicionarLivro(livro);
            return "Livro '" + livro.getTitulo() + "' devolvido com sucesso por " + usuario.getNome() + ".";
        } else {
            return "Livro '" + livro.getTitulo() + "' não foi encontrado na lista de empréstimos de " + usuario.getNome() + ".";
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário " + usuario.getNome() + " adicionado à biblioteca.");
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public void exibirUsuarios() {
        System.out.println("Usuários registrados na biblioteca:");
        for (Usuario usuario : usuarios) {
            System.out.println("- " + usuario.getNome());
        }
    }
}
