package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
    private static final String url = "jdbc:mysql://localhost:3306/unifor";
    private static final String user = "myuser";
    private static final String password = "123";

    public static Connection connection;

    public static Connection getConnection(){
        try {
            if (connection==null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url,user,password);
                String createAlunoTable = "CREATE TABLE IF NOT EXISTS aluno(" +
                        "    id_aluno INT PRIMARY KEY," +
                        "    nome TEXT," +
                        "    data_nascimento DATE" +
                        ")";
                connection.createStatement().execute(createAlunoTable);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
