package malek.jerbi.bank.account.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BankAccountEntity {
    private int bankAccountId;
    private float balance;
    private List<String> statements = new ArrayList<>();

}
