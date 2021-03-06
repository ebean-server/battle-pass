package io.github.battlepass.quests.quests.internal;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import io.github.battlepass.BattlePlugin;
import io.github.battlepass.quests.QuestExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillMobQuest extends QuestExecutor {

    public KillMobQuest(BattlePlugin plugin) {
        super(plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityKill(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        if (entity instanceof Player) {
            return;
        }
        String stringEntity = entity.getType().toString().replace("Craft", "");
        int entityAmount = 1;
        if (Bukkit.getPluginManager().isPluginEnabled("WildStacker")) {
            entityAmount = WildStackerAPI.getEntityAmount(event.getEntity());
        }
        this.execute("kill-mob", player, entityAmount, result -> result.root(stringEntity), replacer -> replacer.set("entity", stringEntity));
    }
}
