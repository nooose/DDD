package wolfdesk.member.application.command

import org.springframework.stereotype.Service
import wolfdesk.member.domain.Member
import wolfdesk.member.domain.MemberRepository

@Service
class MemberCommandService(
    private val memberRepository: MemberRepository
) {

    fun register(command: MemberJoinCommand) {
        check(memberRepository.existsByEmailIs(command.email)) {
            "${command.email} - 이미 존재하는 이메일입니다."
        }

        val member = Member(
            email = command.email,
            password = command.password,
            name = command.name,
        )
        memberRepository.save(member)
    }
}
