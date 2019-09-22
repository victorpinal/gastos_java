package virusquillo.gastos_java;

import java.sql.ResultSet;

public class GastosMain {

	mySQL sql = mySQL.getInstance();
	
	public static void main(String[] args) {
		new GastosMain();
	}
	
	public GastosMain() {
		try {
			StringBuffer str = new StringBuffer();
			ResultSet res = sql._select("SELECT * FROM movimientos m inner join conceptos c on m.idconcepto=c.idconceptos inner join tarjetas t on m.idtarjeta=t.idtarjetas ORDER BY m.fecha DESC;");
			while (res.next()) {
				str.append(String.format("TARJETA [%s]:\n", res.getString("t.nombre")));	
				
			}
			System.out.println(str.toString());
			sql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
