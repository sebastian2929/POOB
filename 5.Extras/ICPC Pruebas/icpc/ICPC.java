package icpc;
import shapes.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Crea un red ICPC donde se pueden agregar y modificar intercepciones, rutas y sennales.
 * 
 * @author Andres Felipe Arias Ajiaco y Sebastian David Blanco Rodriguez.
 * @version 16/10/22
 */
//cree dos metodos privados para verificar si los tipos de rutas e intersecciones exiten(por si ponen algun tipo que no es)
//ya funciona bien "hermit", cambie un poco el metodo privado que estabamos haciendo 
//Cree las dos clases que herdan de ruta (Fixed y Rebel) y el metodo de sobrecarga de ICPC de rutas
//cada tipo de ruta tiene diferente color la normal = gris, la rebel = naranja y la fixed = cyan
public class ICPC
{
    private HashMap<String,Interseccion> intersecciones = new HashMap <String,Interseccion>();
    private HashMap<String,Ruta> rutas = new HashMap <String,Ruta>();
    private HashMap<String,Sennal> sennales = new HashMap <String,Sennal>();
    public boolean isVisible;
    private int width;
    private int length;
    private boolean ok;
    private int cost = 0;
    //jcolor

    /**
     * Constructor de la red ICPC.
     * @param int length, Longitud del canvas.
     * @param int width, Ancho del canvas.
     */
    public ICPC(int length, int width){
        this.ok = false;
        this.width = width;
        this.length = length;
        Canvas board = Canvas.getCanvas("ICPC",length,width);
        board.redraw1();
        this.ok = true;
    }

    /**
     * Constructor de la red ICPC.
     * @param int length, Longitud del canvas.
     * @param int width, Ancho del canvas.
     * @param int cost, Costo de poner una sennnal.
     */
    public ICPC(int length, int width, int cost) {
        this.ok = false;
        this.width = width;
        this.length = length;
        Canvas board = Canvas.getCanvas("ICPC",width,length);
        board.redraw1();
        if(cost > 0 ){
            this.cost = cost;
            this.ok = true; 
        }   
    }

    /**
     * Constructor de la red ICPC.
     * @param int cost, Costo de poner una sennal.
     * @param int[][] routesSpeedLimist, Matriz con la red ICPC.
     */
    public ICPC(int cost, int[][] routesSpeedLimits) {
        this.ok = false;
        if(cost > 0 ){
            this.cost = cost;
        }
        Canvas board = Canvas.getCanvas("ICPC",1000,1000);
        board.redraw1();
        for(int i = 0;i < routesSpeedLimits.length;i++){
            ArrayList<String> contColor = new ArrayList<String>();
            for(int j = 0;j < 2;j++){
                String Color = numberColor(routesSpeedLimits[i][j]);
                contColor.add(j,Color);
                int x = randomNumber();
                int y = randomNumber();
                addIntersection(Color,x,y);
            }
            addRoute(contColor.get(0),contColor.get(1));
            Ruta ruta = rutas.get(contColor.get(0)+contColor.get(1));
            ruta.setLimiteVelocidad(routesSpeedLimits[i][2]);
        }
        this.ok = true;
    }

    /**
     * Metodo que annade una interseccion a la red ICPC.
     * @param String color, Cadena con el color de la interseccion.
     * @param int x, Posicion en x de la interseccion.
     * @param int y, Posicion en y de la interseccion.
     */
    public void addIntersection(String color, int x, int y){
        this.ok = false;
        if(!intersecciones.containsKey(color) && !verificarPosInterseccion(x,y)){
            intersecciones.put(color,new Interseccion(numberColor(color),x,y));
            this.ok = true;
            if (this.isVisible) {
                Interseccion interseccion = intersecciones.get(color);
                interseccion.makeVisible();
            }
        }
        else{
            System.out.println("La interseccion no se puede crear");
        }
    }

