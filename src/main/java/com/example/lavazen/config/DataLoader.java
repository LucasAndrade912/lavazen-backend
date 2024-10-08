package com.example.lavazen.config;

import com.example.lavazen.models.*;
import com.example.lavazen.repositories.CarWashBookingRepository;
import com.example.lavazen.repositories.UserRepository;
import com.example.lavazen.repositories.WashingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final WashingRepository washingRepository;
    private final CarWashBookingRepository carWashBookingRepository;

    public DataLoader(
            UserRepository userRepository,
            WashingRepository washingRepository,
            CarWashBookingRepository carWashBookingRepository
    ) {
        this.userRepository = userRepository;
        this.washingRepository = washingRepository;
        this.carWashBookingRepository = carWashBookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Washing simpleWashing = new Washing(
                "Lavagem simples",
                "Inclui lavagem da carroceria com água, detergente automotivo e esponja, limpeza das rodas e vidros, além da aspiração do interior (bancos e carpetes). Alguns locais também incluem aplicação de cera rápida ou silicone nos plásticos.",
                Duration.ofMinutes(60),
                50.00
        );

        Washing completeWashing = new Washing(
                "Lavagem completa",
                "Envolve uma lavagem mais detalhada da carroceria, rodas, e para-brisas, além de uma limpeza profunda no interior, como estofados, painéis e vidros internos. Pode incluir a limpeza do motor (dependendo do serviço) e enceramento para dar mais brilho e proteção à pintura.",
                Duration.ofMinutes(120),
                120.00
        );

        Washing dryCleaning = new Washing(
                "Lavagem a seco",
                "Utiliza produtos biodegradáveis aplicados com borrifador e removidos com panos de microfibra, que dissolvem e removem a sujeira de forma eficaz sem necessidade de enxágue.",
                Duration.ofMinutes(60),
                70.00
        );

        Washing waxWashing = new Washing(
                "Lavagem com cera",
                "Envolve uma lavagem externa detalhada, seguida da aplicação de cera líquida ou em pasta na carroceria. A cera é aplicada com uma esponja ou pano e, depois de seca, é removida com uma flanela para dar brilho e proteger a pintura contra raios UV e sujeira.",
                Duration.ofMinutes(120),
                150.00
        );

        User user1 = new User("Lucas Andrade", "lucas@email.com", new BCryptPasswordEncoder().encode("lucas12345"), LocalDate.of(2003, 10, 17), "Rua João Pessoa, 999", "83911223344", UserRole.EMPLOYEE);
        User user2 = new User("Johnner Yelcde", "johnner@email.com", new BCryptPasswordEncoder().encode("johnner12345"), LocalDate.of(2003, 10, 10), "Rua João Pessoa, 556", "83955667788", UserRole.CUSTOMER);

        CarWashBooking booking1 = new CarWashBooking("BMW", "ASDASD", PaymentMethod.PIX, LocalDate.of(2024, 10, 1), LocalTime.of(10, 30), user1, simpleWashing);
        CarWashBooking booking2 = new CarWashBooking("BMW", "ASDVF", PaymentMethod.PIX, LocalDate.of(2024, 10, 6), LocalTime.of(11, 0), user1, completeWashing);
        CarWashBooking booking3 = new CarWashBooking("BMW", "QWEWV", PaymentMethod.CASH, LocalDate.of(2024, 10, 4), LocalTime.of(8, 0), user2, waxWashing);

        if (this.userRepository.count() == 0) {
            this.userRepository.save(user1);
            this.userRepository.save(user2);
        }

        if (this.washingRepository.count() == 0) {
            this.washingRepository.save(simpleWashing);
            this.washingRepository.save(completeWashing);
            this.washingRepository.save(dryCleaning);
            this.washingRepository.save(waxWashing);
        }

        if (this.carWashBookingRepository.count() == 0) {
            this.carWashBookingRepository.save(booking1);
            this.carWashBookingRepository.save(booking2);
            this.carWashBookingRepository.save(booking3);
        }
    }
}
