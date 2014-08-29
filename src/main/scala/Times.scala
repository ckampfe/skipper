import com.github.nscala_time.time.Imports._

object Times {
  def hhmmss: String = {
    val formatter = DateTimeFormat.forPattern("HH:mm:ss")
    formatter.print(DateTime.now)
  }

  def iso8601: String = DateTime.now.toString
}
