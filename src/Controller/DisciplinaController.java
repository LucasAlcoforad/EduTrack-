package Controller;

import Entity.Disciplina;
import service.DisciplinaService;

import java.sql.SQLException;

public class DisciplinaController {
    public int createDisciplina(String nome) throws SQLException {
        return new DisciplinaService().createDisciplina(nome);
    }
    public Disciplina getDisciplina(int id) throws SQLException {
        return new DisciplinaService().getDisciplina(id);
    }
    public boolean deleteDisciplina(int id){
        return new DisciplinaService().deleteDisciplina(id);
    }
}
