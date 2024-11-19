package Entity;

import java.sql.Date;
import java.util.List;

public class Professor {

    public int idProfessor;

    public String nome;

    public List<Integer> idDisciplinas;

    public Nota createNota(int idAluno, int idDiciplina, Date data, float nota){
        if(!idDisciplinas.contains(idDiciplina)){
            //Erro
        }
        return new Nota(idAluno, idDiciplina, nota, data);
    }

    public Professor() {
    }

    public Professor(Integer idProfessor, String nome, List<Integer> disciplinas) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.idDisciplinas = disciplinas;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getDisciplinas() {
        return idDisciplinas;
    }

    public void setDisciplinas(List<Integer> disciplinas) {
        this.idDisciplinas = disciplinas;
    }
}
