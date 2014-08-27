import IO._

object SkipperMain {
  def main(args: Array[String]) {
    val log: Log = IO.init(args)
    IO.prompt(log)
  }
}
