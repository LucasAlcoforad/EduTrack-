package Repository;

import Config.DBConfig;
import Entity.Disciplina;

import java.sql.*;
import java.util.ArrayList;

public class DisciplinaRepository {
    public static Disciplina getDisciplina(int id) throws SQLException {
        String sql = """
        SELECT 
            disciplina.id_disciplina,
            disciplina.nome AS disciplina_nome,
            aluno.nome AS aluno_nome
        FROM 
            disciplina 
        LEFT JOIN 
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
    public static Disciplina getDisciplinaByNome(String nome) throws SQLException {
        String sql = """
        SELECT 
            disciplina.id_disciplina,
            disciplina.nome AS disciplina_nome,
            aluno.nome AS aluno_nome
        FROM 
            disciplina 
        lEFT JOIN 
            matricula ON disciplina.id_disciplina = matricula.id_disciplina
        LEFT JOIN 
            aluno ON matricula.id_aluno = aluno.id_aluno
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

    public static int createDisciplina(Disciplina disciplina){
        String sql = "INSERT INTO disciplina (id_disciplina, nome) VALUES (?,?)";
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1, disciplina.getId());
            ps.setString(2,disciplina.getNome());
            ps.execute();
            return disciplina.getId();
        } catch (SQLException e){
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