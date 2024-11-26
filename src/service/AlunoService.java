package service;

import Controller.dto.AlunoDto;
import Entity.Aluno;
import Exceçoes.UserNaoEncontradoException;
import Repository.AlunoRepository;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;

public class AlunoService {

    public int createAluno(AlunoDto createAlunoDto) throws SQLException {
        if (createAlunoDto.dataDeNascimento()==null||
        createAlunoDto.nome()==null||
        createAlunoDto.password()==null){
            //Erro
        }
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        if(AlunoRepository.getAluno(randomNumber)!=null){
            return this.createAluno(createAlunoDto);
        }
        Aluno aluno = new Aluno(randomNumber,
                createAlunoDto.password(),
                createAlunoDto.nome(),
                createAlunoDto.dataDeNascimento(),
                Instant.now(),
                Instant.now(),
                null);
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
        if (dto.nome()!=null){
            aluno.setNome(dto.nome());
        }
        if (dto.password()!=null){
            aluno.setPassword(dto.password());
        }
        if (dto.dataDeNascimento()!=null){
            aluno.setDataNascimento(dto.dataDeNascimento());
        }
        aluno.setUpdateTimestamp(Instant.now());
        return AlunoRepository.updateAluno(aluno);
    }

    public boolean deleteAluno(int id){
        Aluno aluno = AlunoRepository.getAluno(id);
        if (aluno == null){
            throw new UserNaoEncontradoException("Aluno com id " + id + " não encontrado.");
        }
        return AlunoRepository.deleteAluno(id);
    }
}
