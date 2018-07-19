import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Wladimir Litvinov
 */
public class ParseFile {

    @Test
    public void test() throws IOException {
        final FileReader fileReader = new FileReader(
                "/Users/wgdetective/sources/information-resolver/resolver-core/src/test/java/Проверка кандидатов.csv");
        final String text = IOUtils.toString(fileReader);
        final String[] split = text.split("\n");
        int i = 1;
        try (final FileWriter fw = new FileWriter("res.csv", false)) {
            for (String s : split) {
                String[] line = s.replace(String.valueOf('\u2028'), "").split("\t");
                i++;
                if (line.length > 1 && !line[1].isEmpty()) {
                    final StringJoiner joiner = new StringJoiner("\t");
                    joiner.add(String.valueOf(i));
                    joiner.add(line[0]);
                    final CheckResult ch = getCheckResult(line[1]);
                    joiner.add(String.valueOf(ch.getArgs() * 1));
                    //joiner.add(String.valueOf(ch.getMaven()));
                    joiner.add(String.valueOf(ch.getInterf() * 10));
                    joiner.add(String.valueOf(getPassedTests(ch)));
                    joiner.add(String.valueOf(getCleanCode(ch)));
//                    for (int t : ch.getTests()) {
//                        joiner.add(String.valueOf(t));
//                    }
                    fw.append(joiner.toString()).append("\n");
                    fw.flush();
                } else {
                    System.out.println(s);
                }
            }
        }
    }

    private int getPassedTests(final CheckResult ch) {
        final List<Integer> t = ch.getTests();
        return t.get(0) + t.get(1) * 4 + t.get(2) + t.get(3) + t.get(4) * 2;
    }

    private int getCleanCode(final CheckResult ch) {
        switch (ch.getCleanCode()) {
            case "высокая":
                return 3;
            case "выше среднего":
                return 2;
            case "средняя":
                return 1;
            case "среднее":
                return 1;
            case "ниже среднего":
                return 0;
            default:
                System.out.println(ch.getCleanCode());
                throw new NullPointerException();

        }
    }

    private CheckResult getCheckResult(final String line) {
        System.out.println(line);
        final CheckResult result = new CheckResult();
        final String[] split = line.split(":");
        result.setArgs(getValue(split, 1, 1));
        result.setMaven(getValue(split, 2, 1));
        result.setInterf(getValue(split, 3, 1));
        final String passedTests = split[3].substring(split[3].length() - 3, split[3].length() - 2);
        result.setPassedTests(Integer.parseInt(passedTests));
        result.setCleanCode(split[6].substring(1, split[6].indexOf("Комментарии")));
        for (int i = 0; i < 5; i++) {
            result.getTests().add(getValue(split, 4, ((i + 1) * 4) - 1));
        }
        /*
        0 = "Передача параметров через аргументы"
        1 = " - Maven"
        2 = " - Интерфейс программы соответствует условию"
        3 = " - Прохождение тестов 4/5"
        4 = " 1. + 2. + 3. + 4. + 5. - Ошибки в коде"
        5 = " Чистота / понятность кода"
        6 = " выше среднего Комментарии"
         */
        return result;
    }

    private int getValue(final String[] a, final int i, final int c) {
        final char charAt = a[i].charAt(c);
        if (charAt == '-') {
            return 0;
        } else if (charAt == '+') {

            return 1;
        }
        throw new NullPointerException(a[i] + " " + c);
    }
}
