package net.harupiza.askGiveOp.commands

import net.harupiza.askGiveOp.AskGiveOp
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpdenyCE : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, cs: String, ca: Array<out String>): Boolean {
        if (!sender.isOp && sender is Player) {
            sender.sendMessage("You don't have permission to use this command!")
            return false
        }
        if (ca.size == 1) {
            val player = Bukkit.getPlayer(ca[0])
            if (player != null && AskGiveOp.opRequests.contains(player.uniqueId)) {
                AskGiveOp.opRequests.remove(player.uniqueId)
                sender.sendMessage("You have denied ${player.name}'s request")
            } else {
                sender.sendMessage("That's player or Requests does not exists")
            }
        } else {
            sender.sendMessage("Argument does not exists")
            return false
        }
        return true
    }
}