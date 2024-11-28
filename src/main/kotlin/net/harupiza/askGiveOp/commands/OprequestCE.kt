package net.harupiza.askGiveOp.commands

import net.harupiza.askGiveOp.AskGiveOp
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OprequestCE : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, cs: String, ca: Array<out String>): Boolean {
        if (!sender.isOp && sender is Player) {
            sender.sendMessage("You don't have permission to use this command!")
            return false
        }
        val lists = ArrayList<String>()
        for (playeruuid in AskGiveOp.opRequests.keys) {
            val player = Bukkit.getPlayer(playeruuid)
            if (player != null) {
                lists.add("[${AskGiveOp.opRequests[playeruuid]?.size}] ${player.name} : ${AskGiveOp.opRequests[playeruuid]?.joinToString(", ")}")
            }
        }
        sender.sendMessage("""
            | OperatorRequest |
            |-----------------|
            ${lists.joinToString("\n")}
            [------] Command Help
            [Accept] /opaccept <username>
            [Deny  ] /opdeny <username>
        """.trimIndent())
        return true
    }
}