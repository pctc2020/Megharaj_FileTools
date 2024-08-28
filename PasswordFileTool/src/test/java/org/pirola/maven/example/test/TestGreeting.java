package org.pirola.maven.example.test;

/*
 * Maven test example.
 * Copyright (C) 2015 Fabio Pirola <fabio@pirola.org>

 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test main class.
 * @author Fabio Pirola <fabio@pirola.org>
 * @version 1.0
 * @since 2015-07-15
 */
public class TestGreeting extends TestCase {

    /**
     * Constructor.
     * @since 2015-07-15
     * @param testName
     *            Name of the test case.
     */
    public TestGreeting(final String testName) {
        super(testName);
    }

    /**
     * Create test suite.
     * @since 2015-07-15
     * @return The suite of tests being tested.
     */
    public static Test suite() {
        return new TestSuite(TestGreeting.class);
    }

    /**
     * Test get greeting.
     * @since 2015-07-15
     */
    public void testGetGreeting() {
        assertEquals("Test get greeting", "Hello World!!!", org.pirola.maven.example.MavenExample.getGreeting());
    }
}
