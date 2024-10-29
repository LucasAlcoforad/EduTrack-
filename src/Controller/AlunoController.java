package Controller;

import Controller.dto.AlunoDto;
import Entity.Aluno;
import service.AlunoService;

import java.sql.SQLException;
import java.util.Optional;

public class AlunoController {
    public int cadastrarAluno(AlunoDto createAlunoDto) throws SQLException {
        return new AlunoService().createAluno(createAlunoDto);
    }
    public Aluno getAlunoById(int id) throws ClassNotFoundException {
        var aluno = new AlunoService().getAlunoById(id);
        if (aluno == null){
            throw new IllegalArgumentException("Aluno nao cadastrado");
        }
        return aluno;
    }
    public void updateAluno()
}
