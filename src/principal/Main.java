package principal;

import modelo.Livro;
import modelo.Usuario;
import servico.Biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        boolean continuar = true;

        while (continuar) {
            System.out.println(" --- Seja Bem Vindo a nossa Biblioteca ---");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Emprestar livro");
            System.out.println("3. Devolver livro");
            System.out.println("4. Listar livros emprestados de um usuário");
            System.out.println("5. Adicionar novo usuário");
            System.out.println("6. Listar usuários");
            System.out.println("7. Listar livros disponíveis");
            System.out.println("8. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String titulo = scanner.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autor = scanner.nextLine();
                    System.out.println("Digite o ano de publicação:");
                    int anoPublicacao = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    Livro novoLivro = new Livro(titulo, autor, anoPublicacao);
                    biblioteca.adicionarLivro(novoLivro); // Método para adicionar livro
                    System.out.println("Livro '" + titulo + "' adicionado com sucesso.");
                    break;

                case 2:
                    System.out.println("Digite o nome do usuário que deseja emprestar o livro:");
                    String nomeUsuarioEmprestimo = scanner.nextLine();
                    Usuario usuarioEmprestimo = biblioteca.buscarUsuarioPorNome(nomeUsuarioEmprestimo);

                    if (usuarioEmprestimo == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.println("Digite o título do livro que deseja emprestar:");
                    String tituloEmprestimo = scanner.nextLine();
                    Livro livroParaEmprestar = buscarLivroPorTitulo(biblioteca, tituloEmprestimo);

                    if (livroParaEmprestar != null) {
                        String resultado = biblioteca.emprestarLivro(usuarioEmprestimo, livroParaEmprestar);
                        System.out.println(resultado);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 3:
                    System.out.println("Digite o nome do usuário que deseja devolver o livro:");
                    String nomeUsuarioDevolucao = scanner.nextLine();
                    Usuario usuarioDevolucao = biblioteca.buscarUsuarioPorNome(nomeUsuarioDevolucao);

                    if (usuarioDevolucao == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.println("Digite o título do livro que deseja devolver:");
                    String tituloDevolucao = scanner.nextLine();
                    Livro livroParaDevolver = buscarLivroPorTitulo(usuarioDevolucao, tituloDevolucao);

                    if (livroParaDevolver != null) {
                        String resultado = biblioteca.receberDevolucao(usuarioDevolucao, livroParaDevolver);
                        System.out.println(resultado);
                    } else {
                        System.out.println("Livro não encontrado na lista de empréstimos.");
                    }
                    break;

                case 4:
                    System.out.println("Digite o nome do usuário:");
                    String nomeUsuarioConsulta = scanner.nextLine();
                    Usuario usuarioConsulta = biblioteca.buscarUsuarioPorNome(nomeUsuarioConsulta);

                    if (usuarioConsulta != null) {
                        usuarioConsulta.listarLivrosEmprestados();
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Digite o nome do novo usuário:");
                    String nomeNovoUsuario = scanner.nextLine();
                    Usuario novoUsuario = new Usuario(nomeNovoUsuario);
                    biblioteca.adicionarUsuario(novoUsuario);
                    System.out.println("Usuário '" + nomeNovoUsuario + "' adicionado com sucesso.");
                    break;

                case 6:
                    biblioteca.exibirUsuarios();
                    break;

                case 7:
                    biblioteca.exibirLivrosDisponiveis();
                    break;

                case 8:
                    continuar = false;
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static Livro buscarLivroPorTitulo(Biblioteca biblioteca, String titulo) {
        for (Livro livro : biblioteca.getLivrosDisponiveis()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }
    private static Livro buscarLivroPorTitulo(Usuario usuario, String titulo) {
        for (Livro livro : usuario.getLivrosEmprestados()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }
}

