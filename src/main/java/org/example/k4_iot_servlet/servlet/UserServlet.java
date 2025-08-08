package org.example.k4_iot_servlet.servlet;

/*
 * 1. 서블릿
 * : 자바로 만들어진 웹 프로그래밍 도구
 * - 동적 웹 페이지를 만들 때 사용되는 자바 기반 웹 애플리케이션 기술
 * - 웹 요청과 응답의 흐름을 메서드 호출만으로 체계적인 설계를 담당
 *
 * 2. 서블릿 컨테이너
 * : 구현된 Servlet 클래스의 규칙에 맞게 서블릿을 담고 관리해주는 컨테이너
 * - 클라이언트에서 요청하면 컨테이너는 HttpServletRequest, HttpServletResponse
 *       두 객체를 생성
 *       >> post, get 여부에 따라 동적 페이지 생성 후 응답 전송
 * */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.k4_iot_servlet.dao.UserDao;
import org.example.k4_iot_servlet.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/") // 서블릿 실행 초기 페이지 URL
// 서블릿 URL 매핑을 위한 어노테이션
// : 루트 경로("/")에 매핑되어 모든 요청을 받아 처리
public class UserServlet extends HttpServlet { // 서블릿 컨테이너가 해당 클래스를 서블릿으로 인지!
    private UserDao userDao; // 사용자 데이터 처리를 위한 DAO 객체

    public void init() {
        // 서블릿 생성 시 단 한번만 호출
        userDao = new UserDao();
    }

    // GET 요청 처리 메서드


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 현재 요청한 경로(URL 패턴)를 가져옴
        String action = req.getServletPath();

        try {
            // URL 경로에 따라 서로 다른 메서드 호출
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;

                case "/insert":
                    insertUser(req, resp);
                    break;

                case "/edit":
                    showEditForm(req, resp);
                    break;

                case "/update":
                    updateUser(req, resp);
                    break;

                case "/delete":
                    deleteUser(req, resp);
                    break;

                case "/list":
                    listUser(req, resp);
                    break;

                default:
                    listUser(req, resp);
                    // 기본 동작 - 사용자 목록 출력
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // 1. 새 사용자 입력 폼을 보여주는 메서드
    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException
    {
        // 사용자 입력 폼 페이지로 요청 전달
        // : 서버 내부에서 화면 전환
        // RequestDispatcher - 요청을 다른 자원(JSP, 서블릿 등)으로 넘기는 객체
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/user-form.jsp");
        dispatcher.forward(req, resp);
    }

    // 2. 새로운 사용자 정보를 DB에 삽입하는 메서드
    private void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException
    {
        // 요청 파라미터에서 name, email, country 값 가져오기
        // : req.getParameter(String 속성명);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        User newUser = new User(0, name, email, country);

        userDao.insertUser(newUser);
        resp.sendRedirect("list"); // "/list"로 이동 - 클라이언트에게 다른 페이지로 이동을 명령
    }

    // 3. 기존 사용자 정보 수정 폼을 보여주는 메서드
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException
    {
        // URL 파라미터에서 전달된 사용자 id 가져오기
        int id = Integer.parseInt(req.getParameter("id"));

        User existingUser = userDao.selectUserById(id);

        req.setAttribute("user", existingUser); // JSP에서 user라는 이름으로 데이터 사용

        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/user-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        User user = new User(id, name, email, country);

        userDao.updateUser(user);

        resp.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException
    {
        int id = Integer.parseInt(req.getParameter("id"));

        userDao.deleteUser(id);

        resp.sendRedirect("list");
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException
    {
        List<User> listUser = userDao.selectAllUsers();

        req.setAttribute("listUser", listUser);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/user-list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // POST 요청 처리 메서드 (POST 요청을 GET 요청처럼 처리)
        doGet(req, resp);
    }

    // ================================= //



}