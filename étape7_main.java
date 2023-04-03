import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {     //on va pouvoir décompresser un fichier compressé à l'aide d'un codage de Huffman
  public static void main(String args[]) {

    System.out.println("\n----BIENVENUE DANS LE DECOMPRESSEUR DE FICHIER ----\n");  //on affiche un message de bienvenue
    Scanner myObj = new Scanner(System.in); // Create a Scanner object
    System.out.println("Quel texte voulez-vous décompresser ? ");     //on demande à l'utilisateur d'entrer le nom du fichier à décompresser à l'aide d'un objet Scanner
    String doc = myObj.nextLine();      //L'entrée est stockée dans une variable "doc"

    Codage c = new Codage(new BinaryTree(doc + "_freq.txt"));       //Un objet "Codage" est créé en utilisant un objet "BinaryTree" qui est initialisé avec le fichier de fréquence du texte compressé
                                                                    //Le fichier de fréquence est généré par le programme qui a compressé le texte et est utilisé pour construire l'arbre de Huffman

    BinaryFile bf = new BinaryFile(doc + "_comp.bin");      //Un objet "BinaryFile" est créé en utilisant le nom du fichier compressé et L'objet "BinaryFile" est utilisé pour lire le fichier compressé binaire

    HashMap<String, String> dictioCod = c.dictCodage(c.arbre.getRoot(), "");    //Un dictionnaire de codage est créé en appelant la méthode "dictCodage" de l'objet "Codage"

                                                                                //Cette méthode prend en entrée la racine de l'arbre de Huffman et une chaîne vide
                                                                                
    String bin = bf.traduct2();     //La méthode "traduct2" de l'objet "BinaryFile" est appelée pour convertir le fichier binaire en chaîne de caractères

    try {
      FileWriter myWriter = new FileWriter(doc + ".txt");       //on crée un fichier texte de sortie en utilisant un objet "FileWriter" et Les caractères décompressés sont écrits dans ce fichier
      String a = "";
      for (int i = 0; i < bin.length(); i++) {      //boucle "for" pour parcourir la chaîne de caractères résultante
        a += bin.charAt(i);                         //Pour chaque bit on ajoute le bit à une chaîne temporaire "a"
        for (String j : dictioCod.keySet()) {
          if (a.equals(dictioCod.get(j)) == true) {     //on vérifie ensuite si "a" correspond à une clé dans le dictionnaire de codage
            myWriter.write(j);
            a = "";
          }         //Si c'est le cas, le caractère correspondant est écrit dans un fichier de sortie, et la chaîne temporaire "a" est réinitialisée à la chaîne vide
        }
      }
      myWriter.close();
    } catch (IOException e) {
      System.out.println("Une erreur est survenue");
      e.printStackTrace(); 
    }

    Ratio r = new Ratio("exemple_freq.txt");        //on calcule le taux de compression et le nombre moyen de bits de stockage d'un caractère en appelant les méthodes de l'objet "Ratio"
    System.out.println("\nTaux de compression atteint: " + r.compression_ratio());
    System.out.println("Nombre moyen de bits de stockage d’un caractère: " + r.nb_bits_moyen() + "\n");

    System.out.println("Décompression terminée\n"); //on affiche un message de fin de décompression
  }

}