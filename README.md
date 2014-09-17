# skipper


Create a new log like so:

`$ sbt`

`> runMain SkipperMain $FILE_NAME`

Continue a log with the same command.

Entries look like:

```json
{
  "timestamp": "2014-09-08T14:35:14.358-05:00",
  "casename": "9_8_14",
  "entries": [
    {
      "timestamp": "2014-09-08T21:51:47.697-05:00",
      "content": "It's so great!"
    },
    {
      "timestamp": "2014-09-08T17:19:26.451-05:00",
      "content": "Writing tests."
    },
    {
      "timestamp": "2014-09-08T15:00:38.226-05:00",
      "content": "Doing some work on this feature."
    },
    {
      "timestamp": "2014-09-08T14:43:14.886-05:00",
      "content": "Had a quick phone call with Bob, back now."
    },
    {
      "timestamp": "2014-09-08T14:35:14.358-05:00",
      "content": "Starting work on something great."
    }
  ]
}
```
