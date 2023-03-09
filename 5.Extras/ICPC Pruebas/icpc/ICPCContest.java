package icpc;
import shapes.*;
/**
 * Write a description of class ICPCContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ICPCContest
{
    boolean ok = false;
    ICPC icpc;
    private int[][] redICPC;

    /**
     * Metodo que da solucion al problema de la red de ICPC.
     * @param int cost, Costo de poner una sennal.
     * @param int[][] routesSpeedLimist, Matriz con la red ICPC.
     */
    public int solve(int cost, int routeSpeedLimits[][]){
        int totalCost = 0;
        int maxSpeedLimit = maxSpeedLimit(routeSpeedLimits);
        if(cost >= 100){
            for(int i = 0; i < routeSpeedLimits.length;i++){
                totalCost += (maxSpeedLimit - routeSpeedLimits[i][2]);
            }
            matrizIcpcCm(cost,maxSpeedLimit,routeSpeedLimits);
        }
        else if(cost > 0 && cost < 100 ){
            int[][] redIcpc= new int[routeSpeedLimits.length][4];
            for(int i = 0; i < routeSpeedLimits.length;i++){
                int diferencia = maxSpeedLimit - routeSpeedLimits[i][2];
                redIcpc[i][0] = routeSpeedLimits[i][0];
                redIcpc[i][1] = routeSpeedLimits[i][1];
                if(diferencia > cost || diferencia == 0){
                    totalCost += cost;
                    redIcpc[i][2] = routeSpeedLimits[i][2];
                    redIcpc[i][3] = 1;
                }
                else{
                    totalCost += diferencia;
                    redIcpc[i][2] = maxSpeedLimit;
                    redIcpc[i][3] = 0;
                }
            }
            redICPC = redIcpc;
        }
        return totalCost;
    }

    /**
     * Metodo que simula la construccion de una red ICPC
     * @param int cost, Costo de poner una sennal.
     * @param int[][] routesSpeedLimist, Matriz con la red ICPC.
     */
    public void simulate(int cost, int routeSpeedLimits[][]){
        solve(cost,routeSpeedLimits);
        if(cost > 0 && cost < 100 ){
            this.icpc = new ICPC(cost,routeSpeedLimits);
            for(int i = 0;i < redICPC.length;i++){
                if(redICPC[i][3]==1){
                    int intersectionA = redICPC[i][0];
                    int intersectionB = redICPC[i][1];
                    String interA = this.icpc.numberColor(intersectionA);
                    String interB = this.icpc.numberColor(intersectionB);
                    this.icpc.putSign(interA,interB,redICPC[i][2]);
                }
            }
        }
        else if(cost >= 100){
             this.icpc = new ICPC(cost,redICPC);
        }
        this.icpc.makeVisible();
        ok = true;
    }

    /**
     * Metodo que verifica la operacion indicada se pudo ejecutar en el simulador, este devolvera un mensaje de referencia.
     * @return boolean, true si la operacion se realizó satisfactoriamente, de lo contrario false.
     */
    public boolean ok(){
        if (this.icpc.isVisible) {
            if (!this.ok) {
                System.out.println("La accion no se pudo realizar.");
            }
            else{
                System.out.println("La accion se pudo realizar.");
            }
        }
        return ok;
    }

    /*
     * Metodo que genera la matriz de la red icpc cuando el costo es muy elevado.
     * int valorMaximo, limite de velocidad maximo.
     */
    private void matrizIcpcCm(int cost,int valorMaximo , int matriz[][]){
        int speedLimits[][] = new int[matriz.length][3];
        for(int i = 0; i< matriz.length;i++){
            speedLimits[i][0]= matriz[i][0];
            speedLimits[i][1]= matriz[i][1];
            speedLimits[i][2]= valorMaximo;
        }
        redICPC = speedLimits;
    }

    /*
     * Metodo que da el maximo limite de velociad
     * @param int[][] SpeedLimits, matriz redIcpc
     * @return int maxSpeed, maximo limite de velocidad.
     */
    public int maxSpeedLimit(int [][] routeSpeedLimits){
        int maxSpeedLimit = routeSpeedLimits[0][2];
        for(int i = 1; i < routeSpeedLimits.length;i++){
            if(routeSpeedLimits[i][2] > maxSpeedLimit){
                maxSpeedLimit = routeSpeedLimits[i][2];
            }
        }
        return maxSpeedLimit;
    }
}
