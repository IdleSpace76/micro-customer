package ru.micro.customer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author a.zharov
 */
@Data
@NoArgsConstructor
public class Health {
    private HealthStatus status;
}
