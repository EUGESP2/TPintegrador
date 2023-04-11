package validadorCorrelativasTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validadorCorrelativas.Alumno;
import validadorCorrelativas.Inscripcion;
import validadorCorrelativas.Materia;

public class InscripcionTest

{
    private Alumno juan;
    private  Materia programacionI;
    private  Materia programacionII;
    @BeforeEach
    public void init(){

        this.juan = new Alumno("Juan","162025");
        this.programacionI = new Materia("ProgramacionI");
        this.programacionII = new Materia("ProgramacionII");

        this.programacionII.agregarCorrelativa(programacionI);

    }
    @Test
    public void juanSePuedeAnotarAProgramacionI(){

        Inscripcion inscripcionDeJuanAPrograI = new Inscripcion(this.juan,this.programacionI);
        Assertions.assertTrue(inscripcionDeJuanAPrograI.aprobada());
    }

    @Test
    public void juanNoSePuedeAnotarAProgramacionII(){

        Inscripcion inscripcionDeJuanAPrograII = new Inscripcion(this.juan,this.programacionII);
        Assertions.assertFalse(inscripcionDeJuanAPrograII.aprobada());
    }
    @Test
    public void juanSePuedeAnotarAProgramacionII(){


       this.juan.agregarMateriaAprobada(this.programacionI);
        Inscripcion inscripcionDeJuanAPrograII = new Inscripcion(this.juan,this.programacionII);
        Assertions.assertTrue(inscripcionDeJuanAPrograII.aprobada());
    }
}
