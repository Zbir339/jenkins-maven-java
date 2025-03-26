package org.example;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class DureeTest {

    private Duree duree;
    private Duree dureeComparaison;
    private Duree dureeEmpty;

    @BeforeEach
    public void init(){
        duree = new Duree(1,25,39);
        dureeComparaison = new Duree(1,25,39);
        dureeEmpty = new Duree();
    }

    @AfterEach
    public void vider(){
        dureeEmpty.setHeures(0);
        dureeEmpty.setMinutes(0);
        dureeEmpty.setSecondes(0);
        // apres chaque test de comparaison on la retourne a sa valeur initial
        dureeComparaison.setHeures(1);
        dureeComparaison.setMinutes(25);
        dureeComparaison.setSecondes(39);
    }


    ///HEURES TESTING//
    @Test
    void setHeureOneAndReturnOne() {
        dureeEmpty.setHeures(1);
        assertEquals(dureeEmpty.getHeures(),1);
    }

    @Test
    void setHeureBelowOneAndThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,()->{duree.setHeures(-1);});
    }
    ///////////////////
    /// Minutes Testing ////
    @Test
    void setMinuteOneAndReturnOne() {
        dureeEmpty.setMinutes(1);
        assertEquals(dureeEmpty.getMinutes(),1);
    }

    @Test
    void setMinuteOneAndReturnOneSurchage() {
        dureeEmpty.setMinutes(1,true);
        assertEquals(dureeEmpty.getMinutes(),1);
    }

    @Test
    void setMinuteBelowOneAndThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setMinutes(-1);});
    }
    @Test
    void setMinuteBeyodFiftyNineAndThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setMinutes(60);});
    }

    @Test
    void setMinuteBelowOneAndThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setMinutes(-1,true);});
    }
    @Test
    void setMinuteBeyodFiftyNineAndThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setMinutes(61,true);});
    }

    @Test
    void setMinuteBelowOneAndFalseThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setMinutes(-1,false);});
    }
    @Test
    void setMinuteBeyodFiftyNineAndFalseThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setMinutes(61,false);});
    }
    /// ////////////
    /// Seconds Testing ////
    @Test
    void setSecondesOneAndReturnOne() {
        dureeEmpty.setSecondes(1);
        assertEquals(dureeEmpty.getSecondes(),1);
    }

    @Test
    void setSecondesOneAndReturnOneSurchage() {
        dureeEmpty.setSecondes(1,true);
        assertEquals(dureeEmpty.getSecondes(),1);
    }

    @Test
    void setSecondesBelowOneAndThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setSecondes(-1);});
    }

    @Test
    void setSecondesBeyondFiftyNineAndThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setSecondes(60);});
    }

    @Test
    void setSecondesBelowOneAndThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setSecondes(-1,true);});
    }

    @Test
    void setSecondesBeyondFiftyNineAndThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setSecondes(61,true);});
    }

    @Test
    void setSecondesBelowOneAndFalseThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setSecondes(-1,false);});
    }

    @Test
    void setSecondesBeyondFiftyNineAndFalseThrowIllegalArgumentExceptionSurchage() {
        assertThrows(IllegalArgumentException.class,()->{dureeEmpty.setSecondes(61,false);});
    }
    /// ////////////

    @Test
    void setDureeOneHourTwentyFiveMinuteAndThirtyNineSecondesReturnOneTwentyFiveThirtyNine() {
        assertEquals(duree.formatDuree(),"01:25:39");
    }

    @Test
    void afficherDureeOneHourTwentyFiveMinuteAndThirtyNineSecondes() {
        // on se basera sur formatDuree et peut etre on peut utiliser un ByteArrayOutput
        // on peut sauvegarder l'output dans une liste des Bytes
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // on affiche la duree
        duree.afficherDuree();

        // on restaure la sortie standard
        System.setOut(originalOut);

        assertEquals(outputStreamCaptor.toString().trim(),"01:25:39");
    }
    // Comparaison
    @Test
    void comparerDureeReturnZero() {
        assertEquals(duree.comparer(dureeComparaison),0);
    }

    @Test
    void comparerDureeHeureLowerThanTwoReturnMinusOne() {
        dureeComparaison.setHeures(2);
        assertEquals(duree.comparer(dureeComparaison),-1);
    }

    @Test
    void comparerDureeHeureGreaterThanZeroReturnOne() {
        dureeComparaison.setHeures(0);
        assertEquals(duree.comparer(dureeComparaison),1);
    }

    @Test
    void comparerDureeMinutesLowerThanThirtyTwoReturnMinusOne() {
        dureeComparaison.setMinutes(32);
        assertEquals(duree.comparer(dureeComparaison),-1);
    }

    @Test
    void comparerDureeMinutesGreaterThanZeroReturnOne() {
        dureeComparaison.setMinutes(0);
        assertEquals(duree.comparer(dureeComparaison),1);
    }

    @Test
    void comparerDureeSecondesLowerThanFortyTwoReturnMinusOne() {
        dureeComparaison.setMinutes(42);
        assertEquals(duree.comparer(dureeComparaison),-1);
    }

    @Test
    void comparerDureeSecondesGreaterThanZeroReturnOne() {
        dureeComparaison.setMinutes(0);
        assertEquals(duree.comparer(dureeComparaison),1);
    }

    @Test
    void nextSecondeStartWithThirtyNineReturnForty(){
        duree.nextSeconde();
        assertEquals(duree.getSecondes(),40);
    }

    @Test
    void nextSecondeStartWithFiftyNineReturnZeroSecondesAndOneMinutes(){
        dureeEmpty.setSecondes(59);
        dureeEmpty.nextSeconde();
        assertEquals(dureeEmpty.getSecondes(),0);
        assertEquals(dureeEmpty.getMinutes(),1);
    }

    @Test
    void nextMinuteStartWithTwentyFiveReturnTwentySix(){
        duree.nextMinute();
        assertEquals(duree.getMinutes(),26);
    }

    @Test
    void nextMinuteStartWithFiftyNineReturnZeroSecondesAndOneMinutes(){
        dureeEmpty.setMinutes(59);
        dureeEmpty.nextMinute();
        assertEquals(dureeEmpty.getMinutes(),0);
        assertEquals(dureeEmpty.getHeures(),1);
    }

    @Test
    void dureeOneHoureTwentyFiveAndThirtyNineSecondesReturnTrue() {
        assertTrue(duree.isEquals(dureeComparaison));
    }
    @Test
    void dureeOneHoureTwentyFiveAndThirtyNineSecondesPlusMinuteReturnFalse() {
        dureeComparaison.nextMinute();
        assertFalse(duree.isEquals(dureeComparaison));
    }

    @Test
    void dureeOneHoureTwentyFiveAndThirtyNineSecondesPlusSecondesReturnFalse() {
        dureeComparaison.nextSeconde();
        assertFalse(duree.isEquals(dureeComparaison));
    }

    @Test
    void dureeOneHoureTwentyFiveAndThirtyNineSecondesPlusThreeHoursReturnFalse() {
        dureeComparaison.setHeures(3);
        assertFalse(duree.isEquals(dureeComparaison));
    }

    @Test
    void toSeconds() {
        assertEquals(dureeEmpty.toSeconds(),0);
        assertEquals(duree.toSeconds(),5139);
    }
}