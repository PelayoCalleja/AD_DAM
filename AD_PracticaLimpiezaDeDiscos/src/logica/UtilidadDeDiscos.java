package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UtilidadDeDiscos {

    String pathSeleccionado;

    /**
     * Constructor de la clase utilidad de discos
     */
    public UtilidadDeDiscos() {
        pathSeleccionado = ".";
    }

    /**
     *
     * @return directorio sobre el que se realizan las operaciones
     */
    public String getPathSeleccionado() {
        return pathSeleccionado;
    }

    /**
     * Modificar el valor del directorio seleccionado
     *
     * @param pathSeleccionado valor para modificar el directorio seleccionado
     */
    public void setPathSeleccionado(String pathSeleccionado) {
        this.pathSeleccionado = pathSeleccionado;
    }

    /**
     * Elimina todas las carpetas vacias en el directorio pasado por parametro
     * de forma no recursiva
     *
     * @param pathSeleccionado ruta al directorio del que se quieren eliminar
     * las carpetas vacias
     * @return numero de carpetas eliminadas
     */
    public ArrayList<String> eliminarDirectoriosVacios(String pathSeleccionado) {

        File f = new File(pathSeleccionado);
        File[] archivos = f.listFiles();

        if (archivos == null) {
            return null;
        }

        ArrayList<File> borrar = new ArrayList<>();
        ArrayList<String> archivosBorrar = new ArrayList<>();
        for (int i = 0; i < archivos.length; i++) {
            if (archivos[i].isDirectory()) {
                if (archivos[i].listFiles().length == 0) {
                    borrar.add(archivos[i]);
                    archivosBorrar.add(archivos[i].getAbsolutePath());
                }
            }
        }

        for (File fi : borrar) {
            fi.delete();
        }
        return archivosBorrar;
    }

    /**
     * Elimina todas los archivos PDF en el directorio pasado por parametro de
     *
     * @param pathSeleccionado ruta del directorio donde quieres eliminar los
     * pdfs
     * @return numero de archivos PDF eliminados
     */
    public ArrayList<String> eliminarFicherosPDF(String pathSeleccionado) {//

        File f = new File(pathSeleccionado);
        File[] archivos = f.listFiles();
        ArrayList<File> borrar = new ArrayList<>();
        ArrayList<String> archivosBorrar = new ArrayList<>();
        for (int i = 0; i < archivos.length; i++) {
            if (!archivos[i].isDirectory()) {
                String name = archivos[i].getName();
                String extension = name.substring(name.lastIndexOf("."));
                if (extension.equals(".pdf")) {//
                    borrar.add(archivos[i]);
                    archivosBorrar.add(archivos[i].getAbsolutePath());
                }
            }
        }
        archivosBorrar.size();
        for (File fi : borrar) {
            fi.delete();
        }
        return archivosBorrar;
    }

    public ArrayList<String> eliminarFicherosCualquierTipo(String pathSeleccionado, String tipo) {

        File f = new File(pathSeleccionado);
        File[] archivos = f.listFiles();
        ArrayList<File> borrar = new ArrayList<>();
        ArrayList<String> archivosBorrar = new ArrayList<>();
        for (int i = 0; i < archivos.length; i++) {
            if (!archivos[i].isDirectory()) {
                String name = archivos[i].getName();
                String extension = name.substring(name.lastIndexOf("."));
                if (extension.equals("." + tipo)) {
                    borrar.add(archivos[i]);
                    archivosBorrar.add(archivos[i].getAbsolutePath());
                }
            }
        }
        archivosBorrar.size();
        for (File fi : borrar) {
            fi.delete();
        }
        return archivosBorrar;
    }

    /**
     *
     * @param pathSeleccionado ruta del directorio donde quieres eliminar los
     * archivos grandes
     * @param tam tamanio maximo en la carpeta,los archivos superiores a este
     * tamanio seran eliminados. El valor del tamanio se introducira en bytes
     * @return elimina los ficheros grandes
     */
    public ArrayList<String> eliminarFicherosGrandes(String pathSeleccionado, int tam) {

        File f = new File(pathSeleccionado);
        File[] archivos = f.listFiles();
        ArrayList<File> borrar = new ArrayList<>();
        ArrayList<String> archivosBorrar = new ArrayList<>();
        for (int i = 0; i < archivos.length; i++) {
            if (!archivos[i].isDirectory()) {
                if (archivos[i].length() > tam) {
                    borrar.add(archivos[i]);
                    archivosBorrar.add(archivos[i].getAbsolutePath());
                }
            }
        }
        archivosBorrar.size();
        for (File fi : borrar) {
            fi.delete();
        }
        return archivosBorrar;
    }

    /**
     *
     * @param pathSeleccionado
     * @param tipo es la extension de los ficheros que queremos contar
     * @return cuenta el numero de archivos de un tipo
     */
    public ArrayList<String> contarFicherosTipo(String pathSeleccionado, String tipo) {

        File f = new File(pathSeleccionado);
        File[] archivos = f.listFiles();
        int contador = 0;
        ArrayList<String> archivosBorrar = new ArrayList<>();
        for (int i = 0; i < archivos.length; i++) {
            if (!archivos[i].isDirectory()) {
                String name = archivos[i].getName();
                String extension = name.substring(name.lastIndexOf("."));
                if (extension.equals("." + tipo)) {
                    contador++;
                    archivosBorrar.add(archivos[i].getAbsolutePath());
                }
            }
        }

        return archivosBorrar;
    }

    /**
     *
     * @param pathSeleccionado
     * @return borra directorios recursivamente
     */
    public ArrayList<String> eliminarDirectoriosRecursivo(String pathSeleccionado) {
        File f = new File(pathSeleccionado);
        File[] archivos = f.listFiles();
        if (archivos == null) {
            return null;
        }
        ArrayList<File> borrar = new ArrayList<>();
        ArrayList<String> archivosBorrar = new ArrayList<>();
        for (int i = 0; i < archivos.length; i++) {
            borrar.add(archivos[i]);
            archivosBorrar.add(archivos[i].getAbsolutePath());
        }
        for (File fi : borrar) {
            try {
                borrarRecursivo(fi);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return archivosBorrar;
    }

    /**
     * Borrar Recursivo
     * @param file
     * @throws IOException
     */
    public void borrarRecursivo(File file) throws IOException {
        if (file.isDirectory()) {
            if (file.listFiles().length == 0) {
                file.delete();
            } else {
                for (File f : file.listFiles()) {
                    borrarRecursivo(f);
                }
            }
        } else {
            file.delete();
        }
    }

}
