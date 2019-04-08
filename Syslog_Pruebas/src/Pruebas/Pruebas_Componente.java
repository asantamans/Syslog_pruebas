package Pruebas;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.ConectorController;
import beans.QueryEvento;
import objects.TransactionType;

public class Pruebas_Componente {

	public static void main(String[] args) throws SQLException {
		ConectorController a = new ConectorController();
		QueryEvento evt = new QueryEvento();
		a.addPropertyChangeListener(evt);

		// Opciones del componente
		a.setDatabase("forhonor");
		a.setHostSettings("localhost", "3306");
		a.setCredentials("root", "");

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

	}

}
