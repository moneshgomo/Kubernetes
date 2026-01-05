package gomo.dev.Calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final gomo.dev.Calculator.repository.CalculationRepository calculationRepository;

    public CalculatorService(gomo.dev.Calculator.repository.CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public double calculate(double a, double b, String operation) {
        double result = switch (operation) {
            case "add" -> a + b;
            case "subtract" -> a - b;
            case "multiply" -> a * b;
            case "divide" -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Invalid operation");
        };

        gomo.dev.Calculator.model.Calculation calculation = new gomo.dev.Calculator.model.Calculation(a, b, operation,
                result);
        calculationRepository.save(calculation);

        return result;
    }
}