    /**
     * Metodo que annade una interseccion a la red ICPC.
     * @param String type, tipo de interseccion
     * @param String color Cadena con el color de la interseccion.
     * @param int x, Posicion en x de la interseccion.
     * @param int y, Posicion en y de la interseccion.
     */
    public void addIntersection(String type, String color, int x, int y){
        this.ok = false;
        if(!intersecciones.containsKey(color) && !verificarPosInterseccion(x,y) && typeIntersectionExist(type)){
            if(type.equals("normal")){
                intersecciones.put(color,new Interseccion(numberColor(color),x,y));
            }
            if(type.equals("hermit")){
                intersecciones.put(color,new Hermit(numberColor(color),x,y));
            }
            if(type.equals("needy")){
                intersecciones.put(color,new Needy(numberColor(color),x,y));
            }
            this.ok = true;
            if (this.isVisible) {
                Interseccion interseccion = intersecciones.get(color);
                interseccion.makeVisible();
            }
        }
        else{
            System.out.println("La interseccion no se puede crear");
        }
    }

    /**
     * Metodo que annade una ruta a la red ICPC.
     * @param String intersectionA, Color con la interseccion A (posicion inicial de la ruta).
     * @param String intersectionB, Color con la interseccion B (posicion final de la ruta).
     */
    public void addRoute(String intersectionA, String intersectionB){
        this.ok = false;
        if(intersectionA != intersectionB && intersecciones.containsKey(intersectionA) && intersecciones.containsKey(intersectionB) && 
        !compararKeyRuta(intersectionA,intersectionB) && ifIsHermit(intersectionA,intersectionB)){
            Interseccion interseccionA = intersecciones.get(intersectionA);
            Interseccion interseccionB = intersecciones.get(intersectionB);
            rutas.put(intersectionA+intersectionB,new Ruta(interseccionA,interseccionB));
            Ruta ruta = rutas.get(intersectionA+intersectionB);
            interseccionA.verificaRuta(ruta);
            interseccionB.verificaRuta(ruta);
            this.ok = true;
            if (this.isVisible) {
                ruta.makeVisible();
            }
        }
        else{
            System.out.println("La ruta no se puede crear");
        }
    }

    /**
     * Metodo que annade una ruta a la red ICPC.
     * @param String intersectionA, Color con la interseccion A (posicion inicial de la ruta).
     * @param String intersectionB, Color con la interseccion B (posicion final de la ruta).
     */
    public void addRoute(String type,String intersectionA, String intersectionB){
        this.ok = false;
        if(intersectionA != intersectionB && intersecciones.containsKey(intersectionA) && intersecciones.containsKey(intersectionB) && 
        !compararKeyRuta(intersectionA,intersectionB) && ifIsHermit(intersectionA,intersectionB) && typeRouteExist(type)){
            Interseccion interseccionA = intersecciones.get(intersectionA);
            Interseccion interseccionB = intersecciones.get(intersectionB);
            if(type.equals("normal")){
                rutas.put(intersectionA+intersectionB,new Ruta(interseccionA,interseccionB));
            }
            if(type.equals("fixed")){
                rutas.put(intersectionA+intersectionB,new Fixed(interseccionA,interseccionB));
            }
            if(type.equals("rebel")){
                rutas.put(intersectionA+intersectionB,new Rebel(interseccionA,interseccionB));
            }
            Ruta ruta = rutas.get(intersectionA+intersectionB);
            interseccionA.verificaRuta(ruta);
            interseccionB.verificaRuta(ruta);
            this.ok = true;
            if (this.isVisible) {
                ruta.makeVisible();
            }
        }
        else{
            System.out.println("La ruta no se puede crear");
        }
    }

