/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percolar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author estma_000
 */
public class Percolar {

    //Parámetros de la distribucion uniforme
    private static final int a = 0;
    private static final int b = 1;
    private static final int simulaciones = 2;
    //Variables del sistema
    private static int n;
    private static double deltaP;
    
    public static void main(String[] args) {
        //Delta que valida como se va a distribuir la probabilidad en cada una 
        //de las celdas de la piedra
        deltaP = 0.5;
        double countDelta = 0;
        int countSimulaciones = 0;
        n = 3;
        //Matriz que almacena las posiciones llenas
        CasillaPiedra[][] piedra = new CasillaPiedra[n][n];
        ArrayList<Double> totalSimulaciones = new ArrayList<>();
        //Se llenan las posiciones segun el delta
        while (countDelta <= 1 ){
            int contadorExitos = 0;
            while (countSimulaciones < simulaciones){
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){  
                        CasillaPiedra casilla = new CasillaPiedra(i, j);
                        // La piedra en esa posicion es solida
                        if (distribucionUniforme() < countDelta){
                            casilla.setEsPiedra(true);
                        }
                        //La piedra en esa posicion es hueca
                        else {
                            casilla.setEsPiedra(false);
                        }
                        piedra[i][j] = casilla;
                        System.out.print(piedra[i][j].getEsPiedra() + " ");
                    }
                    System.out.println();
                }
                contadorExitos = contadorExitos + recorrer(piedra);
                countSimulaciones++;
            }
            totalSimulaciones.add((double)(contadorExitos/simulaciones));
            countDelta = countDelta + deltaP;
            countSimulaciones = 0;
            System.out.println();
        }
        for (Double totalSimulacione : totalSimulaciones) {
            System.err.print(totalSimulacione + " ");
        }
    }
    
    private static double distribucionUniforme(){
        Random random = new Random();
        double x = random.nextDouble();
        return (x - a)/(b - a);
    }
    
    private static int recorrer(CasillaPiedra[][] leerPiedra){
        //Se lee de abajo hacia arriba
        for (int i = 0; i < n; i++){
            //Se crea una cola vacia para almacenar los vecinos del grafo
            Queue<CasillaPiedra> cola = new LinkedList<CasillaPiedra>();
            //Se identifican los huecos vacios de la ultima columna
            if (leerPiedra[i][n-1].getEsPiedra() == false){
                //Se añade a la cola
                cola.add(leerPiedra[i][n-1]);
                int x = 0;
                int y = 0;
                leerPiedra[i][n-1].setEsPiedra(true);
                //Se recorre mientras la cola no esté vacía
                while(!cola.isEmpty()){
                    //Se extrae de la cola
                    CasillaPiedra tempo = cola.remove();
                    x = tempo.getPosX();
                    y = tempo.getPosY();
                    //Se pregunta si y = 0, lo que significa que si se encontro un camino
                    //hasta la parte de arriba
                    if (y == 0) return 1;
                    
                    //Se pregunta por los vecinos --arriba
                    if ((y - 1) > 0 && leerPiedra[x][y-1].getEsPiedra() == false){
                        cola.add(leerPiedra[x][y-1]);
                        leerPiedra[x][y-1].setEsPiedra(true);
                    }
                    //Se pregunta por los vecinos --izquierda
                    if ((x + 1) < n && leerPiedra[x+1][y].getEsPiedra() == false){
                        cola.add(leerPiedra[x+1][y]);
                        leerPiedra[x+1][y].setEsPiedra(true);
                    }
                    //Se pregunta por los vecinos --abajo
                    if ((y + 1) < n && leerPiedra[x][y+1].getEsPiedra() == false){
                        cola.add(leerPiedra[x][y+1]);
                        leerPiedra[x][y+1].setEsPiedra(true);
                    }
                    //Se pregunta por los vecinos --derecha
                    if ((x - 1) > 0 && leerPiedra[x-1][y].getEsPiedra() == false){
                        cola.add(leerPiedra[x-1][y]);
                        leerPiedra[x-1][y].setEsPiedra(true);
                    }
                }
            }
        }
        return 0;
    }
    
}
