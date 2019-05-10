/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author c106
 */
public class Archivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) throws IOException {

        LeerArchivo m = new LeerArchivo();
        String mish = "";
        m.holi(mish);
        
        Path FROM = Paths.get("Origen\\"+m.holi(mish)+"");
        Path TO = Paths.get("Destino\\"+m.holi(mish)+"");
        //overwrite existing file, if exists
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(FROM, TO, options);
    }
}
