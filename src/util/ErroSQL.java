package util;

import java.sql.SQLException;

/**
*
* @author Diego Munhoz
*/
public class ErroSQL {

	public static String trataErroSQL(Exception e) {
		if (e.getCause() instanceof SQLException) {
			SQLException erroSQL = (SQLException) e.getCause();
			switch (erroSQL.getErrorCode()) {
			case 1062:
				return " Dados duplicados, revise o seguinte dado: ".toUpperCase() + erroSQL.getMessage().substring(16);
			default:
				return " SQL-Code Erro: " + erroSQL.getErrorCode() + "\n " + erroSQL.getMessage();
			}
		}
		return e.getCause().toString();
	}
}
