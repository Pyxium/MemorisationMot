/*
 * TP multiactivités : jeu mémorisation des mots
 *     => activité permettant à l'utilisateur de savoir qu'il a perdu
 * ActivitePerdu.java                    07/16
 */
package fr.iutrodez.memorisationmot;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static fr.iutrodez.memorisationmot.BaseDeMot.combienEnCommun;
import static fr.iutrodez.memorisationmot.BaseDeMot.identique;

/**
 * Cette classe gère l'activité qui informe l'utilisateur qu'il a perdu.
 * Elle affiche la liste des mots proposés par l'utilisateur ainsi que la liste
 * des mots corrects.
 * @author Servières
 * @version 1.0
 */
public class ActiviteResultat extends Activity {
    /** Etiquette du titre de l'activité */
    private TextView etiqTitreActivite;

    /** Zone de texte pour afficher les mots proposés par l'utilisateur */
    private TextView[] zoneMotUtilisateur;

    /** Zone de texte pour afficher les mots corrects qu'il fallait retrouver */
    private TextView[] zoneMotCorrect;

    /** Zone pour afficher combien de mots l'utilisateur a effectivement retrouvé */
    private TextView etiqCombienTrouve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_resultat);

        // Accès au titre de l'activité et la légende associée
        etiqTitreActivite = findViewById(R.id.nom_activite_resultat);
        etiqCombienTrouve = findViewById(R.id.texte_combien);

        // accès sur les zones d'affichage des mots utilisateur
        zoneMotUtilisateur = new TextView[MainActivity.NB_MOT];
        zoneMotUtilisateur[0] =  findViewById(R.id.proposition1);
        zoneMotUtilisateur[1] =  findViewById(R.id.proposition2);
        zoneMotUtilisateur[2] = findViewById(R.id.proposition3);
        zoneMotUtilisateur[3] =  findViewById(R.id.proposition4);
        zoneMotUtilisateur[4] =  findViewById(R.id.proposition5);

        // accès sur les zones d'affichage des mots corrects et des résultats
        zoneMotCorrect = new TextView[MainActivity.NB_MOT];
        zoneMotCorrect[0] =  findViewById(R.id.motok1);
        zoneMotCorrect[1] = findViewById(R.id.motok2);
        zoneMotCorrect[2] = findViewById(R.id.motok3);
        zoneMotCorrect[3] =  findViewById(R.id.motok4);
        zoneMotCorrect[4] = findViewById(R.id.motok5);
        etiqCombienTrouve =  findViewById(R.id.texte_combien);


        // on récupère l'intention à l'origine de l'activité
        Intent intentionRecu = getIntent();

        // on récupère les tableaux de mots contenus dans l'intention
        ArrayList<String> listeAlea = intentionRecu.getStringArrayListExtra(MainActivity.CLE_MOT_CORRECT);
        ArrayList<String> listeJoueur = intentionRecu.getStringArrayListExtra(ActiviteProposition.CLE_MOT_UTILISATEUR);

        // on place les mots dans les zones d'affichage
        for (int indice = 0; indice < listeAlea.size(); indice++) {
            zoneMotCorrect[indice].setText(listeAlea.get(indice));
        }

        for (int indice = 0; indice < listeJoueur.size(); indice++) {
            zoneMotUtilisateur[indice].setText(listeJoueur.get(indice));
        }

        // on modifie les textes affichés selon que le joueur a gagné ou pas
        if (identique(listeAlea, listeJoueur)) {
            etiqTitreActivite.setText(R.string.message_gagne1);
            etiqCombienTrouve.setText(R.string.message_gagne2);
        } else {
            etiqTitreActivite.setText(R.string.message_perdu1);
            etiqCombienTrouve.setText(getString(R.string.message_perdu1bis
                    , combienEnCommun(listeAlea, listeJoueur)));
        }

    }

    /**
     * Méthode invoquée lorsque l'utilisateur clique sur "Rejouer".
     * L'activité principale de l'application est relancée.
     * @param bouton    bouton sur lequel l'utilisateur a cliqué
     */
    public void clicRejouer(View bouton) {
        Intent mainActivity =
                new Intent(ActiviteResultat.this, MainActivity.class);
        startActivity(mainActivity);
    }

}
