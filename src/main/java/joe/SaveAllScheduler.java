package joe;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * 10분에 한번씩 서버 변경내용 저장하는 메소드
 */
@EventBusSubscriber
public class SaveAllScheduler {
    private static int tickCounter = 0;
    private static final int TICKS_PER_SAVE = 36000; // 20틱 * 60초 * 30분

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        tickCounter++;
        if (tickCounter >= TICKS_PER_SAVE) {
            event.getServer().getCommands().performPrefixedCommand(
                    event.getServer().createCommandSourceStack(),
                    "save-all"
            );
            tickCounter = 0;
        }
    }
}
