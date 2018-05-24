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

import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Adds datePattern and mechanisms for adding datePatterns to TimePoint interface which enables us to
 * read dates from string using specified patterns
 */
public class ReadableDate extends SimpleDateDecorator implements ReadableTime {

    private static final Logger log = getLogger(ReadableDate.class);

    private String datePattern;

    /*public ReadableDate(String datePattern, TimePoint timePoint) {
        super(timePoint);

    }*/

    public ReadableDate(String datePattern, TimePoint attribute) {
        super(attribute);
        this.datePattern = datePattern;
        log.debug("New timepoint {} with the pattern {}", attribute, datePattern);
    }

    /**
     * Reads dateString and return equivalent {@link TimePoint} object
     *
     * @param dateString String from which we are extracting the date
     * @return Equivalent TimePoint for the string
     */
    @Override
    public TimePoint parseString(String dateString) {

        log.debug("Parsing the string {} to convert to timePoint, using the pattern {}", dateString, datePattern);

        return parseString(dateString, datePattern);
    }

    /**
     * Reads dateString and return equivalent {@link TimePoint} object
     *
     * @param dateString  String from which we are extracting the date
     * @param datePattern Date pattern to be used in parsing the date string argument
     * @return Equivalent TimePoint for the string
     */
    @Override
    public TimePoint parseString(String dateString, String datePattern) {

        log.debug("Parsing the string {} to convert to timePoint, using the pattern {}", dateString, datePattern);

        return TimePointUtils.parseString(dateString, datePattern);
    }

    @Override
    public String toString() {

        log.debug("Converting timePoint : {} to string using the pattern {}", this, datePattern);

        return getDay(this).format(DateTimeFormatter.ofPattern(datePattern));
    }

    public String format() {

        log.debug("Converting timePoint : {} to string using the pattern {}", this, datePattern);

        return getDay(this).format(DateTimeFormatter.ofPattern(datePattern));
    }

    public String format(String datePattern) {

        log.debug("Converting timePoint : {} to string using the pattern {}", this, datePattern);

        return getDay(this).format(DateTimeFormatter.ofPattern(datePattern));
    }
}
