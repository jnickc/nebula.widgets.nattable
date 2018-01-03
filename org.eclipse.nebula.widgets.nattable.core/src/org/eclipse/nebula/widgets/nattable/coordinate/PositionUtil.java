/*******************************************************************************
 * Copyright (c) 2012, 2018 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.nebula.widgets.nattable.coordinate;

import static org.eclipse.nebula.widgets.nattable.util.ObjectUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PositionUtil {

    /**
     * Finds contiguous numbers in a group of numbers.
     */
    public static List<List<Integer>> getGroupedByContiguous(Collection<Integer> numberCollection) {
        List<Integer> numbers = new ArrayList<Integer>(numberCollection);
        Collections.sort(numbers);

        List<Integer> contiguous = new ArrayList<Integer>();
        List<List<Integer>> grouped = new ArrayList<List<Integer>>();

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i).intValue() + 1 != numbers.get(i + 1).intValue()) {
                contiguous.add(numbers.get(i));
                grouped.add(contiguous);
                contiguous = new ArrayList<Integer>();
            } else {
                contiguous.add(numbers.get(i));
            }
        }
        if (isNotEmpty(numbers)) {
            contiguous.add(numbers.get(numbers.size() - 1));
        }
        grouped.add(contiguous);
        return grouped;
    }

    /**
     * Creates {@link Range}s out of list of numbers. The contiguous numbers are
     * grouped together in Ranges.
     * <p>
     * Example: 0, 1, 2, 4, 5, 6 will return [[Range(0 - 3)][Range(4 - 7)]]
     * </p>
     * <p>
     * The last number in the Range is not inclusive.
     * </p>
     *
     * @param numbers
     *            The numbers to create the Range collection.
     * @return List of Ranges for the given Collection of numbers.
     */
    public static List<Range> getRanges(Collection<Integer> numbers) {
        List<Range> ranges = null;

        if (isNotEmpty(numbers)) {
            List<List<Integer>> grouped = getGroupedByContiguous(numbers);
            ranges = new ArrayList<Range>(grouped.size());
            for (List<Integer> number : grouped) {
                int start = number.get(0);
                int end = number.get(number.size() - 1) + 1;

                ranges.add(new Range(start, end));
            }
        }

        return ranges != null ? ranges : new ArrayList<Range>();
    }

    /**
     * Creates {@link Range}s out of list of numbers. The contiguous numbers are
     * grouped together in Ranges.
     *
     * <p>
     * Example: 0, 1, 2, 4, 5, 6 will return [[Range(0 - 3)][Range(4 - 7)]]
     * </p>
     * <p>
     * The last number in the Range is not inclusive.
     * </p>
     *
     * @param numbers
     *            The numbers to create the Range collection.
     * @return List of Ranges for the given Collection of numbers.
     */
    public static List<Range> getRanges(int... numbers) {
        List<Integer> numberCollection = new ArrayList<Integer>(numbers.length);

        for (int number : numbers) {
            numberCollection.add(number);
        }

        return getRanges(numberCollection);
    }
}
