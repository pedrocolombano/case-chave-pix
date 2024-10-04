package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyValidationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.strategy.KeyValidationStrategy;
import org.springframework.stereotype.Service;

@Service
public class PhoneKeyValidationImpl implements KeyValidationStrategy {

    private static final String COUNTRY_CODE_REGEX = "\\d{1,2}";
    private static final String DDD_REGEX = "\\d{2,3}";
    private static final String NUMBER_REGEX = "\\d{9}";

    @Override
    public KeyType getKeyType() {
        return KeyType.PHONE;
    }

    @Override
    public void validate(final String key) {
        if (!key.startsWith("+")) {
            throw new KeyValidationException("O número do telefone deve iniciar com o caractere +.");
        }

        String phoneNumber = key.replace("+", "");
        if (phoneNumber.length() < 13) {
            throw new KeyValidationException("O número do telefone deve possuir 13 ou 14 caracteres.");
        }

        String countryCode = phoneNumber.substring(0, 2);
        validateCountryCode(countryCode);

        String ddd = phoneNumber.length() == 13 ? phoneNumber.substring(2, 4) : phoneNumber.substring(2, 5);
        validateDdd(ddd);

        String number = phoneNumber.length() == 13 ? phoneNumber.substring(4)
                                                   : phoneNumber.substring(5);
        validatePhoneNumber(number);
    }

    private void validateCountryCode(final String countryCode) {
        if (!countryCode.matches(COUNTRY_CODE_REGEX)) {
            throw new KeyValidationException("O código do país deve ser numérico e possuir 2 dígitos.");
        }
    }

    private void validateDdd(final String ddd) {
        if (!ddd.matches(DDD_REGEX)) {
            throw new KeyValidationException("O DDD deve ser numérico e possuir até 3 dígitos.");
        }
    }

    private void validatePhoneNumber(final String phoneNumber) {
        if (!phoneNumber.matches(NUMBER_REGEX)) {
            throw new KeyValidationException("O número do telefone deve ser numérico e possuir 9 dígitos.");
        }
    }
}
