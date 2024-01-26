import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneraDirectorioController {

    public void generaDirectorioController(String nombreEntidad, String directorioControlador, String entidadMayusculaInicial, String nombreDeAplicacion, int tipoId) {
        File directorioController = new File(directorioControlador);
            if (directorioController.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/controller creado satisfactoriamente.");

                try (FileWriter fw = new FileWriter(
                        directorioControlador + "/" + entidadMayusculaInicial + "Controller.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".controller;");
                    out.println("");
                    out.println("import org.springframework.web.bind.annotation.CrossOrigin;");
                    out.println("import org.springframework.web.bind.annotation.RequestMapping;");
                    out.println("import org.springframework.web.bind.annotation.RestController;");
                    out.println("");
                    if (0 == tipoId) {
                        out.println("import " + nombreDeAplicacion + ".bases.controllers.BaseControllerIdLongImpl;");
                    }

                    else {
                        out.println("import " + nombreDeAplicacion + ".bases.controllers.BaseControllerIdStringImpl;");

                    }

                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + entidadMayusculaInicial
                                    + ";");
                    out.println("import " + nombreDeAplicacion + "." + nombreEntidad + ".service.impl."
                            + entidadMayusculaInicial + "ServiceImpl;");
                    out.println("");
                    out.println("@RestController");
                    out.println("@CrossOrigin(origins = \"*\")");
                    out.println("@RequestMapping(path = \"api/v1/" + nombreEntidad + "\")");
                    if (0 == tipoId) {
                        out.println(
                                "public class " + entidadMayusculaInicial
                                        + "Controller extends BaseControllerIdLongImpl<"
                                        + entidadMayusculaInicial + "," + entidadMayusculaInicial + "ServiceImpl> {");
                    } else {
                        out.println(
                                "public class " + entidadMayusculaInicial
                                        + "Controller extends BaseControllerIdStringImpl<"
                                        + entidadMayusculaInicial + "," + entidadMayusculaInicial + "ServiceImpl> {");

                    }
                    out.println("");
                    out.println("");
                    out.println("}");

                    if (0 == tipoId) {
                        System.out.println("      Archivo " + entidadMayusculaInicial
                                + "Controller.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + entidadMayusculaInicial
                                + "Controller.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }
        }


       
}
