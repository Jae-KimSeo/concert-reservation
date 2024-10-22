package com.project.concert_reservation.application.concert.service;

import com.project.concert_reservation.ConcertReservationApplication;
import com.project.concert_reservation.domain.concert.entity.*;
import com.project.concert_reservation.domain.concert.model.*;
import com.project.concert_reservation.domain.concert.port.*;
import com.project.concert_reservation.mapper.concert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ConcertReservationApplication.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(ConcertServiceTest.class);

    @InjectMocks
    private ConcertService concertService;

    @Spy
    private ConcertRepository concertRepository;
    @Spy
    private PlaceRepository placeRepository;
    @Spy
    private ScheduleRepository scheduleRepository;
    @Spy
    private SeatRepository seatRepository;
    @Spy
    private ReservationRepository reservationRepository;

    @Spy
    private ConcertMapper concertMapper;
    @Spy
    private PlaceMapper placeMapper;
    @Spy
    private ScheduleMapper scheduleMapper;
    @Spy
    private SeatMapper seatMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        ConcertEntity concertEntity = new ConcertEntity();
        concertEntity.setId(1L);
        concertEntity.setName("test-concert");
        when(concertRepository.findConcertById(1L)).thenReturn(Optional.of(concertEntity));

        List<PlaceEntity> places = new ArrayList<>();
        PlaceEntity placeOne = new PlaceEntity();
        placeOne.setId(1L);
        placeOne.setName("Seoul");
        placeOne.setCapacity(30);

        PlaceEntity placeTwo = new PlaceEntity();
        placeTwo.setId(2L);
        placeTwo.setName("Busan");
        placeTwo.setCapacity(50);

        places.add(placeOne);
        places.add(placeTwo);

        when(placeRepository.findPlaceById(1L)).thenReturn(places);

        List<ScheduleEntity> schedules = new ArrayList<>();
        ScheduleEntity scheduleOne = new ScheduleEntity();
        scheduleOne.setConcertId(1L);
        scheduleOne.setPlaceId(1L);
        scheduleOne.setReserveOpenAt(LocalDateTime.of(2024, 8, 4, 12, 0));
        scheduleOne.setReserveCloseAt(LocalDateTime.of(2024, 8, 5, 12, 0));
        scheduleOne.setEventDate(LocalDateTime.of(2024, 8, 6, 12, 0));

        ScheduleEntity scheduleTwo = new ScheduleEntity();
        scheduleTwo.setConcertId(1L);
        scheduleTwo.setPlaceId(1L);
        scheduleTwo.setReserveOpenAt(LocalDateTime.of(2024, 8, 4, 12, 0));
        scheduleTwo.setReserveCloseAt(LocalDateTime.of(2024, 8, 5, 12, 0));
        scheduleTwo.setEventDate(LocalDateTime.of(2024, 8, 8, 12, 0));

        schedules.add(scheduleOne);
        schedules.add(scheduleTwo);

        when(scheduleRepository.findScheduleByConcertAndPlace(1L, 1L)).thenReturn(schedules);

        List<SeatEntity> seats = new ArrayList<>();
        SeatEntity seatOne = new SeatEntity();
        seatOne.setId(1L);
        seatOne.setScheduleId(1L);
        seatOne.setPrice(100000L);

        SeatEntity seatTwo = new SeatEntity();
        seatTwo.setId(2L);
        seatTwo.setScheduleId(1L);
        seatTwo.setPrice(80000L);

        seats.add(seatOne);
        seats.add(seatTwo);

        when(seatRepository.findSeatById(1L)).thenReturn(Optional.of(seatOne));
        when(seatRepository.findSeatById(2L)).thenReturn(Optional.of(seatTwo));
        when(seatRepository.findSeatByScheduleId(1L)).thenReturn(seats);

        ReservationEntity reservation = new ReservationEntity();
        reservation.setId(1L);
        reservation.setHolderId(1L);
        reservation.setSeatId(1L);

        when(reservationRepository.findReservationById(1L)).thenReturn(Optional.of(reservation));

        logger.info("Setup complete with dummy data");
    }

    @Test
    @DisplayName("콘서트 정보 호출 성공 테스트")
    public void testGetConcertInfo() {
        Concert concert = concertService.getConcertInfo(1L);
        assertEquals("test-concert", concert.getName());
        logger.info("Test passed : Get concert info succeed");
    }

    @Test
    @DisplayName("콘서트 장소 정보 호출 성공 테스트")
    public void testGetPlaceInfo() {
        List<Place> places = concertService.getPlacesInfo(1L);
        assertEquals("Seoul", places.getFirst().getName());
        assertEquals("Busan", places.get(1).getName());
        logger.info("Test Passed : Get place info succeed");
    }

    @Test
    @DisplayName("콘서트 스케줄 정보 호출 성공 테스트")
    public void testGetScheduleInfo() {
        List<Schedule> schedules = concertService.getSchedulesInfo(1L, 1L);
        assertEquals(schedules.getFirst().getEventDate(), LocalDateTime.of(2024, 8, 6, 12, 0));
        assertEquals(schedules.get(1).getEventDate(), LocalDateTime.of(2024, 8, 8, 12, 0));
        logger.info("Test passed : Get schedule info succeed");
    }

    @Test
    @DisplayName("콘서트 좌석 정보 호출 성공 테스트")
    public void testGetSeatsInfo(){
        List<Seat> seats = concertService.getSeatsInfo(1L);
        assertEquals(seats.getFirst().getPrice(), 100000L);
        assertEquals(seats.get(1).getPrice(), 80000L);
        logger.info("Test passed : Get seats info succeed");
    }

    @Test
    @DisplayName("콘서트 예약 성공 테스트")
    public void testMakeReservation_Success(){
        Reservation reservation = concertService.makeReservation(1L, 1L);
        assertNull(reservation.getPaidAt());
        assertTrue(reservation.isReserved());
        logger.info("Test passed : Make reservation succeed and payment is unfinished");
    }

    @Test
    @DisplayName("콘서트 예약 실패 테스트")
    public void testMakeReservation_Fail(){
        Reservation reservation = concertService.makeReservation(1L, 3L);
        assertNull(reservation);
        logger.info("Test passed : Requested Seat is couldn't be able to make reservation");
    }
}
