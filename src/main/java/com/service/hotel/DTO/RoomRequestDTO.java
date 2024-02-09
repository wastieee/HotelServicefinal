package com.service.hotel.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoomRequestDTO {
    public String roomType;
    public String conditioner;
    public String privateBathroom;
    private String roomId;
    private Integer pricePerNight;
}
