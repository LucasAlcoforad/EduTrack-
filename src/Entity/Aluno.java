package Entity;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class Aluno {

    public Integer idAluno;

    public String nome;

    public Date dataNascimento;

    public List<Nota> notas;

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Aluno() {
    }

    public Aluno(Integer idAluno, String nome, Date dataNascimento, List<Nota> notas) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "idAluno=" + idAluno +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", notas=" + notas +
                '}';
    }
}
