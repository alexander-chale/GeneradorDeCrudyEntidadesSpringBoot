import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class generadorCrud {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Scanner entidad = new Scanner(System.in);
        String nombre = null;
        String nombreEntidad = null;
        Integer tipoId = null;
        Utilitarios utilitarios = new Utilitarios();
        Scanner tipo = new Scanner(System.in);

        Boolean verdadero = true;
        while (verdadero) {
            System.out.println("Ingrese el nombre de la entidad en camelCase");
            nombre = entidad.next();

            nombreEntidad = utilitarios.validaPalabra(nombre);

            if (!nombreEntidad.isEmpty()) {
                verdadero = false;

            } else {
                verdadero = true;

            }
        }

        String entidadMayusculaInicial = utilitarios.generaMayusculaInicial(nombreEntidad);

        nombreEntidad = utilitarios.generaMinusculaInicial(nombreEntidad);

        verdadero = true;
        while (verdadero) {

            System.out.println("Ingrese 0 = Para id Long");
            System.out.println("Ingrese 1 = Para id String");
            tipoId = tipo.nextInt();

            tipoId = utilitarios.valida0o1(tipoId);

            if ((tipoId == 0) || (tipoId == 1)) {
                verdadero = false;
            } else {
                verdadero = true;
            }
        }

        Properties config = new Properties();
        InputStream configInput = null;

        configInput = new FileInputStream("config.properties");
        config.load(configInput);

        String tipoEstructura = config.getProperty("tipoEstructura");
        String nombreDeAplicacion = config.getProperty("nombreDeAplicacion");

        if ("0".equals(tipoEstructura)) {
            String directorioControlador = nombreEntidad + "/controller";
            String directorioEntidad = nombreEntidad + "/entity";
            String directorioRepositorio = nombreEntidad + "/repository";
            String directorioServicio = nombreEntidad + "/service";
            String directorioServicioImplementacion = nombreEntidad + "/service/impl";

            System.out.println("CREANDO LA ESTRUCTURA BCV DEL CRUD PARA SPRING BOOT... ");

            GeneraDirectorioPrincipal generaDirectorioPrincipal = new GeneraDirectorioPrincipal();
            generaDirectorioPrincipal.generaDirectorioPrincipal(nombreEntidad);

            GeneraDirectorioController generaDirectorioController = new GeneraDirectorioController();
            generaDirectorioController.generaDirectorioController(nombreEntidad, directorioControlador,
                    entidadMayusculaInicial, nombreDeAplicacion, tipoId);

            GeneraDirectorioEntity generaDirectorioEntity = new GeneraDirectorioEntity();
            generaDirectorioEntity.GeneraDirectorioEntity(directorioEntidad, nombreEntidad);

            GeneraDirectorioEntityDtos generaDirectorioEntityDtos = new GeneraDirectorioEntityDtos();
            generaDirectorioEntityDtos.GeneraDirectorioEntityDtos(nombreEntidad);

            GeneraDirectorioEntityMapper generaDirectorioEntityMapper = new GeneraDirectorioEntityMapper();
            generaDirectorioEntityMapper.GeneraDirectorioEntityMapper(nombreEntidad);

            GeneraDirectorioRepository generaDirectorioRepository = new GeneraDirectorioRepository();
            generaDirectorioRepository.generaDirectorioRepository(directorioRepositorio, nombreEntidad,
                    entidadMayusculaInicial, nombreDeAplicacion, tipoId);

            GeneraDirectorioService generaDirectorioService = new GeneraDirectorioService();
            generaDirectorioService.generaDirectorioService(directorioServicio, nombreEntidad, entidadMayusculaInicial,
                    nombreDeAplicacion, tipoId);

            GeneraDirectorioServiceImpl generaDirectorioServiceImpl = new GeneraDirectorioServiceImpl();
            generaDirectorioServiceImpl.generaDirectorioServiceImpl(directorioServicioImplementacion, nombreEntidad,
                    entidadMayusculaInicial, nombreDeAplicacion, tipoId);

        }
    }

}
