package Handle;

import Controller.AlunoController;
import Controller.DisciplinaController;
import Controller.NotaController;
import Controller.ProfessorController;
import Controller.dto.NotaDto;
import Controller.dto.UserDto;
import Entity.Aluno;
import Entity.Disciplina;
import Entity.Nota;
import Entity.Professor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class ProfessorHandle {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProfessorController professorController = new ProfessorController();
    private static final DisciplinaController disciplinaController = new DisciplinaController();
    private static final NotaController notaController = new NotaController();
    private static final AlunoController alunoController = new AlunoController();

    public static void handleProfessorArea() throws SQLException {
        int option;
        do {
            System.out.println("""
                    Digite 1 para se cadastrar como professor,
                    Digite 2 para fazer login ou
                    Digite 0 para voltar ao menu.
                    """);
            option = readInt();
            switch (option) {
                case 1 -> cadastrarProfessor();
                case 2 -> loginProfessor();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    private static void handleLoggedProfessorArea(Professor professor) throws SQLException {
        professor = professorController.getProfessorById(professor.getId());
        int option;
        do {
            System.out.printf("""
                    Bem-vindo, %s!
                    Digite 1 para ver seus dados,
                    Digite 2 para alterar sua senha,
                    Digite 3 para criar uma disciplina,
                    Digite 4 para lançar nota para um aluno,
                    Digite 5 para deletar sua conta,
                    Digite 6 para deletar uma disciplina,
                    Digite 7 para corrigir uma nota,
                    Digite 8 para deletar uma nota ou
                    Digite 0 para sair.
                    %n""", professor.getNome());
            option = readInt();
            switch (option) {
                case 1 -> viewProfessorData(professor);
                case 2 -> updateProfessor(professor);
                case 3 -> createDisciplina(professor);
                case 4 -> lancarNota(professor);
                case 5 -> deleteProfessor(professor);
                case 6 -> deleteDisciplina(professor);
                case 7 -> updateNota(professor);
                case 8 -> deleteNota(professor);
                case 0 -> System.out.println("Saindo da área do professor...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    private static void cadastrarProfessor() throws SQLException {
        System.out.println("Qual será o seu nome de usuário?");
        String nome;
        nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio. Tente novamente.");
        }
        LocalDate data = readDate("Qual a sua data de nascimento? OBS: Formatação (YEAR-MONTH-DAY)");
        System.out.println("Crie sua senha para o login:");
        String senha = scanner.nextLine().trim();
        var professor = professorController.createProfessor(new UserDto(nome, data, senha));
        System.out.println("Cadastro realizado com sucesso! Seu registro é: " + professor);
    }

    private static void loginProfessor() throws SQLException {
        System.out.println("Qual seu registro?");
        int registro = readInt();
        Professor professor = professorController.getProfessorById(registro);

        if (professor == null) {
            System.out.println("Registro não encontrado.");
            return;
        }
        System.out.println("Qual a sua senha?");
        String senha = scanner.nextLine().trim();
        if (!senha.equals(professor.getPassword())) {
            System.out.println("Senha errada");
            loginProfessor();
        }
        handleLoggedProfessorArea(professor);
    }

    private static void viewProfessorData(Professor professor) {
        System.out.println("Seus dados:");
        System.out.println("Nome: " + professor.getNome());
        System.out.println("Data de Nascimento: " + professor.getDataNascimento());
        System.out.println("Registro: " + professor.getId());
        System.out.println("Disciplinas: " + professor.getDisciplinas());
    }

    private static void updateProfessor(Professor professor) throws SQLException {
        System.out.println("Digite sua nova senha:");
        String novaSenha = scanner.nextLine().trim();
        professor.setPassword(novaSenha);
        UserDto dto = new UserDto(professor.getNome(), professor.getDataNascimento(), professor.getPassword());
        professorController.updateProfessor(dto, professor.getId());
        System.out.println("Senha atualizada com sucesso!");
    }

    private static void createDisciplina(Professor professor) throws SQLException {
        System.out.println("Qual o nome da disciplina?");
        String nome = scanner.nextLine().trim();
        if (disciplinaController.getDisciplinaByName(nome)!=null){
            System.out.println("Nome ja em uso utilize outro");
            createDisciplina(professor);
        }
        Disciplina disciplina = disciplinaController.createDisciplina(nome, professor);
        professor.getDisciplinas().add(nome);
        System.out.println("Disciplina criada com sucesso! ID: " + disciplina.getId());
    }

    private static void lancarNota(Professor professor) throws SQLException {
        System.out.println("Qual o ID do aluno?");
        int alunoId = readInt();
        System.out.println("Qual o nome da disciplina?");
        String disciplina = scanner.nextLine().trim();
        if (!professor.getDisciplinas().contains(disciplina)){
            System.out.println("O senhor nao leciona essa cadeira, tente outra");
            lancarNota(professor);
        } else {
            LocalDate data = readDate("Qual dia foi a prova? OBS: Formatação (YEAR-MONTH-DAY)");
            System.out.println("Qual a nota?");
            double valor = Double.parseDouble(scanner.nextLine().trim());
            Nota nota = notaController.createNota(new NotaDto(disciplina,alunoId,valor,data));
            if (nota==null){
                System.out.println("Duas provas nao podem ser dadas no mesmo dia");
                lancarNota(professor);
            }
            System.out.println("Nota lançada com sucesso!");
        }
    }

    private static void deleteProfessor(Professor professor) throws SQLException {
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine().trim();
        if (!senha.equals(professor.getPassword())) {
            System.out.println("Senha errada");
            return;
        }
        if (professorController.deleteProfessor(professor.getId())) {
            System.out.println("Professor deletado.");
        }
    }

    private static void deleteDisciplina(Professor professor) throws SQLException {
        System.out.println("Digite o nome da disciplina que deseja deletar:");
        String nomeDisciplina = scanner.nextLine().trim();
        Disciplina disciplina = disciplinaController.getDisciplinaByName(nomeDisciplina);
        if (disciplina == null || !Objects.equals(disciplina.getProfessor(), professor.getNome())) {
            System.out.println("Disciplina não encontrada ou você não tem permissão para deletá-la.");
            return;
        }

        if (disciplinaController.deleteDisciplina(disciplina.getId())) {
            System.out.println("Disciplina deletada com sucesso.");
        } else {
            System.out.println("Erro ao deletar a disciplina. Tente novamente.");
        }
    }

    private static void updateNota(Professor professor) throws SQLException {
        System.out.println("Digite o nome da disciplina que deseja deletar:");
        String nomeDisciplina = scanner.nextLine().trim();
        Disciplina disciplina = disciplinaController.getDisciplinaByName(nomeDisciplina);
        if (disciplina == null || !professor.getDisciplinas().contains(nomeDisciplina)) {
            System.out.println("Disciplina não encontrada ou você não tem permissão para deletá-la.");
            return;
        }
        LocalDate data = readDate("Qual foi a data de realizaçao da prova? OBS: Formatação (YEAR-MONTH-DAY)");
        System.out.println("Qual a matricula do aluno");
        Aluno aluno = alunoController.getAlunoById(readInt());
        if (aluno == null){
            System.out.println("Aluno nao encontrado");
            updateNota(professor);
        }
        Nota nota = notaController.getNotaByData(data, disciplina.getId(), aluno.getId());
        if (nota==null){
            System.out.println("Nenhuma prova dessa disciplina foi realizada nesse dia");
            updateProfessor(professor);
        }
        System.out.println("Digite a nova nota:");
        double novaNota = Double.parseDouble(scanner.nextLine().trim());
        if (notaController.updateNota(novaNota, nota.getId())) {
            System.out.println("Nota atualizada com sucesso.");
        } else {
            System.out.println("Erro ao atualizar a nota. Tente novamente.");
        }
    }

    private static void deleteNota(Professor professor) throws SQLException {
        System.out.println("Digite o nome da disciplina que deseja deletar:");
        String nomeDisciplina = scanner.nextLine().trim();
        Disciplina disciplina = disciplinaController.getDisciplinaByName(nomeDisciplina);
        if (disciplina == null || !professor.getDisciplinas().contains(nomeDisciplina)) {
            System.out.println("Disciplina não encontrada ou você não tem permissão para deletá-la.");
            return;
        }
        LocalDate data = readDate("Qual foi a data de realizaçao da prova? OBS: Formatação (YEAR-MONTH-DAY)");
        System.out.println("Qual a matricula do aluno");
        Aluno aluno = alunoController.getAlunoById(readInt());
        if (aluno == null){
            System.out.println("Aluno nao encontrado");
            updateNota(professor);
        }
        Nota nota = notaController.getNotaByData(data, disciplina.getId(), aluno.getId());
        if (nota==null){
            System.out.println("Nenhuma prova dessa disciplina foi realizada nesse dia");
            updateProfessor(professor);
        }
        if (notaController.deleteNota(nota.getId())) {
            System.out.println("Nota deletada com sucesso.");
        } else {
            System.out.println("Erro ao atualizar a nota. Tente novamente.");
        }
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
