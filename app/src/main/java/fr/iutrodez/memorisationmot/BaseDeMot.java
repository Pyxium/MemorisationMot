/*
 * Classe outil pour gérer une liste de mots dans laquelle un tirage aléatoire sera fait
 * BaseDeMot.java
 */
package memorisationmot.android.exercice.multiactivite.com.memorisationmot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Classe qui gère une base de mot. Elle permet de faire un tirage aléatoire de
 * n mots parmi une liste fixe. Puis de comparer une liste de mots à une autre liste
 * (celle résultat du tirage aléatoire par exemple et celle proposée par un joueur par
 * exemple).
 *
 * @author Servières
 * @version 1.0
 */
public class BaseDeMot {

    /**  Tableau par défaut pour initialiser la liste de mots */
    private static final String[] MOTS_DEFAUT = {
            "CHATEAU", "MAISON",
            "VOITURE", "VELO", "AVION", "TRAIN",
            "ORANGE","POMME", "KIWI",
            "IMPRIMANTE", "SOURIS", "CLAVIER",
            "PIANO", "VIOLON", "TABLEAU", "PORTRAIT",
            "STYLO", "LIVRE", "OUVRAGE",
            "JAVA", "ANDROID",
            "FAUTEUIL", "CHAISE", "TABLE", "BUREAU",
            "JARDIN", "SAPIN", "NUAGE", "SOLEIL", "MONTAGNE",
            "PROMENADE", "MER", "VACANCES", "SABLE", "PLAGE",
            "RESTAURANT", "CAFE", "VOYAGE"};


    /** Nombre de mots tirés par défaut */
    private static final int NB_MOT_TIRE_DEFAUT = 5;

    /** Liste des mots de la base de mots */
    private ArrayList<String> baseDeMot;

    /** Constructeur qui initialise la base de mots avec la liste par défaut */
    public BaseDeMot() {
        baseDeMot = new ArrayList<>( Arrays.asList(MOTS_DEFAUT));
    }


    /**
     * Constructeur qui initialise la la base de mots avec les mots donnés
     * en argument
     * @param tableMot  tableau contenant les mots initiaux
     */
    public BaseDeMot(String[] tableMot) {
        baseDeMot = new ArrayList<>(Arrays.asList(tableMot));
    }


    /**
     * Détermine le nombre maximum de mots qui pourront être tirés au sort
     * @return le nombre maximum de mots qui pourront être choisis aléatoirement
     */
    public int nbMaxMotTirable() {
        return baseDeMot.size() / 2;
    }


    /**
     * Tirage aléatoires de mots dans la base de mots
     * @param nbMot  nombre de mots à tirer aléatoirement
     *               ce nombre doit être >= 1 et <= nbMaxMotTirable
     *               sinon, il sera réinitialisé à NB_MOT_TIRE_DEFAUT
     * @return une ArrayList contenant les mots choisis aléatoirement
     */
    public ArrayList<String> tirageAleatoire(int nbMot) {

        // on vérifie la validité de nbMot, on le modifie si besoin
        if (nbMot <= 0 || nbMot > nbMaxMotTirable()) {
            nbMot = NB_MOT_TIRE_DEFAUT;
        };

        // on mélange aléatoirement la base de mots
        Collections.shuffle(baseDeMot);

        // on insère les nbMot premiers mots de la base dans la liste résultat
        ArrayList<String> resultat = new ArrayList<>(nbMot);
        for(int i = 1; i <= nbMot; i++) {
            resultat.add(baseDeMot.get(i));
        }
        return resultat;
    }


    /**
     * Tirage aléatoires de mots dans la base de mots
     * Le nombre de mots choisis est égal à NB_MOT_TIRE_DEFAUT
     * @return une ArrayList contenant les mots choisis aléatoirement
     */
    public ArrayList<String> tirageAleatoire() {
        return tirageAleatoire(NB_MOT_TIRE_DEFAUT);
    }


    /**
     * Détermine si 2 listes contiennent les mêmes chaînes de caractères
     * L'ordre des éléments est indifférent
     * @param liste1    première liste à comparer
     * @param liste2    deuxième liste à comparer
     * @return un booléen égal à vrai ssi les 2 listes contiennent les mêmes valeurs
     */
    public static boolean identique(ArrayList<String> liste1,
                                    ArrayList<String> liste2) {
        if (liste1.size() != liste2.size()) {
            return false;
        }

        // mise en majucules des mots des 2 listes
        mettreEnMajuscule(liste1);
        mettreEnMajuscule(liste2);

        // parcours de liste1 pour vérifier que tous ses éléments sont dans liste2
        // et inversement
        int i;
        for (i = 0; i < liste1.size() && liste2.contains(liste1.get(i)); i++) ;
        if (i != liste1.size()) {
            return false;
        } else {
            for (i = 0; i < liste2.size() && liste1.contains(liste2.get(i)); i++) ;
            return i == liste2.size();
        }
    }


    /**
     * Détermine combien d'éléments en commun possèdent 2 listes
     * @param liste1    première liste à comparer
     * @param liste2    deuxième liste à comparer
     * @return un entier égal au nombre de chaînes communes aux 2 listes
     */
    public static int combienEnCommun(ArrayList<String> liste1,
                                      ArrayList<String> liste2) {
         // compteur des chaînes en commun
        int compteur = 0;

        // mise en majucules des mots des 2 listes
        mettreEnMajuscule(liste1);
        mettreEnMajuscule(liste2);

        // transformation en ensemble pour supprimer les doubles
        TreeSet<String> ensemble1 = new TreeSet<>(liste1);
        TreeSet<String> ensemble2 = new TreeSet<>(liste2);
        ArrayList<String> copie1 = new ArrayList<>(ensemble1);
        ArrayList<String> copie2 = new ArrayList<>(ensemble2);

        // parcours de copie1 pour compter les chaînes communes à copie2
        int i;
        for (i = 0; i < copie1.size(); i++) {
            if (copie2.contains(copie1.get(i))) {
                compteur++;
            }
        }
        return compteur;
    }

    /**
     * Transforme les chaînes de caractères de la liste argument pour quelles soient
     * écrites en majucules
     * @param aTransformer     liste de chaînes à transformer
     */
    private static void mettreEnMajuscule(ArrayList<String> aTransformer) {
        String enMajuscule;
        for (int i = 0; i < aTransformer.size(); i++) {
            enMajuscule = aTransformer.get(i).toUpperCase();
            aTransformer.set(i, enMajuscule);
        }
    }

}
