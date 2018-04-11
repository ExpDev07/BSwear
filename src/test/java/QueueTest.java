import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class QueueTest {

    public static void main(String[] args) {
        // Put all players in a list and shuffle it to make it "random"
        List<ProxiedPlayer> list = new ArrayList<ProxiedPlayer>(ProxyServer.getInstance().getPlayers());
        Collections.shuffle(list);

        // Create  queue :O
        Queue<ProxiedPlayer> queue = new ArrayDeque<ProxiedPlayer>(list);

        // Now you can simply call #poll, and it will automatically remove whatever you retrieved
        // #Poll: "Retrieves and removes the head of this queue, or returns null if this queue is empty."
        ProxiedPlayer player1 = queue.poll();
        ProxiedPlayer player2 = queue.poll();
    }

}
