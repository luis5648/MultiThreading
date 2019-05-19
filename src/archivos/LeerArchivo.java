
package archivos;

import java.io.File;


/**
 *
 * @author CODE.zip
 */
public class LeerArchivo {

    String nombre;

    public String[] readFile(String nombre_archivo[] ) {
        LeerArchivo m = new LeerArchivo();
        String path = "Origen";

        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();

           
                nombre_archivo[i] = files;
            }

        }
        
        return nombre_archivo;    
    }
}
