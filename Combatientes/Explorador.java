//JOSE PINTO 25063
package Combatientes;
import Items.Item;
//Clase heredada de Combatiente
public class Explorador extends Combatiente {
    protected Item item;
    //Constructor con Item agregado como atributo
    public Explorador(String nombre, int poderAtaque, int puntosVida, Item item) {
        super(nombre, poderAtaque,puntosVida);
        this.item = item;
    }
    //Getters y setters
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
