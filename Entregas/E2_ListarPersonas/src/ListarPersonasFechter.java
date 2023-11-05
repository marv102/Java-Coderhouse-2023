import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListarPersonasFechter {
    public static void main(String[] args) {

        //Creamos una lista vacia
        List<Persona> listaSinOrdenar = new ArrayList<>();
        String[] listaNombres = {"Alejandro","Isabella","Diego","Valentina","Luis","Javier"};
        String[] listaApellidos = {"Martinez","Lopez","Rodríguez","Morales","Perez","Hernandez"};
        int cantPersonas=5;

        //Agregamos personas con nombres aleatorios a la lista
        for (int i=0; i<cantPersonas; i++){
            listaSinOrdenar.add(new Persona(listaNombres[(int)(Math.random()*5)],
                                          listaApellidos[(int)(Math.random()*5)])
            );
        }

        //Ordenamos la lista vacia de distintas maneras
        List<Persona> listaOrdenadaPorNombre = ListarPersonasFechter.OrdenamientoBurbuja(listaSinOrdenar,"nombre");
        List<Persona>  listaOrdenadaPorApellido = ListarPersonasFechter.OrdenamientoBurbuja(listaSinOrdenar,"apellido");
        List<Persona>  listaOrdenadaPorApellidoInverso = ListarPersonasFechter.OrdenamientoConCollector(listaSinOrdenar,"apellido");


        //Mostramos las listas
        System.out.println("-------Sin Ordenar--------|");
        for(Persona persona:listaSinOrdenar){
            System.out.println(persona.getNombre()+" "+persona.getApellido());
        }

        System.out.println("-------Alfabeticamente por Nombre--------|");
        for(Persona persona:listaOrdenadaPorNombre){
            System.out.println(persona.getNombre()+" "+persona.getApellido());
        }

        System.out.println("-------Alfabeticamente por Apellido--------|");
        for(Persona persona:listaOrdenadaPorApellido){
            System.out.println(persona.getNombre()+" "+persona.getApellido());
        }

        System.out.println("-------Alfabeticamente Inverso por Apellido--------|");
        for(Persona persona:listaOrdenadaPorApellidoInverso){
            System.out.println(persona.getNombre()+" "+persona.getApellido());
        }

    }

    //Algoritmo de Ordenamiento
    public static List<Persona> OrdenamientoBurbuja(List<Persona> lista, String criterio){
        Persona variableAuxiliar;
        boolean condicion;
        List<Persona> listaCopia = new ArrayList<Persona>(lista);

        //Itero la copia de la lista para ordenarla
        for (int i=0;i<listaCopia.size();i++){
            for (int j=0;j<listaCopia.size()-1;j++){

                    //Eligo criterio de ordenamiento
                    if(criterio=="nombre"){
                        //evaluo si nombre j+1 esta alfabeticamente antes que el nombre j
                        condicion=listaCopia.get(j).getNombre().compareTo(listaCopia.get(j+1).getNombre()) > 0;
                    }else {
                        //evaluo si apellido j+1 esta alfabeticamente antes que el apellido j
                        condicion=listaCopia.get(j).getApellido().compareTo(listaCopia.get(j+1).getApellido()) > 0;
                    }

                    //Ordeno
                    if(condicion){
                        variableAuxiliar = listaCopia.get(j);
                        listaCopia.set(j,listaCopia.get(j+1));
                        listaCopia.set(j+1,variableAuxiliar);
                    }
                }
            }
        return listaCopia;
    }

    //Algoritmo de Ordenamiento con Collector para ordenar inversamente por Apellido
    public static List<Persona> OrdenamientoConCollector(List<Persona> lista, String criterio){
        List<Persona> listaCopia = new ArrayList<Persona>(lista);
        Collections.sort(listaCopia, new Comparator<Persona>() {
            @Override
            public int compare(Persona persona1, Persona persona2) {
                return persona2.getApellido().compareTo(persona1.getApellido());
            }
        });
        return listaCopia;
    }
}