package Repository;

import Config.DBConfig;
import Entity.Nota;

import java.sql.*;

public class NotaRepository {
    public static Nota createNota(Nota nota) throws SQLException {
        String sql = "INSERT INTO nota (id_aluno, id_disciplina, id_nota, valor, data) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, nota.getIdAluno());
            ps.setInt(2, nota.getIdDisciplina());
            ps.setInt(3, nota.getId());
            ps.setDouble(4, nota.getNota());
            ps.setDate(5, Date.valueOf(nota.getData()));
            ps.execute();
            return nota;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Nota getNota(int id) throws SQLException{
        String sql = """
                SELECT 
                    * 
                FROM 
                    nota
                WHERE
                    id_nota = ?
                """;
        Nota nota = null;
        try (PreparedStatement ps = DBConfig.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()){
                    nota = new Nota(resultSet.getInt("id_nota"),
                            resultSet.getInt("id_disciplina"),
                            resultSet.getInt("id_aluno"),
                            resultSet.getFloat("valor"),
                            resultSet.getDate("data").toLocalDate()
                            );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nota;
    }

    public static boolean updateNota(double valor, int id){
        String sql = """
                UPDATE
                    nota
                SET
                    valor = ?
                WHERE
                    id_nota = ?
                """;
        try (PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setDouble(1, valor);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteNota(int id){
        String sql = """
                DELETE FROM
                    nota
                WHERE
                    nota.id_nota = ?
                """;
        try (PreparedStatement statement = DBConfig.getConnection().prepareStatement(sql)){
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
