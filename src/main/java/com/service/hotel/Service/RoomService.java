package com.service.hotel.Service;

import com.service.hotel.DTO.*;
import com.service.hotel.DTO.Converter.Room2DTOConverter;
import com.service.hotel.Entity.Room;
import com.service.hotel.Exceptions.NotFoundException;
import com.service.hotel.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    private final Room2DTOConverter room2DTOConverter;

    @Transactional(readOnly = true)
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream().map(room2DTOConverter::convert).toList();
    }

    @Transactional(readOnly = true)
    public RoomDTO getOne(final Long id) {
        return roomRepository.findById(id).map(room2DTOConverter::convert).orElseThrow(() -> new NotFoundException("Номер с таким айди " + id + " не найден!"));

    }

    @Transactional
    public void delete(final long id){
        roomRepository.deleteById(id);
    }

    @Transactional
    public RoomDTO create(final RoomRequestDTO request) {
        Room room = fillOrUpdateRoomEntity(request, new Room());
        return room2DTOConverter.convert(roomRepository.save(room));
    }
    @Transactional
    public RoomDTO update(final Long id, final RoomRequestDTO request) {
        Room existingRoom = fillOrUpdateRoomEntity(request, roomRepository.getReferenceById(id));

        return room2DTOConverter.convert(roomRepository.save(existingRoom));
    }

    private static Room fillOrUpdateRoomEntity(RoomRequestDTO request, Room room){
        room.setRoomType(request.getRoomType());
        room.setConditioner(request.getConditioner());
        room.setPrivateBathroom(request.getPrivateBathroom());
        room.setRoomId(request.getRoomId());
        room.setPricePerNight(request.getPricePerNight());

        return room;
    }

}
