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

import static io.github.ghacupha.time.point.SimpleDate.on;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateRangeTest {

    private static DateRange dateRange;

    @BeforeAll
    public static void setUp() throws Exception {
        dateRange = new DateRange(SimpleDate.newMoment(2017,9,30), SimpleDate.newMoment(2017,12,30));
    }

    @Test
    public void upTo() {

        assertTrue(dateRange.includes(on(2017,11,30)));
        assertTrue(dateRange.includes(on(2017,12,30)));
        assertFalse(dateRange.includes(on(2017,12,31)));

        DateRange infiniteStart = DateRange.upTo((SimpleDate) on(2017,11,30));
        DateRange infiniteEnd = DateRange.startingOn((SimpleDate) on(2017,11,30));

        assertTrue(infiniteStart.includes(on(1900,01,01)));
        assertTrue(infiniteEnd.includes(on(9999,01,01)));
    }


}