//package com.service.hotel.Repository;
//
//import com.service.hotel.Entity.Room;
//import com.service.hotel.Entity.User;
//import com.service.hotel.Entity.UserRent;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//
//@Repository
//public interface UserRentRepository extends JpaRepository<UserRent, Long> {
//
//    void deleteByUserAndRoom(User user, Room room);
//
//    boolean isRoomAvailable(Long roomId, LocalDate start, LocalDate finish);
//}
//