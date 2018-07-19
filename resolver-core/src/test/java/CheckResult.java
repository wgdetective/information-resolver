import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
@Data
public class CheckResult {
    /*
    "Передача параметров через аргументы: - Maven: - Интерфейс программы соответствует условию: - Прохождение тестов 4/5: 1. + 2. + 3. + 4. + 5. - Ошибки в коде: Чистота / понятность кода: выше среднего Комментарии:"
     */
    private int args;
    private int maven;
    private int interf;
    private int passedTests;
    private List<Integer> tests = new ArrayList<>();
    private String cleanCode;
}
