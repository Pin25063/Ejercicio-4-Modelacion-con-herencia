//JOSE PINTO 25063
package Habilidades;
import Combatientes.Combatiente;
//Clase heredada de Habilidad
public class Refuerzo extends Habilidad{
    //Constructor heredado
    public Refuerzo(String nombre, int usos) {
        super(nombre, usos);
    }
    //Metodo de la habilidad restando los usos
    public boolean reforzar(Combatiente combatiente){
        if (this.usos > 0) {
            combatiente.setDefensa(combatiente.getDefensa() + 0.25);
            this.usos--;
            return true;
        }
        return false;
    }
}