    /**
     * Metodo que le asigna un limite de velocidad a una ruta existente.
     * @param String intersectionA, Color con la interseccion A (punto inicial de la ruta).
     * @param String intersectionB, Color con la interseccion B (punto final de la ruta).
     * @param int speedLimit, limite de velocidad de la rura.
     */
    public void routeSpeedLimit(String intersectionA, String intersectionB, int speedLimit){
        this.ok = false;
        if(intersectionA != intersectionB && compararKeyRuta(intersectionA,intersectionB) && speedLimit > 0){
            String clave = obtenerKeyRuta(intersectionA,intersectionB);
            Ruta ruta = rutas.get(clave);
            ruta.setLimiteVelocidad(speedLimit);
            this.ok = true;
        }
        else{
            System.out.println("No se puede establecer un limite de velocidad a la ruta");
        }
    }

    /**
     * Metodo que annade una sennal a una ruta existente.
     * @param String intersectionA, Color de la interseccionA (posicion inicial de la ruta).
     * @param String intersectionB, Color de la interseccionB (posicion final de la ruta).
     * @param int speedLimit, Limite de velocidad.
     */
    public void putSign(String intersectionA, String intersectionB, int SpeedLimit){
        this.ok = false;
        if(compararKeyRuta(intersectionA,intersectionB) && !compararKeySennal(intersectionA,intersectionB) && 
        !ifIsRebel(intersectionA,intersectionB) && SpeedLimit > 0){
            String clave = obtenerKeyRuta(intersectionA,intersectionB);
            sennales.put(clave,new Sennal(intersecciones.get(intersectionA),intersecciones.get(intersectionB),SpeedLimit));
            Sennal sennal = sennales.get(clave);
            sennal.setLimiteVelocidad(SpeedLimit);
            Ruta ruta = rutas.get(clave);
            ruta.setSennal(sennal);
            this.ok = true;
            if (this.isVisible) {
                sennal.makeVisible();
            }
        }
        else{
            System.out.println("La sennal no se puede crear");
        }
    }

    /**
     * Metodo que annade una sennal a una ruta existente.
     * @param String intersectionA, Color de la interseccionA (posicion inicial de la ruta).
     * @param String intersectionB, Color de la interseccionB (posicion final de la ruta).
     * @param int speedLimit, Limite de velocidad.
     */
    public void putSign(String type,String intersectionA, String intersectionB, int SpeedLimit){
        this.ok = false;
        if(compararKeyRuta(intersectionA,intersectionB) && !compararKeySennal(intersectionA,intersectionB) && 
        !ifIsRebel(intersectionA,intersectionB) && typeSennalExist(type) && SpeedLimit > 0){
            String clave = obtenerKeyRuta(intersectionA,intersectionB);
            if(type.equals("normal")){
                sennales.put(clave,new Sennal(intersecciones.get(intersectionA),intersecciones.get(intersectionB),SpeedLimit));
            }
            if(type.equals("twin")){
                ifIsTwinAdd(intersectionA,intersectionB,SpeedLimit);
            }
            if(type.equals("cautious")){
                SpeedLimit = getMinimunSpeedL();
                sennales.put(clave,new Cautious(intersecciones.get(intersectionA),intersecciones.get(intersectionB),SpeedLimit));
            }
            Sennal sennal = sennales.get(clave);
            sennal.setLimiteVelocidad(SpeedLimit);
            Ruta ruta = rutas.get(clave);
            ruta.setSennal(sennal);
            this.ok = true;
            if (this.isVisible) {
                sennal.makeVisible();
            }
        }
        else{
            System.out.println("La sennal no se puede crear");
        }
    }

