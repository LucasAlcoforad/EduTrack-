import Controller.AlunoController;
import Controller.DisciplinaController;
import Controller.NotaController;
import Controller.ProfessorController;
import Controller.dto.NotaDto;
import Controller.dto.UserDto;
import Entity.Aluno;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        AlunoController alunoController = new AlunoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        NotaController notaController = new NotaController();
        ProfessorController professorController = new ProfessorController();
        Integer opçao;
        do {
            System.out.println("""
                    Digite 1 para se encaminhar para a area do aluno,
                    Digite 2 para se encaminhar para a area do professor ou
                    Digite 0 para sair do sistema.
                    """);
            opçao = scanner.nextInt();
            if (opçao==1){
                do {
                    System.out.println("""
                        Digite 1 para se cadastrar como aluno,
                        Digite 2 para fazer login ou
                        Digite 0 para voltar o menu.
                        """);
                    opçao = scanner.nextInt();
                    if (opçao==1){
                        do {
                            System.out.println("Qual sera o seu nome de usuario?");
                            String nome = scanner.nextLine();
                        }


                    }
                    opçao = null;
                }

            }
        }while (opçao!=0);
    }
}