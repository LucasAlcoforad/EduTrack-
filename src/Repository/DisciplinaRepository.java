package Repository;

import Config.DBConfig;
import Entity.Aluno;
import Entity.Disciplina;

import java.sql.*;
import java.util.ArrayList;

public class DisciplinaRepository {
    public static Disciplina getDisciplina(int id) throws SQLException {
        String sql = """
        SELECT 
            disciplina.id_disciplina,
            disciplina.nome AS disciplina_nome,
            aluno.nome AS aluno_nome,
            professor.nome AS professor_nome
        FROM 
            disciplina 
        LEFT JOIN
            professor ON disciplina.id_professor = professor.id_professor
        lEFT JOIN 
            nota ON disciplina.id_disciplina = nota.id_disciplina
        LEFT JOIN 
            aluno ON nota.id_aluno = aluno.id_aluno
        WHERE 
            disciplina.id_disciplina = ?;
        """;
        Disciplina disciplina = null;

        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    if (disciplina == null){
                        disciplina = new Disciplina(resultSet.getInt("id_disciplina"),
                                resultSet.getString("disciplina_nome"),
                                resultSet.getString("professor_nome"),
                                new ArrayList<>());
                    }
                    String aluno = resultSet.getString("aluno_nome");
                    if (!disciplina.getAlunos().contains(aluno)){
                        disciplina.getAlunos().add(aluno);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return disciplina;
    }
    public static Disciplina getDisciplinaByNome(String nome) throws SQLException {
        String sql = """
        SELECT 
            disciplina.id_disciplina,
            disciplina.nome AS disciplina_nome,
            aluno.nome AS aluno_nome,
            professor.nome AS professor_nome
        FROM 
            disciplina 
        LEFT JOIN
            professor ON disciplina.id_professor = professor.id_professor
        lEFT JOIN 
            nota ON disciplina.id_disciplina = nota.id_disciplina
        LEFT JOIN 
            aluno ON nota.id_aluno = aluno.id_aluno
        WHERE 
            disciplina.nome = ?;
        """;
        Disciplina disciplina = null;

        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)) {
            ps.setString(1, nome);

            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    if (disciplina == null){
                        disciplina = new Disciplina(resultSet.getInt("id_disciplina"),
                                resultSet.getString("disciplina_nome"),
                                resultSet.getString("professor_nome"),
                                new ArrayList<>());
                    }
                    String aluno = resultSet.getString("aluno_nome");
                    disciplina.getAlunos().add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disciplina;
    }

    public static Disciplina createDisciplina(Disciplina disciplina, int id){
        String sql = "INSERT INTO disciplina (id_disciplina, nome, id_professor) VALUES (?,?,?)";
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1, disciplina.getId());
            ps.setString(2,disciplina.getNome());
            ps.setInt(3, id);
            ps.execute();
            return disciplina;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean updateDisciplina(Disciplina disciplina){
        String sql = "UPDATE disciplina SET nome = ? WHERE id_disciplina = ?";
        try (PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setString(1,disciplina.getNome());
            statement.setInt(2,disciplina.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean deleteDisciplina(int id){
        String sql = """
                DELETE FROM disciplina
                WHERE id_disciplina = ?
                """;
        try(PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
