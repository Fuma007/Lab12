package se331.lab12.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab12.entity.Event;
import se331.lab12.entity.Organizer;
import se331.lab12.entity.Participant;
import se331.lab12.repository.EventRepository;
import se331.lab12.repository.OrganizerRepository;
import se331.lab12.repository.ParticipantRepository;
import jakarta.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder()
                .name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder()
                .name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder()
                .name("ChiangMai").build());

        Event event1, event2, event3, event4;

        event1 = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petsAllowed(false)
                .build());
        event1.setOrganizer(org1);
        org1.getOwnEvents().add(event1);

        event2 = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petsAllowed(false)
                .build());
        event2.setOrganizer(org1);
        org1.getOwnEvents().add(event2);

        event3 = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petsAllowed(false)
                .build());
        event3.setOrganizer(org2);
        org2.getOwnEvents().add(event3);

        event4 = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petsAllowed(true)
                .build());
        event4.setOrganizer(org3);
        org3.getOwnEvents().add(event4);

        Participant p1 = Participant.builder()
                .name("John Smith")
                .telNo("081-111-1111")
                .build();
        p1.getEventHistories().add(event1);
        p1.getEventHistories().add(event2);
        p1.getEventHistories().add(event3);
        participantRepository.save(p1);

        Participant p2 = Participant.builder()
                .name("Son Goku")
                .telNo("081-222-2222")
                .build();
        p2.getEventHistories().add(event2);
        p2.getEventHistories().add(event3);
        p2.getEventHistories().add(event4);
        participantRepository.save(p2);

        Participant p3 = Participant.builder()
                .name("Joseph Joestar")
                .telNo("081-333-3333")
                .build();
        p3.getEventHistories().add(event3);
        p3.getEventHistories().add(event4);
        p3.getEventHistories().add(event1);
        participantRepository.save(p3);

        Participant p4 = Participant.builder()
                .name("Kujo Jotaro")
                .telNo("081-444-4444")
                .build();
        p4.getEventHistories().add(event1);
        p4.getEventHistories().add(event2);
        participantRepository.save(p4);

        Participant p5 = Participant.builder()
                .name("Johnny Joestar")
                .telNo("081-555-5555")
                .build();
        p5.getEventHistories().add(event4);
        p5.getEventHistories().add(event2);
        participantRepository.save(p5);
    }
}