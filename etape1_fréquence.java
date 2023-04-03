import java.io.*;
import java.util.HashMap;

//on crée une classe nommée "Frequency" qui contient deux méthodes statiques pour traiter un fichier texte

public class Frequency {

    public static HashMap<String, Integer> dictFreq(String nom) {  //Cette méthode prend un nom de fichier en entrée 
        File doc = new File(nom);
        HashMap<String, Integer> frequ = new HashMap<String, Integer>();
        try {
            BufferedReader obj = new BufferedReader(new FileReader(doc)); //La méthode lit le contenu du fichier ligne par ligne en utilisant un objet BufferedReader et vérifie si chaque ligne contient un espace

            String line;
            while ((line = obj.readLine()) != null) { 
                if (line.contains(" ") == true) {
                    Character cara = line.charAt(0);   //Si oui, elle extrait le premier caractère de la ligne et le stocke dans une variable de type Character
                    String number = "";
                    for (int i = 2; i < line.length(); i++) {       //Elle parcourt ensuite la ligne à partir de l'indice 2 pour extraire une chaîne de caractères qui représente le nombre d'occurrences du caractère 
                        number += String.valueOf(line.charAt(i));   //et elle stocke ce nombre dans une variable de type String
                    }
                    frequ.put(String.valueOf(cara) , Integer.valueOf(number)); //Elle insère ensuite une entrée dans la map en utilisant le caractère en tant que clé et le nombre d'occurrences en tant que valeur
                }
            }
            obj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequ;  // on retourne une map qui associe chaque caractère du fichier à son nombre d'occurrences
    }                  //La méthode utilise le type générique HashMap<String, Integer> pour stocker la fréquence des caractères

    public static int taille(String nom) {  //Cette méthode prend un nom de fichier en entrée
        String t = "";
        try { //pour gérer les exceptions *
            BufferedReader first_line = new BufferedReader(new FileReader(nom));  //on lit la première ligne du fichier en utilisant un objet BufferedReader
            t = first_line.readLine(); //on stocke cette ligne dans une variable de type String
            first_line.close();
        } catch (IOException e) { //idem que try gérer les exceptions *
            e.printStackTrace();
        }
        return Integer.valueOf(t);  //puis on convertit cette variable en un entier en utilisant la méthode Integer.valueOf()
        //on retourne un entier représentant la taille du fichier
    }

}


//*Le code utilise des exceptions pour gérer les erreurs de lecture de fichier. 
//Les exceptions sont traitées en utilisant une clause try-catch, 
//et une trace de la pile est affichée en cas d'erreur en appelant la méthode printStackTrace() sur l'objet d'exception.