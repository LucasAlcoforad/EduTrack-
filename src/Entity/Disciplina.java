package Entity;

import java.time.Instant;
import java.util.List;

public class Disciplina {

    public Integer id;

    public String nome;

    public List<String> alunos;


    public Disciplina(Integer id, String nome, List<String> alunos) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", alunos=" + alunos +
                '}';
    }
}
