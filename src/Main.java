import Controller.AlunoController;
import Controller.dto.AlunoDto;
import Entity.Aluno;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //78031
        AlunoController controller = new AlunoController();
        AlunoDto alunoDto = new AlunoDto("aquino",new Date(2002 - 8 - 22));
        System.out.println(controller.deleteAluno(78031));
        //Aluno aluno = controller.getAlunoById()
    }
}