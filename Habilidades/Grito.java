//JOSE PINTO 25063
package Habilidades;
import Combatientes.Combatiente;
//Clase heredada de habilidad
public class Grito extends Habilidad {
    //Construcor
    public Grito(String nombre, int usos) {
        super(nombre, usos);
    }
    //Metodo gritar que resta usos a su vez
    public boolean gritar (Combatiente usuario){
        if (this.usos > 0) {
            usuario.setPoderAtaque(usuario.getPoderAtaque() + 10);
            this.usos--;
            return true;
        }
        return false;
    }
}
