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

public class ReadableDateTest {

    private static ReadableTime readableTime;

    @BeforeAll
    public static void setUp() throws Exception {

        readableTime = new ReadableDate("dd/MM/yyyy",SimpleDate.newMoment(2018,05,02));
    }

    @Test
    public void parseString() throws Exception {

        assertEquals(SimpleDate.on(2018,4,03),readableTime.parseString("03/04/2018"));
    }

    @Test
    public void parseStringWithPattern() throws Exception {

        assertEquals(SimpleDate.on(2018,8,03),readableTime.parseString("08/03/2018","MM/dd/yyyy"));
    }

    @Test
    public void toStringTest() {

        assertEquals("02/05/2018",readableTime.toString());
    }
}