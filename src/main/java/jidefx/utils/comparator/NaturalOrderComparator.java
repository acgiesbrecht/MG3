/*
 * Copyright (c) 2002-2015, JIDE Software Inc. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package jidefx.utils.comparator;

import java.text.Collator;
import java.util.Comparator;

/**
 * This class compares objects as Comparable first, then convert them to <tt>String</tt>s using the <tt>toString</tt>
 * methods. This is the default comparator used by the {@link ObjectComparatorManager} and should be able to handle most
 * comparison cases.
 */
public class NaturalOrderComparator implements Comparator<Object> {
    private static NaturalOrderComparator singleton = null;

    /**
     * Constructor.
     * <p/>
     * Has protected access to prevent other clients creating instances of the class ... it is stateless so we need only
     * one instance.
     */
    protected NaturalOrderComparator() {
    }

    /**
     * Returns <tt>ObjectComparator</tt> singleton.
     *
     * @return an instance of DefaultComparator.
     */
    public static NaturalOrderComparator getInstance() {
        if (singleton == null)
            singleton = new NaturalOrderComparator();
        return singleton;
    }

    /**
     * Compares two Objects using the Comparable interface first. If not, it will use <tt>toString()</tt> methods to
     * compare them as two strings.
     *
     * @param o1 the first object to be compared
     * @param o2 the second object to be compared
     * @return 0 if a and b are equal, less than 0 if a $lt; b, grater than 0 if a &gt; b.
     */
    public int compare(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        else if (o1 == null) {
            return -1;
        }
        else if (o2 == null) {
            return 1;
        }

        if (o1 instanceof Comparable) {
            return ((Comparable) o1).compareTo(o2);
        }
        else if (o2 instanceof Comparable) {
            return 0 - ((Comparable) o2).compareTo(o1);
        }

        return Collator.getInstance().compare(o1.toString(), o2.toString());
    }
}
