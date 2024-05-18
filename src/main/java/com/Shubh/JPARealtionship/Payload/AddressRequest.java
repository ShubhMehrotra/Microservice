package com.Shubh.JPARealtionship.Payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotEmpty(message = "Street door No cannot be empty")
    private String door_No;
    @NotEmpty(message = "Street Name cannot be empty")
    private String street_Name;
    @NotEmpty(message = "City name cannot be empty")
    private String city;
    @NotEmpty(message = "Pin Code cannot be empty")
    private String pin_Code;
    public boolean isValid() {
        return door_No != null && !door_No.isEmpty()
                && street_Name!=null && !street_Name.isEmpty()
                && city!=null && !city.isEmpty()
                && pin_Code!=null && !pin_Code.isEmpty();
    }


}