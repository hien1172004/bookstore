package ptit.example.btlwebbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Configuration
public class DateTimeConfig {
    @Bean
    public FormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy")) // Chấp nhận 09/05/2025
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))   // Chấp nhận 9/5/2025
                .toFormatter();
        registrar.setDateFormatter(formatter);
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}
