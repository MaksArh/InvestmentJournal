import com.example.finwise.models.lesson.Lesson
import com.example.finwise.models.lesson.Step

data class User(val id: Int, val name: String, val finishedLessons: List<Lesson>)