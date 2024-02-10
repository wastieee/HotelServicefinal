//package com.service.hotel.Service;
//
//import com.service.hotel.DTO.UserRentRequestDTO;
//import com.service.hotel.Entity.Room;
//import com.service.hotel.Entity.User;
//import com.service.hotel.Entity.UserRent;
//import com.service.hotel.Repository.RoomRepository;
//import com.service.hotel.Repository.UserRentRepository;
//import com.service.hotel.Repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class UserRentService {
//
//    private final UserRentRepository userRentRepository;
//    private final UserRepository userRepository;
//    private final RoomRepository roomRepository;
//
//    public Long apply(Long roomId, UserRentRequestDTO request) {
//        User user = userRepository.getReferenceById(request.getUserId());
//        Room room = roomRepository.getReferenceById(roomId);
//
//        boolean isRoomAvailable = userRentRepository.isRoomAvailable(roomId, request.getStart(), request.getFinish());
//        if (!isRoomAvailable) {
//            throw new RuntimeException("Комната уже занята в указанные даты.");
//        }
//        UserRent entity = new UserRent();
//        entity.setUser(user);
//        entity.setRoom(room);
//        entity.setStartRentDate(request.getStart());
//        entity.setEndRentDate(request.getFinish());
//
//        return userRentRepository.save(entity).getId();
//    }
//}
//