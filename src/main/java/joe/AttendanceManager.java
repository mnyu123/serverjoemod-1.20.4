package joe;

import net.minecraft.server.level.ServerPlayer;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 서버 출석을 메모장에 저장하고 , 불러오는 내용
 */
public class AttendanceManager {
    private static final String ATTENDANCE_FOLDER = "attendance_data";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public Set<LocalDate> loadAttendanceData(String uuid) {
        Path attendancePath = getAttendanceFilePath(uuid);
        Set<LocalDate> dates = new HashSet<>();

        if (Files.exists(attendancePath)) {
            try (BufferedReader reader = Files.newBufferedReader(attendancePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LocalDate date = LocalDate.parse(line, DATE_FORMAT);
                    dates.add(date);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dates;
    }

    public void saveAttendanceData(String uuid, Set<LocalDate> dates) {
        Path attendancePath = getAttendanceFilePath(uuid);

        try {
            Files.createDirectories(attendancePath.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(attendancePath)) {
                for (LocalDate date : dates) {
                    writer.write(date.format(DATE_FORMAT));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getAttendanceFilePath(String uuid) {
        return Paths.get("world", ATTENDANCE_FOLDER, uuid + ".txt");
    }

    public int getAttendanceDays(ServerPlayer player) {
        String uuid = player.getUUID().toString();
        Set<LocalDate> dates = loadAttendanceData(uuid);
        LocalDate today = LocalDate.now();

        if (!dates.contains(today)) {
            dates.add(today);
            saveAttendanceData(uuid, dates);
        }

        return dates.size();
    }

    public int getTotalServerDays() {
        // 서버 시작일(11/22일 처음 시작)
        LocalDate serverStartDate = LocalDate.of(2024, 11, 22);
        return (int) ChronoUnit.DAYS.between(serverStartDate, LocalDate.now().plusDays(1));
    }
}
