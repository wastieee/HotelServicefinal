package com.service.hotel.Controller;

import com.service.hotel.DTO.*;
import com.service.hotel.Entity.Hotel;
import com.service.hotel.Entity.Room;
import com.service.hotel.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getOne(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @DeleteMapping
    public ResponseEntity<Void> DeleteRoomById(@PathVariable Long id){
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomRequestDTO request) {
        return ResponseEntity.ok(roomService.update(id, request));
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomRequestDTO request) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.create(request));
    }
}
