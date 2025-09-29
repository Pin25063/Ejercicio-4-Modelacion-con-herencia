//JOSE PINTO 25063
package Habilidades;
import Combatientes.Combatiente;
//Subclase de habilidad
public class Regeneracion extends Habilidad{
    //Constructor heredado
    public Regeneracion(String nombre, int usos) {
        super(nombre, usos);
    }
    //Metodo de la habilidad restando los usos, con validacion por si se alcanza la vida maxima
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
