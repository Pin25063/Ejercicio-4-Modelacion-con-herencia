//JOSE PINTO 25063
package Items;
import Combatientes.Combatiente;
//Subclase de Item
public class Chaleco extends Item{
    //Constructor heredado
    public Chaleco(String nombre, int usos) {
        super(nombre, usos);
    }
    //Metodo de lo que realiza el item restando los usos
    public boolean reforzar(Combatiente combatiente){
        if (this.usos > 0) {
            combatiente.setDefensa(combatiente.getDefensa() + 0.25);
            this.usos--;
            return true;
        }
        return false;
    }
}
