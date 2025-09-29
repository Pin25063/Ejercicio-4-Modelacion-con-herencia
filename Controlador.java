//JOSE PINTO 25063

import Combatientes.*;
import Habilidades.Grito;
import Habilidades.Habilidad;
import Habilidades.Refuerzo;
import Habilidades.Regeneracion;
import Items.Botiquin;
import Items.Chaleco;
import Items.Estimulante;
import Items.Item;

import java.util.ArrayList;
import java.util.Random;
public class Controlador {
    //Metodo para crear las instancias de los heroes elegidos
    public static Combatiente crearHeroe(int op, Item item) {
        return switch (op) {
            case 1 -> new Guerrero("Joel", 15, 150, item);
            case 2 -> new Guerrero("Tommy", 15, 150, item);
            case 3 -> new Explorador("Ellie", 10, 100, item);
            case 4 -> new Explorador("Tess", 10, 100, item);
            default -> new Guerrero("Joel", 15, 150, item);
        };
    }
    //Metodo para crear las instancias de los items elegidos para los heroes
    public static Item crearItem(int op) {
        return switch (op) {
            case 1 -> new Botiquin("Botiquin", 3);
            case 2 -> new Estimulante("Estimulante", 3);
            case 3 -> new Chaleco("Chaleco", 3);
            default -> new Botiquin("Botiquin", 3);
        };
    }
    //Metodo para crear el jefe infectado fijo
    public static Jefe crearJefe() {
        Habilidad refuerzo = new Refuerzo("Refuerzo", 2);
        Habilidad grito = new Grito("Grito", 2);
        return new Jefe("Gordinflón", 15, 200, refuerzo, grito);
    }
    //Metodo para crear distintos tipos de enemigos generados aleartoriamente
    public static Enemigo crearEnemigo(Random rnd) {
        int tipo = rnd.nextInt(3);
        return switch (tipo) {
            case 0 -> new Enemigo("Corredor", 10, 90, new Regeneracion("Regen", 3));
            case 1 -> new Enemigo("Acechador", 10, 120, new Refuerzo("Refuerzo", 3));
            default -> new Enemigo("Clicker", 10, 140, new Grito("Grito", 3));
        };
    }
    //Metodo para verificar si hay enemigos vivos aun
    public static boolean hayEnemigosVivos(ArrayList<Enemigo> enemigos) {
        for (Enemigo e : enemigos) if (e.estaVivo()) return true;
        return false;
    }
}