    /**
     * Metodo que borra una interceccion de la red de ICPC.
     * @param String color, Color de la interceccion.
     */
    public void delIntersection(String color){
        this.ok = false;
        ArrayList<String> clavesRutas = verificarKeyRuta(color);
        ArrayList<String> clavesSennales = verificarKeySennal(color);
        if(intersecciones.containsKey(color) && !ifIsFixedInterseccion(color)){
            //Obtener interseccion, hacerla invisble y removerla.
            Interseccion interseccion = intersecciones.get(color);
            interseccion.makeInvisible();
            intersecciones.remove(color);
            // Se obtuvo el ArrayList de claves del HashMap de rutas y sennales que contenian como parte de key el color.
            // Se obtiene los objetos se hacen invisbles y se borran de los HashMap y del ArrayList de cada interseccion.
            for(String clave: clavesRutas){
                for(Interseccion intersection: intersecciones.values()){
                    Ruta ruta = rutas.get(clave);
                    intersection.removeRuta(ruta);
                }
            }
            for(String clave: clavesRutas){
                Ruta ruta = rutas.get(clave);
                ruta.makeInvisible();
                rutas.remove(clave);
            }
            for(String clave: clavesSennales){
                Sennal sennal = sennales.get(clave);
                sennal.makeInvisible();
                sennales.remove(clave);
            }
            this.ok = true;
        }
        else{
            System.out.println("La interseccion no se puede borrar");
        }
    }

    /**
     * Metodo que borra una ruta.
     * @param String intersectionA, Color de la interseccionA (posicion inicial de la ruta).
     * @param String intersectionB, Color de la interseccionB (posicion final de la ruta).
     */
    public void delRoad(String intersectionA, String intersectionB){
        this.ok = false;
        if(intersectionA != intersectionB && compararKeyRuta(intersectionA,intersectionB) && !ifIsFixed(intersectionA,intersectionB)){
            // Obtener la clave correcta de la ruta y las dos intersecciones.
            String claveRuta = obtenerKeyRuta(intersectionA,intersectionB);
            Interseccion interseccionA = intersecciones.get(intersectionA);
            Interseccion interseccionB = intersecciones.get(intersectionB);
            // Hacer invisible la ruta, borrar la ruta de las dos intersecciones y del HasHMap.
            Ruta ruta = rutas.get(claveRuta);
            ruta.makeInvisible();
            interseccionA.removeRuta(ruta);
            interseccionB.removeRuta(ruta);
            rutas.remove(claveRuta);
            // Si tiene una sennal asociada, hacer invisible la sennal y borrarla del HashMap
            if(sennales.containsKey(claveRuta)){
                Sennal sennal = sennales.get(claveRuta);
                sennal.makeInvisible();
                sennales.remove(claveRuta);
            }
            //si una de las interseccion es de tipo Needy y no tiene rutas debe desaparecer
            ifIsNeedy(intersectionA,intersectionB);
            this.ok = true;
        }
        else{
            System.out.println("La ruta no se puede borrar");
        }
    }

    /**
     * Metodo que borra una sennal.
     * @param String IntersectionA, Color de la interseccionA (posicion inicial de la ruta).
     * @param String IntersectionB, Color de la interseccionB (posicion inicial de la ruta).
     */
    public void removeSign(String intersectionA, String intersectionB){
        this.ok = false;
        if(intersectionA != intersectionB && compararKeySennal(intersectionA,intersectionB)){
            ifIsTwinDelete(intersectionA,intersectionB);
            // Obtener la clave de la sennal
            String claveSennal = obtenerKeyRuta(intersectionA,intersectionB);
            // Obtener la sennal, hacerla invisible y removerla de HashMap
            Sennal sennal = sennales.get(claveSennal);
            sennal.makeInvisible();
            sennales.remove(claveSennal);
            // Borrar la senal asociada a la ruta
            sennal = null;
            Ruta ruta = rutas.get(claveSennal);
            ruta.setSennal(sennal);
            this.ok = true;
        }
        else{
            System.out.println("La sennal no existe");
        }
    }

    /**
     * Metodo que perimte consultar las intercepciones.
     * @return String[] intersections, Arreglo con las los colores de las intersecciones.
     */
    public String[] intersections(){
        this.ok = false;
        String[] interseccionesColor = new String[intersecciones.size()];
        ArrayList<String> interseccionesC = new ArrayList<String>();
        int cont = 0;
        for(String key:intersecciones.keySet()){
            interseccionesC.add(key);
        }
        Collections.sort(interseccionesC);
        for(String interseccion: interseccionesC){
            interseccionesColor[cont] = interseccion;
            cont++;
        }
        this.ok = true;
        return interseccionesColor;
    }

