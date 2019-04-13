package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Consultation;
import dk.patientsystemet.demo.Repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ConsultationRepository db;

    @Autowired
    Validate val;

    /**
     Return a list of all consultations by patient ID
     */

    public List<Consultation> getConsultations(int id) {
        ResultSet rs = db.findConsultations(id);
        List<Consultation> consultationList = new ArrayList<>();
        try {
            while (rs.next()) {
                Consultation consultation = new Consultation();
                consultation.setPatientId(rs.getInt("patient.id"));
                consultation.setId(rs.getInt("consultation.id"));
                consultation.setDescription(rs.getString("consultation.description"));
                consultation.setConclusion("consultation.conclusion");
                consultation.setDate(rs.getString("consultation.date"));
                consultation.setTime(rs.getString("consultation.time"));
                consultationList.add(consultation);
            }
            return consultationList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Delete a consultation
     */

    public String deleteConsultation(int id, HttpSession session) {
        if (session.getAttribute("role").equals("Doctor")) {
            db.deleteConsultation(id);
            return "Success";
        }
        return "Error";
    }

    /**
     Create a consultation
     */

    public String createConsultation(Consultation consultation) {
        if(val.biggerOrEqualToNumber(consultation.getDescription(), 1)) {
            return "Fill out description";
        } else {
            db.createConsultation(consultation);
            return "Success";
        }

    }

    /**
     Edit consultation
     */

    public String editConsultation(Consultation consultation) {
        if(val.biggerOrEqualToNumber(consultation.getDescription(), 1)) {
            return "Fill out description";
        } else if(val.biggerOrEqualToNumber(consultation.getConclusion(), 1)) {
            return "Fill out conclusion";
        } else {
            db.editConsultation(consultation);
            return "Success";
        }
    }

    /**
     Return a list of all upcoming consultations by doctor ID (Session ID)
     */

    public List<Consultation> upcomingConsultations(int userId, HttpSession session) {
        ResultSet rs;
        if (session.getAttribute("role").equals("Doctor")) {
            rs = db.upcomingConsultations(userId);
        }  else {
            rs = db.fetchAll();
        }
        List<Consultation> ucList = new ArrayList<>();
        try {
            while (rs.next()) {
                Consultation consultation = new Consultation();
                consultation.setId(rs.getInt("consultation.id"));
                consultation.setPatientName(rs.getString("patient.firstname"));
                consultation.setDate(rs.getString("consultation.date"));
                consultation.setTime(rs.getString("consultation.time"));
                ucList.add(consultation);
            }
            return ucList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Return one consultation by consultation ID
     */

    public Consultation findConsultationById(int id) {
        ResultSet rs = db.findConsultationById(id);
        try {
            if (rs.next()) {
                Consultation consultation = new Consultation();
                consultation.setPatientId(rs.getInt("patient.id"));
                consultation.setPatientName(rs.getString("patient.firstname") + " " + rs.getString("patient.lastname"));
                consultation.setId(rs.getInt("consultation.id"));
                consultation.setConclusion(rs.getString("consultation.conclusion"));
                consultation.setDescription(rs.getString("consultation.description"));
                consultation.setDate(rs.getString("consultation.date"));
                consultation.setTime(rs.getString("consultation.time"));
                return consultation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
