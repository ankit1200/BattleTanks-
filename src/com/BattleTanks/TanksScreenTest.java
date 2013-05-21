package com.BattleTanks;

import android.widget.ProgressBar;
import android.view.View;
import android.widget.*;

// -------------------------------------------------------------------------
/**
 * This tests the main parts of the game info as well as the GUI
 *
 * @author Luke Marsh (mluke93)
 * @author Ankit Agarwal (ankit23)
 * @author Shawn Amjad (samja)
 * @version May 4, 2013
 */

public class TanksScreenTest
    extends student.AndroidTestCase<TanksScreen>
{

    private TextView    gasText1;
    private TextView    gasText2;
    private TextView    phaseView;
    private TextView    nameView01;
    private TextView    nameView02;
    private TextView    angleText;
    private TextView    powerView;
    private ProgressBar healthBar01;
    private ProgressBar healthBar02;
    private View        tank1;
    private View        tank2;
    private View        tank1tur;
    private View        tank2tur;
    private SeekBar     angleBar;
    private SeekBar     powerBar;
    private Button      replayButton;
    private Button      rightButton;
    private Button      leftButton;
    private Button      fireButton;


    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public TanksScreenTest()
    {
        super(TanksScreen.class);
    }


    /**
     * Tests the left button clicked method
     */
    public void testLeftRightButtonClicked()
    {
        click(leftButton);
        assertEquals(80, tank1.getX(), .0001);
        assertEquals("Gas: 29", gasText1.getText());

        click(rightButton);
        assertEquals(83, tank1.getX(), .0001);
        assertEquals("Gas: 28", gasText1.getText());
        click(fireButton);

        click(leftButton);
        assertEquals(629, tank2.getX(), .0001);
        assertEquals("Gas: 29", gasText2.getText());

        click(rightButton);
        assertEquals(632, tank2.getX(), .0001);
        assertEquals("Gas: 28", gasText2.getText());
    }


    /**
     * This is to test the collision method to see if the other tank gets hit
     */
    public void testCollision()
    {
        angleBar.setProgress(45);
        powerBar.setProgress(74);
        click(fireButton);
        assertEquals(80, healthBar02.getProgress());

        angleBar.setProgress(135);
        powerBar.setProgress(74);
        click(fireButton);
        assertEquals(80, healthBar01.getProgress());
    }


    /**
     * This is to test the save state of the tank1
     */
    public void testSaveState1()
    {
        angleBar.setProgress(45);
        powerBar.setProgress(74);
        click(fireButton);

        angleBar.setProgress(135);
        powerBar.setProgress(74);
        click(fireButton);

        click(fireButton);
        click(fireButton);
        assertEquals(60, healthBar01.getProgress());
        assertEquals(60, healthBar02.getProgress());
        assertEquals("Angle: 45", angleText.getText());
        assertEquals("Power: 74", powerView.getText());

        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);

        assertEquals("P1 wins!", phaseView.getText());
    }


    /**
     * This is to test the save state of the tank2
     */
    public void testSaveState2()
    {
        angleBar.setProgress(10);
        powerBar.setProgress(74);
        click(fireButton);

        angleBar.setProgress(135);
        powerBar.setProgress(74);
        click(fireButton);

        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);

        assertEquals("P2 wins!", phaseView.getText());
    }


    /**
     * This is to test the end of the game and the replay method
     */
    public void testReplayButtonAndReset()
    {
        angleBar.setProgress(45);
        powerBar.setProgress(74);
        click(fireButton);

        angleBar.setProgress(135);
        powerBar.setProgress(74);
        click(fireButton);

        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);
        click(fireButton);

        click(replayButton);

        assertEquals(84, tank1.getX(), .0001);
        assertEquals(632, tank2.getX(), .0001);
        assertEquals("P1 Turn!", phaseView.getText());
        assertEquals("Player 1", nameView01.getText());
        assertEquals("Player 2", nameView02.getText());
        assertEquals("Angle: 0", angleText.getText());
        assertEquals("Power: 0", powerView.getText());
    }
}
