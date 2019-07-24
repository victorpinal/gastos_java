package virusquillo.gastos_java.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import virusquillo.gastos_java.mySQL;
import virusquillo.gastos_java.bean.Concepto;
import virusquillo.gastos_java.bean.Movimiento;
import virusquillo.gastos_java.bean.Tarjeta;

public class MovimientoDao implements IDao<Movimiento> {
	
	mySQL db = mySQL.getInstance();
	ConceptoDao cDao = new ConceptoDao();
	

	@Override
	public Movimiento get(int id) {		
		try {
			ResultSet res = db._select("SELECT * FROM movimientos m LEFT JOIN conceptos c ON m.idconcepto=c.idconceptos LEFT JOIN tarjetas t ON m.idtarjeta=t.idtarjetas WHERE idmovimientos=?", id);
			if (res.next()) {
				Movimiento m = new Movimiento();
				m.setId(res.getInt("m.idmovimientos"));
				m.setFecha(res.getDate("m.fecha"));
				int idconcepto = res.getInt("c.idconceptos");
				if (idconcepto>0) {
					Concepto c = new Concepto();
					c.setId(idconcepto);
					c.setNombre(res.getString("c.nombre"));
					int idconceptopadre = res.getInt("c.idpadre");
					if (idconceptopadre>0) {
						c.setPadre(cDao.get(idconceptopadre));
					}
					m.setConcepto(c);
				}				
				int idtarjeta = res.getInt("t.idtarjetas");
				if (idtarjeta>0) {
					Tarjeta t = new Tarjeta();
					t.setId(idtarjeta);
					t.setNombre(res.getString("t.nombre"));
					t.setNumero(res.getString("t.numero"));
					t.setFechaCaducidad(res.getDate("t.fecha_caducidad"));
					t.setVersion(res.getInt("t.version"));
					m.setTarjeta(t);
				}
				m.setImporte(res.getDouble("m.importe"));
				m.setObservaciones(res.getString("m.observaciones"));
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer,Movimiento> getAll() {
		Map<Integer,Movimiento> l = new HashMap<Integer,Movimiento>();
		try {			
			ResultSet res = db._select("SELECT * FROM movimientos m LEFT JOIN conceptos c ON m.idconcepto=c.idconceptos LEFT JOIN tarjetas t ON m.idtarjeta=t.idtarjetas ORDER BY fecha DESC;");
			while (res.next()) {
				Movimiento m = new Movimiento();
				int id =res.getInt("m.idmovimientos"); 
				m.setId(id);
				m.setFecha(res.getDate("m.fecha"));
				int idconcepto = res.getInt("c.idconceptos");
				if (idconcepto>0) {
					Concepto c = new Concepto();
					c.setId(idconcepto);
					c.setNombre(res.getString("c.nombre"));
					int idconceptopadre = res.getInt("c.idpadre");
					if (idconceptopadre>0) {
						c.setPadre(cDao.get(idconceptopadre));
					}
					m.setConcepto(c);
				}				
				int idtarjeta = res.getInt("t.idtarjetas");
				if (idtarjeta>0) {
					Tarjeta t = new Tarjeta();
					t.setId(idtarjeta);
					t.setNombre(res.getString("t.nombre"));
					t.setNumero(res.getString("t.numero"));
					t.setFechaCaducidad(res.getDate("t.fecha_caducidad"));
					t.setVersion(res.getInt("t.version"));
					m.setTarjeta(t);
				}
				m.setImporte(res.getDouble("m.importe"));
				m.setObservaciones(res.getString("m.observaciones"));		
				l.put(id,m);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return l;
	}

	@Override
	public int insert(Movimiento obj) {
		if (obj!=null) {
			try {
				db._execute("INSERT INTO movimientos (fecha,idconcepto,idtarjeta,importe,observaciones) VALUES (?,?,?,?,?);", 
						obj.getFecha(),
						obj.getConcepto()!=null?obj.getConcepto().getId():0,
						obj.getTarjeta()!=null?obj.getTarjeta().getId():0,
						obj.getImporte(),
						obj.getObservaciones());
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
			db._execute("DELETE FROM movimientos WHERE idmovimientos=?",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Movimiento obj) {
		if (obj!=null) {
			try {
				db._execute("UPDATE movimientos SET fecha=?,idconcepto=?,idtarjeta=?,importe=?,observaciones=? WHERE idmovimientos=?;", 
						obj.getFecha(),
						obj.getConcepto()!=null?obj.getConcepto().getId():0,
						obj.getTarjeta()!=null?obj.getTarjeta().getId():0,
						obj.getImporte(),
						obj.getObservaciones(),
						obj.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
