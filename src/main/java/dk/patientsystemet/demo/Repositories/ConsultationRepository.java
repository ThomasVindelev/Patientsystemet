package dk.patientsystemet.demo.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultationRepository {

    @Autowired
    DBConnect dbConnect;

}
