import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneraDirectorioRepository {

    public void generaDirectorioRepository(String directorioRepositorio, String nombreEntidad,
            String entidadMayusculaInicial, String nombreDeAplicacion, int tipoId) {
        File directorioRepository = new File(directorioRepositorio);
        if (directorioRepository.mkdir()) {
            System.out.println("   Directorio " + nombreEntidad + "/repository creado satisfactoriamente.");
            try (FileWriter fw = new FileWriter(
                    directorioRepositorio + "/" + entidadMayusculaInicial + "Repository.java",
                    true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".repository;");
                out.println("");
                out.println("import org.springframework.stereotype.Repository;");
                out.println("import com.bcv.cusg." + nombreEntidad + ".entity." + entidadMayusculaInicial + ";");
                out.println("import com.bcv.cusg.bases.repositories.BaseRepository;");
                out.println("");
                out.println("@Repository");
                if (0 == tipoId) {
                    out.println("public interface " + entidadMayusculaInicial + "Repository extends BaseRepository<"
                            + entidadMayusculaInicial + ", Long> {");

                }

                else {
                    out.println("public interface " + entidadMayusculaInicial + "Repository extends BaseRepository<"
                            + entidadMayusculaInicial + ", String> {");

                }
                out.println("");
                out.println("");
                out.println("}");
                if (0 == tipoId) {
                    System.out.println("      Archivo " + entidadMayusculaInicial
                            + "Repository.java con Id Long creado satisfactoriamente.");
                }

                else {
                    System.out.println("      Archivo " + entidadMayusculaInicial
                            + "Repository.java con Id String creado satisfactoriamente.");

                }

            } catch (IOException e) {
                // exception handling left as an exercise for the reader
            }
        }

    }

}
