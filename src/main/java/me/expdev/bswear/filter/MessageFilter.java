package me.expdev.bswear.filter;

public interface MessageFilter {

    /**
     * Filters the message
     *
     * @param message Message to filter
     * @return Filtered message
     */
    String filter(String message);

    /**
     * Gets priority of filter. LOWEST (handled first) -> HIGHEST (final say in outcome)
     * <b>Note: use MONITOR if you do not wish to change outcome, this has absolute final say</b>
     *
     * @return Priority of filter
     */
    FilterPriority getPriority();

    /**
     * @return Name of filter
     */
    default String getName() {
        return this.getClass().getName();
    }

    /**
     * Priorities for handling filters
     */
    enum FilterPriority {

        LOW, LOWEST, NORMAL, HIGH, HIGHEST, MONITOR

    }

}
