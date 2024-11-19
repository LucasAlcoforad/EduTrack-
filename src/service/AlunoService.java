package service;

import Controller.dto.AlunoDto;
import Entity.Aluno;
import Exceçoes.UserNaoEncontradoException;
import Repository.AlunoRepository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

public class AlunoService {

    public int createAluno(AlunoDto createAlunoDto) throws SQLException {
        Random random = new Random();
        int min = 10000;
        int max = 99999;

        int randomNumber = random.nextInt(max - min + 1) + min;
        Aluno aluno = new Aluno(randomNumber,
                createAlunoDto.nome(),
                createAlunoDto.dataDeNascimento(),
                null
        );
        return AlunoRepository.createAluno(aluno);
    }

    public Aluno getAlunoById(int id){

        Aluno aluno = AlunoRepository.getAluno(id);
        if (aluno == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        return aluno;
    }

    public boolean updateAluno(AlunoDto dto,
                               int id){
        Aluno aluno = AlunoRepository.getAluno(id);
        if (aluno == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        aluno.setNome(dto.nome());
        aluno.setDataNascimento(dto.dataDeNascimento());
        return AlunoRepository.updateAluno(aluno);
    }

    public boolean deleteAluno(int id){
        return AlunoRepository.deleteAluno(id);
    }
}
