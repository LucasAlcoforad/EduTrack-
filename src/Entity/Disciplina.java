package Entity;

import java.time.Instant;
import java.util.List;

public class Disciplina {

    public Integer id;

    public String nome;

    public String Professor;

    public List<String> alunos;


    public Disciplina(Integer id, String nome, String professor, List<String> alunos) {
        this.id = id;
        this.nome = nome;
        Professor = professor;
        this.alunos = alunos;
    }

    public Disciplina() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<String> alunos) {
        this.alunos = alunos;
    }

    public String getProfessor() {
        return Professor;
    }

    public void setProfessor(String professor) {
        Professor = professor;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", Professor='" + Professor + '\'' +
                ", alunos=" + alunos +
                '}';
    }
}
