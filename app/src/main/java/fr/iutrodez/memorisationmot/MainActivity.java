/*
 * TP multiactivités : jeu mémorisation des mots => activité principale
 * MainActivity.java                    07/16
 */
package fr.iutrodez.memorisationmot;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Activité principale de l'application jeu de mémorisation des mots.
 * La vue permet à l'utilisateur de demander 5 mots choisis de manière aléatoire.
 * En cliquant sur le bouton "Jouer", il lance une deuxième activité dans laquelle
 * il devra retrouver les mots mémorisés.
 * @author Servières
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** Nombre de mots à mémoriser */
    public static final int NB_MOT = 5;

    /** Clé pour le tableau des mots corrects transmis à l'activité fille "propositions" */
    public static final String CLE_MOT_CORRECT
            = "fr.iutrodez.memorisationmot.MOT_CORRECT";

    /** Tableau contenant les références sur les zones d'affichage
     *  des mots
     */
    private TextView[] tableTexteMot;

    /** Base de tous les mots possibles dans laquelle les 5 mots sont
     *  choisis au hasard*/
    private BaseDeMot base;

    /** Liste des mots choisis au hasard, proposés à l'utilisateur, et à mémoriser */
    private ArrayList<String> listeMotTire;

    /** Vrai ssi l'utilisateur a cliqué sur le bouton de sélection des mots */
    private boolean clicSur5Mots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicSur5Mots = false;

        // on accède aux zones de texte destinées aux mots
        tableTexteMot = new TextView[NB_MOT];
        tableTexteMot[0] =  findViewById(R.id.mot1);
        tableTexteMot[1] =  findViewById(R.id.mot2);
        tableTexteMot[2] =  findViewById(R.id.mot3);
        tableTexteMot[3] =  findViewById(R.id.mot4);
        tableTexteMot[4] =  findViewById(R.id.mot5);

        // initialisation et création de la base de mots
        base = new BaseDeMot();
    }

    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton de tirage
     * au sort des 5 mots
     * @param bouton  bouton sur lequel le clic a été effectué
     */
    public void clic5mots(View bouton) {

        // tirage aléatoire de 5 mots
        listeMotTire = base.tirageAleatoire();

        // on place les 5 mots dans les TextView
        for (int indice = 0; indice < listeMotTire.size(); indice++) {
            tableTexteMot[indice].setText(listeMotTire.get(indice));
        }

        clicSur5Mots = true;
    }

    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton de
     * réinitialiation des mots proposés (réinitialisés à vide)
     * @param bouton  bouton sur lequel le clic a été effectué
     */
    public void clicRaz(View bouton) {
        for (TextView raz : tableTexteMot) {
            raz.setText(null);
        }
    }


    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton
     * pour jouer. Lancement de l'activité propositions
     * @param bouton  bouton sur lequel le clic a été effectué
     */
    public void clicJouer(View bouton) {
        if ( ! clicSur5Mots) {

            // l'utilisateur n'a pas lancé le tirage des 5 mots
            Toast.makeText(this, R.string.messageToast, Toast.LENGTH_LONG)
                    .show();
			
        } else {
            clicRaz(bouton);    // on vide les mots
            /* Création d'une intention qui sera envoyée à l'activité "proposition"
             * accompagnée d'un tableau contenant les 5 mots proposés à l'utilisateur
             */
            Intent activiteProposition =
                    new Intent(MainActivity.this, ActiviteProposition.class);

            activiteProposition.putExtra(CLE_MOT_CORRECT, listeMotTire);

            startActivity(activiteProposition);
        }
    }

}
