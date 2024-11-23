package joe;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

/**
 * 모드 빌드법 : 터미널 -> ./gradlew build
 * -> build/libs 폴더에 .jar 확장자 파일 생김
 * 그걸 복사하고 서버 재시작(사실 재시작 안해도됨) 해서 아무튼 모드 로드시키셈
 */

/**
 * 서버 사이드에서만 모드 동작하고 싶으면 , mods.toml 파일 수정
 */

/**
 * forge 서버의 메인 클래스
 */
@Mod("serverjoemod")
public class ServerMod {
    public ServerMod() {
        // 생성자 왜만들지?
        // todo : 모드 초기화 코드를 넣으래요
        // 1. Forge 이벤트를 쓰기 위해서 '이벤트 버스'에 등록
        MinecraftForge.EVENT_BUS.register(new LoginEventHandler());
        MinecraftForge.EVENT_BUS.register(new SaveAllScheduler());
    }
}
