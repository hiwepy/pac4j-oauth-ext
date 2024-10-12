package org.pac4j.util;

public class MyCommonHelper {

    /**
     * Build a "nice toString" for an object.
     *
     * @param clazz class
     * @param args  arguments
     * @return a "nice toString" text
     */
    public static String toNiceString(final Class<?> clazz, final Object... args) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(clazz.getSimpleName());
        sb.append("# |");
        boolean b = true;
        for (final Object arg : args) {
            if (b) {
                sb.append(" ");
                sb.append(arg);
                sb.append(":");
            } else {
                sb.append(" ");
                sb.append(arg);
                sb.append(" |");
            }
            b = !b;
        }
        return sb.toString();
    }
}
