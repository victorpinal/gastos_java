package virusquillo.gastos_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

/**
 * Created by victormanuel on 03/12/2015.
 */
public class mySQL {

	private final Logger _log = Logger.getLogger(mySQL.class.getName());
	private Connection myConn = null;
	private static mySQL _instance;
	public static mySQL getInstance() {
		if (_instance==null) {
			return new mySQL();
		} else {
			return _instance;
		}
	}
	
	private mySQL() {

		Preferences preferences = Preferences.userNodeForPackage(mySQL.class);		
		
		// Read connection data from preferences		
		if (preferences.get("servidor", null) == null || preferences.get("contrasenha", null) == null) {
			preferences.put("servidor",
					JOptionPane.showInputDialog(null, "Servidor MySQL","Configuracion", JOptionPane.QUESTION_MESSAGE));
			preferences.put("puerto",
					JOptionPane.showInputDialog(null, "Puerto", "Configuracion", JOptionPane.QUESTION_MESSAGE));
			preferences.put("usuario",
					JOptionPane.showInputDialog(null, "Usuario","Configuracion", JOptionPane.QUESTION_MESSAGE));
			preferences.put("contrasenha",
					JOptionPane.showInputDialog(null, "Contrasenha", "Configuracion", JOptionPane.QUESTION_MESSAGE));
		}

		String connectionString = String.format("jdbc:mysql://%1$s:%2$s/gastos?user=%3$s&password=%4$s",
				preferences.get("servidor", "localhost"), 
				preferences.get("puerto", "3306"),
				preferences.get("usuario", null), 
				preferences.get("contrasenha", null)).toString();

		//Check for driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			_log.log(Level.SEVERE, "Driver no encontrado", e);
		}
		
		//abrir la conexión
		try {
			myConn = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "Error de conexión", e);
			//reseteamos preferencias
			preferences.put("servidor", null);
		}
		
	}
	
	public void close() {
		try {
			myConn.close();
		} catch (Exception e) {
		}
	}
	
	public ResultSet _select(String sql, Object...args) throws Exception {
		try {
			PreparedStatement stm = getStatement(sql, args);
			return stm.executeQuery();
		} catch (SQLException e) {
			_log.severe(e.getMessage());
			throw new Exception(e);
		}			
	}
	
	public void _execute(String sql, Object...args) throws Exception {
		try {
			PreparedStatement stm = getStatement(sql, args);
			stm.executeUpdate();
		} catch (SQLException e) {
			_log.severe(e.getMessage());
			throw new Exception(e);
		}
	}
	
	private PreparedStatement getStatement(String sql, Object...args) throws SQLException {
		if (myConn == null) { return null; }
		PreparedStatement stm = myConn.prepareStatement(sql);
		if (args!=null) {
			int index = 1;
			for(Object param:args) {
				if (param instanceof String) {
					_log.fine("String parameter@"+index+" position");
					stm.setString(index,(String)param);
				} else if (Integer.class.isInstance(param)) {
					_log.fine("Integer parameter@"+index+" position");
					stm.setInt(index, (Integer)param);
				} else if (param instanceof byte[]) {
					_log.fine("Byte[] parameter@"+index+" position");
					stm.setBytes(index, (byte[])param);
				} else if (param instanceof Date) {
					_log.fine("Date parameter@"+index+" position");
					stm.setDate(index, (java.sql.Date)param);
				} else if (Double.class.isInstance(param)) {
					_log.fine("Double parameter@"+index+" position");
					stm.setDouble(index, (Double)param);
				} else {
					_log.fine("Object parameter@"+index+" position");
					stm.setObject(index, param);
				}
				index++;
			}
		}
		_log.fine(stm.toString() + " prepared...");
		return stm;
	}

}
