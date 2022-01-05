package payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @SequenceGenerator(name = "payment_id_sequence",
            sequenceName = "payment_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "payment_id_sequence")
    private Integer id;
    private String amount;

}
