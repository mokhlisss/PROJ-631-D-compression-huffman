import java.io.*;

// On définit ici une classe BinaryFile qui va nous permettre de traduire un fichier binaire en une chaîne de caractères binaire. 
//Cette classe contient 2 méthodes traduct1() et traduct2() qui effectuent cette tâche de 2 manières différentes.

public class BinaryFile {
  private String nom_fichier_bin;

  public BinaryFile(String nom_fichier_bin) {
    this.nom_fichier_bin = nom_fichier_bin;
  }

  public String traduct1() {
    String bin = "";
    try {
      // create a reader
      FileInputStream fis = new FileInputStream(new File(this.nom_fichier_bin));
      BufferedInputStream reader = new BufferedInputStream(fis);

      //La méthode traduct1() lit chaque byte du fichier binaire 
      //et convertit sa valeur en binaire à l'aide de la méthode Integer.toBinaryString().
      int ch;
      int test = 0;
      // read one byte at a time
      while ((ch = reader.read()) != -1) {
        String cha = Integer.toBinaryString(ch);
        test += 1;
        if (test == 1) {
          bin += cha; //Elle ajoute ensuite le résultat à une chaîne de caractères bin pour produire la traduction binaire finale du fichier
        }
        String cha2 = String.format("%8s", cha).replace(" ", "0");
        bin += cha2;

        // la méthode String.format() nous ^permet d'ajouter des zéros devant les octets binaires qui ne sont pas complets (8 bits)

      }

      // close the reader
      reader.close();

    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return bin;
  }

  public String traduct2() {   //Cette méthode peut être utilisée pour traduire un fichier texte en binaire
    String bin = "";
    File doc = new File(this.nom_fichier_bin);
    try {
      BufferedReader obj = new BufferedReader(new FileReader(doc));
      //cette méthode utilise un objet BufferedReader pour lire le fichier binaire ligne par ligne
      String line;
      while ((line = obj.readLine()) != null) {
        for (int i = 0; i < line.length(); i++) {
          bin += String.valueOf(line.charAt(i));
        }
        //Elle convertit ensuite chaque caractère de chaque ligne en sa représentation binaire et l'ajoute à la chaîne de caractères bin
      }
      obj.close();
    } catch (IOException e) { // catch permet de gérer les exceptions 
      e.printStackTrace();
    }
    return bin;
  }
}