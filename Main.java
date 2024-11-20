import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/SistemaVendas";
    private static final String USER = "root"; // Troque pelo usuário do seu MySQL
    private static final String PASSWORD = "sua_senha"; // Troque pela sua senha do MySQL

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Conexão com o banco de dados
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados bem-sucedida!");

            // Inserir um produto
            String insertSQL = "INSERT INTO Produto (nome, preco) VALUES (?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertSQL);
            insertStmt.setString(1, "Notebook");
            insertStmt.setDouble(2, 4500.00);
            int rowsInserted = insertStmt.executeUpdate();
            System.out.println("Linhas inseridas: " + rowsInserted);

            // Consultar produtos
            String selectSQL = "SELECT * FROM Produto";
            PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
            ResultSet resultSet = selectStmt.executeQuery();

            System.out.println("Produtos cadastrados:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");

                System.out.println("ID: " + id + ", Nome: " + nome + ", Preço: " + preco);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Conexão encerrada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
