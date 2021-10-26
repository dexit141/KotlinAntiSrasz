package lol.szymeko.extensions

import net.md_5.bungee.api.ChatColor

fun String.fixColors(): String {
    return ChatColor.translateAlternateColorCodes('&', this)
}