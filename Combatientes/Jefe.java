//JOSE PINTO 25063
package Combatientes;
import Habilidades.Habilidad;
//Clase heredada de Enemigo
public class Jefe extends Enemigo{
    protected Habilidad habilidad2;
    //Constructor con nuevo atributo habilidad 2 y overrride en defensa
    public Jefe(String nombre, int poderAtaque, int puntosVida, Habilidad habilidad, Habilidad habilidad2) {
        super(nombre,poderAtaque, puntosVida, habilidad);
        this.habilidad2 = habilidad2;
        this.defensa = 0.25;
    }
    //Getters y setters
    public Habilidad getHabilidad2() {
        return habilidad2;
    }
    public void setHabilidad2(Habilidad habilidad2) {
        this.habilidad2 = habilidad2;
    }
}