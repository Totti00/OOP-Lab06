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
            fail("I should not get sych far!");
        } catch(PositionOutOfBoundException e) {
        	assertTrue(e.getMessage().contains("pos(" + (WORLD_X_UPPER_LIMIT + 1) + ", 0)"));
        } catch(NotEnoughBatteryException e) {
        	fail("No battery problems expected here");
        }
        try {
        	for (int i = 0; i < WORLD_Y_UPPER_LIMIT; i++) {
                // check if position if coherent
        		r1.moveUp();
            }
            // reached the upper limit of the world
        	r1.moveUp();
        } catch(PositionOutOfBoundException e) {
        	assertTrue(e.getMessage().contains("pos(" + WORLD_X_UPPER_LIMIT + ", " + (WORLD_Y_UPPER_LIMIT + 1) + ", 0)"));
        } catch(NotEnoughBatteryException e) {
        	fail("Battery should not be the issue here! ");
        }
    }

    /**
     * Simple test for testing robot battery.
     * 
     */
    @Test
    public void testRobotBatteryBase() {
        final Robot r2 = new Robot("SimpleRobot2", 20);
        /*
         * Repeatedly move the robot up and down until the battery is completely
         * exhausted.
         */
        try {
        	 while (r2.getBatteryLevel() > 0) {
                 r2.moveUp();
                 r2.moveDown();
             }
        	 r2.moveDown();
        	 fail("You're not supposed to get that far with no battery! ");
        } catch(PositionOutOfBoundException e) {
        	fail("I expected battery to fail! ");
        } catch(NotEnoughBatteryException e) {
        	assertTrue(e.getMessage().contains("Battery level is " + r2.getBatteryLevel()));
        }
       
    }
}
