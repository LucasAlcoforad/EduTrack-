package Repository;

import Config.DBConfig;
import Entity.Aluno;
import Entity.Professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorRepository {
    public static int createProfessor(Professor professor) throws SQLException {
        String sql = "INSERT INTO professor (id_professor, nome) VALUES (?,?)";
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1,professor.getIdProfessor());
            ps.setString(2, professor.getNome());
            ps.execute();
            return professor.getIdProfessor();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Professor getProfessor(int id){
        String sql = "SELECT * FROM professor WHERE id_professor = ?"; // armazena o nosso comando
        Professor professor = null;
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)){
            ps.setInt(1,id); //
            try (ResultSet resultSet = ps.executeQuery()){
                if (resultSet.next()){
                    professor = new Professor(resultSet.getInt("id_professor"),
                            resultSet.getString("nome"),
                            null
                            );
                }
                return professor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateProfessor(Professor professor){
        String sql = "UPDATE professor SET nome = ? WHERE id_professor = ?";
        try (PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setString(1, professor.getNome());
            statement.setInt(3, professor.getIdProfessor());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteProfessor(int id) {
        String sql = "DELETE FROM professor WHERE id_professor = ?";
        try(PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
