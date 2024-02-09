package com.service.hotel.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private String roomType;
    private String conditioner;
    private String privateBathroom;
    private String roomId;
    private Integer pricePerNight;
}
