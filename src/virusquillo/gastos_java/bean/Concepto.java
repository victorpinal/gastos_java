package virusquillo.gastos_java.bean;

public class Concepto {
	
	int id;	
	String nombre;
	Concepto padre;
	
	public Concepto() {		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Concepto getPadre() {
		return padre;
	}

	public void setPadre(Concepto padre) {
		this.padre = padre;
	}

}
