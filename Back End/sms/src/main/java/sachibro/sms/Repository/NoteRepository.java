package sachibro.sms.Repository;

import org.springframework.data.repository.CrudRepository;
import sachibro.sms.Model.Notes;

import java.util.ArrayList;

public interface NoteRepository extends CrudRepository<Notes, Integer> {
    Notes save(Notes notes);
    ArrayList<Notes> getAllByCourseAndType(String Course, String Type);
    Notes getByCourseAndTypeAndDate(String Course, String Type, String Date);
}
