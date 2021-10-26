package lol.szymeko

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin;

object PlayerListener : Listener {

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        val player = event.player;
        val packetDecoder = PacketKotliniarzDecoder(player);
        packetDecoder.inject();
    }

    @EventHandler
    fun playerQuit(event: PlayerQuitEvent) {
        val player = event.player;
        val packetDecoder = PacketKotliniarzDecoder(player);
        packetDecoder.unInject();
    }

}

class Main : JavaPlugin() {

    override fun onEnable() {
        println("GOWNO LOADED")
        server.pluginManager.registerEvents(PlayerListener, this)
    }

    override fun onDisable() {
        println("GOWNO Disabled")
    }
}
