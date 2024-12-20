package service;

import Controller.dto.NotaDto;
import Entity.Aluno;
import Entity.Disciplina;
import Entity.Nota;
import Exceçoes.UserNaoEncontradoException;
import Repository.AlunoRepository;
import Repository.DisciplinaRepository;
import Repository.NotaRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

public class NotaService {
    public Nota createNota(NotaDto nota) throws SQLException {
        Disciplina disciplina = DisciplinaRepository.getDisciplinaByNome(nota.disciplina());
        if (disciplina == null){
            throw new UserNaoEncontradoException("Disciplina nao encontrada");
        }
        Nota notaExist = NotaRepository.getNotaByData(nota.data(), disciplina.getId(), nota.idAluno());
        if (notaExist==null){
            Random random = new Random();
            int min = 10000;
            int max = 99999;
            int randomNumber = random.nextInt(max - min + 1) + min;
            if(NotaRepository.getNota(randomNumber)!=null){
                return this.createNota(nota);
            }
            return NotaRepository.createNota(new Nota(randomNumber,disciplina.getId(), nota.idAluno(), nota.nota(), nota.data()));
        }
        return null;
    }

    public Nota getNota(int id) throws SQLException {
        return NotaRepository.getNota(id);
    }

    public Nota getNotaByDate(LocalDate date, int idDisciplina, int idAluno) throws SQLException {
        return NotaRepository.getNotaByData(date, idDisciplina, idAluno);
    }

    public boolean updateNota(double valor, int id) throws SQLException {
        Nota nota = NotaRepository.getNota(id);
        if (nota == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        return NotaRepository.updateNota(valor, id);
    }

    public boolean deleteNota(int id) throws SQLException {
        Nota nota = NotaRepository.getNota(id);
        if (nota == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        return NotaRepository.deleteNota(id);
    }
}
