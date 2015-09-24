/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percolar;

/**
 *
 * @author estma_000
 */
public class CasillaPiedra {
    
    boolean esPiedra;
    int posX;
    int posY;

    public CasillaPiedra(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
    public boolean getEsPiedra(){
        return esPiedra;
    }
    
    public void setEsPiedra(boolean esPiedra){
        this.esPiedra = esPiedra;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}
