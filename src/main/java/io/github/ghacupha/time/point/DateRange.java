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
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Represents a range {@link SimpleDate} objects of {@link TimePoint} start, and {@link TimePoint} end
 * <p>
 * adopted from Martin Fowler's Range implementation in {@literal https://martinfowler.com/eaaDev/Range.html}
 *
 * @author edwin.njeru
 */
public class DateRange implements Comparable<DateRange> {

    private static final Logger log = LoggerFactory.getLogger(DateRange.class);
    /**
     * Empty DateRange created by providing any end date that is sooner than the start date
     */
    private static final DateRange empty = new DateRange(new SimpleDate(2000, 4, 1), new SimpleDate(2000, 1, 1));
    private TimePoint start;
    private TimePoint end;

    public DateRange(TimePoint start, TimePoint end) {
        this.start = start;
        this.end = end;
    }

    public DateRange(LocalDate start, LocalDate end) {
        this(new SimpleDate(start.getYear(), start.getMonthValue(), start.getDayOfMonth()), new SimpleDate(end.getYear(), end.getMonthValue(), end.getDayOfMonth()));
        log.debug("New range between {} and {}", start, end);
    }

    // open ended constructor
    public static DateRange upTo(SimpleDate end) {
        log.debug("Getting new range ended on {}", end);
        return new DateRange(SimpleDate.PAST, end);
    }

    public static DateRange startingOn(SimpleDate start) {
        log.debug("New date range starting on {}", start);
        return new DateRange(start, SimpleDate.FUTURE);
    }

    public static DateRange combination(DateRange[] args) {

        Arrays.sort(args);

        if (!isContiguous(args)) {
            throw new IllegalArgumentException("Unable to combine dateRanges");
        }
        return new DateRange(args[0].start, args[args.length - 1].end);
    }

    public static boolean isContiguous(DateRange[] args) {

        Arrays.sort(args);

        for (int i = 0; i < args.length - 1; i++) {

            if (!args[i].abuts(args[i + 1])) {
                return false;
            }
        }

        return true;
    }

    public TimePoint getEnd() {
        return end;
    }

    public TimePoint getStart() {
        return start;
    }

    public boolean includes(TimePoint arg) {
        log.trace("Checking if : {} includes timePoint : {}", this, arg);
        return !arg.before(start) && !arg.after(end);
    }

    private boolean isEmpty() {

        return start.after(end);
    }

    public boolean overlaps(DateRange arg) {
        log.debug("Checking if the range {}, overlaps {}", arg, this);
        return arg.includes(start) || arg.includes(end) || this.includes(arg);
    }

    public boolean includes(DateRange arg) {
        log.debug("Checking if the range {}, includes {}", arg, this);
        return this.includes(arg.getStart()) && this.includes(arg.getEnd());
    }

    public DateRange gap(DateRange arg) {
        log.debug("Checking for gap between date range {} and {}", arg, this);
        if (this.overlaps(arg)) {
            return DateRange.empty;
        }
        DateRange lower, higher;

        if (this.compareTo(arg) < 0) {
            lower = this;
            higher = arg;
        } else {
            lower = arg;
            higher = this;
        }

        return new DateRange(lower.end.addDays(1), higher.start.addDays(-1));
    }

    public boolean abuts(DateRange arg) {
        log.debug("Checking if the date range {} abuts {}", arg, this);
        return !this.overlaps(arg) && this.gap(arg).isEmpty();
    }

    public boolean partitionedBy(DateRange[] args) {
        log.debug("Checking if the date ranges : {} are partitioned by {}", Arrays.asList(args), this);
        if (!isContiguous(args)) {
            return false;
        }
        return this.equals(DateRange.combination(args));
    }

    @Override
    public int compareTo(DateRange arg) {
        log.debug("Comparing date range {} to {}", this, arg);
        DateRange other = arg;
        if (!start.equals(other.start)) {
            return start.compareTo(other.start);
        }
        return end.compareTo(other.end);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Empty Date Range";
        }
        return start.toString() + " - " + end.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateRange)) {
            return false;
        }
        DateRange other = (DateRange) obj;

        return start.equals(other.getStart()) && end.equals(other.getEnd());
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
