//Старинные часы бьют каждые полчаса. Причем в начале каждого часа они бьют столько раз,
// сколько сейчас часов (по 1 разу – в час ночи и в час дня, по 2 раза –
// в два часа ночи в два часа дня и т.д., в полночь и в полдень они бьют, соответственно, по 12 раз).
// И еще 1 раз они бьют в середине каждого часа.
//Дан промежуток времени. Известно, что прошло строго меньше 24 часов
// (но при этом могли начаться новые сутки). Напишите программу, определяющую,
// сколько ударов сделали часы за это время.
//Формат ввода:
//В первой строке вводится начальный момент времени, во второй строке — конечный.
// Моменты времени задаются двумя целыми числами, разделяющимися пробелом.
// Первое число задает часы (от 0 до 23), второе — минуты (от 1 до 59, при этом оно не равно 30).
//Формат вывода:
//Выведите одно число — сколько ударов сделали часы за этот отрезок времени.
//Примечание:
//Заметьте, что время в задаче задается в 24-часовой форме записи, в то время как часы показывают
// (и соответственно, бьют) в 12-часовом режиме (т.е., например, в 13 часов часы бьют 1 раз).
//Ограничение по времени: 1 секунда.
//Ограничение по памяти: 256 мегабайт.
import java.util.Scanner;

public class ClockStrikes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод начального времени
        int[] startTime = getTimeInput(scanner, "Введите начальное время (часы минуты):");
        int startHour = startTime[0];
        int startMinute = startTime[1];

        // Ввод конечного времени
        int[] endTime = getTimeInput(scanner, "Введите конечное время (часы минуты):");
        int endHour = endTime[0];
        int endMinute = endTime[1];

        // Здесь будет вызов метода, который подсчитывает удары
        int strikes = calculateStrikes(startHour, startMinute, endHour, endMinute);

        System.out.println("Количество ударов: " + strikes);
    }

    private static int[] getTimeInput(Scanner scanner, String prompt) {
        int hour, minute;
        while (true) {
            System.out.println(prompt);
            hour = scanner.nextInt();
            minute = scanner.nextInt();
            if (isValidTime(hour, minute)) {
                return new int[]{hour, minute};
            } else {
                System.out.println("Ошибка: Введите корректное время (0-23 для часов и 0-59 для минут).");
            }
        }
    }

    private static boolean isValidTime(int hour, int minute) {
        return (hour >= 0 && hour < 24) && (minute >= 0 && minute < 60);
    }

    private static int calculateStrikes(int startHour, int startMinute, int endHour, int endMinute) {
        int totalStrikes = 0;

        // Время в минутах
        int startTimeInMinutes = startHour * 60 + startMinute;
        int endTimeInMinutes = endHour * 60 + endMinute;

        // Обработка перехода через 24 часа
        if (endTimeInMinutes < startTimeInMinutes) {
            endTimeInMinutes += 24 * 60; // Добавляем 24 часа в минуты
        }

        // Подсчет ударов
        for (int currentTime = startTimeInMinutes; currentTime < endTimeInMinutes; currentTime++) {
            int hour = (currentTime / 60) % 24; // Получаем часы (0-23)
            int minute = currentTime % 60; // Получаем минуты (0-59)

            // Учитываем удары в начале каждого часа
            if (minute == 0) {
                int strikesForHour = (hour % 12 == 0 ? 12 : hour % 12); // Количество ударов в зависимости от часа
                totalStrikes += strikesForHour;
            }

            // Учитываем удары в полчаса
            if (minute == 30) {
                totalStrikes += 1; // Удар в полчаса
            }
        }

        return totalStrikes;
    }
}
