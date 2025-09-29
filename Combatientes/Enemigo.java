//JOSE PINTO 25063
package Combatientes;
import Habilidades.Habilidad;
//Clase heredada de combatiente
public class Enemigo extends Combatiente {
    //Nuevo atributo que sera heredado luego a Jefe
    protected Habilidad habilidad;
    //Constructor
    public Enemigo(String nombre, int poderAtaque, int puntosVida, Habilidad habilidad) {
        super(nombre, poderAtaque, puntosVida);
        this.habilidad = habilidad;
    }
    //getter
    public Habilidad getHabilidad1() {
        return habilidad;
    }
}

