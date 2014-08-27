import com.github.nscala_time.time.Imports._

object Times {
  def hhmmss: String = {
    val formatter = DateTimeFormat.forPattern("HH:MM:SS")
    formatter.print(DateTime.now)
  }

  def ISO8601: String = DateTime.now.toString
}
