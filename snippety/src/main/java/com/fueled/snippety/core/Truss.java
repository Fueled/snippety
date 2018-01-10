package com.fueled.snippety.core;

import android.text.SpannableStringBuilder;

import java.util.ArrayDeque;
import java.util.Deque;

import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;

/**
 * wrapper class on top of SpannableStringBuilder based on builder pattern.
 * A {@link SpannableStringBuilder} wrapper whose API doesn't make me want to stab my eyes out.
 *
 * @author Jake Warton
 */

public class Truss {

    private static final String NEW_LINE = "\n";
    private static final String DEFAULT_DELIMITER = "`";
    private final SpannableStringBuilder builder;
    private final Deque<Span> stack;

    /**
     * default constructor for Truss
     */
    public Truss() {
        builder = new SpannableStringBuilder();
        stack = new ArrayDeque<>();
    }

    /**
     * append a String
     *
     * @param str to append
     * @return Truss
     */
    public Truss append(String str) {
        builder.append(str);
        return this;
    }

    /**
     * append a CharSequence
     *
     * @param charSequence to append
     * @return Truss
     */
    public Truss append(CharSequence charSequence) {
        builder.append(charSequence);
        return this;
    }

    /**
     * append a character
     *
     * @param c
     * @return Truss
     */
    public Truss append(char c) {
        builder.append(c);
        return this;
    }

    /**
     * append a number
     *
     * @param number
     * @return Truss
     */
    public Truss append(int number) {
        builder.append(String.valueOf(number));
        return this;
    }

    /**
     * append a string with specified spans (Snippety)
     *
     * @param str
     * @param spans
     * @return Truss
     */
    public Truss append(String str, Object spans) {
        Span span = new Span(builder.length(), spans);
        builder.append(str);
        iterateSpans(span);
        return this;
    }

    /**
     * add specified spans to a space
     * @param spans
     * @return Truss
     */
    public Truss append(Object spans) {
        return append(" ", spans);
    }

    /**
     * append a new line
     *
     * @return Truss
     */
    public Truss appendln() {
        return newLine();
    }

    /**
     * append a String
     *
     * @param str
     * @return Truss
     */
    public Truss appendln(String str) {
        return append(str + NEW_LINE);
    }

    /**
     * append a CharSequence
     *
     * @param charSequence
     * @return Truss
     */
    public Truss appendln(CharSequence charSequence) {
        return append(charSequence + NEW_LINE);
    }

    /**
     * append a character
     *
     * @param c
     * @return Truss
     */
    public Truss appendln(char c) {
        return append(c + NEW_LINE);
    }

    /**
     * append a number
     *
     * @param number
     * @return Truss
     */
    public Truss appendln(int number) {
        return append(String.valueOf(number) + NEW_LINE);
    }

    /**
     * append a string with specified spans (Snippety)
     *
     * @param str
     * @param span
     * @return Truss
     */
    public Truss appendln(String str, Object span) {
        return append(str + NEW_LINE, span);
    }

    /**
     * append specified spans to a space
     * @param span
     * @return
     */
    public Truss appendln(Object span) {
        return append(" " + NEW_LINE, span);
    }

    /**
     * append a new line
     *
     * @return Truss
     */
    public Truss newLine() {
        return append(NEW_LINE);
    }

    /**
     * append a new paragraph
     *
     * @return Truss
     */
    public Truss newParagraph() {
        return newLine().newLine();
    }

    /**
     * append selective text(s) with delimiter
     *
     * @param fullText  entire string
     * @param delimiter delimiter encapsulating subText
     * @param span      properties
     * @return
     */
    public Truss appendDelimiterized(String fullText, String delimiter, Object span) {
        if (null == delimiter || "".equals(delimiter)) {
            delimiter = DEFAULT_DELIMITER;
        }
        int occurences = fullText.split(delimiter).length - 1;
        if (fullText.contains(delimiter) && occurences % 2 == 0) {
            String subText = fullText.substring(fullText.indexOf(delimiter) + 1, fullText.lastIndexOf(delimiter));
            fullText = fullText.replace(delimiter, "");
            int firstIndexOfSubtext = fullText.indexOf(subText);
            int lastIndexOfSubtext = firstIndexOfSubtext + subText.length();
            builder.append(fullText.substring(0, firstIndexOfSubtext));
            append(fullText.substring(firstIndexOfSubtext, lastIndexOfSubtext), span);
            builder.append(fullText.substring(lastIndexOfSubtext, fullText.length()));
        } else {
            append(fullText);
        }
        return this;
    }

    /**
     * append selective text(s) with delimiter and new line
     *
     * @param fullText  entire string
     * @param delimiter delimiter encapsulating subText
     * @param span      properties
     * @return
     */
    public Truss appendDelimiterizedln(String fullText, String delimiter, Object span) {
        return appendDelimiterized(fullText + NEW_LINE, delimiter, span);
    }

    /**
     * append selective text
     *
     * @param fullText entire string
     * @param subText  part of string
     * @param span     properties
     * @return
     */
    public Truss appendSelective(String fullText, String subText, Object span) {
        builder.append(fullText);
        if (fullText.contains(subText)) {
            int startIndex = fullText.indexOf(subText);
            int endIndex = startIndex + subText.length();
            if (span instanceof Snippety) {
                for (Object s : ((Snippety) span).getSpans()) {
                    setSpan(s, startIndex, endIndex);
                }
            } else {
                setSpan(span, startIndex, endIndex);
            }
        }
        return this;
    }

    /**
     * append selective text with new line
     *
     * @param fullText entire string
     * @param subText  part of string
     * @param span     properties
     * @return
     */
    public Truss appendSelectiveln(String fullText, String subText, Object span) {
        return appendSelective(fullText + NEW_LINE, subText, span);
    }

    /**
     * Starts {@code span} at the current position in the builder.
     */
    public Truss pushSpan(Object span) {
        stack.addLast(new Span(builder.length(), span));
        return this;
    }

    /**
     * End the most recently pushed span at the current position in the builder.
     */
    public Truss popSpan() {
        Span span = stack.removeLast();
        iterateSpans(span);
        return this;
    }

    /**
     * sets span on builder
     *
     * @param span  Snippety spans
     * @param start index of CharSequence
     */
    private void setSpan(Object span, int start) {
        setSpan(span, start, builder.length());
    }

    /**
     * sets span on builder
     *
     * @param span  Snippety spans
     * @param start index of CharSequence
     * @param end   index of CharSequence
     */
    private void setSpan(Object span, int start, int end) {
        builder.setSpan(span, start, end, SPAN_INCLUSIVE_EXCLUSIVE);
    }

    /**
     * Create the final {@link CharSequence}, popping any remaining span.
     */
    public CharSequence build() {
        while (!stack.isEmpty()) {
            popSpan();
        }
        return builder; // TODO make immutable copy?
    }

    /**
     * iterates spans and set them to builder
     *
     * @param span Snippety spans
     */
    private void iterateSpans(Span span) {
        if (span.span instanceof Snippety) {
            for (Object s : ((Snippety) span.span).getSpans()) {
                setSpan(s, span.start);
            }
        } else {
            setSpan(span.span, span.start);
        }
    }

    /**
     * Span class used in stack
     */
    private static final class Span {
        final Object span;
        final int start;

        public Span(int start, Object span) {
            this.start = start;
            this.span = span;
        }
    }
}