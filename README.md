# ddd
'도메인 주도 설계 첫걸음' 책을 읽고 가상의 도메인을 DDD로 구현해본다.

# 울프데스크(WolfDesk)
헬프 데스크 티켓 관리 시스템을 서비스로 제공한다.
## 울프데스크 소개
울프데스크의 고객은 테넌트다. 시스템을 처음 이용할 때 테넌트는 간단한 온보딩 과정을 거치게 된다.
사용자 수 기반으로 과금되는 대신 부과 기간 동안 열린 티켓 수에 대해 과금한다.
티켓 수명주기 관리 알고리즘으로 활동이 없는 티켓을 자동으로 닫고, 도움이 필요할 때 새로운 티켓을 열도록 독려한다.
추가로 테넌트가 울프데스크를 악용하지 못하도록 오용된 티켓을 탐지할 수 있는 부정 방지 시스템을 구현했다.
