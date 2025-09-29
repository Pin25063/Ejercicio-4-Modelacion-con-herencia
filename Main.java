import Combatientes.*;
import Habilidades.*;
import Items.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);

        //Menu y solicitud de primeros datos
        System.out.println("== THE LAST OF US (RPG)==\n");
        System.out.println("Bienvenido a el RPG de The last of us. Primero seleccione a sus heroes.");
        //Solicitud y validacion de seleccion de personajes
        int op1 = 0;
        //Variable while para control de ciclo para crear al personaje
        boolean op1Ok = false;
        while (!op1Ok) {
            System.out.println("Seleccione al primer heroe\n1. Joel (Guerrero)\n2. Tommy (Guerrero)\n3. Ellie (Exploradora)\n4. Tess (Exploradora)");
            op1 = sc.nextInt(); sc.nextLine();
            //Validacion de rango de seleccion
            if (op1 > 0 && op1 <= 4) {
                op1Ok = true;
            } else {
                System.out.println("\nOpcion no valida\n");
            }
        }

        //Solicitud y validacion de item para primer personaje
        int obj1 = 0;
        //Variable while para control de ciclo para crear al personaje
        boolean obj1Ok = false;
        while (!obj1Ok) {
            System.out.println("Selecciona el objeto para ese personaje: \n1. Botiquin (cura 30 de salud)\n2. Estimulante (aumenta 25% de daño)\n3. Chaleco (aumenta 25% de defensa)");
            obj1 = sc.nextInt(); sc.nextLine();
            //Validacion de rango de seleccion
            if (obj1 > 0 && obj1 <= 3) {
                obj1Ok = true;
            } else {
                System.out.println("\nOpcion no valida\n");
            }
        }
        //Creacion de instancia de objeto
        Item item1 = Controlador.crearItem(obj1);
        //Validacion para la vetaja de objetos de el Herore de tipo explorador
        if (op1 == 3 || op1 == 4) {
            item1.setUsos(item1.getUsos() * 2);
        }
        //Creacion de la instancia del primer personaje
        Combatiente personaje1 = Controlador.crearHeroe(op1, item1);

        //Solicitud y validacion de segundo personaje
        int op2 = 0;
        //Variable while para control de ciclo para crear al personaje
        boolean op2Ok = false;
        while (!op2Ok) {
            System.out.println("Elija al segundo heroe: \n1. Joel (Guerrero)\n2. Tommy (Guerrero)\n3. Ellie (Exploradora)\n4. Tess (Exploradora)");
            op2 = sc.nextInt();
            sc.nextLine();
            //Validacion de rango de seleccion
            if (op2 > 0 && op2 <= 4) {
                //Validacion de que no se repita el mismo personaje
                if (op1 == op2) {
                    System.out.println("\nNo puede elegir al mismo heroe\n");
                } else {
                    op2Ok = true;
                }
            } else {
                System.out.println("\nOpcion no valida\n");
            }
        }

        //Solicitud y validacion de item para segundo personaje
        int obj2 = 0;
        boolean obj2Ok = false;
        //Ciclo while para valicacion de seleccion
        while (!obj2Ok) {
            System.out.println("Selecciona el objeto para ese personaje: \n1. Botiquin\n2. Estimulante\n3. Chaleco");
            obj2 = sc.nextInt();
            sc.nextLine();
            //Validacion de rango de seleccion
            if (obj2 > 0 && obj2 <= 3) {
                obj2Ok = true;
            } else {
                System.out.println("\nOpcion no valida\n");
            }
        }
        //Creacion de instancia del item
        Item item2 = Controlador.crearItem(obj2);
        //Validacion para la vetaja de objetos de el Herore de tipo explorador
        if (op2 == 3 || op2 == 4) {
            item2.setUsos(item2.getUsos() * 2);
        }
        //Creacion de personaje
        Combatiente personaje2 = Controlador.crearHeroe(op2, item2);

        //Creacion de los enemigos
        Jefe gordinflon = Controlador.crearJefe();
        //Array de los enemigos
        ArrayList<Enemigo> infectados = new ArrayList<>();
        infectados.add(gordinflon);
        int extras = rnd.nextInt(2) + 1;

        //Ciclo for para crear enemigos
        for (int i = 0; i < extras; i++) {
            infectados.add(Controlador.crearEnemigo(rnd));
        }

        //Ciclo de juego
        while ( (personaje1.estaVivo() || personaje2.estaVivo()) && Controlador.hayEnemigosVivos(infectados) ) {
            //Turno de los aliados
            mostrarTablero(personaje1, personaje2, infectados);
            if (personaje1.estaVivo()) turnoHeroe(sc, personaje1, infectados);
            mostrarTablero(personaje1, personaje2, infectados);
            if (personaje2.estaVivo()) turnoHeroe(sc, personaje2, infectados);
            //Ciclo for para los enemigos
            for (Enemigo e : infectados) {
                //Verificacion por si esta vivo
                if (e.estaVivo()){
                    mostrarTablero(personaje1, personaje2, infectados);
                    turnoEnemigo(sc, e, personaje1, personaje2);
                }
            }
        }

        //Verificacion de vencedor de la pelea
        if (!personaje1.estaVivo() && !personaje2.estaVivo()) System.out.println("\nGanan los infectados!");
        else System.out.println("\nGanan los sobrevivientes!");
    }

    //Metodo para realizar la accion seleccionada para los heroes
    public static void turnoHeroe(Scanner sc, Combatiente heroe, ArrayList<Enemigo> enemigos) {
        System.out.println("\nTurno de " + heroe.getNombre() + " | PV=" + heroe.getPuntosVida());
        System.out.println("1) Atacar  2) Usar objeto  3) Pasar");
        int op = sc.nextInt(); sc.nextLine();

        switch (op) {
            case 1 -> { // elegir enemigo vivo
                Enemigo objetivo = elegirEnemigoObjetivo(sc, enemigos);
                if (objetivo != null) {
                    heroe.atacarA(objetivo);
                    System.out.println(heroe.getNombre() + " ataca a " + objetivo.getNombre());
                }
            }
            case 2 -> usarItem(heroe);
            default -> System.out.println(heroe.getNombre() + " pasa turno.");
        }
    }

    //Mertodo para verificar el efecto que realiza el Item, y generacion de variable boolean para metodo que ejecutara el efecto
    public static void usarItem(Combatiente heroe) {
        Item it = null;
        if (heroe instanceof Guerrero)   it = ((Guerrero) heroe).getItem();
        if (heroe instanceof Explorador) it = ((Explorador) heroe).getItem();
        //Switch case para el objeto Item
        boolean ok = false;
        switch (it) {
            case Botiquin botiquin -> ok = botiquin.regenerar(heroe);
            case Estimulante estimulante -> ok = estimulante.estimular(heroe);
            case Chaleco chaleco -> ok = chaleco.reforzar(heroe);
            default -> {
            }
        }
        //Impresion en pantalla para mostrar que se realizo
        System.out.println(ok ? "Usaste " + it.getNombre() + ". Usos: " + it.getUsos() : "No quedan usos de " + it.getNombre() + ".");
    }

    //Metodo para verificar el efecto que realiza el Item y ejecutar el efecto
    public static void usarHabilidad(Scanner sc, Enemigo enemigo) {
        //Verificar si el ususario es jefe para mostrar que habilidad quiere usar
        if (enemigo instanceof Jefe j) {
            Habilidad h1 = j.getHabilidad1();
            Habilidad h2 = j.getHabilidad2();
            System.out.println("Elige habilidad:");
            System.out.println("1) " + h1.getNombre() + " (usos: " + h1.getUsos() + ")");
            System.out.println("2) " + h2.getNombre() + " (usos: " + h2.getUsos() + ")");
            int op = sc.nextInt(); sc.nextLine();
            //Aplicar habilidad
            if (op == 1) {
                aplicarHabilidad(h1, enemigo);
            }
            else {aplicarHabilidad(h2, enemigo);}
        } else {
            //Aplicar habilidad de enemigo
            Habilidad h = enemigo.getHabilidad1();
            aplicarHabilidad(h, enemigo);
        }
    }

    //Aplicar efectos de habilidad dependiendo de el caso
    public static void aplicarHabilidad(Habilidad h, Enemigo enemigo) {
        boolean ok = false;
            switch (h){
                case Regeneracion regeneracion -> ok = regeneracion.regenerar(enemigo);
                case Grito grito -> ok = grito.gritar(enemigo);
                case Refuerzo refuerzo -> ok = refuerzo.reforzar(enemigo);
                default -> {
                }
            }
        System.out.println(ok ? "Usaste " + h.getNombre() + ". Usos: " + h.getUsos() : "No quedan usos de " + h.getNombre() + ".");
    }

    //Metodo para mostrar a los enemigos para atacarlos
    public static Enemigo elegirEnemigoObjetivo(Scanner sc, ArrayList<Enemigo> enemigos) {
        //Array para llevar control de enemigos vivos
        ArrayList<Integer> vivos = new ArrayList<>();
        System.out.println("Elige enemigo:");
        //For para agregar a los enemigos vivos
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            if (e.estaVivo()) {
                vivos.add(i);
                //Mostrar en pantalla enemigos vivos
                System.out.println((vivos.size()) + ") " + e.getNombre());
            }
        }
        //opcion de enemigo a aracar
        int opcion = sc.nextInt(); sc.nextLine();
        int idx = vivos.get(Math.max(0, Math.min(opcion-1, vivos.size()-1)));
        return enemigos.get(idx);
    }

    //Metodo para realizar la accion de los infectados
    public static void turnoEnemigo(Scanner sc, Enemigo enemigo, Combatiente p1, Combatiente p2) {
        System.out.println("\nTurno de " + enemigo.getNombre());
        System.out.println("1) Atacar  2) Usar habilidad  3) Pasar");
        int op = sc.nextInt(); sc.nextLine();
        //Switch para elegir que realiza el infectado
        switch (op) {
            case 1 -> { // elegir héroe objetivo vivo
                Combatiente objetivo = elegirHeroeObjetivo(sc, p1, p2);
                enemigo.atacarA(objetivo);
                System.out.println(enemigo.getNombre() + " ataca a " + objetivo.getNombre());
                }
            case 2 -> usarHabilidad(sc, enemigo);
            default -> System.out.println(enemigo.getNombre() + " pasa turno.");
        }
    }

    //Metodo para retornar el objetivo del atacante
    public static Combatiente elegirHeroeObjetivo(Scanner sc, Combatiente p1, Combatiente p2) {
        //Array a mostrar de los heroes vivos
        ArrayList<Combatiente> vivos = new ArrayList<>();
        if (p1.estaVivo()) vivos.add(p1);
        if (p2.estaVivo()) vivos.add(p2);
        if (vivos.isEmpty()) return null;
        //Mostrar en pantalla los heroes vivos
        System.out.println("Elige héroe:");
        for (int i = 0; i < vivos.size(); i++) {
            Combatiente c = vivos.get(i);
            System.out.println((i+1) + ") " + c.getNombre() + " (PV=" + c.getPuntosVida() + ")");
        }
        //Opcion de seleccion de heroe a atacar
        int op = sc.nextInt(); sc.nextLine();
        int i = Math.max(0, Math.min(op-1, vivos.size()-1));
        return vivos.get(i);
    }

    //Mostrar todos los estados de los hreoes e infectados
    private static void mostrarTablero(Combatiente p1, Combatiente p2, ArrayList<Enemigo> enemigos) {
        System.out.println("\n=== ESTADO DE LA BATALLA ===");
        mostrarHeroe(p1);
        mostrarHeroe(p2);
        for (Enemigo enemigo : enemigos) {
            mostrarEnemigo(enemigo);
        }
        System.out.print("============================");
    }

    //Mostrar en pantalla los atributos de los heroes
    private static void mostrarHeroe(Combatiente c) {
        int defPt = (int) Math.round(c.getDefensa() * 100);
        System.out.print(c.getNombre() + "  PV: " + c.getPuntosVida() + "/" + c.getVidaMax() + "  DEF: " + defPt + "%");
        Item it = null;
        if (c instanceof Guerrero) {
            it = ((Guerrero) c).getItem();
        }
        else if (c instanceof Explorador) {
            it = ((Explorador) c).getItem();
        }
        System.out.print("  Item: " + it.getNombre() + " (usos: " + it.getUsos() + ")");
        System.out.println();
    }

    //Mostrar en pantalla los atributos de los infectados
    private static void mostrarEnemigo(Enemigo e) {
        int defPt = (int) Math.round(e.getDefensa() * 100);
        System.out.print(e.getNombre() + "  PV: " + e.getPuntosVida() + "/" + e.getVidaMax() + "  DEF: " + defPt + "%");
        //Por si la instancia es Jefe, se utilizan diferentes metodos
        if (e instanceof Jefe j) {
            Habilidad h1 = j.getHabilidad1();
            Habilidad h2 = j.getHabilidad2();
            System.out.print("  Hab1: " + h1.getNombre() + " (" + h1.getUsos() + ")");
            System.out.print("  | Hab2: " + h2.getNombre() + " (" + h2.getUsos() + ")" );
        } else {
            Habilidad h = e.getHabilidad1();
            System.out.print("  Hab: " + h.getNombre() + " (usos: " + h.getUsos() + ")");
        }
        System.out.println();
    }
}