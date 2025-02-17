package uml.sc.potencial.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import uml.sc.potencial.controllers.CargoController;

import java.io.File;
import java.io.IOException;

public class Utileria {

    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    public static String guardarArchivo(MultipartFile multipart, String ruta){

        String nombreOriginal = multipart.getOriginalFilename();

        nombreOriginal = nombreOriginal.replace(" ", "_");
        String nombreFinal = randomAlphaNumeric(8)+  nombreOriginal;

        try {
            File imageFile = new File(ruta+nombreFinal);
//            System.out.println("Archivo: "+ imageFile.getAbsolutePath());
            multipart.transferTo(imageFile);
            return nombreFinal;
        }catch (IOException e){
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }


//    METODO PARA GENERAR UN STRING ALEATORIO
    public static String randomAlphaNumeric(int count){
        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ789456123";
        StringBuilder builder = new StringBuilder();

        while(count-- != 0){
            int character = (int) (Math.random() * CARACTERES.length());
            builder.append(CARACTERES.charAt(character));
        }
        return builder.toString();
    }
}
