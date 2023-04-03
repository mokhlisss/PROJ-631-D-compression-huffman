public class Node {                              // on définit une classe Node pour créer les noeuds de notre arbre  
    private Integer freq;
    private String label;      //Chaque nœud de l'arbre contient un entier (freq) et une chaîne de caractères (label) qui peuvent être utilisés pour stocker des informations spécifiques au nœud
    private Node leftChild;
    private Node rightChild;   //chaque nœud a une référence à son enfant gauche et à son enfant droit, qui peuvent eux-même être des nœuds

    public Node(Integer freq, String label) {  //Le constructeur de la classe Node initialise un nœud avec une fréquence et une étiquette données
        this.freq = freq;
        this.label = label;
    }

    public Integer getFrequence() {     //La méthode publique "getFrequence" retourne la fréquence d'un nœud
        return this.freq;
    }                                   

    public String getLabel() {          //La méthode publique "getLabel" retourne l'étiquette d'un nœud

        return this.label;
    }

    public Node getLeftChild() {       //La méthode publique "getLeftChild" retourne les enfants gauche d'un nœud
        return this.leftChild;
    }                                   

    public Node getRightChild() {      //La méthode publique "getRightChild" retourne les enfants droit d'un nœud
        return this.rightChild;
    }

    public void setChildren(Node leftChild, Node rightChild) {  //la méthode publique "setChildren" prend deux nœuds en entrée et définit ces deux nœuds comme enfants gauche et droit du nœud courant
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

}                                       // on obtient ainsi notre structure de base pour la construction de notre arbre