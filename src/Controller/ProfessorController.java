package Controller;

import Controller.dto.UserDto;
import Entity.Aluno;
import Entity.Professor;
import service.AlunoService;
import service.ProfessorService;

import java.sql.SQLException;

public class ProfessorController {
    public int createProfessor(UserDto dto) throws SQLException {
        return new ProfessorService().createProfessor(dto);
    }
    public Professor getProfessorById(int id) {
        return new ProfessorService().getProfessorById(id);
    }
    public boolean updateProfessor(UserDto dto,
                               Integer id){
        return new ProfessorService().updateProfessor(dto, id);
    }
    public boolean deleteProfessor(int id){
        return new ProfessorService().deleteProfessor(id);
    }
}
