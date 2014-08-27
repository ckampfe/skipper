import scala.io.StdIn
import Times._

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.JsonDSL._

case class Log(timestamp: String, casename: String, entries: List[Entry])
case class Entry(timestamp: String, content: String)

object IO {
  implicit val formats = Serialization.formats(NoTypeHints)

  def init(args: Array[String]): Log = {
    Log(
      timestamp = ISO8601,
      casename  = args(0),
      entries   = List(
        Entry(
          timestamp = "INIT",
          content   = "INIT"
        )
      )
    )
  }

  def prompt(previous: Log) {
    // get a line from stdin
    val ln = StdIn.readLine(text = s"${hhmmss} > ")

    // create an entry object
    val entry = Entry(timestamp = s"$ISO8601", content = ln)

    // write it to the log
    val log = previous match {

      // handle the initial case
      case Log(_, _, List(Entry("INIT", "INIT"))) =>
        Log(
             timestamp = s"$ISO8601",
             casename = "temp",
             entries = List(entry)
           )

      // handle the general case
      case Log(timestamp, casename, entries) =>
        Log(timestamp, casename, entry :: entries)
    }

    println(write(log))

    // recurse
    prompt(log)
  }
}
