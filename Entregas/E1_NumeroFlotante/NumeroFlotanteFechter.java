public class NumeroFlotanteFechter {
    public static void main(String[] args) {

        float nroFlotante = -10.5f;

        //Obtener parte entera del nro flotante
        int parteEntera = (int) nroFlotante;

        //Obtener parte decimal del nro flotante
        float parteDecimal = nroFlotante - parteEntera ;

        System.out.println("El numero flotante es: " + nroFlotante);
        System.out.println("Su parte entera es: " + parteEntera);
        System.out.println("Su parte decimal es: " + parteDecimal);
    }
}
