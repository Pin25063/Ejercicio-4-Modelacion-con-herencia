//JOSE PINTO 25063
package Items;
import Combatientes.Combatiente;
//Subclase de Item
public class Botiquin extends Item {
    //Constructor heredado
    public Botiquin(String nombre, int usos) {
        super(nombre, usos);
    }
    //Metodo de lo que realiza el item restando usos
    public boolean regenerar(Combatiente usuario){
        int vida = usuario.getVidaMax();
        int newVida = usuario.getPuntosVida() + 30;
        if (this.usos > 0) {
            usuario.setPuntosVida(newVida > vida ? usuario.getVidaMax() : newVida);
            this.usos--;
            return true;
        }
        return false;
    }
}
