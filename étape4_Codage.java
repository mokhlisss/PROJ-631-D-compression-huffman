import java.util.HashMap;
                            //cette classe Codage  définit une méthode pour générer un dictionnaire de codes de codage binaire pour une arborescence binaire donnée 
public class Codage {
    BinaryTree arbre;    //La classe a deux propriétés: un objet BinaryTree appelé "arbre" et un objet HashMap appelé "codage"
    private HashMap<String, String> codage = new HashMap<String, String>(); //Le dictionnaire de codes est stocké dans une instance de la classe HashMap

    public Codage(BinaryTree arbre) { //La méthode de construction prend un objet BinaryTree en entrée et initialise la propriété "arbre" avec cette entrée
        this.arbre = arbre;
    }

    public HashMap<String, String> dictCodage(Node node, String code_actuel) { //La méthode "dictCodage" est la méthode clé de la classe elle prend un nœud de l'arbre et un code actuel et génère le dictionnaire de codes de codage binaire pour cet arbre
                                                                            //le code actuel est initialisé avec une chaîne vide lors du premier appel
        if (node == null) {      //Si le nœud est null, la méthode renvoie null
            return null;
        }
        if (node.getLabel() != null) {  
            this.codage.put(node.getLabel(), code_actuel);              //Si le label du nœud n'est pas nul le code actuel est ajouté au dictionnaire de codage HashMap avec la clé égale au label du nœud
        }
        this.dictCodage(node.getLeftChild(), code_actuel + "0");        //Ensuite, la méthode est appelée de manière récursive pour les enfants gauche et droit du nœud 
        this.dictCodage(node.getRightChild(), code_actuel + "1");       //avec le code actuel mis à jour en ajoutant "0" pour l'enfant gauche et "1" pour l'enfant droit
        return this.codage;                                         //on retourne le dictionnaire de codage à la fin et il sera stocké dans un objet HashMap
    }
}