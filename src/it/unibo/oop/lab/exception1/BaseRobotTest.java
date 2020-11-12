package it.unibo.oop.lab.exception1;

import static it.unibo.oop.lab.exception1.RobotEnvironment.WORLD_X_UPPER_LIMIT;
import static it.unibo.oop.lab.exception1.RobotEnvironment.WORLD_Y_UPPER_LIMIT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Testing class for PositionOutOfBound.
 * 
 */
public final class BaseRobotTest {

    /**
     * Simple test for testing a robot moving, wandering the available
     * environment.
     * 
     */
    @Test
    public void testRobotMovementBase() {
        /*
         * FIRST OF ALL, take a look to "TestWithExceptions". Read the source and the
         * comments very carefully.
         */
        /*
         *  1) Create a Robot with battery level 100
         */
        final Robot r1 = new Robot("SimpleRobot", 100);
        // checking if robot is in position x=0; y=0
        assertEquals("[CHECKING ROBOT INIT POS X]", 0, r1.getEnvironment().getCurrPosX());
        assertEquals("[CHECKING ROBOT INIT POS Y]", 0, r1.getEnvironment().getCurrPosY());
        /*
         * 2) Move the robot right until it touches the world limit
         */
        try {
        	for (int i = 0; i < WORLD_X_UPPER_LIMIT; i++) {
                // check if position if coherent
            	r1.moveRight();
            }
            // reached the right limit of the world
            r1.moveRight();
            /*
             * 2) Move to the top until it reaches the upper right conrner of the world
             */
            fail("Non va bene se sono arrivato qui");
        } catch(PositionOutOfBoundException e) {
        	assertTrue(e.getMessage().contains("pos(" + (WORLD_X_UPPER_LIMIT + 1) + ", 0)"));
        } catch(NotEnoughBatteryException e) {
        	fail("Nessun problema di batteria qua");
        }
        
        try {
        	for (int i = 0; i < WORLD_Y_UPPER_LIMIT; i++) {
                // check if position if coherent
        		r1.moveUp();
            }
            // reached the upper limit of the world
        	r1.moveUp() ;
        } catch(PositionOutOfBoundException e) {
        	assertTrue(e.getMessage().contains("pos(" + WORLD_X_UPPER_LIMIT + ", " + (WORLD_Y_UPPER_LIMIT + 1) + ")"));
        } catch(NotEnoughBatteryException e) {
        	fail("Qua non dovrei arrivare perchè la batteria non è il problema");
        }
    }

    /**
     * Simple test for testing robot battery.
     * 
     */
    @Test
    public void testRobotBatteryBase() {
        final Robot r2 = new Robot("SimpleRobot2", 20);

        while (r2.getBatteryLevel() > 0) {
        	try {
	            r2.moveUp();
	            r2.moveDown();
	            //Assert.fail();
        	} catch (NotEnoughBatteryException e) {
        		System.out.println(e);
        	}
        }
        
        assertEquals(0d, r2.getBatteryLevel(), 0);
        assertEquals("[CHECKING ROBOT INIT POS Y]", 0, r2.getEnvironment().getCurrPosY());
        
        try {
        	r2.moveUp();
        } catch (NotEnoughBatteryException e) {
        	System.out.println(e);
        }
        r2.recharge();

        assertEquals(100, r2.getBatteryLevel(), 0);
    }
}
