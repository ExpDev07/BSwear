# BSwear
A plugin made exclusively for Marcoo. 

**Order:**
* **Version:** BungeeCord 1.12-SNAPSHOT
* **Made:** 10.04.2018
* **By:** Marcoo
* **Paid:** 10 EUR
* **Completed:** 11.04.2018

**Description:** A simple yet innovative anti-swear plugin which introduces filters you can add to the chat.

**Default filters:**
1. **SwearFilter:** uses a dictionary to remove bad words and replaces them with stars (*).
2. **CapsFilter:** makes a word lower case if it exceeds the allowed use of capital letters in one word.
3. **GrammarFilter:** fixes some common grammatical errors. For example: not using capital letter after punctuations.

**Adding your own filter:** ([TestFilter.java](src/test/java/TestFilter.java))
```java
import me.expdev.bswear.BSwearPlugin;
import me.expdev.bswear.filter.MessageFilter;

/**
 * You will need to implement the interface "me.expdev.bswear.filter.MessageFilter"
 */
public class TestFilter implements MessageFilter {

    /**
     * MessageFilter has one method: #filter(String message)
     */
    @Override
    public String filter(String message) {
        // Make message all lower case and build a StringBuilder
        message = message.toLowerCase();
        StringBuilder result = new StringBuilder();

        // Loop through characters and make every second character uppercase
        // Flag to make char capital
        boolean capital = false;
        for (char c : message.toCharArray()) {
            // We want to ignore non-alphabetic characters
            if (!Character.isAlphabetic(c)) {
                // Just append character and continue
                result.append(c);
                continue;
            }

            // Translate character into a string
            String asString = String.valueOf(c);
            if (capital) {
                asString = asString.toUpperCase();
                capital = false;
            } else capital = true;

            // Append to result!
            result.append(asString);
        }

        // Return edited/filtered message!
        return result.toString();
    }

    /**
     * Provide a priority for this filter. Should it have a final say
     * in the outcome? Use HIGHEST or MONITOR (monitor should not modify outcome, just analyze).
     */
    @Override
    public FilterPriority getPriority() {
        return FilterPriority.HIGHEST;
    }

    /**
     * Optionally, you can also provide a name
     */
    @Override
    public String getName() {
        return "test_filter by ExpDev";
    }

    /**
     * Executed when program is started
     */
    public static void main() {
        // Get filters and add your new filter!
        BSwearPlugin.getPlugin().getFilters().add(new TestFilter());

        // That's all! Now it will apply it to all messages sent in chat
        // Input = "doEs this. Work? HAHA"
        // Output = "dOeS tHiS. wOrK? hAhA"
    }

}
```