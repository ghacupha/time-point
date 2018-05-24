/**
 * time-point - A thin wrapper for Local Date
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.ghacupha.time.point;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimePointTest {

    private static TimePoint timePoint;

    @BeforeAll
    public static void setUp() throws Exception {
        timePoint = new SimpleDate(2018,5,12);
    }

    @Test
    public void addition() throws Exception {

        //assertEquals(timePoint.addDays(5),new SimpleDate(2018,5,17));
        assertTrue(timePoint.addDays(5).equals(new SimpleDate(2018,5,17)));
        assertTrue(timePoint.addDays(30).equals(new SimpleDate(2018,6,11)));

    }

    @Test
    public void minusDays() throws Exception {

        assertTrue(timePoint.minusDays(5).equals(new SimpleDate(2018,5,7)));
        assertTrue(timePoint.minusDays(30).equals(new SimpleDate(2018,4,12)));
    }

    @Test
    public void after() throws Exception {
        assertTrue(timePoint.after(new SimpleDate(2018,5,11)));
        assertTrue(timePoint.after(new SimpleDate(2018,3,31)));
    }

    @Test
    public void before() throws Exception {
        assertTrue(timePoint.before(new SimpleDate(2018,7,1)));
        assertTrue(timePoint.before(new SimpleDate(2018,5,13)));
    }

    @Test
    public void newTimePoint() throws Exception {
        TimePoint testPoint = new SimpleDate();

        assertTrue(new SimpleDate().after(testPoint.minusDays(1)));
        assertTrue(new SimpleDate().before(testPoint.addDays(1)));
    }

    @Test
    public void toStringTest() throws Exception {

        assertEquals("2018-05-08",new SimpleDate(2018,5,8).toString());
    }
}