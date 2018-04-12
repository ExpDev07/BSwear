import me.expdev.bswear.Filters;
import me.expdev.bswear.filter.CapsFilter;
import me.expdev.bswear.filter.GrammarFilter;
import me.expdev.bswear.filter.SwearFilter;
import net.md_5.bungee.api.event.ChatEvent;

import java.io.File;
import java.io.IOException;

public class CharTest {

    public static void main(String[] args) throws IOException {
        Filters filters = new Filters();

        SwearFilter swearFilter = new SwearFilter("test lol").populate(new File(
                "src/main/resources/dictionary.csv")
        );

        // Apply the filters!
        filters.add(swearFilter);
        filters.add(new CapsFilter());
        filters.add(new GrammarFilter());

        ChatEvent event = new ChatEvent(null, null, "does this. work?haha");
        filters.handleEvent(event);

        System.out.println(event.getMessage());
    }

}