    /**
     * Metodo que perimte consultar las rutas.
     * @return String[][] roads, Matriz con las intersecciones en la que se encuentra la ruta.
     */
    public String[][] roads(){
        this.ok = false;
        String[][] rutasColor = new String[rutas.size()][1];
        ArrayList<String> rutasC = new ArrayList<String>();
        int cont = 0;
        for(String key:rutas.keySet()){
            rutasC.add(key);
        }
        Collections.sort(rutasC);
        for(String ruta: rutasC){
            rutasColor[cont][0] = ruta;
            cont++;
        }
        this.ok = true;
        return rutasColor;
    }

    /**
     * Metodo que perimte consultar las sennales.
     * @return String[][] signs, Matriz con las intersecciones en la que se encuentra la sennal.
     */
    public String[][] signs(){
        this.ok = false;
        String[][] sennalesColor = new String[sennales.size()][1];
        ArrayList<String> sennalesC = new ArrayList<String>();
        int cont = 0;
        for(String key:rutas.keySet()){
            sennalesC.add(key);
        }
        Collections.sort(sennalesC);
        for(String sennal: sennalesC){
            sennalesColor[cont][0] = sennal;
            cont++;
        }
        this.ok = true;
        return sennalesColor;
    }

    /**
     * Metodo que determina las sennales erroneas.
     * @return String[][] wrongSigns, Matriz con las intersecciones en la que se encuentra la sennal.
     */
    public String[][] wrongSigns(){
        this.ok = false;
        String[][] wrongSigns = new String[rutas.size()][1];
        ArrayList<String> wrongS = new ArrayList<String>();
        int cont = 0;
        for(String key: rutas.keySet()){
            Ruta ruta = rutas.get(key);
            if(sennales.containsKey(key)){
                int limiteVelocidadR = ruta.getLimiteVelocidad();
                int limiteVelocidadS = ruta.getSennalRuta().getLimiteVelocidad();
                if(limiteVelocidadS > limiteVelocidadR){
                    Sennal sennal = ruta.getSennalRuta();
                    sennal.changeColor("red");
                    wrongS.add(key);
                }
            }
        }
        Collections.sort(wrongS);
        for(String sennal: wrongS){
            wrongSigns[cont][0] = sennal;
            cont++;
        }
        this.ok = true;
        return wrongSigns;
    }

    /**
     * Metodo que determina las sennales innecesarias.
     * @return String[][] unNecessarySigns, Matriz con las intersecciones en la que se encuentra la sennal.
     */
    public String[][] unNecessarySigns(){
        this.ok = false;
        String[][] unNecessaryS = new String[rutas.size()][1];
        ArrayList<String> unNecessarySigns = new ArrayList<String>();
        int cont = 0;
        int velocidadMax = maxSpeed();
        for(String key : rutas.keySet()){
            if(sennales.containsKey(key)){
                int velocidadR = rutas.get(key).getLimiteVelocidad();
                int diferencia = velocidadMax - velocidadR;
                if(diferencia <= cost){
                    Sennal sennal = sennales.get(key);
                    sennal.changeColor("cyan");
                    unNecessarySigns.add(key);
                }
            }
        }
        Collections.sort(unNecessarySigns);
        for(String sennal: unNecessarySigns){
            unNecessaryS[cont][0] = sennal;
            cont++;
        }
        this.ok = true;
        return unNecessaryS;
    }

    /**
     * Metodo que da el costo total de poner las sennales necesarias.
     * @return int totalCost, Costo total de poner la sennales.
     */
    public int totalSignsCost(){
        this.ok = false;
        int totalCost  = cost * sennales.size();
        this.ok = true;
        return totalCost;
    }

