import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class generadorCrud {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        
          
        @SuppressWarnings("resource") 
        Scanner so = new Scanner(System.in);
        Integer sistemaOperativo = null;
        Integer tipoSo = null;
        Utilitarios utilitarios = new Utilitarios();

        Boolean isLinux = true;
        while (isLinux) {

            System.out.println("Ingrese 0 = Si es Linux");
            System.out.println("Ingrese 1 = Para Windows");
            sistemaOperativo = so.nextInt();

            tipoSo = utilitarios.valida0o1(sistemaOperativo);

            if ((tipoSo == 0) || (tipoSo == 1)) {
                isLinux = false;
            } else {
                isLinux = true;
            }
        }

        if (tipoSo == 0){
            System.out.println("Si ejecuta desde Linux, no se necesita ninguna configuración, disfrute!!!");
        } else{
            System.out.println("Si ejecuta desde Windows, debe colocar el archivo 'config.properties' en la raiz c: de windows");
        }
      
      
        @SuppressWarnings("resource") 
        Scanner entidad = new Scanner(System.in);
        String nombre = null;
        String nombreCamelcase = null;
        String nombreEntidad = null;
        Integer tipoId = null;
        
        @SuppressWarnings("resource") 
        Scanner tipo = new Scanner(System.in);

        Boolean verdadero = true;
        Boolean tablaExiste = true;
        String nombreTablaValidada = null;
        while (verdadero) {
            System.out.println("Ingrese el nombre de la tabla, tal como se encuentra creada en la base de datos");
            nombre = entidad.next();
            System.out.println("este es el nombre de la tabla escrita " + nombre);

            tablaExiste = utilitarios.validaNombreTabla(nombre);

            if (tablaExiste==true) {
               nombreCamelcase =  utilitarios.camelCase(nombre);
               //esto hay que hacerlo bien es solo para demostar
               nombreEntidad = nombreCamelcase;
               nombreTablaValidada = nombre;
               verdadero = false;
               

            } else {
                System.out.println("La tabla "+ nombre + "no existe");
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

        if (tipoSo == 0){
            configInput = new FileInputStream("resources/config.properties");
        } else{
            configInput = new FileInputStream("C:\\config.properties");
        }

        
        
        config.load(configInput);

        String tipoEstructura = config.getProperty("tipoEstructura");
        String paquete = config.getProperty("paquete");


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
                    entidadMayusculaInicial, paquete, tipoId);

            GeneraDirectorioEntity generaDirectorioEntity = new GeneraDirectorioEntity();
            generaDirectorioEntity.GeneraDirectorioEntity(directorioEntidad, nombreEntidad, entidadMayusculaInicial, tipoSo,nombreTablaValidada);

            GeneraDirectorioEntityDtos generaDirectorioEntityDtos = new GeneraDirectorioEntityDtos();
            generaDirectorioEntityDtos.GeneraDirectorioEntityDtos(nombreEntidad);

            GeneraDirectorioEntityMapper generaDirectorioEntityMapper = new GeneraDirectorioEntityMapper();
            generaDirectorioEntityMapper.GeneraDirectorioEntityMapper(nombreEntidad);

            GeneraDirectorioRepository generaDirectorioRepository = new GeneraDirectorioRepository();
            generaDirectorioRepository.generaDirectorioRepository(directorioRepositorio, nombreEntidad,
                    entidadMayusculaInicial, paquete, tipoId);

            GeneraDirectorioService generaDirectorioService = new GeneraDirectorioService();
            generaDirectorioService.generaDirectorioService(directorioServicio, nombreEntidad, entidadMayusculaInicial,
                    paquete, tipoId);

            GeneraDirectorioServiceImpl generaDirectorioServiceImpl = new GeneraDirectorioServiceImpl();
            generaDirectorioServiceImpl.generaDirectorioServiceImpl(directorioServicioImplementacion, nombreEntidad,
                    entidadMayusculaInicial, paquete, tipoId);

        }
    }

}
