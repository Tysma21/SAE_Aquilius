package fr.sae.aquilius.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage{

    private IntegerProperty y;
    private IntegerProperty x;
    private boolean droite;
    private boolean gauche;
    private boolean bas;
    private boolean haut;
    private Terrain terrain;
    private Inventaire inventaire;

    public Personnage (int x, int y, Terrain terrain,Inventaire inventaire) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.droite = false;
        this.gauche = false;
        this.bas = true;
        this.haut = false;
        this.terrain = terrain;
        this.inventaire=inventaire;
    }

    public int getX() {
        return this.x.getValue();
    }
    public int getY() {
        return this.y.getValue();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public IntegerProperty xProperty() {
        return x;
    }
    public IntegerProperty yProperty(){
        return y;
    }

    public boolean collisionBloc(char sens){
        boolean bloc ;
        int xPer = this.getX();
        int yPer = this.getY();
        System.out.println("Début systeme collision.");

        if((sens == 'd') && terrain.getBlock(xPer+32,yPer) != 3 ){
            bloc = true;
            System.out.println("Bloc droite.");
        }
        else if((sens == 'g') && terrain.getBlock(xPer,yPer) != 3 ){
            bloc = true;
            System.out.println("Bloc gauche.");
        }
        else {
            bloc = false;
            System.out.println("Il n ya pas de bloc.");
        }
        System.out.println("Fin systeme collision.");
        return bloc;


    }

    public void deplacer(){

        int xdes;
        int ydes;

        if (droite && !collisionBloc('d')){
            xdes = (this.getX())+32;
            if(xdes <= 960)
                this.setX(this.getX()+10);
        }
        else if (gauche &&!collisionBloc('g')){
            xdes = (this.getX()-10);
            if(xdes >= 0)
                this.setX(this.getX()-10);
        }
        else if (haut){
            ydes = (this.getY()-10);
            if(ydes >= 0)
                this.setY(this.getY()-10);
        }
        else if(bas && !estAuSol()){
            this.setY(this.getY()+10);
        }
        if (!haut)
            appliqueGravite();
    }

    public void DeplacementHeroDroite() { droite = true; }

    public void DeplacementHeroGauche() {
        gauche = true;
    }

    public void DeplacementHeroHaut() {
        haut = true;
    }
    public void arretDeplacementHeroDroite() {
        droite = false;
    }
    public void arretDeplacementHeroGauche() {
        gauche = false;
    }
    public void arretDeplacementHeroHaut() {
        haut = false;
    }


    public boolean estAuSol(){
        boolean sol ;
        int xPer = this.getX();
        int yPer = this.getY();

        if(terrain.getBlock(xPer,yPer+32) != 3 ){
            sol = true;
        }
        else if(terrain.getBlock(xPer+32,yPer+32)!=3){
            sol= true;
        }
        else {
            sol=false;
        }
        return sol;

    }

    public void appliqueGravite(){
        if(!estAuSol()){
            this.y.set((int)(this.y.getValue()+9.15));
        }

    }


}