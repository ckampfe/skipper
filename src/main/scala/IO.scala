import scala.io.StdIn
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import java.io.FileNotFoundException

import Times._
import Log._

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.JsonDSL._

object IO {
  implicit val formats = Serialization.formats(NoTypeHints)

  def init(args: Array[String]): Unit = {
    // is the argument empty? prompt for a filename
    val log =
      args(0) match {
        case "" => readOrCreateLogFile(StdIn.readLine(s"please enter a filename: "))
        case filename => readOrCreateLogFile(filename)
      }

    prompt(log)
  }

  def prompt(oldLog: Log): Unit = {
    readOrCreateLogFile(oldLog.casename)

    // get a line from stdin and
    // replace it with "> " when we've after it
    val content = StdIn.readLine("> ")

    // print timestamp for visual feedback
    println(s"${hhmmss}")

    // create new log object
    val currentLog = oldLog.addEntry(s"${iso8601}", content)

    // write to file
    createFileWithJSON(oldLog.casename + ".json", write(currentLog))

    // recurse
    prompt(currentLog)
  }

  private def readOrCreateLogFile(fileName: String): Log = {
    def defaultLog =
      Log(
        iso8601,
        fileName,
        List(
          Entry(
            timestamp = "INIT",
            content   = "INIT"
          )
        )
      )

    val f =
      try {
        Source.fromFile(fileName + ".json")
      } catch {
        case ex: FileNotFoundException => {
          createFileWithJSON(fileName + ".json", write(defaultLog))
          Source.fromFile(fileName + ".json")
        }
      }

      try {
        read[Log](f.mkString)
      } finally {
        f.close()
      }
  }

  private def createFileWithJSON(fileName: String, content: String): Unit = {
    val file = new File(fileName)
    val writer = new PrintWriter(file)

    try {
      writer.print(content)
    } finally {
      writer.close()
    }
  }
}
