package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* @author Diego Munhoz
*/
public final class JDBCUtil {

	public static Connection getConnection() {

		final String driver = "com.mysql.jdbc.Driver";
		final String local = "jdbc:mysql://localhost:3306/projeto4";
		final String login = "root";
		final String senha = "root";

		Connection conexao = null;

		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(local, login, senha);
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER NAO ENCONTRADO");
		} catch (SQLException e) {
			System.out.println("ERRO DE BANCO DE DADOS");
		}

		return conexao;
	}
}
