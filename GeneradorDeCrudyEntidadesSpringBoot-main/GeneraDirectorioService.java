import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneraDirectorioService {

    public void generaDirectorioService(String directorioServicio, String nombreEntidad, String entidadMayusculaInicial,
            String nombreDeAplicacion, int tipoId) {

        File directorioService = new File(directorioServicio);

        if (directorioService.mkdir()) {
            System.out.println("   Directorio " + nombreEntidad + "/service creado satisfactoriamente.");
            try (FileWriter fw = new FileWriter(directorioServicio + "/" + entidadMayusculaInicial + "Service.java",
                    true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".service;");
                out.println("");

                out.println(
                        "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + entidadMayusculaInicial
                                + ";");
                out.println("import com.bcv.cusg.bases.services.BaseService;");
                out.println("");
                if (0 == tipoId) {
                    out.println("public interface " + entidadMayusculaInicial + "Service extends BaseService<"
                            + entidadMayusculaInicial + ", Long> {");
                }

                else {
                    out.println("public interface " + entidadMayusculaInicial + "Service extends BaseService<"
                            + entidadMayusculaInicial + ", String> {");

                }

                out.println("");
                out.println("");
                out.println("}");
                if (0 == tipoId) {
                    System.out.println("      Archivo " + entidadMayusculaInicial
                            + "Service.java con Id Long creado satisfactoriamente.");
                }

                else {
                    System.out.println("      Archivo " + entidadMayusculaInicial
                            + "Service.java con Id String creado satisfactoriamente.");

                }

            } catch (IOException e) {
                // exception handling left as an exercise for the reader
            }
        }
    }

}
