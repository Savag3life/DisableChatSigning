package me.savag3.chat;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.proxy.connection.client.ConnectedPlayer;

import java.lang.reflect.Field;
import java.util.logging.Logger;

@Plugin(id = "disable-chat-signatures", name = "DisableChatSignatures", version = "1.0.0", description = "Disables Minecraft's Forced Chat Signatures", authors = { "Savag3life" })
public class DisableChatSignatures {

    public static final String IDENTIFIED_KEY_FIELD = "playerKey";

    private final Logger logger;

    @Inject
    public DisableChatSignatures(Logger logger) {
        this.logger = logger;
    }

    @Subscribe
    public void playerConnectToNetworkEvent(ServerConnectedEvent e) {
        if (!e.getPreviousServer().isEmpty()) return;
        ConnectedPlayer player = (ConnectedPlayer) e.getPlayer();
        try {
            Field f = ConnectedPlayer.class.getDeclaredField(IDENTIFIED_KEY_FIELD);
            f.setAccessible(true);
            f.set(player, null);
            this.logger.info("Modified " + player.getUsername() + " to disabled chat signatures.");
        } catch (NoSuchFieldException | IllegalAccessException er) {
            er.printStackTrace();
        }
    }
}

