package Entity;

import java.util.List;

public class Disciplina {

    public Integer idDiciplina;

    public String nome;

    public List<Nota> notas;

    public List<Professor> professores;

    public Disciplina() {
    }

    public Disciplina(Integer idDiciplina, String nome, List<Nota> notas) {
        this.idDiciplina = idDiciplina;
        this.nome = nome;
        this.notas = notas;
    }
}
