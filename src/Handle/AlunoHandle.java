package Handle;

import Controller.AlunoController;
import Controller.dto.UserDto;
import Entity.Aluno;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AlunoHandle {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AlunoController alunoController = new AlunoController();
    public static void handleAlunoArea() throws SQLException {
        int option;
        do {
            System.out.println("""
                    Digite 1 para se cadastrar como aluno,
                    Digite 2 para fazer login ou
                    Digite 0 para voltar ao menu.
                    """);
            option = readInt();
            switch (option) {
                case 1 -> cadastrarAluno();
                case 2 -> loginAluno();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    private static void handleLoggedAlunoArea(Aluno aluno) throws SQLException {
        int option;
        do {
            System.out.printf("""
                    Bem-vindo, %s!
                    Digite 1 para ver seus dados e suas notas,
                    Digite 2 para alterar sua senha,
                    Digite 3 para deletar sua conta ou
                    Digite 0 para sair.
                    %n""", aluno.getNome());
            option = readInt();
            switch (option) {
                case 1 -> viewAlunoData(aluno);
                case 2 -> updateAluno(aluno);
                case 3 -> deleteAluno(aluno);
                case 0 -> System.out.println("Saindo da área do aluno...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    private static void cadastrarAluno() throws SQLException {
        System.out.println("Qual será o seu nome de usuário?");
        String nome;
        while (true) {
            nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode ser vazio. Tente novamente.");
                continue;
            }
            if (alunoController.getAlunoByNome(nome) != null) {
                System.out.println("Nome de usuário já existente. Tente outro.");
            } else {
                break;
            }
        }
        LocalDate data = readDate("Qual a sua data de nascimento? OBS: Formatação (YEAR-MONTH-DAY)");
        System.out.println("Crie sua senha para o login:");
        String senha = scanner.nextLine().trim();
        var aluno = alunoController.createAluno(new UserDto(nome, data, senha));
        System.out.println("Cadastro realizado com sucesso! Sua matrícula é: " + aluno.getId());
    }

    private static void loginAluno() throws SQLException {
        System.out.println("Qual sua matrícula?");
        int matricula = readInt();
        Aluno aluno = alunoController.getAlunoById(matricula);

        if (aluno == null) {
            System.out.println("Matrícula não encontrada.");
            return;
        }
        System.out.println("Qual a sua senha?");
        String senha = scanner.nextLine().trim();
        if (!senha.equals(aluno.getPassword())){
            System.out.println("Senha errada");
            loginAluno();
        }
        handleLoggedAlunoArea(aluno);
    }

    private static void viewAlunoData(Aluno aluno) {
        System.out.println("Seus dados:");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Data de Nascimento: " + aluno.getDataNascimento());
        System.out.println("Matrícula: " + aluno.getId());
        System.out.println("Notas: " + aluno.getDisciplinas());
    }

    private static void updateAluno(Aluno aluno) throws SQLException {
        System.out.println("Digite sua nova senha:");
        String novaSenha = scanner.nextLine().trim();
        aluno.setPassword(novaSenha);
        UserDto dto = new UserDto(aluno.getNome(),aluno.getDataNascimento(),aluno.getPassword());
        alunoController.updateAluno(dto,aluno.id);
        System.out.println("Senha atualizada com sucesso!");
    }

    private static void deleteAluno(Aluno aluno) throws SQLException {
        System.out.println("Digite sua nova senha:");
        String senha = scanner.nextLine().trim();
        if (!senha.equals(aluno.getPassword())){
            System.out.println("Senha errada");
            return;
        }
        if (alunoController.deleteAluno(aluno.getId())){
            System.out.println("Aluno Deletado");
        }
        handleAlunoArea();
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }

    private static LocalDate readDate(String message) {
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.println(message);
                date = LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato YEAR-MONTH-DAY (exemplo: 2024-11-29).");
            }
        }
        return date;
    }
}
