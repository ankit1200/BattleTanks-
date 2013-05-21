package com.BattleTanks;

import sofia.graphics.OvalShape;
import android.widget.*;
import android.view.View;
import sofia.app.Screen;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import sofia.graphics.Anchor;
import sofia.app.ShapeScreen;
import android.view.View;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 *  This class handles much of the game info as well as GUI
 *
 *  @author Luke Marsh (mluke93)
 *  @author Ankit
 *  @author Shawn
 *  @version Apr 30, 2013
 */
public class TanksScreen
    extends ShapeScreen implements SeekBar.OnSeekBarChangeListener
{

    private int turn; // 1 = player 1 | 2 = player 2
    private Tank player1;
    private Tank player2;
    private View tank1;
    private View tank1tur;
    private View tank2;
    private View tank2tur;
    private View redBulletView;
    private View greenBulletView;
    private SeekBar angleBar;
    private SeekBar powerBar;
    private TextView gasText1;
    private TextView gasText2;
    private TextView phaseView;
    private TextView nameView01;
    private TextView nameView02;
    private ProgressBar healthBar01;
    private ProgressBar healthBar02;
    private TextView angleText;
    private TextView powerView;
    private Button replayButton;
    private Button rightButton;
    private Button leftButton;
    private Button fireButton;
    private int t1AngleSave;
    private int t2AngleSave;
    private int t1PowerSave;
    private int t2PowerSave;

    /**
     * initializes the screen
     */
    public void initialize()
    {
        reset();
        getCoordinateSystem().origin(Anchor.BOTTOM_LEFT).flipY();
    }

    // ----------------------------------------------------------
    /**
     * Moves left when left button is clicked
     */
    public void leftButtonClicked()
    {
        if (turn == 1)
        {
            player1.move(0);
            gasText1.setText("Gas: " + player1.getGas());
            tank1.setX(player1.getloc());
            tank1tur.setX(player1.getloc());
        }
        else
        {
            player2.move(0);
            gasText2.setText("Gas: " + player2.getGas());
            tank2.setX(player2.getloc());
            tank2tur.setX(player2.getloc());
        }

    }

    /**
     * Moves right then right button is clicked
     */
    public void rightButtonClicked()
    {
        if (turn == 1)
        {
            player1.move(1);
            gasText1.setText("Gas: " + player1.getGas());
            tank1.setX(player1.getloc());
            tank1tur.setX(player1.getloc());
        }
        else
        {
            player2.move(1);
            gasText2.setText("Gas: " + player2.getGas());
            tank2.setX(player2.getloc());
            tank2tur.setX(player2.getloc());
        }
    }

    /**
     * Fire Button is clicked
     */
    public void fireButtonClicked()
    {
        float distance;

        if (turn == 1)
        {
            distance = player1.range();

            redBulletView.setAlpha(0);
            greenBulletView.setX(player1.getloc() + distance + 32);
            greenBulletView.setY(271);
            greenBulletView.setAlpha(1);

            if (collision(player2, player1.getloc(), distance))
            {
                player2.setHealth(player2.getHealth() - 20);

            }

            saveState1();
        }
        else
        {
            distance = player2.range();

            greenBulletView.setAlpha(0);
            redBulletView.setX(player2.getloc() + distance + 32);
            redBulletView.setY(271);
            redBulletView.setAlpha(1);

            if (collision(player1, player2.getloc(), distance))
            {
                player1.setHealth(player1.getHealth() - 20);
            }

            saveState2();
        }
    }

    /**
     * checks collisions
     * @param tank being attacked
     * @param x player x location
     * @param range distance bullet travels
     * @return true if bullet collides
     */
    public boolean collision(Tank tank, float x, float range)
    {
        if (x + range < tank.getloc() + 28 &&
            x + range > tank.getloc() - 28)
        {
            return true;
        }
        return false;
    }

    /**
     * handles saving info and reloading
     */
    public void saveState1()
    {
        healthBar02.setProgress(player2.getHealth());

        if (player2.getHealth() == 0)
        {
            phaseView.setText("P1 wins!");
            gameOver();
        }
        else
        {
            turn = 2;
            phaseView.setText("P2 Turn!");
            angleBar.setProgress(t2AngleSave);
            angleText.setText("Angle: " + t2AngleSave);
            powerBar.setProgress(t2PowerSave);
            powerView.setText("Power: " + t2PowerSave);
        }

    }

    /**
     * handles saving info and reloading for the other player
     */
    public void saveState2()
    {
        healthBar01.setProgress(player1.getHealth());

        if (player1.getHealth() == 0)
        {
            phaseView.setText("P2 wins!");
            gameOver();
        }
        else
        {
            turn = 1;
            phaseView.setText("P1 Turn!");
            angleBar.setProgress(t1AngleSave);
            angleText.setText("Angle: " + t1AngleSave);
            powerBar.setProgress(t1PowerSave);
            powerView.setText("Power: " + t1PowerSave);
        }
    }

    /**
     * Tracks SeekBar movement
     * @param seekBar bar being controlled
     * @param progress the progress of the bar
     * @param fromUser if the bar is from the user
     */
    public void onProgressChanged(SeekBar seekBar, int progress,
        boolean fromUser)
    {
        if (seekBar == powerBar)
        {
            if (turn == 1)
            {
                player1.setPower(progress);
                powerView.setText("Power: " + progress);
                t1PowerSave = progress;
            }
            else
            {
                player2.setPower(progress);
                powerView.setText("Power: " + progress);
                t2PowerSave = progress;
            }
        }
        else
        {
            if (turn == 1)
            {
                tank1tur.setRotation(-progress);
                angleText.setText("Angle: " + progress);
                player1.setAngle(progress);
                t1AngleSave = progress;
            }
            else
            {
                tank2tur.setRotation(-progress);
                angleText.setText("Angle: " + progress);
                player2.setAngle(progress);
                t2AngleSave = progress;
            }
        }
    }

    /**
     * gameOver() is called when someone wins the game, it shows the play again
     * button and disables all the other buttons.
     */
    public void gameOver()
    {
        replayButton.setAlpha(1);
        replayButton.setEnabled(true);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        fireButton.setEnabled(false);
        angleBar.setEnabled(false);
        powerBar.setEnabled(false);
    }

    /**
     * can be clicked when the game ends, it starts it again
     */
    public void replayButtonClicked()
    {
        reset();
    }

    /**
     * resets the game
     */
    public void reset()
    {
        player1 = new Tank("Player 1", 83);
        player2 = new Tank("Player 2", 632);

        // resets the gas
        player1.setGas(30);
        player2.setGas(30);
        gasText1.setText("Gas: " + player1.getGas());
        gasText2.setText("Gas: " + player2.getGas());

        // resets the phaseView
        phaseView.setText("P1 Turn!");

        // sets the names
        nameView01.setText(player1.getName());
        nameView02.setText(player2.getName());

        // sets the turns
        turn = 1;

        // sets angles and powers to 0
        t1AngleSave = 0;
        t2AngleSave = 0;
        t1PowerSave = 0;
        t2PowerSave = 0;

        // sets angle and power bar max
        angleBar.setMax(180);
        powerBar.setMax(80);
        healthBar01.setMax(100);
        healthBar02.setMax(100);

        // sets the health bar progress
        healthBar01.setProgress(player1.getHealth());
        healthBar02.setProgress(player2.getHealth());

        // takes care of angle and progress bars
        angleBar.setProgress(t1AngleSave);
        angleText.setText("Angle: " + t1AngleSave);
        powerView.setText("Power: " + t2PowerSave);
        powerBar.setProgress(t2PowerSave);
        angleBar.setOnSeekBarChangeListener(this);
        powerBar.setOnSeekBarChangeListener(this);

        // sets buttons to respective enables
        replayButton.setEnabled(false);
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);
        fireButton.setEnabled(true);
        powerBar.setEnabled(true);
        angleBar.setEnabled(true);

        // hides unnecessary views
        replayButton.setAlpha(0);
        greenBulletView.setAlpha(0);
        redBulletView.setAlpha(0);
    }

    /**
     * Does nothing in this situation
     */
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        // Does nothing

    }

    /**
     * Does nothing in this situation
     */
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        //Does nothing
    }
}