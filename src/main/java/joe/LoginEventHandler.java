package joe;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

/**
 * 유저가 서버에 접속할때 메시지를 보내는 메소드
 */
public class LoginEventHandler {
    // 출석 관리 클래스 불러오기
    private final AttendanceManager attendanceManager = new AttendanceManager();

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // 서버에 들어온 유저 정보 가져오기
        ServerPlayer player = (ServerPlayer) event.getEntity();
        // 시스템 메시지로 환영 메시지 전달
        player.sendSystemMessage(Component.literal("[joeminecraft] : 2024 연말 서버에 와주셔서 감사합니다!"));
        player.sendSystemMessage(Component.literal("[joeminecraft] : 크리에이티브 모드로 변경 혹은 OP권한 요구는 서버관리자에게 카톡으로 주세요!"));
        player.sendSystemMessage(Component.literal("[joeminecraft] : A. 무지성 pvp 금지. 서버 로그확인후 한 유저를 의도하게 많이죽일(2킬이상) 경우 밴"));
        player.sendSystemMessage(Component.literal("[joeminecraft] : B. TNT 금지를 하지는 않으나 테러가 발생할경우 즉시 차단 및 유저 밴"));
        player.sendSystemMessage(Component.literal("[joeminecraft] : C. 건축은 자유지만 활동반경은 서버가 소규모인것을 고려해 3만블럭 좌표 이하에서 (넘을시 철거 및 강제 텔레포트)"));
        player.sendSystemMessage(Component.literal("[joeminecraft] : etc. 해당 채팅 지우는것은 F3 + D"));

        int attendanceDays = attendanceManager.getAttendanceDays(player); // 유저 출석일
        int totalServerDays = attendanceManager.getTotalServerDays(); // 총 서버 가동일
        double attendanceRate = (attendanceDays / (double) totalServerDays) * 100; // 출석률 계산

        // 출석 메시지 생성
        String attendanceMessage = String.format("현재 당신의 출석일 수 : %d/%d (%.2f%% 출석함.)", attendanceDays, totalServerDays, attendanceRate);
        // 시스템 메시지로 출석 메시지 전달
        player.sendSystemMessage(Component.literal(attendanceMessage));
    }
}
