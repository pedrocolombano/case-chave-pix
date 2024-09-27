package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyRegistrationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.strategy.KeyValidationStrategy;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Set;

@Service
public class CpfKeyValidationImpl implements KeyValidationStrategy {

    private static final Set<String> INVALID_CPFS = Set.of("00000000000",
                                                           "11111111111",
                                                           "22222222222",
                                                           "33333333333",
                                                           "44444444444",
                                                           "55555555555",
                                                           "66666666666",
                                                           "77777777777",
                                                           "88888888888",
                                                           "99999999999");

    public static final int ASC_II_0_CHARACTER = 48;

    @Override
    public KeyType getKeyType() {
        return KeyType.CPF;
    }

    @Override
    public void validate(final String key) {
        String cpf = key.replace(".", "").replace("-", "");
        if (cpf.length() != 11 || INVALID_CPFS.contains(key)) {
            throw new KeyRegistrationException("O CPF informado para cadastro é inválido");
        }
        try {
            int firstMultiplierDigit = 10;
            char firstDigit = getVerifierDigit(key, firstMultiplierDigit);

            int secondMultiplierDigit = 11;
            char secondDigit = getVerifierDigit(key, secondMultiplierDigit);

            if (!(firstDigit == key.charAt(9)) && !(secondDigit == key.charAt(10))) {
                throw new KeyRegistrationException("O CPF informado não é válido.");
            }
        } catch (InputMismatchException erro) {
            throw new KeyRegistrationException("O CPF informado não possui um formato válido.");
        }
    }

    public char getVerifierDigit(final String key, final int digitMultiplier) {
        int sum = 0;
        int finalMultiplier = digitMultiplier;
        int divisionRemainer = 0;

        for (int i = 0; i < 9; i++) {
            int number = key.charAt(i) - ASC_II_0_CHARACTER;
            sum = sum + (number * digitMultiplier);
            finalMultiplier = finalMultiplier - 1;
        }

        divisionRemainer = 11 - (sum % 11);
        if (divisionRemainer == 10 || divisionRemainer == 11) {
            return '0';
        }
        return (char) (divisionRemainer + ASC_II_0_CHARACTER);
    }
}
