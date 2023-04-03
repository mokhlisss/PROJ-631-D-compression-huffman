import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class BinaryTree {    //classe BinaryTree qui crée un arbre binaire à partir d'un fichier donné

    private String nom_fichier;
    private Node root;   //on définit les variables membres nom_fichier et root qui représentent respectivement le nom du fichier d'entrée et la racine de l'arbre binaire

    public BinaryTree(String nom_fichier) {  //Le constructeur BinaryTree prend en entrée le nom du fichier et initialise la variable node_list comme une liste vide d'objets Node
        this.nom_fichier = nom_fichier;
        ArrayList<Node> node_list = new ArrayList<Node>();
        HashMap<String, Integer> f = Frequency.dictFreq(this.nom_fichier);    //la méthode dictFreq de la classe Frequency est appelée pour créer un dictionnaire de fréquences pour chaque mot dans le fichier
        //Les mots et leurs fréquences sont stockés dans la variable f

        for (String i : f.keySet()) {    //boucle pour ajouter chaque mot et sa fréquence à la liste node_list sous forme d'un objet Node
            node_list.add(new Node(f.get(i), i));  //Chaque objet Node est créé en utilisant le constructeur qui prend en entrée la fréquence et le nom du mot
        }

        while (node_list.size() > 1) {   //boucle pour créer l'arbre binaire  La boucle se poursuit tant que la liste node_list contient plus d'un élément
            Comparator<Node> compFreq = Comparator.comparing(Node::getFrequence); //À chaque tour de boucle, la liste est triée par ordre croissant de fréquence en utilisant un comparateur défini avec la méthode Comparator.comparing(Node::getFrequence)
            Collections.sort(node_list, compFreq);

            Node left_node = node_list.remove(0);
            Node right_node = node_list.remove(0);
            Node parent_node = new Node(left_node.getFrequence() + right_node.getFrequence(), null); //Les deux premiers nœuds de la liste triée sont retirés et utilisés pour créer un nouveau nœud parent
            parent_node.setChildren(left_node, right_node);
            node_list.add(parent_node); //Ce nœud parent est ensuite ajouté à la liste node_list

        }
        this.root = node_list.get(0);  //racine de l'arbre binaire est définie comme le seul élément restant dans la liste node_list
    }

    public Node getRoot() {    //La méthode getRoot retourne la racine de l'arbre binaire
        return this.root;
    }
}