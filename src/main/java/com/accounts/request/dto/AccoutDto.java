package com.accounts.request.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
// we should add these two annotations if we use builder for DTOs
@NoArgsConstructor
@AllArgsConstructor
public class AccoutDto {

    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal openingBalance;

}
