package org.pierre.allbadanti;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BadantiFileLoader {
    public List<Badante> loadAllBadanti(String fileFullPath) {
        List<Badante> badantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileFullPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) { // Ensure that the row has exactly 4 columns
                    Badante badante = new Badante(data[0], data[1], data[2], data[3]);
                    badantes.add(badante);
                }
                if (data.length == 3) { // Ensure that the row has exactly 3 columns
                    Badante badante = new Badante(data[0], data[1], data[2], "");
                    badantes.add(badante);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return badantes;
    }

    public String generateVCard(Badante badante) {
        return "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "N:;rabadante " + badante.getFullName() + ";;;\n" +
                "FN:rabadante " + badante.getFullName() + "\n" +
                "item1.ADR;TYPE=HOME;TYPE=pref:;;" + badante.getCity() + ";;;;Italy\n" +
                "item1.X-ABADR:it\n" +
                "TEL;TYPE=CELL;TYPE=pref;TYPE=VOICE:+39 " + badante.getTelephone() + "\n" +
                "TITLE:" + badante.getCertified() + "\n" +
                "PRODID:-//Apple Inc.//iCloud Web Address Book 2422B10//EN\n" +
                "REV:2024-08-16T10:47:15Z\n" +
                "END:VCARD";
    }

}
