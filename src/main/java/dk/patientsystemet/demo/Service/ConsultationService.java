package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Consultation;
import dk.patientsystemet.demo.Model.Patient;
import dk.patientsystemet.demo.Repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ConsultationRepository db;

    public List<Consultation> getConsultations(int id) throws SQLException {
        ResultSet rs = db.findConsultations(id);
        List<Consultation> consultationList = new ArrayList<>();
        while (rs.next()) {
            Consultation consultation = new Consultation();
            consultation.setId(rs.getInt("consultation.id"));
            consultation.setDescription(rs.getString("consultation.description"));
            consultation.setDate(rs.getString("consultation.date"));
            consultationList.add(consultation);
        }
        return consultationList;
    }

}
