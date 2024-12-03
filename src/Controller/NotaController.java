package Controller;

import Controller.dto.NotaDto;
import Entity.Nota;
import Repository.NotaRepository;
import service.NotaService;

import java.sql.SQLException;
import java.time.LocalDate;

public class NotaController {
    public Nota createNota(NotaDto nota) throws SQLException {
        return new NotaService().createNota(nota);
    }

    public Nota getNota(int id) throws SQLException {
        return new NotaService().getNota(id);
    }

    public Nota getNotaByData(LocalDate date, int idDisciplina, int idAluno) throws SQLException {
        return new NotaService().getNotaByDate(date, idDisciplina, idAluno);
    }
    public boolean updateNota(double valor, int id) throws SQLException {
        return new NotaService().updateNota(valor, id);
    }
    public boolean deleteNota(int id) throws SQLException {
        return new NotaService().deleteNota(id);
    }
}
