package ptit.example.btlwebbook.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {
        // Thêm số 0 vào ngày hoặc tháng nếu chỉ có một chữ số
        source = source.replaceAll("(?<=^|/)(\\d)$", "0$1");

        // Định dạng để chuyển thành LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(source, formatter);
    }
}
