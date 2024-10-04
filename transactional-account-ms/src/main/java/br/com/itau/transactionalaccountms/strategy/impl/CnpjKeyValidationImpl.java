package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyValidationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.strategy.KeyValidationStrategy;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CnpjKeyValidationImpl implements KeyValidationStrategy {

    private static final Set<String> INVALID_CNPJS = Set.of("00000000000000",
                                                            "11111111111111",
                                                            "22222222222222",
                                                            "33333333333333",
                                                            "44444444444444",
                                                            "55555555555555",
                                                            "66666666666666",
                                                            "77777777777777",
                                                            "88888888888888",
                                                            "99999999999999");

    public static final int ASC_II_0_CHARACTER = 48;

    @Override
    public KeyType getKeyType() {
        return KeyType.CNPJ;
    }

    @Override
    public void validate(final String key) {
        String cnpj = key.replace(".", "")
                         .replace("/", "")
                         .replace("-", "");
        if (cnpj.length() != 14 || INVALID_CNPJS.contains(key)) {
            throw new KeyValidationException("O CPF informado para cadastro é inválido.");
        }

        try {
            int firstStartingDigit = 11;
            char firstVerifierDigit = getVerifierDigit(cnpj, firstStartingDigit);

            int secondStartingDigit = 12;
            char secondVerifierDigit = getVerifierDigit(cnpj, secondStartingDigit);

            if (!(firstVerifierDigit == cnpj.charAt(12)) || !(secondVerifierDigit == cnpj.charAt(13))) {
                throw new KeyValidationException("O CNPJ informado não possui um formato válido.");
            }
        } catch (Exception erro) {
            throw new KeyValidationException("O CNPJ informado não possui um formato válido.");
        }
    }

    private char getVerifierDigit(final String key, int startingDigit) {
        int sum = 0;
        int digitMultiplier = 2;

        for (int i = startingDigit; i >= 0; i--) {
            int number = key.charAt(i) - ASC_II_0_CHARACTER;
            sum += number * digitMultiplier;
            digitMultiplier++;

            if (digitMultiplier == 10) {
                digitMultiplier = 2;
            }
        }

        int divisionRemainder = sum % 11;
        if (divisionRemainder == 0 || divisionRemainder == 1) {
            return '0';
        }
        return (char) (11 - divisionRemainder + ASC_II_0_CHARACTER);
    }
}
