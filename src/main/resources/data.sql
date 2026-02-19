INSERT INTO flights (
    id,
    flight_number,
    departure_airport,
    arrival_airport,
    departure_time,
    arrival_time,
    company_code,
    flight_status,
    flight_distance_miles
) VALUES
      (
          '38b966d2-9372-4ed1-ba04-be7d90dfa3de',
          'JU201',
          'BEG',
          'FRA',
          '2026-02-10T08:30:00+01:00',
          '2026-02-10T10:45:00+01:00',
          'JU',
          'OPEN_FOR_RESERVATIONS',
          620
      ),
      (
          '8dbccc69-ce58-42f1-83f1-4cd4c23625b7',
          'LH1411',
          'FRA',
          'MAD',
          '2026-02-11T12:15:00+01:00',
          '2026-02-11T15:05:00+01:00',
          'LH',
          'OPEN_FOR_RESERVATIONS',
          870
      ),
      (
          '356b349d-93db-451c-93ee-8e8aebb8f7f1',
          'AF1360',
          'CDG',
          'AMS',
          '2026-02-12T09:00:00+01:00',
          '2026-02-12T10:30:00+01:00',
          'AF',
          'OPEN_FOR_RESERVATIONS',
          260
      ),
      (
          '827e4fae-dfec-47dd-ac2a-68708dfc17de',
          'EK202',
          'DXB',
          'JFK',
          '2026-02-13T02:10:00+04:00',
          '2026-02-13T08:15:00-05:00',
          'EK',
          'OPEN_FOR_RESERVATIONS',
          6830
      ),
      (
          '855ded52-0c5c-401f-a66a-436bbd0db7c0',
          'W64012',
          'BEG',
          'BCN',
          '2026-02-14T18:45:00+01:00',
          '2026-02-14T21:05:00+01:00',
          'W6',
          'OPEN_FOR_RESERVATIONS',
          940
      );

INSERT INTO flight_seats (
    flight_id,
    flight_seat_number,
    flight_seat_status,
    ticket_order_id
) VALUES
-- Flight 1
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','1A','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','1B','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','1C','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','1D','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','2A','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','2B','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','2C','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','2D','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','3A','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','3B','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','3C','AVAILABLE',NULL),
('38b966d2-9372-4ed1-ba04-be7d90dfa3de','3D','AVAILABLE',NULL),

-- Flight 2
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','1A','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','1B','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','1C','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','1D','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','2A','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','2B','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','2C','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','2D','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','3A','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','3B','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','3C','AVAILABLE',NULL),
('8dbccc69-ce58-42f1-83f1-4cd4c23625b7','3D','AVAILABLE',NULL),

-- Flight 3
('356b349d-93db-451c-93ee-8e8aebb8f7f1','1A','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','1B','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','1C','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','1D','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','2A','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','2B','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','2C','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','2D','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','3A','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','3B','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','3C','AVAILABLE',NULL),
('356b349d-93db-451c-93ee-8e8aebb8f7f1','3D','AVAILABLE',NULL),

-- Flight 4
('827e4fae-dfec-47dd-ac2a-68708dfc17de','1A','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','1B','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','1C','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','1D','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','2A','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','2B','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','2C','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','2D','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','3A','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','3B','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','3C','AVAILABLE',NULL),
('827e4fae-dfec-47dd-ac2a-68708dfc17de','3D','AVAILABLE',NULL),

-- Flight 5
('855ded52-0c5c-401f-a66a-436bbd0db7c0','1A','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','1B','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','1C','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','1D','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','2A','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','2B','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','2C','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','2D','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','3A','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','3B','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','3C','AVAILABLE',NULL),
('855ded52-0c5c-401f-a66a-436bbd0db7c0','3D','AVAILABLE',NULL);
