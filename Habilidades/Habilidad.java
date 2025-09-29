//JOSE PINTO 25063
package Habilidades;
public class Habilidad {
    protected String nombre;
    protected int usos;
    //Constructor base para las clases que heredaran de esta
    public Habilidad(String nombre, int usos) {
        this.nombre = nombre;
        this.usos = usos;
    }
    //Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getUsos() {
        return usos;
    }
    public void setUsos(int usos) {
        this.usos = usos;
    }
}
