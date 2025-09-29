//JOSE PINTO 25063
package Combatientes;
public class Combatiente {
    protected String nombre;
    protected final int vidaMax;
    protected int puntosVida;
    protected int poderAtaque;
    protected double defensa; //se agrego defensa para poder aplicar de mejor manera las habilidades e items que afectan al daño que reciben los combatientes
    //Se quito mensaje de atributos para utilizarlo de otra manera
    //Constructor que sera heredado a todos las subclases de combatiente
    public Combatiente(String nombre, int poderAtaque, int vidaMax) {
        this.nombre = nombre;
        this.poderAtaque = poderAtaque;
        this.vidaMax = vidaMax;
        this.puntosVida = vidaMax;
        this.defensa = 0;
    }
    //Metodo para restar daño en fucnion de la defensa del objetivo
    public void recibirDano(int dano) {
        int real = Math.max(0, (int) Math.round(dano * (1 - this.defensa)));
        this.puntosVida = Math.max(0, this.puntosVida - real);
    }
    //Metodo para llamar al metodo que realiza el daño al objetivo
    public void atacarA(Combatiente objetivo) {
        if (objetivo == null || !this.estaVivo()) return;
        objetivo.recibirDano(this.poderAtaque);
    }
    //Boolean para verificar que la vida sea mayor a 0
    public boolean estaVivo(){
        return this.getPuntosVida() > 0;
    }
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntosVida() {
        return puntosVida;
    }
    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
    public int getPoderAtaque() {
        return poderAtaque;
    }
    public void setPoderAtaque(int poderAtaque) {
        this.poderAtaque = poderAtaque;
    }
    public int getVidaMax() {
        return vidaMax;
    }
    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }
    public double getDefensa(){
        return defensa;
    }
}