    /**
     * Metodo que hace visible la red de ICPC.
     */
    public void makeVisible() {
        this.ok = false;
        this.isVisible = true;
        for (Interseccion value : intersecciones.values()) {
            value.makeVisible();
        }
        for (Ruta value : rutas.values()) {
            value.makeVisible();
        }
        for (Sennal value : sennales.values()) {
            value.makeVisible();
        }
        this.ok = true;
    }

    /**
     * Metodo que hace invisible la red de ICPC.
     */
    public void makeInvisible() {
        this.ok = false;
        this.isVisible = true;
        for (Interseccion value : intersecciones.values()) {
            value.makeInvisible();
        }
        for (Ruta value : rutas.values()) {
            value.makeInvisible();
        }
        for (Sennal value : sennales.values()) {
            value.makeInvisible();
        }
        this.ok = true;
    }

    /**
     * Metodo que finaliza el programa.
     */
    public void finish(){
        System.exit(0);
    }

    /**
     * Metodo que verifica la operacion indicada se pudo ejecutar en el simulador, este devolvera un mensaje de referencia.
     * @return boolean, true si la operacion se realizó satisfactoriamente, de lo contrario false.
     */
    public boolean ok() {
        return this.ok;
    }

    /*
     * Metodo que verifica si dos posiciones de una interseccion ya existen.
     * @param int x, posicion x de la interseccion.
     * @param int y, posicion y de la interseccion.
     */
    private boolean verificarPosInterseccion(int x,int y){
        boolean verifica =  false;
        for (Interseccion value : intersecciones.values()) {
            int posX = value.getPositionx();
            int posY = value.getPositiony();
            if (value != null) {
                if(posX == x && posY == y){
                    verifica = true;
                }
            }
        }
        return verifica;
    }

    /*
     * Metodo que permite obtener la clave completa de un HashMap con solo una parte.
     * @param String parteKey, parte que se tiene de la Key.
     */
    private ArrayList<String> verificarKeyRuta(String parteKey){
        ArrayList<String> claves = new ArrayList<String>();
        for(String key:rutas.keySet()){
            if (key.contains(parteKey)) {
                claves.add(key);
            }
        }
        return claves;
    }

    /*
     * Metodo que permite obtener la clave completa de un HashMap con solo una parte.
     * @param String parteKey, parte que se tiene de la Key.
     */
    private ArrayList<String> verificarKeySennal(String parteKey){
        ArrayList<String> claves = new ArrayList<String>();
        for(String key:sennales.keySet()){
            if (key.contains(parteKey)) {
                claves.add(key);
            }
        }
        return claves;
    }

