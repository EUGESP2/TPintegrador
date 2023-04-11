package org.example;

import validadorCorrelativas.Alumno;
import validadorCorrelativas.Inscripcion;
import validadorCorrelativas.Materia;
import validadorCorrelativas.exceptions.AlumnoNoExisteException;
import validadorCorrelativas.exceptions.MateriaNoExisteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        List<Materia> materias = materias();
        List<Alumno> alumnos = alumnos();
        String ruta = "C:\\Users\\eugen\\Desktop\\curso java\\TPintegrador\\inscripciones.csv";
        for (String unaLineaDeInscripcion : Files.readAllLines(Paths.get(ruta))){
            String[] datos = unaLineaDeInscripcion.split(";");
            Inscripcion inscripcion = generarInscripcioinAPartirDeDatos(datos,alumnos,materias);
            mostrarPorPantallaEstadoDeInscripcion(inscripcion);
        }

    }
    private static void mostrarPorPantallaEstadoDeInscripcion(Inscripcion inscripcion){
        System.out.println("La inscripcion del alumno "+ inscripcion.getAlumno() + " a la materia " + inscripcion.getMateria() + " fue " + inscripcion.leyendaEstado());

    }
    private static List<Materia> materias(){
        List<Materia> materias = new ArrayList<>();
        Materia programacionI = new Materia("Programación I");
        programacionI.setId("1");
        Materia programacionII = new Materia("Programación II");
        programacionII.setId("2");

        programacionII.agregarCorrelativa(programacionI);

        materias.add(programacionI);
        materias.add(programacionII);
        return materias;
    }
    private static Inscripcion generarInscripcioinAPartirDeDatos( String[] datos, List<Alumno>alumnos, List<Materia>materias) {
        Alumno alumno = buscarAlumnoPorLegajo(datos[1],alumnos );
        Materia materia = buscarMateriaPorId(datos[3], materias);

        Inscripcion inscripcion = new Inscripcion(alumno,materia);
        return inscripcion;
    }
    private static Materia buscarMateriaPorId(String id, List<Materia>materias){
        Optional<Materia>supuestaMateria = materias.stream().filter(m -> m.getId().equals(id)).findFirst();

        if (!supuestaMateria.isPresent()){
            //lanzo una excepcion y digo q el legajo no existe
            throw new MateriaNoExisteException();
        }
        Materia materia = supuestaMateria.get();
        return materia;
    }

    private static Alumno buscarAlumnoPorLegajo(String legajo, List<Alumno> alumnos){

            Optional<Alumno>supuestoAlumno = alumnos.stream().filter(a -> a.getLegajo().equals(legajo)).findFirst();

            if (!supuestoAlumno.isPresent()){
                //lanzo una excepcion y digo q el legajo no existe
                throw  new AlumnoNoExisteException();
            }
            Alumno alumno = supuestoAlumno.get();
            return alumno;
        }


    private  static  List<Alumno> alumnos(){
        List<Alumno> alumnos = new ArrayList<>();
        Alumno juan = new Alumno("Juan","F1652");
        Alumno belen = new Alumno("Belen","F1653");
        alumnos.add(juan);
        alumnos.add(belen);

        return alumnos;
    }
}
