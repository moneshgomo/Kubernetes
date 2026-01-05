package gomo.dev.Calculator.controller;

import gomo.dev.Calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CalculatorController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody Map<String, String> payload) {
        logger.info("Received calculation request: {}", payload);
        try {
            double a = Double.parseDouble(payload.get("a"));
            double b = Double.parseDouble(payload.get("b"));
            String operation = payload.get("operation");

            double result = calculatorService.calculate(a, b, operation);
            logger.info("Calculation result: {}", result);
            return ResponseEntity.ok(Map.of("result", result));

        } catch (NumberFormatException e) {
            logger.error("Invalid number format in request: {}", payload, e);
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid numbers"));
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok(Map.of("message", "Welcome to Spring Boot Calculator!"));
    }
}
