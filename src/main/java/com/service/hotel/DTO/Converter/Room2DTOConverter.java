package com.service.hotel.DTO.Converter;
import com.service.hotel.DTO.RoomDTO;

import com.service.hotel.Entity.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class Room2DTOConverter implements Converter<Room, RoomDTO>  {
    @Override
    public RoomDTO convert(Room source) {
        if(source == null){
            return null;
        }
        return RoomDTO.builder().roomType(source.getRoomType()).roomId(source.getRoomId())
                .privateBathroom(source.getPrivateBathroom())
                .conditioner(source.getConditioner())
                .pricePerNight(source.getPricePerNight())
                .id(source.getId()).build();
    }
}
