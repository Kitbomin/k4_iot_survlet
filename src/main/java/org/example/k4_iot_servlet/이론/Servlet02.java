package org.example.k4_iot_servlet.이론;

// === 서블릿 동작 흐름 (요청 ~ 응답) === //
// : 웹 서버(서블릿) 와 서블릿 컨테이너 (톰캣 등)가 협력하여 클라이언트의 요청이 들어오고, 응답을 반환하기까지 일어나는 모든 과정

/*
* 1. 전체 흐름 요약
*
* 클라이언트 요청 -> 웹서버 -> 서블릿 컨테이너 -> 필터(전처리) -> 서블릿 요청 처리 (doGet, doPost) -> 필터(후처리) -> 응답 반환 -> 클라이언트에게 전달
* */

/**
 *  1) 클라이언트가 요청을 보냄
 *      - 브라우저에서 'Http://localhost:8080/hello' 같은 URL로 요청
 *      - 요청은 HTTP 프로토콜 사용 + GET/POST/PUT/DELETE 등의 메서드와 함께 전송
 *
 *  2) 웹 서버가 요청을 받음
 *      - 톰캣(Tomcat) 과 같은 웹 서버(서블릿 컨테이너 포함) 가 8080 포트에서 대기 중인 상태임
 *          >> 요청을 수신
 *
 *  3) 서블릿 컨테이너가 요청을 처리
 *      - 요청 URL을 보고 web.xml 또는 어노테이션(@WebServlet)을 통해 어떤 서블릿이 처리할지를 결정함
 *          >> 이 때 서블릿이 없으면 404 NOT FOUND 응답이 반환됨
 *
 *  cf) 리스너(Listener)
 *      - 서블릿 컨테이너가 동작하면서 특정 이벤트 발생 시 실행되는 객체
 *  cf) 필터(Filter) -> 전처리/ 후처리 담당
 *      - 공통 인증 처리, 로깅, 인코딩 설정, 요청 데이터 가공 등 (체인방식 - 여러 개 연결 가능)
 *
 *  4) 서블릿 초기화 (최초 요청 시)
 *      - 요청을 처리할 서블릿이 처음 호출되면 init() 메서드 실행: 초기화
 *      - 이후 요청부터는 재사용
 *          >> DB 연결 등 초기 설정 담당
 *
 *  5) 서블릿이 요청을 처리(service -> doGet/doPost)
 *      - 클라이언트 요청이 오면 service() 메서드가 자동 호출
 *      - service()가 HTTP 메서드에 따라 doGet() - READ 또는 doPost() - CREATE,UPDATE,DELETE 호출
 *          cf) 모든 CRUD 작업이 doPost()에서 사용이 가능하긴 함 -> 권장 x
 *
 *  6) 응답 데이터 생성
 *      - HttpServletResponse 객체를 통해 HTML, JSON 등을 작성해 클라이언트에게 반환
 *      - response.setContentType(), response.getWriter().write() 등을 사용
 *
 *  7) 웹 서버가 클라이언트에게 응답 전송
 *      - 후처리 필터까지 모두 끝나면 HttpServletResponse 내용을 Http 응답 형태로 변환해 브라우저에 전달함
 *
 *  8) 서블릿 종료 (destroy)
 *      - 서버가 종료되거나 서블릿 제거 시 destroy()가 호출됨
 */



public class Servlet02 {

}
