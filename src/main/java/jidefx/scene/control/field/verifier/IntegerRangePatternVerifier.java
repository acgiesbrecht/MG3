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
package jidefx.scene.control.field.verifier;

import jidefx.utils.CommonUtils;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * A special verifier for an integer so that it is in the specified range.
 */
public class IntegerRangePatternVerifier extends RangePatternVerifier<Integer> {
    public IntegerRangePatternVerifier(int min, int max) {
        super(min, max);
    }

    public IntegerRangePatternVerifier(int min, int max, boolean fixedLength) {
        super(min, max, fixedLength);
    }

    @Override
    public String format(Integer value) {
        if (value == null) return "";

        NumberFormat format = NumberFormat.getInstance();
        if (_maxLength != -1) {
            format.setMaximumIntegerDigits(getMaxLength());
            format.setMinimumIntegerDigits(getMinLength());
        }
        format.setMaximumFractionDigits(0);
        format.setGroupingUsed(false);
        return format.format(value);
    }

    @Override
    public Integer parse(String text) {
        if (text == null || text.trim().isEmpty()) return null;

        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumIntegerDigits(getMaxLength());
        format.setMinimumIntegerDigits(getMinLength());
        format.setMaximumFractionDigits(0);
        format.setGroupingUsed(false);

        try {
            format.parse(text).intValue(); // parse to trigger exception.
            return Integer.parseInt(text);
        }
        catch (ParseException e) {
            throw new NumberFormatException(e.getLocalizedMessage());
        }
    }

    @Override
    public Integer getNextPage(Integer current, boolean restart) {
        int value;
        if (current != null && current + 10 < getMax()) {
            value = current + 10;
        }
        else {
            value = restart ? getMin() : getMax();
        }
        return value;
    }

    @Override
    public Integer getPreviousPage(Integer current, boolean restart) {
        int value;
        if (current != null && current - 10 > getMin()) {
            value = current - 10;
        }
        else {
            value = restart ? getMax() : getMin();
        }
        return value;
    }

    @Override
    public Integer getHome(Integer current) {
        return getMin();
    }

    @Override
    public Integer getEnd(Integer current) {
        return getMax();
    }

    @Override
    public Integer getNextValue(Integer current, boolean restart) {
        int value;
        if (current != null && current < getMax()) {
            value = current + 1;
        }
        else {
            value = restart ? getMin() : getMax();
        }
        Boolean valid = call(format(value));
        return valid ? value : getNextValue(value, true);
    }

    @Override
    public Integer getPreviousValue(Integer current, boolean restart) {
        int value;
        if (current != null && current > getMin()) {
            value = current - 1;
        }
        else {
            value = restart ? getMax() : getMin();
        }
        Boolean valid = call(format(value));
        return valid ? value : getPreviousValue(value, true);
    }

    @Override
    public Boolean call(String text) {
        if (text.length() > getMaxLength()) return false;
        try {
            int i = parse(text);
            if (i >= getMin() && i <= getMax()) return true;
        }
        catch (NumberFormatException e) {
            CommonUtils.ignoreException(e);
        }
        return false;
    }
}
