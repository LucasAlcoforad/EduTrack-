package Controller;

import Controller.dto.AlunoDto;
import Entity.Aluno;
import service.AlunoService;

import java.sql.SQLException;
import java.util.Optional;

public class AlunoController {
    public int createAluno(AlunoDto createAlunoDto) throws SQLException {
        return new AlunoService().createAluno(createAlunoDto);
    }
    public Aluno getAlunoById(int id) {
        return new AlunoService().getAlunoById(id);
    }
    public boolean updateAluno(AlunoDto dto,
                            Integer id){
        return new AlunoService().updateAluno(dto, id);
    }
    public boolean deleteAluno(int id){
        return new AlunoService().deleteAluno(id);
    }
}
