package validadorCorrelativas;

import java.time.LocalDate;

public class Inscripcion {
    private Alumno alumno;
    private Materia materia;
    private LocalDate fecha;

    public Inscripcion(Alumno alumno, Materia materia) {
        this.alumno = alumno;
        this.materia = materia;
        this.fecha = LocalDate.now();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public boolean aprobada(){
        /**
         * ¿como sabemos si la inscripcion esta aceptasa?
         * -la inscripcion esta aceptada (true) si la materiaNo tiene correlativas.
         * - La materia esta aceptada (true) si la materia tiene correltivas y el alumno cumple co todas ellas.
         */

        // TO
        return !this.materia.tenesCorrelativas() || this.materia.puedeCursar(this.alumno);
    }
    public String leyendaEstado(){
        return this.aprobada()? "Aprobada" : "Rechazada";
    }
}
