package joe;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "serverjoemod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandHandler {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        registerJoeHelpCommand(event.getDispatcher());
    }

    private static void registerJoeHelpCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("joehelp")
                        .executes(context -> {
                            // 서버 규칙 메시지 전달
                            context.getSource().sendSuccess(() -> Component.literal("[joeminecraft] : 2024 연말 서버에 와주셔서 감사합니다!"), false);
                            context.getSource().sendSuccess(() -> Component.literal("[joeminecraft] : 크리에이티브 모드로 변경 혹은 OP권한 요구는 서버관리자에게 카톡으로 주세요!"), false);
                            context.getSource().sendSuccess(() -> Component.literal("[joeminecraft] : A. 무지성 pvp 금지. 서버 로그확인후 한 유저를 의도하게 많이죽일(2킬이상) 경우 밴"), false);
                            context.getSource().sendSuccess(() -> Component.literal("[joeminecraft] : B. TNT 금지를 하지는 않으나 테러가 발생할경우 즉시 차단 및 유저 밴"), false);
                            context.getSource().sendSuccess(() -> Component.literal("[joeminecraft] : C. 건축은 자유지만 활동반경은 서버가 소규모인것을 고려해 3만블럭 좌표 이하에서 (넘을시 철거 및 강제 텔레포트)"), false);
                            context.getSource().sendSuccess(() -> Component.literal("[joeminecraft] : etc. 해당 채팅 지우는것은 F3 + D , 다시보려면 //joehelp 작성!"), false);
                            return 1;
                        })
        );
    }
}
