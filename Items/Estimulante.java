//JOSE PINTO 25063
package Items;
import Combatientes.Combatiente;
//Suclase de Item
public class Estimulante extends Item {
    //Constructor heredado
    public Estimulante(String nombre, int usos) {
        super(nombre, usos);
    }
    //Metodo de lo que realiza el item
    public boolean estimular (Combatiente usuario){
        if (this.getUsos() > 0) {
            usuario.setPoderAtaque(usuario.getPoderAtaque() + 10);
            this.usos--;
            return true;
        }
        return false;
    }
}
