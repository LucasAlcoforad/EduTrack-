import Controller.AlunoController;
import Controller.DisciplinaController;
import Controller.NotaController;
import Controller.dto.NotaDto;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        //13122

        AlunoController alunoController = new AlunoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        NotaController notaController = new NotaController();
        disciplinaController.createDisciplina("DB");
        /*AlunoDto alunoDto = new AlunoDto("lucas",
                LocalDate.of(2002, 7,22),
                "lucas22");*/
        //System.out.println(matriculaController.createMatricula(alunoController.getAlunoById(13122),"DB"));
        NotaDto notaDto = new NotaDto(98356, 13122, 9.8, LocalDate.now());
        System.out.println(notaController.createNota(notaDto));
    }
}