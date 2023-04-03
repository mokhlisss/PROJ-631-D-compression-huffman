import java.util.HashMap;

public class Ratio {        //Cette classe Ratio implémente plusieurs méthodes pour calculer des ratios de compression de fichiers en utilisant des algorithmes de codage
    private String nom;

    public Ratio(String nom_fichier) {  //La méthode Ratio(String nom_fichier) est un constructeur qui prend en entrée le nom d'un fichier et initialise l'attribut nom de la classe
        this.nom = nom_fichier;
    }

    public int volume_initial() {       //La méthode volume_initial() renvoie la taille (en nombre d'octets) du fichier initial dont le nom est stocké dans l'attribut nom
        return Frequency.taille(this.nom);  //on utilise la méthode taille(String nom_fichier) de la classe Frequency pour calculer cette taille
    }

    public double volume_finale() {     //La méthode volume_finale() calcule la taille (en nombre d'octets) du fichier compressé en utilisant l'algorithme de codage de Huffman
        int nb_bit = 0;
        Codage cd = new Codage(new BinaryTree(this.nom));   //Elle utilise la classe Codage pour coder le fichier en utilisant un arbre de codage construit à partir de l'algorithme de Huffman
        HashMap<String, Integer> d1 = Frequency.dictFreq(this.nom);     //elle utilise les fréquences de chaque caractère dans le fichier initial (obtenues à partir de la méthode dictFreq(String nom_fichier) de la classe Frequency)
        HashMap<String, String> d2 = cd.dictCodage(cd.arbre.getRoot(), ""); //elle utilise aussi les codes de codage pour chaque caractère (obtenus à partir de la méthode dictCodage(Node racine, String code) de la classe Codage) pour calculer la taille du fichier compressé
        for (String caractere : d2.keySet()) {
            nb_bit += d2.get(caractere).length() * d1.get(caractere);
        }
        double nb_octet = nb_bit / 8;
        return nb_octet;
    }

    public double compression_ratio() {     //La méthode compression_ratio() calcule le ratio de compression
        double taux = 1 - (volume_finale() / volume_initial());     //le ratio est égal à la différence entre la taille du fichier initial et la taille du fichier compressé, le tout divisée par la taille du fichier initial.
        return (double) Math.round(taux * 10000d) / 10000d;     //le résultat sera arrondi à quatre décimales
    }

    public double nb_bits_moyen() {     //La méthode nb_bits_moyen() calcule le nombre moyen de bits utilisés pour coder chaque caractère du fichier initial en utilisant l'algorithme de Huffman
        double sm = 0;
        int t = Frequency.taille(this.nom);
        Codage cd = new Codage(new BinaryTree(this.nom));
        HashMap<String, String> d = cd.dictCodage(cd.arbre.getRoot(), "");
        for (String cle : d.keySet()) {
            sm += d.get(cle).length();
        }
        double bit_moyen = sm / t;
        return (double) Math.round(bit_moyen * 10000d) / 10000d;
    }       //on utilise les mêmes méthodes que volume_finale() pour construire l'arbre de codage et obtenir les fréquences et les codes de codage de chaque caractère
            //puis on calcule la moyenne des longueurs de codage pour chaque caractère et on arrondit également le résultat à quatre décimales
}