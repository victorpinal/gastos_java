package virusquillo.gastos_java.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import virusquillo.gastos_java.mySQL;
import virusquillo.gastos_java.bean.Concepto;

public class ConceptoDao implements IDao<Concepto> {
	
	mySQL db = mySQL.getInstance();

	@Override
	public Concepto get(int id) {		
		try {
			ResultSet res = db._select("SELECT * FROM conceptos WHERE idconceptos=?", id);
			if (res.next()) {
				Concepto c = new Concepto();
				c.setId(res.getInt("idconceptos"));
				c.setNombre(res.getString("nombre"));
				int idPadre = res.getInt("idpadre");				
				if (idPadre>0) {
					c.setPadre(get(idPadre));
				}
				return c;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer,Concepto> getAll() {
		Map<Integer,Concepto> l = new HashMap<Integer,Concepto>();
		try {			
			ResultSet res = db._select("SELECT * FROM conceptos");
			while (res.next()) {
				Concepto c = new Concepto();
				int id = res.getInt("idconceptos");
				c.setId(id);
				c.setNombre(res.getString("nombre"));
				int idPadre = res.getInt("idpadre");
				if (idPadre>0) {
					Concepto p = new Concepto();
					p.setId(idPadre);
					c.setPadre(p);
				}
				l.put(id,c);
			}
			//seteamos los padres
			for (Concepto c:l.values()) {
				if (c.getPadre()!=null) {
					c.setPadre(l.get(c.getPadre().getId()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return l;
	}

	@Override
	public int insert(Concepto obj) {
		if (obj!=null) {
			try {
				db._execute("INSERT INTO conceptos (nombre,idpadre) VALUES (?,?);", obj.getNombre(),obj.getPadre()!=null?obj.getPadre().getId():0);
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
			db._execute("DELETE FROM conceptos WHERE idconceptos=?",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Concepto obj) {
		if (obj!=null) {
			try {
				db._execute("UPDATE conceptos SET nombre=?,idpadre=? WHERE idconceptos=?;", obj.getNombre(),obj.getPadre()!=null?obj.getPadre().getId():0,obj.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
