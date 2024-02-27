package com.accounts.response.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
// we should add these two annotations if we use builder for DTOs
// Fixing the errors: no Creators, like default construct, exist
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
	
	private String cif;
	private String firstName;
    private String lastName;
    private String email;
	private Date createdAt;
	private Date upldatedAt;
	private AccountResponse accountResponse;
	
}
