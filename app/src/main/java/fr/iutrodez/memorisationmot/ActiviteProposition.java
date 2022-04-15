/*
 * TP multiactivités : jeu mémorisation des mots
 *     => activité permettant à l'utilisateur de saisir ses propositions de mots
 * ActiviteProposition.java                    07/16
 */
package fr.iutrodez.memorisationmot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Cette classe gère l'activité dans laquelle l'utilisateur est invité à proposer
 * 5 mots. Il doit ensuite cliquer sur le bouton "soumettre" pour savoir si sa
 * réponse est correcte ou pas. Si elle est correcte un message toast s'affihe.
 * Sinon une nouvelle activité "ActivitePerdu" est créée.
 * @author Servieres
 * @version 1.0
 */
public class ActiviteProposition extends Activity {

    /** Clé pour le tableau des mots proposés par l'utilisteur */
    public static final String CLE_MOT_UTILISATEUR
            = "fr.iutrodez.memorisationmott.MOT_UTILISATEUR";

    /** Zone de saisie pour les mots proposés par l'utilisateur */
    private EditText[] tableZoneSaisie;

    /** Liste des mots corrects */
    private ArrayList<String> listeCorrecte;

    /** Liste des mots proposés par l'utilisateur */
    private ArrayList<String> reponseUtilisateur;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_proposition);

        // on accède aux zones de saisie des mots
        tableZoneSaisie = new EditText[MainActivity.NB_MOT];
        tableZoneSaisie[0] =  findViewById(R.id.saisie_mot1);
        tableZoneSaisie[1] =  findViewById(R.id.saisie_mot2);
        tableZoneSaisie[2] =  findViewById(R.id.saisie_mot3);
        tableZoneSaisie[3] =  findViewById(R.id.saisie_mot4);
        tableZoneSaisie[4] =  findViewById(R.id.saisie_mot5);

        //Récupératoin de la liste correcte transmise par l'activité parente
        Intent intentionRecu = getIntent();

        listeCorrecte = intentionRecu.getStringArrayListExtra(MainActivity.CLE_MOT_CORRECT);

    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Soumettre"
     * @param bouton  bouton sur lequel l'utilisateur a cliqué
     */
    public void clicSoumettre(View bouton) {

        // on constitue la liste des mots saisis par l'utilistateur
        reponseUtilisateur = new ArrayList<>();
        for (int indice = 0; indice < tableZoneSaisie.length; indice++) {
            reponseUtilisateur.add(tableZoneSaisie[indice].getText().toString());
        }

        /* Création d'une intention qui sera envoyée à l'activité "resultat"
         * accompagnée d'un tableau contenant les 5 mots corrects et les 5 mots
         * proposés par l'utilisateur
         */
        Intent activiteResultat =
                new Intent(ActiviteProposition.this, ActiviteResultat.class);

        activiteResultat.putExtra(MainActivity.CLE_MOT_CORRECT, listeCorrecte);
        activiteResultat.putExtra(CLE_MOT_UTILISATEUR, reponseUtilisateur);

        startActivity(activiteResultat);
    }

}
