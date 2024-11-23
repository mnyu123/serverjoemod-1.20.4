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
        // 서버에 들어온 유저 정보 가져오고
        ServerPlayer player = (ServerPlayer) event.getEntity();
        // 바로 시스템 메시지로 매크로 메시지 전달
        player.sendSystemMessage(Component.literal("[joeminecraft] : 2024 연말 서버에 와주셔서 감사합니다!"));

        int attendanceDays = attendanceManager.getAttendanceDays(player); // 유저 출석일
        int totalServerDays = attendanceManager.getTotalServerDays(); // 총 서버가동일
        double attendanceRate = (attendanceDays / (double) totalServerDays) * 100; // 유저출석일/서버가동일 %로 보여주는 수식

        // 계산해서 얼마나 출석했는지 체크
        String attendanceMessage = String.format("현재 당신의 출석일 수 : %d%d (%.2f%% 출석함.)", attendanceDays, totalServerDays, attendanceRate);
        // 방금 String 내용을 시스템 메시지로 다시 뿌리기
        player.sendSystemMessage(Component.literal(attendanceMessage));
    }
}
