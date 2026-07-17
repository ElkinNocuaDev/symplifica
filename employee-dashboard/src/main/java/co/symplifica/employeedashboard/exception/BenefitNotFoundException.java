package co.symplifica.employeedashboard.exception;

public class BenefitNotFoundException extends RuntimeException {

    public BenefitNotFoundException(Long id) {
        super("Benefit with id " + id + " not found");
    }

}