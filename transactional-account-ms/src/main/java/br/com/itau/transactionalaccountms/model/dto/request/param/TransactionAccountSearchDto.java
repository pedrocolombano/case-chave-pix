package br.com.itau.transactionalaccountms.model.dto.request.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionAccountSearchDto {

    private UUID id;
    private String keyType;
    private Integer branch;
    private Integer number;
    private String holderName;

    public boolean isIdInvalid() {
        return StringUtils.isNotEmpty(keyType)
                || branch != null
                || number != null
                || StringUtils.isNotEmpty(holderName);
    }

}
