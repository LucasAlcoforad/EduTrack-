package Repository;

import Config.DBConfig;
import Entity.Aluno;
import Entity.Professor;

import java.sql.*;
import java.util.ArrayList;

public class ProfessorRepository {
    public static Professor getProfessor(int id){
        String sql = """
                SELECT
                    professor.*,
                    disciplina.nome AS disciplina_nome
                FROM
                    professor
                LEFT JOIN
                    disciplina ON professor.id_professor = disciplina.id_professor
                WHERE
                    professor.id_professor = ?
                """;
        Professor professor = null;
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    if (professor == null) {
                        professor = new Professor(
                                resultSet.getInt("id_professor"),
                                resultSet.getString("password"),
                                resultSet.getString("nome"),
                                resultSet.getDate("data_nascimento").toLocalDate(),
                                resultSet.getTimestamp("create_timestamp").toInstant(),
                                resultSet.getTimestamp("update_timestamp").toInstant(),
                                new ArrayList<>()
                        );
                    }
                    String disciplina = resultSet.getString("disciplina_nome");
                    if (disciplina != null) {
                        professor.getDisciplinas().add(disciplina);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return professor;
    }

    public static int createProfessor(Professor professor){
        String sql = """
                INSERT INTO
                    professor (id_professor, password, nome, data_nascimento, create_timestamp, update_timestamp)
                VALUES
                    (?,?,?,?,?,?)
                """;
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1, professor.getId());
            ps.setString(2, professor.getPassword());
            ps.setString(3, professor.getNome());
            ps.setDate(4, Date.valueOf(professor.getDataNascimento()));
            ps.setTimestamp(5, Timestamp.from(professor.getCreationTimestamp()));
            ps.setTimestamp(6, Timestamp.from(professor.getUpdateTimestamp()));
            ps.execute();
            return professor.getId();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean updateProfessor(Professor professor){
        String sql = """
                UPDATE
                    aluno
                SET
                    nome = ?, password = ?, data_nascimento = ?, update_timestamp = ?
                WHERE
                    id_aluno = ?
                """;
        try (PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setString(1,  professor.getNome());
            statement.setString(2, professor.getPassword());
            statement.setDate(3, Date.valueOf(professor.getDataNascimento()));
            statement.setTimestamp(4, Timestamp.from(professor.getUpdateTimestamp()));
            statement.setInt(5, professor.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteProfessor(int id){
        String sql = """
                DELETE FROM
                    professor
                WHERE
                    professor.id_professor = ?
                """;
        try(PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