    /*
     * Metodo que verifica que dos intersecciones no tengan una ruta.
     * @param String intersectionA, color de la interseccion A.
     * @param String intersectionB, color de la interseccion B.
     */
    private boolean compararKeyRuta(String intersectionA, String intersectionB){
        String concatenacion1 = intersectionA+intersectionB;
        String concatenacion2 = intersectionB+intersectionA;
        boolean bandera = false;
        if(rutas.containsKey(concatenacion1) || rutas.containsKey(concatenacion2)){
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica que dos intersecciones no tengan una ruta.
     * @param String intersectionA, color de la interseccion A.
     * @param String intersectionB, color de la interseccion B.
     */
    private boolean compararKeySennal(String intersectionA, String intersectionB){
        String concatenacion1 = intersectionA+intersectionB;
        String concatenacion2 = intersectionB+intersectionA;
        boolean bandera = false;
        if(sennales.containsKey(concatenacion1) || sennales.containsKey(concatenacion2)){
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si la key existe y si no da la que si existe.
     * @param String intersectionA, color de la interseccion A.
     * @param String intersectionB, color de la interseccion B.
     */
    private String obtenerKeyRuta(String intersectionA, String intersectionB){
        String concatenacion1 = intersectionA+intersectionB;
        String concatenacion2 = intersectionB+intersectionA;
        String clave = null;
        if(rutas.containsKey(concatenacion1)){
            clave = concatenacion1;
        }
        if(rutas.containsKey(concatenacion2)){
            clave = concatenacion2;
        }
        return clave;
    }

    /*
     * Metodo que verifica si la key existe y si no da la que si existe.
     * @param String intersectionA, color de la interseccion A.
     * @param String intersectionB, color de la interseccion B.
     */
    private String inversaKeyRuta(String intersectionA, String intersectionB){
        String concatenacion1 = intersectionA+intersectionB;
        String concatenacion2 = intersectionB+intersectionA;
        String clave = null;
        if(rutas.containsKey(concatenacion1)){
            clave = concatenacion2;
        }
        if(rutas.containsKey(concatenacion2)){
            clave = concatenacion1;
        }
        return clave;
    }

    /*
     * Metodo que determina un color especifico para cada numero.
     * @param int numero, numero por el que se identifica la interseccion.
     */
    protected String numberColor(String color){
        String[] Colores = {"black","red","blue","yellow","green","magenta","cyan","grey","orange"};
        String colorStr = "black";
        for(int i=0; i < 9 ; i++){
            if(color.equals(Colores[i])){
                colorStr = Integer.toString(i);
            }
        }
        return colorStr;
    }

    /*
     * Metodo que determina el entero de color especifico.
     * @param int numero, numero por el que se identifica la interseccion.
     */
    protected String numberColor(int color){
        String colorStr = Integer.toString(color);
        return colorStr;
    }

    /*
     * Metodo que genera un numero unico.
     * @return int numero, numero random.
     */
    private int randomNumber(){
        int numero = (int) (Math.random()*(591 - 35) + 35);
        return numero;
    }

    /*
     * Metodo que da la velocidad maxima de un arreglo.
     * @return int max, Velocidad maxima.
     */
    private int maxSpeed(){
        ArrayList<Integer> speedLimits = new ArrayList<Integer>();
        int max = 0;
        for(Ruta value : rutas.values()){
            speedLimits.add(value.getLimiteVelocidad());
        }
        max = Collections.max(speedLimits);
        return max;
    }

    /*
     * Metodo que verifica si una interseccion es de tipo hermit y si ya tiene ruta
     * @param String interseccionA, interseccion A.
     * @param String interseccionB, interseccion B.
     */
    private boolean ifIsHermit(String interseccionA, String interseccionB){
        Interseccion intersectionA = intersecciones.get(interseccionA);
        Interseccion intersectionB = intersecciones.get(interseccionB);
        boolean bandera = true;
        if(intersectionA.getType().equals("hermit") && rutas.size() >= 1){
            bandera = false;
        }
        else if(intersectionB.getType().equals("hermit") && rutas.size() >= 1){
            bandera = false;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si una interseccion es de tipo needy y si no tiene rutas
     * @param String interseccionA, interseccion A.
     * @param String interseccionB, interseccion B.
     */
    private void ifIsNeedy(String interseccionA,String interseccionB){
        Interseccion intersectionA = intersecciones.get(interseccionA);
        Interseccion intersectionB = intersecciones.get(interseccionB);
        if(intersectionA.getType().equals("needy") && intersectionA.getSize() == 0){
            intersectionA.makeInvisible();
            intersecciones.remove(interseccionA);
        }
        if(intersectionB.getType().equals("needy") && intersectionB.getSize() == 0){
            intersectionB.makeInvisible();
            intersecciones.remove(interseccionB);
        }
    }

    /*
     * Metodo que verifica si una ruta es de tipo rebel
     * @param String interseccionA, interseccion A.
     * @param String interseccionB, interseccion B.
     */
    private boolean ifIsRebel(String interseccionA,String interseccionB){
        boolean bandera = false; 
        String claveRuta = obtenerKeyRuta(interseccionA,interseccionB);
        Ruta ruta = rutas.get(claveRuta);
        if(ruta.getType().equals("rebel")){
            bandera =true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si una ruta es de tipo fixed
     * @param String interseccionA, interseccion A.
     * @param String interseccionB, interseccion B.
     */
    private boolean ifIsFixed(String interseccionA,String interseccionB){
        boolean bandera = false; 
        String claveRuta = obtenerKeyRuta(interseccionA,interseccionB);
        Ruta ruta = rutas.get(claveRuta);
        if(ruta != null && ruta.getType().equals("fixed")){
            bandera =true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si una interseccion tiene alguna ruta de tipo fixed
     * @param String interseccionA, interseccion A.
     * @param String interseccionB, interseccion B.
     */
    private boolean ifIsFixedInterseccion(String color){
        boolean bandera = false; 
        ArrayList<String> routes = verificarKeyRuta(color);
        System.out.println(routes);
        for(Ruta ruta: rutas.values()){
            if(ruta.getType().equals("fixed")){
                bandera = true;
            }
        }
        return bandera;
    }

    /*
     * Metodo que verifica si una sennal es tipo twin y annade
     * @param String interseccionA, interseccion A.
     * @param String interseccionB, interseccion B.
     */
    private void ifIsTwinAdd(String intersectionA,String intersectionB,int SpeedLimit){
        String claveI = inversaKeyRuta(intersectionA,intersectionB);
        String clave = obtenerKeyRuta(intersectionA,intersectionB);
        sennales.put(clave,new Twin(intersecciones.get(intersectionA),intersecciones.get(intersectionB),SpeedLimit,1));
        sennales.put(claveI,new Twin(intersecciones.get(intersectionA),intersecciones.get(intersectionB),SpeedLimit,2));
        Sennal sennalTwin = sennales.get(claveI);
        if (this.isVisible) {
            sennalTwin.makeVisible();
        }
    }

    /*
     * Metodo que verifica si una sennal es tipo twiny la elimina
     * @param String interseccionA, interseccion A.
     * @param String interseccionB, interseccion B.
     */
    private void ifIsTwinDelete(String intersectionA,String intersectionB){
        String claveI = inversaKeyRuta(intersectionA,intersectionB);
        String claveSennal = obtenerKeyRuta(intersectionA,intersectionB);
        if(sennales.containsKey(claveI)){
            Sennal sennal = sennales.get(claveI);
            sennal.makeInvisible();
            sennales.remove(claveI);
            // Borrar la senal asociada a la ruta
            sennal = null;
            Ruta ruta = rutas.get(claveSennal);
            ruta.setSennal(sennal);
        }
    }

    /*
     * Metodo que verifica si el tipo de interseccion existe;
     * @param String type, tipo de la interseccion.
     */
    private boolean typeIntersectionExist(String type){
        boolean bandera = false;
        if(type == "normal" || type == "needy" || type == "hermit"){
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si el tipo de ruta existe.
     * @param String type, tipo de la ruta.
     */
    private boolean typeRouteExist(String type){
        boolean bandera = false;
        if(type == "normal" || type == "fixed" || type == "rebel"){
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si el tipo de sennal existe.
     * @param String type, tipo de la sennal.
     */
    private boolean typeSennalExist(String type){
        boolean bandera = false;
        if(type == "normal" || type == "twin" || type == "cautious"){
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que obtiene el limite de velocidad minimo de las rutas;
     */
    private int getMinimunSpeedL(){
        int minimunSpeedL = 900000;
        for(Ruta ruta: rutas.values()){
            int speedL = ruta.getLimiteVelocidad();
            if(speedL < minimunSpeedL){
                minimunSpeedL = speedL;
            }
        }
        return minimunSpeedL;
    }

    /*
     * Metodo que verifca que una interseccion este dentro del canvas.
     * @param int x, posicion x  de la interseccion.
     * @param int y, posicion y  de la interseccion. 
     */
    private boolean verificaCanvas(int x,int y){
        boolean bandera = false;
        if(x > 35 && x < length && y > 35 && y < width ){
            bandera = true;
        }
        return bandera;
    }
}
//hacer una lista que guarde en orden los colores y segun eso tomar la posicion y enviarla en modo
//string para el canvas :D