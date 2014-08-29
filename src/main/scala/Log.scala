case class Log(
  timestamp: String,
  casename: String,
  entries: List[Entry]
) {
  def addEntry(timestamp: String, content: String): Log = this match {
    // initial case: return
    case Log(
      timestamp,
      casename,
      List(Entry("INIT", "INIT"))
    ) => Log(timestamp, casename, List(Entry(timestamp, content)))

    // general case: prepend the new entry
    case Log(
      createdAt,
      casename,
      entries
    ) => Log(createdAt, casename, Entry(timestamp, content) :: entries)
  }
}

case class Entry(
  timestamp: String,
  content: String
)
