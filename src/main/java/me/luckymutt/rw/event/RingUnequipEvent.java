package me.luckymutt.rw.event;

import me.luckymutt.rw.Ring;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RingUnequipEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private final Ring ring;
    private boolean isCancelled;


    public RingUnequipEvent(Player player, Ring ring) {
        this.player = player;
        this.ring = ring;
        this.isCancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public Ring getRing() {
        return ring;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }
}
