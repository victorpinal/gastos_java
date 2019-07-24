package virusquillo.gastos_java.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import virusquillo.gastos_java.mySQL;
import virusquillo.gastos_java.bean.Tarjeta;

public class TarjetaDao implements IDao<Tarjeta> {
	
	mySQL db = mySQL.getInstance();

	@Override
	public Tarjeta get(int id) {		
		try {
			ResultSet res = db._select("SELECT * FROM tarjetas WHERE idtarjetas=?", id);
			if (res.next()) {
				Tarjeta c = new Tarjeta();
				c.setId(res.getInt("idtarjetas"));
				c.setNombre(res.getString("nombre"));
				c.setNumero(res.getString("numero"));
				c.setFechaCaducidad(res.getDate("fecha_caducidad"));
				c.setVersion(res.getInt("version"));
				return c;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer,Tarjeta> getAll() {
		Map<Integer,Tarjeta> l = new HashMap<Integer,Tarjeta>();
		try {			
			ResultSet res = db._select("SELECT * FROM tarjetas");
			while (res.next()) {
				Tarjeta c = new Tarjeta();
				int id = res.getInt("idtarjetas");
				c.setId(id);
				c.setNombre(res.getString("nombre"));
				c.setNumero(res.getString("numero"));
				c.setFechaCaducidad(res.getDate("fecha_caducidad"));
				c.setVersion(res.getInt("version"));		
				l.put(id,c);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return l;
	}

	@Override
	public int insert(Tarjeta obj) {
		if (obj!=null) {
			try {
				db._execute("INSERT INTO tarjetas (nombre,numero,fecha_caducidad,version) VALUES (?,?,?,?);", 
						obj.getNombre(),
						obj.getNumero(),
						obj.getFechaCaducidad(),
						obj.getVersion());
				ResultSet res = db._select("SELECT LAST_INSERT_ID();");
				if (res.next()) {
					int id = res.getInt(0);
					obj.setId(id);
					return id;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public void remove(int id) {
		try {
			db._execute("DELETE FROM tarjetas WHERE idtarjetas=?",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Tarjeta obj) {
		if (obj!=null) {
			try {
				db._execute("UPDATE tarjetas SET nombre=?,numero=?,fecha_caducidad=?,version=? WHERE idtarjetas=?;", 
						obj.getNombre(),
						obj.getNumero(),
						obj.getFechaCaducidad(),
						obj.getVersion(),
						obj.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
