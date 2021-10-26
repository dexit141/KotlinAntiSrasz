package lol.szymeko

import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import lol.szymeko.extensions.fixColors
import net.minecraft.server.v1_8_R3.Packet
import net.minecraft.server.v1_8_R3.PacketListener
import org.bukkit.entity.Player
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

open class PacketKotliniarzDecoder(player: Player) : MessageToMessageDecoder<Packet<PacketListener>>() {


    // mozna dac private ale po co D:
    /*private*/ val user: Player = player

    override fun decode(ctx: ChannelHandlerContext?, msg: Packet<PacketListener>, out: MutableList<Any>?) {
        if (ctx != null) {
            if (!ctx.channel().isOpen || ctx.pipeline() == null) return

            val packetName = msg.javaClass.simpleName;
            if (packetName.equals("PacketPlayInBlockPlace")) { // albo if (msg is PacketPlayInBlockPlace) chyba nwm
                user.sendMessage("&3kopiesz kobla".fixColors())
            } else {
                println("Jakis debil (${user.name}) wyslal pakiet $packetName")
            }
        }
    }

    fun inject() {
        val channel: Channel = (user as CraftPlayer).handle.playerConnection.networkManager.channel
        if (channel.pipeline() == null) return
        channel.pipeline().addAfter("decoder", "kotlin", this)
    }

    fun unInject() {
        val channel: Channel = (user as CraftPlayer).handle.playerConnection.networkManager.channel
        if (channel.pipeline() == null || channel.pipeline().get("kotlin") == null) return
        channel.pipeline().remove(this)
    }
}