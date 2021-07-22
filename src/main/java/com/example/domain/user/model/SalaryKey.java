package com.example.domain.user.model;

import java.io.Serializable;
import lombok.Data;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class SalaryKey implements Serializable {
    private String userId;
    private String yearMonth;
}
