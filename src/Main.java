import Controller.AlunoController;
import Controller.DisciplinaController;
import Controller.NotaController;
import Controller.ProfessorController;
import Controller.dto.NotaDto;
import Controller.dto.UserDto;
import Entity.Aluno;
import Entity.Professor;
import Handle.AlunoHandle;
import Handle.ProfessorHandle;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProfessorController professorController = new ProfessorController();
    public static void main(String[] args) throws SQLException {
        int option;
        do {
            System.out.println("""
                    Digite 1 para se encaminhar para a área do aluno,
                    Digite 2 para se encaminhar para a área do professor ou
                    Digite 0 para sair do sistema.
                    """);
            option = readInt();
            switch (option) {
                case 1 -> AlunoHandle.handleAlunoArea();
                case 2 -> ProfessorHandle.handleProfessorArea();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);

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
}