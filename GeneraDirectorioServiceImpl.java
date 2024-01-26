import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneraDirectorioServiceImpl {

    public void generaDirectorioServiceImpl(String directorioServicioImplementacion, String nombreEntidad,
            String entidadMayusculaInicial, String nombreDeAplicacion, int tipoId) {

        File directorioServiceImpl = new File(directorioServicioImplementacion);

        if (directorioServiceImpl.mkdir()) {
            System.out.println("   Directorio " + nombreEntidad + "/service/impl creado satisfactoriamente.");
            try (FileWriter fw = new FileWriter(
                    directorioServicioImplementacion + "/" + entidadMayusculaInicial + "ServiceImpl.java",
                    true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".service.impl;");
                out.println("");
                out.println("import org.springframework.beans.factory.annotation.Autowired;");
                out.println("import org.springframework.stereotype.Service;");
                out.println("");
                out.println(
                        "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + entidadMayusculaInicial
                                + ";");
                out.println("import " + nombreDeAplicacion + "." + nombreEntidad + ".repository."
                        + entidadMayusculaInicial + "Repository;");
                out.println(
                        "import " + nombreDeAplicacion + "." + nombreEntidad + ".service." + entidadMayusculaInicial
                                + "Service;");
                out.println("import " + nombreDeAplicacion + ".bases.repositories.BaseRepository;");
                out.println("import " + nombreDeAplicacion + ".bases.services.BaseServiceImpl;");
                out.println("");
                out.println("@Service");
                if (0 == tipoId) {
                    out.println("public class " + entidadMayusculaInicial + "ServiceImpl extends BaseServiceImpl<"
                            + entidadMayusculaInicial + ", Long> implements " + entidadMayusculaInicial
                            + "Service {");

                }

                else {
                    out.println("public class " + entidadMayusculaInicial + "ServiceImpl extends BaseServiceImpl<"
                            + entidadMayusculaInicial + ", String> implements " + entidadMayusculaInicial
                            + "Service {");

                }
                out.println("");
                out.println("@Autowired");
                out.println("private " + entidadMayusculaInicial + "Repository " + nombreEntidad + "Repository;");
                out.println("");
                if (0 == tipoId) {
                    out.println("public " + entidadMayusculaInicial + "ServiceImpl(BaseRepository<"
                            + entidadMayusculaInicial + ", Long> baseRepository) {");

                }

                else {
                    out.println("public " + entidadMayusculaInicial + "ServiceImpl(BaseRepository<"
                            + entidadMayusculaInicial + ", String> baseRepository) {");

                }
                out.println("     super(baseRepository);");
                out.println("}");
                out.println("");
                out.println("}");
                if (0 == tipoId) {
                    System.out.println("      Archivo " + entidadMayusculaInicial
                            + "ServiceImpl.java con Id Long creado satisfactoriamente.");
                }

                else {
                    System.out.println("      Archivo " + entidadMayusculaInicial
                            + "ServiceImpl.java con Id String creado satisfactoriamente.");

                }

            } catch (IOException e) {
                // exception handling left as an exercise for the reader
            }
        }
    }

}
