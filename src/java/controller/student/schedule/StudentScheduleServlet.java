package controller.student.schedule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.classes.ClassesDTO;
import model.enrol.EnrolDAO;
import model.scheduleSlots.ScheduleSlotsDAO;
import model.scheduleSlots.ScheduleSlotsDTO;
import model.user.UserDTO;

public class StudentScheduleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        
        if (user == null || !"SV".equalsIgnoreCase(user.getRoleID().trim())) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        String userID = user.getUserID().trim();
        EnrolDAO enrolDAO = new EnrolDAO();
        ScheduleSlotsDAO scheduleDAO = new ScheduleSlotsDAO();
        
        try {
            // Get all enrolled classes
            List<ClassesDTO> classes = enrolDAO.getClassesByUser(userID);
            
            // Map: "Day-Slot" -> List of Class IDs (e.g., "Mon-1" -> ["SE1730", ...])
            Map<String, List<String>> scheduleMap = new HashMap<>();
            
            if (classes != null) {
                for (ClassesDTO c : classes) {
                    List<ScheduleSlotsDTO> slots = scheduleDAO.getScheduleByClass(c.getClassID());
                    if (slots != null) {
                        for (ScheduleSlotsDTO slot : slots) {
                            String dayStr = getDayString(slot.getSessionDate().toLocalDate());
                            int slotNumber = getSlotNumber(slot.getStartTime());
                            
                            if (dayStr != null && slotNumber > 0) {
                                String key = dayStr + "-" + slotNumber;
                                scheduleMap.putIfAbsent(key, new ArrayList<>());
                                if (!scheduleMap.get(key).contains(c.getClassID())) {
                                    scheduleMap.get(key).add(c.getClassID());
                                }
                            }
                        }
                    }
                }
            }
            
            request.setAttribute("SCHEDULE_MAP", scheduleMap);
            request.getRequestDispatcher("schedule.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("student/MyClasses.jsp").forward(request, response);
        }
    }
    
    private String getDayString(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case MONDAY: return "Mon";
            case TUESDAY: return "Tue";
            case WEDNESDAY: return "Wed";
            case THURSDAY: return "Thu";
            case FRIDAY: return "Fri";
            case SATURDAY: return "Sat";
            case SUNDAY: return "Sun";
            default: return null;
        }
    }
    
    private int getSlotNumber(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        
        if (hour == 7) return 1; // 07:30
        if (hour == 9) return 2; // 09:50
        if (hour == 12) return 3; // 12:50
        if (hour == 15) return 4; // 15:10
        if (hour == 17) return 5; // 17:30
        return 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
