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

import org.slf4j.Logger;

import java.time.LocalDate;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * A representation of a point in time to the accuracy of a day
 *
 * @author edwin.njeru
 */
public class SimpleDate implements TimePoint {

    private static final Logger log = getLogger(SimpleDate.class);

    static final SimpleDate PAST = new SimpleDate(LocalDate.MIN);
    static final SimpleDate FUTURE = new SimpleDate(LocalDate.MAX);

    private LocalDate base;

    public SimpleDate(LocalDate arg) {
        initialize(arg);
    }

    public SimpleDate(int year, int month, int day) {
        initialize(LocalDate.of(year, month, day));
    }

    public SimpleDate() {
        initialize(LocalDate.now());
    }

    SimpleDate(TimePoint timePoint) {
        this.base = getDay(timePoint);
    }

    public static TimePoint newMoment(int year, int month, int day) {
        return new SimpleDate(year, month, day);
    }

    public static TimePoint today() {

        return new SimpleDate();
    }

    public static TimePoint on(int year, int month, int dayOfMonth) {

        return new SimpleDate(year, month, dayOfMonth);
    }

    protected static LocalDate getDay(TimePoint arg) {
        SimpleDate simpleDate = (SimpleDate) arg;
        return simpleDate.base;
    }

    public static TimePoint newTimePoint(TimePoint arg) {

        return new SimpleDate(getDay(arg));
    }

    public static TimePoint of(int... date) {

        log.debug("Creating TimePoint object from : year{}, month{}, date{}", date[0], date[1], date[2]);

        return on(date[0], date[1], date[2]);
    }

    public static TimePoint ofLocal(LocalDate localDate) {

        log.debug("Creating timePoint object from LocalDate : {}", localDate);

        return on(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    private void initialize(LocalDate arg) {
        log.debug("TimePoint created with localDate {}", arg);
        this.base = arg;
    }

    @Override
    public boolean after(TimePoint arg) {
        log.debug("Checking if {} comes after {}", this, arg);
        return this.base.isAfter(getDay(arg));
    }

    @Override
    public boolean before(TimePoint arg) {
        log.debug("Checking if {} comes before {}", this, arg);
        return this.base.isBefore(getDay(arg));
    }

    @Override
    public SimpleDate addDays(int arg) {
        log.debug("Adding {} day(s) to {}", arg, this);
        return new SimpleDate(this.base.plusDays(arg));
    }

    @Override
    public SimpleDate minusDays(int arg) {
        log.debug("Subtracting {} day(s) from {}", arg, this);
        return new SimpleDate(this.base.minusDays(arg));
    }

    @Override
    public int compareTo(TimePoint arg) {
        log.debug("Comparing timePoint {} to {}", arg, this);
        SimpleDate other = (SimpleDate) arg;
        return this.base.compareTo(other.base);
    }

    @Override
    public boolean equals(Object arg) {
        if (!(arg instanceof SimpleDate)) {
            return false;
        }
        SimpleDate other = (SimpleDate) arg;
        return (base.equals(other.base));
    }

    @Override
    public int hashCode() {
        return base != null ? base.hashCode() : 0;
    }

    @Override
    public String toString() {
        return base.toString();
    }
}
