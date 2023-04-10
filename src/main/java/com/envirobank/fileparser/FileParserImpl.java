package com.envirobank.fileparser;

import com.envirobank.dao.AccountProfileRepository;
import com.envirobank.fileparser.FileParser;
import com.envirobank.models.AccountProfile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;

public class FileParserImpl implements FileParser {

    @Autowired
    private AccountProfileRepository repo;          //database access object 

    @Override
    public void parseCSV(File csvFile) {
        try {

            List<String> lines //converting each line in file to a string in a list
                    = Files.readAllLines(csvFile.toPath());

            lines.remove(0);                        //removing column names

            for (String line : lines) {

                String[] words = line.split(",");
                String name = words[0];
                String surname = words[1];
                String imageFormat = words[2];
                String imageData = words[3];

                File imageFile = convertCSVDataToImage(imageData);          //converting base64 to image file
                URI imageLink = createImageLink(imageFile);                 //converting image file to link(location)

                AccountProfile accountProfile = new AccountProfile();       //creating AccountProfile object from each line in csv file
                accountProfile.setAccountHolderName(name);
                accountProfile.setAccountHolderSurname(surname);
                accountProfile.setHttpImageLink(imageLink.toString());

                repo.save(accountProfile);          //saving to database
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {             //method to convert base64 to image file
        int fileNumber = (int) (Math.random() * 1000) + 1;                  //generating random number to name file(so each time method runs a duplicate file isnt created)
        File outputfile = new File("C:\\Users\\nabka\\Documents\\EnviroImages\\image" + fileNumber + ".jpeg");
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64ImageData);    //decoding base64 to byte array
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIO.write(img, "jpeg", outputfile);                         //writing image to output file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputfile;
    }

    @Override
    public URI createImageLink(File fileImage) {
        return fileImage.toURI();

    }

//    public static void main(String[] args) {
//        FileParser fileParser = new FileParserImpl();
//        File file = new File("C:\\Users\\nabka\\Documents\\EnviroImages\\1672815113084-GraduateDev_AssessmentCsv_Ref003.csv");
//        fileParser.parseCSV(file);
//    }
}
