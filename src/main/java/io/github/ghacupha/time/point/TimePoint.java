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

import java.io.Serializable;

/**
 * This is used and library-specific representation of time for the library.
 * The granularity of the implementation is set to days.
 * The implementation is also such that this remains immutable. For instance if days are
 * added to this, it will remain the same unless it is newly reassigned
 *
 * @author edwin.njeru
 */
public interface TimePoint extends Comparable<TimePoint>, Serializable {

    /**
     * @param arg SimpleDate by which we compare this
     * @return True if the moment is after this
     */
    boolean after(TimePoint arg);

    /**
     * @param arg SimpleDate by which we compare this
     * @return True if the moment is before this
     */
    boolean before(TimePoint arg);

    /**
     * Adds days to this
     *
     * @param arg Number of days to be added
     * @return new instance of {@link TimePoint} with additional days
     */
    TimePoint addDays(int arg);

    /**
     * Removes days from this
     *
     * @param arg Number of days to be subtracted
     * @return new instance of {@link TimePoint} with days less the argument
     */
    TimePoint minusDays(int arg);

    @Override
    boolean equals(Object arg);

    @Override
    int hashCode();

    @Override
    String toString();
}
