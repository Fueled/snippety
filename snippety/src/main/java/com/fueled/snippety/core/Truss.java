package com.fueled.snippety.core;

import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.util.Log;

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

    public Truss append(String string) {
        builder.append(string);
        return this;
    }

    public Truss appendln(String string) {
        builder.append(string + NEW_LINE);
        return this;
    }

    public Truss append(String s, Object span) {
        stack.addLast(new Span(builder.length(), span));
        builder.append(s);
        return popSpan();
    }

    public Truss appendln(String s, Object span) {
        stack.addLast(new Span(builder.length(), span));
        builder.append(s + NEW_LINE);
        return popSpan();
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
        Log.d(TAG, "appendDelimiterized: " + occurences);
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

        int lastIndex = 0;
        int occurences = fullText.split(subText).length - 1;

        for (int i = 0; i < occurences; i++) {
            int startIndex = fullText.indexOf(subText, lastIndex);
            int endIndex = startIndex + subText.length();

            if (span instanceof Snippety) {
                for (Object s : ((Snippety) span).getSpans()) {
                    setSpan(s, startIndex, endIndex);
                }
            } else {
                setSpan(span, startIndex, endIndex);
            }
            lastIndex = endIndex;
        }

//        if (fullText.contains(subText)) {
//            int startIndex = fullText.indexOf(subText);
//            int endIndex = startIndex + subText.length();
//
//            if (span instanceof Snippety) {
//                for (Object s : ((Snippety) span).getSpans()) {
//                    setSpan(s, startIndex, endIndex);
//                }
//            } else {
//                setSpan(span, startIndex, endIndex);
//            }
//        }
        return this;
    }

    /**
     * Apply span on a specific substring within the text.
     *
     * @param span      The span object to be applied.
     * @param subString The sub string to apply the span on.
     * @return Current instance of Truss.
     */
    public Truss applySpan(@NonNull String subString, Object span) {
        String fullText = builder.toString();

        if (fullText.contains(subString)) {
            int startIndex = fullText.indexOf(subString);
            int endIndex = startIndex + subString.length();

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

        if (span.span instanceof Snippety) {
            for (Object s : ((Snippety) span.span).getSpans()) {
                setSpan(s, span.start);
            }
        } else {
            setSpan(span.span, span.start);
        }

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