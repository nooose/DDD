package wolfdesk.app.ticket.command.application

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.data.repository.findByIdOrNull
import wolfdesk.app.EventRecords
import wolfdesk.app.IntegrationTest
import wolfdesk.app.ticket.command.domain.MessageCreatedEvent
import wolfdesk.app.ticket.command.domain.TicketCreatedEvent
import wolfdesk.app.ticket.command.domain.TicketRepository
import wolfdesk.app.truncate

@IntegrationTest
class TicketServiceTest(
    private val service: TicketService,
    private val repository: TicketRepository,
    private val events: EventRecords,
) : DescribeSpec({

    describe("티켓 기능") {
        val command = TicketCreateCommand("티켓 제목", "티켓내용", 1)

        context("티켓을 저장하면") {
            service.create(command, 1)

            it("티켓 생성 이벤트가 발행된다.") {
                val event = events.first<TicketCreatedEvent>()
                event.ticket.id shouldNotBe 0
            }
        }

        context("티켓을 여러개 저장하면") {
            service.create(command, 1)
            service.create(command, 1)

            it("티켓 전체를 조회할 수 있다.") {
                val tickets = repository.findAll()
                tickets.size shouldBe 2
            }
        }

        context("티켓을 생성하고 메시지를 추가하면") {
            service.create(command, 1)
            service.addMessage(1, MessageCreateCommand("테스트 메시지"), 1)

            it("메시지가 저장되고 추가 메시지 이벤트가 발행된다.") {
                val ticket = repository.findByIdOrNull(1)!!
                val event = events.first<MessageCreatedEvent>()

                assertSoftly {
                    event.message shouldNotBe 0
                    ticket.messages.size shouldBe 1
                }
            }
        }
    }

    afterEach {
        repository.truncate()
        events.clear()
    }
})
