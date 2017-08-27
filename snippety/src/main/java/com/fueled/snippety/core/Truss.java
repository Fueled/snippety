package com.fueled.snippety.core;

import android.text.SpannableStringBuilder;

import java.util.ArrayDeque;
import java.util.Deque;

import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;

/**
 * A {@link SpannableStringBuilder} wrapper whose API doesn't make me want to stab my eyes out.
 *
 * @author Jake Warton
 */

public class Truss {

    public static final String NEW_LINE = "\n";
    private static final String DEFAULT_DELIMITER = "`";
    private final SpannableStringBuilder builder;
    private final Deque<Span> stack;
    private static final String TAG = "Truss";

    public Truss() {
        builder = new SpannableStringBuilder();
        stack = new ArrayDeque<>();
    }

    public Truss append(String str) {
        builder.append(str);
        return this;
    }

    public Truss appendln(String str) {
        builder.append(str + NEW_LINE);
        return this;
    }

    public Truss appendln() {
        builder.append(NEW_LINE);
        return this;
    }

    public Truss append(String str, Object spans) {
        Span span = new Span(builder.length(), spans);
        builder.append(str);
        iterateSpans(span);
        return this;
    }

    private void iterateSpans(Span span) {
        if (span.span instanceof Snippety) {
            for (Object s : ((Snippety) span.span).getSpans()) {
                setSpan(s, span.start);
            }
        } else {
            setSpan(span.span, span.start);
        }
    }

    public Truss appendln(String str, Object span) {
        return append(str + NEW_LINE, span);
    }

    public Truss append(CharSequence charSequence) {
        builder.append(charSequence);
        return this;
    }

    public Truss append(char c) {
        builder.append(c);
        return this;
    }

    public Truss append(int number) {
        builder.append(String.valueOf(number));
        return this;
    }

    public Truss newLine() {
        return append(NEW_LINE);
    }

    public Truss newParagraph() {
        return newLine().newLine();
    }

    /**
     * Starts {@code span} at the current position in the builder.
     */
    public Truss pushSpan(Object span) {
        stack.addLast(new Span(builder.length(), span));
        return this;
    }

    /**
     * append selective text(s) with delimiter
     *
     * @param fullText
     * @param span
     * @param delimiter
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
            append(fullText.substring(0, firstIndexOfSubtext));
            append(fullText.substring(firstIndexOfSubtext, lastIndexOfSubtext), span);
            append(fullText.substring(lastIndexOfSubtext, fullText.length()));
        } else {
            append(fullText);
        }
        return this;
    }

    /**
     * append selective text
     *
     * @param fullText
     * @param subText
     * @param span
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
     * End the most recently pushed span at the current position in the builder.
     */
    public Truss popSpan() {
        Span span = stack.removeLast();
        iterateSpans(span);
        return this;
    }

    private void setSpan(Object span, int start) {
        setSpan(span, start, builder.length());
    }

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

    private static final class Span {
        final int start;
        final Object span;

        public Span(int start, Object span) {
            this.start = start;
            this.span = span;
        }
    }
}