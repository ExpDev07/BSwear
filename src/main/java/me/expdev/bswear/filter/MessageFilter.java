package me.expdev.bswear.filter;

public interface MessageFilter {

    /**
     * Filters the message
     *
     * @param message Message to filter
     * @return Filtered message
     */
    String filter(String message);

}
