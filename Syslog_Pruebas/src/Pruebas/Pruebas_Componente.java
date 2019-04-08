package Pruebas;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.ConectorController;
import beans.QueryEvento;
import objects.TransactionType;

public class Pruebas_Componente {

	public static void main(String[] args) throws SQLException {
		// Como inicializar el componente correctamente
		ConectorController a = new ConectorController();
		// Listener del componente
		QueryEvento evt = new QueryEvento();
		a.addPropertyChangeListener(evt);

		// Opciones del componente
		// Permite seleccionar la base de datos (String bbddName)
		a.setDatabase("forhonor");
		// Permite introducir host i el puerto (String host, String port)
		a.setHostSettings("localhost", "3306");
		// Permite introducir el usuario y la contraseña (String userName, String
		// password)
		a.setCredentials("root", "");

		/*
		 * ejecutarQuery -> Permite ejecutar una query 
		 * getResultsSelect -> Devuelve un ArrayList<String> 
		 *con los registros de una sentencia Select pasada como
		 * parametro
		 * 
		 * 
		 */

		// Para obtener los results de un select
		ArrayList<String> tmp = a.getResultsSelect("SELECT * from users");
		System.out.println("Numero de filas retornadas: " + tmp.size());

		// Consultas con usuario root
		System.out.println("SELECT * FROM PERSONAJE;");
		a.ejecutarQuery("Select * from personaje");
		System.out.println("SELECT * FROM USERS");
		a.ejecutarQuery("SELECT * from users");
		System.out.println("CALL test2;");
		a.ejecutarQuery("CALL test2");

		// Consultas con usuario arnau
		a.setCredentials("arnau", "");
		System.out.println("SELECT * FROM FACCION");
		a.ejecutarQuery("Select * from faccion");
		// Cambiamos de base de datos pero no de usuario
		a.setDatabase("bv");
		System.out.println("SELECT * FROM BFPLAYER");
		a.ejecutarQuery("select * from bfplayer");

		// Realizamos un insert -> update -> remove (Comentar y descomentar para validar
		// proceso)
		System.out.println("Realizamos un insert");
		a.ejecutarQuery(
				"INSERT INTO `bfplayer`(`user_id`, `class_id`, `primary_weapon`, `device1`, `kills`, `Deads`) VALUES ('ARNOLD',2,1,2,9,7)");
		System.out.println("Ejecutamos update");
		a.ejecutarQuery("UPDATE bfplayer SET user_id='ARNOLD_PRO' WHERE user_id='ARNOLD'");
		System.out.println("Ejecutamos remove");
		a.ejecutarQuery("delete from bfplayer where user_id = 'ARNOLD_PRO'");

		// Imprimimos reportes de las sentencias ejecutadas
		/*
		 * 	printReport -> printa por consola el reporte segun los datos especificados:
		 *	(String bbddName, TransactionType tipo_consulta)
		 *	(String bbddName, String userName)
		 *	(String bbddName,String userName, TransactionType tipo_consulta)
		 *	Printaran el reporte en caso que existan datos, en caso contrario, se printara un mensaje de error 
		 *	conforme no hay datos
		 *
		 *	Tambien se ofrece la variedad de getReport -> Que devuelve un ArrayList<String>
		 *	con los datos del reporte, separados llos campos por ';'
		 *	Acepta como parametros:
		 *	(String bbddName, TransactionType tipo_consulta)
		 *	(String bbddName, String userName)
		 *	(String bbddName,String userName, TransactionType tipo_consulta)
		 */
		System.out.println("\n\n\n\nPrintado de reporte de bbdd forhonor con query select");
		a.printReport("forhonor", TransactionType.SELECT);

		System.out.println("\n\n\n\nPrintado de reporte de bbdd forhonor con usuario arnau");
		a.printReport("forhonor", "arnau");

		System.out.println("\n\n\n\nPrintado de reporte de bbdd forhonor con usuario root");
		a.printReport("forhonor", "root");

		System.out.println("\n\n\n\nPrintado de reporte  de bbdd bv i usuario arnau");
		a.printReport("bv", "arnau");

		System.out.println("\n\n\n\nPrintado de reporte  de bbdd bv i usuario root");
		a.printReport("bv", "root");

		System.out.println("\n\n\n\nPrintado de reporte de bbdd forhonor con query procedure");
		a.printReport("forhonor", TransactionType.PROCEDURE);
		
		System.out.println("\n\n\n\nPrintado de reporte de bbdd forhonor con query Select i usuario root");
		a.printReport("forhonor","root", TransactionType.SELECT);

	}

}
