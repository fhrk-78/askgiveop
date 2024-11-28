package net.harupiza.askGiveOp

import net.harupiza.askGiveOp.commands.OpacceptCE
import net.harupiza.askGiveOp.commands.OpdenyCE
import net.harupiza.askGiveOp.commands.OprequestCE
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

class AskGiveOp : JavaPlugin() {
    companion object {
        var opMember = ArrayList<UUID>()
        var opRequests = HashMap<UUID, ArrayList<UUID>>()

        var minop = 1
    }

    override fun onEnable() {
        saveDefaultConfig()

        minop = config.getInt("minop")

        server.operators.forEach {
            player -> opMember.add(player.uniqueId)
        }

        getCommand("oprequest")?.setExecutor(OprequestCE())
        getCommand("opaccept")?.setExecutor(OpacceptCE())
        getCommand("opdeny")?.setExecutor(OpdenyCE())

        Bukkit.getScheduler().runTaskTimer(getPlugin(this.javaClass), Runnable {
            server.onlinePlayers.forEach {
                player -> if (player.isOp && !opMember.contains(player.uniqueId)) {
                    player.sendMessage("OP Request is sent.")
                    player.isOp = false
                    if (!opRequests.contains(player.uniqueId)) opRequests[player.uniqueId] = ArrayList()
            }
            }
        }, 0, 5)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
