package modelo;

public class Cafe {
	  private String CAF_NOMBRE;
	  private int PROV_ID;
	  private float PRECIO;
	  private int VENTAS;
	  private int TOTAL;

	  
	
    public Cafe() {
		super();
	}

	public Cafe(String CAF_NOMBRE, int PROV_ID, float PRECIO, int VENTAS,
			int TOTAL) {
		super();
		this.CAF_NOMBRE= CAF_NOMBRE;
		this.PROV_ID = PROV_ID;
		this.PRECIO = PRECIO;
		this.VENTAS = VENTAS;
		this.TOTAL = TOTAL;
	}

	public String getCAF_NOMBRE() {
        return CAF_NOMBRE;
    }

    public void setCAF_NOMBRE(String CAF_NOMBRE) {
        this.CAF_NOMBRE = CAF_NOMBRE;
    }

    public int getPROV_ID() {
        return PROV_ID;
    }

    public void setPROV_ID(int PROV_ID) {
        this.PROV_ID = PROV_ID;
    }

    public float getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(float PRECIO) {
        this.PRECIO = PRECIO;
    }

    public int getVENTAS() {
        return VENTAS;
    }

    public void setVENTAS(int VENTAS) {
        this.VENTAS = VENTAS;
    }

    @Override
    public String toString() {
        return "Cafe{" + "CAF_NOMBRE=" + CAF_NOMBRE + "PROV_ID=" + PROV_ID + "PRECIO=" + PRECIO + "VENTAS=" + VENTAS + "TOTAL=" + TOTAL + '}';
    }


    public int getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(int TOTAL) {
		this.TOTAL = TOTAL;
	}

	//Representaci�n en array de nuestro objeto Libro
    public Object[] toArray(){
        return new Object[]{CAF_NOMBRE,PROV_ID,PRECIO,VENTAS,TOTAL};
    }

}
	
	
	